package network.handlers;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.handler.codec.ByteToMessageDecoder;
import datatypes.VLQ;
import network.Packet;
import network.StarboundStream;
import network.KnownPackets;
import network.packets.PassThroughPacket;
import starnub.StarNub;
import util.Zlib;

import java.util.List;


public class PacketDecoder extends ByteToMessageDecoder {

    private final String sbRemoteHost = "127.0.0.1";
    private final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");
    StarboundStream mainStream;
    private Packet packet;
    private long vlqvalue;
    private boolean compressed;
    private VLQ vlq;
    private byte packetId;
    private boolean firstCycle = true; /* Byte Buffer Cycle */
    private ChannelHandlerContext clientCTX;
    private ChannelHandlerContext serverCTX;
    private volatile Channel outboundChannel;
	
	public PacketDecoder(){
	}
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object> out) throws Exception {
        if (bb.readableBytes() <= 1) {
            return; /* No readable bytes */
        }

        if (firstCycle) /* We only want to perform these functions once */ {
            bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */

            mainStream = new StarboundStream(bb);

            packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved*/

            vlq = mainStream.readSignedVLQ();

            vlqvalue = vlq.getValue();

            compressed = vlq.getValue() < 0;

            if (compressed) {
                vlqvalue = -vlqvalue;
            }
        }

        if (bb.readableBytes() < vlqvalue) //didn't get all of the data
        {
            firstCycle = false; /* Not enough bytes in buffer */
            return;
        }

        byte[] data = mainStream.getBuf().readBytes((int) vlqvalue).array();

        if (compressed) {
            data = new Zlib().decompress(data);
        }

        ByteBuf payload = Unpooled.copiedBuffer(data);

        StarboundStream stream = new StarboundStream(payload);

        KnownPackets packetType = KnownPackets.getKnownPackets(packetId);

        try {
            packet = packetType.makeNewPacket();
        } catch (Exception e) {
            //DEBUG
//			System.out.println("DEBUG: Error creating packet class.");
            firstCycle = true;
        }

        if (PassThroughPacket.class.equals(packet.getClass())) ;
        {
            packet.setPacketId(packetId);
        }

        packet.Read(stream);
        
        /* Routing */
        packet.setClientCTX(clientCTX);
        packet.setServerCTX(serverCTX);

        //DEBUG
        System.out.println("Packet: " + packet);

        ClientSidePacketQue.ClientPacketQue.put(packet);
        firstCycle = true;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clientCTX = ctx;
        /* Get this channels contexts for later use so that we can tie the
    	 * starnub.StarNub Client Connector into the same event loop*/
        final Channel inboundChannel = ctx.channel();
    	
    	/* We are setting up a client Bootstrap. This will be used to 
    	 * connect starnub.StarNub to the Starbound Server*/
        Bootstrap starNubMainOutboundSocket = new Bootstrap();
        
    	/* We are calling on the server bootstrap to configure it */
        starNubMainOutboundSocket
        
        	/* We are assigning this bootstrap to use this channels thread 
        	 * when creating the next channel */
                .group(inboundChannel.eventLoop())
        	
        	/* We are connecting this channel to the channel being created.
        	 * In short we are extending it */
                .channel(ctx.channel().getClass())

                .option(ChannelOption.TCP_NODELAY, true)
        	
        	/* This handler we are currently in is handling the data from the 
        	 * Starbound clients to the the Client Socket. Below this Handler
        	 * is being added in order for the client socket to be able to 
        	 * receive data from Starbound Server and forward it back on to 
        	 * the Starbound Client.
        	 * */
                .handler(new ServerPacketDecoder(clientCTX));
        
        	/* This channel future is setting up a response to a event that has 
        	 * not happened yet. When the channel connects then the 
        	 * ChannelFutureListener will respond and start working */
        ChannelFuture f = starNubMainOutboundSocket.connect(sbRemoteHost, sbRemotePort);
        outboundChannel = f.channel();
        serverCTX = outboundChannel.pipeline().firstContext();
    	
    }

}

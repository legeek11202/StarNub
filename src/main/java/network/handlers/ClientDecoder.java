package network.handlers;

import datatypes.VLQ;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.handler.codec.ByteToMessageDecoder;
import network.KnownPackets;
import network.Packet;
import network.StarboundStream;
import network.packets.PassThroughPacket;
import starnub.StarNub;
import util.Zlib;

import java.util.List;

public class ClientDecoder extends ByteToMessageDecoder
{
    private final String sbRemoteHost = "127.0.0.1";
    private final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");
    private Packet packet;
    private long vlqvalue;
    private boolean compressed;
    private VLQ vlq;
    private byte packetId;
    private boolean firstCycle = true; /* Byte Buffer Cycle */
    private ChannelHandlerContext serverCTX;
    private ChannelHandlerContext clientCTX;

    /* Exceptions */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object>out) throws Exception
    {
        if (bb.readableBytes() <= 1)
        {
            return; /* No readable bytes */
        }

        StarboundStream mainStream = new StarboundStream(bb);

        if (firstCycle) /* We only want to perform these functions once */
        {
            bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */

            packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved*/

            vlq = mainStream.readSignedVLQ();

            vlqvalue = vlq.getValue();

            compressed = vlq.getValue() < 0;

            if (compressed)
            {
                vlqvalue = -vlqvalue;
            }
        }

        if (bb.readableBytes() < vlqvalue) //didn't get all of the data
        {
            firstCycle  = false; /* Not enough bytes in buffer */
            return;
        }

        byte[] data = mainStream.getBuf().readBytes((int) vlqvalue).array();

        if (compressed)
        {
            data = new Zlib().decompress(data);
        }

        KnownPackets packetType = KnownPackets.getKnownPackets(packetId);

        try
        {
            packet = packetType.makeNewPacket();
        }
        catch (Exception e)
        {
            System.out.println("Client Decoder: Error creating packet class.");
            firstCycle  = true;
        }

        if (PassThroughPacket.class.equals(packet.getClass())){
            packet.setPacketId(packetId);
        }

        packet.Read(new StarboundStream(Unpooled.copiedBuffer(data)));

        //DEBUG
//        System.out.println("DEBUG: Packet: "+packet);

        /* Setting the packet contexts so we know where to route it after processing */
        packet.setServerCTX(serverCTX);
        packet.setClientCTX(clientCTX);

        /* Add packet to client processing que */
        ClientPacketProcessor.ClientPacketQue.add(packet);
        firstCycle  = true;

    }

    //TODO heartbeat packet bypass the processor que

    /* Executes once when handler is added */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        /* When handler is added we need to set our own CTX from this side of the connection */
        clientCTX = ctx;
        /* Get this channels contexts for later use so that we can tie the
    	 * StarNub Client Connector into the same event loop*/
        final Channel inboundChannel = ctx.channel();

    	/* We are setting up a client Bootstrap. This will be used to
    	 * connect StarNub to the Starbound Server*/
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
                .handler(new ServerDecoder(clientCTX));

        	/* This channel future is setting up a response to a event that has
        	 * not happened yet. When the channel connects then the
        	 * ChannelFutureListener will respond and start working */
        ChannelFuture f = starNubMainOutboundSocket.connect(sbRemoteHost, sbRemotePort);
        Channel outboundChannel = f.channel();
        serverCTX = outboundChannel.pipeline().firstContext();

    }
}



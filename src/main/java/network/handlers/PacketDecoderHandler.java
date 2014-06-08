package network.handlers;

import datatypes.VLQ;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.handler.codec.ByteToMessageDecoder;
import network.StarboundStream;
import network.packets.KnownPackets;
import network.packets.Packet;
import network.packets.packettypes.PassThroughPacket;
import network.util.PacketEncoder;
import reactor.core.Reactor;
import starnub.StarNub;
import util.Zlib;

import java.util.List;

public class PacketDecoderHandler extends ByteToMessageDecoder
{

//            //DEBUG Print
//            if (connectionSide == 0) { System.out.println("DEBUG SERVER DECODE: "); }
//            else if (connectionSide == 1)  { System.out.println("DEBUG CLIENT DECODE: "); }

    /* Connection Variable */
    private final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");

    /* Used for routing internally */
    private ChannelHandlerContext clientCTX;
    private ChannelHandlerContext serverCTX;
    private ChannelHandlerContext oppositeCTX;

    /* Decoding fields */
    private boolean firstCycle = true;
    private VLQ vlq;
    final private int connectionSide;
    private byte packetId;
    private long vlqValue;
    private boolean compressed;
    private Packet packet;
    private Reactor reactor;

    /* Client side decoder constructor */
    public PacketDecoderHandler(int i) {
        this.connectionSide = i;
    }

    /* Server side decoder constructor */
    private PacketDecoderHandler(int i, ChannelHandlerContext clientCTX) {
        this.clientCTX = clientCTX;
        this.connectionSide = i;
        this.oppositeCTX = clientCTX;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object>out) throws Exception
    {
        /* If not enough bytes in buffer - start over - Starbound >= 3 Byte Packets */
        if (in.readableBytes() <= 3) {
                    return;
        }

        /* First cycle we will set all these fields, if not enough bytes these will not be set over */
        if (firstCycle) {

            /* Packet ID - used to derive packet class */
            packetId = (byte) in.readUnsignedByte();
            /* VLQ is used to get VLQ.value which is payload length */
            vlq = VLQ.signedFromBuffer(in);
            /* Payload Length */
            vlqValue = vlq.getValue();
            /* If the payload is compressed it will be negative */
            compressed = vlq.getValue() < 0;
            /* Remove the Sign pre-operation of payload */
            if (compressed) {
                vlqValue = -vlqValue;
            }
        }

        /* Not enough bytes in buffer */
        if (in.readableBytes() < vlqValue)
        {
            /* Do not want to set fields over */
            firstCycle = false;
            return;
        }

        /* Try and make a packet of a type using reflection - KnownPackets Enum */
        try {
            packet = KnownPackets.getKnownPackets(packetId).makeNewPacket();
        } catch (Exception e) {
            //DEBUG Print - REMOVE ONCE WE CATCH EXCEPTION AND HANDLE WITH IF / RESET STATEMENT
            if (connectionSide == 0) { System.out.println("DEBUG SERVER DECODE: Could not create packet."); }
            else if (connectionSide == 1)  { System.out.println("DEBUG CLIENT DECODE: Could not create packet."); }
            e.printStackTrace();
            System.out.println(packet);
        }

        /* TODO REMOVE ONCE ALL PACKET CLASSES ARE COMPLETE */
        if (PassThroughPacket.class.equals(packet.getClass()))
        {
            packet.setPacketId(packetId);
        }

        /* In order to reduce the amount of object creation we split the compressed and not compressed packet read */
        if (compressed) {
            packet.Read(new StarboundStream(Unpooled.copiedBuffer(new Zlib().decompress(in.readBytes((int) vlqValue).array()))));
        } else {
            /* Non compressed */
            packet.Read(new StarboundStream(in.readBytes((int) vlqValue)));
        }

        //TODO INSERT PACKET LOGIC HERE USING REACTOR EVENTS BASED ON CLASS
        // Notify consumers of the 'parse' topic that data is ready
        // by passing a Supplier<Event<T>> in the form of a lambda

//        reactor.notify(packet.getClass(), (Event.wrap(packet)));
//        //DEBUG REACTOR works
//

        /* We will write packet out to the other end */ //TODO - Create a group flush, remove individual flushing
    oppositeCTX.writeAndFlush(PacketEncoder.PacketToMessageEncoder(packet));
    /* Reset all values to prevent errors */
    clear();
}

    void clear() {
        firstCycle = true;
        vlq = null;
        packetId = 0;
        vlqValue = 0;
        compressed =  false;
        packet = null;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        // This factory call creates a Reactor.
//        reactor = Reactors.reactor()
//                .env(StarNub.env) // our current Environment
//                .dispatcher(Environment.EVENT_LOOP) // use one of the BlockingQueueDispatchers
//                .get(); // get the object when finished configuring
//
//        @Inject
//        Service service;
//
//        reactor.on(T(ClientConnectPacket.class), (Event<ClientConnectPacket> packet)->{
//            System.out.println(packetId+"Received");
//        });
//
//        reactor.on(T(ChatReceivedPacket.class), service::handleEvent);



        if (connectionSide == 1) {
            this.serverCTX = ctx;
        }
        if (connectionSide == 0) {
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
                /* We are using a pooled buffer so we do not have to constantly alloc/dealloc*/
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        	/* This handler we are currently in is handling the data from the
        	 * Starbound clients to the the Client Socket. Below this Handler
        	 * is being added in order for the client socket to be able to
        	 * receive data from Starbound Server and forward it back on to
        	 * the Starbound Client. 1 Indicates Server Sides
        	 * */
                    .handler(new PacketDecoderHandler(1, clientCTX));

        	/* This channel future is setting up a response to a event that has
        	 * not happened yet. When the channel connects then the
        	 * ChannelFutureListener will respond and start working */
            String sbRemoteHost = "127.0.0.1";
            ChannelFuture f = starNubMainOutboundSocket.connect(sbRemoteHost, sbRemotePort);
            Channel outboundChannel = f.channel();
            serverCTX = outboundChannel.pipeline().firstContext();
            oppositeCTX = serverCTX;
        }

    }
}
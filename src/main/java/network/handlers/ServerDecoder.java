package network.handlers;

import datatypes.VLQ;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import network.KnownPackets;
import network.Packet;
import network.StarboundStream;
import network.packets.PassThroughPacket;
import util.Zlib;

import java.util.List;

public class ServerDecoder extends ByteToMessageDecoder
{

    private Packet packet;
    private long vlqvalue;
    private boolean compressed;
    private VLQ vlq;
    private byte packetId;
    private boolean firstCycle = true; /* Byte Buffer Cycle */
    private ChannelHandlerContext serverCTX;
    private ChannelHandlerContext clientCTX;

    public ServerDecoder(ChannelHandlerContext clientCTX) {
        this.clientCTX = clientCTX;
    }

    /* Executes once when handler is added */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        serverCTX = ctx;
    }

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

        StarboundStream mainStream = new StarboundStream(bb);//TODO see if any other way will be better then multiple object creation for large packets

        if (firstCycle) /* We only want to perform these functions once */
        {
            bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */

            packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved */

            vlq = mainStream.readSignedVLQ();

            vlqvalue = vlq.getValue();

            compressed = vlq.getValue() < 0;

            if (compressed)
            {
                vlqvalue = -vlqvalue;
            }
        }

        if (bb.readableBytes() < vlqvalue) /* didn't get all of the data */
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
            System.out.println("Server Decoder: Error creating packet class.");
            firstCycle  = true; //TODO Correct this, will not start over if unable to not make correct packet
        }

            if (PassThroughPacket.class.equals(packet.getClass())){
            packet.setPacketId(packetId);
         }

        packet.Read(new StarboundStream(Unpooled.copiedBuffer(data)));

        /* Setting the packet contexts so we know where to route it after processing */
        packet.setServerCTX(serverCTX);
        packet.setClientCTX(clientCTX);

        //DEBUG
//        System.out.println("DEBUG: Packet: "+packet);


        ServerPacketProcessor.ServerPacketQue.add(packet);
        firstCycle  = true;
    }
}




//TODO heartbeat packet bypass the processor que

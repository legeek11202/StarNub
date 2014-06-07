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

public class PacketDecoder extends ByteToMessageDecoder
{

    private Packet packet;
    private long vlqvalue;
    private boolean compressed;
    private VLQ vlq;
    private byte packetId;
    private boolean firstCycle = true; /* Byte Buffer Cycle */
    StarboundStream mainStream;

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object>out) throws Exception
    {
        if (bb.readableBytes() <= 1)
        {
            return; /* No readable bytes */
        }

        if (firstCycle) /* We only want to perform these functions once */
        {
            bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */

            mainStream = new StarboundStream(bb);

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

        ByteBuf payload = Unpooled.copiedBuffer(data);

        StarboundStream stream = new StarboundStream(payload);

        KnownPackets packetType = KnownPackets.getKnownPackets(packetId);

        try
        {
            packet = packetType.makeNewPacket();
        }
        catch (Exception e)
        {
            //DEBUG
			System.out.println("DEBUG: Error creating packet class.");
            firstCycle  = true;
        }
        //DEBUG
//        System.out.println("Before Check: Packet ID From ByteBuf: "+packetId);
//        System.out.println("Before Check: Packet ID From Packet Class: "+packet.getPacketId());

        if (PassThroughPacket.class.equals(packet.getClass())){
            packet.setPacketId(packetId);
            //DEBUG
//            System.out.println("Passthrough Check: Packet ID From ByteBuf: "+packetId);
//            System.out.println("Passthrough Check: Packet ID From Packet Class: "+packet.getPacketId());
        }

//        System.out.println("After Check: Packet ID From ByteBuf: "+packetId);
//        System.out.println("After Check: Packet ID From Packet Class: "+packet.getPacketId());

        packet.Read(stream);

        //DEBUG
        System.out.println("DEBUG: Packet: "+packet);

        out.add(packet);
        firstCycle  = true;
    }
}

package org.starnub.network.util;

import org.starnub.StarNub;
import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.network.packets.KnownPackets;
import org.starnub.network.packets.Packet;
import org.starnub.network.packets.PassThroughPacket;
import org.starnub.util.Zlib;

import io.netty.buffer.ByteBuf;

public class StreamDecoderEncoder {

	public StreamDecoderEncoder()
	{
		// TODO Auto-generated constructor stub
	}

	public Packet StreamDecode(ByteBuf bb)
	{
		System.out.println("Decoder Start");
		if (bb.readableBytes() <= 1)
		{ /* If no readable bytes */
			System.out.println("Readable Bytes: "+bb.readableBytes());
			return null;
		}
		
		bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */
		
		StarboundStream mainStream = new StarboundStream(bb);
		
		byte packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved*/
		
		VLQ vlq = mainStream.readSignedVLQ();
		
		long vlqvalue = vlq.getValue();

		int len = vlq.getLength();
		
		boolean compressed = vlq.getValue() < 0;
		
		if (compressed)
		{ 
			vlqvalue = -vlqvalue; 
		}
		
		if (bb.readableBytes() < vlqvalue) //didn't get all of the data
		{
			bb.resetReaderIndex();
			return null;
		}
	
		ByteBuf data = mainStream.getBuf().readBytes((int) vlqvalue);
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+mainStream.getBuf().readableBytes()); }
		
		if (compressed)
		{ 
			System.out.println("Decompressing");
			byte[] tmp = new Zlib().decompress(data.array()); 
			data.writeBytes(tmp);
		}
		
		StarboundStream stream = new StarboundStream(data);
		
		System.out.println("Decoder Starting Packet");
		Packet packet = null;
		KnownPackets packetType = KnownPackets.getKnownPackets(packetId);
		System.out.println("Packet Type: "+packetType);
		if (packetType == null)
		{
			return null;
		}
		else if (packetType != null)
        {
			packet = packetType.makeNewPacket();
			System.out.println("Packet: "+packet);	
        }
//        else if (packetId >= 0 && packetId <= 100)
//        {
//        	packet = new PassThroughPacket();
//        }
        if (packet != null)
        {
			if (packet.getClass() == PassThroughPacket.class);
			{
				packet.setPacketId(packetId);
			}
        	packet.setIsReceive(true);
        	packet.Read(stream);
        }
        return packet;
	}
	
	public ByteBuf StreamEncode(Object packet)
	{
		return null;
	}
}

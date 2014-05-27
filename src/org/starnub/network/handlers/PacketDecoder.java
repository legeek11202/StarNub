package org.starnub.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.network.packets.KnownPackets;
import org.starnub.network.packets.Packet;
import org.starnub.network.packets.PassThroughPacket;
import org.starnub.util.Zlib;

public class PacketDecoder extends ByteToMessageDecoder
{

	private Packet packet;

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object>out) throws Exception
	{	
		if (bb.readableBytes() <= 1)
		{ 	/* If no readable bytes */
			return;
		}
		
		bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */
		
		StarboundStream mainStream = new StarboundStream(bb);
		
		byte packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved*/
		
		VLQ vlq = mainStream.readSignedVLQ();
		
		long vlqvalue = vlq.getValue();
		
		boolean compressed = vlq.getValue() < 0;
		
		if (compressed)
		{ 
			vlqvalue = -vlqvalue; 
		}
		
		if (bb.readableBytes() < vlqvalue) //didn't get all of the data
		{
			bb.resetReaderIndex();
			return;
		}
	
		byte[] data = mainStream.getBuf().readBytes((int) vlqvalue).array();
		
		if (compressed)
		{ 
			data = new Zlib().decompress(data); 
		}

		ByteBuf paylaod = Unpooled.copiedBuffer(data);
		
		StarboundStream stream = new StarboundStream(paylaod);

		KnownPackets packetType = KnownPackets.getKnownPackets(packetId);

		if (packetType == null)
		{
			return;
		}
		else
        {		
			packet = packetType.makeNewPacket();
			//DEBUG
//        	System.out.println("Packet: "+packet);
				if (PassThroughPacket.class.equals(packet.getClass()));
				{
					packet.setPacketId(packetId);
				}
        	packet.setIsReceive(true);
        	packet.Read(stream);
        }
		out.add(packet);
		}	
}

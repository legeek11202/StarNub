package org.starnub.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.compression.ZlibDecoder;

import java.util.List;

import org.starnub.StarNub;
import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.network.tempPacket;
import org.starnub.util.Zlib;

public class PacketDecoder extends ByteToMessageDecoder
{
	
	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		if (StarNub.Debug.ON)
		{
			System.out.println("Debug: Packet Decoder: Handler Added.");
		}
	}
	
    /* Executes once when channel becomes active */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception 
    {	
    
    }
	
	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object>out) throws Exception
	{	
		if (bb.readableBytes() <= 1) /* If no readable bytes */
			return;
		
		bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */
		
		StarboundStream stream = new StarboundStream(bb);
		
		byte packetId = (byte) stream.getBuf().readUnsignedByte();/* Reader has moved*/
		
		VLQ vlq = stream.readSignedVLQ();
		
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
			return;
		}
	
		byte[] data = stream.getBuf().readBytes((int) vlqvalue).array();
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+stream.getBuf().readableBytes()); }
		
		if (compressed)
		{ 
			System.out.println("Decompressing");
			data = new Zlib().decompress(data); 
		}
		
		System.out.println("Packet ID:"+packetId);
		System.out.println("VLQ: "+vlq);
		System.out.println("VLQ Length: "+len);
		System.out.println("VLQ Value: "+vlqvalue);
		System.out.println("Compressed?: "+compressed);

		
		tempPacket packet = new tempPacket(packetId,vlq,data);
		out.add(packet);
	   	if (StarNub.Debug.ON) {System.out.println("Debug: Packet Decoder: End. ");}
	}
}

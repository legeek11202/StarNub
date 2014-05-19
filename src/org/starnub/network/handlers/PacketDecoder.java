package org.starnub.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.compression.ZlibDecoder;

import java.util.List;

import org.starnub.StarNub;
import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.util.Zlib;

public class PacketDecoder extends ByteToMessageDecoder
{
	
    /* Executes once when channel becomes active */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception 
    {
    	ctx.writeAndFlush(StarNub.serverVersion);
    	if (StarNub.Debug.ON) {System.out.println("Debug: Packet Decoder: Server version sent to client.");}
    }
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object> out) throws Exception
	{
		
		if (bb.readableBytes() <= 1) /* If no readable bytes */
			return;
		
		bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */
		
		StarboundStream stream = new StarboundStream(bb);
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+stream.getBuf().readableBytes()); }
		
		byte packetId = stream.getBuf().readByte();/* Reader has moved*/
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+stream.getBuf().readableBytes()); }
		
		VLQ vlq = stream.readSignedVLQ();
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+stream.getBuf().readableBytes()); }
		
		long vlqvalue = vlq.getValue();
		
		int len = vlq.getLength();
		
		boolean compressed = vlq.getValue() < 0;
		
		if (compressed)
		{ 
			vlqvalue = -vlqvalue; 
		}
		
		if (bb.readableBytes() < vlq.getValue()) //didn't get all of the data
		{
			bb.resetReaderIndex();
			return;
		}
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+stream.getBuf().readableBytes()); }
		
		System.out.println("Packet ID:"+packetId);
		System.out.println("VLQ: "+vlq);
		System.out.println("VLQ Length: "+len);
		System.out.println("VLQ Value: "+vlqvalue);
		System.out.println("Compressed?: "+compressed);
		
		byte[] data = stream.getBuf().readBytes(stream.getBuf().readableBytes()).array();
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+stream.getBuf().readableBytes()); }
		
		if (compressed)
		{ 
			System.out.println("Decompress");
			data = new Zlib().decompress(data); 
		}
		
		
	   	if (StarNub.Debug.ON) {System.out.println("Debug: Packet Decoder: End. ");}
	}
}

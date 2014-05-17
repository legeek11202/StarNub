package org.starnub.network.handlers;

import java.util.List;

import org.starnub.StarNub;
import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.util.Zlib;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.compression.ZlibDecoder;

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
		
		byte packetId = stream.getBuf().readByte();/* Reader has moved*/
		
		VLQ vlq = stream.readSignedVLQ();
		
		int len;
		
		len = vlq.getLength() + 1;
		
		long vlqvalue = vlq.getValue();
		
		boolean compressed = vlqvalue < 0;
		
		if (compressed)
		{
			len = Math.abs(len);
		}
		
		if (bb.readableBytes() < vlqvalue) /* Did not get all the data */
		{
			bb.resetReaderIndex();
			return;
		}
		
//		byte[] data = bb.readBytes(len).array();
		byte[] data = stream.readByteArray();
//		byte[] data = new byte[len];
		
		
		if (compressed)
		{
			data = Zlib.decompress(data);
		}
		
		System.out.println(packetId);
		System.out.println(vlq);
		System.out.println(len);
		System.out.println(vlqvalue);
		System.out.println(compressed);
		System.out.println(data);
		System.out.println(out);
		
	   	if (StarNub.Debug.ON) {System.out.println("Debug: Packet Decoder: Packet ID: "+packetId+". Packet Length: "+vlq+". Data: "+data+".");}
	}
}

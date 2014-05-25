package org.starnub.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

import org.starnub.StarNub;

public class PacketEncoder extends MessageToByteEncoder {
	
	
	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		if (StarNub.Debug.ON)
		{
			System.out.println("Debug: Packet Encoder: Handler Added.");
		}
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception
	{
		System.out.println("Encoder Out: \n"+msg);	
		
		
		
		
	}
}

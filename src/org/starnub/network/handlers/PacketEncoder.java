package org.starnub.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

import org.starnub.StarNub;

public class PacketEncoder extends MessageToByteEncoder {
	
		@Override
		protected void encode(ChannelHandlerContext ctx, Object out, ByteBuf bb)
				throws Exception
		{
			System.out.println("Encoder Out: \n"+out);
			
		}
}

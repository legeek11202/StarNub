package org.starnub.network.handlers;

import org.starnub.StarNub;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Backend extends ChannelInboundHandlerAdapter  {

	private final Channel inboundChannel;

	public Backend(Channel inboundChannel) 
	{
		this.inboundChannel = inboundChannel;
	}
	
	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		if (StarNub.Debug.ON)
		{
			System.out.println("Debug: Backend: Handler Added.");
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception 
	{
		ctx.read();
		ctx.write(Unpooled.EMPTY_BUFFER);
	}

	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception 
	{
		inboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() 
		{
			@Override
			public void operationComplete(ChannelFuture future) throws Exception 
			{
				if (future.isSuccess()) 
				{
					ctx.channel().read();
				} 
				else 
				{
					future.channel().close();
				}
			}
		});
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception 
	{
		Frontend.closeOnFlush(inboundChannel);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
		cause.printStackTrace();
		Frontend.closeOnFlush(ctx.channel());
	}
	
}

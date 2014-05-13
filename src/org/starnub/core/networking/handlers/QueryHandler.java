package org.starnub.core.networking.handlers;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/* This class is place holder for future query attempts and information 
* will need a disconnect packet and possible get play information*/
public class QueryHandler extends ChannelInboundHandlerAdapter  {

    	/* Transmitting */
   	@Override
    	public void channelActive(ChannelHandlerContext ctx) throws Exception 
    	{
	}
    
	 /* Receiving */
	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception 
	{
	}
    
	/* When receiving is complete */
	 @Override
    	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception 
    	{
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
    	}
	
	@Override
    	public void channelInactive(ChannelHandlerContext ctx) 
    	{
    	}
}

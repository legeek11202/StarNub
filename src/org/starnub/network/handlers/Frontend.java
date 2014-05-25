package org.starnub.network.handlers;

import java.net.SocketAddress;

import org.starnub.StarNub;
import org.starnub.network.ProxyServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Frontend extends ChannelInboundHandlerAdapter  {
    
    private final String sbRemoteHost;
    private final int sbRemotePort;
    private volatile Channel outboundChannel;
    
    public Frontend(String remoteHost, int remotePort) 
    {
        this.sbRemoteHost = remoteHost;
        this.sbRemotePort = remotePort;
    }
	
    /* Executes once when handler is added */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		if (StarNub.Debug.ON)
		{
			System.out.println("Debug: Frontend: Handler Added.");
		}
	}
  
    /* Executes once when channel becomes active */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception 
    {
    	/* Get this channels contexts for later use so that we can tie the 
    	 * StarNub Client Connector into the same event loop*/
    	final Channel inboundChannel = ctx.channel();
    	
    	/* We are setting up a client Bootstrap. This will be used to 
    	 * connect StarNub to the Starbound Server*/
    	Bootstrap starNubMainOutboundSocket = new Bootstrap();
        
    	/* We are calling on the server bootstrap to configure it */
        starNubMainOutboundSocket
        
        	/* We are assigning this bootstrap to use this channels thread 
        	 * when creating the next channel */
        	.group(inboundChannel.eventLoop())
        	
        	/* We are connecting this channel to the channel being created.
        	 * In short we are extending it */
        	.channel(ctx.channel().getClass())
        	
        	
        	/* This handler we are currently in is handling the data from the 
        	 * Starbound clients to the the Client Socket. Below this Handler
        	 * is being added in order for the client socket to be able to 
        	 * receive data from Starbound Server and forward it back on to 
        	 * the Starbound Client.
        	 * */
        	.handler(new Backend(inboundChannel));
        
        	/* This channel future is setting up a response to a event that has 
        	 * not happened yet. When the channel connects then the 
        	 * ChannelFutureListener will respond and start working */
        	ChannelFuture f = starNubMainOutboundSocket.connect(sbRemoteHost, sbRemotePort);
        	outboundChannel = f.channel();
        	f.addListener(new ChannelFutureListener() 
        	{            
        		@Override
        		public void operationComplete(ChannelFuture future) throws Exception 
        		{
        			if (future.isSuccess()) 
        			{
        				/* If StarNub connects to the Starbound Server this will execute */
        				if (StarNub.Debug.ON) {System.out.println("Debug: Frontend: Connected to Starbound Server.");}
        				/* We want to add this channel to the Global list */
        				StarNub.clientChannels.add(ctx.channel());
        				if (StarNub.Debug.ON) {System.out.println("Debug: Frontend: Player joined current channels"+StarNub.clientChannels);}
        				/* Start Processing Data */
        				inboundChannel.read();
        			} 
        			else 
        			{
        				/* Close the connection if the connection attempt has */
        				inboundChannel.close();
        			}
        		}
        	});
	}
    
	/* Receiving Data */
	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception 
	{
		if (outboundChannel.isActive()) 
		{	
			outboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() 
			{
				@Override
				public void operationComplete(ChannelFuture future) throws Exception 
				{
					if (future.isSuccess()) 
					{
						/* Was able to flush out data, start to read the next chunk */
						ctx.channel().read();
					} 
					else 
					{
						future.channel().close();
					}
				}
			});
		}
	}
    
	/* Inactive Channel */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception 
    {
    	if (outboundChannel != null) 
    	{
    		/* Removing this channel from the Global List */
    		StarNub.clientChannels.remove(ctx.channel());
    		if (StarNub.Debug.ON) {System.out.println("Debug: Frontend: Player quit current channels"+StarNub.clientChannels);}
    		/* Remove player from the Global Player list */ // TODO Complete player removal from list
//    		String remoteIp = ctx.channel().remoteAddress().toString();
//    		String connectingIp = remoteIp.substring(1,remoteIp.lastIndexOf(":"));
    		closeOnFlush(outboundChannel);
    	}
	}

    /* Exceptions */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
		cause.printStackTrace();
		System.out.println("Channel exception");	
    }
    
    static void closeOnFlush(Channel ch) 
    {
    	if (ch.isActive()) 
    	{
    		ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    	}
	}
  
}

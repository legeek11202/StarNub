package org.starnub.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

public class QueryServer {
	
	private static boolean status = true;
	
	public QueryServer() 
	{
	}
	
	public static Boolean serverStatus (int type)
	{
		serverQuery(type);
		return status;
	}
	
	private static void serverQuery (int type)
	{
		
    	final String sbRemoteHost = "127.0.0.1";
    	final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");
    	int txAttemps = 0; /* We want to attempt to connect several times */
	
    	do
    	{
    		
    	/* We are going to use one group of threads. */
        EventLoopGroup queryGroup = new NioEventLoopGroup(1); 
        	try 
        	{
        	/* Initiate a client bootstrap for the server */
        	Bootstrap snTxQuerySb = new Bootstrap();
        		snTxQuerySb /* Configuring the Bootstrap */
        		.group(queryGroup) /* Any channels Spawned will use this thread pool */				
        		.channel(NioSocketChannel.class) /* Creates a channel Instance */
        		.handler(new QueryInitializer()); /* Handler to work with channel events */			
        		try 
        		{
        			ChannelFuture f = snTxQuerySb.connect(sbRemoteHost, sbRemotePort).sync();
        			f.addListener(new ChannelFutureListener() 
        			{        
        				@Override
        				public void operationComplete(ChannelFuture future) throws Exception 
        				{
        					if (future.isSuccess()) 
        					{
        						if (StarNub.Debug.ON) {System.out.println("Debug: Server Query: Server Check. Status: Responsive");}
        						status = true; /* If connection is made server is responsive */
        						f.channel().close(); /* Closing Connection */
        					} 
        					else 
        					{
        						if(type == 1) { if (StarNub.Debug.ON) {System.out.println("Debug: Server Query: Server Check. Status: Unresponsive");} }
        						status = false; /* Connection not made server is not responsive */
        						f.channel().close(); /* Closing Connection */
        					}
        				}
        			});
        			f.channel().closeFuture().sync();	
			
        		}
        		catch (Exception e) 
        		{
        			if(type == 1) { if (StarNub.Debug.ON) {System.out.println("Debug: Server Query: Server Check. Status: Unresponsive");} }
        			status = false; /* Connection not made server is not responsive */
        		}
        	}
        	finally
        	{
        		queryGroup.shutdownGracefully();
        	} 
        	
        	if (!status)
        	{
        		if(type == 1) /* Query attempt for status check */
        		{ 
        			txAttemps += 1; /* Decrement tries */
        			new ThreadSleep().timer(10); /* Wait until retry */
        			MessageFormater.msgPrint(StarNub.language.getString("sb.q.1")+" ("+txAttemps+"/12).", 0, 1); 
        		}
        		else if (type ==2) /* Server coming online */
        		{
        			new ThreadSleep().timer(5); /* Shorter wait due to nature*/
        		}
        	}
    	} while (!status && txAttemps < 12);
    }
}



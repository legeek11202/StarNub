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
	
	public static Boolean serverStatus ()
	{
		serverQuery();
		return status;
	}
	
	private static void serverQuery ()
	{
    	final String sbRemoteHost = "127.0.0.1";
    	final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");
    	
    	int txAttemps = 0; /* We want to attempt to connect several times */
		do
    	{
    		
    	/* We are going to use one group of threads. Default's 50.*/
        EventLoopGroup queryGroup = new NioEventLoopGroup(); 
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
        						if (StarNub.Debug.ON) {System.out.println("Debug: Server Query: Server Check. Status: Unresponsive");}
        						status = false; /* Connection not made server is not responsive */
        						f.channel().close(); /* Closing Connection */
        					}
        				}
        			});
        			f.channel().closeFuture().sync();	
			} 
        	catch (Exception e) 
        	{
				if (StarNub.Debug.ON) {System.out.println("Debug: Server Query: Server Check. Status: Unresponsive");}
				status = false; /* Connection not made server is not responsive */
			}
        	}
        finally
        {
        	queryGroup.shutdownGracefully();
        } 
        /* We will decrement the tries and wait some seconds */
        if (!status)
        {
        	txAttemps += 1; new ThreadSleep().timer(10);
        	MessageFormater.msgPrint(StarNub.language.getString("sb.q.1")+" ("+txAttemps+"/12).", 0, 1);
        }
    	} while (!status && txAttemps < 12);
    }
}



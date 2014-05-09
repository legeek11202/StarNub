package org.starnub.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.starnub.StarNub;

/*
 * TODO: Information
 */
public class SN_Server implements Runnable {

		public synchronized void run()
	    {

	    	final int snServerPort = StarNub.configVariables.get("StarNub_Port");
	    	final String sbRemoteHost = "127.0.0.1";
	    	final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");

	    	/* We are making two thread groups, each with X Threads per Group */
	        EventLoopGroup bossGroup = new NioEventLoopGroup(500); 
	        EventLoopGroup workerGroup = new NioEventLoopGroup(500); 

	        try 
	        {
	        	/* Initiate the server bootstrap for the server */
	        	ServerBootstrap starNubMainInboundSocket = new ServerBootstrap();
	            
	        	/* Here we are calling on the server bootstrap to configure it */
	        	try 
	        	{
					starNubMainInboundSocket
					
						/* Sets the Parent and Child Threads */
						.group(bossGroup, workerGroup)  	
						
						/* Creates a channel Instance */
						.channel(NioServerSocketChannel.class) 
						
						/* Initializes the channel and calls the Server Initializer to 
						 * set up this channels handlers 
					 	 * */
						.childHandler(new SN_ServerInitializer(sbRemoteHost, sbRemotePort))
						
						/* Finally we are going to bind the Server Socket */
						.bind(snServerPort).sync().channel().closeFuture().sync();
				} 
	        	catch (InterruptedException e) 
	        	{
					e.printStackTrace();
				}
	        } 
	        finally
	        {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }
	}
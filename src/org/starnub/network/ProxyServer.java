package org.starnub.network;

import java.util.HashMap;
import java.util.Map;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.starnub.StarNub;
import org.starnub.network.packets.PacketData;
import org.starnub.network.packets.PacketData.KnownPackets;

/*
 * TODO: Information																		
 * 
 */


public class ProxyServer implements Runnable {

		public static KnownPackets[] packetType = KnownPackets.values();
		
		public void run()
	    {

	    	final int snServerPort = StarNub.configVariables.get("StarNub_Port");
	    	final String sbRemoteHost = "127.0.0.1";
	    	final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");

	    	/* We are making two thread groups, each with X Threads per Group */
	        EventLoopGroup bossGroup = new NioEventLoopGroup(); 
	        EventLoopGroup workerGroup = new NioEventLoopGroup(500); 

	        	/* Initiate the server bootstrap for the server */
	        	
	        	/* Here we are calling on the server bootstrap to configure it */
	        	try 
	        	{
	        	ServerBootstrap starNubInbound_TCP_Socket = new ServerBootstrap();

					try 
					{
						starNubInbound_TCP_Socket
						
							/* Sets the Parent and child threads for the socket and then 
							 * channels created per client connection*/
							.group(bossGroup, workerGroup)  	
							
							/* Creates a channel Instance */
							.channel(NioServerSocketChannel.class) 
							
							
							/* Initializes the channel and calls the Server Initializer to 
							 * set up this channels handlers 
						 	 * */
							.childHandler(new ProxyServerInitializer(sbRemoteHost, sbRemotePort))
							
							/* Finally we are going to bind the Server Socket */
							.bind(snServerPort).channel().closeFuture().sync();

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
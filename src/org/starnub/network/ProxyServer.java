package org.starnub.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.starnub.StarNub;

/**
 * This class opens a Server Socket on a configured TCP Port. The
 * NioEventLoopGroup specifies a specific amount of threads. The default amount
 * of threads are (CPUs * 2).
 * <p>
 * Credit goes to Netty.io (Asynchronous API) examples.
 * <p>
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 26 May 2014
 *          <br>
 *         
 *
 */

public class ProxyServer implements Runnable {

	private final int snServerPort = StarNub.configVariables.get("StarNub_Port");
	
	@Override
	public void run()
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		EventLoopGroup handlerGroup = new NioEventLoopGroup();
		
		try
		{
			/* Initiate the server bootstrap */
			ServerBootstrap starNubInbound_TCP_Socket = new ServerBootstrap();
			starNubInbound_TCP_Socket
					/* Acceptor thread, Worker thread */
					.group(bossGroup, workerGroup)
					/* Channel instance */
					.channel(NioServerSocketChannel.class)
					/* Server Initializer to set up this channels handlers */
					.childHandler(
							 /* Bind the Server Socket */
							new ProxyServerInitializer())
					.bind(snServerPort).channel().closeFuture().sync();
			
			//FIXME Set buffer to larger amount
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
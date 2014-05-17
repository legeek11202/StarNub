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
 * Credit to Netty.io (Asynchronous API) examples.
 * <p>
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1
 *          <p>
 *          v2 Will support UDP pass through
 * 
 */

public class ProxyServer implements Runnable {

	final int snServerPort = StarNub.configVariables.get("StarNub_Port");
	final String sbRemoteHost = "127.0.0.1";
	final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");

	@Override
	public void run()
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try
		{
			/* Initiate the server bootstrap */
			ServerBootstrap starNubInbound_TCP_Socket = new ServerBootstrap();
			try
			{
				starNubInbound_TCP_Socket
						/* Bootstrap configuration */
						.group(bossGroup, workerGroup)
						/* Acceptor thread, Worker thread */
						.channel(NioServerSocketChannel.class)
						/* Channel instance */
						/* Server Initializer to set up this channels handlers */
						.childHandler(
								new ProxyServerInitializer(sbRemoteHost,
										sbRemotePort)).bind(snServerPort)
						.channel().closeFuture().sync(); /*
														 * Bind the Server
														 * Socket
														 */
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} finally
		{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
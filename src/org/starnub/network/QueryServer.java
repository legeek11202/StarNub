package org.starnub.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.starnub.StarNub;
import org.starnub.managment.SbQueryProcessor;
import org.starnub.network.handlers.PacketDecoder;
import org.starnub.network.handlers.ProtocolVersionHandler;

/**
 * This class will start a client connection to the Starbound server and get a
 * handshake which will tell us if the server is responsive or not.
 * <p>
 * Credit goes to Netty.io (Asynchronous API) examples.
 * <p>
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 26 May 2014
 */

public class QueryServer {

	public QueryServer()
	{
	}

	public static void serverStatus()
	{
		serverQuery();
	}

	private static void serverQuery()
	{
		final String sbRemoteHost = "127.0.0.1";
		final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");

			/* Using one group of threads */
			EventLoopGroup queryGroup = new NioEventLoopGroup(1);
			try
			{
				/* Client Bootstrap */
				Bootstrap snTxQuerySb = new Bootstrap();
				snTxQuerySb /* Configuring the Bootstrap */
				.group(queryGroup)
				.channel(NioSocketChannel.class) /* Creates a channel Instance */
				.handler(new ChannelInitializer<Channel>() {
					/* Initializer to set up handlers for this channel */
					@Override
					public void initChannel(Channel ch) throws Exception
					{
						/* Inbound Handler */
						ch.pipeline().addFirst("PacketDecoder", new PacketDecoder());
						ch.pipeline().addAfter("PacketDecoder", "ProtocolVersion", new ProtocolVersionHandler());
					}})
				.connect(sbRemoteHost,sbRemotePort).channel().closeFuture().sync();
					
			} 
			catch (Exception e)
			{
				SbQueryProcessor.setStatus(false);
			}
			finally
			{
				queryGroup.shutdownGracefully();
			}
	}
}

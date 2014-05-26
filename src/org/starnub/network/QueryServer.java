package org.starnub.network;

import java.util.List;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import org.starnub.ServerVersion;
import org.starnub.StarNub;
import org.starnub.datatypes.VLQ;
import org.starnub.network.packets.Packet;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

/**
 * This class will start a client connection to the Starbound server and get a
 * handshake which results in the server sending a Protocol Packet in which we
 * will capture for later use.
 * <p>
 * Credit goes to Netty.io (Asynchronous API) examples.
 * <p>
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
 */

public class QueryServer {

	private static boolean status = true;

	public QueryServer()
	{
	}

	public static boolean serverStatus()
	{
		serverQuery(); return status;
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
						ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { });
					}});
				try
				{
					ChannelFuture f = snTxQuerySb.connect(sbRemoteHost,
							sbRemotePort).sync();
					f.addListener(new ChannelFutureListener() {
						@Override
						public void operationComplete(ChannelFuture future)
								throws Exception
						{
							if (future.isSuccess())
							{
								if (StarNub.Debug.ON)//FINAL_REMOVE
								{	
									System.out.println("Debug: Server Query: Server Check. Status: 3 Way Handshake Complete.");
									status = true; 
									f.channel().close(); 
								}
							} 
							else
							{
								status = false; 
								f.channel().close(); 
							}
						}
					});
					f.channel().closeFuture().sync();
				} catch (Exception e)
				{
					status = false;
				}
			} 
					finally
			{
				queryGroup.shutdownGracefully();
			}
	}
}

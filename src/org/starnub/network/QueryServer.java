package org.starnub.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

/**
 * This class will start a client conenction to the Starbound server and get a
 * handshake which results in the server sending a Protocol Packet in which we
 * will capture for later use.
 * <p>
 * Credit to Netty.io (Asynchronous API) examples.
 * <p>
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1
 * 
 */

public class QueryServer {

	private static boolean status = true;

	public QueryServer()
	{
	}

	public static Boolean serverStatus(int type)
	{
		serverQuery(type);
		return status;
	}

	private static void serverQuery(int type)
	{

		final String sbRemoteHost = "127.0.0.1";
		final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");
		int txAttemps = 0; /* We want to attempt to connect several times */

		do
		{

			/* Using one group of threads */
			EventLoopGroup queryGroup = new NioEventLoopGroup(1);
			try
			{
				/* Client Bootstrap */
				Bootstrap snTxQuerySb = new Bootstrap();
				snTxQuerySb /* Configuring the Bootstrap */
				.group(queryGroup) /*
									 * Any channels Spawned will use this thread
									 * pool
									 */
				.channel(NioSocketChannel.class) /* Creates a channel Instance */
				.handler(new ChannelInitializer<Channel>() {
					/* Initializer to set up handlers for this channel */
					@Override
					public void initChannel(Channel ch) throws Exception
					{
						/* Inbound Handler */
						ch.pipeline().addLast(
								new ChannelInboundHandlerAdapter() {
									/* Receiving Data */
									@Override
									public void channelRead(
											final ChannelHandlerContext ctx,
											Object msg) throws Exception
									{
										Object serverVersion = StarNub.serverVersion;
										if (serverVersion == null)
										{
											StarNub.serverVersion = msg;
											if (StarNub.Debug.ON)
											{
												System.out
														.println("Debug: Server Query: Server Check. Status: Responsive.");
											}
											status = true; /*
															 * If connection is
															 * made server is
															 * responsive
															 */
										}
									}

									/* When receiving is complete */
									@Override
									public void channelReadComplete(
											ChannelHandlerContext ctx)
											throws Exception
									{
										ctx.close(); /* Closing Connection */
									}

									@Override
									public void exceptionCaught(
											ChannelHandlerContext ctx,
											Throwable cause) throws Exception
									{
										/* Do nothing */
									}
								});
					}
				});

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
								if (StarNub.Debug.ON)
								{
									System.out
											.println("Debug: Server Query: Server Check. Status: 3 Way Handshake Complete.");
								}
							} else
							{
								if (type == 1)
								{
									if (StarNub.Debug.ON)
									{
										System.out
												.println("Debug: Server Query: Server Check. Status: Unresponsive");
									}
								}
								status = false; /*
												 * Connection not made server is
												 * not responsive
												 */
								f.channel().close(); /* Closing Connection */
							}
						}
					});
					f.channel().closeFuture().sync();
				} catch (Exception e)
				{
					if (type == 1)
					{
						if (StarNub.Debug.ON)
						{
							System.out
									.println("Debug: Server Query: Server Check. Status: Unresponsive.");
						}
					}
					status = false; /*
									 * Connection not made server is not
									 * responsive
									 */
				}
			} finally
			{
				queryGroup.shutdownGracefully();
			}

			if (!status)
			{
				if (type == 1) /* Query attempt for status check */
				{
					txAttemps += 1; /* Decrement tries */
					new ThreadSleep().timer(10); /* Wait until retry */
					MessageFormater.msgPrint(
							StarNub.language.getString("sb.q.1") + " ("
									+ txAttemps + "/12).", 0, 1);
				} else if (type == 2) /* Server coming online */
				{
					new ThreadSleep().timer(5); /* Shorter wait due to nature */
				}
			}
		} while (!status && txAttemps < 12);
	}
}

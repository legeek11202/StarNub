package org.starnub.network.handlers;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;

public class ConnectionInspector extends ChannelInboundHandlerAdapter {

	private final String	sbRemoteHost;
	private final int		sbRemotePort;

	private String[]		bannedIps	= StarNub.bannedIps;
	private String[]		bannedUuids	= StarNub.bannedUuids;

	public ConnectionInspector(String sbRemoteHost, int sbRemotePort)
	{
		this.sbRemoteHost = sbRemoteHost;
		this.sbRemotePort = sbRemotePort;
	}

	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		String remoteIp = ctx.channel().remoteAddress().toString();
		String connectingIp = remoteIp.substring(1, remoteIp.lastIndexOf(":"));
		if (StarNub.Debug.ON)
		{
			System.out.println("Debug: Connection Inspector: IP Connecting: "
					+ connectingIp + ".");
		}
		for (String bannedip : bannedIps)
		{
			if (connectingIp.equalsIgnoreCase(bannedip))
			{
				MessageFormater.msgPrint(StarNub.language.getString("banned")
						+ " IP: " + connectingIp + ".", 0, 0);
				/* Send to Logger */
				// TODO Logger
				/* Send a client disconnect with reason */
				// TODO Write packet for above
				/* Close channel due to Banned IP */
				ChannelFuture future = ctx.close();
				future.addListener(new ChannelFutureListener() {
					public void operationComplete(ChannelFuture future)
					{
						if (future.isSuccess())
						{
							if (StarNub.Debug.ON)
							{
								System.out
										.println("Debug: Connection Inspector: IP Connecting was banned but we closed the channel."
												+ connectingIp + ".");
							}
						} else if (StarNub.Debug.ON)
						{
							System.out
									.println("Debug: Connection Inspector: IP Connecting was banned but we could not close the channel."
											+ connectingIp + ".");
						}
					}
				});
			} else
			{
				/* Needs PacketInspector *Maybe* */
				/* Needs Encoder */
				ctx.pipeline().addBefore("Frontend", "PacketEncoder", new PacketEncoder());
				ctx.pipeline().addLast("Frontend", new Frontend(sbRemoteHost, sbRemotePort));
			}
		}
	}

	// @Override
	// public void channelRead(ChannelHandlerContext ctx, //TODO) throws
	// Exception
	// {
	// /* Since the connecting IP and UUID were not banned we will open the
	// proxy through */
	// ctx.pipeline().addLast("Frontend", new ProxyFrontendHandler(sbRemoteHost,
	// sbRemotePort));
	// /* We will also add the IP, UUID, Player Name and CTX and place it in the
	// online players */
	// //TODO player online insertion
	// }
}

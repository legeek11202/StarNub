package org.starnub.network.handlers;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
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

	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		if (StarNub.Debug.ON) { System.out.println("Debug: Connection Inspector: Handler Added."); }
		
		InetAddress connectingIp = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress();

		int banned = 0;
		
		if (StarNub.Debug.ON) { System.out.println("Debug: Connection Inspector: IP Connecting: "+connectingIp + "."); }
		
		try
		{
			for (InetAddress bannedip : StarNub.bannedIps)
			{
				System.out.println(bannedip);
				if (bannedip.equals(connectingIp))
				{
					banned = 1;
				}
			}	
		}
		catch (NullPointerException e)
		{
			/* No IPs are in the ban list */
		}
		switch (banned)
			{
				case 0: 
					/* IP is not banned, send the client the server version */
					ctx.writeAndFlush(StarNub.serverVersion);
					if (StarNub.Debug.ON) {System.out.println("Debug: Packet Decoder: Server version sent to client.");}
					/* Add the regular PacketDecoder to channel */
					ctx.pipeline().addLast("PacketDecoder", new PacketDecoder());
					/* Remove this handler as it is no longer needed */
					ctx.pipeline().remove(this);
					break;
				case 1: 
					MessageFormater.msgPrint(StarNub.language.getString("banned")
							+ " IP: " + connectingIp + ".", 0, 0);
					/* Send to Logger */
					// TODO Logger
					/* Send a client disconnect with reason */
					// TODO Write packet for above??
					/* Close channel due to Banned IP */
					ctx.close();
					break;
			}
	}
}
package org.starnub.network.handlers;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.UUID;

import org.starnub.StarNub;
import org.starnub.network.packets.ClientConnectPacket;
import org.starnub.util.stream.MessageFormater;

public class BanCheck {

	
	public static InetAddress ipChecker(ChannelHandlerContext ctx)
	{
		InetAddress connectingIp = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress();
		try
		{
			for (InetAddress bannedip : StarNub.bannedIps)
			{
				if (bannedip.equals(connectingIp))
				{
					isBanned(ctx, "ip", connectingIp);
					break;
				}
			}	
		}
		catch (NullPointerException e)
		{
			/* No IPs are in the ban list */
		}
		ctx.pipeline().addLast("Frontend", new Frontend());
		ctx.pipeline().addBefore("Frontend", "PacketEncoder", new PacketEncoder());
		return connectingIp;
	}
	
	public static Object uuidChecker(ChannelHandlerContext ctx, Object msg, InetAddress connectingIp)
	{
		if (ClientConnectPacket.class.equals(msg.getClass()))
		{
			ClientConnectPacket connectPacket = (ClientConnectPacket) msg;
		 	UUID connectingUuid = UUID.nameUUIDFromBytes(connectPacket.getUUID());
			try
				{
						for (UUID bannedUuid : StarNub.bannedUuids)
						{
							System.out.println(bannedUuid);
							if (bannedUuid.equals(connectingUuid))
							{
								/* Close channel due to Banned IP */
								isBanned(ctx, "uuid", connectingIp);
								break;
							}	
						}
				}
				catch (NullPointerException e)
				{
					/* No UUIDs are in the ban list */
				}
				System.out.println(connectingIp.toString());
				//TODO add Backend context
			 	String playername = connectPacket.getPlayerName();
				TempClient clientRecord = new TempClient(playername,connectingUuid,connectingIp,ctx);
				msg = (Object) connectPacket;
				/* Remove this handler as it is no longer needed */
			}
		//DEBUG
		
		/* Add packet, Out sends to next handler */
		return (msg);
	}
	
	public static void isBanned(ChannelHandlerContext ctx, String reason, InetAddress connectingIp)
	{
		MessageFormater.msgPrint(StarNub.language.getString("banned")
				+ " IP: " + connectingIp + ".", 0, 0);
		/* Send to Logger */
		// TODO Logger
		/* Send a client disconnect with reason */
		// TODO Write packet for above??
		/* Close channel due to Banned IP */
		ctx.close();
	}
}

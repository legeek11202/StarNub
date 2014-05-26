package org.starnub.network.handlers;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.UUID;

import org.starnub.StarNub;
import org.starnub.network.packets.ClientConnectPacket;
import org.starnub.util.stream.MessageFormater;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;


public class ConnectionInspector extends MessageToMessageDecoder {
	
	private boolean	uuidBanned;
	private boolean	uuidChecked;
	private InetAddress	connectingIp;

	public ConnectionInspector(InetAddress connectingIp)
	{
		this.connectingIp = connectingIp;
	}

	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{

	}

	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List out)
			throws Exception
	{
		//TODO UUID BAN CHECK
		if (StarNub.Debug.ON) {System.out.println("Debug: Connection Inspector: Check UUID Ban Start.");}
		
		if (ClientConnectPacket.class.equals(msg.getClass()))
		{
			System.out.println("Debug: Connection Inspector: Checking UUID Ban.");
			ClientConnectPacket connectPacket = (ClientConnectPacket) msg;
			byte[] uuidBytes = connectPacket.getUUID();
		 	UUID connectingUuid = UUID.nameUUIDFromBytes(uuidBytes);
		 	String playername = connectPacket.getPlayerName();
			System.out.println("UUID: "+connectingUuid);
			try
				{
						for (UUID bannedUuid : StarNub.bannedUuids)
						{
							System.out.println(bannedUuid);
							if (bannedUuid.equals(connectingUuid))
							{
								/* Close channel due to Banned IP */
								// TODO add banned IP message
								BannedPlayer.isBanned(ctx, "uuid", connectingIp);
								uuidBanned = true;
								break;
							}	
						}
						uuidChecked = true;
				}
				catch (NullPointerException e)
				{
					/* No UUIDs are in the ban list */
				}
				if (StarNub.Debug.ON) {System.out.println("Debug: Connection Inspector: End.");}
				TempClient playernames = new TempClient(playername,connectingUuid,connectingIp,ctx);
				msg = (Object) connectPacket;
				System.out.println("Removing Inspector");
				ctx.pipeline().remove(this);
			}
		/* Remove this handler as it is no longer needed */
		out.add(msg);
	}
}
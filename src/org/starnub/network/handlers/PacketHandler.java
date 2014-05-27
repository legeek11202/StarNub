package org.starnub.network.handlers;

import java.net.InetAddress;
import java.util.List;

import org.starnub.StarNub;
import org.starnub.network.packets.Packet;
import org.starnub.util.stream.MessageFormater;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class PacketHandler extends MessageToMessageDecoder{

	private InetAddress	connectingIp;
	byte packetId;
	private Packet packet;
	private TempClient PlayerRecord;
	private String playerName;
	
	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		connectingIp = BanCheck.ipChecker(ctx);
	}
	
	/* On Handler Add */
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception 
	{
		
		if (playerName != null) 
		{ 
//			ServerMessaging.playerDisconnect(playerName,ctx); 
			MessageFormater.msgPrint(PlayerRecord.getPlayerName() + " has disconnected on IP (" + (connectingIp.toString().substring(1, connectingIp.toString().length())) + ").", 0, 0);
		}
		try 
		{
			/* Remove player from the Global Player list */ 
			StarNub.playersOnline.remove(PlayerRecord.getPlayerName(), PlayerRecord);
			/* Removing this channel from the Global List */
			StarNub.clientChannels.remove(ctx.channel());
		}
		catch (NullPointerException e)
		{
			/* Do nothing because no channel or player */
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception 
	{
		packet = (Packet) msg;
		packetId = packet.getPacketId();
		
		//DEBUG
//		System.out.println(packetId);

		switch (packetId)
		{
			/* Most common packets will be at top */
			/* C -> S */
			case 7: 	 
			{	
				PacketStats.ClientConnectPacket++; 
				PlayerRecord = BanCheck.uuidChecker(ctx, packet, connectingIp); 
				/* TODO Move this to server side packet on the server side decoder
				 * This will be a minimal intercepter
				 */
				playerName = PlayerRecord.getPlayerName();
				StarNub.playersOnline.put(playerName, PlayerRecord);
				if (playerName != null) 
				{ 
//					ServerMessaging.playerConnect(playerName,ctx); 
					MessageFormater.msgPrint(playerName + " has connected on IP (" + (connectingIp.toString().substring(1, connectingIp.toString().length())) + ").", 0, 0);
				}
				/* If StarNub connects to the Starbound Server this will execute */
				//TODO Move to decoder
				StarNub.clientChannels.add(ctx.channel());
			}
			case 8: 	 {PacketStats.ClientDisconnectPacket++; out.add(packet); break;}
			case 9: 	 {PacketStats.HandshakeResponsePacket++; out.add(packet); break;}
			
			case 11: 	 
			{
				PacketStats.ChatSentPacket++; 
				out.add(packet); break;
			}
			case 12: 	 {PacketStats.CelestialRequestPacket++; out.add(packet); break;}
			case 25: 	 {PacketStats.ModifyTileListPacket++; out.add(packet); break;}
			case 26: 	 {PacketStats.DamageTilePacket++; out.add(packet); break;}
			case 27: 	 {PacketStats.DamageTileGroupPacket++; out.add(packet); break;}
			case 28: 	 {PacketStats.RequestDropPacket++; out.add(packet); break;}
			case 29: 	 {PacketStats.SpawnEntityPacket++; out.add(packet); break;}
			case 30: 	 {PacketStats.EntityInteractPacket++; out.add(packet); break;}
			case 33: 	 {PacketStats.OpenContainerPacket++; out.add(packet); break;}
			case 34: 	 {PacketStats.CloseContainerPacket++; out.add(packet); break;}
			case 35: 	 {PacketStats.SwapContainerPacket++; out.add(packet); break;}
			case 36: 	 {PacketStats.ItemApplyContainerPacket++; out.add(packet); break;}
			case 37: 	 {PacketStats.StartCraftingContainerPacket++; out.add(packet); break;}
			case 38: 	 {PacketStats.StopCraftingContainerPacket++; out.add(packet); break;}
			case 39: 	 {PacketStats.BurnContainerPacket++; out.add(packet); break;}
			case 40: 	 {PacketStats.ClearContainerPacket++; out.add(packet); break;}
			case 41: 	 {PacketStats.WorldClientStateUpdatePacket++; out.add(packet); break;}
			case 46: 	 {PacketStats.StatusEffectRequestPacket++; out.add(packet); break;}
			case 47: 	 {PacketStats.UpdateWorldPropertiesPacket++; out.add(packet); break;}

			/* Both <-> */
			case 13: 	 {PacketStats.ClientContextUpdatePacket++; out.add(packet); break;}
			case 48: 	 {PacketStats.HeartbeatPacket++; out.add(packet); break;}
			case 42: 	 {PacketStats.EntityCreatePacket++; out.add(packet); break;}
			case 43: 	 {PacketStats.EntityUpdatePacket++; out.add(packet); break;}
			
			/* S -> C */
			case 0: 	 {PacketStats.ProtocolVersionPacket++; out.add(packet); break;}
			case 1: 	 {PacketStats.ConnectionResponsePacket++; out.add(packet); break;} 
			case 2: 	 {PacketStats.DisconnectResponsePacket++; out.add(packet); break;} 
			case 3: 	 {PacketStats.HandshakeChallengePacket++; out.add(packet); break;} 
			case 4: 	 
			{
				PacketStats.ChatReceivedPacket++; 
				out.add(packet); break;
			}
			case 5: 	 {PacketStats.UniverseTimeUpdatePacket++; out.add(packet); break;} /* S -> C*/
			case 6: 	 {PacketStats.CelestialResponsePacket++; out.add(packet); break;} /* S -> C*/
			case 10: 	 {PacketStats.WarpCommandPacket++; out.add(packet); break;}
			case 14: 	 {PacketStats.WorldStartPacket++; out.add(packet); break;}
			case 15: 	 {PacketStats.WorldStopPacket++; out.add(packet); break;}
			case 16: 	 {PacketStats.TileArrayUpdatePacket++; out.add(packet); break;}
			case 17: 	 {PacketStats.TileUpdatePacket++; out.add(packet); break;}
			case 18: 	 {PacketStats.TileLiquidUpdatePacket++; out.add(packet); break;}
			case 19: 	 {PacketStats.TileDamageUpdatePacket++; out.add(packet); break;}
			case 20: 	 {PacketStats.TileModificationFailurePacket++; out.add(packet); break;}
			case 21: 	 {PacketStats.GiveItemPacket++; out.add(packet); break;}
			case 22: 	 {PacketStats.SwapContainerResultPacket++; out.add(packet); break;}
			case 23: 	 {PacketStats.EnvironmentUpdatePacket++; out.add(packet); break;}
			case 24: 	 {PacketStats.EntityInteractResultPacket++; out.add(packet); break;}
			case 44: 	 {PacketStats.EntityDestroyPacket++; out.add(packet); break;}
			case 45: 	 {PacketStats.DamageNotificationPacket++; out.add(packet); break;}
			
			/* Unknown <-> */
			case 31: 	 {PacketStats.ConnectWirePacket++; out.add(packet); break;}
			case 32: 	 {PacketStats.DisconnectAllWiresPacket++; out.add(packet); break;}
		
		}
	}
}

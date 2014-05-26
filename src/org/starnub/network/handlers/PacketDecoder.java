package org.starnub.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetAddress;
import java.util.List;

import org.starnub.StarNub;
import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.network.packets.KnownPackets;
import org.starnub.network.packets.Packet;
import org.starnub.network.packets.PassThroughPacket;
import org.starnub.util.Zlib;

public class PacketDecoder extends ByteToMessageDecoder
{
	private InetAddress	connectingIp;
	private boolean	ipBanned;
	byte packetId;
	PacketStats ps = StarNub.ps;
	private Packet	packet;
	
	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		connectingIp = BanCheck.ipChecker(ctx);
	}

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object>out) throws Exception
	{	
		if (bb.readableBytes() <= 1)
		{ 	/* If no readable bytes */
			return;
		}
		
		bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */
		
		StarboundStream mainStream = new StarboundStream(bb);
		
		byte packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved*/
		
		VLQ vlq = mainStream.readSignedVLQ();
		
		long vlqvalue = vlq.getValue();

		int len = vlq.getLength();
		
		boolean compressed = vlq.getValue() < 0;
		
		if (compressed)
		{ 
			vlqvalue = -vlqvalue; 
		}
		
		if (bb.readableBytes() < vlqvalue) //didn't get all of the data
		{
			bb.resetReaderIndex();
			return;
		}
	
		byte[] data = mainStream.getBuf().readBytes((int) vlqvalue).array();
		
		if (compressed)
		{ 
			data = new Zlib().decompress(data); 
		}

		ByteBuf paylaod = Unpooled.copiedBuffer(data);
		
		StarboundStream stream = new StarboundStream(paylaod);

		KnownPackets packetType = KnownPackets.getKnownPackets(packetId);

		if (packetType == null)
		{
			return;
		}
		else
        {			
			packet = packetType.makeNewPacket();
			//DEBUG
        	System.out.println("Packet: "+packet);
				if (PassThroughPacket.class.equals(packet.getClass()));
				{
					packet.setPacketId(packetId);
				}
        	packet.setIsReceive(true);
        	packet.Read(stream);
        }
		
		switch (packetId)
		{
			/* Most common packets will be at top */
			case 0: 	 {ps.ProtocolVersionPacket++; out.add(packet); break;}
			case 1: 	 {ps.ConnectionResponsePacket++; out.add(packet); break;}
			case 2: 	 {ps.DisconnectResponsePacket++; out.add(packet); break;}
			case 3: 	 {ps.HandshakeChallengePacket++; out.add(packet); break;}
			case 4: 	 {ps.ChatReceivedPacket++; out.add(packet); break;}
			case 5: 	 {ps.UniverseTimeUpdatePacket++; out.add(packet); break;}
			case 6: 	 {ps.CelestialResponsePacket++; out.add(packet); break;}
			case 7: 	 {ps.ClientConnectPacket++; BanCheck.uuidChecker(ctx, packet, connectingIp); out.add(packet); break;}
			case 8: 	 {ps.ClientDisconnectPacket++; out.add(packet); break;}
			case 9: 	 {ps.HandshakeResponsePacket++; out.add(packet); break;}
			case 10: 	 {ps.WarpCommandPacket++; out.add(packet); break;}
			case 11: 	 {ps.ChatSentPacket++; out.add(packet); break;}
			case 12: 	 {ps.CelestialRequestPacket++; out.add(packet); break;}
			case 13: 	 {ps.ClientContextUpdatePacket++; out.add(packet); break;}
			case 14: 	 {ps.WorldStartPacket++; out.add(packet); break;}
			case 15: 	 {ps.WorldStopPacket++; out.add(packet); break;}
			case 16: 	 {ps.TileArrayUpdatePacket++; out.add(packet); break;}
			case 17: 	 {ps.TileUpdatePacket++; out.add(packet); break;}
			case 18: 	 {ps.TileLiquidUpdatePacket++; out.add(packet); break;}
			case 19: 	 {ps.TileDamageUpdatePacket++; out.add(packet); break;}
			case 20: 	 {ps.TileModificationFailurePacket++; out.add(packet); break;}
			case 21: 	 {ps.GiveItemPacket++; out.add(packet); break;}
			case 22: 	 {ps.SwapContainerResultPacket++; out.add(packet); break;}
			case 23: 	 {ps.EnvironmentUpdatePacket++; out.add(packet); break;}
			case 24: 	 {ps.EntityInteractResultPacket++; out.add(packet); break;}
			case 25: 	 {ps.ModifyTileListPacket++; out.add(packet); break;}
			case 26: 	 {ps.DamageTilePacket++; out.add(packet); break;}
			case 27: 	 {ps.DamageTileGroupPacket++; out.add(packet); break;}
			case 28: 	 {ps.RequestDropPacket++; out.add(packet); break;}
			case 29: 	 {ps.SpawnEntityPacket++; out.add(packet); break;}
			case 30: 	 {ps.EntityInteractPacket++; out.add(packet); break;}
			case 31: 	 {ps.ConnectWirePacket++; out.add(packet); break;}
			case 32: 	 {ps.DisconnectAllWiresPacket++; out.add(packet); break;}
			case 33: 	 {ps.OpenContainerPacket++; out.add(packet); break;}
			case 34: 	 {ps.CloseContainerPacket++; out.add(packet); break;}
			case 35: 	 {ps.SwapContainerPacket++; out.add(packet); break;}
			case 36: 	 {ps.ItemApplyContainerPacket++; out.add(packet); break;}
			case 37: 	 {ps.StartCraftingContainerPacket++; out.add(packet); break;}
			case 38: 	 {ps.StopCraftingContainerPacket++; out.add(packet); break;}
			case 39: 	 {ps.BurnContainerPacket++; out.add(packet); break;}
			case 40: 	 {ps.ClearContainerPacket++; out.add(packet); break;}
			case 41: 	 {ps.WorldClientStateUpdatePacket++; out.add(packet); break;}
			case 42: 	 {ps.EntityCreatePacket++; out.add(packet); break;}
			case 43: 	 {ps.EntityUpdatePacket++; out.add(packet); break;}
			case 44: 	 {ps.EntityDestroyPacket++; out.add(packet); break;}
			case 45: 	 {ps.DamageNotificationPacket++; out.add(packet); break;}
			case 46: 	 {ps.StatusEffectRequestPacket++; out.add(packet); break;}
			case 47: 	 {ps.UpdateWorldPropertiesPacket++; out.add(packet); break;}
			case 48: 	 {ps.HeartbeatPacket++; out.add(packet); break;}
		}	
	}
}

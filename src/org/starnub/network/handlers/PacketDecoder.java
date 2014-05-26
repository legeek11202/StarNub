package org.starnub.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetAddress;
import java.net.InetSocketAddress;
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
	
	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		if (StarNub.Debug.ON) { System.out.println("Debug: Connection Inspector: Handler Added."); }
		
		InetAddress connectingIp = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress();
		
		if (StarNub.Debug.ON) { System.out.println("Debug: Connection Inspector: IP Connecting: "+connectingIp + "."); }
		
		try
		{
			for (InetAddress bannedip : StarNub.bannedIps)
			{
				System.out.println(bannedip);
				if (bannedip.equals(connectingIp))
				{
					BannedPlayer.isBanned(ctx, "ip", connectingIp );
					break;
				}
			}	
		}
		catch (NullPointerException e)
		{
			/* No IPs are in the ban list */
		}
		if (StarNub.Debug.ON) {System.out.println("Debug: Packet Decoder: Server version sent to client.");}
		ctx.pipeline().addAfter("PacketDecoder", "ConnectionInspector", new ConnectionInspector(connectingIp));
		ctx.pipeline().addLast("Frontend", new Frontend());
		ctx.pipeline().addBefore("Frontend", "PacketEncoder", new PacketEncoder());
	}
	
    /* Executes once when channel becomes active */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception 
    {	
    	
    }
	
	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object>out) throws Exception
	{	
		System.out.println("Decoder Start");
		if (bb.readableBytes() <= 1)
		{ /* If no readable bytes */
			System.out.println("Readable Bytes: "+bb.readableBytes());
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
		
		if (StarNub.Debug.ON) { System.out.println("Readable Bytes in ByteBuf: "+mainStream.getBuf().readableBytes()); }
	
		byte[] data = mainStream.getBuf().readBytes((int) vlqvalue).array();
		
		if (compressed)
		{ 
			System.out.println("Decompressing");
			data = new Zlib().decompress(data); 
		}

		ByteBuf paylaod = Unpooled.copiedBuffer(data);
		
		StarboundStream stream = new StarboundStream(paylaod);
		
		System.out.println("Decoder Starting Packet");
		Packet packet = null;
		KnownPackets packetType = KnownPackets.getKnownPackets(packetId);
		System.out.println("Packet Type: "+packetType);
		if (packetType == null)
		{
			return;
		}
		else if (packetType != null)
        {
			packet = packetType.makeNewPacket();
			System.out.println("Packet: "+packet);
			
        }
        if (packet != null)
        {
			if (PassThroughPacket.class.equals(packet.getClass()));
			{
				packet.setPacketId(packetId);
			}
        	packet.setIsReceive(true);
        	packet.Read(stream);
        }
        System.out.println(ctx.channel().pipeline().names());
		
		System.out.println(packet.getPacketId());
		byte PacketId = packet.getPacketId();
		
		out.add(packet);
	   	if (StarNub.Debug.ON) {System.out.println("Debug: Packet Decoder: End. ");}
	}
}

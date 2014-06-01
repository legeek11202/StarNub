package org.starnub.network.handlers;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.network.packets.KnownPackets;
import org.starnub.network.packets.Packet;
import org.starnub.network.packets.PassThroughPacket;
import org.starnub.util.Zlib;

public class ServerPacketDecoder extends ByteToMessageDecoder
{

	private Packet packet;
	private long vlqvalue;
	private boolean compressed;
	private VLQ vlq;
	private byte packetId;
	private boolean firstCycle = true; /* Byte Buffer Cycle */
	StarboundStream mainStream;
	private ChannelHandlerContext clientCTX;
	private ChannelHandlerContext serverCTX;
	
	public ServerPacketDecoder(ChannelHandlerContext clientCTX) {
		this.clientCTX = clientCTX;
	}

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object>out) throws Exception
	{	
		if (bb.readableBytes() <= 1)
		{ 	
			return; /* No readable bytes */
		}

		if (firstCycle) /* We only want to perform these functions once */
		{  	
			bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */

			mainStream = new StarboundStream(bb);
		
			packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved*/
		
			vlq = mainStream.readSignedVLQ();
		
			vlqvalue = vlq.getValue();
		
			compressed = vlq.getValue() < 0;
		
			if (compressed)
			{ 
				vlqvalue = -vlqvalue; 
			}
		}
		
		if (bb.readableBytes() < vlqvalue) //didn't get all of the data
		{
			firstCycle  = false; /* Not enough bytes in buffer */
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
		
		try 
		{ 
			packet = packetType.makeNewPacket(); 
		} 
		catch (Exception e) 
		{
			//DEBUG
//			System.out.println("DEBUG: Error creating packet class.");
			firstCycle  = true;
		}
		
		if (PassThroughPacket.class.equals(packet.getClass()));
		{
			packet.setPacketId(packetId);
		}
		
        packet.setIsReceive(true);
        packet.Read(stream);
        	
        /* Routing */
        packet.setClientCTX(clientCTX);
        packet.setServerCTX(serverCTX);
        
        //DEBUG
        System.out.println("Packet: "+packet);
        
        ServerSidePacketQue.ServerPacketQue.put(packet);
		firstCycle  = true;
		}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		serverCTX = ctx;
	}
}


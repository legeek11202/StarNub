package org.starnub.network.handlers;

import static io.netty.buffer.Unpooled.buffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.group.ChannelGroup;

import org.starnub.network.StarboundStream;
import org.starnub.network.packets.ChatReceivedPacket;

import org.starnub.network.packets.Packet;

public class ServerMessaging {

	public static void playerConnect()
	{
		
	}
	
	public static void playerDisconnect()
	{
		
	}
	
	public static void chatSent(Packet packet)
	{
	}
	
	public static void chatReceived(Packet packet)
	{
	}
	
	public static void test(ChannelGroup clientchannels)
	{
		//TODO Temporary
		//TODO print to console as well
		ChatReceivedPacket packet = new ChatReceivedPacket();
		packet.setChannel((byte) 1);
		packet.setName("^#ff0000;SERVER_BROADCAST");
		packet.setWorld("");
		packet.setClientId(1000);
		packet.setMessage("Please visit www.myfu.net/sbm for the server ModPack. This server has no commands yet.");
		
		ByteBuf bb1 = buffer();
		ByteBuf bb2 = buffer();
		StarboundStream mainStream = new StarboundStream(bb1);
		StarboundStream payloadStream = new StarboundStream(bb2);
		
		packet.Write(payloadStream);

		int vlqvalue = payloadStream.getBufferSize();
		
		mainStream.writeByte(packet.PacketId());

		mainStream.writeSignedVLQ(vlqvalue);
		
		mainStream.getBuf().writeBytes(payloadStream.getBuf().readBytes(vlqvalue));
		
		Object msg = mainStream.getBuf();
		
		clientchannels.write(msg);
	}
	
}

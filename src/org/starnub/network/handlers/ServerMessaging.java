package org.starnub.network.handlers;

import static io.netty.buffer.Unpooled.buffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

import org.starnub.StarNub;
import org.starnub.network.StarboundStream;
import org.starnub.network.packets.ChatReceivedPacket;
import org.starnub.network.packets.Packet;

public class ServerMessaging {
	
	public static void chatSent(Packet packet)
	{
	}
	
	public static void chatReceived(Packet packet)
	{
	}
	static ChannelGroup clientchannels = StarNub.clientChannels;
	
	public static void test()
	{
		//TODO Temporary
		//TODO print to console as well
		String name = "^#ff0000;SERVER_BROADCAST";
		String message = "Please visit www.myfu.net/sbm for the server ModPack. This server has no commands yet.";
		Object msg = packager(name, message);
		clientchannels.write(msg);
	}
	
	public static void playerConnect(String playerName, ChannelHandlerContext ctx)
	{
		String name = "^#ff0000;SERVER";
		String message = "^#00FF00;"+playerName+" has connected.";
		Object msg = packager(name, message);
		for (Channel c: clientchannels) 
		{
			 if (c != ctx.channel()) 
			 {
				 c.write(msg);
			 }
		}
	}

	public static void playerDisconnect(String playerName, ChannelHandlerContext ctx)
	{
		String name = "^#ff0000;SERVER";
		String message = "^#00FF00;"+playerName+" has disconnected.";
		Object msg = packager(name, message);
		for (Channel c: clientchannels) 
		{
			 if (c != ctx.channel()) 
			 {
				 c.write(msg);
			 }
		}
		
	}
	
	public static Object packager(String name, String message)
	{
		ChatReceivedPacket packet = new ChatReceivedPacket();
		packet.setChannel((byte) 1);
		packet.setName(name);
		packet.setWorld("");
		packet.setClientId(1000);
		packet.setWorld("");
		packet.setClientId(1000);
		packet.setMessage(message);
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
		return msg;
		
	}
	
	
}




package org.starnub.network.handlers;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import static io.netty.buffer.Unpooled.*;

import java.util.List;

import org.starnub.StarNub;
import org.starnub.datatypes.VLQ;
import org.starnub.network.StarboundStream;
import org.starnub.network.packets.Packet;

public class PacketEncoder extends MessageToMessageDecoder {
	
	/* On Handler Add */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception
	{
		if (StarNub.Debug.ON)
		{
			System.out.println("Debug: Packet Encoder: Handler Added.");
		}
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List out)
			throws Exception
	{
		System.out.println("Encoder Out: "+msg);	
		Packet packet = (Packet) msg;
		
		ByteBuf bb1 = buffer();
		ByteBuf bb2 = buffer();
		StarboundStream mainStream = new StarboundStream(bb1);
		StarboundStream payloadStream = new StarboundStream(bb2);
		
		packet.Write(payloadStream);

		int vlqvalue = payloadStream.getBufferSize();
		
		System.out.println(vlqvalue);
		
		mainStream.writeByte(packet.getPacketId());

		mainStream.writeSignedVLQ(vlqvalue);
		
		mainStream.getBuf().writeBytes(payloadStream.getBuf().readBytes(vlqvalue));
		
		System.out.println(mainStream.getBuf());
		
		out.add(mainStream.getBuf());
		
		System.out.println("Encoder End.");
	}
}

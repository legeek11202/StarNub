package org.starnub.network.handlers;


import static io.netty.buffer.Unpooled.buffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.starnub.network.StarboundStream;
import org.starnub.network.packets.Packet;

@SuppressWarnings("rawtypes")
public class PacketEncoder extends MessageToMessageDecoder {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List out)
			throws Exception
	{	
		//TODO Recomress
		Packet packet = (Packet) msg;
		
		ByteBuf bb1 = buffer();
		ByteBuf bb2 = buffer();
		StarboundStream mainStream = new StarboundStream(bb1);
		StarboundStream payloadStream = new StarboundStream(bb2);
		
		packet.Write(payloadStream);

		int vlqvalue = payloadStream.getBufferSize();
		
		mainStream.writeByte(packet.getPacketId());

		mainStream.writeSignedVLQ(vlqvalue);
		
		mainStream.getBuf().writeBytes(payloadStream.getBuf().readBytes(vlqvalue));
		
		out.add(mainStream.getBuf());
	}
}

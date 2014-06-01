package org.starnub.network.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.starnub.StarNub;
import org.starnub.managment.SbQueryProcessor;
import org.starnub.network.packets.ProtocolVersionPacket;
import org.starnub.util.stream.MessageFormater;

public class ProtocolVersionHandler extends MessageToMessageDecoder<Object> {

	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg,
			List<Object> out) throws Exception 
	{
		if (StarNub.ProtocolVersionPacket.getProtocolVersion() == 0)
		{
			ProtocolVersionPacket pvp = (ProtocolVersionPacket) msg;
			int pvpint = pvp.getProtocolVersion();
			StarNub.ProtocolVersionPacket.setProtocolVersion(pvpint);
			MessageFormater.msgPrint("Server Protocol Version: "+pvpint+".", 0, 0);
		}
		SbQueryProcessor.setStatus(true);
		ctx.close();
	}
	
    /* Exceptions */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
		cause.printStackTrace();
    }
}

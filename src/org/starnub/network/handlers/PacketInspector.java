package org.starnub.network.handlers;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import org.starnub.StarNub;
import org.starnub.network.packets.PacketData.KnownPackets;
import org.starnub.util.stream.MessageFormater;

public class PacketInspector extends ChannelInboundHandlerAdapter { 

    private final String sbRemoteHost;
    private final int sbRemotePort;

	private KnownPackets PacketType;
	private String bannedIp = "192.168.1.5";   
	
    public PacketInspector(String sbRemoteHost, int sbRemotePort) 
    {
        this.sbRemoteHost = sbRemoteHost;
        this.sbRemotePort = sbRemotePort;
    }
    
	/* Channel Start */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx)throws Exception 
    {	
	String remoteIp = ctx.channel().remoteAddress().toString();
	String connectingIp = remoteIp.substring(1,remoteIp.lastIndexOf(":"));
//	for(InetAddresses ip : bannedIPs) 
		if (connectingIp.equalsIgnoreCase(bannedIp))
		{
			MessageFormater.msgPrint(StarNub.language.getString("banned")+" IP: "+connectingIp+".", 0, 0);
			/* Send to Logger */
			//TODO Logger
			/* Send fake packet version with banned */
			//TODO Write fake packet
			 /* Close channel due to Banned IP*/
		     ChannelFuture future = ctx.channel().disconnect();
		     future.addListener(new ChannelFutureListener() 
		     {
		         public void operationComplete(ChannelFuture future) 
		         {
		        	 if (future.isSuccess())
		        	 {
		        	 MessageFormater.msgPrint("Close Sucess", 0, 0);
		        	 ctx.executor().terminationFuture();
		        	 }else{ MessageFormater.msgPrint("Not Sucess", 0, 0);}
		             // ...
		        }
		     });
		 }
		else
		{
			/* Send Server Version packet */
			
	    	ctx.pipeline().addLast("Frontend", new ProxyFrontendHandler(sbRemoteHost, sbRemotePort));
			ctx.pipeline().remove(this); /* Since the IP is authorized we will remove the handler */
		}
    }  

    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception 
	{	

	}
}

//	@Override
//	protected void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		
//	}
//    
//    @Override
//	protected void Decoder(ChannelHandlerContext ctx, ByteBuf in,
//			List<Object> out) throws Exception 
//    {
//    out.add(in.readBytes(in.readableBytes()));
////	if (StarNub.NetworkDebug.ON) 
////	{
////	ByteBuf buf = (ByteBuf) msg;
////	short packetID = buf.readUnsignedByte();
////	if (packetID >= 0 && packetID <= 48)
////	{
////		try 
////		{
////		PacketType = ProxyServer.packetType[packetID];
////		System.out.println("Network Debug: PACKET_ID: "+packetID+". PACKET_TYPE: "+PacketType+".");
////		} catch (ArrayIndexOutOfBoundsException e) {}
////	}
////	}
//    }


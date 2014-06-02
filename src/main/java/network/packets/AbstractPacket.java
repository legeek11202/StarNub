package network.packets;

import io.netty.channel.ChannelHandlerContext;
import network.StarboundStream;

public abstract class AbstractPacket {

	private byte PacketId;
	private boolean Ignore;
	private boolean IsReceive;
	private ChannelHandlerContext serverCTX;
	private ChannelHandlerContext clientCTX;
	
    public abstract void Read(StarboundStream stream);

    public abstract void Write(StarboundStream stream);
    
    public byte getPacketId()
	{
		return PacketId;
	}

	public void setPacketId(byte packetId)
	{
		PacketId = packetId;
	}

	public boolean isIgnore()
	{
		return Ignore;
	}

	public void setIgnore(boolean ignore)
	{
		Ignore = ignore;
	}

	public boolean getIsReceive()
	{
		return IsReceive;
	}
	
	public void setIsReceive(boolean isReceive)
	{
		IsReceive = isReceive;
	}
	
	public ChannelHandlerContext getServerCTX() {
		return serverCTX;
	}

	public void setServerCTX(ChannelHandlerContext serverCTX) {
		this.serverCTX = serverCTX;
	}

	public ChannelHandlerContext getClientCTX() {
		return clientCTX;
	}

	public void setClientCTX(ChannelHandlerContext clientCTX) {
		this.clientCTX = clientCTX;
	}

}

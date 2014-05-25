package org.starnub.network.packets;

import org.starnub.network.StarboundStream;

public abstract class AbstractPacket {

	private byte PacketId;
	private boolean Ignore;
	private boolean IsReceive;

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

}

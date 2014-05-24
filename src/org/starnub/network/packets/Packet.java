package org.starnub.network.packets;

import org.starnub.network.StarboundStream;

public class Packet extends AbstractPacket {

	public Packet()
	{
	}

	@Override
	void Read(StarboundStream stream)
	{
	}

	@Override
	void Write(StarboundStream stream)
	{
	}

	@Override
	public byte getPacketId()
	{
		return super.getPacketId();
	}

	@Override
	public void setPacketId(byte packetId)
	{
		super.setPacketId(packetId);
	}

	@Override
	public boolean isIgnore()
	{
		return super.isIgnore();
	}

	@Override
	public void setIgnore(boolean ignore)
	{
		super.setIgnore(ignore);
	}

	@Override
	public boolean getIsReceive()
	{
		return super.getIsReceive();
	}

	@Override
	public void setIsReceive(boolean isReceive)
	{
		super.setIsReceive(isReceive);
	}

}

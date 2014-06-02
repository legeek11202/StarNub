package org.starnub.network.packets;

import network.StarboundStream;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 24 May 2014
 * 
 */
public class ProtocolVersionPacket extends Packet {
	
	public byte PacketId()
	{
		return 0;
	}

	private int ProtocolVersion;

	public ProtocolVersionPacket()
	{
	}
	
	public ProtocolVersionPacket(int protocolVersion)
	{
		ProtocolVersion = protocolVersion;
	}

	/**
	 * @return the protocolVersion
	 */
	public int getProtocolVersion()
	{
		return ProtocolVersion;
	}

	/**
	 * @param protocolVersion
	 *            the protocolVersion to set
	 */
	public void setProtocolVersion(int protocolVersion)
	{
		ProtocolVersion = protocolVersion;
	}

	@Override
	public void Read(StarboundStream stream)
	{
        ProtocolVersion = stream.readInt();
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeInt(ProtocolVersion);
	}
}

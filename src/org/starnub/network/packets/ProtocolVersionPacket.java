package org.starnub.network.packets;

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

	public byte[] ProtocolVersion;

	public ProtocolVersionPacket()
	{
	}
	
	public ProtocolVersionPacket(byte[] protocolVersion)
	{
		ProtocolVersion = protocolVersion;
	}

	/**
	 * @return the protocolVersion
	 */
	public byte[] getProtocolVersion()
	{
		return ProtocolVersion;
	}

	/**
	 * @param protocolVersion
	 *            the protocolVersion to set
	 */
	public void setProtocolVersion(byte[] protocolVersion)
	{
		ProtocolVersion = protocolVersion;
	}
}

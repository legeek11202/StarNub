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
 * @version 1.0, 17 May 2014 (Incomplete)
 * 
 */
public class ProtocolVersionPacket {
	public byte PacketId()
	{
		return 0;
	}

	public long	ProtocolVersion;

	public ProtocolVersionPacket(long protocolVersion)
	{
		ProtocolVersion = protocolVersion;
	}

	/**
	 * @return the protocolVersion
	 */
	public long getProtocolVersion()
	{
		return ProtocolVersion;
	}

	/**
	 * @param protocolVersion
	 *            the protocolVersion to set
	 */
	public void setProtocolVersion(long protocolVersion)
	{
		ProtocolVersion = protocolVersion;
	}
}

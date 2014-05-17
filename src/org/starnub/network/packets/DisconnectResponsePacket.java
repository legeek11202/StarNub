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
public class DisconnectResponsePacket {

	public byte PacketId()
	{
		return 2;
	}

	public byte	Unknown;

	public DisconnectResponsePacket()
	{
		Unknown = 0;
	}

	/**
	 * @return the unknown
	 */
	public byte getUnknown()
	{
		return Unknown;
	}

	/**
	 * @param unknown
	 *            the unknown to set
	 */
	public void setUnknown(byte unknown)
	{
		Unknown = unknown;
	}
}

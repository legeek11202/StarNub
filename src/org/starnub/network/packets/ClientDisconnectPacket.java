package org.starnub.network.packets;

import org.starnub.network.StarboundStream;

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
public class ClientDisconnectPacket extends Packet {

	public byte PacketId()
	{
		return 8;
	}

	private byte	Unknown;

	public ClientDisconnectPacket()
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
	
	@Override
	public void Read(StarboundStream stream)
	{
		Unknown = stream.readUnsignedByte();
	}

	@Override
	public void Write(StarboundStream stream)
	{
		stream.writeByte(Unknown);
	}
}

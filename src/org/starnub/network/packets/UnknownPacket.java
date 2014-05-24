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
public class UnknownPacket extends Packet {

	public byte		PacketId;
	public boolean	Compressed;
	public byte[]	Data;
	private int		Length;

	public UnknownPacket()
	{
		PacketId = 48; // TODO Program max value of ENUM here
	}

	public UnknownPacket(boolean compressed, int length, byte packetId)
	{
		Compressed = compressed;
		Length = length;
		Data = new byte[Length];
		PacketId = packetId;
		boolean Ignore = false;
	}

	/**
	 * @return the packetId
	 */
	public byte getPacketId()
	{
		return PacketId;
	}

	/**
	 * @param packetId
	 *            the packetId to set
	 */
	public void setPacketId(byte packetId)
	{
		PacketId = packetId;
	}

	/**
	 * @return the compressed
	 */
	public boolean isCompressed()
	{
		return Compressed;
	}

	/**
	 * @param compressed
	 *            the compressed to set
	 */
	public void setCompressed(boolean compressed)
	{
		Compressed = compressed;
	}

	/**
	 * @return the data
	 */
	public byte[] getData()
	{
		return Data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data)
	{
		Data = data;
	}

	/**
	 * @return the length
	 */
	public int getLength()
	{
		return Length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length)
	{
		Length = length;
	}

	@Override
	void Read(StarboundStream stream)
	{

	}

	@Override
	void Write(StarboundStream stream)
	{

		
	}
}

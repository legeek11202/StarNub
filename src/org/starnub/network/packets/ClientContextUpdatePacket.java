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
public class ClientContextUpdatePacket {

	public byte PacketId()
	{
		return 13;
	}

	public byte[]	Data;

	// public World World; //TODO Need to add world read

	public ClientContextUpdatePacket()
	{
		Data = new byte[0];
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

	// /**
	// * @return the world
	// */
	// public World getWorld()
	// {
	// return World;
	// }
	//
	// /**
	// * @param world the world to set
	// */
	// public void setWorld(World world)
	// {
	// World = world;
	// }
}

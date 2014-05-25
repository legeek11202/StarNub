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
public class ClientContextUpdatePacket extends Packet {

	public byte PacketId()
	{
		return 13;
	}

	private byte[]	Data;

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
	
	@Override
	public void Read(StarboundStream stream)
	{
		* //TODO
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeInt8Array(Data);
	}
}

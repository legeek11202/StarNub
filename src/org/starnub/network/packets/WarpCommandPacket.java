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
public class WarpCommandPacket extends Packet {
	
	public byte PacketId()
	{
		return 10;
	}

	public WarpType			WarpType;
	public WorldCoordinate	Coordinates;
	public String			Player;

	public WarpCommandPacket()
	{
		Coordinates = new WorldCoordinate();
	}

	/**
	 * @return the warpType
	 */
	public WarpType getWarpType()
	{
		return WarpType;
	}

	/**
	 * @param warpType
	 *            the warpType to set
	 */
	public void setWarpType(WarpType warpType)
	{
		WarpType = warpType;
	}

	/**
	 * @return the coordinates
	 */
	public WorldCoordinate getCoordinates()
	{
		return Coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setCoordinates(WorldCoordinate coordinates)
	{
		Coordinates = coordinates;
	}

	/**
	 * @return the player
	 */
	public String getPlayer()
	{
		return Player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(String player)
	{
		Player = player;
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

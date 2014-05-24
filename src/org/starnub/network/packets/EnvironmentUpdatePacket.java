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
public class EnvironmentUpdatePacket extends Packet {
	
	public byte PacketId()
	{
		return 23;
	}

	public byte[]	Sky;
	public byte[]	Weather;

	public EnvironmentUpdatePacket()
	{
		Sky = new byte[0];
		Weather = new byte[0];
	}

	/**
	 * @return the sky
	 */
	public byte[] getSky()
	{
		return Sky;
	}

	/**
	 * @param sky
	 *            the sky to set
	 */
	public void setSky(byte[] sky)
	{
		Sky = sky;
	}

	/**
	 * @return the weather
	 */
	public byte[] getWeather()
	{
		return Weather;
	}

	/**
	 * @param weather
	 *            the weather to set
	 */
	public void setWeather(byte[] weather)
	{
		Weather = weather;
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

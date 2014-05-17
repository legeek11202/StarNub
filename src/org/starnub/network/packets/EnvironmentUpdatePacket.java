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
public class EnvironmentUpdatePacket {
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

}

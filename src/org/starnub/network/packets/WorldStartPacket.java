package org.starnub.network.packets;

import org.starnub.datatypes.Variant;
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
public class WorldStartPacket extends Packet {
	
	public byte PacketId()
	{
		return 14;
	}

	public Variant	Planet;
	public Variant	WorldStructure;
	public byte[]	Sky;
	public byte[]	Weather;
	public float	SpawnX;
	public float	SpawnY;
	public Variant	WorldProperties;
	public long		ClientId;
	public boolean	Local;

	/**
	 * @return the planet
	 */
	public Variant getPlanet()
	{
		return Planet;
	}

	/**
	 * @param planet
	 *            the planet to set
	 */
	public void setPlanet(Variant planet)
	{
		Planet = planet;
	}

	/**
	 * @return the worldStructure
	 */
	public Variant getWorldStructure()
	{
		return WorldStructure;
	}

	/**
	 * @param worldStructure
	 *            the worldStructure to set
	 */
	public void setWorldStructure(Variant worldStructure)
	{
		WorldStructure = worldStructure;
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

	/**
	 * @return the spawnX
	 */
	public float getSpawnX()
	{
		return SpawnX;
	}

	/**
	 * @param spawnX
	 *            the spawnX to set
	 */
	public void setSpawnX(float spawnX)
	{
		SpawnX = spawnX;
	}

	/**
	 * @return the spawnY
	 */
	public float getSpawnY()
	{
		return SpawnY;
	}

	/**
	 * @param spawnY
	 *            the spawnY to set
	 */
	public void setSpawnY(float spawnY)
	{
		SpawnY = spawnY;
	}

	/**
	 * @return the worldProperties
	 */
	public Variant getWorldProperties()
	{
		return WorldProperties;
	}

	/**
	 * @param worldProperties
	 *            the worldProperties to set
	 */
	public void setWorldProperties(Variant worldProperties)
	{
		WorldProperties = worldProperties;
	}

	/**
	 * @return the clientId
	 */
	public long getClientId()
	{
		return ClientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(long clientId)
	{
		ClientId = clientId;
	}

	/**
	 * @return the local
	 */
	public boolean isLocal()
	{
		return Local;
	}

	/**
	 * @param local
	 *            the local to set
	 */
	public void setLocal(boolean local)
	{
		Local = local;
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
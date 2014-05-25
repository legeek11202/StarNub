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

	private Variant	Planet;
	private Variant	WorldStructure;
	private byte[]	Sky;
	private byte[]	Weather;
	private float	SpawnX;
	private float	SpawnY;
	private Variant	WorldProperties;
	private int	ClientId;
	private boolean	Local;

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
	public void Read(StarboundStream stream)
	{
        Planet = stream.readVariant();
        WorldStructure = stream.readVariant();
        Sky = stream.readInt8Array();
        Weather = stream.readInt8Array();
        SpawnX = stream.readFloatInt32();
        SpawnY = stream.readFloatInt32();
        WorldProperties = stream.readVariant();
        ClientId = stream.readUnsignedInt();
        Local = stream.readBoolean();
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeVariant(Planet);
        stream.writeVariant(WorldStructure);
        stream.writeInt8Array(Sky);
        stream.writeInt8Array(Weather);
        stream.writeFloatInt32(SpawnX);
        stream.writeFloatInt32(SpawnY);
        stream.writeVariant(WorldProperties);
        stream.writeInt(ClientId);
        stream.writeBoolean(Local);
		
	}
}
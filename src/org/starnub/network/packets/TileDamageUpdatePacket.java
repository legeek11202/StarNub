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
public class TileDamageUpdatePacket {
	public byte PacketId()
	{
		return 19;
	}

	public long		TileX;
	public long		TileY;
	public byte		Unknown;
	public float	Damage;
	public float	DamageEffect;
	public float	SourcePosX;
	public float	SourcePosY;
	public byte		DamageType;

	public TileDamageUpdatePacket()
	{
		TileX = 0;
		TileY = 0;
		Unknown = 0;
		Damage = 0;
		DamageEffect = 0;
		SourcePosX = 0;
		SourcePosY = 0;
		DamageType = 0;
	}

	/**
	 * @return the tileX
	 */
	public long getTileX()
	{
		return TileX;
	}

	/**
	 * @param tileX
	 *            the tileX to set
	 */
	public void setTileX(long tileX)
	{
		TileX = tileX;
	}

	/**
	 * @return the tileY
	 */
	public long getTileY()
	{
		return TileY;
	}

	/**
	 * @param tileY
	 *            the tileY to set
	 */
	public void setTileY(long tileY)
	{
		TileY = tileY;
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

	/**
	 * @return the damage
	 */
	public float getDamage()
	{
		return Damage;
	}

	/**
	 * @param damage
	 *            the damage to set
	 */
	public void setDamage(float damage)
	{
		Damage = damage;
	}

	/**
	 * @return the damageEffect
	 */
	public float getDamageEffect()
	{
		return DamageEffect;
	}

	/**
	 * @param damageEffect
	 *            the damageEffect to set
	 */
	public void setDamageEffect(float damageEffect)
	{
		DamageEffect = damageEffect;
	}

	/**
	 * @return the sourcePosX
	 */
	public float getSourcePosX()
	{
		return SourcePosX;
	}

	/**
	 * @param sourcePosX
	 *            the sourcePosX to set
	 */
	public void setSourcePosX(float sourcePosX)
	{
		SourcePosX = sourcePosX;
	}

	/**
	 * @return the sourcePosY
	 */
	public float getSourcePosY()
	{
		return SourcePosY;
	}

	/**
	 * @param sourcePosY
	 *            the sourcePosY to set
	 */
	public void setSourcePosY(float sourcePosY)
	{
		SourcePosY = sourcePosY;
	}

	/**
	 * @return the damageType
	 */
	public byte getDamageType()
	{
		return DamageType;
	}

	/**
	 * @param damageType
	 *            the damageType to set
	 */
	public void setDamageType(byte damageType)
	{
		DamageType = damageType;
	}
}

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
public class DamageNotificationPacket extends Packet {

	public byte PacketId()
	{
		return 45;
	}

	public long		CauseEntityId;
	public long		TargetEntityId;
	public long		PositionX;
	public long		PositionY;
	public long		Damage;
	public byte		DamageKind;
	public String	DamageSourceKind;
	public String	TargetMaterialKind;
	public byte		HitResultKind;

	/**
	 * @return the causeEntityId
	 */
	public long getCauseEntityId()
	{
		return CauseEntityId;
	}

	/**
	 * @param causeEntityId
	 *            the causeEntityId to set
	 */
	public void setCauseEntityId(long causeEntityId)
	{
		CauseEntityId = causeEntityId;
	}

	/**
	 * @return the targetEntityId
	 */
	public long getTargetEntityId()
	{
		return TargetEntityId;
	}

	/**
	 * @param targetEntityId
	 *            the targetEntityId to set
	 */
	public void setTargetEntityId(long targetEntityId)
	{
		TargetEntityId = targetEntityId;
	}

	/**
	 * @return the positionX
	 */
	public long getPositionX()
	{
		return PositionX;
	}

	/**
	 * @param positionX
	 *            the positionX to set
	 */
	public void setPositionX(long positionX)
	{
		PositionX = positionX;
	}

	/**
	 * @return the positionY
	 */
	public long getPositionY()
	{
		return PositionY;
	}

	/**
	 * @param positionY
	 *            the positionY to set
	 */
	public void setPositionY(long positionY)
	{
		PositionY = positionY;
	}

	/**
	 * @return the damage
	 */
	public long getDamage()
	{
		return Damage;
	}

	/**
	 * @param damage
	 *            the damage to set
	 */
	public void setDamage(long damage)
	{
		Damage = damage;
	}

	/**
	 * @return the damageKind
	 */
	public byte getDamageKind()
	{
		return DamageKind;
	}

	/**
	 * @param damageKind
	 *            the damageKind to set
	 */
	public void setDamageKind(byte damageKind)
	{
		DamageKind = damageKind;
	}

	/**
	 * @return the damageSourceKind
	 */
	public String getDamageSourceKind()
	{
		return DamageSourceKind;
	}

	/**
	 * @param damageSourceKind
	 *            the damageSourceKind to set
	 */
	public void setDamageSourceKind(String damageSourceKind)
	{
		DamageSourceKind = damageSourceKind;
	}

	/**
	 * @return the targetMaterialKind
	 */
	public String getTargetMaterialKind()
	{
		return TargetMaterialKind;
	}

	/**
	 * @param targetMaterialKind
	 *            the targetMaterialKind to set
	 */
	public void setTargetMaterialKind(String targetMaterialKind)
	{
		TargetMaterialKind = targetMaterialKind;
	}

	/**
	 * @return the hitResultKind
	 */
	public byte getHitResultKind()
	{
		return HitResultKind;
	}

	/**
	 * @param hitResultKind
	 *            the hitResultKind to set
	 */
	public void setHitResultKind(byte hitResultKind)
	{
		HitResultKind = hitResultKind;
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

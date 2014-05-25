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
public class EntityDestroyPacket extends Packet {
	
	public byte PacketId()
	{
		return 44;
	}

	private long	EntityId;
	private Boolean	Death;
	private byte[]	Unknown;

	/**
	 * @return the entityId
	 */
	public long getEntityId()
	{
		return EntityId;
	}

	/**
	 * @param entityId
	 *            the entityId to set
	 */
	public void setEntityId(long entityId)
	{
		EntityId = entityId;
	}

	/**
	 * @return the death
	 */
	public Boolean getDeath()
	{
		return Death;
	}

	/**
	 * @param death
	 *            the death to set
	 */
	public void setDeath(Boolean death)
	{
		Death = death;
	}

	/**
	 * @return the unknown
	 */
	public byte[] getUnknown()
	{
		return Unknown;
	}

	/**
	 * @param unknown
	 *            the unknown to set
	 */
	public void setUnknown(byte[] unknown)
	{
		Unknown = unknown;
	}

	@Override
	public void Read(StarboundStream stream)
	{
		* //TODO
	}

	@Override
	public void Write(StarboundStream stream)
	{
		//TODO
	}
}

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
public class RequestDropPacket extends Packet {
	
	public byte PacketId()
	{
		return 28;
	}

	private long EntityId;

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

	@Override
	public void Read(StarboundStream stream)
	{
        int discarded;

        EntityId = stream.readSignedVLQ();
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeSignedVLQ(EntityId);	
	}
}

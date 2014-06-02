package network.packets;

import network.StarboundStream;
import datatypes.VLQ;

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
public class CloseContainerPacket extends Packet {

	public byte PacketId()
	{
		return 34;
	}

	private VLQ EntityId;

	/**
	 * @return the entityId
	 */
	public VLQ getEntityId()
	{
		return EntityId;
	}

	/**
	 * @param entityId
	 *            the entityId to set
	 */
	public void setEntityId(VLQ entityId)
	{
		EntityId = entityId;
	}
	
	@Override
	public void Read(StarboundStream stream)
	{
        EntityId = stream.readSignedVLQ();
	}

	@Override
	public void Write(StarboundStream stream)
	{
		stream.writeSignedVLQ(EntityId);
	}
}

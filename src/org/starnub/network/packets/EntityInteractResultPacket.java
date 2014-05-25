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
public class EntityInteractResultPacket extends Packet {
	
	public byte PacketId()
	{
		return 24;
	}

	private int	ClientId;
	private int	EntityId;
	private Variant	Results;

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
	public void setClientId(int clientId)
	{
		ClientId = clientId;
	}

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
	public void setEntityId(int entityId)
	{
		EntityId = entityId;
	}

	/**
	 * @return the results
	 */
	public Variant getResults()
	{
		return Results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(Variant results)
	{
		Results = results;
	}

	@Override
	public void Read(StarboundStream stream)
	{
        ClientId = stream.readUnsignedInt();
        EntityId = stream.readInt();
        try { Results = stream.readVariant(); } catch (Exception e) { e.printStackTrace(); }
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeInt(ClientId);
        stream.writeInt(EntityId);
        stream.writeVariant(Results);
	}
}

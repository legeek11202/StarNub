package org.starnub.network.packets;

import java.util.List;

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
public class EntityCreatePacket extends Packet {

	public byte PacketId()
	{
		return 42;
	}//TODO

	// public List<Entity> Entities;
	//
	// public EntityCreatePacket()
	// {
	// Entities = new List<Entity>();
	// }
	//
	// /**
	// * @return the entities
	// */
	// public List<Entity> getEntities()
	// {
	// return Entities;
	// }
	//
	// /**
	// * @param entities the entities to set
	// */
	// public void setEntities(List<Entity> entities)
	// {
	// Entities = entities;
	// }
	//

	@Override
	void Read(StarboundStream stream)
	{

	}

	@Override
	void Write(StarboundStream stream)
	{

		
	}
}

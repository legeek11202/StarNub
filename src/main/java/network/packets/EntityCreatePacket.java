package network.packets;

import network.Packet;
import network.StarboundStream;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 24 May 2014
 */
public class EntityCreatePacket extends Packet {

    @Override
    public byte getPacketId() {
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
    public void Read(StarboundStream stream) {
//        * //TODO
    }

    @Override
    public void Write(StarboundStream stream) {
        //TODO
    }
}

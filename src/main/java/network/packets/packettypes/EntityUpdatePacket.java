package network.packets.packettypes;

import network.packets.Packet;
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
public class EntityUpdatePacket extends Packet {

    private long EntityId;
    private byte[] Unknown;

    @Override
    public byte getPacketId() {
        return 43;
    }

    /**
     * @return the entityId
     */
    public long getEntityId() {
        return EntityId;
    }

    /**
     * @param entityId the entityId to set
     */
    public void setEntityId(long entityId) {
        EntityId = entityId;
    }

    /**
     * @return the unknown
     */
    public byte[] getUnknown() {
        return Unknown;
    }

    /**
     * @param unknown the unknown to set
     */
    public void setUnknown(byte[] unknown) {
        Unknown = unknown;
    }

    @Override
    public void Read(StarboundStream stream) {
//        *//TODO
    }

    @Override
    public void Write(StarboundStream stream) {
        //TODO
    }
}

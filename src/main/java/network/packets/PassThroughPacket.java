package network.packets;

import network.StarboundStream;
import network.Packet;

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
public class PassThroughPacket extends Packet {

    private byte packetId;
    private byte[] payload;

    /**
     * @return the packetId
     */
    @Override
    public byte getPacketId() {
        return packetId;
    }

    /**
     * @param packetId the packetId to set
     */
    public void setPacketId(byte packetId) {
        this.packetId = packetId;
    }

    /**
     * @return the payload
     */
    public byte[] getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public void Read(StarboundStream stream) {
        payload = stream.readAllBytes();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeAllBytes(payload);
    }


}
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
public class WorldStopPacket extends Packet {

    private String Status;

    public WorldStopPacket() {
    }

    public byte PacketId() {
        return 15;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public void Read(StarboundStream stream) {
        Status = stream.readString();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeString(Status);
    }
}
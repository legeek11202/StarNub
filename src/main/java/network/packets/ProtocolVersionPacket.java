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
 * @version 1.0, 06 June 2014
 */
public class ProtocolVersionPacket extends Packet {

    private int ProtocolVersion;

    public ProtocolVersionPacket(){
    }

    public ProtocolVersionPacket(int protocolVersion) {
        ProtocolVersion = protocolVersion;
    }

    @Override
    public byte getPacketId() {
        return 0;
    }

    /**
     * @return the protocolVersion
     */
    public int getProtocolVersion() {
        return ProtocolVersion;
    }

    /**
     * @param protocolVersion the protocolVersion to set
     */
    public void setProtocolVersion(int protocolVersion) {
        ProtocolVersion = protocolVersion;
    }

    @Override
    public void Read(StarboundStream stream) {
        ProtocolVersion = stream.readInt();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeInt(ProtocolVersion);
    }
}

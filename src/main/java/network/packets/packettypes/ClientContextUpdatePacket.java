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
public class ClientContextUpdatePacket extends Packet {

    private byte[] Data;

    public ClientContextUpdatePacket() {
        Data = new byte[0];
    }

    // public World World; //TODO Need to add world read

    @Override
    public byte getPacketId() {
        return 13;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return Data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        Data = data;
    }

    // /**
    // * @return the world
    // */
    // public World getWorld()
    // {
    // return World;
    // }
    //
    // /**
    // * @param world the world to set
    // */
    // public void setWorld(World world)
    // {
    // World = world;
    // }

    @Override
    public void Read(StarboundStream stream) {
//        * //TODO
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeInt8Array(Data);
    }
}

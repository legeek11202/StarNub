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
public class EnvironmentUpdatePacket extends Packet {

    private byte[] Sky;
    private byte[] Weather;
    public EnvironmentUpdatePacket() {
        Sky = new byte[0];
        Weather = new byte[0];
    }

    @Override
    public byte getPacketId() {
        return 23;
    }

    /**
     * @return the sky
     */
    public byte[] getSky() {
        return Sky;
    }

    /**
     * @param sky the sky to set
     */
    public void setSky(byte[] sky) {
        Sky = sky;
    }

    /**
     * @return the weather
     */
    public byte[] getWeather() {
        return Weather;
    }

    /**
     * @param weather the weather to set
     */
    public void setWeather(byte[] weather) {
        Weather = weather;
    }

    @Override
    public void Read(StarboundStream stream) {
        Sky = stream.readInt8Array();
        Weather = stream.readInt8Array();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeInt8Array(Sky);
        stream.writeInt8Array(Weather);
    }
}

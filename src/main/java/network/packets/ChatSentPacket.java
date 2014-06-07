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
public class ChatSentPacket extends Packet {

    private String Message;
    private byte Channel;

    @Override
    public byte getPacketId() {
        return 11;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        Message = message;
    }

    /**
     * @return the channel
     */
    public byte getChannel() {
        return Channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(byte channel) {
        Channel = channel;
    }

    @Override
    public void Read(StarboundStream stream) {
        Message = stream.readString();
        Channel = stream.readUnsignedByte();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeString(Message);
        stream.writeByte(Channel);
    }
}

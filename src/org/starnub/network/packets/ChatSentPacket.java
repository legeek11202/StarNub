package org.starnub.network.packets;

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
public class ChatSentPacket extends Packet {

	public byte PacketId()
	{
		return 11;
	}

	public String	Message;
	public byte		Channel;

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return Message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message)
	{
		Message = message;
	}

	/**
	 * @return the channel
	 */
	public byte getChannel()
	{
		return Channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(byte channel)
	{
		Channel = channel;
	}

	@Override
    public void Read(StarboundStream stream)
    {
        Message = stream.readString();
        Channel = (byte) stream.getBuf().readUnsignedByte();
    }
	
	@Override
    public  void Write(StarboundStream stream)
    {
        stream.writeString(Message);
        stream.getBuf().writeByte(Channel);
    }
}

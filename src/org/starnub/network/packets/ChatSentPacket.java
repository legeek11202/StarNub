package org.starnub.network.packets;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
 * 
 */
public class ChatSentPacket {

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

}

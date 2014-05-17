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
public class ChatReceivedPacket {

	public byte PacketId()
	{
		return 4;
	}

	public byte		Channel;
	public String	World;
	public long		ClientId;
	public String	Name;
	public String	Message;

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

	/**
	 * @return the world
	 */
	public String getWorld()
	{
		return World;
	}

	/**
	 * @param world
	 *            the world to set
	 */
	public void setWorld(String world)
	{
		World = world;
	}

	/**
	 * @return the clientId
	 */
	public long getClientId()
	{
		return ClientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(long clientId)
	{
		ClientId = clientId;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		Name = name;
	}

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
}

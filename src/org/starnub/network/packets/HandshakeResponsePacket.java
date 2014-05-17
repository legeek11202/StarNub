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
public class HandshakeResponsePacket {
	public byte PacketId()
	{
		return 9;
	}

	public String	ClaimMessage;
	public String	PasswordHash;

	/**
	 * @param passwordHash
	 */
	public HandshakeResponsePacket(String passwordHash)
	{
		PasswordHash = passwordHash;
	}

	/**
	 * @return the claimMessage
	 */
	public String getClaimMessage()
	{
		return ClaimMessage;
	}

	/**
	 * @param claimMessage
	 *            the claimMessage to set
	 */
	public void setClaimMessage(String claimMessage)
	{
		ClaimMessage = claimMessage;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash()
	{
		return PasswordHash;
	}

	/**
	 * @param passwordHash
	 *            the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash)
	{
		PasswordHash = passwordHash;
	}

}

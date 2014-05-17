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
public class HandshakeChallengePacket {
	public byte PacketId()
	{
		return 3;
	}

	public String	Claim;
	public String	Salt;
	public int		Rounds;

	public HandshakeChallengePacket()
	{
		Rounds = 5000;
	}

	/**
	 * @return the claim
	 */
	public String getClaim()
	{
		return Claim;
	}

	/**
	 * @param claim
	 *            the claim to set
	 */
	public void setClaim(String claim)
	{
		Claim = claim;
	}

	/**
	 * @return the salt
	 */
	public String getSalt()
	{
		return Salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt)
	{
		Salt = salt;
	}

	/**
	 * @return the rounds
	 */
	public int getRounds()
	{
		return Rounds;
	}

	/**
	 * @param rounds
	 *            the rounds to set
	 */
	public void setRounds(int rounds)
	{
		Rounds = rounds;
	}
}

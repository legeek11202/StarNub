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
public class UniverseTimeUpdatePacket {
	public byte PacketId()
	{
		return 5;
	}

	private long	Time;

	public UniverseTimeUpdatePacket()
	{
		Time = 0L;
	}

	/**
	 * @return the time
	 */
	public long getTime()
	{
		return Time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(long time)
	{
		Time = time;
	}
}

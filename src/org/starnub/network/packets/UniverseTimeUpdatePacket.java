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
public class UniverseTimeUpdatePacket extends Packet {
	
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
	
	@Override
	public void Read(StarboundStream stream)
	{
        int discarded;
        Time = stream.readSignedVLQ();
	}

	@Override
	public void Write(StarboundStream stream)
	{
        stream.writeSignedVLQ(Time);
	}
}

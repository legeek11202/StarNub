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
public class HeartbeatPacket {
	public byte PacketId()
	{
		return 48;
	}

	public long	CurrentStep;

	/**
	 * @return the currentStep
	 */
	public long getCurrentStep()
	{
		return CurrentStep;
	}

	/**
	 * @param currentStep
	 *            the currentStep to set
	 */
	public void setCurrentStep(long currentStep)
	{
		CurrentStep = currentStep;
	}
}

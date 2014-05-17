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
public class ConnectionResponsePacket {

	public byte PacketId()
	{
		return 1;
	}

	public boolean	Success;
	public long		ClientId;
	public String	RejectionReason;
	public byte[]	Unknown;

	public ConnectionResponsePacket()
	{
		Unknown = new byte[0];
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess()
	{
		return Success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success)
	{
		Success = success;
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
	 * @return the rejectionReason
	 */
	public String getRejectionReason()
	{
		return RejectionReason;
	}

	/**
	 * @param rejectionReason
	 *            the rejectionReason to set
	 */
	public void setRejectionReason(String rejectionReason)
	{
		RejectionReason = rejectionReason;
	}

	/**
	 * @return the unknown
	 */
	public byte[] getUnknown()
	{
		return Unknown;
	}

	/**
	 * @param unknown
	 *            the unknown to set
	 */
	public void setUnknown(byte[] unknown)
	{
		Unknown = unknown;
	}

}

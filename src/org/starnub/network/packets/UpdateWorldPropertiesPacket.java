package org.starnub.network.packets;

import org.starnub.datatypes.Variant;

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
public class UpdateWorldPropertiesPacket {
	public byte PacketId()
	{
		return 47;
	}

	public byte		NumPairs;
	public String	PropertyName;
	public Variant	PropertyValue;

	/**
	 * @return the numPairs
	 */
	public byte getNumPairs()
	{
		return NumPairs;
	}

	/**
	 * @param numPairs
	 *            the numPairs to set
	 */
	public void setNumPairs(byte numPairs)
	{
		NumPairs = numPairs;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName()
	{
		return PropertyName;
	}

	/**
	 * @param propertyName
	 *            the propertyName to set
	 */
	public void setPropertyName(String propertyName)
	{
		PropertyName = propertyName;
	}

	/**
	 * @return the propertyValue
	 */
	public Variant getPropertyValue()
	{
		return PropertyValue;
	}

	/**
	 * @param propertyValue
	 *            the propertyValue to set
	 */
	public void setPropertyValue(Variant propertyValue)
	{
		PropertyValue = propertyValue;
	}

}

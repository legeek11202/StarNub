package network.packets;

import datatypes.Variant;
import network.Packet;
import network.StarboundStream;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 24 May 2014
 */
public class UpdateWorldPropertiesPacket extends Packet {

    private byte NumPairs;
    private String PropertyName;
    private Variant PropertyValue;

    @Override
    public byte getPacketId() {
        return 47;
    }

    /**
     * @return the numPairs
     */
    public byte getNumPairs() {
        return NumPairs;
    }

    /**
     * @param numPairs the numPairs to set
     */
    public void setNumPairs(byte numPairs) {
        NumPairs = numPairs;
    }

    /**
     * @return the propertyName
     */
    public String getPropertyName() {
        return PropertyName;
    }

    /**
     * @param propertyName the propertyName to set
     */
    public void setPropertyName(String propertyName) {
        PropertyName = propertyName;
    }

    /**
     * @return the propertyValue
     */
    public Variant getPropertyValue() {
        return PropertyValue;
    }

    /**
     * @param propertyValue the propertyValue to set
     */
    public void setPropertyValue(Variant propertyValue) {
        PropertyValue = propertyValue;
    }

    @Override
    public void Read(StarboundStream stream) {
        NumPairs = stream.readUnsignedByte();
        PropertyName = stream.readString();
        try { PropertyValue = stream.readVariant(); } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeByte(NumPairs);
        stream.writeString(PropertyName);
        stream.writeVariant(PropertyValue);
    }
}

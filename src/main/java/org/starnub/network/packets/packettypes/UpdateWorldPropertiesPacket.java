/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.starnub.network.packets.packettypes;

import org.starnub.datatypes.Variant;
import org.starnub.network.packets.Packet;
import org.starnub.network.StarboundStream;

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

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
public class EnvironmentUpdatePacket extends Packet {

    private byte[] Sky;
    private byte[] Weather;
    public EnvironmentUpdatePacket() {
        Sky = new byte[0];
        Weather = new byte[0];
    }

    @Override
    public byte getPacketId() {
        return 23;
    }

    /**
     * @return the sky
     */
    public byte[] getSky() {
        return Sky;
    }

    /**
     * @param sky the sky to set
     */
    public void setSky(byte[] sky) {
        Sky = sky;
    }

    /**
     * @return the weather
     */
    public byte[] getWeather() {
        return Weather;
    }

    /**
     * @param weather the weather to set
     */
    public void setWeather(byte[] weather) {
        Weather = weather;
    }

    @Override
    public void Read(StarboundStream stream) {
        Sky = stream.readInt8Array();
        Weather = stream.readInt8Array();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeInt8Array(Sky);
        stream.writeInt8Array(Weather);
    }
}

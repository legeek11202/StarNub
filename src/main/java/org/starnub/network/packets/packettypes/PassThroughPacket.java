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
public class PassThroughPacket extends Packet {

    private byte packetId;
    private byte[] payload;

    /**
     * @return the packetId
     */
    @Override
    public byte getPacketId() {
        return packetId;
    }

    /**
     * @param packetId the packetId to set
     */
    public void setPacketId(byte packetId) {
        this.packetId = packetId;
    }

    /**
     * @return the payload
     */
    public byte[] getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public void Read(StarboundStream stream) {
        payload = stream.readAllBytes();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeAllBytes(payload);
    }


}
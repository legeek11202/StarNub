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
public class HandshakeChallengePacket extends Packet {

    private String Claim;
    private String Salt;
    private int Rounds;
    public HandshakeChallengePacket() {
        Rounds = 5000;
    }

    @Override
    public byte getPacketId() {
        return 3;
    }

    /**
     * @return the claim
     */
    public String getClaim() {
        return Claim;
    }

    /**
     * @param claim the claim to set
     */
    public void setClaim(String claim) {
        Claim = claim;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return Salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        Salt = salt;
    }

    /**
     * @return the rounds
     */
    public int getRounds() {
        return Rounds;
    }

    /**
     * @param rounds the rounds to set
     */
    public void setRounds(int rounds) {
        Rounds = rounds;
    }

    @Override
    public void Read(StarboundStream stream) {
        Claim = stream.readString();
        Salt = stream.readString();
        Rounds = stream.readInt();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeString(Claim);
        stream.writeString(Salt);
        stream.writeInt(Rounds);
    }
}

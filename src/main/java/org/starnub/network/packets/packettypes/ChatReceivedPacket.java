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
public class ChatReceivedPacket extends Packet {

    private byte Channel;
    private String World;
    private int ClientId;
    private String Name;
    private String Message;

    @Override
    public byte getPacketId() {
        return 4;
    }

    /**
     * @return the channel
     */
    public byte getChannel() {
        return Channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(byte channel) {
        Channel = channel;
    }

    /**
     * @return the world
     */
    public String getWorld() {
        return World;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(String world) {
        World = world;
    }

    /**
     * @return the clientId
     */
    public long getClientId() {
        return ClientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public void Read(StarboundStream stream) {
        Channel = stream.readUnsignedByte();
        World = stream.readString();
        ClientId = stream.readUnsignedInt();
        Name = stream.readString();
        Message = stream.readString();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeByte(Channel);
        stream.writeString(World);
        stream.writeInt(ClientId);
        stream.writeString(Name);
        stream.writeString(Message);
    }
}

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

package org.starnub.network.packets;

import io.netty.channel.ChannelHandlerContext;
import org.starnub.network.StarboundStream;

public class Packet extends AbstractPacket {

    private ChannelHandlerContext serverCTX;
    private ChannelHandlerContext clientCTX;
    /* 0 = Client, 1 = Server */
    private int connectionSide;
    private ChannelHandlerContext oppositeCTX;

    public Packet() {
    }

    @Override
    public void Read(StarboundStream stream) {
    }

    @Override
    public void Write(StarboundStream stream) {
    }

    @Override
    public byte getPacketId() {
        return super.getPacketId();
    }

    public int getConnectionSide() {
        return connectionSide;
    }

    public void setConnectionSide(int connectionSide) {
        this.connectionSide = connectionSide;
    }

    public ChannelHandlerContext getClientCTX() {
        return clientCTX;
    }

    public void setClientCTX(ChannelHandlerContext clientCTX) {
        this.clientCTX = clientCTX;
    }

    public ChannelHandlerContext getServerCTX() {
        return serverCTX;
    }

    public void setServerCTX(ChannelHandlerContext serverCTX) {
        this.serverCTX = serverCTX;
    }

    public void setOppositeCTX(ChannelHandlerContext oppositeCTX) {
        this.oppositeCTX = oppositeCTX;
    }

    public ChannelHandlerContext getOppositeCTX() {
        return oppositeCTX;
    }
}

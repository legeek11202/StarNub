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

package org.starnub.network;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.util.UUID;

public final class ConnectedClient {

    private final String playername;
    private final InetAddress connectingIp;
    private final UUID uuid;
    private final ChannelHandlerContext ctx; //TODO Label client context
    //TODO Server context

    public ConnectedClient(String playername, UUID connectingUuid, InetAddress connectingIp, ChannelHandlerContext ctx) {
        this.playername = playername;
        this.connectingIp = connectingIp;
        this.uuid = connectingUuid;
        this.ctx = ctx;

    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playername;
    }

    /**
     * @return the connectingIp
     */
    public InetAddress getConnectingIp() {
        return connectingIp;
    }

    /**
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return the ctx
     */
    public ChannelHandlerContext getCtx() {
        return ctx;
    }
}

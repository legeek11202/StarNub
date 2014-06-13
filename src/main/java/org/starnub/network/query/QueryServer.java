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

package org.starnub.network.query;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.starnub.StarNub;

/**
 * This class will start a client connection to the Starbound server and get a
 * handshake which will tell us if the server is responsive or not.
 * <p>
 * Credit goes to Netty.io (Asynchronous API) examples.
 * <p>
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 26 May 2014
 */

public class QueryServer {

    public QueryServer() {
    }

    public static void serverStatus() {
        serverQuery();
    }

    private static void serverQuery() {
        final String sbRemoteHost = "127.0.0.1";
        final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");

			/* Using one group of threads */
        EventLoopGroup queryGroup = new NioEventLoopGroup(1);
        try {
                /* Client Bootstrap */
            Bootstrap snTxQuerySb = new Bootstrap();
            snTxQuerySb /* Configuring the Bootstrap */
                    .group(queryGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        public void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addFirst(new QueryPacketDecoder());
                        }
                    })
                    .connect(sbRemoteHost, sbRemotePort).channel().closeFuture().sync();

        } catch (Exception e) {
            SbQueryProcessor.setStatus(false);
        } finally {
            queryGroup.shutdownGracefully();
        }
    }
}

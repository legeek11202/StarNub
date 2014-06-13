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

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.starnub.StarNub;


/**
 * This class opens a Server Socket on a configured TCP Port. The
 * NioEventLoopGroup specifies a specific amount of threads. The default amount
 * of threads are (CPUs * 2).
 * <p>
 * Credit goes to Netty.io (Asynchronous API) examples.
 * <p>
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 26 May 2014
 *          <br>
 */
public class ProxyServer implements Runnable {

    private final int snServerPort = StarNub.configVariables.get("StarNub_Port");

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /* Initiate the server bootstrap */
            ServerBootstrap starNubInbound_TCP_Socket = new ServerBootstrap();
            starNubInbound_TCP_Socket
					/* Acceptor thread, Worker thread */
                    .group(bossGroup, workerGroup)
					/* Channel instance */
                    .channel(NioServerSocketChannel.class)
					/* Attempt to help reduce bandwidth due to Starbound small packettypes */
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    /* We are using a pooled buffer so we do not have to constantly alloc/dealloc*/
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
					/* Server Initializer to set up this channels handlers */
                    .childHandler(
							 /* Bind the Server Socket */
                            new ProxyServerInitializer())
                    .bind(snServerPort).channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
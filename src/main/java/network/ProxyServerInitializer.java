package network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import network.handlers.PacketDecoderHandler;

/**
 * This class initializes the initial channel handlers.
 * <p>
 * Credit goes to Netty.io (Asynchronous API) examples.
 * <p>
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
 */

public class ProxyServerInitializer extends ChannelInitializer<SocketChannel> {

    public ProxyServerInitializer() {
    }

    EventLoopGroup packetPicker = new NioEventLoopGroup();

    EventLoopGroup workerGroup = new NioEventLoopGroup();
    /* We use an initializer to set up any handlers for this channel */
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
         /* 0 is Client Side */
         ch.pipeline().addFirst(new PacketDecoderHandler(0));
    }
}

package network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import network.handlers.Frontend;
import network.handlers.PacketDecoder;
import network.handlers.PacketEncoder;

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

    /* We use an initializer to set up any handlers for this channel */
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        /*
		 * Connection Inspector will check the IP and UUID for bans before it
		 * removes itself
		 */
         ch.pipeline().addFirst("PacketDecoder", new PacketDecoder());
		 ch.pipeline().addAfter("PacketDecoder", "PacketEncoder", new PacketEncoder());
		 ch.pipeline().addAfter("PacketEncoder", "Frontend", new Frontend());
//		 ch.pipeline().addAfter("PacketDecoder", "PacketHandler", new PacketHandler());
    }
}

package network.packets;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Daniel on 6/6/2014.
 */
public class LocalPacketTest extends Packet {

    private ChannelHandlerContext serverCTX;
    private ChannelHandlerContext clientCTX;

    public ChannelHandlerContext getServerCTX() {
        return serverCTX;
    }

    public void setServerCTX(ChannelHandlerContext serverCTX) {
        this.serverCTX = serverCTX;
    }

    public ChannelHandlerContext getClientCTX() {
        return clientCTX;
    }

    public void setClientCTX(ChannelHandlerContext clientCTX) {
        this.clientCTX = clientCTX;
    }
}

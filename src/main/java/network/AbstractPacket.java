package network;

import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractPacket {

    private byte PacketId;
    private ChannelHandlerContext serverCTX;
    private ChannelHandlerContext clientCTX;

    public abstract void Read(StarboundStream stream);

    public abstract void Write(StarboundStream stream);

    public byte getPacketId() {
        return PacketId;
    }

    public void setPacketId(byte packetId) {
        PacketId = packetId;
    }

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

package network.packets;

import io.netty.channel.ChannelHandlerContext;
import network.StarboundStream;

public class Packet extends AbstractPacket {

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

    @Override
    public void setPacketId(byte packetId) {
        super.setPacketId(packetId);
    }

    public ChannelHandlerContext getServerCTX() {
        return super.getServerCTX();
    }

    public void setServerCTX(ChannelHandlerContext serverCTX) {
        super.setServerCTX(serverCTX);
    }

    public ChannelHandlerContext getClientCTX() {
        return super.getClientCTX();
    }

    public void setClientCTX(ChannelHandlerContext clientCTX) {
        super.setClientCTX(clientCTX);
    }

}

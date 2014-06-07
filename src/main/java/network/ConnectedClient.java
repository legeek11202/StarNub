package network;

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

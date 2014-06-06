package network.handlers;

import io.netty.channel.ChannelHandlerContext;
import network.ConnectedClient;
import network.Packet;
import network.packets.ClientConnectPacket;
import starnub.StarNub;
import util.stream.MessageFormater;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.UUID;

public class BanCheckLogic {

    private static ClientConnectPacket connectPacket;
    private static UUID connectingUuid;
    private static ChannelHandlerContext clientContext;
    private static Packet packet;

    public BanCheckLogic(ChannelHandlerContext clientContextCon, Packet packetCon) {
        clientContext = clientContextCon;
        packet = packetCon;
    }

    public static void isBanned(int i) {
		/* 0 IP Banned, 1 UUID Banned */
        MessageFormater.msgPrint(StarNub.language.getString("banned")
                + " IP: " + ".", 0, 0);//TODO connecting IP
		/* Send to Logger */
        // TODO Logger
		/* Send a client disconnect with reason */
        // TODO Write packet for above??
		/* Close channel due to Banned IP */
        clientContext.close();
    }

    public ConnectedClient banCheck() {
        InetAddress connectingIp = ((InetSocketAddress) clientContext.channel().remoteAddress()).getAddress();
        try {
            for (InetAddress bannedip : StarNub.bannedIps) {
                if (bannedip.equals(connectingIp)) {
                    isBanned(0);
                    break;
                }
            }
        } catch (NullPointerException e) {
            /* No IPs are in the ban list */
        }

        connectPacket = (ClientConnectPacket) packet;
        connectingUuid = UUID.nameUUIDFromBytes(connectPacket.getUUID());
        try {
            for (UUID bannedUuid : StarNub.bannedUuids) {
                System.out.println(bannedUuid);
                if (bannedUuid.equals(connectingUuid)) {
						/* Close channel due to Banned IP */
                    isBanned(1);
                    break;
                }
            }
        } catch (NullPointerException e) {
			/* No UUIDs are in the ban list */
        }
        //TODO ServerSideContext
        return new ConnectedClient(connectPacket.getPlayerName(), connectingUuid, connectingIp, clientContext);
    }
}

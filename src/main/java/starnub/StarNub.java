package starnub;

import configuration.Configurator;
import configuration.SbConfigurator;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import localization.LanguageLoader;
import managment.SbServerMonitor;
import network.ConnectedClient;
import network.ProxyServer;
import network.UDPProxyServer;
import network.handlers.ClientSidePacketQue;
import network.handlers.PacketStats;
import network.handlers.ServerSidePacketQue;
import network.packets.ProtocolVersionPacket;
import org.joda.time.DateTime;
import util.KeyListener;
import util.os.Directories;
import util.stream.MessageFormater;
import util.stream.MultiOutputStreamLogger;
import util.timers.ThreadSleep;

import java.net.InetAddress;
import java.util.*;

/*
 * Represents the StarNub core.
 */

public final class StarNub {

    public volatile static ResourceBundle language;
    public volatile static Map<String, Integer> configVariables = new HashMap<String, Integer>();
    public volatile static Map<String, ConnectedClient> playersOnline = new HashMap<String, ConnectedClient>();
    public volatile static ChannelGroup clientChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public volatile static List<InetAddress> bannedIps = new ArrayList<InetAddress>();
    public volatile static List<UUID> bannedUuids = new ArrayList<UUID>();
    public volatile static ProtocolVersionPacket ProtocolVersionPacket = new ProtocolVersionPacket();
    /* Debugging Only */
    public volatile static PacketStats cps = new PacketStats();
    public volatile static PacketStats sps = new PacketStats();

    public StarNub() {
    }

    public static void main(String[] args) {
        //TODO Replace with a real version number
        System.out.println("\n"
                + "=============================================\n"
                + "===        StarNub v0.1-alpha             ===\n"
                + "=============================================\n"
                + "===        Underbalanced                  ===\n"
                + "===        			                     ===\n"
                + "=============================================\n");
    	/* Any code that has *side 0-9 = Server Side, 10-19 are client side
    	 * these are for specific code functions within a handler. */

        //TODO Correctly replace
//    	org.apache.log4j.BasicConfigurator.configure();

        //TODO Add replies from threads then play string
		/* Directory Check */
        Directories.snDirCheck();
        language = LanguageLoader.getResources();
        MessageFormater.msgPrint(language.getString("ss"), 0, 0);

    	/* Configuration Validation */
		Configurator.validateConfig();
        MessageFormater.msgPrint("\n\n" + language.getString("uvf") + "\n" + StarNub.configVariables.toString() + "\n", 0, 0);

		/* Configure starbound.config */
        SbConfigurator.sbConfigConfiguration();
        MessageFormater.msgPrint(language.getString("sbcc"), 0, 0);

    	/* Initiates Logger (MultiOutputStream) */
        new MultiOutputStreamLogger().snLogger();
        MessageFormater.msgPrint(language.getString("l"), 0, 0);

        /* Plug-in Loader */

    	/* Proxy Initialization */
        //TODO UDP Checker for thread crash
        Runnable sn_Proxy = new ProxyServer();
        Runnable ClientSidePacketQue = new ClientSidePacketQue();
        Runnable ServerSidePacketQue = new ServerSidePacketQue();
        Runnable sn_UDP_Proxy = new UDPProxyServer();
        new Thread(ClientSidePacketQue).start();
        new Thread(ServerSidePacketQue).start();
        new Thread(sn_Proxy).start();
        new Thread(sn_UDP_Proxy).start();

    	/* Starts the SN_Server WatchDog
    	 * TODO turn into a scheduled task */
        Runnable sb_Monitor = new SbServerMonitor();
        new Thread(sb_Monitor).start();
        MessageFormater.msgPrint(language.getString("sm"), 0, 0);

    	/* Pause Thread */
        new ThreadSleep().timer(2);

    	/* Starts the KeyListener */
        Runnable sn_KeyListener = new KeyListener();
        new Thread(sn_KeyListener).start();
        MessageFormater.msgPrint(language.getString("kl"), 0, 0);

    	/* Log Refresher */
        int loggerRefresh = new DateTime().getDayOfMonth();
        int infinite = 0;

        //TODO clean up stats timer = packetstats
        int timer = 0;
        int timer2 = 0;

        do {
            while (loggerRefresh == new DateTime().getDayOfMonth()) {

                new ThreadSleep().timer(5);
                timer += 5;
                timer2 += 5;
				/* Place POST data processing methods here */
				/*
				 * Post processing of player data and math for stat's
				 *
				 */
                while (timer >= 20) {
                    //TODO Make Broadcast Plugin
                    timer = 0;
//				ServerMessaging.test();
                }
                while (timer2 >= 20) {
//					//FINAL_REMOVE - PacketStat Tracking
//					System.err.println("\n\nPACKETSTATS CLIENT TO SERVER\n\n");
//					System.err.println(cps.packetStats());
//					System.err.println("\n\nPACKETSTATS SERVER TO CLIENT\n\n");
//					System.err.println(sps.packetStats());
                    timer2 = 0;
                }
            }
            new MultiOutputStreamLogger().snLogger();
            loggerRefresh = new DateTime().getDayOfMonth();
        }
        while (infinite == 0);

    }
}

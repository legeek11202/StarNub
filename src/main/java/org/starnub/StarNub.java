package org.starnub;


import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.joda.time.DateTime;
import org.starnub.configuration.Configurator;
import org.starnub.configuration.SbConfigurator;
import org.starnub.localization.LanguageLoader;
import org.starnub.managment.SbServerMonitor;
import org.starnub.network.ConnectedClient;
import org.starnub.network.ProxyServer;
import org.starnub.network.UDPProxyServer;
import org.starnub.network.packets.packettypes.ProtocolVersionPacket;
import org.starnub.util.KeyListener;
import org.starnub.util.os.Directories;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.stream.MultiOutputStreamLogger;
import org.starnub.util.timers.ThreadSleep;
import reactor.core.Environment;

import java.net.InetAddress;
import java.util.*;

/**
 * Represents the StarNubs core, for version and Server singleton handling
 */
public final class StarNub {
//    private static Server server;

    /**
     * Static class cannot be initialized.
     */
    private StarNub() {}

//    /**
//     * Gets the current {@link Server} singleton
//     *
//     * @return Server instance being ran
//     */
//    public static Server getServer() {
//        return server;
//    }
//
//    /**
//     * Attempts to set the {@link org.starnub.Server} singleton.
//     * <p>
//     * This cannot be done if the Server is already set.
//     *
//     * @param server Server instance
//     */
//    public static void setServer(Server server) {
//        if (StarNub.server != null) {
//            throw new UnsupportedOperationException("Cannot redefine singleton Server");
//        }
//
//        StarNub.server = server;
//        server.getLogger().info("This server is running " + " version " + " (Implementing API version "  + ")");
//    }
//
///*
// * Represents the StarNub core.
// */


    ///TODO move this to new singleton server


        public volatile static ResourceBundle language;
        public volatile static Map<String, Integer> configVariables = new HashMap<String, Integer>();
        public volatile static Map<String, ConnectedClient> playersOnline = new HashMap<String, ConnectedClient>();
        public volatile static ChannelGroup clientChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        public volatile static List<InetAddress> bannedIps = new ArrayList<InetAddress>();
        public volatile static List<UUID> bannedUuids = new ArrayList<UUID>();
        public volatile static ProtocolVersionPacket ProtocolVersionPacket = new ProtocolVersionPacket();
        public volatile static String pluginDirectory = "StarNub/Plugins"; //TODO will be moved alone with most variables
        public final static Environment env = new Environment();
    /* Debugging Only */
//    public volatile static PacketStats cps = new PacketStats();
//    public volatile static PacketStats sps = new PacketStats();


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
            //TODO thread crash checker ???
            Runnable sn_Proxy = new ProxyServer();
            Runnable sn_UDP_Proxy = new UDPProxyServer();
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




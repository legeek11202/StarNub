package org.starnub;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import org.joda.time.DateTime;
import org.starnub.configuration.ConfigurationCheck;
import org.starnub.localization.LanguageLoader;
import org.starnub.managment.SbServerMonitor;
import org.starnub.network.ProxyServer;
import org.starnub.network.UDPProxyServer;
import org.starnub.network.handlers.PacketStats;
import org.starnub.network.handlers.ServerMessaging;
import org.starnub.network.handlers.TempClient;
import org.starnub.util.KeyListener;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.stream.MultiOutputStreamLogger;
import org.starnub.util.timers.ThreadSleep;

/*
 * Represents the StarNub core.
 */

public final class StarNub {

	public StarNub() 
	{
    }
	
	//     Debugging Classes Start    //
	public final class Debug 
	{
		public static final boolean ON = true; /* Messages Toggle */
	} 
	
	public final class DebugFullWrapper 
	{
		public static final boolean ON = true; /* Proxy Toggle */
	} 
	
	/*  Debug Statements
	 *  if (Debug.ON) {System.out.println("Debug: ");}
	 *  if (StarNub.Debug.ON) {System.out.println("Debug: ");}
	 */
	//  Debugging Classes End     //
	
	/* Since arrays are faster we will keep the bans in arrays */
	/* Arrays will be updated upon /banned or /unbanned commands */
	
	//FIXME Language and no directories
	public static ResourceBundle language = LanguageLoader.getResources(); 
	public static Map<String, Integer> configVariables = new HashMap<String, Integer>();
	public static Map<String, TempClient> playersOnline = new HashMap<String, TempClient>();
	public static final ChannelGroup clientChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	public static List<InetAddress> bannedIps = new ArrayList<InetAddress>();
	public static List<UUID> bannedUuids = new ArrayList<UUID>();
	public static PacketStats ps = new PacketStats();
	
    public static void main(String [] args)
	{
    	System.out.println("\n"
    			+ "=============================================\n"
    			+ "===        StarNub v0.1-alpha             ===\n"
    			+ "=============================================\n"
    			+ "===        Underbalanced                  ===\n"
    			+ "===        Teihoo                         ===\n"
    			+ "=============================================\n");
    	
    	MessageFormater.msgPrint(language.getString("ss"), 0, 0);
    	
    	/* Runs the StarNub configuration checker */
    	ConfigurationCheck.checkConfiguration();
    	
    	/* Initiates Logger (MultiOutputStream) */
    	new MultiOutputStreamLogger().snLogger();
    	MessageFormater.msgPrint(language.getString("l"), 0, 0);
    	
    	if  (StarNub.DebugFullWrapper.ON)
    	{
        /* Plug-in Loader */
    		
    	/* Proxy Initialization */
    		//TODO UDP Checker for thread crash
    	Runnable sn_Proxy = new ProxyServer();
    	Runnable sn_UDP_Proxy = new UDPProxyServer();
    	new Thread (sn_Proxy).start();
    	new Thread (sn_UDP_Proxy).start();
    	}
    	
    	/* Starts the SN_Server WatchDog */
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

		do 
		{
			while (loggerRefresh == new DateTime().getDayOfMonth())
			{
				
				new ThreadSleep().timer(5);
				timer += 5;
				timer2 += 5;
				/* Place POST data processing methods here */
				/*
				 * Post processing of player data and math for stat's
				 * 
				 */
				while (timer >= 20)
				{
					//TODO Make Broadcast Plugin
				timer = 0;
				ServerMessaging.test();
				}
				while (timer2 >= 3600)
				{
					//FINAL_REMOVE - PacketStat Tracking
					System.err.println(ps.packetStats());
				timer2 = 0;
				}
			}
			new MultiOutputStreamLogger().snLogger();
			loggerRefresh = new DateTime().getDayOfMonth();
		}
    	while (infinite == 0);
    	
	}
}

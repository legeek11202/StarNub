package org.starnub;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.starnub.core.configuration.ConfigurationCheck;
import org.starnub.core.localization.LanguageLoader;
import org.starnub.core.managment.SbServerMonitor;
import org.starnub.core.util.KeyListener;
import org.starnub.core.util.stream.MessageFormater;
import org.starnub.core.util.stream.MultiOutputStreamLogger;
import org.starnub.core.util.timers.ThreadSleep;
import org.starnub.full.network.ProxyServer;

/*
 * Represents the StarNub core.
 */

public final class StarNub {
	
	public StarNub() 
	{
    }
	
	public final class Debug 
	{
		public static final boolean ON = true; /* Poor Mans #ifdef */
	} 
	
	public final class fullWrapper 
	{
		public static final boolean ON = true; /* Turns on or off features */
	} 
	
	/*  Debug Statements
	 *  if (Debug.ON) {System.out.println("Debug: ");}
	 *  if (StarNub.Debug.ON) {System.out.println("Debug: ");}
	 */
	
	public static ResourceBundle language = LanguageLoader.getResources(); 
	public static Map<String, Integer> configVariables = new HashMap<String, Integer>();
	public static Map<String, String> playersOnline = new HashMap<String, String>();
	public static Map<String, String> bannedPlayers = new HashMap<String, String>();
	
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
    	
    	if  (StarNub.fullWrapper.ON) 
    	{
        /* Plug-in Loader */
    		
    	/* Proxy Initialization */
    	Runnable sn_Proxy = new ProxyServer();
//    	Runnable sn_UDP_Proxy = new SN_UDP_Server();
    	new Thread (sn_Proxy).start();
//    	new Thread (sn_UDP_Proxy).start();
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
    	if (Debug.ON) {System.out.println("Debug: StarNub: Today is: "+loggerRefresh);}
		do 
		{
			while (loggerRefresh == new DateTime().getDayOfMonth())
			{
				new ThreadSleep().timer(5);
			}
			new MultiOutputStreamLogger().snLogger();
			loggerRefresh = new DateTime().getDayOfMonth();
		}
    	while (infinite == 0);
    	
	}
}

package org.starnub;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.starnub.configuration.ConfigurationCheck;
import org.starnub.localization.LocalizationLoader;
import org.starnub.managment.SbServerMonitor;
import org.starnub.network.ProxyServer;
import org.starnub.util.KeyListener;
import org.starnub.util.stream.MultiOutputStreamLogger;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

/*
 * Represents the StarNub core.
 */

public final class StarNub {
	
	public final class Debug 
	{
		public static final boolean ON = true; /* Poor Mans #ifdef */
	} 
	
	
	/*  Debug Statments
	 *  if (Debug.ON) {System.out.println("Debug: ");}
	 *  if (StarNub.Debug.ON) {System.out.println("Debug: ");}
	 */
	
	public static ResourceBundle language = LocalizationLoader.getResources(); 
	public static Map<String, Integer> configVariables = new HashMap<String, Integer>();
	public static Map<String, String> playersOnline = new HashMap<String, String>();
	public static Map<String, String> bannedPlayers = new HashMap<String, String>();

	public StarNub() 
	{
    }
	
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
    	
    	/* Plug-in Loader */
    	
    	/* Status Tracker*/
    	
    
    	/* Proxy Initialization */
    	Runnable sn_Proxy = new ProxyServer();
//    	Runnable sn_UDP_Proxy = new SN_UDP_Server();
    	new Thread (sn_Proxy).start();
//    	new Thread (sn_UDP_Proxy).start();
    	
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

	}
}
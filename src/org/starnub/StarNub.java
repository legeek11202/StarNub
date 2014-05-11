package org.starnub;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.starnub.configuration.SN_Configuration;
import org.starnub.localization.SN_LocalizationLoader;
import org.starnub.managment.SB_ServerMonitor;
import org.starnub.network.SN_Server;
import org.starnub.util.SN_KeyListener;
import org.starnub.util.stream.MultiOutputStreamLogger;
import org.starnub.util.stream.SN_MessageFormater;
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
	
	public static ResourceBundle language = SN_LocalizationLoader.getResources(); 
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
    	
    	SN_MessageFormater.msgPrint(language.getString("ss"), 0, 0);
    	
    	/* Runs the StarNub configuration checker */
    	SN_Configuration.checkConfiguration();
    	
    	/* Initiates Logger (MultiOutputStream) */
    	new MultiOutputStreamLogger().snLogger();
    	SN_MessageFormater.msgPrint(language.getString("l"), 0, 0);
    	
    	/* Plug-in Loader */
    	
    	/* Status Tracker*/
    	
    
    	/* Proxy Initialization */
    	Runnable sn_Proxy = new SN_Server();
//    	Runnable sn_UDP_Proxy = new SN_UDP_Server();
    	new Thread (sn_Proxy).start();
//    	new Thread (sn_UDP_Proxy).start();
    	
    	/* Starts the SN_Server WatchDog */
    	Runnable sb_Monitor = new SB_ServerMonitor();
    	new Thread(sb_Monitor).start();
    	SN_MessageFormater.msgPrint(language.getString("sm"), 0, 0);
    	
    	/* Pause Thread */
    	new ThreadSleep().timer(2);

    	/* Starts the KeyListener */
    	Runnable sn_KeyListener = new SN_KeyListener();
    	new Thread(sn_KeyListener).start();
    	SN_MessageFormater.msgPrint(language.getString("kl"), 0, 0);

	}
}
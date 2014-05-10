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
    	SN_MessageFormater.msgPrint(language.getString("l1"), 0, 0);
    	MultiOutputStreamLogger Logger = new MultiOutputStreamLogger();
    	Logger.snLogger();
    	SN_MessageFormater.msgPrint(language.getString("l2"), 0, 0);
    	
    	/* Plug-in Loader */
    	
    	/* Status Tracker*/
    	
    	/* Network Initialization */
    	
    	/* Proxy Initialization */
    	Runnable sn_Proxy = new SN_Server();
    	new Thread (sn_Proxy).start();
    	
    		/* Remote Console Administration */
    	
    	/* Starts the SN_Server WatchDog */
    	SN_MessageFormater.msgPrint(language.getString("sm1"), 0, 0);
    	Runnable sb_Monitor = new SB_ServerMonitor();
    	new Thread(sb_Monitor).start();
    	SN_MessageFormater.msgPrint(language.getString("sm2"), 0, 0);
    	
    	new ThreadSleep().timer(2);

    	/* Starts the KeyListener */
    	SN_MessageFormater.msgPrint(language.getString("kl1"), 0, 0);
    	Runnable sn_KeyListener = new SN_KeyListener();
    	new Thread(sn_KeyListener).start();
    	SN_MessageFormater.msgPrint(language.getString("kl2"), 0, 0);

	}
}
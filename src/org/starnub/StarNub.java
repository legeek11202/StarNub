package org.starnub;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.starnub.configuration.SN_Configuration;
import org.starnub.localization.SN_LocalizationLoader;
import org.starnub.managment.SB_ServerMonitor;
import org.starnub.managment.SN_KeyListener;
import org.starnub.managment.SN_Logger;
import org.starnub.managment.SN_MessageFormater;
import org.starnub.util.SN_ThreadTimer;


/*
 * Represents the StarNub core.
 */

public final class StarNub {
	
	public static ResourceBundle lang = SN_LocalizationLoader.getResources(); 
	public static Map<String, Integer> configVariables = new HashMap<String, Integer>();
	public static Map<String, String> playersOnline = new HashMap<String, String>();
	public static Map<String, String> bannedPlayers = new HashMap<String, String>();
	
	public StarNub() 
	{
    }
	
    public static void main(String [ ] args)
	{
    	System.out.println("\n"
    			+ "=============================================\n"
    			+ "===        StarNub v0.1-alpha             ===\n"
    			+ "=============================================\n"
    			+ "===        Underbalanced                  ===\n"
    			+ "===        Teihoo                         ===\n"
    			+ "=============================================\n");
    	
    	SN_MessageFormater.msgPrint(lang.getString("ss"), 0, 0);
    	/* Runs the StarNub configuration checker */
    	SN_Configuration.snConfigurationCheck();
    	   
    	/* Initiates Logger (SN_MultiOutputStream) */
    	SN_MessageFormater.msgPrint(lang.getString("l1"), 0, 0);
    	SN_Logger.snLogger ();
    	SN_MessageFormater.msgPrint(lang.getString("l2"), 0, 0);

    	/* Starts the Server WatchDog */
    	SN_MessageFormater.msgPrint(lang.getString("sm1"), 0, 0);
    	Runnable sb_Watchdog = new SB_ServerMonitor();
    	new Thread(sb_Watchdog).start();
    	SN_MessageFormater.msgPrint(lang.getString("sm2"), 0, 0);
    	
    	SN_ThreadTimer.startTimer(2);

    	/* Starts the KeyListener */
    	SN_MessageFormater.msgPrint(lang.getString("kl1"), 0, 0);
    	Runnable sn_KeyListener = new SN_KeyListener();
    	new Thread(sn_KeyListener).start();
    	SN_MessageFormater.msgPrint(lang.getString("kl2"), 0, 0);

	}
}
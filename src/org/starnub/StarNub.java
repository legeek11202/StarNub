package org.starnub;

import org.starnub.configuration.SN_Configuration;
import org.starnub.managment.SB_ServerMonitor;
import org.starnub.managment.SN_KeyListener;
import org.starnub.managment.SN_Logger;
import org.starnub.managment.SN_MessageFormater;
import org.starnub.util.SN_ThreadTimer;


/*
 * Represents the StarNub core.
 */

public final class StarNub {

	private StarNub() 
	{
    }
	
    public static void main(String [ ] args)
	{
    	System.out.println("\n"
    			+ "=============================================\n"
    			+ "===        StarNub (Version Null)         ===\n"
    			+ "=============================================\n"
    			+ "===        Underbalanced                  ===\n"
    			+ "===        Teihoo                         ===\n"
    			+ "=============================================\n");
    	
    	/* Runs the StarNub configuration checker */
    	SN_MessageFormater.msgPrint("Staring StarNub...", 0, 0);
    	SN_Configuration.sn_CongiruationCheck();
    	   
    	/* Initiates Logger (SN_MultiOutputStream) */
    	SN_MessageFormater.msgPrint("Starting StarNub Logger...", 0, 0);
    	SN_Logger.snLogger ();
    	SN_MessageFormater.msgPrint("Logger running.", 0, 0);

    	/* Starts the Server WatchDog */
    	SN_MessageFormater.msgPrint("Starting StarNub Server Watchdog...", 0, 0);
    	Runnable sb_Watchdog = new SB_ServerMonitor();
    	new Thread(sb_Watchdog).start();
    	SN_MessageFormater.msgPrint("Server watchdog running.", 0, 0);
    	
    	SN_ThreadTimer.startTimer(2);

    	/* Starts the KeyListener */
    	SN_MessageFormater.msgPrint("Starting StarNub KeyListner...", 0, 0);
    	Runnable sn_KeyListener = new SN_KeyListener();
    	new Thread(sn_KeyListener).start();
    	SN_MessageFormater.msgPrint("KeyListner running.", 0, 0);


    	
    	
	}
}
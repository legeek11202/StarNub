package org.starnub.managment;

public class SbServerStats {

	// TODO push variables to menu, add all time counters
	
	/* All Time */
	static int serverAutoRestarts = 0;
	static int serverCrashes = 0;
	static int serverUnresponsive = 0;
	
	/* Since Wrapper Start */
	static int serverAutoRestartsWrap = 0;
	static int serverCrashesWrap = 0;
	static int serverUnresponsiveWrap = 0;
	
	/* Since SN_Server Last Restart */
	static int serverCrashesTemp = 0;
	static int serverUnresponsiveTemp = 0;
	static int serverUptimeTemp = 0;
	
	public SbServerStats() 
	{
	}
	
	public void getStat(){
	
	}
	
	public void setStat(){
	}
}

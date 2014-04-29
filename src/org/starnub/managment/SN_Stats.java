package org.starnub.managment;

public class SN_Stats {

	// TODO push variables to menu, add all time counters
	
	/* All Time */
	static int serverAutoRestarts = 0;
	static int serverCrashes = 0;
	static int serverUnresponsive = 0;
	
	/* Since Wrapper Start */
	static int serverAutoRestartsWrap = 0;
	static int serverCrashesWrap = 0;
	static int serverUnresponsiveWrap = 0;
	
	/* Since Server Last Restart */
	static int serverCrashesTemp = 0;
	static int serverUnresponsiveTemp = 0;
	static int serverUptimeTemp = 0;	

	public SN_Stats() {
		// TODO Auto-generated constructor stub
	}

}

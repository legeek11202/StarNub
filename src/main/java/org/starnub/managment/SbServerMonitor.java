/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.starnub.managment;

import org.starnub.network.query.SbQueryProcessor;
import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

import java.util.ResourceBundle;

/**
 * This class will monitors the Starbound Server and
 * tracks statistics about its up time, restarts, ect.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 26 May 2014
 */

public class SbServerMonitor implements Runnable {

    private static final int autoRestartTime = StarNub.configVariables.get("Auto_Restart_Timer"); /* Auto restart timer in hours */
    public static SbServerStats s = new SbServerStats();
    private static ResourceBundle lang = StarNub.language; /* Language resource pack, used to reduce characters below */
    private static int snPort = StarNub.configVariables.get("StarNub_Port");

    public SbServerMonitor() {
    }

    public synchronized void run() {
        s.setSnOnlineTime(); /* Set the time SN came online */
        serverMonitor();
    }

    private void serverMonitor() {

        s.resetTempStats(); /* Refresh Temporary Stat's */
        processStartup();
        /* Create a restart time stamp (Hours = Restart org.starnub.configuration variable (((Hours * Minutes)*Seconds)*Milliseconds) ) */
        long futureAutoRestartTime = s.getSbOnlineTime() + (((autoRestartTime * 60) * 60) * 1000);

        do {
            switch (statusChecker()) {
                case "statusOk":
                    new ThreadSleep().timer(30);
                    break;
                case "pCrash":
                    processCrashed();
                    break;
                case "sUnresp":
                    serverUnresponsive();
                    break;
            }
        }
        while (System.currentTimeMillis() <= futureAutoRestartTime); //TODO - Need to calculate current up time on cycle.

        s.addSbAutoRestarts(); /* Decrement auto restart counter */
        MessageFormater.msgPrint(lang.getString("ssmar1") + " " + autoRestartTime + " " + lang.getString("ssmar2"), 0, 0);
        SbProcessManagment.sb_ProcessKill(); /* Kill the Process */
        serverMonitor(); /* Restart this method to clear the stat's */
    }

    private String statusChecker() {
        if (!SbProcessManagment.sb_ProcessStatus()) {
            return "pCrash";
        } else if (!SbQueryProcessor.serverStatus(1)) {
            return "sUnresp";
        } else {
            return "statusOk";
        }
    }

    private void processStartup() {
        MessageFormater.msgPrint(lang.getString("ssm"), 0, 0);
        SbProcessManagment.sb_ProcessStart(); /* Start SB Server process */
        while (!SbQueryProcessor.serverStatus(2)) {
        } /* We will check the server until it is online. */
        MessageFormater.msgPrint(StarNub.language.getString("sb.q.3")+" "+ StarNub.ProtocolVersionPacket.getProtocolVersion(), 0, 0);
        MessageFormater.msgPrint(lang.getString("sta") + " " + snPort + ".", 0, 0);
        s.setSbOnlineTime(); /* Set the time SB came online */
    }

    private void processCrashed() {
        s.addSbCrashes(); /* Decrement crash counter */
        MessageFormater.msgPrint(lang.getString("ssmc"), 0, 1);
        MessageFormater.msgPrint(lang.getString("ssmc1") + " " + s.getSbCrashesTemp() + " " + lang.getString("ssmc2"), 0, 1);
        processStartup(); /* Start SB Server process */
    }

    private void serverUnresponsive() {
        s.addSbUnresponsive(); /* Decrement unresponsive counter */
        MessageFormater.msgPrint(lang.getString("ssmu"), 0, 1);
        MessageFormater.msgPrint(lang.getString("ssmu1") + " " + s.getSbUnresponsiveTemp() + " " + lang.getString("ssmu2"), 0, 1);
        SbProcessManagment.sb_ProcessKill(); /* Kill the unresponsive SB server */
        processStartup(); /* Start up SB Server */
    }
}

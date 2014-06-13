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

import org.starnub.util.stream.MessageFormater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class will direct the Starbound Subprocess Stream which must be dealt
 * with or it will I/O Block. While it has formatted output this is for partial
 * wrapper functionality. While when the wrapper is fully functional we will
 * just discard the stream as we make our own from the org.starnub.network stack.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
 */

public class SbProcessStreamManagment implements Runnable {

    public SbProcessStreamManagment() {
    }

    public synchronized void run() {
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(
                SbProcessManagment.getSbProcess().getInputStream()));
        try {
            while ((line = input.readLine()) != null) {
//					System.out.println(line); /* Prints all data */
                    /* Player Chat */
                if (line.contains("Info:  <")) {
                    MessageFormater.msgPrint(
                            line.substring(7, line.length()), 1, 2);
                }
//					else if (line.contains("Client '") && line.contains("> ("))
//					{
//						/* Player Names */
//						String playerName = line.substring(
//								(1 + line.indexOf("'")), line.lastIndexOf("'"));
//						/* Player IPS */
//						String playerIP = line.substring(
//								(1 + line.indexOf("(")), line.lastIndexOf(":"));
//						/* Player Activity (Disconnects and Connects) */
//						String activity = line
//								.substring((2 + line.indexOf(")")));
//						/* Prints Player Connect and Disconnect to Console */
//						MessageFormater.msgPrint(playerName + " has "
//								+ activity + " (" + playerIP + ").", 1, 0);
//					}
                //TODO KEEP
					/* Prints Starbound SN_Server Version to console */
                else if (line.contains("SN_Server version")) {
                    MessageFormater.msgPrint(
                            line.substring(6, line.length()) + ".", 1, 0);
                } else {
						/*
						 * Discard anything that did not pass the filter.
						 * 
						 * This removed unwanted errors and or Starbound console
						 * spam. You may view the Starbound SN_Server log at
						 * /starbound/Starbound_Server.log if you want to see
						 * anything not filtered about.
						 */
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

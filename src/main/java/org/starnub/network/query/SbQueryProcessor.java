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

package org.starnub.network.query;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

/**
 * This class will manage Queryies to the Starbound Server.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 26 May 2014
 */

public class SbQueryProcessor {

    private static boolean status;
    private static int type; /* 1 = Regular Query, 2 = Server Startup */
    private static int txAttemps = 0; /* Query Attempts */

    public SbQueryProcessor() {
    }

    public static void setStatus(boolean online) {
        status = online;
    }

    public static boolean serverStatus(int i) {
        type = i;
        queryProcessor();
        return status;
    }

    private static void queryProcessor() {
        while (!status) {
            QueryServer.serverStatus();
            if (!status && txAttemps < 12 && type == 1) {
                txAttemps += 1; /* Decrement tries */
                new ThreadSleep().timer(10); /* Wait until retry */
                MessageFormater.msgPrint(StarNub.language.getString("sb.q.1") + " (" + txAttemps + "/12).", 0, 1);
            }
            if (!status && type == 2) {
                new ThreadSleep().timer(5); /* Shorter wait due to start up query */
                MessageFormater.msgPrint(StarNub.language.getString("sb.q.2"), 0, 0);
            }
        }
    }
}

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

package org.starnub.util.stream;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/*
* This class's method will push output to the console and log file.
* 
* This method will return nothing.
**/

public class MultiOutputStreamLogger {

    public MultiOutputStreamLogger() {
    }

    public void snLogger() {
        try {
            String timestamp = new DateTime().toString(DateTimeFormat.forPattern("dd-MMM-yy"));

			/* Appends the data to one file. Each day starts a new log. */
            FileOutputStream fout = new FileOutputStream("StarNub/Server Logs/" + timestamp + ".log", true);
            FileOutputStream ferr = new FileOutputStream("StarNub/Error Logs/" + timestamp + ".log", true);

            MultiOutputStream multiOut = new MultiOutputStream(System.out, fout);
            MultiOutputStream multiErr = new MultiOutputStream(System.err, ferr);

            PrintStream stdout = new PrintStream(multiOut);
            PrintStream stderr = new PrintStream(multiErr);

            System.setOut(stdout);
            System.setErr(stderr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

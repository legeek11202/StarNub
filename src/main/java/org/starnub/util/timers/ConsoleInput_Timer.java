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

package org.starnub.util.timers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Coming Soon.
 *
 * @author Daniel Merwin(Underbalanced)
 * @version 1.0, 17 May 2014 (Incomplete)
 */

public class ConsoleInput_Timer {

    private static String questionAnswer = "";
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private boolean reading = true;
    TimerTask task = new TimerTask() {
        public void run() {
            reading = false;
        }
    };

    public ConsoleInput_Timer() {
    }

    public static String inputCall(String questionString, int length) {
        try {
            new ConsoleInput_Timer().getInput(questionString, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionAnswer;
    }

    private void getInput(String questionString, int length) throws Exception {
        Timer timer = new Timer();
        timer.schedule(task, length * 1000);
        System.out.println(questionString);
        while (reading) {
            try {
                if (in.ready()) {
                    try {
                        questionAnswer = in.readLine();
                        timer.cancel();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                new ThreadSleep().timer(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

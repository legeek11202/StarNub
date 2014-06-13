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

package org.starnub.util;

import org.starnub.managment.SbProcessManagment;

import java.util.Scanner;

/**
 * This class will wait for input.
 * <p>
 * Input:
 * <br>
 * 1 - Terminates the Server Wrapper and Server.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 17 May 2014
 */

public class KeyListener implements Runnable {

    public KeyListener() {
    }

    public synchronized void run() {
        while (true) {
            menu();
        }
    }

    private void menu (){
        System.out
                .println("Press 1 and then enter to shutdown the Wrapper and SN_Server.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println(i);
        //TODO Graceful Exit and miss type corrector
        if (i == 1) {
            /* Exit program (Not Graceful) */
            if (SbProcessManagment.sb_ProcessStatus()) {
                SbProcessManagment.sb_ProcessKill();
            }
            System.exit(1);
        }
        else{
            sc.close();
            menu();
        }

    }
}
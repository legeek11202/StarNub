package util;

import managment.SbProcessManagment;

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
        sc.close();
    }
}
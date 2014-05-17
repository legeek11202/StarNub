package org.starnub.util;

import java.util.Scanner;

import org.starnub.managment.SbProcessManagment;

/**
 * This class will wait for input.
 * <p>
 * Input:
 * <p>
 * 1 - Terminates the Server Wrapper and Server.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1
 * 
 */

public class KeyListener implements Runnable {

	public KeyListener()
	{
	}

	public synchronized void run()
	{
		System.out
				.println("Press 1 and then enter to shutdown the Wrapper and SN_Server.");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		System.out.println(i);

		if (i == 1)
		{
			/* Exit program (Not Graceful) */
			if (SbProcessManagment.sb_ProcessStatus())
			{
				SbProcessManagment.sb_ProcessKill();
			}
			System.exit(1);
		}
		sc.close();
	}
}
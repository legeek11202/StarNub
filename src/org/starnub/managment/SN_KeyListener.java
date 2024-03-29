package org.starnub.managment;

import java.util.Scanner;

/*
 * Temporary Key Listener for program exit
 * Future - StarNub Menu Options
 */

public class SN_KeyListener implements Runnable {

	public synchronized void run() 
	{
		System.out.println("Press 1 and then enter to shutdown the Wrapper and Server.");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		System.out.println(i);
	
		if (i == 1) 
		{
			/* Exit program (Not Graceful) */
			if(SB_ProcessManagment.sb_ProcessStatus())
			{
			SB_ProcessManagment.sb_ProcessKill();
			}
			System.exit(1);
		}
		sc.close();
	}
	
	public SN_KeyListener() 
	{
	}
}
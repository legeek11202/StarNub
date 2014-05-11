package org.starnub.util;

import java.util.Scanner;

import org.starnub.managment.SbProcessManagment;

/*
 * Temporary Key Listener for program exit
 * Future - StarNub Menu Options
 */

public class KeyListener implements Runnable {

	public synchronized void run() 
	{
		System.out.println("Press 1 and then enter to shutdown the Wrapper and SN_Server.");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		System.out.println(i);
	
		if (i == 1) 
		{
			/* Exit program (Not Graceful) */
			if(SbProcessManagment.sb_ProcessStatus())
			{
			SbProcessManagment.sb_ProcessKill();
			}
			System.exit(1);
		}
		sc.close();
	}
	
	public KeyListener() 
	{
	}
}
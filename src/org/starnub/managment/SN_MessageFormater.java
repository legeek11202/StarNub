package org.starnub.managment;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/*
* This class's method will print out StarNub messages with a time stamp.
* 
* This method will return nothing.
**/

public class SN_MessageFormater {

	private static String SN = "[StarNub ";
	private static String[] typeArray = new String[]
			{
				"Info]: ",
				"Error]: ",
			};

	public static void msgPrint(String message,int type)
	{
		String currentTime = new DateTime().toString(DateTimeFormat.forPattern("[HH:mm:ss]"));
		System.out.println(currentTime+SN+typeArray[type]+message);
	}
	
	public SN_MessageFormater() 
	{
	}

}

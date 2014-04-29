package org.starnub.managment;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/*
* This class's method will print out StarNub messages with a time stamp.
* 
* This method will return nothing.
**/

public class SN_MessageFormater {
	
	private static String[] serverArray= new String[]
			{
				"[StarNub ",
				"[Starbound ",
			};
	private static String[] typeArray = new String[]
			{
				"Info]: ",
				"Error]: ",
				"Chat]: ",
			};

	public static void msgPrint(String message,int server,int type)
	{
		new DateTime();
		System.out.println(DateTime.now().toString(DateTimeFormat.forPattern("[HH:mm:ss]"))+serverArray[server]+typeArray[type]+message);
	}
	
	public SN_MessageFormater() 
	{
	}

}

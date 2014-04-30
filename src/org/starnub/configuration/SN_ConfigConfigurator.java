package org.starnub.configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.starnub.managment.SN_MessageFormater;
import org.starnub.util.SN_TaskTimer;

public class SN_ConfigConfigurator {
	
	private static String[] snConfigQuestions = new String[]
			{
			"\nPlease enter the port that you want players to connect to. (Default: 21025)",
			"\nPlease enter the port that you want Starbound to use to speak with StarNub. (Default: 21024)",
			"\nPlease enter the amount of hours you want to use between Auto Restart Starbound. (Default: 4)",
			};
    
	private static String[]  snConfigOptions = new String[]
        {
		"StarNub_Port",
		"Starbound_Port",
        "Auto_Restart_Timer",
        };
	
	private static String[]  snConfigValues = new String[]
	        {
			"21025",
	        "21024",
	        "4",
	        };  
	static Properties p = new Properties();

	public static void createConfig()
	{
		configurationQuestions();
	}

	private static void configurationQuestions()
	{
			OutputStream StarNub = null;
			int a = 0;
			SN_MessageFormater.msgPrint("\n\nThis prompt will only come up if your configuration is missing. Type in the value or hit 'Enter' to accept the default value", 0, 0);
			/* Loop through array of configuration questions */
			for(String Qs : snConfigQuestions) 
			{
				/* Calls the TaskTimer and waits a 60 second reply */
				String answerString = SN_TaskTimer.inputCall(Qs,5);
				if (answerString.isEmpty())
				{
					p.setProperty(snConfigOptions[a], snConfigValues[a]);
				}
				else
				{
					p.setProperty(snConfigOptions[a], answerString);
				}
				System.out.println(a);
				a += 1;
				System.out.println(a);
			}
			SN_MessageFormater.msgPrint(
			  "\nPlayers will connect to the following port: "+p.getProperty(snConfigOptions[0])
    		+ "\nStarNub will communicate to Starbound on the following: "+p.getProperty(snConfigOptions[1])
    		+ "\nYour Server will auto restart every "+p.getProperty(snConfigOptions[2])+" hour(s).", 0, 0);
			String answerString = SN_TaskTimer.inputCall("\nAre these correct? (Y/N)(Default Y)",60);
			if (answerString.equalsIgnoreCase("Y") || answerString.isEmpty())
			{
				try 
				{
				StarNub = new FileOutputStream("StarNub/StarNub.config");
				p.store(StarNub, null);
				SN_MessageFormater.msgPrint("StarNub is now configured.", 0, 0);
				}
				catch (IOException io) 
				{
					io.printStackTrace();
					SN_MessageFormater.msgPrint("Configuration creation error: Java Message: "+io.getMessage(), 0, 1);
				} 
				finally 
				{
					if (StarNub != null) 
					{
						try 
						{
							StarNub.close();
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
							SN_MessageFormater.msgPrint("Configuration creation error: Java Message: "+e.getMessage(), 0, 1);
						}
					}	 
				}
			}
			else
			{
				configurationQuestions();
			}	
		
	}
	
	public SN_ConfigConfigurator() 
	{
	}
}
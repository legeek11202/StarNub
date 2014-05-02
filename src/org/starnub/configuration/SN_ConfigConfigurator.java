package org.starnub.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.starnub.StarNub;
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

	private static String filePath = "StarNub/StarNub.config";
	
	public static void validateConfig()
	{
		configurationValidation();
	}
	
	private static void configurationQuestions()
	{
			OutputStream starNubConfig = null;
			Properties prop = new Properties();
			int a = 0;
			String answerString;
			SN_MessageFormater.msgPrint("Creating StarNub Configuration...", 0, 0);
			SN_MessageFormater.msgPrint("\n\nThis prompt will only come up if your configuration is missing. \nType in the value or hit 'Enter' to accept the default value.", 0, 0);
			/* Loop through array of configuration questions */
			for(String qS : snConfigQuestions) 
			{
				/* Calls the TaskTimer and waits 30 second reply */
				answerString = SN_TaskTimer.inputCall(qS,30);
				if (answerString.isEmpty())
				{
					prop.setProperty(snConfigOptions[a], snConfigValues[a]);
					StarNub.configVariables.put(snConfigOptions[a], Integer.parseInt(snConfigValues[a]));
				}
				else
				{
					prop.setProperty(snConfigOptions[a], answerString);
					StarNub.configVariables.put(snConfigOptions[a], Integer.parseInt(answerString));
				}
				a += 1;
			}
			SN_MessageFormater.msgPrint(
			  "\nPlayers will connect to the following port: "+prop.getProperty(snConfigOptions[0])
			  + "\nStarNub will communicate to Starbound on the following: "+prop.getProperty(snConfigOptions[1])
			  + "\nYour Server will auto restart every "+prop.getProperty(snConfigOptions[2])+" hour(s).", 0, 0);
			answerString = SN_TaskTimer.inputCall("\nAre these correct? (Y/N)(Default Y)",30);
			if (answerString.equalsIgnoreCase("Y") || answerString.isEmpty())
			{
				try 
				{
				starNubConfig = new FileOutputStream(filePath);
				prop.store(starNubConfig, null);
				SN_MessageFormater.msgPrint("StarNub is now configured.", 0, 0);
				}
				catch (IOException e) 
				{
					SN_MessageFormater.msgPrint("Configuration creation error.", 0, 1);
	    			e.printStackTrace();
				} 
				finally 
				{
					if (starNubConfig != null) 
					{
						try 
						{
							starNubConfig.close();
						} 
						catch (IOException e) 
						{
							SN_MessageFormater.msgPrint("Configuration creation error.", 0, 1);
			    			e.printStackTrace();
						}
					}	 
				}
			}
			else
			{
				StarNub.configVariables.clear();
				configurationQuestions();
			}	
	}
	
	public static void configurationValidation()
	{

		Properties prop = new Properties();
		try 
		{
			InputStream starNubConfig = new FileInputStream(filePath);
			try 
			{
				prop.load(starNubConfig);
			} 
			catch (IOException e) 
			{
				SN_MessageFormater.msgPrint("ConfigConfigurator: Error loading properties.", 0, 0);
				e.printStackTrace();
			}
			for(String keys : snConfigOptions) 
			{
				StarNub.configVariables.put(keys, Integer.parseInt(prop.getProperty(keys)));
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			SN_MessageFormater.msgPrint("ConfigConfigurator: Configuration does not exsist.", 0, 0);
			configurationQuestions();
		}
	}
	
	public SN_ConfigConfigurator() 
	{
	}
}
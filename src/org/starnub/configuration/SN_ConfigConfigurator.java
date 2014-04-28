package org.starnub.configuration;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import org.starnub.managment.SN_MessageFormater;

public class SN_ConfigConfigurator {
	
	int timer = 2;
	
	private static String[] snConfigOptions = new String[]
			{
			"Starbound_Port",
			"StarNub_Port",
			"Auto_Restart_Timer"
			};
	
	private static String lines = "\n\n";
	
	public void SN_ConfigValidator ()
	{
		//TODO Configuration validation
		
	}
	
	public static void SN_ConfigCreator ()
	{
		Properties p = new Properties();
		OutputStream StarNub = null;
		
		//TODO Input Timer
	 
		try 
		{
			SN_MessageFormater.msgPrint(lines
					+ "\nThis prompt will only come up if your configuration is missing."
					+ "\nYou will have 60 seconds to make each choice or the defaults will be selected for you.", 0);
			StarNub = new FileOutputStream("StarNub/StarNub.config");
	        BufferedReader questions = new BufferedReader(new InputStreamReader(System.in));
	       
	        System.out.print("\n\n"
	        		+ "Please enter the port that you want players to connect to. (Default: 21025)");
	        String snPort = questions.readLine();
	        if (isEmpty(snPort))
	        {
	        	p.setProperty(snConfigOptions[0], "21025");
	        	snPort = "21025";
	        }
	        else
	        {
	        	p.setProperty(snConfigOptions[0], snPort);
	        }
	        
	        System.out.print(lines
	        		+ "Please enter the port that you want Starbound to use to speak with StarNub. (Default: 21024)");
	        String sbPort = questions.readLine();
	        if (isEmpty(sbPort))
	        {
	        	p.setProperty(snConfigOptions[1], "21024");
	        	sbPort = "21024";
	        }
	        else
	        {
	        	p.setProperty(snConfigOptions[1], sbPort);
	        }
	        
	        System.out.print(lines
	        		+ "Please enter the amount of hours you want to use between Auto Restart Starbound. (Default: 4)");
	        String autoRestartTimer = questions.readLine();
	        if (isEmpty(autoRestartTimer))
	        {
	        	p.setProperty(snConfigOptions[2], "4");
	        	autoRestartTimer = "4";
	        }
	        else
	        {
	        	p.setProperty(snConfigOptions[2], autoRestartTimer);
	        }
	        System.out.print(lines
	        		+ "\nPlayers will connect to the following port: "+snPort
	        		+ "\nStarNub will communicate to Starbound on the following: "+sbPort
	        		+ "\nYour Server will auto restart every "+autoRestartTimer+" hour(s)."
	        		+ "\nAre these correct? (y/n)(Default y)");
	        String correctValues = questions.readLine();
	        if (isEmpty(correctValues))
	        {
	        	correctValues = "y";
	        	p.store(StarNub, null);
	        }
	        else
	        {
	        	if (correctValues.equalsIgnoreCase("y"))
	        	{
	        		p.store(StarNub, null);
	        	}
	        	else 
	        	{
	        		System.out.print("Restarting configuration prompt.");
	        		SN_ConfigCreator ();
	        	}
	        	SN_MessageFormater.msgPrint("StarNub is now configured.", 0);
	        	}
	 
		} 
		catch (IOException io) 
		{
			io.printStackTrace();
			SN_MessageFormater.msgPrint("Configuration creation error: Java Message: "+io.getMessage(), 1);
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
					SN_MessageFormater.msgPrint("Configuration creation error: Java Message: "+e.getMessage(), 1);
				}
			}
	 
		}
	}
	
	public void SN_ConfigLoader() 
	{
		//TODO Configuration Loader
	}
	
	public static boolean isEmpty(String str) 
	{
		return str == null || str.length() == 0;
	} 

	public SN_ConfigConfigurator() 
	{
	}

}

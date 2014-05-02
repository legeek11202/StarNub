package org.starnub.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.managment.SN_MessageFormater;
import org.starnub.util.SN_TaskTimer;

public class SN_ConfigConfigurator {
	
	private static ResourceBundle s = StarNub.lang;
	
	private static String[] snConfigQuestions = new String[]
			{
			"\n"+s.getString("cq1"),
			"\n"+s.getString("cq2"),
			"\n"+s.getString("cq3"),
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
			SN_MessageFormater.msgPrint(s.getString("cc"), 0, 0);
			SN_MessageFormater.msgPrint("\n\n"+s.getString("cc1")+"\n"+s.getString("cc2"), 0, 0);
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
			  "\n"+s.getString("cqc1")+" "+prop.getProperty(snConfigOptions[0])
			  + "\n"+s.getString("cqc2")+" "+prop.getProperty(snConfigOptions[1])
			  + "\n"+s.getString("cqc3")+" "+prop.getProperty(snConfigOptions[2])+" "+s.getString("cqc4"), 0, 0);
			answerString = SN_TaskTimer.inputCall("\n"+s.getString("cqc5"),30);
			if (answerString.equalsIgnoreCase("Y") || answerString.isEmpty())
			{
				try 
				{
				starNubConfig = new FileOutputStream(filePath);
				prop.store(starNubConfig, null);
				SN_MessageFormater.msgPrint(s.getString("cf"), 0, 0);
				}
				catch (IOException e) 
				{
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
				e.printStackTrace();
			}
			for(String keys : snConfigOptions) 
			{
				StarNub.configVariables.put(keys, Integer.parseInt(prop.getProperty(keys)));
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			SN_MessageFormater.msgPrint(s.getString("sn.cc.3"), 0, 0);
			configurationQuestions();
		}
	}
	
	public SN_ConfigConfigurator() 
	{
	}
}
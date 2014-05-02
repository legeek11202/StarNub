package org.starnub.localization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.managment.SN_MessageFormater;

public class SN_LocalizationLoader {
	
	public static ResourceBundle getResources ()
	{
		return null;
	}
	
	public static Locale getLocal ()
	{
		return null;
	}
	
	private static void localConfig()
	{
		Properties prop = new Properties();
		String filePath = "StarNub/Localization.config";
		
			try 
			{
				InputStream starLocalizationInput = new FileInputStream("StarNub/Localization.config");
				try 
				{
					prop.load(starLocalizationInput);
				} 
				catch (IOException e) 
				{
					SN_MessageFormater.msgPrint("ConfigConfigurator: Error loading properties.", 0, 0);
					e.printStackTrace();
				}
			}
			catch (FileNotFoundException e) 
			{

				FileOutputStream starLocalizationOutput = null;
				
				
				try 
				{
				starLocalizationOutput = new FileOutputStream(filePath);
				prop.store(starLocalizationOutput, null);
				SN_MessageFormater.msgPrint("StarNub is now configured.", 0, 0);
				}
				catch (IOException io) 
				{
					SN_MessageFormater.msgPrint("Configuration creation error.", 0, 1);
	    			io.printStackTrace();
				} 
				finally 
				{
					if (starLocalizationOutput != null) 
					{
						try 
						{
							starLocalizationOutput.close();
						} 
						catch (IOException io) 
						{
							SN_MessageFormater.msgPrint("Configuration creation error.", 0, 1);
			    			io.printStackTrace();
						}
					}	 
				}
				
			}
			
			
			//TODO load locaization
	}
	

	public SN_LocalizationLoader() {
	}
}

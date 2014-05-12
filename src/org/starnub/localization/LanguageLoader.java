package org.starnub.localization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ConsoleInput_Timer;

/*
 * These strings are static. They will not be localized.
 * All other strings will be based on local file.
 * 
 */

public class LanguageLoader {
	
	private static String language;
	
	public LanguageLoader() 
	{
	}
	
	public static ResourceBundle getResources ()
	{
		localConfig();
		Locale locale = new Locale(language);
		MessageFormater.msgPrint("Language: "+language, 0, 0);
		return ResourceBundle.getBundle("org.starnub.localization.language", locale);
	}
	
	private static void localConfig()
	{
		Properties prop = new Properties();
		String filePath = "StarNub/Localization.config";
		
			try 
			{
				InputStream starLocalizationInput = new FileInputStream("StarNub/Language.config");
				try 
				{
					prop.load(starLocalizationInput);
					language = prop.getProperty("Language");
					language = language.toLowerCase();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			catch (FileNotFoundException e) 
			{

				FileOutputStream starLocalizationOutput = null;
				MessageFormater.msgPrint("No language configured.", 0, 0);
				String answerString = ConsoleInput_Timer.inputCall(""
						+ "\n\n\n\nYou have 1 minutes to select your language. (Default English)"
						+ "\nPlease type in the number and press 'Enter'."
						+ "\n\nSupported Languages"
						+ "\n==================="
						+ "\n1. English"
						+ "\n",60);
				switch (answerString)
				{
					case "": language = "english"; break;
					case "1": language = "english"; break;
					default: 
					{
					MessageFormater.msgPrint("Error language selection.", 0, 0);
					localConfig();
					}
				}
				try 
				{
					prop.setProperty("Language", language);
					starLocalizationOutput = new FileOutputStream(filePath);
					prop.store(starLocalizationOutput, null);
					MessageFormater.msgPrint("Language configured.", 0, 0);
				}
				catch (IOException io) 
				{
					MessageFormater.msgPrint("Language configuration creation error.", 0, 1);
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
							MessageFormater.msgPrint("Language configuration creation error.", 0, 1);
							io.printStackTrace();
						}
					}	 
				}
			}
	}
}
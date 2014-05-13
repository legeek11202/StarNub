package org.starnub.core.configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.starnub.StarNub;
import org.starnub.core.util.simplejson.JSONObject;
import org.starnub.core.util.simplejson.JSONPrettyPrint;
import org.starnub.core.util.simplejson.parser.JSONParser;
import org.starnub.core.util.simplejson.parser.ParseException;

public class SbConfigurator {
	
	public SbConfigurator() 
	{
	}
	
    public static void sbConfigConfiguration() 
    {
    	configStarboundConfiguration();
    }
    
	private static void configStarboundConfiguration () 
    {	
		JSONParser parser = new JSONParser();
		Integer playerConnectPort;
		if  (StarNub.fullWrapper.ON) /* Full wrapper */
		{
			playerConnectPort = StarNub.configVariables.get("Starbound_Port");
		}
		else /* Server manager only */
		{
			playerConnectPort = StarNub.configVariables.get("StarNub_Port");
		}	
		try 
		{
			Object obj = parser.parse(new FileReader("starbound.config"));
			JSONObject jsonObject = (JSONObject) obj;
			jsonObject.put("gamePort", new Long(playerConnectPort));
			FileWriter file = new FileWriter("starbound.config");
			file.write(JSONPrettyPrint.toJSONString(jsonObject));
			file.flush();
			file.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
    }
}
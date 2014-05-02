package org.starnub.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.starnub.StarNub;
import org.starnub.managment.SN_MessageFormater;

public class SB_Query {
	
	static int sbPort = StarNub.configVariables.get("Starbound_Port");
	
	public static boolean getServerResponse ()
	{
		return serverResponse();
	}

	private static boolean serverResponse ()
	{
		
		boolean continueSending = true;
		int packetAttempt = 0;
		int packetsTry = 12;
		DatagramSocket ds = openSocket();
		
		try {
			/* Set packet receive timeout in milliseconds */
			ds.setSoTimeout(10000);
		} catch (SocketException e1) 
		{
			SN_MessageFormater.msgPrint("Starbound Query: Unable to set the packet receive timeout period.", 0, 1);
			e1.printStackTrace();
		}
		
		try
		{	
			/* Attempts to send a packet, if no response, retry X times */
			while (continueSending && packetAttempt < packetsTry) 
			{
				ds.send(packetAssembly());
				packetAttempt++;
				try 
				{
					ds.receive(packetReceive());
					/* A packet has been received : stop sending */
					continueSending = false; 
					ds.close();
					return true;
				}
				catch (SocketTimeoutException e) 
				{
					/* No response received after 3 second - Retry sending */
					SN_MessageFormater.msgPrint("UDP Query: No response from the Starbound server. Packet "+packetAttempt+" of "+packetsTry+" tried." , 0, 1);
				}
			}
			ds.close();
		}
		catch (Exception e)
		{
			SN_MessageFormater.msgPrint("UDP Query: Error sending packets.", 0, 1);
			return true;
		}
		SN_MessageFormater.msgPrint("UDP Query: The Starbound server could not be reached."
				+ "\n"
				+ "Starbound Port: "+sbPort+ "StarNub Port: "+StarNub.configVariables.get("StarNub_Port"), 0, 1);
		return false;
	}
	
	/* Packet assembly */
	private static DatagramPacket packetAssembly() throws UnknownHostException 
	{
		/* TSource Engine Query = https://developer.valvesoftware.com/wiki/Server_queries */
		char peer0_0[] = 
			{ 
				0xff, 0xff, 0xff, 0xff, 
				0x54, 0x53, 0x6f, 0x75,
				0x72, 0x63, 0x65, 0x20, 
				0x45, 0x6e, 0x67, 0x69, 
				0x6e, 0x65, 0x20, 0x51, 
				0x75, 0x65, 0x72, 0x79, 0x00 
			};
		byte[] data = new String(peer0_0).getBytes();
		return new DatagramPacket(data, 0, data.length, InetAddress.getLoopbackAddress(), sbPort);
	}
	
	/* Receive portion of the UDP Query */
	private static DatagramPacket packetReceive()
	{

		return new DatagramPacket(new byte[1024], 1024);
	}
	
	/* Socket creation and address bind */
	private static DatagramSocket openSocket()
	{
		for (int port = 9000 ; port <= 65000 ; port++)
		{
			try 
			{
				return new DatagramSocket (port,InetAddress.getLoopbackAddress());
			} 
			catch (IOException e) 
			{
				/* Tries the next port */
			}
		}
		SN_MessageFormater.msgPrint("Starbound Query: Error: Creating Socket.", 0, 1);
		return null;
	}
	
	public SB_Query() 
	{
	}
}

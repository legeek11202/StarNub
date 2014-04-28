package org.starnub.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.starnub.managment.SN_MessageFormater;

public class SB_Query {
	
	public static boolean getServerResponse ()
	{
		return serverResponse();
	}

	private static boolean serverResponse ()
	{
		try
		{
		DatagramPacket queryPacket = packetAssembly();
		DatagramPacket responsePacket = packetReceive();
		DatagramSocket ds = new DatagramSocket(65000);
		boolean continueSending = true;
		int counter = 0;
	
		//Set packet send/receive timeout (Milliseconds)
		ds.setSoTimeout(2000);
		
			// Sends packet up to 5 times if no response in 2 seconds between packets
			while (continueSending && counter < 5) 
			{
				ds.send(queryPacket);
				counter++;
				try 
				{
					ds.receive(responsePacket);
					continueSending = false; // A packet has been received : stop sending
					ds.close();
					return true;
				}
				catch (SocketTimeoutException e) 
				{
					// No response received after 1 second. Continue sending.
					SN_MessageFormater.msgPrint("Starbound Query: No response from your Starbound Server", 1);
				}
			}
			SN_MessageFormater.msgPrint("Starbound Query: Error: While Loop", 1);
			ds.close();
		}
		catch (Exception e)
		{
			SN_MessageFormater.msgPrint("Starbound Query: Error: Creating Socket or Socket Functions", 1);
			
		}
		return false;
	}
	
	private static DatagramPacket packetAssembly() throws UnknownHostException 
	{
		/* Building the packet */
		byte[] data;
		// TSource Engine Query (https://developer.valvesoftware.com/wiki/Server_queries)
		char peer0_0[] = 
			{ 
				0xff, 0xff, 0xff, 0xff, 
				0x54, 0x53, 0x6f, 0x75,
				0x72, 0x63, 0x65, 0x20, 
				0x45, 0x6e, 0x67, 0x69, 
				0x6e, 0x65, 0x20, 0x51, 
				0x75, 0x65, 0x72, 0x79, 0x00 
			};
		data = new String(peer0_0).getBytes();
		// TODO import port variable
		return new DatagramPacket(data, 0, data.length, InetAddress.getByName("127.0.0.1"), 21025);
	}

	private static DatagramPacket packetReceive()
	{
		/* Receive portion of the UDP Query */
		byte[] rec = new byte[1024];
		return new DatagramPacket(rec, 1024);
	}
	
	public SB_Query() 
	{
	}
}

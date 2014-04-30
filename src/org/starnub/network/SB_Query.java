package org.starnub.network;

import java.io.IOException;
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
		
		boolean continueSending = true;
		int counter = 0;
		DatagramSocket ds = openSocket();
		
		//Set packet send/receive timeout (Milliseconds)
		try
		{
			// Sends packet up to 10 times if no response in 2 seconds between packets
			while (continueSending && counter < 10) 
			{
				ds.setSoTimeout(3000);
				ds.send(packetAssembly());
				counter++;
				try 
				{
					ds.receive(packetReceive());
					continueSending = false; // A packet has been received : stop sending
					ds.close();
					return true;
				}
				catch (SocketTimeoutException e) 
				{
					// No response received after 3 second. Continue sending.
					SN_MessageFormater.msgPrint("Starbound Query: No response from the Starbound server.", 0, 1);
				}
			}
			ds.close();
		}
		catch (Exception e)
		{
			SN_MessageFormater.msgPrint("Starbound Query: Error sending packets.", 0, 1);
			return true;
		}
		SN_MessageFormater.msgPrint("Starbound Query: The Starbound server could not be reached.", 0, 1);
		// Dump UDP query port, server port wrapper port
		return false;
	}
	
	private static DatagramPacket packetAssembly() throws UnknownHostException 
	{
		/* Building the packet */
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
		byte[] data = new String(peer0_0).getBytes();
		// TODO import port variable
		return new DatagramPacket(data, 0, data.length, InetAddress.getLoopbackAddress(), 21025);
	}

	private static DatagramPacket packetReceive()
	{
		/* Receive portion of the UDP Query */
		return new DatagramPacket(new byte[1024], 1024);
	}
	
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
				// Try the next port
				System.out.println("Port "+port+" unable to bind");
			}
		}
		SN_MessageFormater.msgPrint("Starbound Query: Error: Creating Socket.", 0, 1);
		return null;
	}
	
	private static InetAddress setInetAddress()
	{
		//TODO check various address method
		InetAddress.getLoopbackAddress();
		return null;
	}
	
	public SB_Query() 
	{
	}
}

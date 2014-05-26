package org.starnub.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.starnub.StarNub;

public class UDPProxyServer implements Runnable {

	private final int snServerPort = StarNub.configVariables.get("StarNub_Port");
	private final String sbRemoteHost = "127.0.0.1";
	private final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");
	
	DatagramSocket ds;
	
	@Override
	public void run()
	{
		

		try {
			ds = new DatagramSocket(snServerPort);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Buffers for receive a request and return the real server answer.
		// If we receive more then 1024 bytes from the client
		// they will be discard.
		byte[] request = new byte[1024];
		byte[] reply= new byte[4096];
		
		while(true) {
		
			try {
				
				// (1) receive data from client
				DatagramPacket from_client = new DatagramPacket(request,request.length);
				ds.receive(from_client);
				// Need to create another buffer with the size 
				// of bytes that we really received from client
				byte[] real_request = new byte[from_client.getLength()];
				for(int i=0;i<from_client.getLength();i++) real_request[i] = request[i];
				
				// (2) sending the received data to the server
				InetAddress IPAddress = InetAddress.getByName(sbRemoteHost);
				DatagramPacket sendPacket =
					new DatagramPacket(real_request,real_request.length,IPAddress,sbRemotePort);
				ds.send(sendPacket);
					
				// (3) reading the server answer
				DatagramPacket from_server = new DatagramPacket(reply,reply.length);
				ds.receive(from_server);
				byte[] real_reply = new byte[from_server.getLength()];
				for(int i=0;i<from_server.getLength();i++) real_reply[i]=reply[i];
				
				// (4) returning that answer to the client
				InetAddress address = from_client.getAddress();
				int port = from_client.getPort();
				DatagramPacket to_client =
					new DatagramPacket(real_reply,real_reply.length,address,port);
				ds.send(to_client);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}//end-while
	}
}
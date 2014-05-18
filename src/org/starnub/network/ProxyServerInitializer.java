package org.starnub.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import org.starnub.network.handlers.ConnectionInspector;
import org.starnub.network.handlers.PacketDecoder;
import org.starnub.network.handlers.Frontend;

/**
 * This class initializes the initial channel handlers.
 * <p>
 * Credit goes to Netty.io (Asynchronous API) examples.
 * <p>
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
 */

public class ProxyServerInitializer extends ChannelInitializer<SocketChannel> {

	private final String sbRemoteHost;
	private final int sbRemotePort;

	public ProxyServerInitializer(String sbRemoteHost, int sbRemotePort)
	{
		this.sbRemoteHost = sbRemoteHost;
		this.sbRemotePort = sbRemotePort;
	}

	/* We use an initializer to set up any handlers for this channel */
	@Override
	public void initChannel(SocketChannel ch) throws Exception
	{
		/*
		 * This handler will handle data coming from a Starbound Client into the
		 * Proxy when the data is received this Handler will open a channel to
		 * the Starbound Server. This handler is the last handler in this
		 * channel
		 */

		/* Packet decoder will open and read SB Data */
		ch.pipeline().addFirst("PacketDecoder", new PacketDecoder());
		/*
		 * Connection Inspector will check the IP and UUID for bans before it
		 * removes itself
		 */
		// ch.pipeline().addAfter("PacketDecoder", "ConnectionInspector", new
		// ConnectionInspector(sbRemoteHost, sbRemotePort));

		/* Debug Version */
		// ch.pipeline().addFirst("ConnectionInspection", new
		// ConnectionInspector(sbRemoteHost, sbRemotePort));
	}
}
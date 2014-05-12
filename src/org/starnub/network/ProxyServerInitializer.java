package org.starnub.network;

import org.starnub.network.handlers.ProxyFrontendHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

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
    	/* This handler will handle data coming from a Starbound Client into the Proxy
    	 * when the data is received this Handler will open a channel to the Starbound 
    	 * Server. This handler is the last handler in this channel */
    	ch.pipeline().addLast(new ProxyFrontendHandler(sbRemoteHost, sbRemotePort));
    }
}
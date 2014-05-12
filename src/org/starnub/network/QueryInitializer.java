package org.starnub.network;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

import org.starnub.network.handlers.QueryHandler;

public class QueryInitializer extends ChannelInitializer<Channel> {

    public QueryInitializer() 
    {
    }
    
    /* We use an initializer to set up any handlers for this channel */
    @Override
    public void initChannel(Channel ch) throws Exception 
    {
    	/* Handler for future use with packets and connection assurance */
    	ch.pipeline().addLast(new QueryHandler());
    }
}
package org.starnub.full.network.handlers;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


/* Close channel on idle connections */
public class IdleStateHandler extends ChannelDuplexHandler {
	
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
            	System.out.println("readeridle");
                ctx.channel().close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
            	System.out.println("writer");
            	ctx.channel().close();
            }
        }
    }
}
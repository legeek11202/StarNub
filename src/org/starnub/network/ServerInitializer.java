package org.starnub.network;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;
    private final int remotePort;

    public ServerInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new LoggingHandler(LogLevel.INFO),
                new ServerFrontendHandler(remoteHost, remotePort));
        ch.pipeline().addFirst(
        		"frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4));
        ch.pipeline().addFirst(
        		"bytesDecoder", new ByteArrayDecoder());
    }
}
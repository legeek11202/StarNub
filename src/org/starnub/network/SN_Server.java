package org.starnub.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class SN_Server {
	
	//TODO Import Variables
	int serverPort = 21025;
	String remoteHost = "127.0.0.1";
	int remotePort = 21024;

	    public SN_Server() {
	    }

	    public void run() throws Exception {
	        // Configure the bootstrap.
	        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class)
	             //.childHandler(new ServerInitializer(remoteHost, remotePort))
	             .bind(serverPort).sync().channel().closeFuture().sync();
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }

	    public static void main(String[] args) throws Exception {
	        new SN_Server().run();
	    }
	}
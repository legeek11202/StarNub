package org.starnub.network;
import io.netty.bootstrap.ChannelFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;


public class Server {
	
	    public Server() {
	    }

	    public void run() throws Exception {
	    	
	    	//TODO Configuration variable import
	    	int serverPort = 21025;
	    	String remoteHost = "127.0.0.1";
	    	int remotePort = 21024;
	    	
	    	//ChannelFactory factory = new NioServerSocketChannelFactory (
	        EventLoopGroup bossGroup = new NioEventLoopGroup();
	        EventLoopGroup workerGroup = new NioEventLoopGroup();

	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class)
	             .childHandler(new ServerInitializer(remoteHost, remotePort))
	             .bind(serverPort).sync().channel().closeFuture().sync();
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }

	    public static void main(String[] args) throws Exception {
	        new Server().run();
	    }
	}
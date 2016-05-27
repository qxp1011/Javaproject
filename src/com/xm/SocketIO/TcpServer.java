package com.xm.SocketIO;

/**
 * Created by XMqxp on 2016/5/27.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.example.socksproxy.SocksServerInitializer;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;


public class TcpServer {

    public TcpServer() {

    }

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8888;


    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;

    protected static final int BIZTHREADSIZE = 4;

    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);

    private static final EventLoopGroup workGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    protected static void run() throws Exception {
        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup, workGroup);

        b.channel(NioServerSocketChannel.class);

        b.childHandler(new ChannelInitializer<SocketChannel>() {

                           @Override
                           protected void initChannel(SocketChannel socketChannel) throws Exception {
                               ChannelPipeline pipeline = socketChannel.pipeline();
                             //  pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            //   pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                             //  pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                             //  pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                               pipeline.addLast(new TcpServerHandler());
                           }
                       }
        );

        b.bind(IP, PORT).sync();

    }


    protected static void shutdown() {
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }


    public static void main(String[] args) throws Exception {


        try {
            TcpServer.run();
        } finally {
          //  TcpServer.shutdown();
        }


/*
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            ((ServerBootstrap)((ServerBootstrap)b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)).handler(new LoggingHandler(LogLevel.INFO))).childHandler(new SocksServerInitializer());
            b.bind(PORT).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    */
    }

}
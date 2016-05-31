package com.xm.SocketIO;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by XMqxp on 2016/5/31.
 */
public class TcpClient {


    public  TcpClient()
    {

    }



    private  void connect(String host,int port) throws Exception
    {
        EventLoopGroup workgroup=new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();

        try {

            b.group(workgroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                          @Override
                          protected void initChannel(SocketChannel ch) throws Exception {

                              ch.pipeline().addLast(new TcpClientHandler());
                          }
                      }
            );

            ChannelFuture f = b.connect(host, port).sync();

            f.channel().closeFuture().sync();
        }
        finally {
            workgroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws  Exception
    {
        TcpClient tcpClient=new TcpClient();
        tcpClient.connect("127.0.0.1",8888);
    }



}

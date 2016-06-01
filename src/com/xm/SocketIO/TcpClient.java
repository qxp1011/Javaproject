package com.xm.SocketIO;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
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
        //工作任务
        EventLoopGroup workgroup=new NioEventLoopGroup();
        //启动对象
        Bootstrap b=new Bootstrap();

        try {

            //注册工作任务
            b.group(workgroup);
            //注册通道类型
            b.channel(NioSocketChannel.class);
            //注册通道类型
            b.option(ChannelOption.SO_KEEPALIVE, true);
            //注册Handler
            b.handler(new ChannelInitializer<SocketChannel>() {
                          @Override
                          protected void initChannel(SocketChannel ch) throws Exception {

                              ch.pipeline().addLast(new TcpClientHandler());
                          }
                      }
            );

            //启动TCP Client
            ChannelFuture f = b.connect(host, port).sync();

            //发送数据测试
            String data="ABCDEFGH";

            //构造回送缓冲区
            ByteBuf sendBuf=f.channel().alloc().buffer(4 * data.length());

            //填写字符串内容到回送缓冲区
            sendBuf.writeBytes(data.getBytes());
            f.channel().writeAndFlush(sendBuf);

            //设置退出方式
            f.channel().closeFuture().sync();




        }
        finally {

            //正常退出
            workgroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws  Exception
    {
        TcpClient tcpClient=new TcpClient();
        tcpClient.connect("127.0.0.1",8888);
    }

}

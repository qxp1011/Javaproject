package com.xm.SocketIO;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.nio.NioDatagramChannel;


/**
 * Created by qxp91 on 2017/7/12.
 */
public class UdpServer {






    //Server IP final意为只读
    private static  String IP = "127.0.0.1";
    //Server PORT
    private static  int PORT = 9999;

    private static final    EventLoopGroup Udpgroup = new NioEventLoopGroup();


    protected static void run() throws Exception {

        try {

            //启动对象
            /*
            Bootstrap b = new Bootstrap();
            b.group(Udpgroup).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new UDPServerHandler());
*/

            Bootstrap b = new Bootstrap();//udp不能使用ServerBootstrap
            b.group(Udpgroup).channel(NioDatagramChannel.class)//设置UDP通道
                    .handler(new UdpServerInitializer())//初始化处理器
                    .option(ChannelOption.SO_BROADCAST, true)// 支持广播
                  //  .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024)// 设置UDP读缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024);// 设置UDP写缓冲区为1M


            //启动UDP Server
            ChannelFuture f = b.bind(IP, PORT).sync();
            //设置关闭模式
            f.channel().closeFuture().sync();
        }
        finally {
            //正常关闭
            shutdown();
        }

    }


    protected static void shutdown() {

        if(Udpgroup!=null)
        {
            Udpgroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {

        try {

            if(args.length>1 && args[0]!=null && args[1]!=null)
            {
                IP=args[0];
                PORT=Integer.parseInt(args[1]);
            }


            UdpServer.run();

        } catch(Exception ex) {

            throw new Exception(ex);
        }

    }
















    ////////////////////////////////////////////////////////////////////////////////

/*
    //TCP Server IP final意为只读
    private static  String IP = "127.0.0.1";
    //TCP Server PORT
    private static  int PORT = 9999;

    /**
     * udp服务端，接受客户端发送的广播
     */
    /*
    public static void initServer() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UDPServerHandler());
            Channel channel = bootstrap.bind(IP,PORT ).sync().channel();
            channel.closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
*/
    /*
    private static class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            // 因为Netty对UDP进行了封装，所以接收到的是DatagramPacket对象。
            String req = msg.content().toString(CharsetUtil.UTF_8);
            System.out.println(req);

            if ("hello!!!".equals(req)) {
                ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
                        "结果：", CharsetUtil.UTF_8), msg.sender()));
            }
        }
    }
    */
}


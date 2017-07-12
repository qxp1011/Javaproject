package com.xm.SocketIO;

/**
 * Created by XMqxp on 2016/5/27.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.example.socksproxy.SocksServerInitializer;
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

    //TCP Server IP final意为只读
    //private static final String IP = "127.0.0.1";
    //TCP Server PORT
    //private static final int PORT = 8888;


    //TCP Server IP final意为只读
    private static  String IP = "127.0.0.1";
    //TCP Server PORT
    private static  int PORT = 8888;

    //bossGroup任务个数
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;

    //workGroup任务个数
    protected static final int BIZTHREADSIZE = 4;

    //创建BossGroup
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);

    //创建WorkGroup
    private static final EventLoopGroup workGroup = new NioEventLoopGroup(BIZTHREADSIZE);


    protected static void run() throws Exception {

        try {

            //启动对象
            ServerBootstrap b = new ServerBootstrap();

        //    Bootstrap b = new Bootstrap();

//            http://stackoverflow.com/questions/28331809/netty-bootstrap-with-boss-group-or-with-just-with-workers-eventloopgroup
          //  The book and examples indicates that we should use the so-called boss group and the worker group when bootstraping the server:
           // serverBootstrap.group(bossGroup, workerGroup);
          //  And then, in the Vert.x that is based on Netty we have:
           // bootstrap.group(availableWorkers);
          //  which mean (afaiu) that all workers is going to work the same, so no bosses to handle just the incoming connections.
          //  Why is that?
            //Most of the times using the same group for accepting and handling the
            // accepted connections is working out quite well and so allows you to save
            // some threads. The only time when you may not want to do this is if the
            // handling logic of the accepted connections will keep the EventLoops so
            // busy that you are not able to accept fast enough. So best is to just
            // use the same group when you start and switch to two if you need it.
            //注册工作任务
            b.group(bossGroup, workGroup);

            //注册通道类型，NioServerSocketChannel指定为TCP Sever  NioDatagramChannel指定为UDP
            b.channel(NioServerSocketChannel.class);


            //注册Channel
            b.childHandler(new ChannelInitializer<SocketChannel>() {

                               @Override
                               protected void initChannel(SocketChannel socketChannel) throws Exception {
                                   ChannelPipeline pipeline = socketChannel.pipeline();
                                   //  pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                   //   pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                                   //  pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                                   //  pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                                  // pipeline.addLast(new TcpServerHandler());
                                   pipeline.addLast(new TcpServerHandler());
                               }
                           }
            );

            //启动TCP Server
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

        if(workGroup!=null) {
            workGroup.shutdownGracefully();
        }

        if(bossGroup!=null) {
            bossGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {

        try {

            if(args.length>1 && args[0]!=null && args[1]!=null)
            {
                IP=args[0];
                PORT=Integer.parseInt(args[1]);
            }


            TcpServer.run();

        } catch(Exception ex) {

            throw new Exception(ex);
        }

    }

}
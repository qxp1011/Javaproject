package com.xm.SocketIO;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by qxp on 2016/5/31.
 */
public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    public UDPServerHandler()
    {

    }

    private  volatile int  icount=0;

    private void MsgProcess(ChannelHandlerContext ctx, DatagramPacket datagramPacket) throws Exception {

        //得到发送方的IP
        String senderIP=datagramPacket.sender().getHostString();
        //得到发送方的端口
        int senderPORT=datagramPacket.sender().getPort();

        //得到发送内容的ByteBuf
        ByteBuf byteBuf=datagramPacket.copy().content();

        //创建接收内容的缓冲区
        byte [] bytes=new byte[byteBuf.readableBytes()];

        //获取接收内容
        byteBuf.readBytes(bytes);

        //将接收内容转换成String
        String s=new String(bytes,"ASCII");

        System.out.println("UDP Client Receive:"+icount++ +"  Len:"+ bytes.length+"  Msg:"+s+"\n");

        //释放接收的ByteBuf
        byteBuf.release();

        //构造回送字符串
       String response="UDP Client Send Msg:"+icount+" "+s;

        //构造回送对象
        DatagramPacket sendpack=new DatagramPacket(Unpooled.copiedBuffer(response.getBytes(Charset.forName("UTF-8")))
                ,new InetSocketAddress(senderIP,senderPORT) );

        //回送数据给发送方
        //ctx.channel().writeAndFlush(sendpack);
        ctx.writeAndFlush(sendpack);

    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket datagramPacket) throws Exception {
        MsgProcess(ctx,datagramPacket);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        super.channelRegistered(ctx);

    }



}

package com.xm.SocketIO;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by XMqxp on 2016/5/31.
 */
public class TcpClientHandler  extends ChannelInboundHandlerAdapter{

    //接收计数
    int icount=0;

    private void MsgProcess(ChannelHandlerContext ctx,ByteBuf byteBuf) throws Exception {

        //接收缓冲区
        byte [] bytes=new byte[byteBuf.readableBytes()];

        //将数据放入接收缓冲区
        byteBuf.readBytes(bytes);

        //构造打印字符串
        String s=new String(bytes,"ASCII");

        System.out.println("Client Receive:"+icount++ +"  Len:"+ bytes.length+"  Msg:"+s+"\n");

        //通知下一个ChannelHanler处理消息
        //ctx.fireChannelRead(byteBuf);

        //释放接收缓冲区
        byteBuf.release();

        //构造回送字符串
        String response="Client Send Msg:"+icount+" "+s;

        //构造回送缓冲区
        ByteBuf sendBuf=ctx.alloc().buffer(4 * response.length());

        //填写字符串内容到回送缓冲区
        sendBuf.writeBytes(response.getBytes());

        //发送数据
        ctx.writeAndFlush(sendBuf);

        //通知下一个ChannelHanler处理消息
        //ctx.fireChannelRead(sendBuf);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception
    {
        MsgProcess(ctx,(ByteBuf)msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {

    }

}

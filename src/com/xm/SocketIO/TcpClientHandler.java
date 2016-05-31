package com.xm.SocketIO;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by XMqxp on 2016/5/31.
 */
public class TcpClientHandler  extends ChannelInboundHandlerAdapter{

    int icount=0;

    private void MsgProcess(ChannelHandlerContext ctx,ByteBuf byteBuf) throws Exception {

        // byteBuf.toString();

        byte [] bytes=new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);

        String s=new String(bytes,"ASCII");

        System.out.println("Client Receive:"+icount++ +"  Len:"+ bytes.length+"  Msg:"+s+"\n");

        //通知下一个ChannelHanler处理消息
        //ctx.fireChannelRead(byteBuf);

        byteBuf.release();

        String response="Client Send Msg:"+icount+" "+s;

        ByteBuf sendBuf=ctx.alloc().buffer(4 * response.length());

        sendBuf.writeBytes(response.getBytes());

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

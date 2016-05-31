package com.xm.SocketIO;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import javax.swing.text.html.HTMLDocument;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XMqxp on 2016/5/27.
 */



public class TcpServerHandler extends ChannelInboundHandlerAdapter {


    int icount=0;

    private void MsgProcess(ChannelHandlerContext ctx,ByteBuf byteBuf) throws Exception {

        // byteBuf.toString();

        byte [] bytes=new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);

        String s=new String(bytes,"ASCII");

        System.out.println("Receive:"+icount++ +"  Len:"+ bytes.length+"  Msg:"+s+"\n");

        byteBuf.release();

        String response="Send Msg:"+icount+" "+s;

        ByteBuf sendBuf=ctx.alloc().buffer(4 * response.length());

        sendBuf.writeBytes(response.getBytes());

        ctx.writeAndFlush(sendBuf);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception
    {
        MsgProcess(ctx,(ByteBuf)msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws  Exception
    {
        ctx.flush();
    }


}

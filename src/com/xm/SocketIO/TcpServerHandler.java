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
import java.util.concurrent.Exchanger;

/**
 * Created by XMqxp on 2016/5/27.
 */



public class TcpServerHandler extends ChannelInboundHandlerAdapter {


    //接收包计数器
    int icount=0;

    private void MsgProcess(ChannelHandlerContext ctx,ByteBuf byteBuf) throws Exception {

        try {
            //接收字节缓冲区
            byte[] bytes = new byte[byteBuf.readableBytes()];

            //拷贝缓冲区数据
            byteBuf.readBytes(bytes);

            //构造输出字符串
            String s = new String(bytes, "ASCII");

            System.out.println("Receive:" + icount++ + "  Len:" + bytes.length + "  Msg:" + s + "\n");

            //释放接收缓冲区
            byteBuf.release();

            //构造回送字符串
            String response = "Send Msg:" + icount + " " + s;

            //构造回送缓冲区
            ByteBuf sendBuf = ctx.alloc().buffer(4 * response.length());

            //将数据放入回送缓冲区
            sendBuf.writeBytes(response.getBytes());

            //发送数据
            ctx.writeAndFlush(sendBuf);
        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage() + "\n");
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception
    {
        /*
        try
        {
            MsgProcess(ctx, (ByteBuf) msg);
        }
        catch (Exception ex)
        {
            System.out.println("Exception:" +  ex.getMessage() + "\n");
        }
        */


        MsgProcess(ctx, (ByteBuf) msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws  Exception
    {
        ctx.flush();
    }


}

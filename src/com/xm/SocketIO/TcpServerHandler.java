package com.xm.SocketIO;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XMqxp on 2016/5/27.
 */



public class TcpServerHandler extends ByteToMessageDecoder  {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {

       // byteBuf.toString();

        /*
        String s="";
      for(int i=0;i< byteBuf.readableBytes();i++)
      {
          s+= byteBuf.readChar();

      }

        System.out.println("Receive:"+ s+"\n");
        */
       // System.out.println("Receive:"+ byteBuf.readableBytes()+"\n");
        ctx.writeAndFlush(byteBuf);
        byteBuf.release();
      //  byteBuf.clear();
    }

    /*
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {

        System.out.println("Receive:"+ o.toString()+"\n");
      //  ctx.channel().writeAndFlush("Yes,Server is Accepted You,Nice Job!"+(String)o);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable cause) throws Exception
    {
        channelHandlerContext.close();

    }
    */
}

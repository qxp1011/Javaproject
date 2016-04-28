/**
 * Created by XMqxp on 2016/4/28.
 */

import com.xm.GUI.javagui;
import com.xm.SocketIO.nettyserver;
import com.xm.buffer.javabuffer;
import com.xm.control.javacontrol;
import com.xm.db.javadb;
import com.xm.thread.javathread;

public final class Program {

   // public static boolean b_Exit=true;

    public static void main(String[] args) throws Exception {

        System.out.print("Program Start!\n");


        //定义局部变量
        javagui m_javagui = new javagui();
        javacontrol m_javacontrol = new javacontrol();
        javathread m_javathread = new javathread();
        nettyserver m_nettyserver = new nettyserver();
        javabuffer m_javabuffer = new javabuffer();
        javadb m_javadb = new javadb();

        //调用线程类的内部函数
        m_javathread.Test();

        //启动线程，开始异步操作
        m_javathread.start();

        //等待线程退出标识
        while (javathread.b_Exit) {

            //同时还可以做其它事情
            System.out.print("Program is Running!\n");

            //这里休眠一下
            Thread.sleep(1000);
        }

        //退出进程
        System.out.print("Program Exit!\n");
    }

}

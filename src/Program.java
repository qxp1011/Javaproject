/**
 * Created by XMqxp on 2016/4/28.
 */

import com.xm.GUI.javagui;
import com.xm.SocketIO.nettyserver;
import com.xm.buffer.javabuffer;
import com.xm.control.javacontrol;
import com.xm.db.javadb;
import com.xm.thread.consumerthread;
import com.xm.thread.javathread;
import com.xm.thread.producerthread;
import com.xm.thread.userthread;

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


        userthread UserThread=new userthread();

        Thread pThread=new Thread(new producerthread(UserThread));
        Thread cThread=new Thread(new consumerthread(UserThread));


        pThread.start();
        cThread.start();


        //等这两个线程执行完比再执行后面的代码
        pThread.join();
        cThread.join();


        //调用线程类的内部函数
        m_javathread.Test();

        //启动线程，开始异步操作
        m_javathread.start();



        //等待线程退出标识
        while (javathread.b_Exit) {

            //同时还可以做其它事情
            System.out.print("Program is Running!\n");

            //这里休眠一下的话要出错，
           //Thread.sleep(1000);

            //注：javathread这个线程的运行很有意思，其run函数并不是执行一次就退出，主线程虽然在wait状态，但不保证它能竞争过子线程立即得到执行


            //等线程运行函数退出
            synchronized (javathread.obj) {

                javathread.obj.wait();
            }
        }




         //强制退出线程
        if(m_javathread!=null ) {

           // pThread.interrupt();
            m_javathread.StopNonRunThread();
        }


        //等待线程结束
        m_javathread.join();



        //退出进程
        System.out.print("Program Exit!\n");
    }

}

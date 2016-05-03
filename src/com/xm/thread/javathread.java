package com.xm.thread;

/**
 * Created by XMqxp on 2016/4/28.
 */
public class javathread extends Thread    {

    //全局变量，标识线程退出
    public static boolean b_Exit=true;

    //线程通知事件
    public final  static Object obj=new Object();


    //线程类的内部普通函数
    public void Test(){

        System.out.print("javathread.Test is running!\n");

    }

    //继承Thread类之后必须实现的函数
    public void run()
    {
        try {

            synchronized (obj ) {
                System.out.print("javathread.run is running!\n");

                //休眠10秒
                Thread.sleep(3000);

                //本线程退出了
                b_Exit = false;

                //线程通知
                obj.notifyAll();

                System.out.print("Thread is Exit!\n");

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

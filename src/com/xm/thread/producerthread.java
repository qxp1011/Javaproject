package com.xm.thread;

/**
 * Created by qxp on 2016/5/10.
 */
public class producerthread implements Runnable {


    //生产者和消费者的执行类
    userthread UserThread;


    public producerthread(userthread uThread)
    {
        this.UserThread=uThread;

    }

    //线程函数，生产者循环
    public void run() {

        System.out.println("producer thread is start!\n");


        //执行一定次数的生产者任务
        for(int p=1;p<=10;p++)
        {

            //休眠等待
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //用Lock控制线程同步
            UserThread.setproduct_lock();

            //用sync控制线程同步
            UserThread.setproduct_sync();

            //执行生产者任务，用sync控制线程同步
            UserThread.setproduct(p);



        }



    }
}

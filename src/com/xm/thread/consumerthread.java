package com.xm.thread;

/**
 * Created by qxp on 2016/5/10.
 */
public class consumerthread implements Runnable {


    //生产者和消费者的执行类
    private userthread UserThread;

    public  consumerthread(userthread uThread)
    {

        this.UserThread=uThread;
    }

    //线程函数
    public void run() {

        System.out.println("consumer thread is start!\n");

        //执行一定次数的消费者任务
        for(int p=1;p<=10;p++) {

            //休眠等待
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            UserThread.getproduct_lock();

            UserThread.getproduct_sync();

            //执行消费者任务
            UserThread.getproduct();



        }

    }
}

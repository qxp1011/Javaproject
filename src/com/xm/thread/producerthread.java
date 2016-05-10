package com.xm.thread;

/**
 * Created by qxp on 2016/5/10.
 */
public class producerthread implements Runnable {


    userthread UserThread;

    public producerthread(userthread uThread)
    {
        this.UserThread=uThread;

    }

    public void run() {
        System.out.println("producer thread is start!\n");

        for(int p=1;p<=10;p++)
        {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UserThread.setproduct(p);

        }



    }
}

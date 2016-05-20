package com.xm.thread;

/**
 * Created by qxp on 2016/5/10.
 */
public class consumerthread implements Runnable {


    private userthread UserThread;

    public  consumerthread(userthread uThread)
    {

        this.UserThread=uThread;
    }

    public void run() {

        System.out.println("consumer thread is start!\n");

        for(int p=1;p<=10;p++) {


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            UserThread.getproduct();
        }

    }
}

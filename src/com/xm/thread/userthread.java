package com.xm.thread;

import java.io.PrintStream;




/*
import java.util.concurrent.locks.Lock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
*/

/**
 * Created by qxp on 2016/5/10.
 */
public class userthread {

    private int iproduct = -1;

   // Lock lock=new ReentrantLock();

    public synchronized void setproduct(int ithreadproducter) {

        while  (this.iproduct!=-1) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            this.iproduct = ithreadproducter;

           System.out.println("\n user set product!");
            System.out.println(this.iproduct);


            notify();

    }


    public synchronized int getproduct() {


        while (this.iproduct == -1) {
            try {
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int p = this.iproduct;
        System.out.println("\n user get product!");
        System.out.println(p);

        this.iproduct = -1;
        notify();
        return p;

    }

}

package com.xm.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qxp on 2016/5/10.
 */
public class userthread {

    private int iproduct = -1;
    private int iproduct_sync =0;
    private int iproduct_lock =0;

//多线程生产者消费者同步方法 ，利用方法的synchronized实现
    public synchronized void setproduct(int ithreadproducter) {

        //当变量没有被get时，wait，直到get结束
        while  (this.iproduct !=-1) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //get已经结束，可以开始set
            this.iproduct = ithreadproducter;

           System.out.println(" user set product!"+this.iproduct+"\n");
          //  System.out.println(this.iproduct);

        //set已经结束，通知get可以开始工作
            notify();

    }



    public synchronized int getproduct() {

        //等待set结束
        while (this.iproduct == -1) {
            try {
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //set已经结束，开始执行get
        int p = this.iproduct;
        System.out.println(" user get product!"+p+"\n" );
    //    System.out.println(p);

        this.iproduct = -1;

        //get已经结束，通知set可以开始工作
        notify();
        return p;

    }









    //多线程生产者消费者同步方法 ，利用synchronized锁定对象实现，相当于lock操作
    public  void setproduct_sync()  {

        //相当于lock
        synchronized (this) {
            System.out.println("user set product Sync!\n");

            for(int p=0;p<=5;p++)
            {
                //get已经结束，可以开始set
                this.iproduct_sync++;
                System.out.println(this.iproduct_sync+"\n");
               // System.out.println("\n");
            }
        }
    }


    public  void getproduct_sync() {

        //相当于lock
        synchronized (this) {

            System.out.println("user get product Sync!\n ");

            for(int p=0;p<=5;p++)
            {
                //get已经结束，可以开始set
                this.iproduct_sync--;
                System.out.println(this.iproduct_sync+"\n");
              //  System.out.println("\n");
            }
        }
    }


//注意：synchronized的性能相较于Lock而言，要差好几倍，建议在有性能需求时使用Lock，在对性能要求不是特别高时，还是可以使用synchronized
    //synchronized基于JVM的基本功能，易于调试，但难于掌握，需要一定的编程技巧和经验才能写好
    //Lock是concurrent实现的高级特性，通常建议“高级用户”使用它，包括了wait，notify，notifyAll的特性

    //多线程生产者消费者同步方法 ，使用lock操作
    Lock lock =new ReentrantLock();
    public  void setproduct_lock()  {

        //使用lock
        lock.lock ();

        //lock之后必须在try...finally...当中执行unlock
        try{
            System.out.println("user set product Lock!\n ");

            for(int p=0;p<=3;p++)
            {
                //get已经结束，可以开始set
                this.iproduct_lock++;
                System.out.println(this.iproduct_lock+"\n");
              //  System.out.println("\n");
            }
        }
        finally {
            lock.unlock();
        }
    }


    public  void getproduct_lock() {

        //使用lock
        lock.lock ();

        //lock之后必须在try...finally...当中执行unlock
        try {

            System.out.println("\n user get product Lock!");

            for(int p=0;p<=3;p++)
            {
                //get已经结束，可以开始set
                this.iproduct_lock--;
                System.out.println(this.iproduct_lock+"\n");
              //  System.out.println("\n");
            }
        }
        finally {
            lock.unlock();
        }
    }


}

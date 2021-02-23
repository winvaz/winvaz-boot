package com.icore.winvaz.javase.basic.thread;

import java.util.concurrent.TimeUnit;

/**
 * 1、 线程状态分类
 * 线程一共有六种状态，分别为New、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED，
 * 同一时刻只有一种状态，通过线程的getState方法可以获取线程的状态。
 *
 * 2、状态详解
 * Thread的状态使用java.lang.Thread.State枚举表示。
 * @Author wdq
 * @Create 2019-02-20 15:34
 */
public class ThreadTest extends Thread {
    // 面试
    static String s = "Hello";

    public void convert(String s) {
        s = s + " World!";
        start();
    }

    // Thread构造方法设置线程名字
    /*public ThreadDemo(String name) {
        super(name);
    }*/

    @Override
    public void run() {
        /*for (int i = 0; i < 10; i++) {
            // 获取当前线程的名称
            System.out.println( Thread.currentThread().getName() + "run...." + i);
        }*/
        // 获取线程名称
        //System.out.println(super.getName());
        // 设置线程名字
        //super.setName("Thread-11");
        //System.out.println(super.getName());

        // 面试题
        for (int i = 1; i < 4; i++) {
            s = s + " " + i;
        }
        System.out.println("run()" + s);
    }

    // 测试线程状态
    static String lock = "锁";
    public static void main(String[] args) throws InterruptedException {

        // 面试
        ThreadTest threadTest = new ThreadTest();
        /**
         * NEW
         * 当线程被创建出来还没有被调用start()时候的状态。
         */
        // System.out.println(threadTest.getState()); // NEW
        /**
         * RUNNABLE
         * 当线程被调用了start()，且处于等待操作系统分配资源（如CPU）、等待IO连接、正在运行状态，即表示Running状态和Ready状态。
         * 注：不一定被调用了start()立刻会改变状态，还有一些准备工作，这个时候的状态是不确定的。
         */
        // threadTest.start();
        // System.out.println(threadTest.getState()); //RUNNABLE
        /**
         * BLOCKED
         * 等待监视器锁而被阻塞的线程的线程状态，当进入synchronized块/方法或者在调用wait()被唤醒/超时之后重新进入synchronized块/方法，
         * 但是锁被其它线程占有，这个时候被操作系统挂起，状态为阻塞状态。
         *
         * 阻塞状态的线程，即使调用interrupt()方法也不会改变其状态。
         *
         * 下面看案例代码，thread1持有lock对象的锁一直没有释放，而thread2也想获取lock对象的锁，
         * 但是锁一直被thread1持有者，导致thread2被阻塞在@1处，此时thread2
         * 的状态就是BLOCKED状态。
         */
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                synchronized (lock) {
                    // 死循环导致thread1一直持有lock对象锁
                    while (true) ;
                }
            }
        };
        thread1.start();

        // 休眠1秒，让thread1先启动
        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread("thread2") {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("thread2");
                }
            }
        };
        thread2.start();

        System.out.println("thread1.state:" + thread1.getState()); // RUNABLE
        System.out.println("thread2.state:" + thread2.getState()); // BLOCKED
        /*
        threadTest.convert(" World!");
        System.out.println("main()" + s);

        ThreadTest threadTest1 = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();

        // 调用start()
        threadTest1.start();
        //  如果要将用户线程设置为守护进程，则不能启动它，否则将抛出IllegalThreadStateException。
        threadTest1.setDaemon(true);
        threadTest2.start();
        */
        // 调用run()
        //threadDemo1.run();
        //threadDemo2.run();
        /*while (!Thread.currentThread().isInterrupted()) {
            System.out.println(true);
        }*/
    }
}
package com.icore.winvaz.javase.basic.thread;

/**
 * 多线程死锁案例
 *
 * @Author wdq
 * @Create 2019-02-22 12:47
 */
public class ThreadDead implements Runnable {

    private Boolean flag;

    public ThreadDead(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            // 如果flag成立，让线程进入A同步代码块，再进入B同步代码块
            if (flag) {
                synchronized (LockA.locka) {
                    System.out.println("if...locka");
                    synchronized (LockB.lockb) {
                        System.out.println("if...lockb");
                    }
                }
            } else {
                // 如果flag成立，让线程进入B同步代码块，再进入A同步代码块
                synchronized (LockB.lockb) {
                    System.out.println("else...lockb");
                    synchronized (LockA.locka) {
                        System.out.println("else...locka");
                    }
                }
            }
        }
    }
}

// 定义两把锁，A锁，B锁，唯一对象
class LockA {
    public static final LockA locka = new LockA();
}

class LockB {
    public static final LockB lockb = new LockB();
}

class ThredDeadTest {

    public static void main(String[] args) {
        // 进入A同步代码块
        ThreadDead threadDead1 = new ThreadDead(true);
        // 进入B同步代码块
        ThreadDead threadDead2 = new ThreadDead(false);

        Thread thread1 = new Thread(threadDead1);
        Thread thread2 = new Thread(threadDead2);

        thread1.start();
        thread2.start();
    }
}
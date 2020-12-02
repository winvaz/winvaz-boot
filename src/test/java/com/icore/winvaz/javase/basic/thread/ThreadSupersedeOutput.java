package com.icore.winvaz.javase.basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Deciption 多线程交替输出1~100
 * 线程1执行-->等待-->唤醒线程2执行-->等待-->唤醒线程3执行-->唤醒线程1执行。
 * @Author wdq
 * @Create 2019-09-12 20:48
 */
public class ThreadSupersedeOutput {
    // 定义一个计数变量
    private static int i = 0;

    public static void main(String[] args) {
        /**
         * 多线程交替打印数据
         * 线程1执行-->等待-->唤醒线程2执行-->等待-->唤醒线程3执行-->等待-->唤醒线程1执行
         */
        // 线程锁
        Lock lock = new ReentrantLock();

        // 定义三个线程
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 线程1
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 上锁
                    lock.lock();
                    try {
                        condition1.await();
                        if (i <= 100) {
                            System.out.println(Thread.currentThread().getName() + "===" + i);
                            i++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 唤醒线程2
                        condition2.signal();
                        // 释放锁
                        lock.unlock();
                    }
                }
            }
        });

        // 线程2
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 上锁
                    lock.lock();
                    try {
                        condition2.await();
                        if (i <= 100) {
                            System.out.println(Thread.currentThread().getName() + "===" + i);
                            i++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 唤醒线程3
                        condition3.signal();
                        // 释放锁
                        lock.unlock();
                    }
                }
            }
        });

        // 线程3
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 上锁
                    lock.lock();
                    try {
                        condition3.await();
                        if (i <= 100) {
                            System.out.println(Thread.currentThread().getName() + "===" + i);
                            i++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 唤醒线程1
                        condition1.signal();
                        // 释放锁
                        lock.unlock();
                    }
                }
            }
        });

        // 单独启动线程
        new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    condition1.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();
    }
}
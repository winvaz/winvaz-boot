package com.icore.winvaz.javase.basic.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程通讯：多生产多消费案例改造，使用Lock
 *  * 定义产品对象---生产，消费
 *  * 生产者线程控制生产
 *  * 消费者线程控制消费
 * @Author wdq
 * @Create 2019-02-22 17:43
 */
public class LockProductionConsumption {

    public static void main(String[] args) {
        Product product = new Product();
        Pro producter = new Pro(product);
        Cus customer = new Cus(product);
        Thread thread1 = new Thread(producter);

        Thread thread2 = new Thread(producter);
        Thread thread3 = new Thread(producter);
        Thread thread4 = new Thread(producter);
        Thread thread5 = new Thread(customer);

        Thread thread6 = new Thread(customer);
        Thread thread7 = new Thread(customer);
        Thread thread8 = new Thread(customer);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

    }
}

// 定义产品对象
class Product {
    private String name;
    private int count;
    private boolean flag = false;

    // 获取Lock接口的实现类对象
    private Lock lock = new ReentrantLock();
    private Condition pro = lock.newCondition();
    private Condition cus = lock.newCondition();

    // 生产方法
    public void set(String name) {
        // 获取锁
        lock.lock();
        // this.name是成员变量，是名称家计数器
        while (flag) {
            try {
                pro.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + "..." + count++;
        System.out.println(Thread.currentThread().getName() + "...生产第..." + this.name + "...产品");
        // 修改标记
        flag = true;
        // 唤醒消费
        cus.signal();
        // 释放锁
        lock.unlock();
    }

    // 消费方法
    public void get() {
        lock.lock();
        while (!flag) {
            try {
                cus.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "...........消费第..........." + this.name + "...........产品");
        // 修改标记
        flag = false;
        // 唤醒生产
        pro.signal();
        lock.unlock();
    }
}

class Pro implements Runnable{

    private Product product;

    public Pro(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.set("黄金");
        }
    }
}

// 定义消费线程
class Cus implements Runnable {
    private Product product;

    public Cus(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.get();
        }
    }
}


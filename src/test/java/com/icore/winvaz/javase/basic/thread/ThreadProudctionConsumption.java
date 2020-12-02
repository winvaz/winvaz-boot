package com.icore.winvaz.javase.basic.thread;

/**
 * 多线程通讯：多生产多消费
 * 定义产品对象---生产，消费
 * 生产者线程控制生产
 * 消费者线程控制消费
 * @Create 2019-02-22 16:15
 */
public class ThreadProudctionConsumption {

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
class Production {
    private String name;
    private int count;
    private boolean flag = false;

    // 生产方法
    public synchronized void set(String name) {
        // this.name是成员变量，是名称家计数器
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + "..." + count++;
        System.out.println(Thread.currentThread().getName() + "...生产第..." + this.name + "...产品");
        // 修改标记
        flag = true;
        // 唤醒消费
        this.notifyAll();
    }

    // 消费方法
    public synchronized void get() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "...........消费第..........." + this.name + "...........产品");
        // 修改标记
        flag = false;
        // 唤醒生产
        this.notifyAll();
    }
}

// 定义生产线程
class Producter implements Runnable{

    private Product product;

    public Producter(Product product) {
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
class Cusumption implements Runnable {
    private Product product;

    public Cusumption(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.get();
        }
    }
}

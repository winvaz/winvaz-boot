package com.icore.winvaz.javase.basic.thread;

/**
 * 模拟储户到银行存钱
 *
 * @Author wdq
 * @Create 2019-02-21 16:44
 */
public class RunableTest {
    // 定义余额
    private static int sum = 0;

    //private Object object = new Object();
    // 存钱方法
    /*public void add(int money) {
        synchronized (object) {
            sum = sum + money;
            System.out.println("sum=" + sum);
        }
    }*/
    // 静态同步方法的锁为本类的class文件对象
    public static void add(int money) {
        synchronized (RunableTest.class) {
            sum = sum + money;
            System.out.println("sum=" + sum);
        }
    }
    // 同步方法
    /*public static synchronized void add(int money) {
        sum = sum + money;
        System.out.println("sum=" + sum);
    }*/
    /*public void add(int money) {
        synchronized (this) {
            sum = sum + money;
            System.out.println("sum=" + sum);
        }
    }*/

    // 定义储户
    class Customer implements Runnable {
        // 共享数据
        private RunableTest runableTest = new RunableTest();

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                runableTest.add(100);
            }
        }
    }

    /**
     * 模拟火车站售票
     */
    class Ticket implements Runnable {
        // 定义100张票
        private int ticket = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // 线程停止。一、改变循环变量
                            System.out.println("线程被打了。");
                            ticket = 0;
                            e.printStackTrace();
                        }
                        // 线程的让步，让别的线程执行
                        // Thread.yield();
                        System.out.println(Thread.currentThread().getName() + ", 出售第" + ticket-- + "张票");
                    }
                }
            }
        }
    }

    // 测试
    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        /*
        // 模拟储户存钱
        Customer customer = new RunableTest().new Customer();
        Thread thread1 = new Thread(customer);
        Thread thread2 = new Thread(customer);
        // 开启线程
        thread1.start();
        thread2.start();
        */
        // 模拟售票
        // 创建Thread对象，在构造方法中传递Runable接口实现类的对象
        Ticket ticket = new RunableTest().new Ticket();
        // 开启线程
        // new Thread(ticket).start();
        // new Thread(ticket).start();
        Thread thread0 = new Thread(ticket);
        Thread thread1 = new Thread(ticket);
        // thread0.setPriority(Thread.MAX_PRIORITY);
        // 守护线程
        // Thread类的方法 void setDaemon(boolean )传递的是true，将该线程标记为守护线程
        // thread0.setDaemon(true);
        thread0.start();
        /**
         * join方法，yield方法
         *   join方法，等待该线程终止
         *   t0线程，t1线程，main线程，t0调用join方法
         *   t0先执行完毕，t1 main进行CPU的资源争夺
         *
         *   static yield方法，线程的让步，线程把CPU的执行权礼让出去
         *   写在执行的线程中就可以了，不需要对象调用
         */
        // thread0.join();
        thread1.start();
        // 线程停止。二、interrupt()方法
        // thread0.interrupt();
        /**
         * Thread类的toString()方法，优先级
         *   toString(Thread-0, 5, main)方法，名字，优先级，线程组
         *   优先级，设置的优先级三个级别 最低1，默认5，最高10
         *   Thread方法 void setPriority(int )设置优先级
         */
        // System.out.println(thread0.toString()); // Thread[Thread-4,5,main]
    }
}
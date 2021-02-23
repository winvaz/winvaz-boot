package com.icore.winvaz.javase.basic.thread;

/**
 * @Deciption 线程顺序执行
 * 使用了7中方法实现在多线程中让线程按顺序运行的方法，涉及到多线程中许多常用的方法，
 * 不止为了知道如何让线程按顺序运行，更是让读者对多线程的使用有更深刻的了解。使用的方法如下:
 * [1] 使用线程的join方法
 * [2] 使用主线程的join方法
 * [3] 使用线程的wait方法
 * [4] 使用线程的线程池方法
 * [5] 使用线程的Condition(条件变量)方法
 * [6] 使用线程的CountDownLatch(倒计数)方法
 * [7] 使用线程的CyclicBarrier(回环栅栏)方法
 * [8] 使用线程的Semaphore(信号量)方法
 * @Author wdq
 * @Create 2021/2/22 11:47
 * @Version 1.0.0
 */
public class ThreadOrderExecute {
    /**
     * 我们下面需要完成这样一个应用场景：
     * 1.早上；2.测试人员、产品经理、开发人员陆续的来公司上班；3.产品经理规划新需求；
     * 4.开发人员开发新需求功能；5.测试人员测试新功能。
     * <p>
     * 规划需求，开发需求新功能，测试新功能是一个有顺序的，
     * 我们把thread1看做产品经理，thread2看做开发人员，thread3看做测试人员。
     */

    /**
     * 1.使用线程的join方法
     * join():是Theard的方法，作用是调用线程需等待该join()线程执行完成后，才能继续用下运行。
     * 应用场景：当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
     */
    /*
    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> System.out.println("产品经理规划新需求"));

        final Thread thread2 = new Thread(() -> {
            try {
                // 等待线程1执行
                thread1.join();
                System.out.println("开发人员开发新需求功能");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                // 等待线程2执行
                thread2.join();
                System.out.println("测试人员测试新功能");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("产品经理来上班了...");
        thread1.start();
        System.out.println("开发人员来上班了...");
        thread2.start();
    }
    */

    /**
     * 2.使用主线程的join方法
     * 这里是在主线程中使用join()来实现对线程的阻塞。
     */
    /*
    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(() -> System.out.println("产品经理正在规划新需求..."));

        final Thread thread2 = new Thread(() -> {
            System.out.println("开发人员开发新需求功能");
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("测试人员测试新功能");
        });

        System.out.println("早上：");
        System.out.println("产品经理来上班了...");
        System.out.println("开发人员来上班了...");
        System.out.println("测试人员来上班了...");
        thread1.start();
        // 在父进程调用子进程的join方法后，父进程需要等待子进程运行完成再继续运行
        System.out.println("开发人员和测试人员休息会...");
        thread1.join();
        System.out.println("产品经理新需求规划完成!");
        thread2.start();
        System.out.println("测试人员休息会...");
        thread2.join();
        thread3.start();
    }
    */

    /**
     *3.使用线程的wait方法
     * wait():是Object的方法，作用是让当前线程进入等待状态，同时，
     * wait()也会让当前线程释放它所持有的锁。“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，当前线程被唤醒 (进入“就绪状态”)
     *
     * notify()和notifyAll():是Object的方法，作用则是唤醒当前对象上的等待线程；notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。
     * wait(long timeout):让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的notify()方法或 notifyAll() 方法，或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
     *
     * 应用场景：Java实现生产者消费者的方式。
     */
    private static Object lockA = new Object();
    private static Object lockB = new Object();
    // 为什么要加这两个标识状态？
    // 如果没有标识状态，当t1已经运行完了t2才运行，t2在等待t1唤醒导致t2永远处于等待状态
    private static Boolean t1Flag = false;
    private static Boolean t2Flag = false;
    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("产品经理规划新需求...");
                t1Flag = true;
                lockA.notify();
            }
        });

        final Thread thread2 = new Thread(() -> {
            synchronized (lockA) {
                try {
                    if (!t1Flag) {
                        System.out.println("开发人员先休息会...");
                        lockA.wait();
                    }
                    synchronized (lockB) {
                        System.out.println("开发人员开发新需求功能");
                        lockB.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (lockB) {
                try {
                    if (!t2Flag) {
                        System.out.println("测试人员休息会...");
                        lockB.wait();
                    }
                    System.out.println("测试人员测试新功能");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("产品经理来上班了...");
        thread1.start();
        System.out.println("开发人员来上班了...");
        thread2.start();
    }
}

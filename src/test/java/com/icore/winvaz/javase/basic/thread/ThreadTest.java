package com.icore.winvaz.javase.basic.thread;

/**
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

    public static void main(String[] args) {

        // 面试
        ThreadTest threadDemo = new ThreadTest();
        threadDemo.convert(" World!");
        System.out.println("main()" + s);

        ThreadTest threadTest1 = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();

        // 调用start()
        threadTest1.start();
        //  如果要将用户线程设置为守护进程，则不能启动它，否则将抛出IllegalThreadStateException。
        threadTest1.setDaemon(true);
        threadTest2.start();

        // 调用run()
        //threadDemo1.run();
        //threadDemo2.run();
        /*while (!Thread.currentThread().isInterrupted()) {
            System.out.println(true);
        }*/
    }
}
package com.icore.winvaz.javase.basic.thread;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/6/2 16:11
 * @Version 1.0.0
 */
public class ThreadPoolExecutorTest {
    // 定义线程池参数
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        // 通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 10; i++) {
            // 创建线程类
            ThreadPoolExecutorRunnableTest runableTest = new ThreadPoolExecutorRunnableTest("" + i);
            // 执行
            executor.execute(runableTest);
        }
        // 终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("Finished all threads");
    }
}

/**
 * 线程类，5秒钟执行任务
 */
class ThreadPoolExecutorRunnableTest implements Runnable {

    private String command;

    public ThreadPoolExecutorRunnableTest(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start Time = " + LocalDateTime.now());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " Stop Time = " + LocalDateTime.now());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
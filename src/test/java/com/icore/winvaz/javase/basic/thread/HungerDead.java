package com.icore.winvaz.javase.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Deciption 饥饿死锁
 * @Author wdq
 * @Create 2020/9/17 10:47
 * @Version 1.0.0
 */
public class HungerDead {

    private static ExecutorService single = Executors.newSingleThreadExecutor();

    public static class AnotherCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("in AnotherCallable");
            return "annother success";
        }
    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("in MyCallable");
            Future<String> submit = single.submit(new AnotherCallable());
            return "success:" + submit.get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable task = new MyCallable();
        Future<String> submit = single.submit(task);
        System.out.println(submit.get());
        System.out.println("over");
        single.shutdown();
    }

    // 趋势图

}

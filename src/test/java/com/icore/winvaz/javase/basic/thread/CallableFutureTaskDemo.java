package com.icore.winvaz.javase.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author wdq
 * @Create 2019-10-11 10:16
 */
public class CallableFutureTaskDemo implements Callable {
    @Override
    public Object call() throws Exception {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count += i;
        }
        return count;
    }

    public static void main(String[] args) {
        // 创建Callable接口的实现类对象
        CallableFutureTaskDemo callableFutureTaskDemo = new CallableFutureTaskDemo();
        // 使用FutureTask来包装Callable对象
        FutureTask futureTask = new FutureTask(callableFutureTaskDemo);
        // 使用FutureTask对象作为Thread对象的target创建并启动新线程。
        new Thread(futureTask).start();
        // 调用 FutureTask 对象的 get() 方法来获得子线程执行结束后的返回值。
        Object o = null;
        try {
            o = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(o);
    }
}

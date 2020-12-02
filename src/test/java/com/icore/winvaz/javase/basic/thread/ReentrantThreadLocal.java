package com.icore.winvaz.javase.basic.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 若一个程序或子程序可以“在任意时刻被中断然后操作系统调度执行另外一段代码，这段代码又调用了该子程序不会出错”，则称其为可重入（reentrant或re-entrant）的。
 * 即当该子程序正在运行时，执行线程可以再次进入并执行它，仍然获得符合设计时预期的结果。
 * 与多线程并发执行的线程安全不同，可重入强调对单个线程执行时重新进入同一个子程序仍然是安全的。
 *
 * @Deciption 基于ThreadLocal实现可重入锁
 * @Author wdq
 * @Create 2020/6/16 22:54
 * @Version 1.0.0
 */
public class ReentrantThreadLocal {
    // Java中ThreadLocal可以使每个线程拥有自己的实例副本，可以利用这个特性对线程重入次数计数。
    // 每个线程都可以通过 ThreadLocal获取自己的  Map实例，Map 中 key 存储锁的名称，而 value存储锁的重入次数。
    private static ThreadLocal<Map<String, Integer>> LOCKS = ThreadLocal.withInitial(HashMap::new);

    /**
     * @param lockName  锁名字，代表需要争临界资源
     * @param request   唯一标识，可以使用UUID，根据该值判断是否可以重入
     * @param leaseTime 锁释放时间
     * @param timeUnit  锁释放时间单位
     * @throws
     * @Description 可重入锁
     * @author wdq
     * @create 2020/6/16 23:01
     * @Return boolean
     */
    public boolean tryLock(String lockName, String request, long leaseTime, TimeUnit timeUnit) {
        Map<String, Integer> stringIntegerMap = LOCKS.get();
        if (stringIntegerMap.containsKey(lockName)) {
            stringIntegerMap.put(lockName, stringIntegerMap.get(lockName) + 1);
            return true;
        } else {

        }
        return false;
    }

    /**
     * 当一个线程执行一段代码成功获取锁之后，继续执行时，又遇到加锁的代码，可重入性就就保证线程能继续执行，
     * 而不可重入就是需要等待锁释放之后，再次获取锁成功，才能继续往下执行。
     */
    /**
     * 假设 X 线程在 a 方法获取锁之后，继续执行 b 方法，如果此时不可重入，线程就必须等待锁释放，再次争抢锁。
     * 锁明明是被 X 线程拥有，却还需要等待自己释放锁，然后再去抢锁，这看起来就很奇怪，我释放我自己~
     * <p>
     * 可重入性就可以解决这个尴尬的问题，当线程拥有锁之后，往后再遇到加锁方法，直接将加锁次数加 1，然后再执行方法逻辑。
     * 退出加锁方法之后，加锁次数再减 1，当加锁次数为 0 时，锁才被真正的释放。
     * 可以看到可重入锁最大特性就是计数，计算加锁的次数。所以当可重入锁需要在分布式环境实现时，我们也就需要统计加锁次数。
     * 分布式可重入锁实现方式有两种：
     * 基于 ThreadLocal 实现方案
     * 基于 Redis Hash 实现方案
     */
    public synchronized void methodA() {
        System.out.println("同步方法methodA()...");
        methodB();
    }

    private synchronized void methodB() {
        System.out.println("同步方法methodB()...");
    }
}

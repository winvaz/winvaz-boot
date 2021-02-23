package com.icore.winvaz.javase.basic.thread;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
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

        // 1.8添加 创建一个抢占式线程池(任务执行顺序不确定)
        // Executors.newWorkStealingPool();

        /**
         * ThreadPoolExecutor
         * 最原始的创建线程池的方式，它包含了 7 个参数可供设置。
         */
        /*
        // 通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, // corePoolSize 核心线程数，线程池中始终存活的线程数
                MAX_POOL_SIZE, // maximumPoolSize 最大线程数，线程池中允许的最大线程数，当线程池的任务队列满了之后可以创建的最大线程数
                KEEP_ALIVE_TIME, // keepAliveTime 最大线程数可以存活的时间，当线程中没有任务执行时，最大线程就会销毁一部分，最终保持核心线程数量的线程。
                TimeUnit.SECONDS, // unit 单位是和参数keepAliveTime存活时间配合使用的，合在一起用于设定线程的存活时间，keepAliveTime 的时间单位有以下 7 种可选：
                // TimeUnit.DAYS：天
                // TimeUnit.HOURS：小时
                // TimeUnit.MINUTES：分
                // TimeUnit.SECONDS：秒
                // TimeUnit.MILLISECONDS：毫秒
                // TimeUnit.MICROSECONDS：微妙
                // TimeUnit.NANOSECONDS：纳秒
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),  // workQueue 一个阻塞队列，用来存储线程池等待执行的任务，均为线程安全
                // ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
                // LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
                // SynchronousQueue：一个不存储元素的阻塞队列，即直接提交给线程不保持它们。
                // PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
                // DelayQueue：一个使用优先级队列实现的无界阻塞队列，只有在延迟期满时才能从中提取元素。
                // LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。与SynchronousQueue类似，还含有非阻塞方法。
                // LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
                // 较常用的是 LinkedBlockingQueue 和 Synchronous，线程池的排队策略与 BlockingQueue 有关。
                new CustomizableThreadFactory("springThread-pool-"),// threadFactory 线程工厂，主要用来创建线程，默认为正常优先级，非守护线程。
                // ThreadFactory 设置线程名称的三种方式
                //  第一种 Spring框架提供的CustomizableThreadFactory
                // 第二种 Google guava工具类提供的ThreadFactoryBuilder，使用链式方式创建
                // 第三种 Apache commons-lang3提供的BasicThreadFactory
                new ThreadPoolExecutor.CallerRunsPolicy() // handler 拒绝策略，拒绝处理任务时的策略，系统提供了4种可选：
                // AbortPolicy：拒绝并抛出异常。
                // CallerRunsPolicy：使用当前调用的线程来执行此任务。
                // DiscardOldestPolicy：抛弃队列头部（最旧）的一个任务，并执行当前任务。
                // DiscardPolicy：忽略并抛弃当前任务。
                // 默认策略为 AbortPolicy。
        );
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            // 创建线程类
            // ThreadPoolExecutorRunnableTest runableTest = new ThreadPoolExecutorRunnableTest("" + i);
            // 执行
            executor.execute(() -> {
                System.out.println(index + " 被执行，线程名:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        */
        // 终止线程池
        /*executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("Finished all threads");*/

        // 测试延迟任务线程池
        // scheduledThreadPool();
        // singleThreadScheduledExecutor();

        /**
         * 测试ThreadPoolExecutor 的拒绝策略的触发，我们使用 DiscardPolicy  的拒绝策略，它会忽略并抛弃当前任务的策略
         */
        // 任务的具体方法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前任务被执行，执行时间:" + LocalDateTime.now() + " 执行线程:" + Thread.currentThread().getName());
                // 等待1s
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 创建线程，线程的任务队列的长度为1
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardPolicy());*/

        // 自定义拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                (r, executor) -> {
                    // 执行自定义拒绝策略的相关操作
                    System.out.println("我是自定义拒绝策略");
                });

        // 添加并执行4个任务
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
    }

    /**
     * 线程池（ThreadPool）是一种基于池化思想管理和使用线程的机制。它是将多个线程预先存储在一个“池子”内，
     * 当有任务出现时可以避免重新创建和销毁线程所带来性能开销，只需要从“池子”内取出相应的线程执行对应的任务即可。
     *
     * 线程池的好处是减少在创建和销毁线程上所消耗的时间以及系统资源的开销，解决资源不足的问题。如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。
     * 线程池的优势主要体现在以下 4 点
     * 降低资源消耗：通过池化技术重复利用已创建的线程，降低线程创建和销毁造成的损耗。
     * 提高响应速度：任务到达时，无需等待线程创建即可立即执行。
     * 提高线程的可管理性：线程是稀缺资源，如果无限制创建，不仅会消耗系统资源，还会因为线程的不合理分布导致资源调度失衡，降低系统的稳定性。使用线程池可以进行统一的分配、调优和监控。
     * 提供更多更强大的功能：线程池具备可拓展性，允许开发人员向其中增加更多的功能。比如延时定时线程池ScheduledThreadPoolExecutor，就允许任务延期执行或定期执行。
     *
     * 线程池的使用
     * 线程池的创建方法总共有 7 种，但总体来说可分为 2 类：
     * 一类是通过 ThreadPoolExecutor 创建的线程池；
     * 另一个类是通过 Executors 创建的线程池。
     *
     * 线程池的创建方式总共包含以下 7 种（其中 6 种是通过 Executors 创建的，1 种是通过 ThreadPoolExecutor 创建的）：
     * Executors.newFixedThreadPool：创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待；
     * Executors.newCachedThreadPool：创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程；
     * Executors.newSingleThreadExecutor：创建单个线程数的线程池，它可以保证先进先出的执行顺序；
     * Executors.newScheduledThreadPool：创建一个可以执行延迟任务的线程池；
     * Executors.newSingleThreadScheduledExecutor：创建一个单线程的可以执行延迟任务的线程池；
     * Executors.newWorkStealingPool：创建一个抢占式执行的线程池（任务执行顺序不确定）【JDK 1.8 添加】。
     * ThreadPoolExecutor：最原始的创建线程池的方式，它包含了 7 个参数可供设置，后面会详细讲。
     */
    /**
     * FixedThreadPool
     * 创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待。
     */
    @Test
    public void fixedThreadPool() {
        // 创建2个数据级的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        /*
        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行，线程:" + Thread.currentThread().getName());
            }
        };

        // 线程池执行任务(一次添加4个任务)
        // 执行任务的方式有两种，submit和execute
        threadPool.submit(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        */

        // 简单方式
        threadPool.execute(() -> {
            System.out.println("任务被执行，线程:" + Thread.currentThread().getName());
        });
    }

    /**
     * CachedThreadPool
     * 创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程。
     */
    @Test
    public void cachedThreadPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 执行任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("任务被执行，线程:" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * SingleThreadExecutor
     * 创建单个线程数的线程池，它可以保证先进先出的执行顺序。
     */
    @Test
    public void singleThreadExecutor() {
        // 创建线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + ":任务被执行");
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ScheduledThreadPool
     * 创建一个可以执行延迟任务的线程池。
     */
    public static void scheduledThreadPool() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);

        // 添加定时执行任务(1s后执行)
        System.out.println("添加任务，时间:" + LocalDateTime.now());
        threadPool.schedule(() -> {
            System.out.println("任务被执行，时间:" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
    }

    /**
     * SingleThreadScheduledExecutor
     * 创建一个单线程的可以执行延迟任务的线程池。
     */
    public static void singleThreadScheduledExecutor() {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();

        // 添加定时执行任务(2s后执行)
        System.out.println("添加任务，时间:" + LocalDateTime.now());
        threadPool.schedule(() -> {
            System.out.println("任务被执行，时间:" + LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.SECONDS);
    }

    /**
     * newWorkStealingPool
     * 创建一个抢占式执行的线程池（任务执行顺序不确定），注意此方法只有在 JDK 1.8+ 版本中才能使用。newWorkStealingPool
     */
    @Test
    public void workStealingPool() {
        // 创建线程池
        ExecutorService threadPool = Executors.newWorkStealingPool();

        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行，线程名:" + Thread.currentThread().getName());
            });
        }
        // 确保任务被执行完成
        while (!threadPool.isTerminated()) {
        }
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
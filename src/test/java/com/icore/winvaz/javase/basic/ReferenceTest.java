package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Deciption 对象的引用
 * 从JDK1.2版本开始，把对象的引用(Reference)分为四种级别，从而使程序能更加灵活的控制对象的生命周期。
 * 这四种级别由高到低依次为：强引用、软引用(SoftReference)、弱引用(WeakReference)和虚引用(PhantomReference)
 * @Author wdq
 * @Create 2021/2/23 22:07
 * @Version 1.0.0
 */
public class ReferenceTest {
    /**
     * Reference的主要源码
     */
    /*
    public abstract class Reference<T>{
        private T referent;
        Reference(T referent) {
            this(referent, null);
        }

        Reference(T referent, ReferenceQueue<? super T> queue) {
            this.referent = referent;
            this.queue = (queue == null) ? ReferenceQueue.NULL : queue;
        }
        public T get() {
            return this.referent;
        }
    }
    */

    /**
     * 强引用
     * <p>
     * 以前我们使用的大部分引用实际上都是强引用，这是使用最普遍的引用。
     * 如果一个对象具有强引用，那就类似于必不可少的生活用品，垃圾回收器绝不会回收它。当内存空间不足，
     * Java虚拟机宁愿抛出OutOfMemoryError
     * 错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足问题。
     */
    private static final int KB_1 = 2014; // 1KB
    private static final int MB_1 = 2014 * KB_1; // 1MB
    private static final int MB_5 = 5 * MB_1; // 5MB
    private static final int MB_50 = 50 * MB_1; // 50MB

    /**
     * 设置一下jvm启动参数，最大堆内存100MB
     * -Xmx100M -Xms100M
     */
    /**
     *  Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     *  at com.icore.winvaz.javase.basic.ReferenceTest.strongReferenceTest(ReferenceTest.java:54)
     */
    @Test
    public void strongReferenceTest() {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            list.add(new byte[MB_5]);
        }
    }

    /**
     * 软引用（SoftReference）
     *
     * 如果一个对象只具有软引用，那就类似于可有可物的生活用品。
     * 如果内存空间足够，垃圾回收器就不会回收它，
     * 如果内存空间不足了，就会回收这些对象的内存。
     * 只要垃圾回收器没有回收它，该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存。
     * 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收，
     * JAVA虚拟机就会把这个软引用加入到与之关联的引用队列中。
     */
    /**
     * 软引用对应的类：SoftReference
     */
    /*
    public class SoftReference<T> extends Reference<T> {
        public SoftReference(T referent) {
            super(referent);
        }
    }
    */
    @Test
    public void softReferenceTest() throws InterruptedException {
        // 创建一个软引用，引用50MB的byte数据
        SoftReference<byte[]> sr = new SoftReference<>(new byte[MB_50]);
        // 获取软引用中的数据
        System.out.println(sr.get());
        // 来个强引用的list
        List<byte[]> list = new ArrayList<>();
        // 选好向list中添加数据，慢慢内存不足，会触发软引用的50MB byte数据被回收
        for (int i = 0; i < 10; i++) {
            list.add(new byte[MB_5]);
            // 获取软引用中的引用数据
            byte[] bytes = sr.get();
            System.out.println(list.size() + ":" + bytes);
            TimeUnit.SECONDS.sleep(1);
            if (bytes == null) break;
        }
    }

    /**
     * 弱引用（WeakReference）
     * <p>
     * 如果一个对象只具有弱引用，那就类似于可有可物的生活用品。
     * 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它
     * 所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
     * 不过，由于垃圾回收器是一个优先级很低的线程， 因此不一定会很快发现那些只具有弱引用的对象。
     * 弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，
     * Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
     */
    @Test
    public void weakReferenceTest() {
        // 创建一个弱引用，引用50MB的byte数据
        WeakReference<byte[]> wr = new WeakReference<>(new byte[MB_50]);
        // 获取弱引用中的数据
        System.out.println(wr.get()); // [B@1757cd72
        System.out.println("触发GC"); // 触发GC
        System.gc(); // 触发GC，会导致弱引用中的数据被回收，即wr中引用的50MB byte被回收
        System.out.println("GC完毕"); // GC完毕
        System.out.println(wr.get()); // null
    }

    /**
     * 虚引用（PhantomReference）
     * <p>
     * “虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。
     * 如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收。虚引用主要用来跟踪对象被垃圾回收的活动。
     * 虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列（ReferenceQueue）联合使用。
     * 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
     * 程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。
     * 程序如果发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。
     */
    @Test
    public void phatomReferenceTest() {
        // 创建引用队列，当Reference对象引用的数据被回收的时候，Reference对象会被加入到这个队列中
        ReferenceQueue<byte[]> rq = new ReferenceQueue<>();
        // 创建虚引用，引用50MB的byte数据
        PhantomReference<byte[]> pr = new PhantomReference<>(new byte[MB_50], rq);
        System.out.println(pr); // java.lang.ref.PhantomReference@1757cd72
        // 获取虚引用的数据
        System.out.println(pr.get()); // null
        System.out.println(rq.poll()); // null
        System.out.println("触发gc"); // 触发gc
        System.gc(); // 触发gc
        System.out.println("gc完毕"); // gc完毕
        System.out.println(pr.get()); // null
        System.out.println(rq.poll()); // java.lang.ref.PhantomReference@1757cd72
    }
}

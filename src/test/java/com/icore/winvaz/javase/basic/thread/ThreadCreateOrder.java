package com.icore.winvaz.javase.basic.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 多线程创建顺序订单号
 * @Author wdq
 * @Create 2019-06-16 21:54
 */
public class ThreadCreateOrder {
    public static void main(String[] args) {
        OrderInfo orderInfo = new OrderInfo();

        ThreadA threadA = new ThreadA(orderInfo);
        ThreadB threadB = new ThreadB(orderInfo);

        Thread A = new Thread(threadA);
        A.setName("ThreadA");
        Thread B = new Thread(threadB);
        B.setName("ThreadB");

        A.start();
        B.start();
    }
}

/**
 * 订单产品
 */
class OrderInfo {

    // 定义100个订单号
    private String num = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    // 定义一个标识，true为A线程输出，false为B线程输出。
    private boolean flag = false;
    // 定义一个计数器
    private int count = 1;

    public OrderInfo() {

    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}

/**
 * 线程A输出奇数
 */
class ThreadA  implements Runnable{

    private OrderInfo orderInfo;

    public ThreadA(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public void run() {
        while (orderInfo.getCount() < 10) {
            // 同步代码块
            synchronized (orderInfo) {
                if (orderInfo.isFlag()) {
                    try {
                        orderInfo.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 输出线程A结果
                System.out.println(Thread.currentThread().getName() + "：输出的奇数为：" + orderInfo.getNum() + orderInfo.getCount());
                // 计数器自增
                orderInfo.setCount(orderInfo.getCount() + 1);
                // 修改标记
                orderInfo.setFlag(true);
                // 唤醒B线程
                orderInfo.notify();
            }
        }
    }
}

/**
 * 线程B输出偶数
 */
class ThreadB implements Runnable {

    private OrderInfo orderInfo;

    public ThreadB(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public void run() {
        while (orderInfo.getCount() <= 10) {
            // 同步代码块
            synchronized (orderInfo) {
                if (!orderInfo.isFlag()) {
                    try {
                        orderInfo.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 输出线程A结果
                System.out.println(Thread.currentThread().getName() + "：输出的偶数为：" + orderInfo.getNum() + orderInfo.getCount());
                // 计数器自增
                orderInfo.setCount(orderInfo.getCount() + 1);
                // 修改标记
                orderInfo.setFlag(false);
                // 唤醒B线程
                orderInfo.notify();
            }
        }
    }
}
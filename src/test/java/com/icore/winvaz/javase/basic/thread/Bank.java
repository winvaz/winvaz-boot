package com.icore.winvaz.javase.basic.thread;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Deciption 银行类
 * @Author wdq
 * @Create 2020/11/30 14:51
 * @Version 1.0.0
 */
public class Bank {

    // 条件对象
    private Condition sufficientFunds;
    // 锁
    private Lock bankLock;

    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }
    /*
    public void transfer(int from, int to, double amount) throws InterruptedException {
        *//*if (accounts[from] < amount) {
            return;
        }*//*
        // 加锁同步
        bankLock.lock();
        try {
            // 判断余额
            while (accounts[from] < amount)
                // 暂停，放弃锁
                sufficientFunds.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balanc: %10.2f%n", getTotalBalance());
            // 激活
            sufficientFunds.signalAll();
        } finally {
            // 释放锁
            bankLock.unlock();
        }
    }

    private double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    */

    // synchronized
    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        /*if (accounts[from] < amount) {
            return;
        }*/
        // 判断余额
        while (accounts[from] < amount)
            // 暂停，放弃锁
            wait();
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balanc: %10.2f%n", getTotalBalance());
        // 激活
        notifyAll();
    }

    private synchronized double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}

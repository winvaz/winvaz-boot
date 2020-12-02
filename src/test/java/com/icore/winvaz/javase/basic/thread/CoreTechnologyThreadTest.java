package com.icore.winvaz.javase.basic.thread;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @Deciption Java核心技术 卷I 并发 554
 * @Author wdq
 * @Create 2020/11/30 14:49
 * @Version 1.0.0
 */
public class CoreTechnologyThreadTest {

    // public static final int NACCOUNTS = 100;
    public static final int NACCOUNTS = 10; // 死锁
    public static final double INITIAL_BALANCE = 1000;
    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        // double amount = MAX_AMOUNT * Math.random();
                        double amount = 2 * MAX_AMOUNT ; // 死锁
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            new Thread(r).start();
        }



        // Bank bank = new Bank(4, 100000);

        // 错误
        /*
        Runnable r = () -> {
            try {
                while (true) {
                    int toAmount = (int) (bank.size() * Math.random());
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(1, toAmount, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
        */

       /*
        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(0, 1, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (Exception e) {

            }
        };

        Runnable  task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(2, 3, amount);
                    Thread.sleep((int)(DELAY * Math.random()));
                }
            } catch (Exception e) {

            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
        */
    }
}

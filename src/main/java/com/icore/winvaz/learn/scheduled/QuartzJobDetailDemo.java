package com.icore.winvaz.learn.scheduled;

import java.time.LocalDateTime;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/7/23 10:06
 * @Version 1.0.0
 */
public class QuartzJobDetailDemo {
    public void sayHello() {
        System.out.println("HelloTask>>>" + LocalDateTime.now());
    }
}

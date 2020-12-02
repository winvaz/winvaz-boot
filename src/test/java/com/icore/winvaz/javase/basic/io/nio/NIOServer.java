package com.icore.winvaz.javase.basic.io.nio;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @Author wdq
 * @Create 2019-04-08 14:15
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        new Thread(() -> {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

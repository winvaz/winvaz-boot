package com.icore.winvaz.javase.basic.io.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author wdq
 * @Create 2019-04-08 14:10
 */
public class IOClient {

    @Test
    public void test() {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}

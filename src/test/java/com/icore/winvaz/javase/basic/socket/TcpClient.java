package com.icore.winvaz.javase.basic.socket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * TCP协议，客户端，连接服务器，发送数据，读取服务器回来的数据
 *
 * @Author wdq
 * @Create 2019-02-25 15:43
 */
public class TcpClient {
    public static void main(String[] args) {
        try {
            // 创建连接对象，Socket
            Socket socket = new Socket("127.0.0.1", 8000);
            // 从连接对象Socket中，获取字节输出流
            OutputStream outputStream = socket.getOutputStream();
            // 写数据
            outputStream.write("你好TCP".getBytes());

            // 通过连接对象，获取字节输入流，读取服务器发送回来的数据
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[12];
            int len = inputStream.read(bytes);
            System.out.println(new String(bytes, 0, len));

            // 关闭资源
            inputStream.close();
            outputStream.close();
            socket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void talnetTest() {
        try (Socket socket = new Socket("time-a.nist.gov", 13);
             Scanner scanner = new Scanner(socket.getInputStream(), "UTF-8")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inetAddressTest() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }
}

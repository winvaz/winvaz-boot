package com.icore.winvaz.javase.basic.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议，服务器，连接客户端，发送数据，读取服务器回来的数据
 * @Author wdq
 * @Create 2019-02-25 15:52
 */
public class TcpServer {
    public static void main(String[] args) {
        try {
            // 建立服务器的连接对象
            ServerSocket serverSocket = new ServerSocket(8000);
            // 获取客户端的连接对象
            Socket socket = serverSocket.accept();
            // 通过客户端的连接对象，获取字节输入流，读取客户端数据
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[12];
            int len = inputStream.read(bytes);
            System.out.println(new String(bytes,0,len));

            // 获取字节输出流，往客户端发送数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("行收到了".getBytes());

            outputStream.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

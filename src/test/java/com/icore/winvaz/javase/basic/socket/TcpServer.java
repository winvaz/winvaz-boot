package com.icore.winvaz.javase.basic.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * TCP协议，服务器，连接客户端，发送数据，读取服务器回来的数据。改造为多线程版本
 *
 * @Author wdq
 * @Create 2019-02-25 15:52
 */
public class TcpServer {

    /*public static void main(String[] args) {
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
    }*/

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(8189)) {
            int i = 1;
            while (true) {
                Socket accept = socket.accept();
                System.out.println("Spawning " + i);
                ThreadHandler threadHandler = new ThreadHandler(accept);
                Thread thread = new Thread(threadHandler);
                thread.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadHandler implements Runnable {

    private Socket socket;

    public ThreadHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();) {
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            System.out.println("Hello ! Enter BYE to exit.");

            // Echo client input
            boolean done = false;
            while (done && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Echo " + line);
                if ("BYE".trim().equalsIgnoreCase(line)) {
                    done = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

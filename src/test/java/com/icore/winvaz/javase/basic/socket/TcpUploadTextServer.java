package com.icore.winvaz.javase.basic.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * 服务器，创建目录
 * 读取客户端的数据，写到目录中
 * 写完文件后，发送上传成功
 *
 * @Author wdq
 * @Create 2020/5/28 19:13
 * @Version 1.0.0
 */
public class TcpUploadTextServer {
    public static void main(String[] args) {
        try {
            // 建立ServerSocket对象
            ServerSocket serverSocket = new ServerSocket(8000);
            // 获取客户端连接对象
            Socket socket = serverSocket.accept();
            // 创建upload目录
            File file = new File("/Users/wdq/Documents/upload");
            if (!file.exists()) {
                file.mkdirs();
            }
            // 读取客户端的文本文件
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 打印流对象，将读取到的客户端数据，写了目录中
            // 将文件名修改
            String fileName = "winvaz" + System.currentTimeMillis() + new Random().nextInt(100) + ".txt";
            PrintWriter printWriter = new PrintWriter(new FileWriter(file + File.separator + fileName), true);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            // 回传上传成功到客户端
            socket.getOutputStream().write("上传成功".getBytes());
            // 关流
            printWriter.close();
            bufferedReader.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.icore.winvaz.javase.basic.socket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 上传文本文件--客户端
 * 读取文本文件，打印流将数据打印到服务器
 * 读取服务器的回传数据，上传成功
 *
 * @Author wdq
 * @Create 2020/5/28 19:00
 * @Version 1.0.0
 */
public class TcpUploadTextClient {
    public static void main(String[] args) {
        try {
            // 建立Socket服务器
            Socket socket = new Socket("127.0.0.1", 8000);
            // 使用IO流，读取文本文件，缓冲区
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/wdq/Documents/old.txt"));
            // 获取字节输入流，将数据发送至服务器，打印流
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            // 调用Socket的shutdownOutput(),结束标记发给服务器
            socket.shutdownOutput();
            // 如果循环退出，文件写到服务器，读取服务器发回的上传成功
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[8];
            int len = inputStream.read(bytes);
            System.out.println(new String(bytes, 0, len));
            // 关流
            inputStream.close();
            printWriter.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.icore.winvaz.javase.basic.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

/**
 * 多线程的单一窗口的收与发
 * @Author wdq
 * @Create 2020/5/27 23:06
 * @Version 1.0.0
 */
public class ThreadChatTest {
    public static void main(String[] args) throws Exception {
        DatagramSocket send = new DatagramSocket(10000);
        DatagramSocket server = new DatagramSocket(8000);

        new Thread(new ChatSend(send)).start();
        new Thread(new ChatServer(server)).start();
    }
}

// 客户端
class ChatSend implements Runnable {
    // Socket对象
    private DatagramSocket datagramSocket;

    public ChatSend(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // 转字节
                byte[] bytes = line.getBytes();
                // 封装数据包
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8000);
                // 发送
                datagramSocket.send(datagramPacket);
                // over结束
                if (Objects.equals("over", line)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(datagramSocket)) {
                    datagramSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (Objects.nonNull(bufferedReader)) {
                        bufferedReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// 服务器
class ChatServer implements Runnable {
    private DatagramSocket datagramSocket;

    public ChatServer(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        byte[] bytes = new byte[1024];
        while (true) {
            try {
                // 数据包对象
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                // 接受对象
                datagramSocket.receive(datagramPacket);
                // 转字符串
                String message = new String(bytes, 0, datagramPacket.getLength());
                if (Objects.equals("over", message)) {
                    System.out.println(message + "..."
                            + datagramPacket.getAddress().getHostAddress()
                            + "..." + datagramPacket.getPort() + "...走了");
                } else {
                    System.out.println(message + "..." + datagramPacket.getAddress().getHostAddress()
                            + "..." + datagramPacket.getPort());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
package com.icore.winvaz.javase.basic.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @Author wdq
 * @Create 2020/5/22 14:12
 * @Version 1.0.0
 */
public class UdpClient {
    public static void main(String[] args) {
        // 字符输入流
        BufferedReader bufferedReader = null;
        // 建立Socket服务
        DatagramSocket datagramSocket = null;
        try {
            // 客户端固定端口号
            datagramSocket = new DatagramSocket(10000);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // 读一行，封装成数据包，发送
                byte[] bytes = line.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8000);
                datagramSocket.send(datagramPacket);
                if (Objects.equals("over", line)) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(bufferedReader)) {
                    bufferedReader.close();
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
                }
            }
        }
    }
}
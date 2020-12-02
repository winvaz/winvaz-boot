package com.icore.winvaz.javase.basic.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;

/**
 * @Author wdq
 * @Create 2020/5/27 11:27
 * @Version 1.0.0
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        // 建立Socket服务
        DatagramSocket datagramSocket = new DatagramSocket(8000);
        byte[] bytes = new byte[1024];
        while (true) {
            // 封装数据包，用于接收数据
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            datagramSocket.receive(datagramPacket);
            String message = new String(bytes, 0, datagramPacket.getLength());
            if (Objects.equals("over", message)) {
                System.out.println(message + "..."
                        + datagramPacket.getAddress().getHostAddress()
                        + "..." + datagramPacket.getPort() + "...走了");
            } else {
                System.out.println(message + "..." + datagramPacket.getAddress().getHostAddress()
                        + "..." + datagramPacket.getPort());
            }
        }
    }
}

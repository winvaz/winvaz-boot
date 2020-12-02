package com.icore.winvaz.javase.basic.socket;
/*
 * 文本上传的服务器
 * 读取图片，写图片
 * 写结束标记
 * 读取服务器的上传成功
 */

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPUpLoadJPGClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("192.168.23.206", 8000);
        //字节流读取文件
        FileInputStream fis = new FileInputStream("c:\\a.jpg");
        //获取字节输出流，图片写到服务器
        OutputStream out = s.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        //结束标记
        s.shutdownOutput();
        //读取服务器的上传成功
        InputStream in = s.getInputStream();
        len = in.read(bytes);
        System.out.println("服务器说：：：" + new String(bytes, 0, len));
        fis.close();
        s.close();
    }
}
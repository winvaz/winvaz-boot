package com.icore.winvaz.javase.basic.socket;
/*
 * 图片上传的服务器
 * 读取客户端的图片，字节流
 * 写上传成功
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPUpLoadJPGServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8000);
        Socket s = ss.accept();
        File file = new File("d:\\upload");
        if (!file.exists()) file.mkdirs();
        //获取字节输入流,读取客户端的图片
        InputStream in = s.getInputStream();
        //随机生产文件名
        String filename = "icore" + System.currentTimeMillis() + new java.util.Random().nextInt(9999999) + ".jpg";
        //建立写文件的字节输出流
        FileOutputStream fos = new FileOutputStream(file + File.separator + filename);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        s.getOutputStream().write("上传成功".getBytes());
        fos.close();
        s.close();
        ss.close();
    }
}
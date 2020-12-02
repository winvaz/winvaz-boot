package com.icore.winvaz.javase.basic.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Upload implements Runnable {
    private Socket s;

    Upload(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败");
        }
    }
}

public class TCPThreadUploadJPGServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocket对象，监听端口
        ServerSocket ss = new ServerSocket(8000);
        while (true) {
            Socket s = ss.accept();//线程阻塞，等待客户端连接
            Thread.sleep(500);
            //将客户端端对象s ,传递给多线程 run方法处理
            new Thread(new Upload(s)).start();
        }
    }
}
package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 8.	字节输入流
 * 字节可以操作任意文件，字节输入流读取文件，单个字节8个二进制
 * 字节输入流的抽象基类InputStream
 * 读取的方法，读取单个字节，读取字节数组，读取字节数组一部分
 * 子类是FileInputStream，构造方法直接传递字符串文件名
 * 读取单个字节的read()方法，返回字节的码值，文件末尾返回-1
 * int available()字节输入流的方法,返回字节输入流封装的文件的字节数
 */
public class InputStreamTest {

    @Test
    public void test() throws IOException {
        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("/Users/wdq/Desktop/writer.txt");
            // 读取单个字节的方法，父类的方法read()方法，返回单个字节，int
            //int read = fileInputStream.read();
            //System.out.println(read);
            // 字节数
            System.out.println(fileInputStream.available());
            // 循环读取
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
    }
}

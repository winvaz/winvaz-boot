package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 9.	字节流复制任意文件
 * 第一种方式读取一个字节，写一个字节
 * 	第二种方式读取一个字节数组，写一个字节数组--重点案例，必须要写熟练
 * 	第三种方式读取文件，用字节流缓冲区实现
 */
public class CopyFileTest {

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        // 创建字节流对象
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        // 字节缓冲流
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            // 数据源与数据目的
            fileInputStream = new FileInputStream("/Users/wdq/Desktop/reader.txt");
            fileOutputStream = new FileOutputStream("/Users/wdq/Desktop/writer.txt");

            // 字节数组缓冲区
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            /*
            int len = 0;
            while ((len = fileInputStream.read()) != -1) {
                fileOutputStream.write(len);
            }
            */

            /**
             * 字节流复制任意文件
             * 读取字节数组，写字节数组
             */
            // 字节输入流独有的方法：available()获取数据源的字节数
            int i = fileInputStream.available();
            System.out.println(i);
            /*
            int len = 0;
            // 定义字节数组
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
            */

            /**
             * 字节数组缓冲
             * 读取单个字节的方式
             */
            int len = 0;
            /*
            byte[] bytes = new byte[1024];
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
            */
            while ((len = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // throw new RuntimeException("复制失败》》》" + e);
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("字节输出流资源关闭失败", e);
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("字节输入流资源关闭失败", e);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 7.	字节输出流
 * 字节可以操作任意文件，字节输出流，写任意文件，单个字节8个二进制。
 * 不查询编码表
 * 字节输出流的抽象基类OutputStream
 * 写字节数组，字节数组一部分，单个字节
 * 子类FileOutputStream类，文件的输出
 * 构造方法方法，传递字符串文件名
 * 字节流写数据，不需要刷新，直接向数据写到目的地
 */
public class OutputStreamTest {

    @Test
    public void test() {
        // 字节流没有行的概念，那怎么来实现换行操作呢，获取操作系统
        Properties properties = System.getProperties();
        //System.out.println(properties.getProperty("os.name"));
        String os = properties.getProperty("os.name");
        String line = "";
        if (os.charAt(0) == 'W') {
            line = "\r\n";
        } else {
            line = "\n";
        }
        // 创建字节输出流对象
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("/Users/wdq/Desktop/writer.txt");
            // 调用父类的写方法,Write()写
            fileOutputStream.write(("写字符串" + line).getBytes());
            fileOutputStream.write(65);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

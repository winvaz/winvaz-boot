package com.icore.winvaz.javase.basic.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 3.	打印流
 * Java.io.PrintStream
 * java.io.PrintWriter
 * 打印流的特点：打印流只操作数据目的，不操作数据源。打印流永远不会抛出IO异常。
 * System.out 获取到打印流对象PrintStream
 * <p>
 * PrintStream构造方法，传递的是打印流的输出目的
 * File对象
 * 字节输出流对象
 * 字符串的文件名
 * <p>
 * PrintWrtier类实现了PrintStream类的所有print方法，区别在于构造方法不同
 * File对象
 * 字节输出流
 * 字符串文件名
 * 字符输出流
 * <p>
 * 打印流中，实现换行输出，使用println()方法。打印流自动刷新
 * 打印流中，构造方法可以写boolean值，如果是true，开启自动刷新，但是开启自动刷新，打印流中传递的对象，必须是流对象才行
 * new PrintWriter(输出流对象, true)
 * 必须调用println,printf,format三个方法，才能实现自动刷新
 * 日后开发有什么，特点简单，方便，自动刷新，不出异常，用在Java的服务器端程序，可以把数据，使用打印流写会客户端浏览器
 * <p>
 * 打印流案例：使用打印流对象PrintWrtier，替代转换流，字符转成字节，OutputStreamWriter，实现数据的打印功能。OutputStreamWriter(System.out)
 * PrintWriter类属于Writer的子类，字符流，只能操作文本数据。如果操作的不是文本数据，纯字节流
 */
public class PrintStreamTest {

    /**
     * * 打印流对象
     * * 将键盘录入的数据
     * * 通过打印流，打印到一个文本中
     */
    public static void main(String[] arg) {
        // 直接转换流，字符流的缓冲区，实现键盘输入
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 数据目的是一个文本文件，写文本文件FileWriter
        // 创建打印流对象，传递字符输出流
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("/Users/wdq/Documents/print.txt"), true);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // 读一行，将数据通过打印流，打印到目的文件中
                printWriter.println(line);
                // 读取到over结束
                if ("over".equals(line)) {
                    break;
                }
            }
            printWriter.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

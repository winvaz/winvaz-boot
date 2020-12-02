package com.icore.winvaz.javase.basic.io;

import java.io.*;

/**
 * 12.	InputStreamReader
 * 是Reader的子类，字符流对象，字节流向字符的桥梁。InputStreamReader有一个子类就是FileReader
 * 字节转成字符流，InputStreamReader如果使用
 * 构造方法：InputStreamReader(InputStream in)传递字节输入流，变成字符流
 * 字节输入流读的是文本文件，可以用转换流
 *
 * @Deciption 转换流---字节转字符
 * 实现读一行的功能
 * System.in
 * InputStreamReader---字节转字符
 * BufferedReader
 * <p>
 * 14.	OutputStreamWriter
 * 是Writer的子类，字符流对象，字符流向字节的桥梁
 * OutputStreamWriter构造方法，传递的是字节输出流，System.out
 */
public class TransformStreamTest {

    public static void main(String[] args) {
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;

        FileInputStream fileInputStream = null;

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            // fileInputStream = new FileInputStream("/Users/wdq/Desktop/a.csv");
            // inputStreamReader = new InputStreamReader(fileInputStream, "GBK");

            // 获取键盘输入的字节流
            InputStream in = System.in;
            // 将字节流转成字符流，InputStreamReader的构造方法中，传递字节流
            inputStreamReader = new InputStreamReader(in);

            // 利用字符缓冲区读取一行
            bufferedReader = new BufferedReader(inputStreamReader);

            // 将读取到的readLine()的数据输出到控制台，数据是一个字符流
            // 输出数据的目的地System.out
            // 获取字节输出流对象
            //OutputStream outputStream = System.out;
            OutputStream outputStream = new FileOutputStream("/Users/wdq/Desktop/writer.txt");
            // 将字符流转成字节流
            // 一个是字节流，一个是编码表名
            outputStreamWriter = new OutputStreamWriter(outputStream, "GBK");
            // OutputStreamWriter是字符流，可以使用字符缓冲区对象操作，实现写换行
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if ("over".equals(line)) {
                    break;
                }
                bufferedWriter.write(line.toUpperCase());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

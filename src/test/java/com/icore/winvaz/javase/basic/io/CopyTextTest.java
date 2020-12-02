package com.icore.winvaz.javase.basic.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 复制文本文件：字符流对象完成，读一个字符，写一个字符
 * 实现步骤：
 * 1. FileReader建立对象，包装源文件 c:\a.html
 * 2. FileWriter建立对象，包装目的文件 d:\a.html
 * 3. read方法读取一个字符
 * 4. write方法写一个字符，刷新
 * 5. 关闭资源
 */
public class CopyTextTest {
    public static void main(String[] args) {
        // 程序开始时间
        long start = System.currentTimeMillis();
        // 建立流对象
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        // 字符缓冲流
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileReader = new FileReader("/Users/wdq/Desktop/reader.txt");
            fileWriter = new FileWriter("/Users/wdq/Desktop/writer.txt");

            // 创建缓冲区对象，提高字符输出流效率
            bufferedReader = new BufferedReader(new FileReader("/Users/wdq/Desktop/reader.txt"));
            bufferedWriter = new BufferedWriter(new FileWriter("/Users/wdq/Desktop/writer.txt"));
            /*
            // 开始循环读取源文件，读一个字符写一个字符
            int len = 0;
            while ((len = fileReader.read()) != -1) {
                // 写单个
                fileWriter.write(len);
                // 刷新
                fileWriter.flush();
            }
            */
            /**
             * 采用读写字符数组提升效率
             * 单个字符的读写速度快100倍以上
             */
            /*
            char[] chars = new char[1024];
            int len = 0;
            while ((len = fileReader.read(chars)) != -1) {
                // 写字符数组
                fileWriter.write(chars, 0, len);
                // 刷新
                fileWriter.flush();
            }
            */
            System.out.println("======================================");
            /**
             * 1.	字符输出流的缓冲区对象
             * 数组的缓冲，提高了流对象的读写效率，开发出现流对象的缓冲区对象，
             * 目的提高流的操作效率。字符输出流的缓冲区 java.io.BufferedWriter
             * 字符输出流缓冲区对象方法
             * 	构造方法 BufferedWriter(Writer out)传递的是Writer的子类对象。
             * 目的：缓冲区对象出现的目的，提高输出流的效率，提高哪一个流，只要传递的是Writer的子类，
             * 提高的就是你这个子类的效率。FileWriter     比喻：耳机和手机
             * 	void newLine()写一个换行。\r\n Windows下有效，Linux操作系统\n
             * newLine()具有跨平台性，安装的JVM版本了，如果安装的JVM是Windows版本的，
             * newLine()方法中写的就是\r\n ,如果安装的是Linux版本的JVM，newLine()方法中的写就是\n
             */
            /**
             * 2.	字符输入流的缓冲区对象
             *  * 提高字符输入流的读取效率，java.io.BufferedReader，查阅API文档，这个缓冲区可以按行读取。（
             *  * 字符输入流缓冲区对象的方法
             *  * 	构造方法 BufferedReader(Reader in),传递是的Reader类的子类对象。
             *  * 目的，提高字符输入流的读取效率，提高哪个流对象，传递Reader子类，FileReader.
             *  * 	String readLine()读取文本一行，返回字符串,读取文件结尾，返回null，读取一行，
             *  * 返回的字符串中，没有\r\n符号，返回是一行有效字符
             */
            /**
             * 复制文本，缓冲区，读一行写一行，写换行，刷新
             */
            // 按行读取数据
            // 读取一行没有\r\n这个符号，只读取一行的有效字符。
            //String string = bufferedReader.readLine();
            //System.out.println(string);
            // 改用循环方式
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                // Windows系统的换行\r\n，Linux系统的换行\n
                // \r回车，\n换行
                // 换行
                bufferedWriter.newLine();
                // 刷新
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("复制失败！");
        } finally {
            // 关闭流的顺序，先开的后关
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("写入源资源流关闭失败!");
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("读取源资源流关闭失败!");
                }
            }
        }
        // 所用时间
        System.out.println("程序所用时间为：" + (System.currentTimeMillis() - start));
    }
}

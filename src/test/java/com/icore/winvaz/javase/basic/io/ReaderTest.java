package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.FileReader;

/**
 * Reader类，抽象类，找子类，FileReader，构造方法，直接传递字符串的文件名
 * 调用FileReader父类方法实现读取功能
 * 读取单个字符的方法  int read() 读取一个字符
 * read()方法，每次只能读取一个字符，返回这个字符的码值，没调用一次read()自动向后读取一个字符，如果读取到文件的末尾，返回-1
 * 注意：文件读取的时候，一次循环不允许出现2次read()
 * 保证读取的文件必须是GBK编码的(ANSI)
 * 	读取字符数组 int read(char[] ch)
 * 	每次读取到的字符，装在字符数组中，返回每次读取的有效字符个数
 */
public class ReaderTest {

    @Test
    public void test() {
        // 读取单个字符
        FileReader fileReader = null;
        try {
            // 创建字符输入流对象，传递字符串的文件名
            fileReader = new FileReader("/Users/wdq/Desktop/writer.txt"); // abc

            // 调用父类的方法，读取单个字符
            /*
            int read = fileReader.read();
            System.out.println(read); // 97

            int read1 = fileReader.read();
            System.out.println(read1); // 98

            int read2 = fileReader.read();
            System.out.println(read2); // 99

            int read3 = fileReader.read();
            System.out.println(read3); // -1
            */

            // 循环读取文件，当read()方法返回的数不是-1，没有读完
            /*
            int len = 0;
            while ((len = fileReader.read()) != -1) {
                System.out.println((char)len);
            }
            */

            // read()方法，读取文本文件，传递字符数组 int read(char[] ch)
            // 定义字符数组，定义大小为1024的倍数
            char[] chars = new char[1024];
            /*
            // 开始调用父类方法read()，返回一个int值
            int read = fileReader.read(chars);
            System.out.println("X=" + read); // 2
            System.out.println(new String(chars)); // ab

            int read1 = fileReader.read(chars);
            System.out.println("X=" + read1); // 1
            System.out.println(new String(chars)); // cb

            int read2 = fileReader.read(chars);
            System.out.println("X=" + read2); // -1
            System.out.println(new String(chars)); // cb

            int read3 = fileReader.read(chars);
            System.out.println("X=" + read3); // -1
            System.out.println(new String(chars)); // cb
            */

            // 循环读取
            int len = 0;
            while ((len = fileReader.read(chars)) != -1) {
                // 返回字符有效个数
                System.out.print(new String(chars, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
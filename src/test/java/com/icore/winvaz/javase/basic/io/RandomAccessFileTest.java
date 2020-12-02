package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 6.	RandomAccessFile随机读写流
 * 随机读写流的特点：
 * 	直接继承Object类
 * 	这个类中封装了一个大型的字节数组
 * 	这个类可以写，可以读
 * 	随机访问，可以从文件的某一个位置上进行读写
 * 	移动文件指针的方法seek[long]
 * 	这个类可以读写基本数据类型
 * 	实际应用，制作p2p下载软件，迅雷，旋风，电驴
 * <p>
 * RandomAccessFile的构造方法
 * 两个参数，一个是File类型包装一个文件,Stirng类型
 * 两个参数，一个是String类型文件名，String类型
 * <p>
 * 随机读写类，读和写基本数据类型
 * 随机读写，张三65
 * 读取有基本数据类型，利用异常将程序停下，不能判断-1，读取的方法返回是int类型-1也是有效
 */
public class RandomAccessFileTest {

    /**
     * 定义方法，实现读取基本数据类型 int
     */
    @Test
    public void readInt() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/wdq/Documents/ran.txt", "rw");
        int x = raf.readInt();
        System.out.println(x);
    }

    /**
     * 定义方法，实现写基本数据类型int
     */
    @Test
    public void writeInt() throws IOException {
        //创建随机读写类对象，rw的形式打开
        RandomAccessFile raf = new RandomAccessFile("/Users/wdq/Documents/ran.txt", "rw");
        raf.writeInt(65);
        raf.close();
    }

    /**
     * 读取的方法，读取文件的全部内容
     */
    @Test
    public void readAll() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/wdq/Documents/ran.txt", "rw");
        byte[] bytes = new byte[4];
        int age = 0;
        while (true) {
            try {
                raf.read(bytes);
                age = raf.readInt();
                System.out.println(new String(bytes) + age);
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * 读取文件，随机读取
     */
    @Test
    public void read() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/wdq/Documents/ran.txt", "rw");
        //偏移指针
        raf.seek(8);
        byte[] bytes = new byte[4];
        raf.read(bytes);
        int age = raf.readInt();
        System.out.println(new String(bytes) + age);
    }


    /**
     * 写张三65
     */
    @Test
    public void write() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/wdq/Documents/ran.txt", "rw");
        raf.write("张三".getBytes());
        raf.writeInt(65);

        raf.seek(16);
        raf.write("李四".getBytes());
        raf.writeInt(66);
        //将文件指针，偏移到8字节
        raf.seek(8);
        raf.write("王武".getBytes());
        raf.writeInt(67);
        raf.close();
    }
}

package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符输出流 Writer,父类不能直接使用，找子类，FileWriter类
 * 构造方法 FileWriter(字符串的文件名)
 * 字符流在写数据的时候，write方法写，数据写在了内存中，并没有直接写到目的文件中，内存(缓冲)，如果让数据写到目的文件中，字符流要进行缓存区的刷新操作。Writer中的方法flush，刷新流的缓冲，将数据写到目的地，字符流只要刷新的，数据必会走向目的地。
 * 文件的续写，文件的后面追加写入，原有的数据都被保留下来
 * FileWriter类构造方法中，传递String的文件名，true实现追加写入
 */

/**
 * IO流的异常处理方式  try..catch
 * 关闭流的工作，写在finally中，无论写数据成功与否，流必须要关闭
 * 关闭流对象的方法，有几个，单独的写几个try..catch
 * 程序代码已经是固定写发，日后开发就是这样写的
 * 流对象的异常必须处理，关闭动作必须要做
 */
public class WriterTest {
    @Test
    public void test() {
        // try外声明变量，try内建立对象
        FileWriter fileWriter = null;
        /**
         * IO流的异常处理方式，try....catch
         * 关闭流的工作，写在finally中，无论写数据成功与否，流必须关闭
         * 关闭流对象的方法，有几个流就要单独写几个try....catch
         * 程序代码已经是固定写法，日后开发就是这样写的。
         * 流对象的异常必须处理。
         */
        try {
            /**
             * 抛出IOException
             * 创建一个文件，如果文件已经存在则覆盖掉原来的。
             * IO流不具备创建文件的能力
             * 调用Windows系统的功能创建的，占用了操作系统的资源
             */
            fileWriter = new FileWriter("/Users/wdq/Desktop/writer.txt", true);
            // 文件的续写
            fileWriter.write("文件的续写\r\n");
            fileWriter.write("文件的续写\r\n");
            fileWriter.flush();

            //fileWriter = new FileWriter("/Users/wdq/Desktop/writer.txt");
            // 调用父类的方法来写字符串
            //fileWriter.write("abc");
            // 写数据最好写一个刷一个。
            //fileWriter.flush();

            // 字符输出流，写单个字符和字节数组
            // A-Z 65-90
            // a-z 97-122
            // 0-9 48-57
            //fileWriter.write(50); // ASCII编码表
            //fileWriter.flush();

            // 写字符数组
            //char[] chars = {'w', 'q', '汉', '字'};
            //fileWriter.write(chars);
            //fileWriter.flush();
            // 写字符数组一部分
            //char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
            // 从0下标位置开始，写3个字符。
            //fileWriter.write(chars, 0, 3);
            //fileWriter.flush();

            //fileWriter.write("写个中文行吗？");
            //fileWriter.flush();

            //fileWriter.write("wasd");
            // 字符流在写数据的时候，先把数据写在内存中，并没有直接写到目的文件中，内存(缓冲)。
            // 如果想让数据写到目的文件中，字符流需要进行缓冲区的刷新操作。字符流只要刷新了，数据必会写到目的地。
            //fileWriter.flush();
            // IO流，使用完毕后，必须关闭流对象，释放对象内存资源。
        } catch (IOException e) {
            e.printStackTrace();
            //程序停止，不要运行了
            throw new RuntimeException("文件写入失败");
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("资源流关闭失败！");
            }
        }
    }
}

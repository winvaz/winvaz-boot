package com.icore.winvaz.javase.basic.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @Deciption 自定义读一行装饰类
 * @Author wdq
 * @Create 2019-08-10 22:33
 */
public class MyReaderLineTest {
    // 自己的写方法，没有读取功能，必须依靠Reader类的子类read方法读取
    // 调用者，传递一个Reader的子类
    private Reader reader;

    public MyReaderLineTest(Reader reader) {
        this.reader = reader;
    }

    // 定义方法，读取一行，返回String
    // 调用者处理异常
    public String readerLine() throws IOException {
        // 定义一个字符缓冲区
        StringBuilder stringBuilder = new StringBuilder();

        // 调用Reader子类的read()方法，单个字符的读取
        int len = 0;
        while ((len = reader.read()) != -1) {
            // 如果读取到'\r'无效字符，不能装进缓冲区，继续读取下一个
            if (len == '\r') {
                continue;
            }
            // 如果读取到'\n'，说明这一行结束了，将缓冲区变成string返回
            if (len == '\n') {
                return stringBuilder.toString();
            }
            // 如果以上两个判断都不成立，说明读取到的是有效字符，装进缓冲区
            stringBuilder.append((char) len);
        }
        // 判断缓冲区中是否有字符，如果有就返回，没有字符返回null
        if (stringBuilder.length() != 0) {
            return stringBuilder.toString();
        }
        // 如果读取到的是-1，说明文件结束，循环退出
        return null;
    }

    /**
     * @Description 自定义关闭资源方法
     * @Author wdq
     * @Params []
     * @Return void
     * @Create 2019-08-10 22:45
     */
    public void close() throws IOException{
        if (reader != null) {
            reader.close();
        }
    }

    // main方法测试
    public static void main(String[] args) throws  IOException{
        MyReaderLineTest myReaderLineDemo = new MyReaderLineTest(new FileReader("/Users/wdq/Desktop/reader.txt"));
        // 调用自定义读取一行的方法
        String line = null;
        while ((line = myReaderLineDemo.readerLine()) != null) {
            System.out.println(line);
        }
        myReaderLineDemo.close();
    }
}

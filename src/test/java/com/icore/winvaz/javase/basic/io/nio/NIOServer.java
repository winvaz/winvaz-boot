package com.icore.winvaz.javase.basic.io.nio;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.util.Scanner;

/**
 * @Author wdq
 * @Create 2019-04-08 14:15
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        new Thread(() -> {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Java零拷贝
     * java nio提供的FileChannel提供了map()方法，该方法可以在一个打开的文件和MappedByteBuffer之间建立一个虚拟内存映射，
     * MappedByteBuffer继承于ByteBuffer，类似于一个基于内存的缓冲区，只不过该对象的数据元素存储在磁盘的一个文件中；
     * 调用get()方法会从磁盘中获取数据，此数据反映该文件当前的内容，调用put()方法会更新磁盘上的文件，并且对文件做的修改对其他阅读者也是可见的
     */
    @Test
    public void mappedByteBufferTest() throws IOException {
        File file = new File("/Users/wdq/Desktop/writer.txt");
        long len = file.length();
        byte[] bytes = new byte[(int) len];
        MappedByteBuffer mappedByteBuffer = new FileInputStream(file).getChannel().map(FileChannel.MapMode.READ_ONLY,
                0, len);
        for (int offset = 0; offset < len; offset++) {
            byte b = mappedByteBuffer.get();
            bytes[offset] = b;
        }
        Scanner scanner = new Scanner(new ByteArrayInputStream(bytes)).useDelimiter(" ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next() + " ");
        }
    }

    /**
     *  Channel-to-Channel传输
     *  经常需要从一个位置将文件传输到另外一个位置，FileChannel提供了transferTo()方法用来提高传输的效率
     */
    @Test
    public void channelTransferTest() throws IOException {
        String[] files = new String[1];
        files[0] = "/Users/wdq/Desktop/writer.txt";
        for (int i = 0; i < files.length; i++) {
            FileInputStream fis = new FileInputStream(files[i]);
            FileChannel channel = fis.getChannel();
            channel.transferTo(0, channel.size(), Channels.newChannel(System.out));
            channel.close();
            fis.close();
        }
    }
}

package com.icore.winvaz.javase.basic.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/1/22 11:09
 * @Version 1.0.0
 */
public class PathsFilesTest {

    public static long checksumInputStream(Path filename) throws IOException {
        try (InputStream in = Files.newInputStream(filename)) {
            CRC32 crc = new CRC32();
            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumBufferedInpustream(Path filename) throws IOException {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(filename))) {
            CRC32 crc = new CRC32();
            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumRandomAccessFile(Path filename) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
            long length = file.length();
            CRC32 crc = new CRC32();
            for (long i = 0; i < length; i++) {
                file.seek(i);
                int c = file.readByte();
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumMappedFile(Path filename) throws IOException {
        try (FileChannel channel = FileChannel.open(filename)) {
            CRC32 crc = new CRC32();
            int length = (int) channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int i = 0; i < length; i++) {
                int c = buffer.get(i);
                crc.update(c);
            }
            return crc.getValue();
        }
    }


    public static void main(String[] args) throws IOException {
        /**
         *  Path表示的是一个目录名序列，其后还可以跟着一个文件名。路径中的第一个部件可以是根部件。
         *  以根部件开始的路径是绝对路径；否则，就是相对路径
         */
        /*
        Path absolute = Paths.get("/Users", "wendingquan");
        System.out.println(absolute); // /Users/wendingquan
        Path relative = Paths.get("com", "icore", "winvaz");
        System.out.println(relative); // com/icore/winvaz
        // System.out.println(System.getProperties());
        String property = System.getProperty("user.dir");
        Path basePath = Paths.get(property);
        System.out.println(basePath);
        System.out.println(basePath.resolve(relative));
        System.out.println(basePath.resolve(absolute));
        // 创建兄弟路径
        // System.out.println(basePath.resolveSibling("temp"));
        System.out.println(basePath.relativize(relative));
        */

        /**
         *  读写文件
         *      Files类可以使得普通文件操作变得快捷。
         */
        /*
        // 读文件转字节
        byte[] bytes = Files.readAllBytes(Paths.get("ran.txt"));
        // 文件当做字符串读入
        String s = new String(bytes, StandardCharsets.UTF_8);
        // 写出字符串
        // Files.write(Paths.get("ran.txt"), s.getBytes(StandardCharsets.UTF_8));
        // 文件当做行序列读入
        // List<String> lines = Files.readAllLines(Paths.get("ran.txt"));
        // 指定文件追加内容
        Files.write(Paths.get("ran.txt"), s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        */

        // 测试
        System.out.println("Input Stream:");
        long start = System.currentTimeMillis();
        Path filename = Paths.get(args[0]);
        long crcValue = checksumInputStream(filename);
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + "milliseconds");
        // .....
    }
}
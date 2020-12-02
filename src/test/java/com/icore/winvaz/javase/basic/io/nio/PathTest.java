package com.icore.winvaz.javase.basic.io.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/12/1 11:21
 * @Version 1.0.0
 */
public class PathTest {
    public static void main(String[] args) throws IOException {
        //在传统java.io中， 文件和目录都被抽象成File对象， 即 File file = new File(".");
        //NIO.2中则引入接口Path代表与平台无关的路径，文件和目录都用Path对象表示
        //通过路径工具类Paths返回一个路径对象Path
        Path path = Paths.get(".");
        System.out.println("path里包含的路径数量：" + path.getNameCount());
        System.out.println("path的根路径： " + path.getRoot());
        //path的绝对路径
        //对比传统java.io, 取绝对路径 file.getAbsoluteFile()
        Path absolutePath = path.toAbsolutePath();
        System.out.println("path的绝对路径：");
        System.out.println(absolutePath);
        System.out.println("absolutePath的根路径： " + absolutePath.getRoot());
        System.out.println("absolutePath里包含的路径数量：" + absolutePath.getNameCount());
        System.out.println(absolutePath.getName(3));
        //以多个String来构建path
        Path path2 = Paths.get("g:", "publish", "codes");
        System.out.println(path2);

        //将传统io读写文件高度封装之后，在NIO.2中拷贝文件只需要调用File工具类的copy()方法
        Files.copy(Paths.get("tmp.txt"), new FileOutputStream("tmp2.txt"));
        //是否为隐藏文件
        System.out.println("tmp.txt是否为隐藏文件: "+ Files.isHidden(Paths.get("tmp.txt")));
        //一次性读取所有行 , 需要指定编码规则
        List<String> lines = Files.readAllLines(Paths.get("tmp.txt"), Charset.forName("gbk"));
        System.out.println(lines);
        //文件大小
        System.out.println("tmp.txt文件大小为： "+Files.size(Paths.get("tmp.txt")));
        List<String> poem = new ArrayList<>();
        poem.add("海阔凭鱼跃");
        poem.add("天高任鸟飞");
        //直接将字符串数组写入文件
        Files.write(Paths.get("tmp.txt"), poem, Charset.forName("gbk"));
    }
}

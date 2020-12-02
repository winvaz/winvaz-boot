package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

/**
 * 3.	File类
 * 不属于流对象，作用：将机器上的路径和目录封装成一个对象，File对象，提供了很多的方法和成员变量，让我们操作目录和路径
 * 目录就是文件夹，路径。对于类中的方法熟知，File类需要和IO流对象配合使用
 */
public class FileTest {

    /**
     * 4.	File类的静态成员变量
     * * 一共提供的4个变量，掌握的只有2个,跨平台
     * * 	static String separator  结果\ Windows目录分隔符  Linux下(/)
     * * 	static String pathSeparator 结果 ; Windows下的路径与路径之间的分隔符 Linux :
     */
    @Test
    public void test() {
        System.out.println(File.separator); // Mac和Linux(/)，Windows(\)
        System.out.println(File.pathSeparator); // Mac和Linux(:)，Windows(;)
    }

    /**
     * 5.	File类的构造方法
     * 	File(String pathname) 将一个字符串的路径，封装成File对象.只负责把路径封装成对象，
     * 至于路径有没有，够造方法不考虑。（参数写到文件夹，写到文件也行）
     * 	File(String parent,String child)传递两个，字符串格式父路径，字符串格式子路径
     * 	File(File parent,String child)传递两个，File类型父路径，字符串格式子路径
     */
    @Test
    public void test1() {
        //带有两个参数的，一个File类型父父路径，字符串子路径
        File parent = new File("/Users");
        File son = new File(parent, "/wdq");
        System.out.println("son:" + son);
        //带有两个String的参数，传递字符串父路径，字符串子路径
        File two = new File("/Users", "/wdq");
        System.out.println("two:" + two);
        //带有一个String类型参数的构造方法，传递字符串的路径
        File one = new File("/Users/wdq/Documents/");
        System.out.println("one:" + one);
    }

    /**
     * 6.	File类的创建功能
     * 	boolean createNewFile()创建新文件，创建成功返回true,
     * 所创建的文件是File构造方法中封装的文件，文件已经存在，不会创建，只能创建文件
     * 	boolean mkdir()创建目录，文件夹，创建成功返回true,
     * 创建的是File构造方法中封装的路径,目录存在，不在创建了
     * 	boolean mkdirs()创建多级目录
     */
    @Test
    public void test2() {
        // File类的创建文件功能 boolean createNewFile
        File file = new File("/Users/wdq/Documents/NewFiles");
        /*
        try {
            boolean newFile = file.createNewFile();
            System.out.println(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("创建文件失败!");
        }
        */
        //File类的创建文件夹功能boolean mkdir()
        //请你使用mkdirs
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }

    /**
     * File类的删除方法
     * <p>
     * 	boolean delete()删除File构造方法中封装的路径，成功返回true，不走回收站。删除目录必须保证目录是空的
     * 	void deleteOnExit()延迟删除，等待JVM退出之前，在删除
     */
    @Test
    public void test3() {
        File file = new File("/Users/wdq/Documents/NewFiles");
        boolean delete = file.delete();
        System.out.println(delete);
    }

    /**
     * 8.	File类的判断方法
     * 	boolean exists()判断File构造方法中封装的路径是否存在，如果存在返回true
     * 	boolean isAbsolute()判断File构造方法中封装的路径，是不是绝对路径，如果是绝对路径返回true
     * 	boolean isDirectory()判断File构造方法中封装的是目录还是文件，如果是目录返回true
     * 	boolean isFile()判断File构造方法中封装的是不是文件，如果是文件返回true
     * 	boolean isHidden()判断File构造方法中封装的路径是不是隐藏属性，如果是发回true
     */
    @Test
    public void test4() {
        File file = new File("/Users/wdq/Documents/NewFiles/");
        //判断File构造方法封装的路径是否存在 boolean exists()
        // boolean exists = file.exists();
        // System.out.println(exists); // false

        //判断File构造方法封装的路径是否是绝对路径
        // boolean absolute = file.isAbsolute();
        // System.out.println(absolute); // true

        //判断File构造方法中封装是不是目录，是返回true boolean isDirectory()
        // boolean directory = file.isDirectory();
        // System.out.println(directory); // 存在返回true

        //判断File构造方法中封装的是不是文件 是返回true boolean isFile()
        // boolean isFile = file.isFile();
        // System.out.println(isFile); // false

        //判断File构造方法中封装的是隐藏属性 是返回true boolean isHidden()
        // boolean hidden = file.isHidden();
        // System.out.println(hidden); // false
    }

    /**
     * 9.	File类的获取方法get开头
     * 	String getName()获取的是File构造方法中封装的路径的最后那个部分的名字
     * 	String getParent()获取File构造方法中封装的路径的父路径,返回String
     * 	File getParentFile()获取File构造方法中封装的路径的父路径，返回File对象
     * 	String getPath()将你File封装的路径，变成字符串
     * 	File getAbsoluteFile()获取File构造方法封装的路径的绝对路径，返回File对象
     * 	String getAbsolutePath()获取File构造方法封装的路径的绝对路径，返回String对象
     */
    @Test
    public void test5() {
        File file = new File("/Users/wdq/Documents/NewFiles/");

        // 获取名字 String getName()
        // String name = file.getName();
        // System.out.println(name); // NewFiles

        // 获取File构造方法中封装的路径的父路径  String getParent  File getParentFile
        // File parentFile = file.getParentFile();
        // System.out.println(parentFile); // /Users/wdq/Documents

        // 将File构造方法中封装的路径，变成字符串
        String string = file.toString();
        System.out.println(string);
        String path = file.getPath();
        System.out.println(path);

        // 获取File构造方法中封装的路径的绝对路径 File getAbsoluteFile  String getAbsolutePath
        File absoluteFile = file.getAbsoluteFile();
        System.out.println(absoluteFile); // /Users/wdq/Documents/NewFiles
        System.out.println(absoluteFile.exists()); // true
    }

    /**
     * 10.	File的获取方法，包含list
     * 	static File[] listRoots()返回系统的根目录
     * 	String[] list()获取File对象中封装的路径下的全部目录和文件夹，返回String数组
     * 	File[] listFiles()获取File对象中封装的路径下的全部目录和文件夹，返回File数组,返回的是封装路径下的全部内容的全路径，可以继续使用File类的方法
     * 	File[] listFileFileFilter filter)参数是文件过滤器，可以实现过滤想要的文件
     */
    @Test
    public void test6() {
        File file = new File("/Users/wdq/Documents/");

        // static File[] listRoots()反系统根，目录
        /*
        File[] files = File.listRoots();
        for (File file: files) {
            System.out.println(file); // /
        }
        */

        // String[] list() File[] listFiles()获取File构造方法封装的路径下的全部内容
        /*
        String[] list = file.list();
        for (String s: list) {
            System.out.println(s); // IntelliJetBrains 隐藏文件也会显示
        }
        */
        /*
        File[] files = file.listFiles();
        for (File listFile: files) {
            System.out.println(listFile); // /Users/wdq/Documents/IntelliJetBrains
        }
        */

        // File[] listFiles(FileFilter filter) 可以过滤文件
        // File[] listFiles(FileFilter filter)传递接口的实现类对象
        /*File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java");
            }
        });*/
        File[] files = file.listFiles((pathname -> {
            return pathname.getName().endsWith(".java");
        }));
        for (File listFile : files) {
            System.out.println(listFile);
        }
    }

    /**
     * 11.	File类的其他方法
     * boolean renameTo(File file)对File构造方法中的封装的文件重命名，命名成功返回true，如果修改后的路径不同，出现剪切效果
     * long lastModified()获取File构造方法中封装的文件的最后修改时间的毫秒值
     */
    @Test
    public void test7() {
        //对文件重命名
        File file = new File("/Users/wdq/Documents/old.txt");
        /*
        File newFile = new File("/Users/wdq/Documents/NewFiles/new.txt");
        boolean rename = oldFile.renameTo(newFile);
        System.out.println(rename); // true
        */

        //获取文件的最后修改时间的毫秒值
        long time = file.lastModified();
        // 毫秒值转日期对象
        Date date = new Date(time);
        System.out.println(date);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String format = dateFormat.format(date);
        System.out.println(format);
    }
}
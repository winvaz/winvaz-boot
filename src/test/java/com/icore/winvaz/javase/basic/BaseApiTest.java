package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.io.Console;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * @Deciption 基础API
 * @Author wdq
 * @Create 2020/4/9 21:36
 * @Version 1.0.0
 */
public class BaseApiTest {
    /**
     * Scanner：
     * 构造方法最常用为  Scanner(InputStream source)  我们通常使用System.in为其提供这个值
     * <p>
     * 常用方法：
     * nextInt()	将输入的数据转换成int类型
     * nextLine()  将一行当中的内容转换成String类型，不包括结尾处的行分隔符
     * next()	将一行当中的内容转换成String类型
     */
    /**
     * main方法是静态的：
     * <p>
     * public：访问权限为公共
     * static：静态：存放在静态区的。供JVM直接调用的。不需要创建对象。
     * void：返回值为空：JVM调用，不需要返回值
     * main：方法名：JVM在调用时，会寻找main这个字符串的方法.所以如果写成其他名字的话，能编译，不能运行。
     * String[] args:是main函数接收的参数：可以自己其形参名称，但是不建议自己起
     * <p>
     * 早期使用main方法来接收键盘录入的数据，后期出现了更优秀，更简单的接收方法。比如说Scanner。于是这种方式就不再使用了。
     * main函数对其他函数不产生影响。
     */
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // 从键盘接受数据

        /**
         * next()
         *      1.一定要读取到有效字符后才可以结束输入。
         *      2.对输入有效字符之前遇到的空白，会自动将其去掉。
         *      3.只有输入有效字符后才将其后面输入的空白作为分隔符或结束符。
         *      4.不能得到带有空格的字符串。
         *
         *  nextLine()
         *      1.以Enter为结束符，也就是说返回输入回车之前的所有字符。
         *      2.可以获得空白。
         */
        // next()方式接受字符串
        /*
        System.out.println("next()方式接受："); // 1 2 3
        if (scanner.hasNext()) {
            // 只有1被输出
            System.out.println("输入的数据为：" + scanner.next()); // 1
        }
        */
        /*
        // nextLine()方式接受字符串 标识
        System.out.println("nextLine()方式接受："); // 1 2 3
        // 判断是否还有输入
        if (scanner.hasNextLine()) {
            // 只有1被输出
            System.out.println("输入的数据为：" + scanner.nextLine()); // 1 2 3
        }
        scanner.close();
        */
        /*
        Console console = System.console();
        String s = console.readLine("User Name：", scanner.nextLine());
        System.out.println(s);
        char[] chars = console.readPassword("User Password：", scanner.nextLine());
        System.out.println(new String(chars));
        */
        int[] ints = {1, 42, 51, 62, 8, 94, 23, 13, 40, 5};
        Date dt = new Date();
        Random random = new Random(dt.getSeconds());
        int len = ints.length;
        for (int i = 0; i < len; i++) {
            int pos = (int) (random.nextDouble() * (len - i + 1) + i) - 1;
            int temp = ints[i];
            ints[i] = ints[pos];
            ints[pos] = temp;
        }
    }

    /**
     * Object:
     * equals(Object obj) :指示其他某个对象是否与此对象“相等”(实际上是相同)。Object中对比的为两个对象的地址。但是其子类可以重写这个方法，
     * 比较其他的内容(比如String重写了equals方法，比较的是具体的字符创值是否相等)
     * 比较两个对象或者基本数据类型时，使用的为：==.但凡使用==比较的对象，均比较的是其地址值。
     * hashCode() ：  返回该对象的哈希码值。相当于该对象在内存中的地址，是通过哈希算法，加入一些参数完成的对应值。在同一个程序当中，其哈希值应该相同
     * toString() ： 返回该对象的字符串表示。
     * clone() ： 创建并返回此对象的一个副本。相当于复制一份
     * finalize() ： 当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。(手动调用之后，相当于告诉垃圾回收器该回收他了，快去！)
     * getClass() ： 返回此 Object 的运行时类。(返回正在运行这个字节码对象，在反射时候讲)
     */
    @Test
    public void test() {
        Object object1 = new Object();
        Object object2 = new Object();

        System.out.println(object1 == object2); //false 哈希值不同（地址值不同），代表两个对象的不同引用
        System.out.println(object1.equals(object2)); //false 哈希值不同（地址值不同），代表两个对象的不同引用
        System.out.println("============================");

        System.out.println(1 == 1); // true
        //System.out.println(1.equals(1)); //错误，常量无方法。
        System.out.println("============================");

        //字符串特殊的比较
        //这种定义及赋值方式实际上指的是同样一个对象。这是特殊的！！！
        String s = "abc"; // String string = "abc";
        String s2 = "abc"; // String string = "abc";
        System.out.println(s == s2);// true 这种做法是非常特殊的做法，只有显式初始化字符串可以有这样的结果
        System.out.println(s.equals(s2)); // true
        System.out.println("======================");

        String string = new String("abc");
        String string2 = new String("abc");
        System.out.println(string == string2); // false 用双等号比较的永远是地址值。
        System.out.println(string.equals(string2)); // true
        System.out.println("=====================");

        String string3 = string;
        System.out.println(string == string3); // true 地址值相等
        System.out.println(string.equals(string3)); //true

        System.out.println("=========================");
        // hash码与toString
        System.out.println(object1.hashCode());

        System.out.println(Integer.toHexString(object1.hashCode())); // 哈希值转换成十六进制 683dbc2c
        // getClass().getName() + '@' + Integer.toHexString(hashCode())
        System.out.println(object1.toString()); // java.lang.Object@683dbc2c
    }

    /**
     * Math:
     * 构造函数为私有化，不能创建实例对象。
     * 但是不影响其使用，因为所有的成员均为静态成员
     * <p>
     * 当我们在查询一个类的时候：
     * 1、看类说明(所属包以及出现版本)
     * 2、构造方法
     * 3、字段属性
     * 4、普通方法（先看静态方法，再看非静态方法）
     */
    @Test
    public void test1() {

        // Math类的静态常量
        System.out.println(Math.E);
        System.out.println(Math.PI);
        System.out.println("=======");

        // Math.abs()求绝对值
        System.out.println(Math.abs(5)); // 5
        System.out.println(Math.abs(-5)); // 5
        System.out.println("======");

        // Math.ceil(double d)返回大于或等于该参数的最小整数(向上取整)
        System.out.println(Math.ceil(1.2)); // 2.0
        System.out.println(Math.ceil(-1.2)); // -1.0
        System.out.println(Math.ceil(9.9)); // 10
        System.out.println(Math.ceil(-9.9)); // -9.0
        System.out.println("========");

        // floor(double d)返回小于或等于该参数的最大整数(向下取整)
        System.out.println(Math.floor(1.2)); // 1.0
        System.out.println(Math.floor(-1.2)); // -2.0
        System.out.println(Math.floor(9.9)); // 9.0
        System.out.println(Math.floor(-9.9)); // -10.0
        System.out.println("=========");

        // Math.max(int x, int y)取最大值
        System.out.println(Math.max(10, 20)); // 20
        System.out.println(Math.min(10, 20)); // 10

        // pow(double a, double b)幂运算a的b次方
        System.out.println(Math.pow(2, 3)); // 8.0

        // round(double d)四舍五入
        System.out.println(Math.round(2.6)); // 3
        System.out.println(Math.round(-2.6)); // -3
        // 面试题
        System.out.println(Math.round(11.5)); // 12
        System.out.println(Math.round(-11.5)); // -11

        // random()伪随机数,随机生成大于等于0.0小于1.0之间的数
        System.out.println(Math.random());

        /**
         Math随机数方法
         //随机出(0-100]的数。
         通过这个随机数做一个游戏：猜数字
         Scanner类:

         目前我们要通过键盘录入int类型数据,必须按照如下步骤：
         A:导包: 导包时在文件第一行导入(注释不算)
         import java.util.Scanner;
         B:创建对象,封装键盘录入
         Scanner $$$$ = new Scanner(System.in);
         C:调用方法,获取数据
         int number = $$$$.nextInt();

         自己写出一个随机数游戏。
         */
        /*
        int x = (int)(Math.random() * 100) + 1;
        Scanner scanner = new Scanner(System.in);
        while (true) {
          int nextInt = scanner.nextInt();
          if (nextInt == x) {
            System.out.println("恭喜，猜中了！！");
            break;
          } else if (nextInt < x) {
            System.out.println("抱歉，猜小了，请继续》》》");
            continue;
          } else {
            System.out.println("抱歉，猜大了，请继续》》》");
            continue;
          }
        }
        */

        int num = 1;
        double random = Math.random();
        if (random < 0.1D) {
            random += 0.1D;
        }
        for (int i = 0; i < 2; ++i) {
            num *= 10;
        }
        System.out.println((int) (random * num));

        // @see TypeConvertTest.java
        /*
        Random random = new Random();
        int i = random.nextInt();
        System.out.println(i);
        */
    }

    @Test
    public void test2() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;

        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false"); // false
        }

        Float m = Float.valueOf(a);
        Float n = Float.valueOf(b);

        if (m.equals(n)) {
            System.out.println("true");
        } else {
            System.out.println("false"); // false
        }

        Double x = new Double(a);
        Double y = new Double(b);

        if (x.equals(y)) {
            System.out.println("true");
        } else {
            System.out.println("false"); // false
        }
        System.out.println("==============");
        /**
         但是，浮点数使用==或equals()判断都是错误的
         浮点类型的数据进行等值判断，基本数据类型禁止使用==，包装数据类型禁止使用equals()
         因为浮点数采用"尾数+阶码"的编码方式，类似于科学计数法的"有效数+指数"的表示方式。具体原理参考《码出高效》
         */
        // 正确为指定一个误差范围，两个浮点数的差值在此范围内，则认为相等
        float e = 1.0f - 0.9f;
        float f = 0.9f - 0.8f;
        // 误差范围
        float diff = 1e-6f;
        if (Math.abs(e - f) < diff) {
            System.out.println("true"); // true
        }
    }

    /**
     * System类：
     * * out:标准输出流  比如  System.out.println();
     * * in:标准输入流  比如Scanner(System.in);
     * * err:错误输出流 比如  System.err.println(); 涉及到线程问题。并不是每次都是按顺序输出的
     * *
     * * 一般方法：
     * * 	arraycopy(Object src, int srcPos, Object dest, int destPos, int length)：
     * * 	从指定源数组中复制一个数组，复制从指定的位置开始，到目标数组的指定位置结束。
     * * 		参数：
     * src - 源数组。
     * srcPos - 源数组中的起始位置。
     * dest - 目标数组。
     * destPos - 目标数据中的起始位置。
     * length - 要复制的数组元素的数量。
     * <p>
     * * 	currentTimeMillis() :距协调世界时的毫秒值(当前毫秒值)
     * *  exit(int status) ：终止当前正在运行的 Java 虚拟机
     * *  gc()
     * *  Object类 finalize()
     * *  解析：
     * *  	1、gc():程序员调用          finalize()：垃圾回收器调用的
     * *  	2、在调用完gc()方法之后做一些努力来回收未用对象。但是最终是否调用finalize()方法，仍然视垃圾回收器的运行结果
     * *  	3、调用gc()方法不可以立即回收内存（一般情况下会产生这样的效果）
     */
    @Test
    public void test3() {
        String[] srcBytes = new String[]{"KO", ".", "1"};
        String[] destBytes = new String[]{"Welcome ", "To ", "Hello ", "World ", "!"};
        System.arraycopy(srcBytes, 0, destBytes, 2, srcBytes.length);
        // 数组直接打印就是个地址值，遍历打印数组
        for (String s : destBytes) {
            System.out.print(s); // Welcome To KO.1
        }
        //获取运行循环前的毫秒值
        System.out.println(System.currentTimeMillis());
    }

    /**
     * BigDecimal 
     */
    @Test
    public void test4() {
        // 禁止使用BigDecimal构造函数的方式把double类型的数据转换成BigDecimal对象，这样会损失精度
        BigDecimal bigDecimal = new BigDecimal(1.0);

        // 推荐方式一
        BigDecimal bigDecimal1 = BigDecimal.valueOf(1.0);
        System.out.println(Objects.equals(bigDecimal, bigDecimal1)); // false

        // 推荐方式二
        BigDecimal bigDecimal2 = new BigDecimal("1.0");
        System.out.println(Objects.equals(bigDecimal, bigDecimal2)); // false
        System.out.println(Objects.equals(bigDecimal1, bigDecimal2)); // true
        BigDecimal bigDecimal3 = new BigDecimal("0.9");
        BigDecimal bigDecimal4 = new BigDecimal("0.8");

        BigDecimal x = bigDecimal2.subtract(bigDecimal3);
        BigDecimal y = bigDecimal3.subtract(bigDecimal4);
        if (x.equals(y)) {
            System.out.println("true"); // true
        }

        // System.out.println(bigDecimal.divide(3)); // Error 不兼容的类型，int无法转换成java.Math.BigDecimal

        BigDecimal a = new BigDecimal(5);
        BigDecimal b = new BigDecimal(3);
        // BigDecimal的两整数相除，能被整除得出结果。不能被整除抛出ArithmeticException算术异常
        System.out.println(b.divide(a)); // 0.6
        // 5/3，除不尽，抛异常
        // Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        // System.out.println(a.divide(b));
    }

    /**
     * 加
     */
    public static BigDecimal add(double v1, double v2) {// v1 + v2
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    /**
     * 减
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    /**
     * 乘
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    /**
     * 除
     */
    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        // 2 = 保留小数点后两位   ROUND_HALF_UP = 四舍五入
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
    }
}

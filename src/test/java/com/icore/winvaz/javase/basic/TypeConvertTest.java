package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

/**
 * 1:关键字(掌握)
 * (1)被Java语言赋予特殊意义的单词。
 * (2)特点：
 * Java语言中的所有关键字全部是小写。
 * (3)注意事项：
 * goto和const作为保留字(在JDK的新版本中可能提升为关键字)存在。没有被使用。
 * <p>
 * 2:标识符(掌握)
 * (1)就是给类,变量,方法起名字。
 * (2)组成规则：
 * 由数字0-9,英文大小写字母,$以及_组成。
 * (3)注意事项：
 * A:只能有组成规则规定的内容组成。
 * B:不能以数字开头。
 * C:不能是Java中的关键字。
 * D:区分大小写。
 * (4)常用的命名规则：见名知意,驼峰命名
 * A:包(文件夹，用于区分同名的类)
 * 全部小写。如果多级包，用.分开。
 * 举例：
 * com	一级包
 * cn.icore	二级包
 * 注意：www.icore.cn(域名反写)
 * B:类和接口
 * 如果是一个单词,首字母大写。
 * 举例：Demo,Test,Student
 * 如果是多个单词,每个单词的首字母大写。大驼峰式
 * 举例：HelloWorld,StudyJava
 * C:变量和方法名
 * 如果是一个单词,首字母小写。
 * 举例：main,name,age
 * 如果是多个单词,从第二个单词开始每个单词的首字母大写。小驼峰式
 * 举例：showName(),studentName
 * D:常量
 * 全部大写，如果多个单词组成，用_连接。
 * 举例：PI,STUDENT_MAX_AGE
 * <p>
 * 3：注释(掌握)
 * (1)就是对程序的解释性文字。
 * (2)注释的分类：
 * A:单行注释
 * a:以//开头，以回车结束。
 * b:单行注释是可以嵌套的。
 * B:多行注释
 * a:以/*开头，以*结束。
 * b:多行注释是不可以嵌套的。
 * C:文档注释(了解)
 * 将来被javadoc工具解析，生成一个说明书。
 * (3)注释的作用：
 * A:解释程序，提高程序的阅读性。
 * B:可以调试错误。
 * (4)把HelloWorld案例用注释改版。
 * 建议先写思路，在写代码体现。
 * <p>
 * 4：常量(理解)
 * (1)在程序的运行过程中，其值是不可以发生改变的量。
 * (2)常量的分类：
 * A:字面值常量
 * a:整数常量
 * 12,-23
 * b:实数（这里专指小数）常量
 * 12.5,-65.43
 * c:字符常量
 * 'a','A','0'
 * d:字符串常量
 * "hello"
 * e:布尔常量
 * true,false
 * d:空常量(后面讲，不是类型，是一个数值)
 * null
 * B:自定义常量(后面讲)
 * (3)常量可以直接被输出。
 * <p>
 * 5：进制(理解)
 * (1)是一种进位的方式。X进制，表示逢x进1。
 * (2)Java中整数常量的表示
 * int a=15；
 * int a=0b1111；
 * int a=017;
 * int a=0xF;
 * A:二进制 由0,1组成。以0b开头。JDK7以后的新特性。
 * B:八进制 由0-7组成。以0开头。
 * C:十进制 由0-9组成。默认就是十进制。
 * D:十六进制 由0-9，A-F(不区分大小写)组成，以0x开头。
 * (3)进制转换：
 * A:其他进制到十进制
 * 系数：就是每一位上的数据。
 * 基数：X进制，基数就是X。
 * 权：在右边，从0开始编号，对应位上的编号即为该位的权。
 * 结果：把系数*基数的权次幂相加即可。
 * <p>
 * 二进制：1011
 * 十进制：
 * 八进制：74
 * 十进制：
 * 十六进制：a3
 * 十进制：
 * <p>
 * B:十进制到其他进制
 * 除基取余，直到商为0，余数反转。
 * <p>
 * 十进制：60
 * 结果：
 * 二进制
 * 八进制
 * 十六进制
 * <p>
 * C:快速转换
 * a:8421码。
 * b:二进制--八进制(3位组合)
 * c:二进制--十六进制(4位组合)
 * 二进制转换成十进制，由于通常只计算少位数的转换，
 * 所以直接采用8421法即可
 * 0001 1
 * 0010 2
 * 0100 4
 * 1000 8
 * <p>
 * 6：变量(掌握)
 * (1)程序的运行过程中，在指定范围内发生改变的量。
 * (2)格式：
 * 数据类型 变量名=初始化值;
 * <p>
 * 变量的定义格式：
 * 数据类型 变量名;
 * 变量名=初始化值;
 * 数据类型 变量名=初始化值;
 * 数据类型 变量1,变量2,变量3=10;
 * <p>
 * 举例：
 * 方式1：    byte b=10;
 * <p>
 * 方式2：    byte b;
 * b=10;
 * <p>
 * 7：数据类型(掌握)
 * (1)分类
 * 基本类型：4类8种。
 * 引用类型：类，接口，数组。(了解)
 * (2)基本类型
 * 整型：
 * byte 1
 * short 2
 * int 4
 * long 8
 * 浮点型：
 * float 4
 * double 8
 * 字符型：
 * char 2
 * 布尔型：
 * boolean 不明确。可以认为是1个字节。
 * <p>
 * 注意：
 * 整数默认是int类型。long类型需要加L或者l后缀。
 * 浮点数默认是double类型。float类型需要加F或者f后缀。
 * (3)类型转换
 * A:boolean类型不参与转换。
 * B:隐式转换(从小到大)
 * byte,short,char--int--long--float--double
 * C:强制转换(从大到小)
 * 格式：
 * (数据类型)数据;
 * (4)面试题
 * byte b1=3;
 * byte b2=4;
 * byte b3=b1+b2; // 精度损失
 * byte b4=3+4; // 正常运行
 * 运算符暂留
 * 8：运算符(掌握)
 * (1)就是把常量和变量连接的符号，一般参与运算使用。
 * (2)分类：
 * 算术运算符
 * 赋值运算符
 * 关系运算符
 * 逻辑运算符
 * 位运算符
 * 三元运算符
 * (3)算术运算符
 * +,-,*,/,%,++,--
 * <p>
 * +：正号，加法，字符串连接符。
 * System.out.println("5+5="+5+5);//5+5=55
 * System.out.println(5+5+"=5+5");//10=5+5
 * <p>
 * %：取得余数
 * 左边如果大于右边，结果是余数。
 * 左边如果小于右边，结果是左边。
 * 左边如果等于右边，结果是0。
 * <p>
 * 正负号跟左边一致。
 * <p>
 * ++/--：
 * ++ 其实相当于把数据+1
 * <p>
 * 单独使用：
 * 在数据的前后，结果一致。
 * <p>
 * 参与操作使用：
 * 如果在数据的后边，数据先操作，在++/--
 * 如果在数据的前边，数据先++/--，再操作。
 * <p>
 * (4)赋值运算符
 * =,+=,-=,*=,/=,%=
 * <p>
 * int a = 10;
 * 把10赋值给int类型的变量a。
 * <p>
 * a += 20;
 * 把左边和右边的和赋值给左边。
 * <p>
 * 注意事项：
 * a = a + 20;
 * a += 20;
 * 结果是等价的，理解不是等价的。
 * <p>
 * 因为+=这种运算符，内含了强制类型转换功能。
 * 比如：
 * short s = 2;
 * <p>
 * s+=3;
 * 等价于
 * s = (short)(s+3);
 */
public class TypeConvertTest {
    /**
     * 类型转换
     * 自动类型转换(隐式类型转换)：
     * 小范围的类型转换成大范围的类型：
     * byte,short,char->int->long->float->double
     * 字节	 1       2       2       4      8        4         8
     * 1个字节(8位)
     * 强制类型转换：
     * 大范围的类型转换成小范围的类型：
     * 在变量前加(),里边放置要转换的数据类型
     * <p>
     * 当byte，short，char类型进行计算时，会提升为int类型
     */
    @Test
    public void test() {
        byte b = 100;
        short s = 200;
        int i = 400;
        long l = 800L;

        i = i + b; //b会自动提升为int类型进行运算
        // b = b + 4; // 报错
        b = (byte) (b + 4);//强制类型转换，强制将(b+4)的结果转换为byte类型，再赋值给b。

        byte b1 = 3, b2 = 4, b4;
        // b4=b1+b2; // 报错，变量在计算时自动提升数据类型
        b = 3 + 4;


        // 当一个byte类型与一个int类型进行计算时，类型会自动提升为int类型
        // byte b6 = b + 10; // 可能损失精度
        byte b3 = 10;
        // 当一个byte类型与一个byte类型进行计算时，类型会自动提升为int类型
        // byte b4 = b + b3; // 可能损失精度

        char c = 'a';
        System.out.println(c + 1); // 98
        char c1 = (char) (c + 1);
        System.out.println(c1); // b

        //显示给数值：可以编译通过(出了long超出int范围的情况)
        byte b5 = 100;  //显示赋值编译时可以直接判断是否在范围，在范围赋值成功
        //如果有计算，则不能成功，因为提升成了int类型

        // float f2 = 100.0;//显示赋值如果是浮点型，则会默认必须为double类型接受，否则报错，如果给其赋值需要加f

        float f = 100.0F;
        double d = 100.0;

        // 小给大
        // s = b; // byte类型值赋值给short 安全
        // i = s; // short类型值赋值给int 安全
        // l = i; // int类型值赋值给long 安全

        // 大给小
        // Error java:不兼容的类型，从long转换到int可能会有损失
        // i = l; // long类型值赋值给int 不安全
        // s = i; // 从int转换到short可能会有损失 不安全
        // b = s; // 从short转换到byte可能会有损失 不安全

        // 强制类型转换
        // i = (int)l; // 800
        // s = (short)i; // 800
        // b = (byte)s; // 32 错误 有可能出现负数，因为最高符号位可能为1

        System.out.println(s);
        System.out.println(i);
        System.out.println(b);
        System.out.println("================");
        TypeConvertTest.characterCode();
    }

    /**
     * 字符编码表：
     * 对于字符，如果不进行计算即打印的为字符本身，经过计算会提升成int类型，打印对应的数字
     * <p>
     * 是按照ASCII码表顺序进行一一对应的
     * <p>
     * 计算当中存储的是码表本身，以及对应字符的数字的二进制表现
     * 在介绍IO时，会给大家介绍ISO-8859-1以及GBK/GB2312以及UTF-8
     */
    public static void characterCode() {
        System.out.println('a'); // a
        System.out.println('a' + 0); // 97，计算之后才会提升为int类型
        System.out.println((char) ('a' + 1)); // b，计算之后才会提升为int类型

        System.out.println('0' + 0); // 字符0=48
        System.out.println((char) 0); // 未知
        System.out.println((char) ('A' + 0)); // A=65
        System.out.println((char) ('A' + 1)); // A=65 + 1 = B
    }

    /**
     * 基本数据类型包装对象：
     * * byte : Byte
     * * short :Short
     * * int :Integer
     * * long :Long
     * * char :Character
     * * boolean :Boolean
     * * double :Double
     * * float :Float
     * *
     * * Integer:
     * * 	构造方法：
     * * 	Integer(int value)
     * * 	Integer(String value)
     * * 	字段：
     * * 	MAX_VALUE：Integer类型能存储的最大数
     * * 	MIN_VALUE:Integer类型能存储的最小数
     * * 	方法：
     * * 	 equals()  //重写Object的equals()方法比较的为两个整数类型的值
     * *   toString()  //重写Object的toString()方法
     * *   static int parseInt(String s)  // 将字符串参数作为有符号的十进制整数进行解析。
     * *   static Integer valueOf(String s) //返回保存指定的 String 的值的 Integer 对象
     * *
     * *   static String toBinaryString(int i) //  以二进制（基数 2）无符号整数形式返回一个整数参数的字符串表示形式。
     * static String toHexString(int i) // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
     * static String toOctalString(int i) //以八进制（基数 8）无符号整数形式返回一个整数参数的字符串表示形式。
     * <p>
     * 自动装箱和拆箱，1.5版本后的新特性
     *   装箱：将基本数据类型封装成对象 Integer xx = 10;
     *   拆箱：将封装成对象的基本数据类型，转成基本数据类型  xx+1
     */
    @Test
    public void test1() {
        Integer a = new Integer(12);
        Integer b = new Integer(12);
        System.out.println(a == b); // false
        //如果一个数字在一个字节的范围中，是始终被缓冲的，相当于在常量值一直都有。
        // 什么时候用到，直接调用。(一个字节的信息使用的频率十分频繁)
        Integer c = 127;
        Integer d = 127;
        System.out.println(c == d); // true
        Integer e = 128;
        Integer f = 128;
        System.out.println(e == f); // false
        System.out.println(e.equals(f)); // true
        Integer h = -128;
        Integer i = -128;
        System.out.println(h == i); // true
        System.out.println("======面试题分界线=========");

        Integer o = 100, j = 100, p = 150, y = 150;
        System.out.println(o == j); // true
        System.out.println(p == y); // false

        int q = 100, w = 100, r = 150, t = 150;
        System.out.println(q == w); // true
        System.out.println(r == t); // true

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        // int x = 10;
        // Integer x = new Integer(10);
        Integer x = 10; // 自动完成了  Integer i = new Integer(10);  自动装箱 将int类型自动转换成Integer进行包装

        //只有在调用方法时，必须使用Integer类型，其他时候可以自动相互转换
        int parseInt = Integer.parseInt("10");
        Integer valueOf = Integer.valueOf("10");
        int intValue = valueOf.intValue();
        System.out.println((parseInt + intValue)); //自动拆箱  valueOf.intValue() 将Integer自动转换成int类型进行计算
    }

    /**
     * Random类:
     * * 	构造方法：
     * * 	Random(): 此构造方法将随机数生成器的种子设置为某个值，该值与此构造方法的所有其他调用所用的值完全不同。
     * *  Random(long seed)：使用单个 long 种子创建一个新的随机数生成器。
     * *  通过构造方法的种子可以推断出随机数不随机，但是一般情况下已经满足了随机的需求
     * *  其他方法：
     * *  int	nextInt()  返回下一个伪随机数，它是此随机数生成器的序列中均匀分布的 int 值。
     * *  nextInt(int n) 返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值
     */
    @Test
    public void test2() {
        // Random random = new Random(10);
        // System.out.println(random.nextInt(2));

        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < 10; i++) {
            num = num * 10;
        }
        System.out.println((random * num));
    }
}
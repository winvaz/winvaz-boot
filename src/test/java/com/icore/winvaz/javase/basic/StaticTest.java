package com.icore.winvaz.javase.basic;

/**
 * static关键字：
 * 用于修饰成员（成员变量和成员函数）
 * 被修饰后的成员具备以下特点：
 * 随着类的加载而加载，优先于对象存在
 * 被所有对象所共享
 * 可以直接被类名调用
 * 使用注意
 * 静态修饰的变量一旦赋值便影响所有实例
 * 静态方法只能访问静态成员
 * 静态方法中不可以写this，super关键字
 * 主函数是静态的
 */

import org.junit.jupiter.api.Test;

/**
 * static:
 * 当一个属性需要被所有实例对象共享时，可以将这个属性使用static修饰
 * <p>
 * 静态修饰的变量一旦赋值便影响所有实例
 * 静态方法只能访问静态成员
 * 静态成员可以直接使用类型调用。也可以使用对象名调用，但是，建议使用类名调用。
 */

/**
 * javadoc:
 * Javadoc是JDK提供的一个生成文档的工具。
 * 必须配合文本注释(文档注释)
 *
 * @version @author
 * <p>
 * 对于类与类中的方法可以进行说明，生成网页形式的帮助文档
 * <p>
 * 最简单的使用方法：
 * javadoc -d doc -author -version Tools.java
 * <p>
 * 注意：
 * 1、生成文档的必须为java文件，而不能为class。
 * 2、需要生成文档的类必须为public修饰的或者是受保护的。
 */
/**
 * @author wdq
 * @create 2020/4/4 16:13
 */
public class StaticTest {
    /**
     * 变量初始化顺序
     */
    private String a = "a"; // 4

    // 静态成员
    private static String b = "b"; // 1

    private String c; // 5

    private static String d; // 2

    static {
        d = "d"; // 2
    }

    public StaticTest() { // 3
        c = "c"; // 5
    }

    // 非静态成员
    private String name;
    // 静态成员
    static Boolean loveJava;

    // 静态方法
    public static void showOne() {
        // 静态方法只能访问静态成员
        // System.out.println("name:" + name + "loveJava:" + loveJava); // 错误
        // System.out.println("loveJava:" + loveJava); // 正确
        // showTwo(); // 错误

    }

    public void showTwo() {
        System.out.println("loveJava:" + loveJava);
    }

    @Test
    public void test() {
        showOne();
    }

    // 代码块测试
    @Test
    public void test1() {
        /*
        // 局部代码块
        {
            int a = 10;
        }
        // System.out.println(a); // 访问错误
        */
    }

    public static void main(String[] args) {
        StaticTest staticTest = new StaticTest(); // 3
    }
}

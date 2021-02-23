package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 11.	异常概述
 * 异常：程序中，在运行的时候出现的不正常现象。Java是面向对象的语言，
 * 异常也是一种对象，因此每次出现异常的时候，看到XXX.XXX.XXXException异常类，
 * 告诉你发生了什么情况 ArithmeticException 算术异常。异常类描述对象的
 * 刚才程序做了除以0
 * 12.	异常继承体系
 * java.lang.Throwable类，所有异常和错误的父类
 * Error类
 * 所有错误的父类
 * Exception类
 * 所有异常的父类
 * RuntimeException
 * **NullPointerException
 * **ClassCastException
 * **IndexOutOfBoundsException
 * 错误，比较严重的问题，一旦出现了错误，程序根本不能运行，必须修改源代码。
 * 错误，相当于，非典，艾滋
 * 异常，比较轻微的问题，一旦出现了异常，程序可以处理掉异常，继续运行
 * 异常，相等于，阑尾炎
 */
public class ExceptionTest {
    /**
     * Throwable类中的方法
     * 构造方法 空参数
     * 构造方法 Throwable(String message)异常信息
     * <p>
     * 	String getMessage()返回异常信息的详细字符串
     * 	String toString()返回异常信息的简短描述
     * 	void printStackTrace()将异常信息直接输出在控制台
     * <p>
     * 13.
     */

    /**
     * 异常的第一种处理方式
     * * try  catch 代码块处理
     * * 标准格式:
     * *    try{
     * *        被检测的代码，可能出现异常的代码
     * *    }catch(异常类 变量){
     * *        异常的处理方式
     * *        变量.调用Throwable类的三个方法
     * *    }
     */
    public void test(int x) throws FileNotFoundException {
        /*
        try {
            // 异常发生，下面的程序不再运行，直接跳转到catch运行
            int x = 3 / 0; // java.lang.ArithmeticException: / by zero
            System.out.println("x = " + x); // 上一行代码抛异常后，下面的代码不执行
        } catch (Exception e) {
            // System.out.println(e.getMessage()); // / by zero
            // System.out.println(e.toString()); // java.lang.ArithmeticException: / by zero
            e.printStackTrace();
        } finally {
            // finally代码块无论是否有异常都必须执行，一般用于释放资源使用
            // 测试finaly代码块
            System.out.println("finally代码块必须执行");
        }
        */

        // 面试题
        // 1.7 try-with-resources
        /**
         *  try (Resources res = ...) {
         *      worked with res
         *  }
         *  try块退出时，会自动调用res.close()
         */
        try (FileReader reader = new FileReader("/Users/wdq/Documents/python_notes")) {
            // FileReader reader = new FileReader("/");
            char[] bytes = new char[1024];
            int len = 0;
            while ((len = reader.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
            // int i = 1 / 0;
            assert x > 0 : x;
            System.out.println(x / 2);
            /**
             * finally没有执行的原因：
             *  1.没有进入try代码块
             *  2.进入了try代码块，代码块中出现死循环或死锁状态
             *  3.进入了try代码块，但是执行了System.exit()操作
             */
            // System.exit(0); // 虚拟机停止finally不执行
            System.out.println('A');
            /**
             *  捕获多个异常
             *  合并catch字句(异常类型彼此之间不存在子类关系时才可用)
             *  catch (FileNotFoundException | ArithmeticException e) {}
             */
        } catch (ArithmeticException e) {
            System.out.println('B'); // B
            return; // 没有return会打印D
        } catch (IOException e) {
            /**
             *  再次抛出异常与异常链
             */
            // 更好的做法是，是把原始异常设置为新异常的"原因"
            FileNotFoundException fileNotFoundException = new FileNotFoundException("文件没找到");
            fileNotFoundException.initCause(e);
            throw fileNotFoundException;
        } finally {
            // finally必须执行，不要在try catch finally中写返回语句
            // 不要写return，避免误读
            System.out.println('C'); // C
        }
        System.out.println("D");

        /*
        // 测试自定义异常
        int x = 0;
        if (x == 0) {
            throw new ThisTestException("异常了。");
        }
        */
        System.out.println("程序正常结束了。");
    }

    /**
     * 1.	异常的第二种处理方式抛出异常
     * 如果程序运行的时候，由于用户或者调用非法传递参数，导致程序出现问题，假如调用者真的传递了非法的数据，需要手动抛出异常。在方法中，使用关键字throw 写异常对象，new 出来的.
     * 错误: 未报告的异常错误Exception;程序中，有异常没有处理
     * 方法内部抛出异常，不处理这个异常，交给调用者处理，谁调用我这个函数，谁处理这个异常。必须在方法的声明上，声明出来，我这个方法有异常，谁调用谁处理。
     * 在方法的定义上，使用throws关键字，把异常声明出来，告诉调用者，如果你调用处理我的异常  throws 异常类
     * 	throw : 只能写在方法内部，后面必须写 new 异常()
     * 	throws: 只能写在方法声明上，后面必须写 异常类
     */
    // Exception in thread "main" java.lang.ArithmeticException: / by zero
    // 	at com.icore.winvaz.javase.basic.ExceptionTest.main(ExceptionTest.java:70)
    public static void main(String[] args) throws ArithmeticException {
        // int x = 3 / 0;
        // System.out.println("x = " + x);
        try {
            new ExceptionTest().test(0);
        } catch (FileNotFoundException e) {
            // 捕获这个异常后，获取原始异常
            Throwable cause = e.getCause();
            cause.printStackTrace();
        }
    }

    /**
     * try、finally中都有return时，代码如何执行？？？
     * try{
     *  return 代码1;
     * }finally{
     *  return 代码2;
     * }
     * 不论try中代码如何执行，是否有异常，最终都是按照下面过程执行，finally不管任何情况，最终都会执行。
     * 执行代码1
     * 执行代码2
     * return 代码2的结果
     */
    @Test
    public void tryFinallyTest() {
        // System.out.println("result:" + m0()); // m0 m1 m2 result:m2
        // System.out.println("result:" + m0()); // m0 m2 m3 result:m3
    }

    public String m0() {
        try {
            System.out.println("m0");
            System.out.println(10 / 0);
            return m1();
        } catch (Exception e) {
            return m2();
        } finally {
            return m3();
        }
    }

    private String m3() {
        System.out.println("m3");
        return "m3";
    }

    private String m2() {
        System.out.println("m2");
        return "m2";
    }

    private String m1() {
        System.out.println("m1");
        return "m1";
    }


    /*
    public static String m0() {
        try {
            System.out.println("m0");
            return m1();
        } finally {
            return m2();
        }
    }
    public static String m1() {
        System.out.println("m1");
        return "m1";
    }
    public static String m2() {
        System.out.println("m2");
        return "m2";
    }
    */

    /**
     * 递归函数
     * @author wdq
     * @create 2020/11/13 11:40
     * @param null
     * @Return
     * @exception
     */
}

/**
 * 自定义异常
 */
// 开发中90%都是继承运行时异常，方法声明上可以不用写throws
class ThisTestException extends RuntimeException {
    public ThisTestException(String message) {
        // super()调用父类的构造方法
        super(message);
    }
}
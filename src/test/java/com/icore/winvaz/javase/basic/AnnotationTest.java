package com.icore.winvaz.javase.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解()
 *
 * @Author wdq
 * @Create 2020/6/29 22:57
 * @Version 1.0.0
 */
public class AnnotationTest {
    public static void main(String[] args) {
        /**
         * 1.得到作用目标
         */
        Class<Test> c = Test.class;
        /**
         * 2.得到作用目标得到上面的注解
         *   获取c上的Anno类型的注解
         */
        Anno anno = c.getAnnotation(Anno.class);
        System.out.println(anno.name() + ", " + anno.age());
    }
}

/**
 * 在自定义注解时，使用 @Target注解来限制注解的作用目标
 */
@Target({ElementType.TYPE, ElementType.METHOD})
/**
 * 注解的保留策略
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Anno {

    /**
     * 指定属性
     * 格式：属性类型 属性名()
     */
    /**
     * 注解属性类型
     */
    String name();

    /**
     * 属性的默认值
     * 有默认值可以赋值也可以不赋值
     */
    int age() default 1;

    Class clazz();

    X ABC();

    Y y();

    String[] hobbys();

    /**
     * 名为value属性的特权
     */
    String[] value();
}

enum X {
    A, B, C
}

@interface Y {
    String username();
}

// 注解作用在类上
// 使用带有属性的注解(属性都要赋值不然报错)
@Anno(
        value = {"Anno"},
        name = "zhangsan",
        age = 18,
        clazz = int.class,
        ABC = X.A,
        y = @Y(username = "lisi"),
        hobbys = {"Hello", "World"})
class Test {

    // 注解作用于成员变量
    // @Anno
    private String name;

    // 注解作用于构造函数
    // @Anno
    public Test() {
    }

    // 注解作用于普通方法
    // @Anno
    public void method() {

    }

    // 注解作用于参数
    /*public void params(@Anno String name) {

    }*/

    public void partVariable() {
        // 注解作用于局部变量
        // @Anno
        int a = 10;
    }
}

package com.icore.winvaz.javase.basic.java8;

/**
 * @Deciption Java 8 新特性--默认方法
 * @Author wdq
 * @Create 2019-10-12 11:07
 */
interface DefaultMethod {

    // 默认方法
    default void defaultMethod() {
        System.out.println("接口默认方法。。。");
        // privateMethod();
    }

    // 静态方法
    static void staticMethod() {
        System.out.println("接口静态方法。。。");
    }

    // Java 9接口中的方法可以是private
    /*private void privateMethod() {
        System.out.println("接口私有方法。。。");
        staticMethod();
    }*/
}

interface StaticMethod {

    // 默认方法
    default void defaultMethod() {
        System.out.println("接口默认方法。。。");
    }

    // 静态方法
    static void staticMethod() {
        System.out.println("接口静态方法。。。");
    }
}

class DefaultMethodDemo implements DefaultMethod, StaticMethod {

    // 实现的接口有多个默认方法时的解决方案
    // 1.创建自己的默认方法，来覆盖重写接口的默认方法
    @Override
    public void defaultMethod() {
        //System.out.println("实现类实现接口的默认方法。。。");
        // 2.使用 super 来调用指定接口的默认方法
        DefaultMethod.super.defaultMethod();
    }

    public static void main(String[] args) {
        DefaultMethodDemo defaultMethodDemo = new DefaultMethodDemo();
        defaultMethodDemo.defaultMethod();
    }
}
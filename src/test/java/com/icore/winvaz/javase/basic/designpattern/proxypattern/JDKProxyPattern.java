package com.icore.winvaz.javase.basic.designpattern.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * 与静态代理类对照的是动态代理类，动态代理类的字节码在程序运行时由Java反射机制动态生成，无需程序员手工编写它的源代码。
 * 动态代理类不仅简化了编程工作，而且提高了软件系统的可扩展性，因为Java 反射机制可以生成任意类型的动态代理类。
 * java.lang.reflect 包中的Proxy类和InvocationHandler 接口提供了生成动态代理类的能力。
 * java.lang.reflect.Proxy:生成动态代理类和对象；
 * java.lang.reflect.InvocationHandler（处理器接口）：可以通过invoke方法实现
 * 对真实角色的代理访问。
 * <p>
 * 每次通过 Proxy 生成的代理类对象都要指定对应的处理器对象。
 *
 * @Author wdq
 * @Create 2019-05-10 19:24
 */
public class JDKProxyPattern {
    public static void main(String[] args) {
        // 如何查看生成的代理类？ 在生成代理类之前加上以下代码(我用的jdk1.8)：
        // 新版本，jdk产生代理类
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        // 真实对象
        Subject subject = new RealSubject();
        BeforeAdvice beforeAdvice = () -> System.out.println("前值增强。。。before()");

        AfterAdvice afterAdvice = () -> System.out.println("后置增强。。。after()");

        Subject proxySubject = new JDKProxy().createProxyObject(subject, beforeAdvice, afterAdvice);

        proxySubject.method();

        // class com.icore.winvaz.javase.basic.designpattern.proxypattern.$Proxy0
        System.out.println("Subject的代理类:" + proxySubject.getClass().getName());
        System.out.println("=========================");
    }
}

/**
 * 步骤1
 * 创建接口
 */
interface Subject {
    void method();
}

// 前值增强
interface BeforeAdvice {
    void before();
}

interface AfterAdvice {
    void after();
}

/**
 * 步骤2
 * 创建接口的实现类
 */
class RealSubject implements Subject {

    @Override
    public void method() {
        System.out.println("method().....");
    }
}

class JDKProxy implements InvocationHandler {

    private Subject subject;
    private BeforeAdvice beforeAdvice;
    private AfterAdvice afterAdvice;

    //代理类中提供一个方法，可以创建原始被代理对象的代理对象
    public Subject createProxyObject(Subject subject, BeforeAdvice beforeAdvice, AfterAdvice afterAdvice) {
        this.subject = subject;
        this.beforeAdvice = beforeAdvice;
        this.afterAdvice = afterAdvice;
        //类加载器
        ClassLoader classLoader = subject.getClass().getClassLoader();
        //被代理对象实现的所有接口
        Class[] clazz = subject.getClass().getInterfaces();
        //方法拦截
        InvocationHandler h = this;
        //最后一步
        Subject temp = (Subject) Proxy.newProxyInstance(classLoader, clazz, h);

        // 获取代理类class com.icore.winvaz.javase.basic.designpattern.proxypattern.$Proxy0
        System.out.println(Proxy.getProxyClass(classLoader, clazz));
        // 判断指定Class对象是否是一个代理类
        System.out.println(Proxy.isProxyClass(subject.getClass())); // false
        System.out.println(Proxy.isProxyClass(new RealSubject().getClass())); // false
        System.out.println(Proxy.isProxyClass(new JDKProxy().getClass())); // false
        return temp;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // System.out.println("前置增强");
        if (Objects.nonNull(beforeAdvice)) {
            beforeAdvice.before();
        }

        // proxy.toString(); // 导致无限递归调用

        //为原始方法的调用功能进行增强
        Object invoke = method.invoke(subject, args);

        // System.out.println("后置增强");
        if (Objects.nonNull(afterAdvice)) {
            afterAdvice.after();
        }

        return invoke;
    }
}
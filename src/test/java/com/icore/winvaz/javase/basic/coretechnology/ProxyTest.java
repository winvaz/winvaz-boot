package com.icore.winvaz.javase.basic.coretechnology;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @Deciption 代理测试
 * @Author wdq
 * @Create 2020/11/12 15:35
 * @Version 1.0.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            TraceHandler traceHandle = new TraceHandler(value);
            elements[i] = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class},
                    traceHandle);
        }
        // 获取代理类名class com.sun.proxy.$Proxy1
        System.out.println(Proxy.getProxyClass(new ProxyTest().getClass().getClassLoader(),
                new ProxyTest().getClass().getInterfaces()));
        // 判断一个Class对象是否表示一个代理类 false
        System.out.println(Proxy.isProxyClass(new ProxyTest().getClass())); // false
        System.out.println(Proxy.isProxyClass(new TraceHandler(1).getClass())); // false
        // 构造一个随机整数
        Integer key = new Random().nextInt(elements.length) + 1;
        // Search for the key
        int result = Arrays.binarySearch(elements, key);
        // 查找匹配到的数据
        if (result > 0) System.out.println(elements[result]);
    }
}

class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object object) {
        target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) System.out.print(", ");
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}

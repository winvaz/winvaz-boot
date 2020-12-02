package com.icore.winvaz.javase.basic.designpattern.proxypattern;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Cglib 动态代理是针对代理的类, 动态生成一个子类, 然后子类覆盖代理类中的方法, 如果是private或是final类修饰的方法,则不会被重写。
 * <p>
 * CGLIB是一个功能强大，高性能的代码生成包。它为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充。
 * 通常可以使用Java的动态代理创建代理，但当要代理的类没有实现接口或者为了更好的性能，CGLIB是一个好的选择。
 * <p>
 * 从代理对象反编译源码可以知道，代理对象继承于Cat ，拦截器调用intercept()方法，
 * intercept()方法由自定义CglibProxyInterceptor实现，所以，最后调用CglibProxyInterceptor中的intercept()方法，
 * 从而完成了由代理对象访问到目标对象的动态代理实现。
 * <p>
 * CGlib是一个强大的,高性能,高质量的Code生成类库。它可以在运行期扩展Java类与实现Java接口。
 * 用CGlib生成代理类是目标类的子类。
 * 用CGlib生成 代理类不需要接口。
 * 用CGLib生成的代理类重写了父类的各个方法。
 * 拦截器中的intercept方法内容正好就是代理类中的方法体。
 *
 * @Author wdq
 * @Create 2019-05-12 21:39
 */
public class CglibProxyPattern {
    public static void main(String[] args) {
        /*
		User u = new User();
		u.eat();
		*/
        // Animal animal = new Cat();
        //animal.method();

        //Cat cat = new CglibProxy(animal).createProxyObject();
        Cat cat = new CglibProxy().createProxyObject();
        cat.method();
        //a.fn调用时，调用的就是是父类的fn还是子类的fn?,运行的是找对应的实现类

        /*User  u = new CglibProxy().createProxyObject();

        u.eat();*/

        // com.icore.winvaz.javase.basic.designpattern.proxypattern.Cat$$EnhancerByCGLIB$$743802c
        System.out.println("Cat的代理类：" + cat.getClass().getName());

    }
}

/**
 * 步骤1
 * 创建接口
 */
class Animal {
    // 可以被代理
    public void method() {
        System.out.println("Animal.method().....");
    }
}

/**
 * 步骤2
 * 创建Animal类的子类
 */
class Cat extends Animal {

    @Override
    public void method() {
        System.out.println("Cat.method().....");
    }

    // final修饰的方法不会被生成的子类覆盖
    public final void eat() {
        System.out.println("Animal.eat().....");
    }

    // private修饰的方法不会被生成的子类覆盖
    private void play() {
        System.out.println("Animal.paly()......");
    }
}

class User {
    public void eat() {
        System.out.println("User.eat().....");
    }
}

// cglib代理类
class CglibProxy implements MethodInterceptor {
    /*
    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }
    */
    //cglib是对某个类做代理，而不是对对象做代理，jdk代理是对对象做代理
    /*public User createProxyObject(){
        //1.创建一个Enhance对象
        //Enhancer描述的是一个内存中的class,名称？未知
        Enhancer eh = new Enhancer();
        //2.cglib的工作原理是对原始被代理类创建子类
        //指定eh的父类是User
        eh.setSuperclass(User.class);
        //3.设置方法拦截
        eh.setCallback(this);
        //4.创建代理对象
        User temp = (User) eh.create();
        return temp;
    }*/

    public Cat createProxyObject() {
        // 1.创建一个Enhance对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        // Enhancer描述的是一个内存中的class,名称？未知
        Enhancer eh = new Enhancer();
        // 2.cglib的工作原理是对原始被代理类创建子类
        // 指定eh的父类是Cat
        // 设置目标类的字节码文件
        eh.setSuperclass(Cat.class);
        // 3.设置方法拦截
        // 设置回调函数
        eh.setCallback(this);
        // 4.创建代理对象
        // 这里的create方式就是正式创建代理对象
        Cat temp = (Cat) eh.create();

        return temp;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {


        //增强
        System.out.println("前置增强");

        //调用原始的方法
        //method.invoke(proxy, args); //调用的是子类的方法
        //调用时应该通过父类来调用方法，最终达到运行子类实现的目的
        Object object = methodProxy.invokeSuper(proxy, args);
        //methodProxy.invoke(proxy, args); // 堆栈溢出

        /*
        System.out.println("###   before invocation");
        method.invoke(target, args);
        System.out.println("###   end invocation");
        */
        return object;
    }
}
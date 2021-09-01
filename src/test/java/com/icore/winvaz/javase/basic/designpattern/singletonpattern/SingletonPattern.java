package com.icore.winvaz.javase.basic.designpattern.singletonpattern;

import org.junit.jupiter.api.Test;

/**
 * 单例模式（SingletonBad Pattern）是 Java 中最简单的设计模式之一。
 * 这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。
 * 这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。
 * <p>
 * 意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 主要解决：一个全局使用的类频繁地创建与销毁。
 * 何时使用：当您想控制实例数目，节省系统资源的时候。
 * 如何解决：判断系统是否已经有这个单例，如果有则返回，如果没有则创建。
 * 关键代码：构造函数是私有的。
 * <p>
 * 应用实例：
 * 1、一个班级只有一个班主任。
 * 2、Windows 是多进程多线程的，在操作一个文件的时候，就不可避免地出现多个进程或线程同时操作一个文件的现象，所以所有文件的处理必须通过唯一的实例来进行。
 * 3、一些设备管理器常常设计为单例模式，比如一个电脑有两台打印机，在输出的时候就要处理不能两台打印机打印同一个文件。
 * <p>
 * 优点：
 * 1、在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例（比如管理学院首页页面缓存）。
 * 2、避免对资源的多重占用（比如写文件操作）。
 * <p>
 * 缺点：没有接口，不能继承，与单一职责原则冲突，一个类应该只关心内部逻辑，而不关心外面怎么样来实例化。
 * <p>
 * 使用场景：
 * 1、要求生产唯一序列号。
 * 2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
 * 3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。
 * <p>
 * 注意事项：getSingletonBad() 方法中需要使用同步锁 synchronized (SingletonBad.class) 防止多线程同时进入造成 singletonBad 被多次实例化。
 * 1、单例类只能有一个实例。
 * 2、单例类必须自己创建自己的唯一实例。
 * 3、单例类必须给所有其他对象提供这一实例。
 *
 * @Author wdq
 * @Create 2019-05-07 21:21
 */
public class SingletonPattern {

    @Test
    public void test() {

        //不合法的构造函数
        //编译时错误：构造函数 SingletonBad() 是不可见的
        //SingletonBad singletonBad = new SingletonBad();

        // 获取单例对象
        SingletonVolatile singletonVolatile1 = SingletonVolatile.getInstance();
        SingletonVolatile singletonVolatile2 = SingletonVolatile.getInstance();
        System.out.println(singletonVolatile1);
        System.out.println(singletonVolatile2);
        if (singletonVolatile1.equals(singletonVolatile2)) {
            System.out.println(true); // true
        } else {
            System.out.println(false);
        }
        System.out.println("=========");

        SingletonStaticInner instance = SingletonStaticInner.getInstance();
        instance.showMessage();

        // 获取Runtime类对象，静态方法getRuntime
        //Runtime runtime = Runtime.getRuntime();
        // exec(String string)可以执行Windows中的可运行文件
        //runtime.exec("notepad");
        // -a 取消，-s 关机， -r 重启
        //runtime.exec("shoutdown -s -t 3600");
    }
}

/**
 * 步骤1
 * 创建一个Singleton类(饿汉式)
 */
class SingletonBad {

    // 让构造函数为private，这样该类就不会被实例化
    private SingletonBad() {
    }

    // 成员位置创建静态的Singleton类的一个对象
    private static final SingletonBad singletonBad = new SingletonBad();

    //获取唯一可用的对象方法
    public static SingletonBad getSingletonBad() {
        return singletonBad;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}

/**
 * 步骤1
 * 创建一个Singleton类(懒汉式，非线程安全)(延迟加载对象)
 */
class SingletonIdlerNotThread {
    // 让构造函数为 private，这样该类就不会被实例化
    private SingletonIdlerNotThread() {
    }

    // 成员位置创建静态的Singleton类的一个本类变量
    // 类的成员位置，定义本类类型变量，但是不建立对象
    private static SingletonIdlerNotThread singletonIdlerNotThread = null;

    // 对外提供公共静态方法，获取唯一可用的对象方法
    public static SingletonIdlerNotThread getSingletonIdlerNotThread() {
        if (singletonIdlerNotThread == null) {
            // 建立本类对象
            singletonIdlerNotThread = new SingletonIdlerNotThread();
        }
        return singletonIdlerNotThread;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}

/**
 * 步骤1
 * 创建一个Singleton类(懒汉式，线程安全)
 */
class SingletonIdlerThread {
    // 让构造函数为 private，这样该类就不会被实例化
    private SingletonIdlerThread() {

    }

    // 成员位置创建静态的Singleton类的一个本类变量
    private static SingletonIdlerThread singletonIdlerThread = null;

    // 获取唯一可用的对象方法
    // 同步代码块
    /*public static SingletonIdlerThread getSingletonIdlerThread() {
        // 双重判断提高效率
        if (singletonIdlerThread == null) {
            synchronized (SingletonIdlerThread.class) {
                if (singletonIdlerThread == null) {
                    singletonIdlerThread = new SingletonIdlerThread();
                }
            }
        }
        return singletonIdlerThread;
    }*/

    // 同步方法
    public static synchronized SingletonIdlerThread getSingletonIdlerThread() {
        // 双重判断提高效率
        if (singletonIdlerThread == null) {
            singletonIdlerThread = new SingletonIdlerThread();
        }
        return singletonIdlerThread;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}

/**
 * 单例设计模式--Volatile局部变量
 */
class SingletonVolatile {

    // 成员位置创建静态的Singleton类的一个本类变量，并且被volatile修饰
    /**
     * 这里其实和volatile有关，我们知道，双重校验锁单例为了避免发生指令重排，一定要使用volatile来定义单例对象。
     */
    private static volatile SingletonVolatile singletonVolatile = null;

    // 私有构造
    private SingletonVolatile() {

    }

    // 获取唯一可用的对象方法
    public static SingletonVolatile getInstance() {
        // 定义一个局部变量
        SingletonVolatile temp = singletonVolatile;
        // 双重判断提高效率
        if (temp == null) {
            // 同步代码块
            synchronized (SingletonVolatile.class) {
                // 变量赋值
                temp = singletonVolatile;
                if (temp == null) {
                    // 对局部变量进行赋值
                    temp = new SingletonVolatile();

                    // 再将局部变量赋值给单例对象
                    singletonVolatile = temp;
                }
            }
        }
        // 返回单例对象
        return temp;
    }
}

/**
 * 登记式/静态内部类
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 实现难度：一般
 * <p>
 * 描述：这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。
 * 这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
 * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程，
 * 它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载了，那么 instance 就会被实例化（没有达到 lazy loading 效果），
 * 而这种方式是 Singleton 类被装载了，instance 不一定被初始化。
 * 因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，
 * 从而实例化 instance。想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，
 * 另外一方面，又不希望在 Singleton 类加载时就实例化，因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，
 * 那么这个时候实例化 instance 显然是不合适的。这个时候，这种方式相比第 3 种方式就显得很合理。
 */
class SingletonStaticInner {

    //让构造函数为 private，这样该类就不会被实例化
    private SingletonStaticInner() {

    }

    // 静态内部类
    private static class SingletonHolder {
        // 静态字段
        private static final SingletonStaticInner INSTANCE = new SingletonStaticInner();
    }

    // 静态访问方法
    public static final SingletonStaticInner getInstance() {
        // 访问静态内部类的静态字段
        return SingletonHolder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}

/**
 * 枚举:简单来讲，就是几选一的问题
 * JDK 版本：JDK1.5 起
 * 是否 Lazy 初始化：否
 * 是否多线程安全：是
 * 实现难度：易
 * <p>
 * 描述：这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
 * 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，
 * 防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，
 * 用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
 * 不能通过 reflection attack 来调用私有构造方法。
 * <p>
 * 枚举类中的枚举项，必须出现在枚举类的第一个位置上。(其他内容必须出现在枚举项后面)
 * 构造器，成员变量，方法及抽象方法
 * 如果枚举类中包含抽象方法，列举枚举项时，必须实现抽象方法。
 * <p>
 * 枚举类的常用方法：java.lang.Enum
 */
/*class SingletonConstants {
    public static final SingletonConstants INSTANCE = new SingletonConstants();
}*/
enum SingletonEnum {
    // [在枚举常量后面必须添加分号，因为在枚举常量后面还有其他成员时，分号是必须的。
    // 枚举常量必须在枚举类中所有成员的上方声明。]
    INSTANCE;

    // [枚举类的构造器不可以添加访问修饰符，枚举类的构造器默认是private的。
    // 但你自己不能添加private来修饰构造器。]
    SingletonEnum() {

    }

    public void whateverMethod() {

    }
}

/**
 * 带包的定义、编译、运行
 * 	定义：使用package关键字指定包名，放置在类当中的第一行。
 * 		  包的命名规则：一般使用软件公司的域名反写作为包的定义
 * 		  www.baidu.com>>com.baidu.www.自己的类
 * 		  icore.cn>>cn.icore 传智包
 *
 * 	编译：使用参数-d可以直接在编译时生成对应的文件夹即字节码文件
 * 	         javac -d .InterfaceTest.java
 *
 * 	运行：在运行时，需要使用类的全限定名：即包名.类名
 *
 * 	但是这样仍然比较麻烦：后期我们使用eclipse直接屏蔽掉这些麻烦的步骤
 *
 * 	跨包访问类时，被访问的类必须为public修饰的.
 * 	可以采用导包的方式来解决不同包间访问的问题.可以简化操作.
 *
 * 	当有多个包有相同类名的类时，只能导入一个包里的这个类。而另外的包中的相同名称类只能使用全限定名。
 *
 * 	导包时，可以使用*来导入所有文件，但是通常不这样做，因为会有效率问题
 * 	顺序：package>import>class
 */
package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

/**
 * Description: 接口
 * 接口变量固定写法: public static final 数据类型 变量 = 值
 * 接口抽象方法固定写法：public abstract 返回值类型 方法名(参数);
 * <p>
 * 接口中的所有方法都是抽象的
 * 接口是对外提供的规则
 * 接口是抽象程度更高的抽象类
 * 接口只能继承接口，不能继承其他类
 * 如果后边有实现类，则实现类要实现所继承的接口的所有抽象方法
 * <p>
 * 抽象类与接口的区别(抽象类是继承体系的共性内容,接口是继承体系的扩展内容。)
 * 1.抽象类有构造函数，接口没有
 * 2.抽象类有普通成员与方法，接口有常量，方法都是抽象的
 * 3.Java 8 接口有默认和静态方法。
 * 4.
 *
 * @author wendq
 * @create 2018/11/6 16:27
 */
public class InterfaceTest {

    @Test
    public void test() {
        // 接口可以new
        Printable[] printables = new Printable[9];

        Able able = new Able();
        // 成员变量
        System.out.println(able.COLOR);
        // 调用重写方法
        able.print();
        // 调用默认方法
        able.interfaceDefaultMethod();
        // 调用静态方法
        Printable.interfaceStaticMethod();
        System.out.println("========");

        // 接口多态
        Printable printable = new Table();
        // 成员变量
        System.out.println(printable.COLOR);
        // 调用重写方法
        printable.print();
        // 调用默认方法
        printable.interfaceDefaultMethod();
        // 调用静态方法
        Printable.interfaceStaticMethod();
        System.out.println("==========");
        //Printable printable = new MyPrintable();
        //System.out.println(printable.toString());
        //System.out.println(printable.equals(args));

        // Printable printable1 = InterfaceTest.getPrintable();
        // printable1.print();

        Calendar calendar = Calendar.getInstance();
    }

    // 返回接口类型，返回的肯定是接口的实现类对象
    public static Printable getPrintable(Object object) {
        if (object instanceof Able) {
            return new Able();
        } else if (object instanceof Table) {
            return new Table();
        }
        return null;
    }

}
/**
 * 在抽象类中，抽象方法本质上是定义接口规范：即规定高层类的接口，
 * 从而保证所有子类都有相同的接口实现。
 * 若果一个抽象类没有字段，所有方法全部都是抽象方法，可以把该抽象类改写为接口：interface
 * <p>
 * 接口中的所有方法均必须是抽象的！
 * 接口是对外提供的规则。
 * 接口可以理解为抽象程度更高的抽象类。
 * <p>
 * 接口中的所有方法均必须是抽象的！
 * 接口是对外提供的规则。
 * 接口可以理解为抽象程度更高的抽象类。
 * <p>
 * 接口中的所有方法均必须是抽象的！
 * 接口是对外提供的规则。
 * 接口可以理解为抽象程度更高的抽象类。
 * <p>
 * 接口与抽象类
 * 共  性：
 * 都是不断抽取出来的抽象的概念
 * 区别 1：
 * 抽象类体现继承关系，一个类只能单继承
 * 接口体现实现关系，一个类可以多实现
 * 区别 2：
 * 抽象类是继承，是 "is a "关系
 * 接口是实现，是 "like a"关系
 * 区别 3：
 * 抽象类中可以定义非抽象方法，供子类直接使用
 * 接口的方法都是抽象，接口中的成员都有固定修饰符
 */
/**
 *      接口中的所有方法均必须是抽象的！
 * 	接口是对外提供的规则。
 * 	接口可以理解为抽象程度更高的抽象类。
 */
/**
 * 接口与抽象类
 * 共  性：
 * 都是不断抽取出来的抽象的概念
 * 区别 1：
 * 抽象类体现继承关系，一个类只能单继承
 * 接口体现实现关系，一个类可以多实现
 * 区别 2：
 * 抽象类是继承，是 "is a "关系
 * 接口是实现，是 "like a"关系
 * 区别 3：
 * 抽象类中可以定义非抽象方法，供子类直接使用
 * 接口的方法都是抽象，接口中的成员都有固定修饰符
 */

/**
 * 接口的特点
 *        接口是对外暴露的规则。
 *        接口是程序的功能扩展。
 *        接口的出现降低耦合性。
 *        接口可以用来多实现。
 *        类与接口之间是实现关系，而且类可以继承一个类的同时实现多个接口。
 *        接口与接口之间可以有继承关系。并且可以多继承
 *        接口不能被实例化
 * 接口的成员特点
 *        成员变量
 *              接口中只有常量
 *        成员函数
 *              均为抽象，实现的类要创建实例需要将方法全部重写
 *        构造函数
 *              无构造方法
 *        推荐在定义接口时加入修饰符
 */
interface Printable {
    /**
     * 接口可以理解为抽象类，所以不可以被实例化
     *
     * 	成员变量被：public static final 修饰
     * 		1、必须有值，因为是常量
     * 		2、因为static修饰，所以为静态常量
     * 		3、因为为常量，所以必须在命名时，为大写
     * 		4、默认为public所以不能为private或者其他权限修饰符修饰
     *
     * 	成员函数：public abstract
     * 		1、abstract修饰，方法不能有方法体。
     * 		2、public修饰，所以方法权限为公共。子类在重写方法时，也必须为public
     *
     * 	通过接口的多实现间接实现了部分其他语言的多继承。
     *
     * 	接口只能继承接口，不能继承其他的类。
     * 	如果后边有实现类，实现了接口，则必须把这个接口继承的接口里边的方法一同实现，
     * 	即实现所有相关的抽象方法，才能实例化
     *
     * 	建议：
     * 		在接口当中将关键字手动给全
     */
    // 成员属性，默认 public static final修饰
    // 1.必须有值，因为是常量
    // 2.因为static修饰，所以为静态常量
    // 3.因为是常量，所以必须大写命名
    // 4.默认为public，所以不能为其他权限修饰符修饰
    //public static final String color = "红色";
    String COLOR = "红色";

    // 成员函数，默认 public abstract修饰
    // 1.abstract修饰，所以方法不能有方法体
    // 2.public修饰，方法权限都是公共的，子类重写方法时，也必须为public
    void print();

    // Java 8接口中的默认方法
    default void interfaceDefaultMethod() {
        System.out.println("JDK 1.8 Interface default method");
    }

    // Java 8接口中的静态方法
    static void interfaceStaticMethod() {
        System.out.println("JDK 1.8 Interface static method");
    }


    /**
     * 接口嵌套
     */
 /* interface MessagePrintable {
    void msg();
  }*/
}

/**
 * 接口继承接口
 */
interface Showable extends Printable {
    void show();
}

/**
 * 接口多继承
 */
interface Ime extends Printable, Showable {

}

/**
 * 接口实现
 */
class Table implements Ime {

    @Override
    public void print() {
        System.out.println("ImeImpl.print");
    }

    @Override
    public void show() {
        System.out.println("ImeImpl.show");
    }
}

/**
 * 接口多实现
 */
class Able implements Printable, Showable {

    @Override
    public void print() {
        System.out.println("Able.print");
    }

    @Override
    public void show() {
        System.out.println("Able.show");
    }
}
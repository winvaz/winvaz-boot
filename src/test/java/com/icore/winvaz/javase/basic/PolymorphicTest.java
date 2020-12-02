package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * Description: 多态的特点
 * 成员变量：
 * 编译时，父类对该成员变量有定义则不报错，父类没有对其定义，子类对其定义，会报错
 * 运行时，访问的成员变量为父类的成员变量
 * 编译时，看左边(数据类型)
 * 运行时，看左边(对应值)
 * <p>
 * 成员函数(重写方法)：
 * 编译时，父类对该成员函数有定义则不会报错，父类没有对其定义，子类对其定义则报错。
 * 运行时，调用的是子类的方法(子类重写父类方法)，如果子类没有重写，则调用父类的方法。
 * 编译时，看左边(数据类型)
 * 运行时，看右边(子类是否重写了父类的方法)
 * <p>
 * 编译时，看左边，检查语法。
 *
 * 弊端：
 *      当出现多态时，子类持有的特有方法将无法被调用。
 * <p>
 * 多态的向上向下转型：
 * 向上转型，子类类型提升成父类类型，通常为默认向上转型。
 * 向下转型，在多台下，父类的引用指向之类的实例对象。执行这个实例的引用就可以强制向下转型成子类。
 */

/**
 * 多态使模块在复用性基础上更加有扩展性，使系统运行更有想象空间。
 * 多态使以上述的三个面向对象特性为基础，根据运行时的实际对象类型，同一个方法产生不同的运行结果，
 * 使同一个行为具有不同的表现形式。
 * 多态使面向对象天空中绚丽多彩的礼花。提升了对象的扩展能力和运行时丰富想象力。
 * <p>
 * override(覆写)动态绑定:子类实现接口，或继承父类时，保持方法签名完全相同，实现不同的方法体。是垂直方向上行为的不同实现。
 * overload(重载)静态绑定:方法名称相同，但是参数类型或参数个数是不同的，是水平方向上行为的不同实现。
 * 重载是编译期确定方法调用，属于静态绑定。所以多态专指覆写
 * 多态是指在编译层面无法确定最终调用的方法体，以覆写为基础来实现面向对象特征，在运行期由JVM进行动态绑定，调用合适的覆写方法体来执行。
 * <p>
 * 多态并不是面向对象的一种特质，而是一种由继承行为衍生而来的进化能力而已。
 * <p>
 * 多态的特性：Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
 * <p>
 * 多态：针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法。
 */
public class PolymorphicTest {

    @Test
    public void test() {
        // 多态测试
        // Jnc jnc = new Jnc(); // 剑南春是剑南春 OK
        // Wine wine = new Wine(); // 酒是酒 OK
        // wine = new Jnc(); // 剑南春是酒 OK
        // jnc = new Wine(); // 酒是剑南春 错误 不兼容类型

        // 默认的向上转型
        Wine wine = new Jnc();
        // 父类成员变量
        System.out.println(wine.name);
        // 子类重写父类方法
        wine.fun2();
        // 向上转型后，父类无法调用子类的重载方法
        // wine.fun1("1");
        // 不转型可以
        // Jnc jnc = new Jnc(1958);
        // jnc.fun1("1988");

        // 调用子类特有函数,强制向下转型
        ((Jnc) wine).sleep();
        System.out.println("===========");

        /*
         * 注意：
         *       如果有子父类关系，向下转型时，在运行时报错，java.lang.ClassCastException:
         *       如果没有子父类关系，向下转型时，在编译时报错，不可转换的类型
         */
        // Wine wine1 = new Wine();
        // Jnc jnc = (Jnc) wine1;
        // System.out.println(jnc.name); // 编译检查语法通过。运行时：java.lang.ClassCastException:

        // 给一个有普通收入，工资收入和享受国务院特殊津贴的小伙伴算税
        /*
        Income[] incomes = new Income[]{
                new Income(3000D),
                new Salary(7500D),
                new StateCouncilSpecialAllowance(15000D)
        };
        System.out.println("总税收为:" + totalTax(incomes));
        */
        // 给一个有工资收入和稿费收入的小伙伴算税:
        Income[] incomes = new Income[]{
                new Salary(15000D),
                new remuneration(20000D)
        };
        System.out.println("总税收为:" + totalTax(incomes));
    }

    // 编写一个报税的财务软件，对于一个人的所有收入进行报税
    public Double totalTax(Income... incomes) {
        Double total = 0D;
        for (Income income : incomes) {
            total += income.getTax();
        }
        return total;
    }
}

/**
 * final可以修饰类，方法，变量。
 * final修饰的类不可以被继承。
 * final修饰的方法不可以被覆盖。
 * final修饰的变量是一个常量。只能被赋值一次。
 * 内部类只能访问被final修饰的局部变量。
 * 注意：
 *       final修饰变量必须有值，在构造方法结束前(即初始化结束前)赋值即可。可在构造方法或构造代码块中赋值。
 *         内存图解释：如果不希望在某方法内部修改参数值，则在形参前加final修饰。如果为基本数据类型，则值不能被修改。
 *         如果为引用数据类型，则内部属性仍可以被修改，仅仅为引用指向的对象地址不能改变
 */
// final修饰的类不能再被继承
// final class Wine{
class Wine {

    String name = "酒";

    // final修饰的变量变为一个常量，常量是必须要有值的
    // final修饰变量后，报错的依据是：是否这个变量一定会被初始化，一旦出现不初始化的情况，即报错(语法错误)
    // final修饰字段，在初始化之后不能修改，不能修改类似(就是?)常量
    // public final Integer age = 18;
    /*
    public final Integer age;
    public Wine(Integer age) {
        this.age = age;
    }
    */
    // final在构造方法内赋值测试
    /*public Wine() {
        final int x;
        x = 10;
        System.out.println("x:" + x);
    }*/

    // 多态测试
    public Wine() {

    }


    // 标记为 private/final的方法不能被覆写
    private void hello() {
        System.out.println("Hello");
    }
    // 测试final修饰局部变量
    public void finalTest(final int x) {
        // x = 10; // 报错，不能分配最终参数x
        System.out.println("Hello");
    }
    public void finalTest(final String food) { // String是特殊的引用类型，不够典型，没有代表性
        // food = "狗粮"; // 报错，不能分配最终参数x
        System.out.println("Hello");
    }
    public void finalTest(final Jnc jnc) {
        // 同样不能修改jnc值，我们修改的是jnc这个实例对象，堆内存空间的内容。而引用没有修改。
        // jnc = new Jnc(1988); // 错误
        jnc.name = "五粮液";
        System.out.println("Hello " + jnc.name); // Hello 五粮液
    }


    public final void World() {
        System.out.println("World");
    }

    public void fun1() {
        System.out.println("Wine的fun1().......");
        fun2();
    }

    public void fun2() {
        System.out.println("Wine的fun2()........");
    }

    // 静态方法
    public static void fun3() {
        System.out.println("Wine的静态fun3()........");
    }

    // 覆写Object方法
    // 所有类都继承自Object
    // toString():把instance输出为String。
    // equal():判断两个instance是否逻辑相等。
    // hashCode():计算一个instance的哈希值。

    // 比较是否相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return name.equals(wine.name);
    }

    // 计算hash
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // 显示更具意义
    @Override
    public String toString() {
        return "Wine{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Jnc extends Wine {

    String name = "剑南春";

    Jnc() {

    }

    /**
     * 子类重载父类的方法
     * 父类中不存在该方法，向上转型后，父类是不能引用该方法的
     */
    public void fun1(String a) {
        System.out.println("Jnc的fun1(String a)....." + a);
        fun2();
    }

    /**
     * 子类重写父类的方法
     * 指向子类的父类引用调用fun2(),必定是调用该方法
     */
    @Override
    public void fun2() {
        System.out.println("Jnc的fun2()........");
    }

    /**
     * 子类自由方法
     */
    public void sleep() {
        System.out.println("洞藏");
    }

}

// 多态案例
// 定义一种收入，需要给它报税
class Income {
    protected Double income;

    public Income(Double income) {
        this.income = income;
    }

    public Double getTax() {
        // 税率10%
        return income * 0.1;
    }
}

// 对于工资收入，可以减去一个基数
class Salary extends Income {

    public Salary(Double income) {
        super(income);
    }

    @Override
    public Double getTax() {
        // 小于基数不用缴税
        if (income <= 5000) {
            return 0D;
        }
        // 税率20%
        return (income - 5000) * 0.2;
    }
}

// 如果享有国务院特殊津贴，那么按照规定，可以全部免税
class StateCouncilSpecialAllowance extends Income {

    public StateCouncilSpecialAllowance(Double income) {
        super(income);
    }

    @Override
    public Double getTax() {
        return 0D;
    }
}

// 稿酬税收
class remuneration extends Income {

    public remuneration(Double income) {
        super(income);
    }

    @Override
    public Double getTax() {
        // 应纳税所得额 = 稿酬所得（不超过4000元） - 800元
        //应纳税所得额 = 稿酬所得（超过4000元）×（1 - 20%）
        //应纳税额 = 应纳税所得额 ×14%
        if (income < 4000) {
            return (income - 800) * 0.7 * 0.2;
        } else {
            return income * (1 - 0.2) * 0.7 * 0.2;
        }
    }
}



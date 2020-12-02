package com.icore.winvaz.javase;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author wdq
 * @Create 2020/4/3 09:30
 * @Version 1.0.0
 */
// 实现Serializable接口，实现该类对象的序列化
// 实现Comparable接口，让Person具备自然顺序,自然顺序，我们自己定义，比较姓名，姓名相同，比较年龄
public class Person implements Comparable, Serializable {
    // 实现Serializable接口的类一定要显示地定义serialVersionUID属性值。
    private static final long serialVersionUID = 1L;

    /**
     * 把成员变量加上了static后，序列化没有问题，但是反序列化的时候，成员变量的值丢失，
     * 原因序列化的时候，文件没有保存静态变量的值。序列化是对象的序列化，
     * 静态成员变量，属于类，不属于对象，因此静态不能序列化
     */
    //在设计一个类时，如果该属性为该类的属性，则应该定义在成员变量位置，
    // 如果不属于该类(事物)本身则定义在局部位置
    // 为了子类能够访问到父类的成员变量，需要将private私有修饰符改为protected受保护的修饰符
    // private String name;
    protected String name;
    // Integer age = 10; // 显示初始化
    private Integer age; // 显示初始化
    // 敏感属性不想被序列化，使用transient关键字
    private Integer sex; //在软件当中通常不会使用字符串记录性别，通常使用int类型。1代表男，0代表女，2代表未知
    // 根据开发手册建议，POJO类的Boolean类型的变量不要加is前缀，以部分防框架会引起序列化错误。
    // private Boolean isLoveCode; // 错误的定义方式。
    // private Boolean loveCode = true;//是否热爱编程

    /**
     * 代码块：
     * 使用{}括起来的区域
     * <p>
     * 局部代码块：
     * 位置：存在于方法中
     * 作用：变量作用域控制，影响变量生命周期
     * <p>
     * 构造代码块：
     * 位置：直接存在于类中
     * 作用：将构造函数中相同代码抽取，在构造器前执行
     * <p>
     * 静态构造代码块：
     * 位置：直接存在于类中，使用static
     * 作用：由于对象存在，只初始化一次
     * <p>
     * 注意：
     * 只在第一次创建对象的时候调用
     * 执行顺序：静态代码块>构造代码块>构造方法
     * 对于代码块与成员变量(显示赋值)的执行顺序时谁写在前边就先执行谁。
     * 注意，一般都将构造内容写在成员变量后边，所以先执行显示初始化，再执行构造内容
     */
    /*
    // 静态代码块
    static {
        System.out.println("我是一个静态代码块");
    }
    */
    // 成员代码块(构造代码块)
    {
        System.out.println("我是一个构造代码块");
    }

    /**
     * 构造方法：
     * 函数名与类名相同
     * 不用定义返回值类型
     * 没有具体的返回值
     * <p>
     * 如果我们没有手动给出构造方法，则java会为我们提供一个 <无参> 的构造方法。
     * 建议手动给出空参的构造方法：
     * 1、习惯性在创建一个对象时，使用空参的构造方法
     * 2、不同参数的构造方法是重载的方式存在。可能会导致忘记写空参的构造方法
     * 3、后边要学习继承，多态，在继承与多态时，经常遇到儿子调用父亲的构造方法。
     * 如果使用默认的形式，父亲没有空参方法，则会报错。
     * <p>
     * 使用构造方法完成对对象属性的初始化。
     * <p>
     * this关键字在构造方法中的使用：
     * 构造方法在调用构造方法时，直接使用方法名是会报错的，无法找到。
     * 在构造方法中，使用this(参数)可以调用其他参数的构造方法
     * this(参数)必须放在构造函数的第一个位置
     */
    public Person() {
        System.out.println("调用了空参的构造方法");
        // System.out.println("其他要执行的业务功能代码");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("调用了默认的一个参数name的构造方法");
        // System.out.println("其他要执行的业务功能代码");
    }

    private Person(Integer age) {
        this.age = age;
        System.out.println("调用了私有的一个参数age的构造方法");
    }

    public Person(String name, Integer age) {
        // this.name = name;
        this(name);
        this.age = age;
        System.out.println("调用了公共的两个参数name和age的构造方法");
        // System.out.println("其他要执行的业务功能代码");
    }

    public void eat() {
        // 局部变量不赋值而需使用可能未被初始化而报错。
        int age = 2;
        if (age <= 3) {
            System.out.println("Person.eat().....我喝了!");
        } else {
            System.out.println("Person.eat().....我吃了！");
        }
    }

    public void show() {
        System.out.println("Person...show");
    }

    public void show(String s, int x) {
        System.out.println("Person..show" + s + "..." + x);
    }

    public void sleep() {
        System.out.println("Person.sleep().....我睡了！");
    }

    private void speak() {
        System.out.println("Person...private...speak");
    }

    private void speak(String s, int x, double d) {
        System.out.println("Person...private...speak..." + s + "..." + x + "..." + d);
    }

    protected void say() {
        System.out.println("Person...protected...say");
    }

    public static void staticMethod() {
        System.out.println("Person.staticMethod().....父类的静态方法");
    }

    public void hit() {
        System.out.println("Person.hit().....我爽了！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // System.out.println("ArrayList去除重复对象需重写equals()方法");
        // return this == o; // 比较两个对象的地址
        // 判断this和obj是不是同一个对象，如果是 ，地址应该相同
        if (this == o) {
            return true;
        }
        // o == null 健壮性判断
        // 判断obj传递的参数，是不是Person类的引用
        // 匿名子类做该判断会失败
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        // 比较对象 p1 和 p2 年龄是否相同
        // 对参数obj进行向下转型
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        // 存储到HashSet中的对象，保证对象的唯一性，必须重写hashCode()和equals()方法
        // System.out.println("实现去掉重复的元素，让姓名和年龄相同的对象，具有相同的哈希值。");
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {
        // this 后进来的，o 先进来的
        int i = this.name.compareTo(((Person) o).name);
        /*if (i == 0) {
          return this.age - ((Person) o).age;
        } else {
          return i;
        }*/
        return i == 0 ? this.age - ((Person) o).age : i;
    }

    /**
     * 如果要用TreeMap对key进行排序
     */
    /*
    final int compare(Object o1, Object o2) {
        return comparator == null
                ? ((Comparable< ? super K>)o1).compareTo((K)o2)
                : comparator.compare((K)o1, (K)o2);
    }
    */

    int i;

    Person test() {
        i++;
        return this;
    }

    public Person print() {
        System.out.println("i " + i);
        return null;
    }

    private static int j = 0;

    private static boolean flag(int i) {
        j += i;
        return true;
    }

    private static void method(int i) {
        boolean b;
        if (i > 0) {
            b = i > 10 & flag(1);
            b = i > 10 && flag(2);
        }
    }

    public static void main(String[] args) {
        method(0);
        System.out.println(j);
    }
}
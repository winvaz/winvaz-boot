package com.icore.winvaz.javase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * @Author wdq
 * @Create 2019-05-08 19:39
 */
public class FaceTestDemo {

    public static void main(String[] args) throws IOException {

        /**
         * 以下会跑空指针的是
         */
        /*String s = null;
        if( (s!=null) & (s.length()>0) ) { // 会
            System.out.println("NullPointerException");
        }
        if( (s!=null) && (s.length()>0) ) {

        }
        if( (s==null) | (s.length()==0) ) { // 会
            System.out.println("NullPointerException");
        }
        if( (s==null) || (s.length()==0) ) {

        }

        System.out.println("==========="); */

        /**
         * 以下的结果
         */
        /*String x = "hello";
        String y = "he" + new String("llo");
        String z = "he" + "llo";
        System.out.println(x == y); // false
        System.out.println(x == z); // true

        System.out.println("=============");*/

        /**
         * 继承关系
         * 子类可以访问父类非私有的成员(成员变量、成员函数)
         * 子类重写父类的方法
         *    指向子类的父类引用调用fun2(),必定是调用该方法
         */
        // 父类对象创建
        /*
        Father father = new Father("zhangsan"); // Father空参数构造函数
        System.out.println(Father.X); // Father.X
        System.out.println(Son.X); // Son.X
        System.out.println(father.Y); // Father.Y
        System.out.println("========");
        father.method(); // method()父类受保护的方法 Father.method()...Father.X Father.Y
        father.say(); // say()父类的方法 Father.say()....Father.X Father.Y
        System.out.println("=========");
        */
        // 继承：子类对象创建(子类中所有的构造函数默认都会访问父类中空参数的构造函数)
        /*
        Son son = new Son("zhangsan"); // Father空参数构造函数 Son空参数构造函数
        System.out.println(Son.X); // Son.X
        System.out.println(son.Y); // Son.Y
        System.out.println("======");
        son.method(); // method()父类受保护的方法 Father.method()...Father.X Father.Y
        son.say(); // // say()重写父类的方法 Son.say()Son.X Son.Y
        son.test(); // 子类特有的方法
        */
        // 多态：父类或接口的引用指向子类的实例对象
        /*
        Father fatherSon = new Son("zhangsan"); // // Father空参数构造函数 Son空参数构造函数
        System.out.println(Father.X); // Father.X
        System.out.println(Son.X); // Son.X
        System.out.println(fatherSon.Y); // Father.Y
        System.out.println("========");
        fatherSon.method(); // method()父类受保护的方法 Father.method()...Father.X Father.Y
        fatherSon.say(); // say()重写父类的方法 Son.say()Son.X Son.Y
        //fatherSon.test(); // 多态不能调用子类特有的方法
        */
        /*
        Father father = new Son();
        father.say(); // Son.say()
        father.method(10, 20);
        Father.eat();
        System.out.println("=========");
        Son son = new Son();
        son.method(10, 10);
        Son.eat();
        son.say();
        System.out.println(Son.X);
        */
        /**
         * 异常
         */
        /*
        try {
            throw new ExceptionB();
        } catch (ExceptionA e) {
            System.out.println("ExceptionA"); // ExceptionA
        } catch (Exception e) {
            System.out.println("Exception");
        }
        System.out.println("===========");
        */

        /**
         * 日期
         */
        /*
        Date date = new Date();
        System.out.println(date.getMonth() + "====" + date.getDate()); // 4(国外的日期比国内少一个月) 13
        */

        /**
         *
         */

        BigDecimal one = new BigDecimal("1");
        BigDecimal two = new BigDecimal("2");
        BigDecimal three = new BigDecimal("3");

        BigDecimal sum = new BigDecimal("0");

        sum.add(one);
        sum.add(two);
        sum.add(three);

        System.out.println(sum.doubleValue()); // 0.0
        BigDecimal a = new BigDecimal(5);
        BigDecimal b = new BigDecimal(3);
        // BigDecimal的两整数相除，能被整除得出结果。不能被整除抛出ArithmeticException算术异常
        System.out.println(b.divide(a));
        // 5/3，除不尽，抛异常
        // Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        //System.out.println(a.divide(b));


        // new Child("mike"); // 1 3 2

        System.out.println("=========");

        new Z(); // TTTT TT Y X Y Z
                        //  TTTT TT Y X Y Z

        System.out.println("========");
        /*
        int a = 0, b = 0;
        do {
            --b;
            a = a - 1;
        }while (a > 0);
        System.out.println(b); // -1
        */
        /*System.out.println("========");
        Parent parent = new Parent("south", "north");
        Parent child = new Child("east", "west");*/
        /*
        int i = 1, j = 10;
        do {
            if (i > j) {
                break;
            }
            j--;
        }while (++i < 5);
        System.out.println("i = " + i + "and j = " + j); // i = 5and j = 6
        */
        /*
        Integer i = 127;
        Integer j = 127;
        if (i == j) {
            // 如果一个数字在一个字节的范围中，是始终被缓冲的，相当于在常量池一直都有，什么时候用到，直接调用
            System.out.println(true); // 取值在127以内为true
        }
        */
        long l = 100;
        long l1 = 8888888888L;
        System.out.println(l);
        System.out.println(l1);

        double d = 10.2;
        //float f = 10.2; // 不兼容的类型：从double转换到float可能会损失
        float f = 10.2F;
        System.out.println(f);

        System.out.println(Math.round(11.5)); // 12
        System.out.println(Math.round(-11.5)); // -11
        /*
        System.out.println('a'); // a
        System.out.println('a' + 1); // 字符类型提升为int类型 a = 97 + 1 = 98
        System.out.println((char)('a' + 1)); // b
        System.out.println('0' + 0); // 48
        System.out.println((char)0); // 未知
        System.out.println('A' + 0); // 65
        System.out.println((char)('A' + 1)); // B
        System.out.println((char)126); // ~
        System.out.println("=========");
        */
        /*
        int x = 10;
        int y = 20;

        String s = "hi";
        String s1 = "dream";

        // 都是字符串拼接
        System.out.println(s + x);
        System.out.println(x + y + s1);
        System.out.println("========");
        */
        /*System.out.println("'单独使用++算术运算符");
        ++x; // x = x + 1
        System.out.println(x);
        x++;
        System.out.println(x);*/
        /*
        System.out.println("和其他运算符组合使用");
        int z = x++;
        System.out.println(x);
        System.out.println(z);
        System.out.println("========");

        int a = 3;
        System.out.println(a++ * ++a); // 3 * 5 = 15
        System.out.println(a++ + ++a); // 5 + 7 = 12
        System.out.println("=======");

        int b = 3510;
        b = b / 1000 * 1000;
        System.out.println(b); // 3000
        System.out.println("5+5="+5+5); // 55
        System.out.println("=======");
        */
        /*
        short c = 3;
        // c = c+2;//错误
        // c += 2;   //(short)(c + 2);

        int g = 10;
        int h = 20;

        // 单&时，左边无论真假，右边都进行运算
        //System.out.println(g == 10 & g == 20); // false
        //System.out.println(g == 20 & g == 10); // false
        //System.out.println(g == 10 & g == 10); // true

        // 双&时，如果左边为真，右边参与运算，如果左边为假，那么右边不参与运算。
        //	“|”和“||”的区别同理，双或时，左边为真，右边不参与运算。
        //System.out.println(g == 10 && g == 20); // false
        //System.out.println(g == 20 && g == 10); // false
        System.out.println(g == 10 && g == 10); // true
        System.out.println("========");
        */
        /**
         * a,switch语句选择的类型有六种：byte，short，int ， char。1.7版本可以接收String字符串。1.5版本的时候可以接收枚举
         * 	b,case与default之间没有顺序。先执行第一个case，没有匹配的case执行default。
         * 	c,结束switch语句的两种情况：遇到break，执行到switch语句结束。
         * 	d,如果匹配的case或者default没有对应的break，那么程序会继续向下执行，运行可以执行的语句，直到遇到break或者switch结尾结束。
         */
        /*
        char k = '1';
        switch (k) {
            default:
                System.out.println("d");
                break;
            case 0:
                System.out.println("a");
                break;
            case 1:
                System.out.println("b");
            case 2:
                System.out.println("c");
                break;
        }
        */
        System.out.println("=======");
        /*用*号打印矩形(for的嵌套循环)*/
        /*
        for (int m = 0; m < 4; m++) { // 外循环控制列的个数
            for (int n = 0; n < 4; n++) { // 内循环控制行的个数
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("=======");
        */
        /*打印三角形*/
        /**
         *    *
         *   * *
         *  * * *
         * * * * *
         */
        /*
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < m + 1; n++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("=======");
        */
        /*打印倒三角形*/
        /*
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < m; n++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (2 * (4 - m) - 1); i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("==========");
        */
        /*打印九九乘法表*/
        /*
        for (int m = 1; m <= 9; m++) {
            for (int n = 1; n <= m; n++) {
                System.out.print(n + "*" + m + " = " + (m * n) + "\t");
            }
            System.out.println();
        }
        System.out.println("=========");
        */
        /**
         * 计算阶乘
         * 提示：5的阶乘是：5 * 4 * 3 * 2 * 1
         * 初步分析：
         * 1.number变量来记录要求阶乘的这个数，count记录循环次数，result记录最后的结果
         * 2.每次循环之后放number - 1
         * 3.开始的result即为number本身，后边一次乘以number
         */
        /*
        int n = 6;
        int  count = n - 1;
        int result = n;
        for (int m = 0; m < count; m++) {
            result = result * (n - 1);
            n--;
        }
        System.out.println(result);
        System.out.println("======");
        */
        /**
         * 不使用第三方变量交换两数
         */
        /*
        int a = 10;
        int b = 20;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + "与" + b); // 20和10
        */

        //System.out.println(m());
        /**
         * 数组的三种定义格式
         * 格式一：数据类型[] 变量名称 = new 元素类型[元素个数或数组长度]
         * int[] arrays = new int[10]
         * 格式二：数据类型[] 变量名称 = new 元素类型[]{元素1，元素2，。。。。元素n}
         * int[] arrays = new int[]{1, 2, 3, 4}
         * 格式三：数据类型[] 变量名称 = {元素内容}
         * int[] arrays = {1, 2, 3, 4}
         */
        /*
        int[] arrays = {1, 3, 5, 7, 8, 3, 2, 6, 10, 22, 34, 40};
        System.out.println(arrays.length); // 正确 12
        System.out.println(arrays.length - 1); // 错误 11
        int count = 0;
        for (int i = 0; i < arrays.length; i++) {
            //count += arrays[i];
            if (count < arrays[i]) {
                count = arrays[i];
            }
        }
        System.out.println(count);
        */
        /**
         * 多个引用指向同一个对象
         * 内存地址引用
         */
        /*
        int[] a = {1, 2, 3, 4, 5};
        System.out.println("数组a修改前:" + a[2]); // 3
        int[] b = {6, 7, 8, 9, 10};

        // 赋值
        int[] c = a;
        // 修改值
        c[2] = 33;
        System.out.println("数组c:" + c[2]); // 33
        System.out.println("数组a修改后:" + a[2]); // 33, 不是原来的3
        */
        /*
        int[] a = new int[5];
        System.out.println(a[2]); // 基本数据类型的数组没有值默认为0

        String[] s = new String[5];
        System.out.println(s[2]); // 引用数据类型的数组没有值默认为null
        */
        /*
        int a = 10;
        int b = 20;
        System.out.println("main函数: a = " + a + ", b = " + b);
        test(a, b);
        */
        /*
        byte b = 3;
        int x = 4;
        x = x + b; // b会自动提升为int类型进行运算。
        // 强制类型转换
        byte b = 3;
        b = b + 4;// 报错
        b = (byte)b+4;// 强制类型转换，强制将b+4的结果转换为byte类型，再赋值给b。
        // 思考：
        byte b1=3,b2=4,b;
        b=b1+b2; // 有错
        b=3+4;
        */
        /*
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 2) {
                continue;
            }
            if (i % 2 == 0) {
                System.out.print(i);
            }
        }
        */
        /*
        int a = 1;
        int b = 10;
        do {
            b -= a;
            a++;
        } while (b-- < 0);
        System.out.print(b);
        */
        /*
        SimpleTest test = new SimpleTest();
        System.out.print((test).str); // B
        System.out.print(((SimpleTestBase) test).str); // A
        */

        /*
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(3);
        System.out.println(a.divide(3)); // Error 不兼容的类型，int无法转换成java.Math.BigDecimal
        */
        /*
        List list = new ArrayList();
        System.out.println(list.size()); // 0
        */
        /*
        try {
            int i = 1 / 0;
            System.out.println('A');
        } catch (Exception e) {
            System.out.println('B'); // B
            return; // 没有return会打印D
        } finally {
            System.out.println('C'); // C
        }
        System.out.println("D");
        */
        /*
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
        */

        /**
         * 1, 2, 3, 4四个数字，组成互不相同且不重复的三位数有哪些，个数是多少
         */
        /*
        int sum = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k < 5; k++) {
                    if (i != j && j != k && k != i) {
                        int a = 100 * i + 10 * j + k;
                        System.out.print(a + " ");
                        sum += 1;
                    }
                }
            }
        }
        System.out.println(sum);
        */

        /**
         * 递归计算1+2+3+4+。。。到总数为44980060时，计算多少次
         */
        //System.out.println(new FaceTestDemo().test(1L));

        /**
         * 习题
         */
        /*
        Manager manager = new Manager("张三", 20, 20000);
        System.out.println(manager.getName());
        System.out.println(manager.getAge());
        System.out.println(manager.getSalary());
        manager.eat();
        manager.work();
        manager.sleep();
        */

        /**
         *System.arraycopy()
         */
        /*
        int[] ints1 = new int[]{1, 2, 3, 4, 5};
        int[] ints2 = new int[]{6, 7, 8, 9, 10};
        System.arraycopy(ints1, 0, ints2, ints2.length + 1, 5);
        for (int i = 0; i < ints2.length; i++) {
            System.out.println(ints2[i]);
        }
        */

    }




    /*
    public static void test(int a, int b) {
        System.out.println("");
    }*/

    /*
    public static int m() {
        int count = 0;
        int i = 0;
        for (; i < 100; i++) {
            if (i % 2 == 0) {
                count++;
                if (count == 3) {
                    break;
                }
            }
        }
        return i;
    }
    */
}

class SimpleTestBase {
    String str = "A";
    // 公共的成员变量
    // public String alibaba;

    /*
    public void method() {
        // 同一个方法内的不同代码块定义相同的变量
        if ("A".equals(str)) {
            final int x = 15;
        }
        if (!"A".equals(str) ) {
            final int x = 15;
        }
    }
    */
}

class SimpleTest extends SimpleTestBase {
    String str = "B";
    // 子类与父类同名成员变量
    // public String alibaba;
}

class Father {
    private String name;
    private Integer age;
    static String X = "Father.X";
    protected String Y = "Father.Y";

    /*
    public Father() {
        System.out.println("Father空参数构造函数");
    }
    */
    public Father(String name) {
        //this(); // 调用无参构造函数
        System.out.println("Father一个参数构造函数");
        this.name = name;
    }

    public Father(String name, Integer age) {
        //this.name = name;
        this(name); // new Father(name) 调用一个参数的构造函数
        System.out.println("Father两个参数构造函数");
        this.age = age;
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

    // 受保护修饰符
    protected void method() {
        System.out.println("Father.method()..." + X + " " + Y);
    }

    public Father say() {
        //eat(); // 非静态方法可以调用静态方法
        System.out.println("Father.say()...." + X + " " + Y);
        return null;
    }

    static void eat() {
        //say(); // 静态方法不可以调用非静态方法
        System.out.println("Father.eat()....." + X);
    }
}

class Son extends Father {
    static String X = "Son.X";
    protected String Y = "Son.Y";

    /*
    public Son() {
        System.out.println("Son空参数构造函数");
    }
    */
    public Son(String name) {
        super(name);
        System.out.println("Son一个参数构造函数");
    }

    public Son(String name, Integer age) {
        super(name, age);
        System.out.println("Son两个参数构造函数");
    }

    // 可以重写
    /*public int method1(int a, int b) {
        return 0;
    }*/
    // 可以
    /*private int method1(int a, int b) {
        return 0;
    }*/

    // 可以
    /*
    private int method1(int a, long b) {
        return 0;
    }
    */

    // 可以
    /*
    public short method1(int a, int b) {
        return 0;
    }
    */
    /*
    static protected int method1(int a, int b) {
        return 0;
    }
    */

    // 如果返回值类型为引用数据类型时，子类返回值类型必须为父类返回值类型的子类
    @Override
    public Son say() {
        //super.say(); // 在子类覆盖方法中，继续使用被覆盖的方法可以通过super.函数名获取。
        System.out.println("Son.say()" + X + " " + Y);
        return null;
    }

    // 参数不同，函数名相同同样符合重载规律
    public Integer say(int x) {
        return null;
    }
    /*
    void eat() {
        // Son cannot override static method eat()
    }
    */

    public void test() {
        System.out.println("子类特有的方法");
    }
}

/**
 * 异常
 */
class ExceptionA extends Exception {

}

class ExceptionB extends ExceptionA {

}

class People {
    String name;

    public People() {
        System.out.println(1);
    }

    public People(String name) {
        System.out.println(2);
        this.name = name;
    }


}

/*class Child extends People {
    People father;

    public Child() {
        System.out.println(4);
    }

    public Child(String name) {
        System.out.println(3);
        this.name = name;
        father = new People(name + ":F");
    }
}*/

class Child extends Parent {

    public Child(String onw, String two) {
        super(onw, two);

        // 构造器中不能写方法
        /*public void print() {
            System.out.println(one + "to" + two);
        }*/
    }
}

class X {
    // Y y = new Y();
    /*
    static {
        System.out.println("TTTT"); // 第一
    }
    */
    /*
    private int a;

    public X() {
        System.out.println(this.getClass().getName());
        System.out.println(this.getClass());
        System.out.println("X"); // 第四
        this.a = 1000;
    }
    */

    Y y = new Y();

    X() {
        super();
        System.out.println("X");
    }
}

class Y {
    /*
    public Y() {
        System.out.println("Y"); // 第三，第五
    }
    */

    int a = 19;

    Y() {
        System.out.println("Y");
    }
}

class Z extends X {
    // Y y = new Y();
    /*
    static {
        System.out.println("TT"); // 第二
    }
    */
    private int b;
    /*
    public Z() {
        System.out.println("Z"); // 第六
    }
    */

    Y y = new Y();

    Z() {
        super();
        System.out.println("Z");
    }
}

class Parent {
    String one, two;

    public Parent(String onw, String two) {
        this.one = onw;
        this.two = two;
        // 构造器中不能写方法
        /*public void print() {
            System.out.println(one);
        }*/
    }
}

/**
 * 修饰interface
 */
interface SB {
    String name = null;

    void method();
}

/**
 * 面试题
 */
class Test {

    /**
     * 静态代码块
     */
    static {
        System.out.println("静态代码块。。。。。"); // 第一执行。随着类的加载而加载，只初始化一次
    }

    /**
     * 构造代码块
     */ {
        System.out.println("构造代码块。。。。。"); // 第二执行。new对象时调用，new几次调用几次
    }

    /**
     * 构造函数
     */
    public Test() {
        System.out.println("构造函数。。。。。"); // 第三执行。同上构造代码块
    }

    public void method() {
        {
            System.out.println("局部代码块。。。。。"); // 第四执行。方法调用几次执行几次。
        }
    }

    /*static boolean foo(char c) {
            System.out.println(c); // A B D C B D C B
            return true;
        }*/
    /*
    public static void operate(StringBuilder x, StringBuilder y) {
        x.append(y);
        y = x;
    }
    */
    /*
    int x;
    Integer y;
    boolean f;
    Boolean F;
    */
    public static void chage(String a, String b) {
        System.out.println("a = " + a + ", b = " + b); // a = 10, b = 20
        a = b;
        a = a + b;
        System.out.println("a = " + a + ", b = " + b); // a = 40, b = 20
    }

    public static void main(String[] args) {
        /*
        Test test = new Test();
        //静态代码块。。。。。
        //构造代码块。。。。。
        //构造函数。。。。。
        //局部代码块。。。。。
        test.method();
        System.out.println("=======");
        Test test1 = new Test();
        //构造代码块。。。。。
        //构造函数。。。。。
        //局部代码块。。。。。
        test1.method();
        */
       /* int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }*/

       /*
        StringBuilder x = new StringBuilder("A");
        StringBuilder y = new StringBuilder("B");
        operate(x, y);
        System.out.println(x + ", " + y); // AB, B
        */

       /*
        System.out.println(test.x); // 基本数据类型的默认值为0
        System.out.println(test.y); // 引用数据类型的默认值为null
        System.out.println(test.f); // 布尔类型的基本数据类型默认值为false
        System.out.println(test.F); // 布尔类型的引用数据类型默认值为null
        */

        String a = "10";
        String b = "20";
        System.out.println("a = " + a + ", b = " + b); // a = 10, b = 20
        chage(a, b);
        System.out.println("a = " + a + ", b = " + b); // a = 10, b = 20

        try {
            System.out.println("中".getBytes("UTF-8").length);
            System.out.println("中".getBytes("GBK").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("============");
        // var 关键字 JDK 11
        /*
        var x = new StringBuilder();
        x.append(a);
        x.append(b);
        System.out.println("x = " + x.toString());
        */
        Person person = new Person();
        /*
        int n = 15;
        person.setAge(n);
        System.out.println(person.getAge()); // 15
        n = 20;
        System.out.println(person.getAge()); // 15
        */
    }
}

/**
 * 设计人、餐厅员工、餐厅经理、厨师4个类。
 * 强制要求：
 * 本关系中存在两层继承关系
 * 对于人类必须有吃饭、睡觉的方法，以及姓名，年龄的属性
 * 对于员工必须有薪资属性与上班的方法，要求上班的方法内有System.out.println(“上班打卡”)。
 * 对于餐厅经理与厨师除了上班打卡外，
 * 分别均有自己的上班方式：如经理上班的内容还包括处理客户矛盾等，
 * 厨师上班的内容还包括炒菜。（要求使用方法重写）
 */
class Employee extends Person {
    private Integer salary;

    public Employee() {

    }

    public Employee(String name, Integer age, Integer salary) {
        super(name, age);
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void work() {
        System.out.println("上班打卡。");
    }
}

class Manager extends Employee {

    public Manager() {

    }

    public Manager(String name, Integer age, Integer salary) {
        super(name, age, salary);
    }

    @Override
    public void work() {
        super.work();
        System.out.println("处理客户矛盾。");
    }
}

class Cook extends Employee {

    public Cook() {

    }

    public Cook(String name, Integer age, Integer salary) {
        super(name, age, salary);
    }

    @Override
    public void work() {
        super.work();
        System.out.println("炒菜。");
    }
}

/**
 * @author wdq
 * @Description 数据类型默认值
 * @create 2020/3/3 11:55
 */
class DataType {
    /*
     * 基本数据类型
     */
    byte by;
    short s;
    int i;
    long l;
    float f;
    double d;
    char c;
    boolean bo;
    /*
     * 包装数据类型
     */
    Byte By;
    Short S;
    Integer I;
    Long L;
    Float F;
    Double D;
    Character C;
    Boolean Bo;

    public void print() {
        /*
         * byte：0
           short:0
           int:0
           long:0
           float:0.0
           double:0.0
           char: 
           boolean:false
         */
        System.out.println("byte：" + by);
        System.out.println("short:" + s);
        System.out.println("int:" + i);
        System.out.println("long:" + l);
        System.out.println("float:" + f);
        System.out.println("double:" + d);
        System.out.println("char:" + c);
        System.out.println("boolean:" + bo);
        System.out.println("===========");
        /*
         * Bbyte：0
            Short:null
            Integer:null
            Long:null
            Float:null
            Double:null
            Character:null
            Boolean:null
         */
        System.out.println("Bbyte：" + by);
        System.out.println("Short:" + S);
        System.out.println("Integer:" + I);
        System.out.println("Long:" + L);
        System.out.println("Float:" + F);
        System.out.println("Double:" + D);
        System.out.println("Character:" + C);
        System.out.println("Boolean:" + Bo);
    }

    public static void main(String[] args) {
        // new DataType().print();
        // short s = 1234;
        // int i = 1234567;
        // int x = s + i;
        // short y = s + i; // 编译报错
        // 计算前N个自然数之和
        // int n = 100;
        // long sum = 0;
        // sum += ((1 + n) * n) / 2;
        // System.out.println(sum);
        // System.out.println("========");
        // boolean b = 5 < 3;
        // boolean result = b & (5 / 0 > 0);
        // System.out.println(result);
        // System.out.println("========");
        // int age = 7;
        // boolean isPrimaryStudent = age > 6 && age < 12;
        // System.out.println(isPrimaryStudent ? "Yes" : "No");
        // System.out.println("========");
        // JDK 13 多行字符串表示
        // String s = """SELECT * FROM users where id > 100""";
        // String s = "Hello";
        // String t = s;
        // s = "World";
        // System.out.println(t); // Hello
        // int[] ns = new int[1];
        // System.out.println(ns[0]);
        // String[] names = {"ABC", "XYZ", "Zoo"};
        // String s = names[1];
        // names[1] = "Cat";
        // System.out.println(s);

        /*
        // 成绩提高百分比
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入上次考试成绩：");
        int last = scanner.nextInt();
        System.out.print("请输入本次考试成绩：");
        int now = scanner.nextInt();
        double rate = (now - last) * 1.0 / last;
        // 计算百分比
        System.out.printf("成绩提高%.2f%%\n", rate * 100);
        */

        /*
        // 计算BMI
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的体重(kg):");
        double weight = scanner.nextDouble();
        System.out.print("请输入您的身高(m):");
        double hight = scanner.nextDouble();
        double BMI = weight / (hight * hight);
        System.out.println("BMI:" + BMI);
        if (BMI < 18.5) {
            System.out.println("您的BMI小于18.5，过轻");
        } else if (BMI >= 18.5 && BMI < 25) {
            System.out.println("您的BMI在18.5~25之间，正常");
        } else if (BMI >= 25 && BMI < 28) {
            System.out.println("您的BMI在25~28之间，过重");
        } else if (BMI >= 28 && BMI < 32) {
            System.out.println("您的BMI在28~32之间，肥胖");
        } else {
            System.out.println("您的BMI高于32，非常肥胖");
        }
        */

        //
        int opertion = 1;
        switch (opertion) {
            case 1:
                System.out.println("== ");
            case 2:
                System.out.println("==");
            default:
                throw new IllegalStateException("Unexpected value: " + opertion);
        }
    }
}
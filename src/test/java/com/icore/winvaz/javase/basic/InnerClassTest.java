package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;
/**
 * 内部类
 * 将一个类定义在另一个类的里面，对里面那个类就称为内部类（内置类，嵌套类）。
 * 内部类是符合面向对象的思想的。如人与心脏可以理解成心脏是人的内部类
 * 编译时会同时生成两个class文件
 * 访问特点：
 * 内部类可以直接访问外部类中的成员，包括私有成员。
 * 而外部类要访问内部类中的成员必须要建立内部类的对象。
 */

/**
 * Description: 内部类
 * 将一个类定义在另一个类的里面，对里面那个类就称为内部类(内置类，嵌套类)，外部的类称为外部类
 * 内部类是一个相对的概念。
 * 访问特点：
 * 内部类可以直接访问外部类的成员，包括私有成员。
 * 外部类要访问内部类的成员时需建立内部类对象。
 * <p>
 * 匿名内部类和静态内部类时比较常用的方式。
 *
 * @author wendq
 * @create 2018/11/7 16:02
 */
public class InnerClassTest {
    /**
     * 内部类可以是静态和非静态的，它可以出现在属性定义，方法体和表达式中，甚至可以匿名出现
     * 外部类与内部类之间使用$符号分隔。
     * 匿名内部类使用数字进行编号，
     * 方法内部类使用编号加方法名称来标识是哪个方法
     */
    // 成员属性
    // public String name = "张三";
    // 私有成员属性
    private String name;
    // 静态属性
    private static String staticField;

    // 提供get/set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 成员内部类
     */
    // 可以私有修饰符或静态(static)修饰
    private class InstanceInnerClass {
        // 成员内部类属性
        private String instanceInnerClassNature;
        // 内部类的静态字段必须是final，并显示赋值
        private static final String finalStaticField = "Str";

        public String getInstanceInnerClassNature() {
            return instanceInnerClassNature;
        }

        public void setInstanceInnerClassNature(String instanceInnerClassNature) {
            this.instanceInnerClassNature = instanceInnerClassNature;
        }

        // 成员内部类非静态方法(非静态内部类不能有静态方法)+访问外部类私有成员变量
        // OuterClass.this表示外部类的引用。
        public void InstanceInnerClassNoStaticMethod() {
            System.out.println("成员内部类的show()。。。。访问私有属性name为:" + InnerClassTest.this.name);
        }
    }

    /**
     * 静态内部类的好处
     * 1.作用域不会扩散到包外。
     * 2.可以通过"外部类.内部类"的方式直接访问
     * 3.内部类可以访问外部类中的所有静态属性和方法。
     */
    // 静态内部类
    // 可以私有修饰符或静态(static)修饰
    private static class StaticInnerClass {
        // 静态内部类成员属性
        private String staticInnerClassNature;
        // 内部类的静态字段必须是final
        private static String finalStaticField;

        public String getStaticInnerClassNature() {
            return staticInnerClassNature;
        }

        public void setStaticInnerClassNature(String staticInnerClassNature) {
            this.staticInnerClassNature = staticInnerClassNature;
        }

        // 静态内部类的静态方法
        // 访问外部类的静态属性
        public static void staticInnerClassStaticMethod() {
            // 调用getClass()时调用的是this.getClass()，而静态方法没有this
            // System.err.println("Something awful happened in " + getClass());
            // 使用此方式，new StaticTest(){}会建立StaticTest的匿名子类的一个匿名对象，getEnclosingClass得到其外部类，也就是包含这个静态方法的类
            System.out.println(new Object() {
            }.getClass().getEnclosingClass());
            System.out.println("静态内部类的静态方法。。。。instanceInnerClassStaticMethod" + staticField);
        }

        // 静态不能调用非静态
        public void show() {
            System.out.println("静态内部类的show()。。。。");
        }
    }

    /**
     * 局部内部类
     */
    public void partMethod() {
        // 1.7以前访问所在局部中的局部变量，但必须是被final修饰的。(为了延长局部变量的生命周期)
        // 1.8之后不需要手动添加final修饰，因为JVM自动在底层添加final修饰了，也就是"语法糖"
        // 局部变量的数据类型使用基本数据类型
        int sex = 1;

        // // 如果定义在局部变量，则不能再其他类中直接创建其实例对象
        // 局部内部类不能有访问权限修饰符
        // 编译后的类名为OuterClassTest$1MethodClass1和OuterClassTest$2MethodClass2
        class PartMethodClass {
            // 局部内部类成员变量
            private Integer age;

            public Integer getAge() {
                return age;
            }

            public void setAge(Integer age) {
                this.age = age;
            }

            // 局部内部类不能有静态方法
            // 局部内部类非静态方法
            public void show() {
                System.out.println("局部内部类的show()。。。。访问私有属性name为:" + name + "，age为:" + age + "，sex为:" + sex);
            }
        }
        // 局部内部类需要先创建后实例调用
        // 调用局部内部类的方法的调用方式
        new PartMethodClass().show();

        // System.out.println(age); // 报错
        // 外部类需访问内部类时需建立内部类对象
        // 私有后加入数据校验
        // InstanceInnerClass instanceInnerClass = new InstanceInnerClass();
        // System.out.println(instanceInnerClass.getInstanceInnerClassNature());

        /**
         * 匿名内部类
         * 匿名内部类需要多态
         */
        // 匿名内部类
        // 编译后的类名为OuterClassTest$1和OuterClassTest$2
        (new Thread() {
            public void show() {
                System.out.println("匿名内部类的show()。。。。访问私有属性name为:" + name);
            }
        }).start();
        /*
        (new Thread() {
        }).start();
        */
    }

    /**
     * 内部类是个接口
     */
    interface InnerClass {
        void show();
    }

    /**
     * 第一种方式 Outer.method.show();
     */
    public static InnerClass method = () -> System.out.println("Hello World");

    /**
     * 第二种方式 O.method().show();
     */
    /*
    public static InnerClass method() {
        InnerClass innerClass = new InnerClass() {
            @Override
            public void show() {
                System.out.println("Hello World !");
            }
        };
        return innerClass;
    }
    */
    // 简化方式
    public static InnerClass method() {
        return () -> System.out.println("Hello World !");
    }

    @Test
    public void test() {
        InnerClassTest outerClassTest = new InnerClassTest();
        // 练习题
        // 第一种方式，属性
        InnerClassTest.method.show();
        // 第二种方式，方法
        InnerClassTest.method().show();
        System.out.println("==============");

        // 把name属性私有化
        outerClassTest.setName("张三");
        System.out.println(outerClassTest.getName());
        // name属性私有化，无法访问(在同一个类中可以访问)
        System.out.println(outerClassTest.name);
        // JDK源码ConcurrentHashMap的静态内部类Node
        System.out.println("================");
        /**
         * 内部类定义在成员位置上
         *      访问格式：
         *             Outer.Inner x = new Outer().new Inner();
         *      或外部类中，直接创建内部类对象调用其方法
         *      可以被private成员修饰符修饰，保证数据安全。
         *      可以被static成员修饰符修饰，方便调用。
         *      被static修饰的内部类只能访问外部类中的静态成员。
         *      Static修饰后的访问格式：
         *      Outer.Inner  x = new Outer.Inner();
         *      调用静态内部类中的静态方法：
         *      Outer.Inner.method();
         * 注意事项：
         * 		在内部类的学习当中，需要完全记住其定义及使用格式。尤其是android的同学，内部类会在开发中大量使用。
         *
         * 	内部类可以定义在成员位置与局部位置
         *
         * 	内部类定义在成员位置：即直接定义在类里
         * 			创建成员内部类的格式：
         * 				Outer.Inner x = new Outer().new Inner();
         * 			Outer.Inner相当于一种新的数据类型：Outer当中的Inner类型
         * 			想创建内部类实例对象就必须创建外部类的实例对象，即分别调用Outer与Inner的构造方法(不考虑静态的情况下)
         *
         * 			或者，在外部类的方法中直接创建其内部类的实例进行访问
         */
        // 内部类无法直接创建对象
        // Inner inner = new Inner();

        // 内部类的访问方式
        // 内部类定义在成员位置的访问格式
        // InnerClassTest.InstanceInnerClass instanceInnerClass = this.new InstanceInnerClass();
        InnerClassTest.InstanceInnerClass instanceInnerClass = new InnerClassTest().new InstanceInnerClass();
        // 调用成员变量
        System.out.println(instanceInnerClass.getInstanceInnerClassNature());
        // 调用成员方法
        instanceInnerClass.InstanceInnerClassNoStaticMethod();

        // 静态内部类的访问方式
        InnerClassTest.StaticInnerClass staticInnerClass = new InnerClassTest.StaticInnerClass();
        // 调用静态内部类成员变量
        System.out.println(staticInnerClass.getStaticInnerClassNature());
        // 调用静态内部类的非静态成员方法
        staticInnerClass.show();
        // 调用静态内部类的静态成员方法，直接调用
        InnerClassTest.StaticInnerClass.staticInnerClassStaticMethod();

        // 内部类定义在局部位置的访问格式
        new InnerClassTest().partMethod();

        // 匿名内部类
        AnonymousOuter anonymousOuter = new AnonymousOuter();
        anonymousOuter.getMethod();
        Compter compter = new Compter();
        Meachine meachine = new Compter();

        MeachineTool.meachineShow(compter);
        MeachineTool.meachineShow(meachine);
        MeachineTool.meachineShow(new Compter());

        // 如果构造参数列表的结束小括号后面跟一个开始大括号，，就是在定义匿名内部类
        MeachineTool.meachineShow(new Meachine() {
            @Override
            public void show() {
                System.out.println("机器被使用了");
            }
        });
    }
}

/**
 * 匿名内部类
 * 匿名内部类需要多态
 */
class AnonymousOuter {

    static String NAME = "近平";

    public static void getMethod() {
        // new 类名或接口() {覆盖类或接口代码，(也可以自定义内容)}
        new Meachine() {
            @Override
            public void show() {
                System.out.println("展示show()");
            }
        }.show();

        Meachine meachine = new Meachine() {
            @Override
            public void show() {
                System.out.println("展示show()");
            }
        };
        meachine.show();

        Meachine meachine1 = new Compter();
        meachine1.show();

        Meachine meachine2 = new Meachine() {
            @Override
            public void show() {
                System.out.println("展示show()");
            }
        }.createMeachine();
        meachine2.show();
    }
}

/**
 * 抽象类
 */
abstract class Meachine {
    public String name;

    public abstract void show();

    public Meachine createMeachine() {
        Meachine meachine = new Compter();
        return meachine;
    }
}

/**
 * 实现类
 */
class Compter extends Meachine {

    @Override
    public void show() {
        System.out.println("电脑被使用了");
    }
}

class MeachineTool {
    public static void meachineShow(Meachine meachine) {
        meachine.show();
    }
}

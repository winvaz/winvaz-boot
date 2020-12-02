package com.icore.winvaz.javase.basic;

import com.icore.winvaz.javase.Person;
import org.junit.jupiter.api.Test;

/**
 * 继承使子类能够继承父类，获得父类的部分属性和行为，使模块更具有复用性。
 * 继承是面向对象编程技术的基石，允许创建具有逻辑等级结构的类体系，形成一个继承树，
 * 让软件在业务多变的客观条件下，某些基础模块可以被直接复用，间接复用或增强复用，
 * 父类的能力通过这种方式赋予子类。继承把枯燥的代码世界变得更有层次感，具有扩展性，为多态打下语法基础。
 * <p>
 * 继承的判断标准既是否符合里氏代换原则(LSP)
 * LSP是指任何父类能够出现的地方，子类都能够出现。
 * <p>
 * 继承滥用的危害性为方法污染和方法爆炸
 * 方法污染：父类具备的行为，通过继承传递给子类，子类并不具备执行此行为的能力
 * 方法爆炸：继承树不断扩大，底层类拥有的方法虽然都能够执行，但是由于方法众多，
 * 其中部分方法并非与当前类的功能定位相关，很容易在实际编程中产生选择困难症。
 * 提倡组合优先原则来扩展类的能力，即优先采用组合或聚合的类关系来复用其他类的能力，而不是继承。
 */

/**
 * 多个类中存在相同属性和行为时，将这些内容抽取到单独一个类中，那么多个类无需再定义这些属性和行为，只要继承那个类即可。
 * 多个类可以称为子类，单独这个类称为父类或者超类。
 * 子类可以直接访问父类中的非私有的属性和行为。
 * 通过 extends 关键字让类与类之间产生继承关系。
 * class SubDemo extends Demo{}
 * 继承的出现提高了代码的复用性。
 * 继承的出现让类与类之间产生了关系，提供了多态的前提。
 * <p>
 * 继承的特点：
 * Java只支持单继承，不支持多继承。
 * 一个类只能有一个父类，不可以有多个父类。
 * class SubDemo extends Demo{} //ok
 * class SubDemo extends Demo1,Demo2...//error
 * <p>
 * Java支持多层继承(继承体系)
 * class A{}
 * class B extends A{}
 * class C extends B{}
 * <p>
 * 任何类都直接或者间接的继承自Object
 * 定义继承需要注意：
 * 不要仅为了获取其他类中某个功能而去继承
 * 类与类之间要有所属( " is a " )关系，xx1是xx2的一种。
 * <p>
 * 继承的注意事项：
 * 1、类不支持多继承
 * 2、类是支持多层继承：当多层继承时，子类会一直向上寻找对应成员。如果均没有，则报错。
 * 3、所有的类(数组)都实现了Object的方法。所有的类均继承或间接继承自Object。
 * 4、在使用System.out.println(引用变量);实际上是在打印之前调用了该对象的toString()然后再打印。
 * 5、不要因为部分功能而继承另外一个类。
 * 6、必须符合"is a"即XX是XX的一种。
 * 7、如果说就为了一种功能而想与该类产生关系，则可以考虑使用接口。
 */
/**
 * 类关系
 *          关系是指事物之间存在单向或相互的作用力或者影响力的状态。分为两种：有关系和无关系。
 *          没关系：涉及业务、架构、模块边界的问题，由于业务模型的抽象角度不同而不同，
 *                      如果找到没有关系的点，就可以如庖丁解牛一样，进行架构隔离，模块解耦。
 *          有关系：
 *                      继承 extends(is-a)
 *                      实现 implements(can-do)
 *                      组合 类是成员变量(contains-a)
 *                      聚合 类是成员变量(has-a)
 *                      依赖 是除组合和聚合外的单向弱关系，比如使用一个类的属性，方法，或者作为方法的参数输入，或返回值输出。
 *                      关联 是互相平等的依赖关系(links-a)
 */
public class ExtendsTest {
    @Test
    public void test() {
        /*
        Student student = new Student("张三", 10, 99);
        System.out.println(student.hello());
        student.sleep();
        */

        System.out.println(getClass());
        Person person = new Person("张三", 100);
        // 父类有hit()方法
        person.hit(); // Person.hit().....我爽了！
        Student student = new Student();
        // 子类没有hit()方法，但因为继承的关系，调用了父类的hit()方法
        student.hit(); // Person.hit().....我爽了！
        // 子类特有方法
        student.hello();


        // 向上转型
        // 如果一个引用变量的类型是Student，那么它可以指向一个Student类型的实例：
        // Student s = new Student(); // 正确

        // 如果一个引用类型的变量是Person，那么它可以指向一个Person类型的实例：
        // Person p = new Person(); // 正确

        // 如果Student是从Person继承下来的，那么，一个引用类型为Person的变量，能否指向Student类型的实例
        // 这是因为Student继承自Person，因此，它拥有Person的全部功能。
        // Person类型的变量，如果指向Student类型的实例，对它进行操作，是没有问题的！

        // 这种把一个子类类型安全地变为父类类型的赋值，被称为向上转型（upcasting）。
        // Person p = new Student(); // 正确

        // 向上转型实际上是把一个子类型安全地变为更加抽象的父类型：
        /*
        Student s = new Student();
        Person p = s; // upcasting, ok
        Object o1 = p; // upcasting, ok
        Object o2 = s; // upcasting, ok
        */

        /*
        // 向下转型
        // 如果把一个父类类型强制转型为子类类型，就是向下转型（downcasting）
        Person p1 = new Student(); // upcasting, ok
        Person p2 = new Person();
        Student s1 = (Student) p1; // ok
        // Student s2 = (Student) p2; // runtime error! ClassCastException!

        // 为了避免向下转型出错，Java提供了instanceof操作符，可以先判断一个实例究竟是不是某种类型
        System.out.println(p2 instanceof Person); // true
        System.out.println(p2 instanceof Student); // false
        System.out.println(p1 instanceof Student); // true
        System.out.println(p1 instanceof Person); // true

        // 如果一个引用变量为null，那么对任何instanceof的判断都为false。
        // String n = null;
        // System.out.println(n instanceof Student); // false
        */
    }
}

// 定义一个Student类
// Java使用extends关键字来实现继承
// 在OOP的术语中，我们把Person称为超类（super class），父类（parent class），基类（base class），
// 把Student称为子类（subclass），扩展类（extended class）。

/**
 * 子类的实例化过程
 * 子类中所有的构造函数默认都会访问父类中空参数的构造函数
 * 因为每一个构造函数的第一行都有一条默认的语句super();
 * 子类会具备父类中的数据，所以要先明确父类是如何对这些数据初始化的。
 * 当父类中没有空参数的构造函数时，子类的构造函数必须通过this或者super语句指定要访问的构造函数。
 */
class Student extends Person {
    /**
     * 成员变量
     * 就近原则：局部变量>成员变量>父类成员变量>父类的父类
     * 成员方法
     * 子类可以直接使用父类的非私有方法
     * 当子类有与父类重名方法时，方法重写(覆盖，复写)
     * 构造方法
     * 继承是否将构造方法继承？
     * 父类的构造方法负责对成员变量的初始化
     * 先调用父类的无参构造，再调用子类的无参构造
     * 子类的构造函数的第一行均有super()
     * this()与super()均需要放置在第一行,只会存在一种。
     * 当父类没有无参构造方法时，子类需要手动调用
     */

    // 区分继承和组合
    // 继承是is关系，组合是has关系
    protected ExtendsTest extendsTest;

    // 不要重复name和age字段/方法
    // 只需要定义新增score字段/方法
    private Integer score;

    /**
     * 构造方法：
     * 继承是否将构造方法继承？   构造方法没有继承，父类构造方法被子类调用了！
     * 虽然父类的构造方法被调用了，但是没有创建父类的实例对象
     * 父类的构造方法负责对成员变量的初始化
     * 先调用父类的无参构造，再调用子类的无参构造
     * 子类的构造函数的第一行均有super()，无论这个构造方法有参还是无参
     * this()与super()均需要放置在第一行,只会存在一种。
     * 当父类没有无参构造方法时，子类需要手动调用
     */
    // 父类子类都添加空参构造方便向上向下转型测试
    public Student() {

    }

    // 这是因为在Java中，任何class的构造方法，第一行语句必须是调用父类的构造方法。
    // 如果没有明确地调用父类的构造方法，编译器会帮我们自动加一句super();
    // Person类并没有无参数的构造方法，因此，编译失败。
    // 解决方法是调用Person类存在的某个构造方法。
    // 如果父类没有默认的构造方法，子类就必须显式调用super()并给出参数以便让编译器定位到父类的一个合适的构造方法。
    // 子类不会继承任何父类的构造方法。子类默认的构造方法是编译器自动生成的，不是继承的。
    public Student(String name, Integer age, Integer score) {
        /**
         * this与super
         * 如果父类没有提供默认的无参构造，子类在继承时就会编译报错
         * 如果父类坚持不提供默认无参构造方法，必须在本类的无参构造方法中使用super方式调用父类的有参构造方法。
         * 一个实例变量/实例方法/构造方法可以通过this.赋值另一个实例变量/实例方法/构造方法。
         * 如果this和super指代构造方法，则必须位于方法体的第一行。换句话说，在构造方法中，this和super只能出现一个，且只能出现一次。
         * 由于this和super都在实例化阶段调用，所以不能再静态方法和静态代码块中使用。
         * this还可以指代当前对象，比如同步代码块synchronized(this){...}
         * super可以在子类覆写父类方法时，使用super调用父类同名的实例方法。
         */
        super(name, age); // 调用父类的构造方法Person(String, Integer)
        this.score = score;
    }

    // 继承有个特点，就是子类无法访问父类的private字段或者private方法。例如，Student类就无法访问Person类的name和age字段：
    public void hello() {
        // return "Hello, " + name; // 编译错误：无法访问name字段
        // 这使得继承的作用被削弱了。为了让子类可以访问父类的字段，
        // 我们需要把private改为protected。用protected修饰的字段可以被子类访问：
        // protected关键字可以把字段和方法的访问权限控制在继承树内部，一个protected字段和方法可以被其子类，
        // 以及子类的子类所访问，后面我们还会详细讲解。

        // super关键字表示父类（超类）。子类引用父类的字段时，可以用super.fieldName。
        // 如果父类的name属性是私有的，super.getName()还是获取不到具体数据，需要修改name属性的修饰符
        /**
         *      当存在子父类关系时，创建子类对象，会访问对应的父类空间，与其他的父类实例对象完全没关系。
         * 	继承后成员变量的特点：
         * 	就近原则：局部变量>成员变量>父类成员变量>父类的父类的成员变量
         */
        System.out.println("Hello, " + super.getName());
    }

    /**
     * 子类比父类更强大：
     * 1、子类可以包括父类的所有方法，但是还可以有属于子类自己的方法
     * 2、方法的重写(就近原则)，重写的方法就有了更详细，更强大的功能，满足我们的实际需求
     */
    /**
     * 继承后成员方法：
     * 子类可以直接使用父类的非私有方法
     * 当子类有与父类重名方法时，方法重写(覆盖，复写)
     * <p>
     * 重写时注意事项：
     * 方法重写后：
     * 1、方法体可以不同(从语法角度讲可以相同)，而且必须不同(从实际业务出发)
     * 2、访问权限问题：public>默认>private
     * 子类重写方法的访问权限大于或等于父类的方法访问权限
     * 3、方法重写时，方法名必须相同
     * 4、方法重写时，如果只是其他相同，但是参数不同，则形成了方法重载的关系，而不再是方法重写
     * 5、静态只能重写静态，静态方法只能被静态方法重写
     * 6、重写时的返回值：
     * 覆盖时，返回值类型为基本数据类型(包括void)时，必须相同
     * 如果是引用数据类型，子类返回值类型必须为父类返回值类型的子类
     */
    // 子类当中存在与父类的方法相同的方法，叫做方法的重写/复写
    @Override
    public void sleep() {
        System.out.println("Student.sleep()......睡了");
    }

    // 静态只能重写静态，但是为什么写@Override注解时会报错？？？？？？(静态方法用类名就能调用，也展现不了多态的作用)
    public static void staticMethod() {
        System.out.println("Student.staticMethod().....子类的静态方法");
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}

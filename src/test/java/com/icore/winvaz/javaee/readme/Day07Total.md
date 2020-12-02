Eclipse或MyEclipse的使用:
 * 介绍:
   * Java语言开发的集成工具(IDE).
   * Eclipse:
     * 从官网下载之后,得到一个压缩包.
     * 必须先安装JDK环境.
   * MyEclipse:
     * 尽量从官网下载(收费).
     * 建议安装独立JDK环境.
 * Debug模式:
   * 找错的能力.
 * 测试:
   * 分类:
     * 白盒测试:业务测试.
       * 专业的测试人员.
     * 黑盒测试:逻辑测试.
       * 多开发人员来完成.
   * 单元测试:
     * 介绍:
       * 单元测试:定义一个测试类只用于测试一个类.
         * 增加了开发人员的工作量.
	 * 增强保证开发人员编写的代码的正确性.
       * JUnit是一个第三方的测试工具(Jar包).
       * 使用JUnit工具的最基本用法.
       * JUnit的版本:
         * 3.x版本
	 * 4.x版本:主流使用.
       * 4.x版本的JUnit在MyEclipse中已经提供.
     * 测试原则:编写一个类,就测试一个类.
Java 5版本的新特性:
 * 注意:
   * 在将来的实际开发中,使用Java的版本不会太新.
   * 新版本不够稳定,老版本相对比较稳定.
   * 一般情况下,新版本提供的新功能,老版本也能实现.
自动装箱\拆箱:
 * Java 4版本的问题:
   * 如果定义一个变量存储100的话,100应该是int类型.
   * 将一个int类型的变量不能直接赋值给Integer类型.
 * 如果使用Java 4版本进行int与Integer之间的转换:
   * int类型转换成Integer类型,使用valueOf(int类型),该方法返回Integer类型.
   * Integer类型转换成int类型,使用intValue(),该方法返回int类型.
 * 在Java 5版本中,int类型与Integer类型可以直接赋值.
   * 编写代码时,int类型与Integer类型可以直接赋值.
   * 在Java文件编译后的class文件中,还是使用Java 4版本的内容.
   * Java 5版本提供的新特性,只是Java编译器提供的.
   * 直接将int类型变量赋值给Integer,叫自动装箱.
   * 直接将Integer类型变量赋值给int,叫自动拆箱.
 * 举例案例:研究valueOf()方法
   public static Integer valueOf(int i) {
        assert IntegerCache.high >= 127;
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
   * 解读代码:
     * 判断传入的参数的值是否在-128至127这个区间内?
       * 如果传入的参数的值是在这个区间内的话,直接返回Integer缓存内的值.
       * 如果传入的参数的值不在这个区间内的话,返回新创建Integer类型的值.
可变参数:
 * 在函数的参数位置:用来替代数组.
 * 使用比数组更灵活.
 * 注意:
   * 一个函数中只能保存一个可变参数.
   * 可变参数只能定义在参数列表的最后.
 * 格式:int... arr.
for加强循环:
 * 格式:
   for ( Object object : list){}
 * 作用:替代Java中的迭代器.
 * 注意:
   * 迭代器可以迭代的是List和Set集合.
   * 因为List和Set集合都继承于Iterable接口.
 * 用法:
   * 用于实现Iterable接口的所有类.
   * 用于遍历数组.
 * 局限性:
   * 只能从头到尾的遍历.
   * 不能指定获取集合中某个元素.
泛型:
 * 什么是泛型:
   * 泛型就是类型.
   * 为集合确定类型(该集合只能存储什么类型的数据).
 * 泛型的语法:
   * 格式:List<E>、Map<K, V>
 * 通过查看Java底层的List集合的代码:
   public interface List<E>{
	boolean add(E e);
	E get(int index);
   }
 * (掌握)自定义泛型类:
   * 类可以是泛型的.
   * 定义的变量类型是泛型的.
   * 定义的方法的参数类型及返回值类型都是泛型的.
   * 泛型不能定义在static修饰符的成员中.
 * (掌握)自定义泛型方法(非泛型类中):
   * 与在泛型类中的泛型方法不同.
   * 格式:public <T> T 方法名(T t){}
 * 泛型通配符:
   * 泛型在应用中的问题:
     * 如果参数类型使用泛型,只能接收相同类型参数(不能接收其他类型).
     * 接收不同类型的List集合参数,又不能定义多个同名方法.
     * 不定义参数集合的泛型,出现警告提示.
   * 解决方案,使用通配符:
     * 只需要在参数集合的后面增加<?>
   * 通配符的上边界和下边界(了解):
     * List<? extends Number> list
       * 设置通配符的上边界,当前List集合类型只能是Number的子类.
         List<Integer>
     * List<? super Integer> list
       * 设置通配符的下边界,当前List集合类型只能是Intger的父类.
         List<Number>
枚举:
 * 作用:就是解决几选一的问题.
 * 格式:
   enum 枚举类名称{
	罗列枚举项(枚举项之间用",";最后面加";")
   }
   * 注意:在枚举类中的枚举项,必须出现在第一个位置(其他内容都在枚举项后面).
 * 枚举类可以包含构造器、成员及抽象方法:
   * 构造器只能用private修饰.
   * 抽象方法在枚举项中必须实现.
 * 枚举类的常用方法:
   * name()方法:返回当前枚举项的名称.
   * ordinal()方法:返回当前枚举项的索引值.
   * valueOf(String name)方法:
     * name参数表示枚举项名称.
     * 该方法返回指定的枚举项.
   * values()方法:返回当前枚举类的所有枚举项.
反射:
 * Class类:
   * 如何获取Class类实例:
     * 类名.class
     * 类名.getClass()
     * Class.forName(对应类的完整路径)
       * 对应抛异常
   * Class类的常用方法:
     * getName():返回对应类的完整路径.
     * getSimpleName():返回对应类的类名.
     * getSuperClass():返回对应类的父类.
     * newInstance():返回对应类的实例对象.
   * 用途:
     * 可以获取对应类的完整路径或类名.
     * 可以获取对应类的所有继承链关系.
     * 通过反射机制来创建对应类的对象.
 * Constructor
   * 如何获取构造器:
     * getConstructors()方法:返回对应类的所有public的构造器集合.
     * getDeclaredConstructors()方法:返回对应类的所有访问级别的构造器集合.
     * getConstructor(Class type)方法:返回对应类指定的public的构造器.
     * getDeclaredConstructor()方法:返回对应类的所有访问级别的具体构造器.
   * 构造器的用途:
     * 通过构造器获取对应类的实例对象.
 * Method
   * 如何获取Method:
     * getMethods()方法:返回对应类及父类的所有方法(public).
     * getDeclaredMethods()方法:返回对应类的所有方法(所有级别).
     * getMethod(对应方法名,方法接收的参数类型)方法:返回对应类具体的public的方法.
     * getDeclaredMethod(对应方法名,方法接收的参数类型)方法:返回对应类具体的所有级别的方法.
   * Method的用途:
     * 利用Method类的invoke(对象名,方法实参)方法,操作对应类的对应方法.
 * Field
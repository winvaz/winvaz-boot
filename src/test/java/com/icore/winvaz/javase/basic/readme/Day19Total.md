day19
1.	集合嵌套
数据结构，百度容器，存基础班容器，就业班容器
基础班存储学生 001 张三  002 李四
就业班存储学生 001 王武  002 赵柳
HashMap<String,HashMap<String,String >>  百度 = new HashMap<>();
HashMap<String,String> javase = new HashMap<String,String>();
HashMap<String,Strng> javaee = new HashMap<String,String>()
百度.put("javase",javaseMap);
百度.put("javaee",javaeeMap);
基础班Map.put("001","张三")
就业班Map.put("001","王武")

2.	LinkedHashMap
是HashMap的子类，特点保证键的顺序，存储的键，怎么存的，怎么取出来，不同步执行效率高
底层结构基于哈希表的链表实现
3.	TreeMap集合
底层数据结构红黑树，自然平衡二叉树
线程不同步的，不安全，执行效率高
存储键，对键进行自然顺序的排序，要求的作为键的对象，必须具备自然顺序
或者自定义比较器
TreeMap使用方式，原来的TreeSet是一致的，保证对象的自然顺序，或者自定义比较器就可以了，程序代码，存储的时候和TreeSet几乎一致。Set使用add,Map使用put
4.	Hashtable
  JDK1.0出现的集合，存储键值对，底层数据结构也是哈希表，Hashtable JDK1.2后实现Map接口。线程安全，不允许存null值，null键。除了线程，null，和HashMap是一致的。已经被HashMap取代。但是Hashtable虽然不用了，但是有一个子类，至今活跃在开发的舞台中。Properties是Hashtable的子类
5.	Properties
 存储键值对的集合，线程安全，IO流配合使用，这个集合的泛型，定好了，键值的泛型都是String
 Prperties类的两个自己的特性方法
	setProperty(String key,String value)将键值对存储到集合
	String getProperty(String key)根据键，获取值

6.	计算一个字符串中每个字符出现的次数
"asfreedffewfg"  字符a出现1次  字符b出现5次
7.	JDK1.5后的新特性
  增强for循环，出现，简化了传统意义上的for循环，遍历数组或者集合
  如果你的程序，不需要改变数组或者集合，只需要遍历的话，优先考虑使用增强for循环
 格式:
   for(数据类型 变量 : 集合或者数组){
         输出(变量);
   }
弊端：增强的for循环，只能遍历，只能看，不能摸

8.	JDK1.5后的新特性
  可变参数，方法的参数，是可变的
  static void a()
可变参数的数据类型，必须统一
方法名(数据类型...变量名)
多个参数，可变参数，必须写在最后面
方法中的可变参数，只能写一个

9.	集合工具类java.util.Collections
 凡是工具类，都是静态的
 Arrays操作数组的工具类
 集合工具类的方法
	static sort(List list)可以排序List集合，升序
	static sort(List list,Comparator com)传递List，比较器，按照比较器对集合升序排序
	static Comparator reverseOrder()返回一个比较器，逆转了对象的自然顺序(Comparable)
	static Comparator reverseOrder(传递比较器)返回比较器，逆转了传递的比较器的顺序
	static int binarySerach(List list,Object key)集合的折半查找,如果找不到该元素，返回-插入点-1。插入点，就是将查找的元素，放在集合中个，保证集合有序，下标插入点
	static reverse(List list)反转集合
	static fill(List list,Object o)替换集合中的所有对象
	static shuffle(List list)随机排列集合中的对象，洗牌
	Collections工具类中一组方法 synchronized开头的，将线程不安全的集合，变成线程安全的集合

10.	数组工具类 java.util.Arrays
数组的工具类，直接类名调用静态方法
折半，排序，将数组变成字符串，数组变集合
	static sort(数组) 数组进行升序排序，快排
	static int binarySerach(数组，查找的元素)数组的折半查找
	static String toString(数组)不是重写Object,将数组变成字符串
	static List asList(数组)数组转集合，List集合，集合长度不可变
	Collection接口方法 T[] toArray(T[]) 集合变数组

11.	异常概述
 异常：程序中，在运行的时候出现的不正常现象。Java是面向对象的语言，异常也是一种对象，因此每次出现异常的时候，看到XXX.XXX.XXXException异常类，告诉你发生了什么情况 ArithmeticException 算术异常。异常类描述对象的
 刚才程序做了除以0
12.	异常继承体系
  java.lang.Throwable类，所有异常和错误的父类
     Error类
        所有错误的父类
     Exception类
        所有异常的父类
        RuntimeException
             NullPointerException
             ClassCastException
             IndexOutOfBoundsException
 错误，比较严重的问题，一旦出现了错误，程序根本不能运行，必须修改源代码。
 错误，相当于，非典，艾滋
 异常，比较轻微的问题，一旦出现了异常，程序可以处理掉异常，继续运行
 异常，相等于，阑尾炎

 Throwable类中的方法
 构造方法 空参数
 构造方法 Throwable(String message)异常信息

	String getMessage()返回异常信息的详细字符串
	String toString()返回异常信息的简短描述
	void printStackTrace()将异常信息直接输出在控制台

13.	异常的第一种处理方式
  try  catch 代码块处理
 标准格式:
     try{
      被检测的代码，可能出现异常的代码
     }catch(异常类 变量){
         异常的处理方式
         变量.调用Throwable类的三个方法
     }
14.	作业布置
自己总结集合框架
Collection
   方法写出来
   a()干什么

List
  方法写出来
  b()干什么
List集合的特点

ArrayList
  自身特点
  存储和取出的代码

预习IO流
java.io包
Writer
Reader
InputStream
OutputStream

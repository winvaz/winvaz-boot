day15
1. for for
  程序的运行次序
  for(int i =1 ; i < 3; i++){
    for(int j = 0 ; j < 2 ; j++){
		sop();
    }
  }
 i=0 -- i <3 -- j = 0 -- j < 2 -- 内层的sop输出 -- j++ -- j<2-- 内层的sop输出 -- j++ -- j < 2--出去内层循环，i++ -- I < 3 j=0

new 对象
  类  变量名字 = new 类();
  类 --> 类类型，数据类型
  变量 --> 类类型的变量
  new 类() --> 对象

构造方法
  方法名字必须和类名的名字一致
  没有返回值的,不用写return
  初始化成员变量的，new 对象的时候，构造方法给类的成员变量赋值
  可以重载的，参数列表不同，方法名字相同，同一个类

this
  本类对象的引用
  调用自己本类的变量和方法
  区分成员变量和局部变量重名的情况
  构造方法，set方法
  this()在构造方法之间进行调用
  可以作为对象返回的

static静态
  修饰成员
  被类名调用
  被静态修饰的内容，内存的静态区存储，属于自己的类
  静态成员变量，非静态的成员变量有什么区别
     静态修饰的，对象的共享数据
     非静态，是对象的特有数据

继承  extends
  变量
      子类中，使用父类的成员变量 super ,调用父类的成员
  方法
     重写，子类父类中，出现了相同的方法，子类方法权限大于或者等于父类方法权限
     子类重写父类的方法，目的就是为了程序的扩展性
     在类中重写的方法需要和父类被重写的方法具有相同的方法名，参数列表以及返回值类型。

  构造方法
     子类的所有构造方法，必须访问父类构造方法
     访问父类构造方法，的语句super()父类构造方法，()写参数
     子类构造方法第一行，super()，并且只能出现一次。
     如果父类中，没有空参数的构造方法，子类的构造方法，没有写super,直接编译失败
     this() super() 两个语句选择一个写,保证子类所有构造都可以访问到父类构造方法

多态
   父类或者接口的引用 变量 = new 子类的对象()
   变量.方法() 运行的都是子类重写的方法
   编译时期和运行期的特点
     除了非静态的成员方法以外，编译运行全是父类，看左边
     只有非静态的成员方法，编译看父类，运行是看子类

   父类 变量 = new 子类();JVM做了类型的提升，子类提成为了父类的类型
   将提升父类类型的子类，转成纯子类对象
   子类 变量 = (子类)提升为父类的变量
   ClassCastException 类型转换异常--开发中最常见的异常之一
   instanceof 关键字  比较运算符 == boolean
   引用类型 instanceof 类  可以避免类型转换异常

抽象类
  abstract 抽象 ，类和方法
  抽象方法，不能写(没有方法体){}
  抽象类，不能建立对象，写子类继承抽象类，重写抽象方法，创建子类的对象
  抽象类有构造方法，可以不写抽象方法，抽象类可以写静态方法吗，可以	

 接口
  定义interface 接口名字(类名)
   定义的内容是固定写法
   public static final 数据类型 变量名 = 值;
   public abstract 返回值类型 方法名(参数列表..);

接口与接口之间继承关系，接口之间支持多继承

  抽象类和接口的区别：
     抽象类是继承体系的共性内容
     接口是继承体系的扩展内容
接口不能建立对象，定义实现类，实现接口implements,重写接口中的全部抽象方法，建立子类的对象

内部类，匿名内部类
  外部类，外部类的成员位置，写了一个内部类，成员内部类
      成员内部类，非静态的，成员内部类的方法也是非静态的
           外部名.内部类名 变量 = new 外部类对象(). new内部类对象();
      成员内部类，静态的，成员内部类的方法也是非静态的
          外部名.内部类名 变量 = new 外部类.内部类对象()
      成员内部类，静态的，成员内部类的方法也是静态的
           外部名.内部类名.方法名()

  外部类，外部类有一个方法，方法里面写一个内部类，局部内部类
    写在一个方法中，使用局部内部类的方法，
    通过外部类的方法，来实现

匿名内部类
   new 父类或者接口(){
     重写父类或直接口的方法
   }
看做就是接口或者父类的子类对象


Object类
    private static native void registerNatives ();
    见到本地关键字修饰的方法，这个方法的源代码，不是Java语言编写，C++写的
    调用本地操作系统功能
    栈，堆，方法区 ，本地方法栈

  equals 比较对象的，比较的是对象的什么，比较对象的地址
  覆盖equals方法，建立对象自己的比较方法，比较对象中的成员变量的值
  public boolean equals(Object obj){  return this == obj ;}

  toString
  返回一个字符串，方法的意义，返回对象的字符串表现形式
  没有覆盖之前 类名+@十六进制
  覆盖toString方法，返回自定义的对象的字符串表现形式
   返回对象中的成员变量的值

权限 p p d p
public  protected  default   private

String 类
  String类，描述字符串对象的类，String类不可变对象，字符串一旦创建完毕，不可改变
  字符串的底层实现是字符数组，"asdfasfafef"  数组被final修饰了
  String s1 = new String("abc")  String s = "abc"  sop(s1);sop(s)
字符串的常见的方法回顾
构造方法：
	将字节数组转成字符串 String(byte[] bytes) 查询本机默认编码表GBK
	将字节数组转成字符串 String(byte[] bytes,int offset,int length)开始下标，个数

   将字符数组转成字符串 String(char[] ch)直接将数组中内容变成字符串，不查询编码表
   将字符数组转成字符串 String(char[] ch,int offset,int length) 开始下标，个数


判断方法：返回boolean
	判断两个字符串是否完全相等 boolean equals(Object obj)
判断两个字符串是否完全相等，忽略大小写 boolean equalsIgnoreCase(String s)
    判断是否以一个字符串开头 , boolean startsWith(String s)
    判断是否以一个字符串结尾 , boolean endsWith(String s)
	判断一个字符串是否包含另一个字符串 boolean contains(String s)
    判断字符串中是否为空  boolean isEmpty() 开始于JDK1.6版本

获取方法：
	获取指定位置上的单个字符  char charAt(int index)
	获取指定字符在字符串中第一次出现的下标 int indexOf(char ch)
	获取指定字符在字符串中第一次出现的下标 int indexOf(char ch,int fromindex)
	获取指定字符串在字符串中第一次出现的下标 int indexOf(String s)
	获取指定字符串在字符串中第一次出现的下标 int indexOf(String s, ,int fromindex)
获取字符在字符串中最后一次出现的下标，反向索引 lastindexOf(char ch)
	获取字符串的一部分 返回新的String  substring(int start,int end)包含头，不包含尾
	获取字符串的一部分 返回新的String  substring(int start)包含头，后面的全要
	获取字符串中字符的个数 int length()   
 
转换方法：
	字符串转成字节数组  byte[]  getBytes()
	字符串转成字符数组  char[]  toCharArray()
	字符串转成大写字母  String  toUpperCase()
	字符串转成小写字母  String  toLowerCase()
     将其他数据类型转成字符串  static String valueOf() 

其他方法：
	切割字符串，返回字符串数组 String[] split()
	替换字符串，返回新的字符串 String replace()
	去掉字符串两端的空格,返回新的字符串 String trim()
	比较字符串谁大谁小 int compareTo(String s) 按照字典顺序比较

StringBuffer类StringBuilder类
 StringBuffer append()将数组追加到缓冲区
StringBuffer delete()删除缓冲区中的数据
StringBuffer insert()在指定的位置上插入数据
void  setCharAt()修改指定位置上的单个字符
String toString()将缓冲区变成字符串，可以使用String类方法

Integer类
	static int parseInt(String s)将数字格式的字符串转成基本数据类型
	构造方法 Integer(String s)将数字格式的字符串，包装成Integer类型对象
	int intvalue()将构造方法中包装的字符串，转成基本数据类型

自动装箱和拆箱，1.5版本后的新特性
  装箱：将基本数据类型封装成对象 Integer xx = 10;
  拆箱：将封装成对象的基本数据类型，转成基本数据类型  xx+1

Date
  构造方法：
     空参数的 Date() 获取当前操作系统的时间和日期
	带有一个long型参数的 Date(long time)传递一个毫秒值，将毫秒值转成日期对象

    把日期对象，转成毫秒值 long getTime()
    获取当前日期的毫秒值   long System.currentTimeMillis()
    将毫秒值变成日期对象   void setTime(long time)
Date类，必须要会，毫秒值和日期对象之间的转换

DateFormat
几个静态方法:
   getDateInstance()
	获取DateFormat的子类对象的static DateFormat getDateTimeInstance(int dateStyle,int timeStyle)
	格式化日期方法 String format(Date对象)
	Date parse(String s)将字符串变成日期对象
必须会做字符串转成日期对象
SimpleDateFormat
构造方法，SimpleDateFormat(String 日期模式   yyyy:MM:dd -- HH:mm:ss)
将日期按照模式格式化 String format(日期对象Date类的对象)

Calendar
  日历字段：年，月，日，星期几
  static Calendar getinstance() 返回的也是子类对象：格林威治类
  int get(int 日历字段) 日历字写的是月，返回值就是当期的月份
  add(int日历字符，int 偏移)设置日期的偏移量
  set(int year,int month,int day)设置日历对象
  计算保质期，闰年计算，案例会写
/*
 * 计算保质期，计算闰年的
 */
import java.text.DateFormat;
import java.util.*;
public class CalendarDemo {
	public static void main(String[] args) throws Exception{
		//调用静态方法getIntance()获取日历对象的子类对象
		//Calendar c = Calendar.getInstance();
		//printCalendar(c);
		baoZhi();
		runNian();
	}
	
	//定义方法，实现闰年的计算
	private static void runNian(){
		/*
		 * 计算闰年，2月的最后一天是什么，如果是28平年，29闰年
		 * 获取日历对象getInstance
		 * 已知年份
		 * 将日历设置到这一年的3月1日
		 * 向前偏移1天，获取天数判断
		 */
		Calendar c = Calendar.getInstance();
		c.set(2002, 2, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		if(c.get(Calendar.DAY_OF_MONTH)==29)
			System.out.println("闰年");
		else
			System.out.println("平年");
	}
	
	//定义方法，实现保质期的计算
	private static void baoZhi()throws Exception{
		/*生产日期是2014-3-10，保质期是187天，那一天到期
		 * 获取日历获取对象--操作系统走
		 * set方法，将日历设置到生产日期上
		 * 调用add方法，让天数，偏移187天，输出日历
		 */
		String date = "2014-3-10";
		DateFormat df = DateFormat.getDateInstance();
		Date d = df.parse(date);
		Calendar c = Calendar.getInstance();
		//c.set(2014, 2, 10);
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, 187);
		printCalendar(c);
	}
	
	//打印当前日历对象的信息
	private static void printCalendar(Calendar c){
		System.out.println(c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月"+
	c.get(Calendar.DAY_OF_MONTH)+"日 星期"+(c.get(Calendar.DAY_OF_WEEK)-1));
	}
}
Math
  数学计算类，定义了很多的静态方法，实现数学计算
  静态成员变量，final修饰，常量double PI 圆周率
  abs() 绝对值
	ceil(double d)返回大于或者等于该参数的最小整数
	floor(double d)返回小于或者等于该参数的最大整数  
pow(double a,double b)幂运算 a的b次方
round(double )四舍五入
random()0.0-1.0之间的伪随机数

Random
   空参数构造创建对象
   调用方法 int nextInt(int 参数)获取伪随机数，小于传递的参数

Scanner
   接收控制台的键盘输入
   构造方法 Scanner(System.in) System.in字节输入流
   String nextLine()获取键盘输入的一行

单例设计模式两种写法，都要会写
单例设计模式之：饿汉式
public class Single() {
private int age;
------------gs----------
public void setAge(int age) {
    This.age = age;
}
public int getAge() {
    Return age;
}
   // 私有化构造函数
   private Single() {}
   // 在自己类的成员位置上，创建自己类的对象
   public static Single single = new Single();
   // 给外部类提供一个公共的静态方法，返回本类的对象
   public static Single getSingle() {
       Return single;
}
}
单例设计模式之: 懒汉式
// 私有化构造方法
private Single(){}
// 在本类的成员位置，定义本类类型的变量，但不创建对象
public static Single single = null;
// 给外部类提供一个静态的方法，返回本类的对象
public static Single getSingle() {
  if (single == null) {
     Synchronized(single.class) {
        if (single == null) {
           single = new Single();
}
}
}
return single;
}
// 测试类
public class Test{
  public static void main(String[] args) {
Single single = Single.getSingle();
Single single2 = Single.getSingle(); 
Single.setAge(20);
System.out.println(Single2.getAge());
}
}
设计模式，为了解决开发中的实际问题出现的思想
单例：解决一个类的对象，在内存的唯一性。这个类的对象，只有一个
实现单例设计模式的步骤：
  A. 私有修饰构造方法
  B. 在本类的成员位置，new自己类的对象
  C. 提供一个静态方法，返回本类的对象

如果其他程序，需要这个类的对象，不能去new ,通过方法的返回值，获取该类对象

Runtime类
Java程序的运行时类，每一个Java应用程序都有自己的一个运行时对象
static Runtime getRuntime() 这个类，采用单例模式设计的类

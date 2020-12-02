day23
1.	IO集合的综合
  提供一个文本文件，文件中保存的是姓名和成绩
  读取文件，对姓名和成绩按照成绩高低排序
  将排序后的结果，保存到另一个文件中
  数据中姓名，成绩可能重复
  对于文件有要求，提供你的文件 studentscore.txt 排序后另存的文件sortstudentscore.txt.      abc.txt    sortabc.txt

2.	 复制一个文件夹
  首先将数据源 c:\\demo，数据目的 d: 封装成File对象
  从源中获取文件夹的名字，将目的和文件夹的名组成新的File对象，创建目的文件夹
 获取数据源的中的全部文件 文件的数组File[]
 遍历数组，获取到的是数据源中的文件的全路径
 获取文件名，将文件名和你的数据目的文件夹组成新的File对象
 源有了，目的有了，字节流读写文件
3.	打印流
 Java.io.PrintStream
 java.io.PrintWriter
 打印流的特点：打印流只操作数据目的，不操作数据源。打印流永远不会抛出IO异常。
 System.out 获取到打印流对象PrintStream

 PrintStream构造方法，传递的是打印流的输出目的
 File对象
 字节输出流对象
 字符串的文件名

 PrintWrtier类实现了PrintStream类的所有print方法，区别在于构造方法不同
 File对象
 字节输出流
 字符串文件名
 字符输出流

 打印流中，实现换行输出，使用println()方法。打印流自动刷新
 打印流中，构造方法可以写boolean值，如果是true，开启自动刷新，但是开启自动刷新，打印流中传递的对象，必须是流对象才行
  new PrintWriter(输出流对象, true)
必须调用println,printf,format三个方法，才能实现自动刷新 
日后开发有什么，特点简单，方便，自动刷新，不出异常，用在Java的服务器端程序，可以把数据，使用打印流写会客户端浏览器

打印流案例：使用打印流对象PrintWrtier，替代转换流，字符转成字节，OutputStreamWriter，实现数据的打印功能。OutputStreamWriter(System.out)
PrintWriter类属于Writer的子类，字符流，只能操作文本数据。如果操作的不是文本数据，纯字节流

4.	对象的序列化
  序列化，将对象的数据保存到硬盘 -- ObjectOutputStream，字节流
  ObjectOutputStream构造方法中，传递一个字节输出流对象，字节输出流对象肯定封装一个文件吧，对象就会写进这个文件
  写对象的方法 void writeObject(Object obj)

  反序列化，将硬盘中的对象数据读取出来 -- ObjectInputStream，字节流
  ObjectInputStream构造方法中，传递一个字节输入流对象，字节输入流对象封装一个文件，读取封装的文件中的对象
   读取对象的方法 Object readObject() 抛出IO异常，抛出类中不到异常
   找不到类异常，肯能没有对应的class文件
 类必须实现java.io.Serializable接口，启用序列化功能

把成员变量加上了static后，序列化没有问题，但是反序列化的时候，成员变量的值丢失，原因序列化的时候，文件没有保存静态变量的值。序列化是对象的序列化，静态成员变量，属于类，不属于对象，因此静态不能序列化

Serializable接口：
  接口没有任何抽象方法，实现接口后，不需要重写方法。凡是以后我们在看到没有抽象方法接口，这样的接口，标记型接口。
  猪肉 上蓝色的戳子(XXXX时期，XXX加工厂,)，这个猪肉检验合格准许销售。
   接口Serializable就相当于猪肉上的戳子，标记一下，告诉JVM，这个类可以序列化

java.io.InvalidClassException，序列号冲突。
  产生原因：Person类，写好后，进行了对象的序列化，Person.clas，person.txt生产了，同样都存储了一个序列号。可是我么将Person类的原始代码进行了修改，从新的编译，生成了一个新的Person.class文件。记录的序列号，person.txt中，记录的序列号已经不一致了，我们又进行了反序列化，Person.class中的号person.txt中的序列号出现冲突问题。
  序列号产生：public static 内存不认识，JVM也不认识，只认识10101，比如修饰符，名字，都一个数字进行表示，1000表示public 2000 private ,序列号根据数字签名计算出来的
  程序修改了源代码后，并不会影响正常的使用，不影响对象序列化，编译是的时候，不从新计算序列号，对于Person类，不从新计算序列号，自定义序列号
   static final long serialVersionUID = 自定义的long型数据

5.	Properties IO结合
  Properties是双列的集合 ， Hashtable的子类，线程安全的
  完全可以使用操作Map的方法和步骤，操作Properties类
  Properties自己的特性:  getProperty()  setProperty()方法的参数定死为String
 Properties如何与IO流结合使用
 自己的方法load(字节输入流)   load(字符输入流) 
 load将读取到到文件中的键值对，存储到 Properties集合中

 IO和集合的关联，操作的文件，配置文件
 应用程序运行的时候，读取配置文件，怎么配置的，程序就必须运行
 配置文件中的书写都是死规定

 将文本中的键值对，读取存储到集合load
 将集合中的键值对，修改后，存储会文本文件 store(字节输出流,"") store(字符输出流,"")

 集合有一个方法list(打印流对象)

  自己定义功能，实现集合中的load方法，两种重载形式

 集合IO的关联使用练习
  要求：文件记录程序的运行次数，如果程序大于了3次，程序就不运行了，提示你给钱。记录运行次数的配置文件，键值对存储的，第一次运行，文件没有，创建一个，存储上已经运行1次了

6.	RandomAccessFile随机读写流
  随机读写流的特点：
	直接继承Object类
	这个类中封装了一个大型的字节数组
	这个类可以写，可以读
	随机访问，可以从文件的某一个位置上进行读写
	移动文件指针的方法seek[long]
	这个类可以读写基本数据类型
	实际应用，制作p2p下载软件，迅雷，旋风，电驴

  RandomAccessFile的构造方法
   两个参数，一个是File类型包装一个文件,Stirng类型
   两个参数，一个是String类型文件名，String类型

   随机读写类，读和写基本数据类型
   随机读写，张三65
    读取有基本数据类型，利用异常将程序停下，不能判断-1，读取的方法返回是int类型-1也是有效


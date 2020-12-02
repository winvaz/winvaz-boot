day21
1.	字符输出流的缓冲区对象
 数组的缓冲，提高了流对象的读写效率，开发出现流对象的缓冲区对象，目的提高流的操作效率。字符输出流的缓冲区 java.io.BufferedWriter
  字符输出流缓冲区对象方法
	构造方法 BufferedWriter(Writer out)传递的是Writer的子类对象。目的：缓冲区对象出现的目的，提高输出流的效率，提高哪一个流，只要传递的是Writer的子类，提高的就是你这个子类的效率。FileWriter     比喻：耳机和手机
	void newLine()写一个换行。\r\n Windows下有效，Linux操作系统\n 
newLine()具有跨平台性，安装的JVM版本了，如果安装的JVM是Windows版本的，newLine()方法中写的就是\r\n ,如果安装的是Linux版本的JVM，newLine()方法中的写就是\n

2.	字符输入流的缓冲区对象
 提高字符输入流的读取效率，java.io.BufferedReader，查阅API文档，这个缓冲区可以按行读取。（
 字符输入流缓冲区对象的方法
	构造方法 BufferedReader(Reader in),传递是的Reader类的子类对象。目的，提高字符输入流的读取效率，提高哪个流对象，传递Reader子类，FileReader.
	String readLine()读取文本一行，返回字符串,读取文件结尾，返回null，读取一行，返回的字符串中，没有\r\n符号，返回是一行有效字符

3.	文本文件复制的第三种方式
  利用字符流缓冲区对象，按行读取，读一行，写一行，写换行，刷新
  目的是为了行的操作！！
4.	完成一个案例
  人吃饭的案例，人对象功能，吃饭功能。吃饭功能变得更加复杂了，吃饭之前喝汤，喝酒，吃饭，水果，抽一根。设计一个提高原有人吃饭功能的程序，保证原有的人不能变的
NewPerson  BufferedReader  装饰类，出现的目的都是为了增强原有对象的功能而来
解决了原有对象功能的不足，出现的装饰类，装饰设计模式。
比喻：木地板，地面原本承重，加上木地板后，增强了原有地面的功能，打地铺
地板装饰类，地面原有的对象

5.	继承和覆盖方法，装饰设计模式区别，好处
  继承：OOP(面向对象)三大特征之一
  装饰模式：解决实际问题出现的一种思想
	有如下继承体系，原有对象功能不够，采用继承覆盖的方式解决
读取流Reader
  文本读取流TextReader
     SubTextReader extends TextReader
  音频读取流MP3Reader
    SubMP3Reader extends MP3Reader
  游戏读取流GameReader
    SubGameReader extends GameReader
  视频读取流VideoReader
    SubVideoReader extends VideoReader
  以上的解决办法，类实现太多了，造成继承体系过于庞大，臃肿。对于学习者，学习9个类了，对于开发者，也很痛苦
	  有如下继承体系，原有对象功能不够，采用装饰设计模式解决
读取流Reader
  文本读取流TextReader
  音频读取流MP3Reader
  游戏读取流GameReader
  视频读取流VideoReader
BufferedReader类 extends Reader出现增强原有读取流的功能
BufferedReader(Reader in)所有的Reader读取流的子类，全部增强
  以上解决问题的办法，装饰模式,BufferdReader装饰，增强原有读取流对象的功能，始终只有一个类，完成增强，这样的体系简单，易学，易用

6.	自己定义装饰类，实现读取一行的功能
  定义的类，属于装饰类，被装饰的对象，Reader的子类，FileReader里面有一个方法read()读取单个字符，读取一行返回String，读取文件末尾，返回null

7.	字节输出流
 字节可以操作任意文件，字节输出流，写任意文件，单个字节8个二进制。
   不查询编码表
 字节输出流的抽象基类OutputStream
 写字节数组，字节数组一部分，单个字节
 子类FileOutputStream类，文件的输出
 构造方法方法，传递字符串文件名
 字节流写数据，不需要刷新，直接向数据写到目的地
8.	字节输入流
字节可以操作任意文件，字节输入流读取文件，单个字节8个二进制
字节输入流的抽象基类InputStream
读取的方法，读取单个字节，读取字节数组，读取字节数组一部分
子类是FileInputStream，构造方法直接传递字符串文件名
读取单个字节的read()方法，返回字节的码值，文件末尾返回-1
int available()字节输入流的方法,返回字节输入流封装的文件的字节数

9.	字节流复制任意文件
  第一种方式读取一个字节，写一个字节
	第二种方式读取一个字节数组，写一个字节数组--重点案例，必须要写熟练
	第三种方式读取文件，用字节流缓冲区实现
10.	字节流缓冲区
  BufferedInputStream 构造方法，传递字节输入流 FileInputStream
  BufferedOutputStream 构造方法，传递字节输出流 FileOutputStream

11.	键盘输入
 Scanner(System.in)
 System类的静态成员变量 public static final InputStream in;返回的字节输入流对象是JVM底层自带的一个字节输入流。不需要知道是哪个子类，知道父类的方法read()读取
所有的read方法readLine方法，等待效果，线程阻塞.
对于键盘的输入，实现了按行读取，换行问题，和停止问题，但是发现代码就是以前写过一次，模仿的BufferedReader中的readLine()方法。
如果能让readLine()方法，读取控制台输入System.in，如果可以，程序简单，不出错。
BufferedReader字符输入流的缓冲区对象，System.in==InputStream字节输入流
为了解决这个问题，Java工程师很善良，让你实现字符缓冲区读取字节输入流，开发出了转换流，将字节流转成字符流去读呢
12.	InputStreamReader
是Reader的子类，字符流对象，字节流向字符的桥梁。InputStreamReader有一个子类就是FileReader
字节转成字符流，InputStreamReader如果使用
构造方法：InputStreamReader(InputStream in)传递字节输入流，变成字符流
                          字节输入流读的是文本文件，可以用转换流

13.	控制台输出
 System.out
 System类的静态成员变量 public static final PrintStream out;返回打印流对象，知道的是打印流对象也是字节输出流OutputStream的子类。
 刚才的程序，readLine()读取到的字符，能直接写到字节输出流中吗？肯定不行
将字符流数据，转成字节流数据，写回控制台
14.	OutputStreamWriter
是Writer的子类，字符流对象，字符流向字节的桥梁
OutputStreamWriter构造方法，传递的是字节输出流，System.out

15.	转换流的案例
用的是转换流，转换流不能操作非文本数据
	读取键盘输入，输入后的内容，转成大写，保存在一个记事本中
	读取一个文本文件，转大写后，输出在控制台
	读取一个文本文件，转大写后，输出在另一个文件中
16.	

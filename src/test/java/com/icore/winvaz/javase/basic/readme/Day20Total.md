day20
1.	异常的第二种处理方式抛出异常
 如果程序运行的时候，由于用户或者调用非法传递参数，导致程序出现问题，假如调用者真的传递了非法的数据，需要手动抛出异常。在方法中，使用关键字throw 写异常对象，new 出来的.
 错误: 未报告的异常错误Exception;程序中，有异常没有处理
方法内部抛出异常，不处理这个异常，交给调用者处理，谁调用我这个函数，谁处理这个异常。必须在方法的声明上，声明出来，我这个方法有异常，谁调用谁处理。
在方法的定义上，使用throws关键字，把异常声明出来，告诉调用者，如果你调用处理我的异常  throws 异常类
	throw : 只能写在方法内部，后面必须写 new 异常()
	throws: 只能写在方法声明上，后面必须写 异常类

2.	编译时期异常和运行时期异常
  编译时期异常：调用了一个抛出异常的方法，不处理，编译失败
  运行时期异常：RuntimeException类，和他所有的子类都属于运行时期异常
  特点：如果方法内部用throw 抛出的是一个运行时期异常，此时方法声明上，不需要使用throws声明异常，调用者不需要处理这个异常，Java工程师们设计运行时异常的初衷，运行时期异常一旦发生，程序必须停下来修改源代码，运行时期异常，是不能发生的
   Person p = new Person();
   p.show();
   p =null;
   p.show();空指针异常
   int[] arr = {1,2,3};
   arr[10] = 100;
   if(arr[50] < arr[90])
 今后如果我们写一个程序让别人去用，如果别人乱传递参数导致程序后面的计算，无法运行，抛出运行时期异常，结束程序，要求用户传递参数必须合法！
   数组越界，字符串越界，空指针异常，类型转换异常
3.	自定义的异常
 Java原有异常体系中，很多的现象进行异常的封装，越界，类型转换，JDK中也有想不到的异常，所以自定义异常
  第一步，入伙  继承 Exception 或者继承RuntimeException
  第二步，传递异常信息，将异常信息传递给Exception的构造方法
  注意：异常必须是Exception或者RuntimeException的子类，具备可抛性
4.	finally代码块
try{} catch(Exception e){} finally{}
try{} finally{}
finally代码块中的程序，必须要执行，不管程序有没有异常方法，finally中的程序必须执行
finally代码块，一般用于释放资源使用
5.	面试题
final 修饰符，类，方法，变量 finally 代码块跟随异常处理，必须执行 finalize方法，收垃圾
6.	子类继承父类后的异常细节
子类继承后，重写父类的方法：
	父类的方法不抛出异常，子类的方法不能抛出异常。如果子类的重写方法，调用了一个抛出异常的方法，子类只能try catch处理异常
	如果父类的方法，抛出异常，子类的方法可抛可不抛，但是如果子类抛异常，抛出的异常不能大于父类方法抛出的异常
	方法中可以不可以，抛出多个异常,每个异常需要在方法声明上throws声明出来,调用者写多个catch处理
	多catch处理异常过程中，注意，最高的异常父类，写在最后面
  
7.	IO流概述
 流：程序用来读写文件数据这个技术，流技术，输出和输入，IO流技术
	IO流技术实现设备之间的数据传输，可以使用流技术读取数据，自己机子的，读取其他计算机，设备硬盘，另一台主机，或者互联网
	按照操作数据的不同，分成了字符流和字节流
	字符流，专门用于操作文本数据，记事本，Java程序，网页，字符流在读或者写数据的时候，查询本机默认编码表(GBK),有利于操作文本文件，出现于JDK1.1
	字节流，可以用来操作任意文件，读写文件的时候，不查询编码表
	按照流向分，输出流Output和输入流Input。输出，Java程序写文件，操作数据目的，输入，Java程序读取文件，操作数据源
	字符流只能操作文本数据，字节流任意数据，输出就是写文件，输入就是读取件

8.	IO中的继承体系
	字符输出流，写文本文件的，抽象基类 java.io.Writer，写文件的，写的方法有哪些
write(字符数组)  write(字符数组一部分) write(单个字符) write(字符串)
	字符输入流，读文本文件的，抽象基类 java.io.Reader，读文件的，读的方法有
read()单个字符  read(字符数组)  read(字符数组一部分)
	字节输出流，写任意文件的，抽象基类 java.io.OutputStream，写文件的，写的方法
write(单个字节)  write(字节数组) write(字节数组一部分)
	字节输入流，读任意文件的，抽象基类 java,io.InputStream，读文件的，读的方法
read()单个字节  read(字节数组)  read(字节数组一部分)

9.	写文本文件
字符输出流 Writer,父类不能直接使用，找子类，FileWriter类
构造方法 FileWriter(字符串的文件名)
字符流在写数据的时候，write方法写，数据写在了内存中，并没有直接写到目的文件中，内存(缓冲)，如果让数据写到目的文件中，字符流要进行缓存区的刷新操作。Writer中的方法flush，刷新流的缓冲，将数据写到目的地，字符流只要刷新的，数据必会走向目的地。
文件的续写，文件的后面追加写入，原有的数据都被保留下来
FileWriter类构造方法中，传递String的文件名，true实现追加写入

10.	读取文本文件
  Reader类，抽象类，找子类，FileReader，构造方法，直接传递字符串的文件名
  调用FileReader父类方法实现读取功能
  读取单个字符的方法  int read() 读取一个字符
 read()方法，每次只能读取一个字符，返回这个字符的码值，没调用一次read()自动向后读取一个字符，如果读取到文件的末尾，返回-1
 注意：文件读取的时候，一次循环不允许出现2次read()
       保证读取的文件必须是GBK编码的(ANSI)
	读取字符数组 int read(char[] ch)
	每次读取到的字符，装在字符数组中，返回每次读取的有效字符个数

11.	 文件的复制，文本文件
 读写原理，一个读，一个写
 FileReader类读取源文件
 FileWriter将读取到的数据源中的文本，写向数据目的文件
 采用读取一个字符，写一个字符的方式

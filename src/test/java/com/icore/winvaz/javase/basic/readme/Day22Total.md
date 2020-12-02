day22
1.	转换流的编码效果
 编码表 ASCII GBK UTF-8
 每一个字符，在编码中，对应一个十进制数
 美国标准信息交换码ASCII  符号，数字，字母大小写区别
 中国也开始研制自己语言的编码表，汉字对应二个十进制数
 中文编码表诞生，GB2312  存储了4000个汉字
 进行升级和扩容，增加到了20000个汉字，GBK
 把全球所有的语言都制作成一个呢？Unicode 万国码
 现在使用的UTF-8。汉字在UTF-8编码中，是三个字节
 ISO8859-1 拉丁文。在转换流对象中，可以指定编码表
 转换流对象，构造方法，第一个参数是字节流，第二个参数可写编码表的名字
 ??? 不是乱码，编码表中，没有查到任何数据
 幻彩 : 编码表中查到的结果
 以后做程序，如果发生乱码现象，无论是做se,还是ee,Adnroid,就是存储时候的字，和获取字的时候，采用的编码表不一致

2.	字符的编码和解码
  将一个字符串，转成字节数组，称为字符的编码 String类的方法getBytes
  将字节数组，还原会字符串，成为字符的解码   String类的构造方法

3.	File类
 不属于流对象，作用：将机器上的路径和目录封装成一个对象，File对象，提供了很多的方法和成员变量，让我们操作目录和路径
  目录就是文件夹，路径。对于类中的方法熟知，File类需要和IO流对象配合使用
4.	File类的静态成员变量
 一共提供的4个变量，掌握的只有2个,跨平台
	static String separator  结果\ Windows目录分隔符  Linux下 /
	static String pathSeparator 结果 ; Windows下的路径与路径之间的分隔符 Linux :
5.	File类的构造方法
	File(String pathname) 将一个字符串的路径，封装成File对象.只复杂把路径封装成对象，至于路径有没有，够造方法不考虑。（参数写到文件夹，写到文件也行）
	File(String parent,String child)传递两个，字符串格式父路径，字符串格式子路径
	File(File parent,String child)传递两个，File类型父路径，字符串格式子路径

6.	File类的创建功能
	boolean createNewFile()创建新文件，创建成功返回true,所创建的文件是File构造方法中封装的文件，文件已经存在，不会创建，只能创建文件
	boolean mkdir()创建目录，文件夹，创建成功返回true,创建的是File构造方法中封装的路径,目录存在，不在创建了
	boolean mkdirs()创建多级目录
7.	File类的删除方法
 
	boolean delete()删除File构造方法中封装的路径，成功返回true，不走回收站。删除目录必须保证目录是空的
	void deleteOnExit()延迟删除，等待JVM退出之前，在删除

8.	File类的判断方法
	boolean exists()判断File构造方法中封装的路径是否存在，如果存在返回true
	boolean isAbsolute()判断File构造方法中封装的路径，是不是绝对路径，如果是绝对路径返回true
	boolean isDirectory()判断File构造方法中封装的是目录还是文件，如果是目录返回true
	boolean isFile()判断File构造方法中封装的是不是文件，如果是文件返回true
	boolean isHidden()判断File构造方法中封装的路径是不是隐藏属性，如果是发回true

9.	File类的获取方法get开头
	String getName()获取的是File构造方法中封装的路径的最后那个部分的名字
	String getParent()获取File构造方法中封装的路径的父路径,返回String
	File getParentFile()获取File构造方法中封装的路径的父路径，返回File对象
	String getPath()将你File封装的路径，变成字符串
	File getAbsoluteFile()获取File构造方法封装的路径的绝对路径，返回File对象
	String getAbsolutePath()获取File构造方法封装的路径的绝对路径，返回String对象

10.	File的获取方法，包含list
	static File[] listRoots()返回系统的根目录
	String[] list()获取File对象中封装的路径下的全部目录和文件夹，返回String数组
	File[] listFiles()获取File对象中封装的路径下的全部目录和文件夹，返回File数组,返回的是封装路径下的全部内容的全路径，可以继续使用File类的方法
	File[] listFileFileFilter filter)参数是文件过滤器，可以实现过滤想要的文件

11.	File类的其他方法
boolean renameTo(File file)对File构造方法中的封装的文件重命名，命名成功返回true，如果修改后的路径不同，出现剪切效果
long lastModified()获取File构造方法中封装的文件的最后修改时间的毫秒值

12.	递归
  递归只是一种编程手段而已，C C++ C# php java
  递归就是方法的自身调用，自己调用自己  public static void a(){  a();  }
  从前有个山，山里有庙，庙里有个老和尚讲故事，讲的是从前有座山，山里有个庙...
  什么时候可以使用递归：当你发现一个功能，运算的主体不变，但是，每次运算的参数都在变化，考虑使用递归实现功能
   注意：
	递归必须要有条件限制
	递归不能太深，方法进栈次数不能过多，否则出现栈内存溢出
13.	流对象的使用规律
  字符，字节 ，字符输出，字符输入，字节输出，字节输入，转换流
使用IO流对象的时候，进行文件的读写。
	明确数据源
  如果操作的数据源是文本文件，可以选择字符输入流FileReader读取
  如果需要提高流的读取效率，请你采用字符(char)数组缓冲
  如果为了方便按行读取数据源，请你采用BufferedReader来实现

  如果数据源不是文本文件，选择字节输入流FileInputStream读取
  如果需要提高流的读取效率，请你采用字节(byte)数组缓冲

  如果不能确定数据源到底是什么类型的文件，选择字节输入流FileInputStream读取
  互联网中的数据传输基本上是字节流

	明确数据目的
  如果操作的数据目的是文本文件，可以选择字符输出流FileWriter写入
  如果提高流的输出效率，请你采用字符数组(char)缓冲
  如果为了方便按行输出数据目的，请你采用BufferedWriter来实现

  如果数据目的不是文本文件，选择字节输出流FileOutputStream写入
  如果提高流的输出效率，请你采用字节(byte)数组缓冲

  如果不确定数据目的是什么类型文件，选择字节输出流FileOutputStream写入

	如果数据源和数据目的都是字节流的，但是流中的内容又是纯文本数据，使用转换流来方便我们读取和写入
 读取使用InputStream字节转成字符，按照行读取
 写入使用OutputStreamWriter字符转成字节，按照行写入
	File对象，封装任意的目录和文件，可以将File对象传递给流对象进行写入或者读取，但是保证源和目的的文件类型。保证File对象封装的必须是文件，才能传递给IO流读取进行读取和写入

14.	IO集合的综合
  提供一个文本文件，文件中保存的是姓名和成绩
  读取文件，对姓名和成绩按照成绩高低排序
  将排序后的结果，保存到另一个文件中
  数据中姓名，成绩可能重复
  对于文件有要求，提供你的文件 studentscore.txt 排序后另存的文件sortstudentscore.txt.      abc.txt    sortabc.txt

15.	 复制一个文件夹
  首先将数据源 c:\\demo，数据目的 d: 封装成File对象
  从源中获取文件夹的名字，将目的和文件夹的名组成新的File对象，创建目的文件夹
 获取数据源的中的全部文件 文件的数组File[]
 遍历数组，获取到的是数据源中的文件的全路径
 获取文件名，将文件名和你的数据目的文件夹组成新的File对象
 源有了，目的有了，字节流读写文件

day27
1.	TCP的客户端和服务器的数据交换
  客户端通过键盘输入，数据传递到服务器，转换大写字母，发回客户端
  Socket ServerSocket IO流
  readLine方法，读取数据的时候，按行读，自己写过readLine方法，读取到什么就不读了呢，读取到一行，返回正行的有效字符

2.	TCP的文件上传
  上传文本文件，打印流
  上传图片，字节流
  必须获取到服务器发回的上传成功
  上传的文件，指定存在d:\\upload目录下
  多线程并发上传：
    每一个客户端连接服务器，服务器开启一个线程处理这个客户端的上传
    程序服务器多线程，客户端单线程，采用main开启服务器线程的做法，
    服务器连接工作，由main完成，获取客户端对象，也由main完成。
3.	服务器端的应用程序
 一个主机，之所以可以成为服务器，按照服务器版本操作系统，硬件配置，按照服务器端的应用程序，JavaWeb中，apache Tomcat
start Services : 建立ServerSocket服务，开启8080端口
Tomcat  Socket IO Thread
IE，内部封装Socket对象，IO流
4.	URL类
  构造方法，传递String格式的字符串地址
  自动的将地址，端口分开
  Socket s = new Socket(192.,1000);内部封装Socket对象，进行连接
  new URL(http://192...)
  URL类的方法 openConnection()打开构造方法传递的连接的Socket连接
  返回一个连接对象URLCollection，连接对象中IO流已经建立完毕

5.	反射由来
程序的后期扩展，有程序了，后期需要加内容
Animal a = new Cat();  Animal a = new Dog();
 可以实现配置文件，之地类，方法，进行运行
目的，就在于扩展，无源码修改！

6.	反射学习的目的
	获取一个类的class文件对象的方法
	有了class文件后，解剖这个class文件，中文件中直接获取构造方法，普通方法运行起来--反射概念
	获取构造方法运行
	获取成员方法运行
  万物皆对象, .class是对象，描述类 java.lang.Class
  构造方法是对象，描述类 java.lang.reflect.Constructor
  成员变量是对象，描述类 java.lang.reflect.Field
  普通方法是对象，描述类 java.lang.reflect.Method
  
  使用反射中类，技术，实现程序读取配置文件运行

7.	获取一个类的字节码文件对象
	通过类的对象获取
	通过类的静态属性class获取
	通过Class类的静态方法forName(String 类名)获取

8.	获取构造方法，并运行
 一个类的class文件中，直接获取构造方法，运行
 Class描述class文件对象的类的方法
	Constructor[] getConstructors 获取class文件中的构造方法，只有公共的public权限，获取多个，存储数组
	Constructor getConstructor(Class...c)通过参数列表获取构造方法，想获取什么构造，传递相对应的参数列表
	运行构造方法，找构造方法的描述类Constructor,有可以运行构造方法的方法，返回Object newInstance(Object...o)可变参数，运行方法，传递参数，实际传递参数
	Class类的方法 getDeclaredConstructors()返回一个Constructor数组，可以获取所有构造方法，包括私有
	Class类的方法 getDeclaredConstructor(Class..c)返回一个Constructor类，只有一个构造方法，获取哪一个，传递相对参数列表就可以
	Constructor类的有个父类AccessibleObject类,父类中有一个方法setAccessible(boolean )传递的是true，运行期间，JVM取消程序的访问检查，暴力访问。
	如果类中，有public修饰的，空参数的构造方法，可以简单获取构造方法并运行，一种简化形式，保证类有public的空参数构造.Class类中，有一个方法 newInstance()可以直接运行空参数，public权限的构造方刚发

9.	获取成员方法并运行
  使用Class类的方法，获取一个类的成员方法
	Method[] getMethods()返回字节码文件中的方法，是数组，多个方法,返回public权限的还是继承的或者是实现的方法
	Method getMethod(String方法名,Class...c)返回一个方法,传递方法名和参数列表
	Method类中，有没有方法，可以运行获取到的方法呢，Object类型invoke()运行方法的方法，调用invoke(Object obj,Object...o),调用invoke运行其他方法的，传递对象，和实际的参数
	Method getDeclaredMethod(方法名，参数列表)获取到私有方法
10.	反射通过配置文件运行程序
  需要运行的类，方法，写在配置文件中个，采用键值对存储
  IO读取文件，存储到集合中Properties，反射运行方法
11.	反射绕过编译器检查，将不同的数据类型存储到带有泛型的集合中
考点，不是集合，不是泛型，会使用反射技术
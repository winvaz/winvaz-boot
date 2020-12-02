类加载器

类加载器：
1.BootstrapClassLoader(启动类加载器)：最顶层的加载类，负责加载Java_home/lib目录下的jar包和类或被-Xbootclasspath参数指定的路径中的所有类。
2.ExtensionClassLoader(扩展类加载器)：负责加载jre_home/lib/ext目录下的jar包和类，或被java.ext.dirs系统变量所指定的路径下的jar包。
3.AppClassLoader(应用程序类加载器)：负责加载当前应用classpath下的所有jar包和类。

双亲委派模型(自底向上检查类是否被加载，自顶向下尝试加载类)：
每一个类都有一个对应它的类加载器，系统中的classLoader在协同工作时默认使用双亲委派模型。
即在类加载的时候，系统先判断当前类是否被加载过，已被加载直接返回。否则才会尝试加载，
在加载时，首先会把该请求委派该父类加载器的loadClass()处理，
因此所有的请求最终都应该传送到顶层的启动类加载器BootstrapClassLoader中。
当父类加载器无法处理时，才由自己来处理。当父类加载器为null时，由启动类加载器作为父类加载器。


1. 什么是类加载器
  * 作用：它可把硬盘上的.class文件加载到内存的方法区，形成一个Class类的对象！
  * 类加载器的分类：
    > 引导：rt.jar，都是类库中的类！
    > 扩展：ext目录下的jar！！！
    > 系统（应用类加载器）：当前应用的classpath下的类！
  * 类加载器就是片警！每个类加载器都有自己负责范围
  * 类加载器的上下级关系：
    > 系统的上层是扩展
    > 扩展的上层是引导
    > 引导就没上层了！

2. 类加载器的委托机制（代理模式）
  * 自己先不去加载类，委托给上层去加载，如果上层加载成功，那么加载结束！
  * 上层加载失败，那么自己再去加载！

  类加载器负责加载类，那么谁来加载类加载器呢？
  1. 系统类加载器、扩展类加载器都是rt.jar中的class，由引导来加载！
  2. 谁来加载引导呢？引导类加载器它不是类！它是JVM的组成部分！
  3. 引导类加载器（JVM）它是由C语言实现的！
  4. 你永远都得不到类加载器的引用！

  所有类加载器都是ClassLoader的子类！
  得到类加载器：
    Class对象的getClassLoader()：返回当前Class是被谁加载的！
    Thread对象的getContextClassLoader()：获取当前线程独有的类加载器！

  获取上层
  getParent()获取上层类加载器！若使用扩展的getParent()返回的是null！

3. 自定义类加载器（不打代码）
  * 继承ClassLoader
  * 完成加载类的方法叫：Class loadClass(String name)
  * loadClass()做了什么事情：
    > 调用Class findLoadedClass(String name)：在内存中搜索！返回Class对象，说明这个类已经加载过了，返回null表示还没有加载过！
    > 上面语句返回的是null，启动了委托机制。执行this.getParent().loadClass(name)：调用上层的同名方法，去让上层加载本类！
    > 上面语句返回的是null，执行findClass()方法完成类的加载！（我们需要重写的方法是findClass()）

  * 自定义类加载器需要重写父类的findClass()方法
    > defineClass()：这个方法可以把字节数组变成Class对象！

4. Tomcat的类加载器
  * Tomcat有两个自定义类加载器！
    > Tomcat级别的类加载器：负责加载Tomcat下的lib目录下的jar文件！
    > Web应用级别的类加载器：每个应用下都有WEB-INF\classes，WEB-INF\lib
  * 它们有个独特之处：
    > 它们首先是自己动手，如果自己不行，再去委托上层！
  * Web应用级别的类加载器需要加载类时：
    > Web应用级别，自己去加载类！去WEB-INF\classes，WEB-INF\lib加载类
    > Tomcat级别，去加载类！${CATALINA_HOME}\lib加载类
    > 扩展类
    > 引导类加载

  * Tomcat下类的加载优先级问题：
    > ${CATALINA_HOME}\lib（最低）
    > ${APP}\WEB-INF\lib（次之）
    > ${APP}\WEB-INF\classes（最高）

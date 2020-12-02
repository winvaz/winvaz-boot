1. Servlet是什么
  * 一个Servlet对应一个功能
  * Servlet可以被浏览器访问

2. 实现Servlet
  * 实现javax.servlet.Servlet接口
  * 继承GenericServlet类
  * 继承HttpServlet类

3. Servlet接口中的方法
  * void init(ServletConfig) --> 创建时被调用（只调用一次）
  * void service(ServletRequest,ServletResponse) --> 工作(被请求)时被调用（调用N次）
  * void destory() --> 销毁时被调用（只调用一次）
  * String getServletInfo()
  * ServletConfig getServletConfig()

4. 编写Servlet的步骤
  * 编写一个Servlet类，实现Servlet接口
  * 在web.xml中为Servlet指定访问路径(浏览器就可以访问Servlet了)

5. Tomcat的执行流程
  * 访问Servlet的路径，Tomcat就可以找到对应的Servlet类了
  * Tomcat查看这个类的实例是否存在
    > 如果不存在创建之，并调用init()方法(Servlet是单例的)
  * Tomcat调用service()方法处理请求！！！
  * 当关闭Tomcat时，销毁Servlet

6. Servlet是线程不安全的！
  * 因为一个实例要同时处理多个请求（每个请求是一个线程）

===============================================

Servlet相关类（由Tomcat完成创建）

1. ServletConfig
  * String getServletName()：获取Servlet名称：<servlet-name>xxx</servlet-name>，这个方法很没用！
  * 获取Servlet初始化参数：还没学习配置初始化参数呢
    > String getInitParameter(String name)：通过参数名，获取参数值
  <servlet>
  	<servlet-name>BServlet</servlet-name>
  	<servlet-class>cn.icore.web.servlet.BServlet</servlet-class>
  	<init-param>
  		<param-name>p1</param-name>
  		<param-value>v1</param-value>
  	</init-param>
  	<init-param>
  		<param-name>p2</param-name>
  		<param-value>v2</param-value>
  	</init-param>
  </servlet>
  * ServletContext getServletContext()：获取ServletContext：还没学呢

2. ServletRequest（塑料袋）有东西的
  * 表示请求（你可以从这里获取到所有与请求相关的数据）
  * Tomcat把所有请求数据都封装到ServletRequest对象中，然后用来调用service()方法，
          也就是说我们可以使用ServletRequest来获取任意的请求数据

3. ServletResponse（手机，只能与发送请求的浏览器通话）
  * 表示响应对象
  * 你可以使用它向浏览器发送响应数据
    > 响应首行（状态码）
    > 发送响应头
    > 发送响应体

4. Servlet的创建时间
  * 默认Servlet是在首次被访问时
  * 也可以是在Tomcat启动时，这需要在web.xml中进行配置

  <servlet>
  	<servlet-name>CServlet</servlet-name>
  	<servlet-class>cn.icore.web.servlet.CServlet</servlet-class>
  	<load-on-startup>1</load-on-startup>
  </servlet>

  * 使用<load-on-startup>来配置一个Servlet在服务器启动时完成创建！
  * 其中<load-on-startup>元素的值必须是一个非负整数，它表示创建的顺序
    > 多个Servlet都配置了<load-on-startup>，创建顺序由其值顺序决定！

=============================

GenericServlet

1. 继承GenericServlet比较方便
  * 提供了获取ServletConfig对象的功能
    > ServletConfig getServletConfig();
  * 它本身是ServletConfig的装饰类
    > String getServletName()
    > String getInitParameter(String name);
    > Numeration getInitParameterNames();
    > ServletContext getServletContext();
  * 它提供了init()和destory()方法，它们都是空方法
  * 不能覆盖父类的init(ServletConfig)方法，若覆盖了，那么就不能再使用ServletConfig了。
  * 如果子类需要做初始化操作，那么去覆盖父类init()方法！

=============================

HttpServlet

1. HttpServlet专注处理Http请求的！
  * doGet(HttpServletRequest,HttpServletResponse)：用来处理GET请求
    > 如果只覆盖HttpServlet的doGet()方法，那么这个Servlet只支持GET请求，调用doPost()就会405(不支持的请求方式)。
  * doPost(HttpServletRequest,HttpServletResponse)：处理POST请求
    > 如果只覆盖HttpServletr的doPost()方法，那么这个Servlet只支持POST请求，调用doGet()就会405。

=============================

web.xml

1. DefaultServlet
  * 它的响应就是404页面
  * 当没有任何Servlet与访问路径匹配时，执行DefaultServlet，它给你响应一个404页面！

2. JspServlet
  * 当访问的是jsp页面时，就会执行这个JspServlet！
    > 这个JspServlet会把.jsp文件转换成.java文件，再编译成.class文件，然后执行之。

3. 默认的session超时时间
  * 默认的session最大空闲时间为30分钟。

4. mime类型与扩展名映射关系！
  * 例如jpg的mime类型为image/jpeg

5. 默认的欢迎页
  * index.html
  * index.htm
  * index.jsp

====================================
====================================
====================================

ServletContext

1. 什么是ServletContext
  它是Servlet的生存环境的描述
  可以获取一些与Servlet环境相关的数据
  Servlet的环境就是整个应用程序，所以ServletContext也代表应用程序！

2. ServletContext生命周期
  * 一个程序只有一个ServletContext对象！
  * 在tomcat启动时就创建ServletContext对象。
  * 在tomcat关闭时才会销毁ServletContext对象。

3. ServletContext是域对象
  * Servlet中有三个域对象，它是其中一个！（JSP比Servlet还多一个域！）
  * 域对象可以用来存取属性，域对象用起来与map相似。
    > void setAttribute(String name, Object value)：设置属性
    > Object getAttribute(String name)：获取属性值
    > void removeAttribute(String name)：删除属性
  * ServletContext域
    > 跨请求
    > 跨用户
  * 获取初始化参数
    > String getInitParameter(String name)
    > Enumeration getInitParameterNames()

web.xml中配置context的初始化参数，这种参数在任何servlet中使用ServletContext对象都可以获取！
	<context-param>
		<param-name></param-name>
		<param-value></param-value>
	</context-param>
	<context-param>
		<param-name></param-name>
		<param-value></param-value>
	</context-param>
 
  * 获取真实路径
    > String getRealPath(String path)：根据参数指向的相对路径，返回真实路径！
  * 获取资源流
    > InputStream getResourceAsStream(String path)：根据参数指向的相对路径，得到资源流！
  * 通过文件名得到对应的mime类型
    > 通过文件名，得到mime类型
    > String getMimeType(String filename)

============================

统计访问量

1. 访问AServlet，也是访问本网站
2. 访问BServlet，也是访问本网站

3. 统计的结果存放在哪里比较好！
  * ServletContext中!

------------------

思路：

1. 多个Servlet中都要有统计访问量的代码
  * 获取ServletContext
  * 判断ServletContext中是否存在名为count的这个属性。
    > 第一次访问：获取不到数量，那么我们直接向ServletContext中保存名为count的属性：setAttribute("count", 1);
    > 非第一次：把原数量获取过来，加一后保存保存回去！int cnt = sc.getAttribute("count"); sc.setAttribute("count", cnt+1);


------------------
------------------
/*
		 * 获取类路径下的资源
		 * 1. classes/a.txt
		 * 2. classes/cn/icore/domain/a.txt
		 */
		/*
		 * 获取类路径下的资源可以使用
		 * 1. ClassLoader
		 * 2. Class
		 */
		Class c = this.getClass();
		ClassLoader cl = c.getClassLoader();
		
		/*
		 * 通过类加载器得到类路径的根路径下的资源
		 * 相对classes目录
		 */
		InputStream input = cl.getResourceAsStream("/a.txt"); // classes/a.txt
		InputStream input = cl.getResourceAsStream("cn/icore/domain/a.txt"); // classes/cn/icore/domain/a.txt
		/*
		 * 把流中的字符串读取出来
		 */
		String text = IOUtils.toString(input);
		System.out.println(text);
		
		/*
		 * 使用Class来获取类路径的资源
		 * classes下的，这里必须使用“/”
		 */
		input = c.getResourceAsStream("/a.txt"); // classes/a.txt
		input = c.getResourceAsStream("a.txt"); // NullPointerException
		text = IOUtils.toString(input);
		System.out.println(text);
		
		/*
		 * 在不使用“/”开头时，那么读取的是当前Class对应的.class文件所在路径
		 */
		c = Person.class;
		input = c.getResourceAsStream("a.txt"); // classes/cn/icore/domain/a.txt
		text = IOUtils.toString(input);
		System.out.println(text);
-----------------------------------------

获取类路径下的资源

1. 使用ClassLoader
  * InputStream getResourceAsStream(String)
    > 获取的是classes下的资源

2. 使用Class
  * InputStream getResourceAsStream(String)
    > 使用“/”开头，获取的是classes下的资源
    > 不使用“/”开头，获取的是当前Class对应的.class文件所在路径，例如cn.icore.domain.Person.class.getResourceAsStream("a.txt")
      获取的是classes/cn/icore/domain/a.txt
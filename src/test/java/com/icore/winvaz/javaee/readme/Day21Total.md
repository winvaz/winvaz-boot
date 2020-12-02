过滤器

1. 什么是过滤器
  * 大门口保安
  * 在目录资源之前（之后）执行
  * 山大王

  * 过滤器是在目标资源（jsp、servlet、html、css、js、jpg）前后执行
    > 预处理，不会拦截
    > 校验，可能会拦截
    > 后续处理，回程处理

2. helloworld，编写的步骤
  * 实现javax.servlet.Filter接口
  * web.xml中进行配置

public class AFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 目标资源之前执行
		System.out.println("hello filter1...");
		
		//放行！调用目标资源，如果目标资源为servlet，它等同与调用servlet的service()方法一样！！！
		chain.doFilter(request, response);
		
		// 目标资源之后执行
		System.out.println("hello filter1...end...");
	}
	
	public void init(FilterConfig config) throws ServletException {
	}
}
  * javax.servlet.Filter
    > init(FilterConfig)：在过滤器对象被创建之后，执行一次
    > destory()：在器被销毁之前，执行一次
    > doFilter(ServletRequest,ServletResponse, FilterChain)：每次过滤时都执行一次
  * FilterChain：只有一个方法
    > doFilter(ServletRequest,ServletResponse)：放行！调用servlet的service()方法一样！！！


  <filter>
  	<filter-name>afilter</filter-name>
  	<filter-class>cn.icore.web.filter.AFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>afilter</filter-name>
  	<url-pattern>*.jsp</url-pattern><!--还可以指定<servlet-name>-->
  </filter-mapping>


3. 过滤器生命周期
  * javax.servlet.Filter
    > init(FilterConfig)：创建过滤器对象后，马上执行！只执行一次
    > doFilter(ServletRequest,ServletResponse,FilterChain)：每次过滤时都要执行！执行N次！
    > destory()：在过滤器对象被销毁之前执行，只执行一次

  * 过滤器是单例的！（与servlet相同）
  * 在服务器启动时就会创建！
  * 在服务器关闭时会销毁过滤器！

  * 相关的类型
    > FilterChain：它是一个接口！不用我们去实现，由Tomcat去实现，并创建对象，传递给doFilter方法！
      * void doFilter(ServletRequest,ServletResponse)：放行！
        > 执行过滤器链中下一个过滤器，或是执行目标资源！
    > FilterConfig
      * String getFilterName()：获取<filter-name>的值
      * String getInitParameter(String name)：获取过滤器的初始化参数
      * Enumeration getInitParameterNames()：获取所有初始化参数名称
      * ServletContext getServletContext()：获取Servlet上下文对象


4. 多个过滤器执行顺序
  * 多个过滤器执行顺序，由web.xml中配置的<filter-mapping>的顺序来决定！
  * 谁的<filter-mapping>在上，就先执行谁！


5. 四种上拦截方式
  * 四种拦截方式
    > 请求：REQUEST，默认值！！！
    > 转发：FORWARD
    > 包含：INCLUDE
    > 错误：ERROR

  * 配置拦截方式
    > 在<filter-mapping>中给出0~N个<dispatcher>元素，其取值为：
      * REQUEST
      * FORWARD
      * INCLUDE
      * ERROR：可以web.xml中配置错误页面！在某页面出错时，去往错误页面时，执行错误过滤器！
  <error-page>
  	<error-code>404</error-code>
  	<location>/xxx.jsp</location>
  </error-page>
    > 在没有<dispatcher>时，默认为request
    > 如果配置了<dispatcher>，那么就没有默认值了！


=========================================================

小结：

1. 在目标资源前后执行
2. 编写
  * 写一个类实现javax.servlet.Filter接口
  * 在web.xml中进行配置
3. 生命周期
  * 创建Filter后马上执行init(FilterConfig)
  * 每次过滤时都会挪doFilter(ServletRequest,ServletResponse,FilterChain)
  * 在销毁Filter之前执行destory
  * 服务器在启动时会创建Filter对象，它是单例的！
  * 服务器在关闭时会销毁Filter对象
  * FilterChain
    > doFilter(ServletRequest,ServletResponse)：放行！执行下一个过滤器或目标资源
  * FilterConfig
    > 与ServletConfig相似
    > ServletContext getServletContext()
    > String getInitParamter(String name)
    > Enumeration getINitParameterNames()
    > String getFilterName()
4. 多个过滤器的执行顺序
  * <filter-mapping>的配置顺序决定了执行顺序
5. 四种拦截方式
  * REQUEST：请求（默认）
  * FORWARD：转发
  * INCLUDE：包含
  * ERROR：错误
  * 在<filter-mapping>中配置0~N个<dispatcher>元素

6. 过滤器的应用场景
  * 预处理：不拦截，总是放行！
  * 校验：可能会拦截！
  * 后续处理：在回程中进行拦截（处理）

  * 过滤器中的代码往往是从多个Servlet中提取出来的共同部分！！！



========================================


1. 分ip统计访问次数
  * 大家都来访问我的网站，我这里有N个资源（a.jsp、b.jsp、c.jsp）！
  * 无论访问本站任何资源，我们都会分ip进行统计访问次数！
  * 编写一个过滤器，用来统计本站所有资源被访问的结果！
    > 过滤的是西站所有资源！<url-pattern>/*</url-pattern>
    > 使用Map保存统计结果！把Map放到ServletContext中！
    > 如果在Filter中得到ServletContext对象：FilterConfig对象可以得到ServletContext

1.1 页面
  * a.jsp
  * b.jsp
  * c.jsp
  * show.jsp --> 显示map，即统计结果！

1.2 过滤器
  * 得到map：得到FilterConfig，再得到ServletContext，再得到map
  * 得到ip：request.getRemoteAddr()
  * 得到次数：查看map中是否存在这个ip，如果不存在，说明是第一次访问；如果存在，在原访问次数上加1，再保存回去

  * 放行！
1.3 生命周期监听器
  * ServletContextListener：有一个方法会在ServletContext创建后马上执行！

===========================================================================

2. 粗粒度权限控制
  * 给出三种资源
    > 游客可访问：index.jsp
    > 会员可访问：/users/user.jsp
      * 过滤器：/users/*
        > 从session中获取用户名，如果存在用户名，就一定是会员或管理员，放行；
	> 如果session不存在用户名，向request中保存错误信息，转发回到login.jsp
    > 管理员可访问：/admin/admin.jsp
      * 过滤器：/admin/*
        > 从session中获取用户名，如果存在用户名
	  * 如果用户名是icore，放行；否则保存错误信息到reqeust，转发回到login.jsp
	> 从session中获取的用户名不存在
	  * 保存错误信息，转发到login.jsp
  * 登录：
    > login.jsp --> 表单页面
    > LoginServlet --> 认证，名称是icore，那么就是管理员；如果名称不是icore，那么就是普通会员！
      * 成功：把当前用户名保存到session中，转发到/index.jsp

===========================================================================

3. 解决全站编码问题
　* 请求编码：
    > POST：request.setCharacterEncoding("utf-8");
    > GET：
      * 获取参数：String username = request.getParameter("username");
      * 回退重解：username = new String(username.getBytes("iso-8859-1"), "utf-8");
  * 响应编码：response.setContentType("text/html;charset=utf-8");

---------------

3.1 写一个登录案例
  * login.jsp（post、get）
  * LoginServlet

3.2 再写一个编码过滤器，来处理编码！
  
===========================================================================

4. 页面静态化

4.1 图书管理
  * 需求：
    > 查询所有
    > 按分类查询图书
  * 数据库表
    > bid 主键
    > bname 书名
    > price 单价
    > category 分类

CREATE TABLE t_book(
  bid INT PRIMARY KEY AUTO_INCREMENT,
  bname VARCHAR(50),
  price DECIMAL(5,2),
  category INT
);

TRUNCATE TABLE t_book;

INSERT INTO t_book VALUES(NULL, 'JAVASE_1', 10, 1);
INSERT INTO t_book VALUES(NULL, 'JAVASE_2', 20, 1);
INSERT INTO t_book VALUES(NULL, 'JAVASE_3', 30, 1);
INSERT INTO t_book VALUES(NULL, 'JAVASE_4', 40, 1);
INSERT INTO t_book VALUES(NULL, 'JAVAEE_1', 50, 2);
INSERT INTO t_book VALUES(NULL, 'JAVAEE_2', 60, 2);
INSERT INTO t_book VALUES(NULL, 'JAVAEE_3', 70, 2);
INSERT INTO t_book VALUES(NULL, 'JAVAFramework_1', 80, 3);
INSERT INTO t_book VALUES(NULL, 'JAVAFramework_2', 90, 3);

SELECT * FROM t_book;

  * 页面：
    > index.jsp：
      * 四个链接：
        > 查询所有
	> JavaSE分类
	> JavaEE分类
	> Java框架分类
    > list.jsp：显示查询结果

  * BookServlet
    > findAll()；查询所有
    > findByCategory：按分类查询

  * BookService
    > List<Book> findAll()
    > List<Book> findByCategory(int category)

  * BookDao
    > List<Book> findAll()
    > List<Book> findByCategory(int category)

  ------------

  搭建环境
  1. 导包
    * mysql驱动
    * c3p0
      > 两个jar一个配置文件
    * dbutils
    * icore-tools
    * beanutils
      > logging
  2. 数据库表
  3. 创建包和类

===============================

页面静态化

1. 在第一次访问时，把数据写入到html静态页面中，重定向到静态页面！
2. 重定向到静态页面！

------------
分析：

1. 编写一个response的装饰类
  把response的字符流换了
  重写getWriter()方法！！！

2. 编写Filter
  判断html是否生成，如果已经生成，直接重定向到html
  如果没有生成，先调包放行，然后再重定向！

---------------

1. 创建一个目录：static
  * 用来存放生成的html页面

2. 创建response的装饰类
  * StaticResponse

3. 创建Filter
  * 查看html文件是否存在
    > 如果不存在，调包，放行！（目的是为了生成html）
  * 重定向！


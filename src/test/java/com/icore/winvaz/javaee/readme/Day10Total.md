内容：
* response
* request
* 编码
* 路径

------------------

服务器处理请求的流程：
  服务器每次收到请求时，都会为这个请求开辟一个新的线程。（一个请求一个线程！）
  服务器会把客户端的请求数据封装到request对象中，request就是请求数据的载体！（袋子）
  服务器还会创建response对象，这个对象与客户端连接在一起，它可以用来向客户端发送响应。（手机）

------------------


response：其类型为HttpServletResponse
  ServletResponse-->与协议无关的类型
  HttpServletResponse-->与http协议相关的类型

　　回忆一下http协议！http协议中响应的内容包含哪些东西呢？
　　* 状态码：200表示成功、302表示重定向、404表示客户端错（访问的资源不存在）、500表示服务器端错
　　　　> sendError(int sc) --> 发送错误状态码，例如404、500
　　　　> sendError(int sc, String msg) --> 也是发送错误状态码，还可以带一个错误信息！  response.sendError(404, "您要访问的资源真实的存在，就不给你看！");
　　　　> setStatus(int sc) --> 发送成功的状态码，可以用来发送302！可以用来发302，也可以发200，但不发默认为200。
　　　　案例：
　　　　> 发送404
　　* 响应头：Content-Type、Refresh、Location等等
　　　　头就是一个键值对！可能会存在一个头（一个名称，一个值），也可能会存在一个头（一个名称，多个值！）
　　　　> *****setHeader(String name, String value)：适用于单值的响应头，例如：response.setHeader("aaa", "AAA");
　　　　> addHeader(String name, String value)：适用于多值的响应头
		response.addHeader("aaa", "A");
		response.addHeader("aaa", "AA");
		response.addHeader("aaa", "AAA");
　　　　> setIntHeader(String name, int value)：适用于单值的int类型的响应头
	`	response.setIntHeader("Content-Length", 888);resopnse.setHeader("Content-Length", "888");
　　　　> addIntHeader(String name, int value)：适用于多值的int类型的响应头
　　　　> setDateHeader(String name, long value)：适用于单值的毫秒类型的响应头
		response.setDateHeader("expires", 1000 * 60 * 60 * 24);
　　　　> addDateHeader(String name, long value)：适用于多值的毫秒类型的响应头
　　　　案例：
　　　　> 发送302，设置Location头，完成重定向！
　　　　> 定时刷新：设置Refresh头，你可以把它理解成，定时重定向！
　　　　> 禁用浏览器缓存：Cache-Control、pragma、expires
　　　　> <meta>标签可以代替响应头：<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
　　* 响应体：通常是html、也可以是图片！
　　　　> response的两个流：
　　　　　　<> ServletOutputStream，用来向客户端发送字节数据。ServletOutputStream out = resopnse.getOutputStream();
　　　　　　<> PrintWriter，用来向客户端发送字符数据！需要设置编码。PrintWriter writer = response.getWriter();
　　　　　　<> 两个流不能同时使用！
　　　　案例：
　　　　> 使用PrintWriter发送字符数据
　　　　> 使用ServletOutputStream发送字节数据（图片）
　　* 重定向：设置302，设置Location！其中变化的只有Location，所以java提供了一个快捷方法，完成重定向！
　　　> sendRedirect(String location)方法

------------------

request --> 封装了客户端所有的请求数据！
请求行
请求头
空行
请求体（GET没体）

　　回忆一下http协议！请求协议中的数据都可以通过request对象来获取！
　　* 获取常用信息
　　　　> 获取客户端IP，案例：封IP。request.getRemoteAddr()
　　　　> 请求方式，request.getMethod()，可能是POST也可能是GET
　　* 获取HTTP请求头
　　　　> *****String getHeader(String name)，适用于单值头
　　　　> int getIntHeader(String name)，适用于单值int类型的请求头
　　　　> long getDateHeader(String name)，适用于单值毫秒类型的请求头
　　　　> Enumeration<String> getHeaders(String name)，适用于多值请求头

　　　案例：
　　　　> 通过User-Agent识别用户浏览器类型
　　　　> 防盗链：如果请求不是通过本站的超链接发出的，发送错误状态码404。Referer这个请求头，表示请求的来源！




　　* 获取请求URL
	http://localhost:8080/day10_2/AServlet?username=xxx&password=yyy
　　　　> String getScheme()：获取协议，http
　　　　> String getServerName()：获取服务器名，localhost
　　　　> String getServerPort()：获取服务器端口，8080
　　　　> *****String getContextPath()：获取项目名，/day10_2
　　　　> String getServletPath()：获取Servlet路径，/AServlet
　　　　> String getQueryString()：获取参数部分，即问号后面的部分。username=xxx&password=yyy
　　　　> String getRequestURI()：获取请求URI，等于项目名+Servlet路径。/day10_2/AServlet
　　　　> String getRequestURL()：获取请求URL，等于不包含参数的整个请求路径。http://localhost:8080/day10_2/AServlet

　　* 获取请求参数：请求参数是由客户端发送给服务器的！有可能是在请求体中（POST），也可能是在URL之后（GET）
　　　　请求参数：有一个参数一个值的，还有一个参数多个值！
　　　　> *****String getParameter(String name)：获取指定名称的请求参数值，适用于单值请求参数
　　　　> String[] getParameterValues(String name)：获取指定名称的请求参数值，适用于多值请求参数
　　　　> Enumeration<String> getParameterNames()：获取所有请求参数名称
　　　　> *****Map<String,String[]> getParameterMap()：获取所有请求参数，其中key为参数名，value为参数值。
　　　　案例：超链接参数
　　　　案例：表单数据

　　* 请求转发和请求包含（一个请求跨多个Servlet就是请求转发或包含！）
　　　　转发：留头不留体！！！
　　　　包含：留头又留体！！！都留！

　　　　RequestDispatcher rd = request.getRequestDispatcher("/MyServlet");  使用request获取RequestDispatcher对象，方法的参数是被转发或包含的Servlet的Servlet路径
　　　　请求转发：*****rd.forward(request,response);
　　　　请求包含：rd.include(request,response);

　　　　有时一个请求需要多个Servlet协作才能完成，所以需要在一个Servlet跳到另一个Servlet！
　　　　> 一个请求跨多个Servlet，需要使用转发和包含。
　　　　> 请求转发：由下一个Servlet完成响应体！当前Servlet可以设置响应头！（留头不留体）
　　　　> 请求包含：由两个Servlet共同未完成响应体！（都留）
　　　　> 无论是请求转发还是请求包含，都在一个请求范围内！使用同一个request和response！
　　* request域
　　　　Servlet中三大域对象：request、session、application，都有如下三个方法：
　　　　> void setAttribute(String name, Object value)
　　　　> Object getAttribute(String name)
	> void removeAttribute(String name);
　　　　> 同一请求范围内使用request.setAttribute()、request.getAttribute()来传值！前一个Servlet调用setAttribute()保存值，后一个Servlet调用getAttribute()获取值。

　　* 请求转发和重定向的区别
　　　　> 请求转发是一个请求一次响应，而重定向是两次请求两次响应
　　　　> 请求转发地址栏不变化，而重定向会显示后一个请求的地址
　　　　> 请求转发只能转发到本项目其他Servlet，而重定向不只能重定向到本项目的其他Servlet，还能定向到其他项目
　　　　> 请求转发是服务器端行为，只需给出转发的Servlet路径(/BServlet)，而重定向需要给出requestURI(/day10_2/BServlet)，即包含项目名！
　　　　> 请求转发和重定向效率是转发高！因为是一个请求！
　　　　　　<> 需要地址栏发生变化，那么必须使用重定向！
　　　　　　<> 需要在下一个Servlet中获取request域中的数据，必须要使用转发！
　　
------------------

编码（字符）
　　常见字符编码：iso-8859-1(不支持中文)、gb2312、gbk、gb18030(系统默认编码，中国的国标码)、utf-8(万国码，支持全世界的编码，所以我们使用这个)

1. 响应编码
　　* 当使用response.getWriter()来向客户端发送字符数据时，如果在之前没有设置编码，那么默认使用iso，因为iso不支持中文，一定乱码
　　* 在使用response.getWriter()之前可以使用response.setCharaceterEncoding()来设置字符流的编码为gbk或utf-8，当然我们通常会选择utf-8。这样使用response.getWriter()发送的字符就是使用utf-8编码的。但还是会出现乱码！因为浏览器并不知道服务器发送过来的是什么编码的数据！这时浏览器会使用gbk来解码，所以乱码！
　　* 在使用response.getWriter()之前可以使用response.setHeader("Content-type","text/html;charset=utf-8")来设置响应头，通知浏览器服务器这边使用的是utf-8编码，而且在调用setHeader()后，还会自动执行setCharacterEncding()方法。这样浏览器会使用utf-8解码，所以就不会乱码了！
　　* setHeader("Content-Type", "text/html;charset=utf-8")的快捷方法是：setContentType("text/html;charset=utf-8)。
2. 请求编码
　　* 客户端发送给服务器的请求参数是什么编码：
　　　　客户端首先要打开一个页面，然后在页面中提交表单或点击超链接！在请求这个页面时，服务器响应的编码是什么，那么客户端发送请求时的编码就是什么。
　　* 服务器端默认使用什么编码来解码参数：
　　　　服务器端默认使用ISO-8859-1来解码！所以这一定会出现乱码的！因为iso不支持中文！
　　* 请求编码处理分为两种：GET和POST：GET请求参数不在请求体中，而POST请求参数在请求体中，所以它们的处理方式是不同的！
　　* GET请求编码处理：
　　　　> String username = new String(request.getParameter("iso-8859-1"), "utf-8");
　　　　> 在server.xml中配置URIEncoding=utf-8
　　* POST请求编码处理：
　　　　> String username = new String(request.getParameter("iso-8859-1"), "utf-8");
　　　　> 在获取参数之前调用request.setCharacterEncoding("utf-8");

3. URL编码
　　表单的类型：Content-Type: application/x-www-form-urlencoded，就是把中文转换成%后面跟随两位的16进制。
　　为什么要用它：在客户端和服务器之间传递中文时需要把它转换成网络适合的方式。

　　* 它不是字符编码！
　　* 它是用来在客户端与服务器之间传递参数用的一种方式！
　　* URL编码需要先指定一种字符编码，把字符串解码后，得到byte[]，然后把小于0的字节+256，再转换成16进制。前面再添加一个%。
　　* POST请求默认就使用URL编码！tomcat会自动使用URL解码！
　　* URL编码：String username = URLEncoder.encode(username, "utf-8");
　　* URL解码：String username = URLDecoder.decode(username, "utf-8");

　　最后我们需要把链接中的中文参数，使用url来编码！今天不行，因为html中不能给出java代码，但后面学了jsp就可以了。

------------------

路径
　　* web.xml中<url-pattern>路径，（叫它Servlet路径！）
　　　　> 要么以“*”开关，要么为“/”开头
　　* 转发和包含路径
　　　　> *****以“/”开头：相对当前项目路径，例如：http://localhost:8080/项目名/　request.getRequestdispacher("/BServlet").for...();
　　　　> 不以“/”开头：相对当前Servlet路径。 request.getRequestdispacher("/BServlet").for...();，假如当前Servlet是：http://localhost:8080/项目名/servlet/AServlet，　就是http://localhost:8080/项目名/servlet/BServlet
　　* 重定向路径（客户端路径）
　　　　> 以“/”开头：相对当前主机，例如：http://localhost:8080/，　所以需要自己手动添加项目名，例如；response.sendRedirect("/day10_1/Bservlet");
　　* 页面中超链接和表单路径
　　　　> 与重定向相同，都是客户端路径！需要添加项目名
	> <form action="/day10_1/AServlet">
	> <a href="/day10_/AServlet">
	> <a href="AServlet">，如果不以“/”开头，那么相对当前页面所在路径。如果是http://localhost:8080/day10_1/html/form.html。　即：http://localhost:8080/day10_1/html/ASevlet
	> *****建立使用以“/”开头的路径，即绝对路径！
　　* ServletContext获取资源路径
　　　　> 相对当前项目目录，即当然index.jsp所在目录
　　* ClassLoader获取资源路径
　　　　> 相对classes目录
　　* Class获取资源路径
　　　　> 以“/”开头相对classes目录
　　　　> 不以“/”开头相对当前.class文件所在目录。


--------------------

<url-pattern>没有太多要求，它必须以“/”开头，它是在定义Servlet路径<url-pattern>


服务器端路径，不需要项目名
转发和包含：Servlet路径！

浏览器使用的都需要使用项目名，它们被称之为客户端路径！！！
重定向：需要项目和Servlet路径！
超链接：项目名和Servlet路径
表单：项目名和Servlet路径

--------------------------

<a href="http://localhost:8080/day10_4/b.html"></a>  完整绝对路径，不用解释
<a href="/day10_4/b.html"></a>  绝对路径，相对http://localhost:8080
<a href="b.html"></a>  相对路径，相对当前文件所在目录。http://locahost:8080/day10_4/xx/b.html

------------------------
request和response

1. 服务器处理请求的流程
  * 当服务器接收到请求时，会把请求数据封装到request中，再创建response
  * 使用这两个对象调用servlet的service()方法
  * 其中request可以用来获取请求数据
  * 其中response可以用来向客户端做响应作用。

  *****request和response对象都是每次请求创建一份！！！每次请求都是新的！

===========================================

repsonse

1. 作用
  * 用来向客户端发送响应信息

2. 响应包含什么
  * 状态码
    > 正确：setStatus(int sc)
    > 错误：
      * sendError(int sc)
      * sendError(int sc, String msg)
  * 响应头
    > setHeader(String name, String value)：一头一值
    > addHeader(String name, String value)：一头多值
    > setIntHeader(String name, int value)：值为int类型
    > addIntHeader(String name, int value)：值为int类型
    > setDateHeader(String name, long value)：值为毫秒值
    > addDateHeader(String name, long value)：值为毫秒值
  * 响应体
    > 字符类型的响应体：PrintWriter out = response.getWriter(); out.print("fdsafdsa");
    > 字节类型的响应体：ServletOutputStream out = response.getOutputStream();
    > 这两个流都是与浏览器绑定在一起的，向流中写数据等同与向浏览器发送数据。
    > 这两个流：老列不相往来！对与一个response而言，只能使用其中一个！

3. 练习使用response
  * 发送404或500
  * 发送1头1值和1头多值
  * 发送响应体：字符的和字节的
  * 玩玩Refresh：定时刷新
  * 于玩重定向：302 + Location

=======================================

处理响应编码问题

1. 我们只需要设置名为Content-Type的响应头就可以处理响应乱码问题
response.setHeader("Content-Type", "text/html;charset=utf-8");
便捷方式
response.setContentType("text/html;charset=utf-8");

  * 默认响应编码使用的是ISO-8859-1（不支持中文）
  * GB2312、GBK、GB18030 --> 一个汉字两个字节
  * Unicode：UTF-8（一个汉字三个字节）、UTF-16(老) .... 
  * 强烈建议使用UTF-8
  
Tomcat要做两件事
  * 把字符串转换成一种编码
    > response.setCharacterEncoding("utf-8");
  * 再告诉浏览器我们使用的是什么编码
    > response.setHeader("Content-Type", "text/html;charset=utf-8");
      * 先把数据转换成utf-8
      * 再添加响应头Content-Type，用来通知浏览器我们服务器使用的是utf-8

========================

response.getWriter()的缓冲区

1. 缓冲区是8KB
  * 向response.getWriter()中写入数据，并不表示发送到浏览器了！而只在缓冲区中！
  * 如果说缓冲区满了，那么才会自动刷新，数据到了浏览器了。
  * 手动刷新，即调用response.getWriter().flush()来手动刷新！
  * 前提是响应没有结束！！！
  * 只要是数据发送了，那么response对象的状态为已提交状态！！


========================
========================
========================

1. request表示请求对象
  * 它封装了所有的请求数据，你可以通过它来获取请求数据
  * 它还是一个域对象，request是一个请求一个request对象！！！

2. 获取参数


3. request对象可以请求转发和请求包含


4. 域与请求转发和请求包含的关系


5. 请求转发和请求包含与重定向的区别

========================

request域功能

1. 域对象的相关方法
* void setAttribute(String name, Object value)
* Object getAttribute(String name)
* void removeAttribute(String name)
* Enumeration getAttributeNames()
  它需要与请求转发和请求包含一起使用！！！

2. request获取请求头

* String getHeader(String name)：获取指定名称的请求头；
* Enumeration getHeaderNames()：获取所有请求头名称；
* int getIntHeader(String name)：获取值为int类型的请求头。
* long getDateHeader(String name)：获取值为毫秒类型的请求头。

思路题：
1). Servlet获取Referer请求头，查看请求是否从本站发出：
  * 如果是：say hello
  * 如果不是：重定向本站主页！

2). Servlet获取User-Agent请求头，输出当前用户的浏览器品牌

-----------------------

3. request的常用方法
  * String getContentType()：获取内容类型，GET方式返回null，只有POST会返回：application/x-www-form-urlencoded，说明post请求体内容是经过url编码的！
  * String getMethod()：返回请求方法，例如：GET或POST之类的东西
  * Locale getLocale()：获取的是当前客户端支持的语言环境！zh,CN  en,US
  * void setCharacterEncoding(String code)：设置POST请求参数的编码
  * String getContextPath()：获取项目名称，例如；/day10_1
  * String getQueryString()：获取参数字符串，例如GET请求参数在URL之后：username=qdmmy6&password=123
  * String getServletPath()：返回Servlet路径，例如：/AServlet
  * String getRequestURI()：请求URI表示：contextPath + servletPath，例如；/day10_1/AServlet
  * StringBuffer getRequestURL()：表示整个请求的URL：http://localhost:8080/day10_1/AServlet，不包含参数部分。
  * String getRemoteAddr()：获取客户端IP地址！
  * String getScheme()：返回请求协议，例如：http
  * String getServerName()：返回主机名，例如：localhost
  * int getServerPort()：返回服务器端口号，例如：8080

-----------------------

4. request获取请求参数(请求编码问题)(一参一值和一参多值)
  * String getParameter(String name)：通过参数名获取参数值，适用与一参一值
  * String[] getParameterValues(String name)：一参多值
  * Map getParameterMap()：获取所有参数封装到一个Map<String,String[]>中。{username:[qdmmy6], password:[123]}
  * Enumeration getParameterNames()：获取所有参数名称

----------------

5. 请求编码问题
  * POST：在调用获取参数方法之前，先调用request.setCharacterEnding("utf-8")
  * GET：
    > 1. 获取到参数：String username = request.getParameter("username");
    > 2. 从解再编：username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
  
-----------

GET请求参数不在请求体中
POST请求参数在请求体中

无论客户端浏览器发送过来的是什么编码的东西，默认情况下tomcat都当成iso-8859-1来处理！
  * POST请求可以处理来设置这个编码，例如：request.setCharacterEncoding("utf-8")，这时Tomcat就会把参数当前utf-8编码来处理！
  * GET请求没有这种方式，这需要我们先获取到使用iso-8859-1得到的乱码！
    > 然后使用iso-8859-1把乱码再回退到字节数组
    > 再使用utf-8进行重解

============================

请求转发和请求包含

1. 请求转发
  * 语句：
    > RequestDispatcher rd = request.getRequestDispatcher("Servlet路径");
    > rd.forward(request,response);//相当与调用了另一个Servlet的service()方法
   
   留头不留体
   AServlet
   /**
     * 请求转发
     * 留头不留体
     * 留体抛异常
     * 
     * 1. 在转发过程中，Tomcat会查看当前response的状态，如果为已提交，那么抛出异常。转发失败！
     * 2. 如果response状态为未提交，那么tomcat会把response的缓冲区清空，再转发！
     */
    response.setContentType("text/html;charset=utf-8"); // 生效
    response.getWriter().print("您好！"); // 失效
    response.getWriter().flush(); // 刷新会导致response的状态为已提交！
    
    // 转发不能在response提交之后
    request.getRequestDispatcher("/BServlet").forward(request, response);
   
   BServlet
   response.getWriter().print("DServlet问候您！"); // 生效
============================

参数和域

1. 参数
  * 参数都是字符串
  * 参数是从客户端发送给服务器
  * 参数使用request.getParameter()等系列方法获取

2. 域
  * 域属性是Object类型
  * 域属性是在多个Servlet中传递的！即都在服务器端！
  * 域属性由我们自己设置，由我们自己获取：
    > request.setAttribute(String name, Object value)
    > request.getAttribute(String name)

============================

请求转发和重定向

1. 转发一个请求，重定向两个请求（*****）
2. 转发浏览器地址栏不变化，重定向变化
3. 转发只能在一个项目内，而重定向没有限制！（***）
4. 转发可以使用request域传递数据，而重定向不能！（*****）
5. 转发只有一个请求（原来是什么方式就是什么方式），而重定向两个请求（第二个请求一定是GET方式）

============================

请求包含

1. 请求包含：
  > 请求转发和请求包含唯一的区别：包含是留头又留体！
  > 语法：
    RequestDispatcher rd = request.getRequestDispatcher("Servlet路径");//与转发无区别
    rd.include(request, response);//请求包含！

============================

请求转发的应用：

1. 在Servlet中向数据库获取数据，保存到request域中
2. 转发到jsp页面，jsp从request域中获取数据，显示在页面上。

============================

页面中的路径：以项目名开头
1. 超链接
2. 表单的路径

<a href="/day10_1/AServlet">...</a>
<form action="/day10_1/AServlet" ...>

-----------------

Servlet中的转发和包含：给出Servlet路径！

1. request.getRequestDispatcher("/AServlet")

-----------------

重定向：以项目名开头

response.sendRedirect("/day10_1/AServlet");

-----------------

获取真实路径

this.getServletContext().getRealPath("/");　-->　相对当前项目路径（带有盘符的真实路径）

-----------------

Class和ClassLoader

classLoader.getResourceAsStream("a.txt"); --> 相对classes下a.txt
classLoader.getResourceAsStream("/a.txt"); --> 与上面相同，相对classes下a.txt

class.getResourceAsStream("a.txt"); --> 相对当前class对象对应的.class文件所在目录
class.getResourceAsStream("/a.txt"); --> 相对当classes路径下的a.txt

============================
============================

URL编码

1. URL编码不是字符编码
  * URL编码它是用来在客户端与服务器端传输使用的一种编码
  * 因为客户端向服务器传递中文时，可能会导致缺失字节！
  * URL编码可以把汉字转换成字母和数字以及百分号的组成传递给服务器
  * 如果客户端传递的是URL编码，服务器会自动解码，不用我们出手！

2. 客户端进行编码、服务器端进行解码
  * 如果客户端是post的表单，那么会自动进行url编码
    > 因为post表单会设置请求头：Content-Type为application/x-www-form-urlencoded
    > <form>元素有一个名为enctype的属性，如果不设置，默认为application/x-www-form-urlencoded
  * 服务器永远都会自动解码

----------
        // 编码
	@Test
	public void fun2() throws UnsupportedEncodingException {
		String s = URLEncoder.encode("服务器", "UTF-8");
		System.out.println(s);
	}
	
        // 解码
	@Test
	public void fun3() throws UnsupportedEncodingException {
		String s = URLDecoder.decode("%E6%9C%8D%E5%8A%A1%E5%99%A8", "UTF-8");
		System.out.println(s);
	}


今后会在超链接中使用URL编码！
超链接中的参数值有可能是中文，我们可以使用URL编码！
但要记住，路径中不能包含中文，但参数值可以是中文。

* http://localhost:8080/day10_1/AServlet?username=张三
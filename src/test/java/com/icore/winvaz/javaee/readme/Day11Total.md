主要内容：
1. JSP基础
2. Cookie
3. HttpSession

================================

JSP基础

1. jsp的作用：
  * Servlet：
    > 缺点：不适合设置html响应体，需要大量的response.getWriter().print("<html>")
    > 优点：动态资源，可以编程。
  * html：
    > 缺点：html是静态页面，不能包含动态信息
    > 优点：不用为输出html标签而发愁
  * jsp(java server pages)：
    > 优点：在原有html的基础上添加java脚本，构成jsp页面。

2. jsp和Servlet的分工：
  * JSP：
    > 作为请求发起页面，例如显示表单、超链接。
    > 作为请求结束页面，例如显示数据。
  * Servlet：
    > 作为请求中处理数据的环节。

3. jsp的组成
  * jsp = html + java脚本 + jsp标签(指令)
  * jsp中无需创建即可使用的对象一共有9个，被称之为9大内置对象。例如：request对象、out对象
  * 3种java脚本：
    > <%...%>：java代码片段(常用)，用于定义0~N条Java语句！方法内能写什么，它就可以放什么！
    > <%=...%>：java表达式，用于输出(常用)，用于输出一条表达式（或变量）的结果。response.getWriter().print( ... );这里能放什么，它就可以放什么！
    > <%!...%>：声明，用来创建类的成员变量和成员方法(基本不用，但容易被考到)，类体中可以放什么，它就可以放什么！


    案例：演示jsp中java脚本的使用！
    案例：演示jsp与servlet分工！

4. jsp原理（理解）
  * jsp其实是一种特殊的Servlet
    > 当jsp页面第一次被访问时，服务器会把jsp编译成java文件（这个java其实是一个servlet类）
    > 然后再把java编译成.class
    > 然后创建该类对象
    > 最后调用它的service()方法
    > 第二次请求同一jsp时，直接调用service()方法。
  * 在tomcat的work目录下可以找到jsp对应的.java源代码。
  * 查看jsp对应java文件：
    > java脚本
    > html
5. jsp注释
  * <%-- ... --%>：当服务器把jsp编译成java文件时已经忽略了注释部分！
  <!--fdsafdsa-->：html注释

＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝

Cookie

1. Http协议与Cookie（了解）
  * Cookie是HTTP协议制定的！先由服务器保存Cookie到浏览器，再下次浏览器请求服务器时把上一次请求得到Cookie再归还给服务器
  * 由服务器创建保存到客户端浏览器的一个键值对！服务器保存Cookie的响应头：Set-Cookie: aaa=AAA  Set-Cookie: bbb=BBB
    > response.addHeader("Set-Cookie", "aaa=AAA");response.addHeader("Set-Cookie", "bbb=BBB");
  * 当浏览器请求服务器时，会把该服务器保存的Cookie随请求发送给服务器。浏览器归还Cookie的请求头：Cookie: aaa=AAA; bbb=BBB
  * Http协议规定（保证不给浏览器太大压力）：
    > 1个Cookie最大4KB
    > 1个服务器最多向1个浏览器保存20个Cookie
    > 1个浏览器最多可以保存300个Cookie
  * 浏览器大战：因为浏览器竞争很激励，所以很多浏览器都会在一定范围内违反HTTP规定，但也不会让一个Cookie为4GB！

2. Cookie的用途
  * 服务器使用Cookie来跟踪客户端状态！
  * 保存购物车(购物车中的商品不能使用request保存，因为它是一个用户向服务器发送的多个请求信息)
  * 显示上次登录名(也是一个用户多个请求)

  **********Cookie是不能跨浏览器的！***********

3. JavaWeb中使用Cookie
  * 原始方式（了解）：
    > 使用response发送Set-Cookie响应头
    > 使用request获取Cookie请求头
  * 便捷方式（精通）：
    > 使用repsonse.addCookie()方法向浏览器保存Cookie
    > 使用request.getCookies()方法获取浏览器归还的Cookie

  Cookie第一例：
    > 一个jsp保存cookie, a.jsp
    > 另一个jsp获取浏览器归还的cookie！ b.jsp

4. Cookie详解
  * Cookie不只有name和value两个属性
  * Cookie的maxAge（掌握）：Cookie的最大生命，即Cookie可保存的最大时长。以秒为单位，例如：cookie.setMaxAge(60)表示这个Cookie会被浏览器保存到硬盘上60秒
    > maxAge>0：浏览器会把Cookie保存到客户机硬盘上，有效时长为maxAge的值决定。
    > maxAge<0：Cookie只在浏览器内存中存在，当用户关闭浏览器时，浏览器进程结束，同时Cookie也就死亡了。
    > maxAge=0：浏览器会马上删除这个Cookie！
  * Cookie的path（理解）：
    > Cookie的path并不是设置这个Cookie在客户端的保存路径！！！
    > Cookie的path由服务器创建Cookie时设置
    > 当浏览器访问服务器某个路径时，需要归还哪些Cookie给服务器呢？这由Cookie的path决定。
    > 浏览器访问服务器的路径，如果包含某个Cookie的路径，那么就会归还这个Cookie。
    > 例如：
      <> aCookie.path=/day11_1/; bCookie.path=/day11_1/jsps/; cCookie.path=/day11_1/jsps/cookie/;
      <> 访问：/day11_1/index.jsp时，归还：aCookie
      <> 访问：/day11_1/jsps/a.jsp时，归还：aCookie、bCookie
      <> 访问：/day11_1/jsps/cookie/b.jsp时，归还：aCookie、bCookie、cCookie
    > Cookie的path默认值：当前访问路径的父路径。例如在访问/day11_1/jsps/a.jsp时，响应的cookie，那么这个cookie的默认path为/day11_1/jsps/
  * Cookie的domain（了解）
    > domain用来指定Cookie的域名！当多个二级域中共享Cookie时才有用。
    > 例如；www.baidu.com、zhidao.baidu.com、news.baidu.com、tieba.baidu.com之间共享Cookie时可以使用domain
    > 设置domain为：cookie.setDomain(".baidu.com");
    > 设置path为：cookie.setPath("/");


Cookie中不能存在中文！！！

// 保存
Cookie c = new Cookie("username", URLEncoder.encode("张三", "utf-8"));//出错！
response.addCookie(c);

// 获取
Cookie[] cs = request.getCookies();
if(cs != null) {
  for(Cookie c : cs){
    if("username".equals(c.getName())) {
      String username = c.getValue();
      username = URLDecoder.decode(username, "utf-8");
    }
  }
}

============================================
会话跟踪

1. HTTP协议是无状态的
  * 建立连接，请求-->响应，然后断开！
  * 建立连接，请求-->响应，然后断开！

2. 会话跟踪技术
  * Cookie：HTTP协议提供的一种会话跟踪技术！
  * HttpSession：服务器提供的会话跟踪技术！底层依赖Cookie

3. Cookie
  * 第一个请求时：服务器会把数据保存到Cookie中，Cookie会被带回到浏览器
  * 第二个请求时，浏览器会把上一次请求的Cookie归还给服务器，服务器来使用！

4. HTTP对Cookie制订的规范（了解）
  * Cookie大小上限为4KB；
  * 一个服务器最多在客户端浏览器上保存20个Cookie；
  * 一个浏览器最多保存300个Cookie；

5. Cookie的生命
  * 大于0：表示Cookie会在客户端的硬盘上保存，过期时间由这个大于0的整数说明！（单位是秒）
  * 小于0：表示Cookie只在浏览器内存存活，关闭浏览器，就死掉！Cookie默认的生命是小于0。
  * 等于0：表示服务器要追杀Cookie，服务器告诉浏览器马上杀死这个Cookie

  Cookie类提供了setMaxAge(60*60*24*365)

6. Cookie的path
  * cookie.setPath(String path);
  * Cookie的路径，与Cookie是否归还相关！
  * Cookie的路径默认值：创建Cookie的资源所在路径！
    > 例如在访问/day11_1/servlet/AServlet，服务器给浏览器发送了一个Cookie，那么这个Cookie的默认路径为/day11_1/servlet
  * Cookie的路径的作用：当访问路径包含当前Cookie路径时，归还这个Cookie；否则不归还。

  * 如果Cookie的路径为/day11_1、/day11_1/servlet
  * 如果访问的路径为http://localhost:8080/day11_1/servlet/BServlet

7. Cookie的domain
  * cookie.setDomain(String domain)
  * 在多个二级域名下共享cookie
  * cookie.setDomain(".baidu.com");
    > cookie.setPath("/");

  www.baidu.com
  zhidao.baidu.com
  tiepa.baidu.com
  news.baidu.com

======================

8. Cookie案例：记住用户名
  * 当用户第一次登录成功后，我们把用户名通过Cookie保存到浏览器
  * 当用户第二次登录时，从Cookie中获取用户名，显示在登录表单中

1). 页面：
  * login.jsp --> 登录表单，请求LoginServlet
    > 获取Cookie，把用户名提取出来，显示在表单中。
  * LoginServlet
    > 获取请求参数：username和password
    > 校验：如果用户名不为icore就表示登录成功，否则登录失败！
      * 如果校验通过（成功），把用户名保存到Cookie中，发送给浏览器


2). 向Cookie中保存中文
  * Cookie中不能保存中文
  * 可以先把中文转换成URL编码，然后保存到Cookie中
  * 在获取Cookie后，再把URL编码，解码，得到中文

============================================
session


1. 什么是session?
  * 它是服务器端的会话跟踪技术
  * 保存在服务器端
  * 它是一个域对象(HttpSession)

2. 会话
  * 从第一次访问服务器开始：会话开始
  * 到关闭浏览器：会话结束
  * session是一个会话创建一个session对象！
  * 只要这N个请求在一个会话范围内，那么就可以使用session传递数据

3. 小案例：认证和鉴权
  * 注册用户在登录时需要认证（通过用户名和密码查询数据库，是否存在这个用户）
    > 在认证成功时，把用户信息保存到当前会话中
  * 在用户登录之后，访问一些受保护资源，这时需要查看用户是否已经登录（鉴权）
    > 查看当前会话中是否保存了用户信息

1). 页面
  * login.jsp --> 登录页面，有登录表单，提交后请求LoginServlet
  * a.jsp --> 受保存页面(买过的东西)
    > 进行鉴权：从session中获取用户信息，如果存在，那么通过，否则输出“您还没有登录”
2). LoginServlet
  * 获取用户名、密码，进行认证
    > 成功：把用户信息保存到session中，输出“成功”
    > 失败：输出“失败”

--------------------

1. 创建session
  * HttpSession session = request.getSession();

============================================
============================================
============================================

session原理

1. session是服务器端组件
  * 由服务器创建
  * 保存在服务器
  * 由服务器使用

2. session底层依赖cookie
  * 首次访问服务器，创建一个session对象，存放到session池中(Map)
  * 再把session的id保存到Cookie中，发送给浏览器
  * 当客户端再次访问服务器，会归还cookie，即sessionId
  * 服务器通过sessionId找到对应的session对象，进行操作！！！
  * 保存sessionid的Cookie是内存Cookie!!!

3. 销毁session不是在关闭浏览器时
  * 关闭浏览器，只是客户端丢失sessionid，并没有销毁session
    > 但这个session从此没有办法再操作！当超时后服务器才会销毁session！（默认空闲30分钟）

4. session创建不是第一次访问服务器
  * 首次调用request.getSession()方法才会创建session
    > 获取Cookie中的sessionid，如果不存在这个cookie，创建session，返回这个刚刚创建的session对象
    > 获取cookie中的sessionid，如果存在sessionid，那么这个方法会使用sessionid去查找对应的session对象，如果没有查找到，创建session
    > 获取cookie中的sessionid，如果存在sessionid，那么这个方法会使用sessionid去查找对应的session对象，如果查找到了，直接返回
  * 任何jsp页面对应的servlet中都存在request.getSession()这条语句！
  * 如果首次访问的是servlet，而这个servlet中不包含request.getSession()，那么就不会创建session对象。

5. 设置session的最大空闲时间
  <session-config>
  	<session-timeout>15</session-timeout>
  </session-config>

6. HttpSession的常用API
  * String getId()：获取sessionId，其实它就是一个UUID，32位长的字符串；
  * int getMaxInactiveInterval()：获取session可以的最大不活动时间（秒），默认为30分钟。当session在30分钟内没有使用，那么Tomcat会在session池中移除这个session；
  * void setMaxInactiveInterval(int interval)：设置session允许的最大不活动时间（秒），如果设置为1秒，那么只要session在1秒内不被使用，那么session就会被移除；
  * long getCreationTime()：返回session的创建时间，返回值为当前时间的毫秒值；
  * long getLastAccessedTime()：返回session的最后活动时间，返回值为当前时间的毫秒值；
  * void invalidate ()：让session失效！调用这个方法会被session失效，当session失效后，客户端再次请求，服务器会给客户端创建一个新的session，并在响应中给客户端新session的sessionId；
  * boolean isNew ()：查看session是否为新。当客户端第一次请求时，服务器为客户端创建session，但这时服务器还没有响应客户端，也就是还没有把sessionId响应给客户端时，这时session的状态为新。


  * getId()、invalidate()、isNew()

7. URL重写
  * 如果浏览器禁用了Cookie，那么还可以使用URL重写来支持session
  * 在当前网站上所有的超链接，以及所有的表单，都要在后面链出来一个参数，即jsessionid

  * HttpServletResponse提供了一个方法：String encodeURL(String url)
    > 它会自动识别客户端是否支持Cookie，然后决定是否来做url重写！
    > 它真正的原理是：查看名为JSESSIONID的Cookie，如果存在，那么不重写，如果不存在，那么重写
  * <a href='<%=response.encodeURL("/day11_5/AServlet")%>'>点击这里</a>

============================================

HttpSession(*****)

1. HttpSession概述
  * HttpSession是由JavaWeb提供的，用来会话跟踪的类。session是服务器端对象，保存在服务器端！！！
  * HttpSession是Servlet三大域对象之一(request、session、application(ServletContext))，所以它也有setAttribute()、getAttribute()、removeAttribute()方法
  * HttpSession底层依赖Cookie，或是URL重写！

2. HttpSession的作用
  * 会话范围：会话范围是某个用户从首次访问服务器开始，到该用户关闭浏览器结束！
    > 会话：一个用户对服务器的多次连贯性请求！所谓连贯性请求，就是该用户多次请求中间没有关闭浏览器！
  * 服务器会为每个客户端创建一个session对象，session就好比客户在服务器端的账户，它们被服务器保存到一个Map中，这个Map被称之为session缓存！
    > Servlet中得到session对象：HttpSession session = request.getSession();
    > Jsp中得到session对象：session是jsp内置对象之下，不用创建就可以直接使用！
  * session域相关方法：
    > void setAttribute(String name, Object value);
    > Object getAttribute(String name);
    > void removeAttribute(String name);

3. 案例1：演示session中会话的多次请求中共享数据
  * AServlet：向session域中保存数据
  * BServlet：从session域中获取数据
  * 演示：
    > 第一个请求：访问AServlet
    > 第二个请求：访问BServlet

4. 案例2：演示保存用户登录信息（精通）
  * 案例相关页面和Servlet：
    > login.jsp：登录页面
    > succ1.jsp：只有登录成功才能访问的页面
    > succ2.jsp：只有登录成功才能访问的页面
    > LoginServlet：校验用户是否登录成功！
  * 各页面和Servlet内容：
    > login.jsp：提供登录表单，提交表单请求LoginServlet
    > LoginServlet：获取请求参数，校验用户是否登录成功
      <> 失败：保存错误信息到request域，转发到login.jsp(login.jsp显示request域中的错误信息)
      <> 成功：保存用户信息到session域中，重定向到succ1.jsp页面，显示session域中的用户信息
    > succ1.jsp：从session域获取用户信息，如果不存在，显示“您还没有登录”。存在则显示用户信息
    > succ2.jsp：从session域获取用户信息，如果不存在，显示“您还没有登录”。存在则显示用户信息

  只要用户没有关闭浏览器，session就一直存在，那么保存在session中的用户信息也就一起存在！那么用户访问succ1和succ2就会通过！
  
5. HttpSession原理（理解）
  * request.getSession()方法：
    > 获取Cookie中的JSESSIONID：
      <> 如果sessionId不存在，创建session，把session保存起来，把新创建的sessionId保存到Cookie中
      <> 如果sessionId存在，通过sessionId查找session对象，如果没有查找到，创建session，把session保存起来，把新创建的sessionId保存到Cookie中
      <> 如果sessionId存在，通过sessionId查找到了session对象，那么就不会再创建session对象了。
      <> 返回session
    > 如果创建了新的session，浏览器会得到一个包含了sessionId的Cookie，这个Cookie的生命为-1，即只在浏览器内存中存在，如果不关闭浏览器，那么Cookie就一直存在。
    > 下次请求时，再次执行request.getSession()方法时，因为可以通过Cookie中的sessionId找到session对象，所以与上一次请求使用的是同一session对象。
  
  * 服务器不会马上给你创建session，在第一次获取session时，才会创建！request.getSession();

  * request.getSession(false)、request.getSession(true)、request.getSession()，后两个方法效果相同，
    > 第一个方法：如果session缓存中(如果cookie不存在)，不存在session，那么返回null，而不会创建session对象。

6. HttpSession其他方法： 
  * String getId()：获取sessionId；
  * int getMaxInactiveInterval()：获取session可以的最大不活动时间（秒），默认为30分钟。当session在30分钟内没有使用，那么Tomcat会在session池中移除这个session；
  * void invalidate()：让session失效！调用这个方法会被session失效，当session失效后，客户端再次请求，服务器会给客户端创建一个新的session，并在响应中给客户端新session的sessionId；
  * boolean isNew()：查看session是否为新。当客户端第一次请求时，服务器为客户端创建session，但这时服务器还没有响应客户端，也就是还没有把sessionId响应给客户端时，这时session的状态为新。

7. web.xml中配置session的最大不活动时间
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>

8. URL重写（理解）

　　就是把所有的页面中的路径，都使用response.encodeURL("..")处理一下！

  * session依赖Cookie，目的是让客户端发出请求时归还sessionId，这样才能找到对应的session
  * 如果客户端禁用了Cookie，那么就无法得到sessionId，那么session也就无用了！
  * 也可以使用URL重写来替代Cookie
    > 让网站的所有超链接、表单中都添加一个特殊的请求参数，即sessionId
    > 这样服务器可以通过获取请求参数得到sessionId，从而找到session对象。
  * response.encodeURL(String url)
    > 该方法会对url进行智能的重写：当请求中没有归还sessionid这个cookie，那么该方法会重写url，否则不重写！当然url必须是指向本站的url。


9. 一次性图形验证码
  * 编写VerifyCode，用来生成一次图形验证码
  * 编写VerfiyServlet：
    > 使用VerfiyCode来生成图形验证码
    > 把文本保存到session中
    > 把图片响应给客户。
  * 在login.jsp页面中
    > <img>元素请求VerfiyServlet，页面中显示图形验证码
    > 表单给出用户输入的验证码
  * 在LoginServlet中
    > 获取用户输入的验证码，请求参数！！！
    > 获取session中保存的真正的验证码文本，这是在打开login.jsp时已经保存到session中的
    > 比较用户输入的验证码与session中真正的验证码


=======================================
=======================================
=======================================

JSP

1. 什么是JSP
 JSP即java server pages，它是JavaWeb的动态资源。
 JSP = html + java脚本 + jsp动作标签(包含EL表达式)
 
3. jsp由来
  html都是静态的！
  servlet可以有动态的！
  在servlet中输出html太麻烦
  但在servlet中可以使用动态信息。

  jsp可以处理这个问题：在原有的html基础上添加jsp脚本（java代码片段）！

2. JSP中java脚本
 * <% ... %>：代码段（方法内可以放什么，它就可以放什么），Java语句
 * <%= ... %>：表达式（response.getWriter().print()，其中print方法的参数能放什么，它就可以放什么），只是表达式！表达式的值会被输出到客户端
 * <%! ... %>：定义（class定义中可以放什么，它就可以放什么），定义成员！例如例如成员变量，方法等

<%
  int a = 10;//定义变量
%>
<%
  out.println(a);//输出变量
%>
<%=a%>//输出变量
<%!
  private String hello = "world";
  public String sayHello() {
    return hello;
  }
%>
<%=sayHello()%>

---------------------
jsp的原理

1. JSP是一种特殊的Servlet
2. JSP的执行流程
  * 当客户端访问一个.jsp文件时，执行的是JspServlet
  * JspServlet把.jsp文件转换成.java文件(Servlet)
  * 再把.java编译成.class文件
  * 创建Servlet的实例对象，调用service()方法。
  * 当客户端再次访问jsp时，直接调用service()方法！
3. jsp的真身
  * 就是jsp生成的.java文件
  * 在tomcat\work目录下！

4. jsp内容与真身内容的对应关系
  * html：输出字符串常量
  * <% ... %>：原样放到_jspService()方法中
  * <%= ... %>：放到out.print()方法的参数中
  * <%! ... %>：在真身是由类体直接包含！
  * jsp动态标签：在真身变成方法调用！(创建对象，调用对象的方法)

-----------------------
4. JSP真身

  * JSP也是Servlet
  * JSP会在第一次被请求时编码成.java，再编码成.class文件，它其实就是一个Servlet，在处理请求时执行service()方法。

  查看真身可以得到，jsp中有9个对象可以无需创建即可使用，它们就是jsp九大内置对象。
  
JSP九大内置对象

* request --> HttpServletRequest
* response --> HttpServletResponse
* session --> HttpSession
* application --> ServletContext
* config --> ServletConfig
* page --> Object，Object page = this;
* pageContext --> 后面会学（一个顶九个）
* exception --> 异常对象
* out --> 相当与response.getWriter()
-----------------------
jsp小问题

1. 在其他方法中不能使用九大内置对象

<%!
public void fun1() {
	out.print("hello");//这个方法内没有out对象
	System.out.println("hello");
}
%>
-----------------------

5. JSP注释

  * <!-- -->，html注释(Tomcat会把它当成普通的html标签对待, out.write("<!-- ... -->");)，不是JSP注释，它会出现在JSP的真身中，并发送到客户端，但客户端浏览器不会显示它。
  * <%-- --%>，JSP注释(Tomcat会在把jsp生成.java时忽略jsp注释，这个东西在真身中不存在！)，它不会出现在JSP真身中，也就不可能发送到客户端浏览器了。

================================
================================
================================

会话跟踪

1. 什么是会话
  * 用户拨打10086，从服务台接通后会话开始；
  * 用户发出话费查询请求，服务台响应。这是该会话中的一个请求；
  * 用户发出套餐变更请求，服务台响应。这是该会话中的又一个请求；
  * ...
  * 用户挂断电话，会话结束。

2. 会话的特性
  * 一个会话中可能包含多个请求；
  * 一个会话中发出请求的用户是唯一的；

3. JavaWeb会话
  * 从用户打开本站第一个页面开始，会话也开始了；
  * 用户会发出0~n个请求；
  * 用户关闭浏览器会话结束了。

4. 什么是会话跟踪技术
  HTTP是无状态协议，也就是没有记忆力的协议，每个请求之间无法共享数据。这就无法知道会话什么时候开始，什么时候结束，也无法确定发出请求的用户身份。这说明需要使用额外的手段来跟踪会话！
  * 在一个会话中共享数据即会话跟踪技术

------------------------------------

Cookie

1. 什么是Cookie

* Cookie是HTTP协议的规范之一，它是服务器和客户端之间传输的小数据。
* 首先由服务器通过响应头把Cookie传输给客户端，客户端会将Cookie保存起来。
* 当客户端再次请求同一服务器时，客户端会在请求头中添加该服务器保存的Cookie，发送给服务器。
* Cookie就是服务器保存在客户端的数据！
* Cookie就是一个键值对！！！

2. Cookie规范
* Cookie通过请求头和响应头在服务器与客户端之间传输；
* Cookie大小限制在4KB之内；
* 一台服务器在一个客户端最多保存20个Cookie；
* 一个浏览器最多可以保存300个Cookie；

虽然Cookie规范是如此，但在今天，浏览器厂商的竞争异常激烈，所以多少会超出Cookie规则的限制。但也不会超出过多！

3. Cookie与请求头和响应头

* 服务器向客户端发送Cookie的响应头为Set-Cookie，例如：Set-Cookie:cookiename=cookievalue
* 客户端向服务器发送Cookie的请求头为Cookie，例如：Cookie:cookiename=cookievalue

4. Servlet中向客户端发送Cookie
 Cookie cookie1 = new Cookie("test1", "abcdefg");
 Cookie cookie2 = new Cookie("test2", "ABCDEFG");
 response.addCookie(cookie1);
 response.addCookie(cookie2);

5. Servlet中获取客户端发送过来的Cookie

 Cookie[] cs = request.getCookies();
 if(cs != null) {
    for(Cookie c : cs) {
      System.out.println(c.getName() + "=" + c.getValue());
    }
 }

=============================

Cookie的细节

1. Cookie的maxAge
  当服务器创建Cookie对象后，可以调用setMaxAge()方法设置Cookie的最大生命。
  * maxAge > 0：表示Cookie在客户端硬盘上保存的最大时间，单位为秒；
  * maxAge < 0：表示Cookie不会被浏览器保存到硬盘上，而只在浏览器内存中存活，一旦客户端关闭浏览器在，那么Cookie就消失；
  * maxAge == 0：表示删除Cookie，例如客户端硬盘已经存在名为abc的Cookie，如果服务器再向客户端发送名为abc，并且maxAge为0的Cookie，那么表示删除客户端上的名为abc的Cookie。

2. Cookie的path
  浏览器在访问BServlet时，是否要带上AServlet保存的Cookie呢？这要看Cookie的path了。
  现有资源如下：
  * http://localhost:8080/day06_2/servlet/AServlet
  * http://localhost:8080/day06_2/servlet/BServlet，保存名为xxx的Cookie
  * http://loclahost:8080/day06_2/servlet/CServlet，保存名为yyy的Cookie
  * http://loclahost:8080/day06_2/servlet/user/DServlet, 保存名为zzz的Cookie

  // 没有设置Cookie的path
  AServlet {
     Cookie c = new Cookie("xxx", "XXX");
     response.addCookie(c);
  }
  // 设置了Cookie的path为/day06_2
  CServlet {
     Cookie c = new Cookie("yyy", "YYY");
     c.setPath="/day06_2";
    response.addCookie(c);
  }
  DServlet {
    Cookie c = new Cookie("zzz", "ZZZ");
    resposne.addCookie(c);
  }

  
  在BServlet中保存的Cookie没有设置path，那么它的path默认为当前BServlet的所在路径，即“/day06_2/servlet”。
  在CServlet中保存的Cookie设置了path为/day06_2。
  在DServlet中保存的Cookie没有设置path，那么它的path默认为DServlet的所在路径，即“day06_2/servlet/user”


  当访问AServlet时，是否要带上xxx这个Cookie呢？因为AServlet的访问路径为/day06_2/servlet/BServlet，它包含了xxx的path，即/day06_2/servlet，所以需要带上。

  当访问AServlet时，是否要带上yyy这个Cookie呢？因为AServlet的访问路径为/day06_2/servlet/BServlet，它包含了xxx的path，即/day06_2，所以需要带上。

  当访问AServlet时，是否要带上zzz这个Cookie呢？因为AServlet的访问路径为/day06_2/servlet/BServlet，它不包含zzz的path，即/day06_2/servlet/user，所以不会带上。

  
3. Cookie的domain
  Cookie的path是在同一主机中指定共享Cookie，如果主机不同那么就一定不能共享Cookie，无论path是什么。
  如果希望不同的二级域名中可以共享Cookie，那么就要设置Cookie的domain了。
  例如：news.baidu.com、tieba.baidu.com、zhidao.baidu.com，它们的域名不同，但百度希望它们之间可以共享Cookie，那么就要设置domain了。

  1). 设置Cookie的path为“/”，例如：cookie.setPath("/");
  2). 设置Cookie的domain，例如：cookie.setDomain(".baidu.com")，其中domain中没有指定域名前缀！

  在news.baidu.com主机中的某个项目中保存了Cookie
  在tieba.baidu.com主机中某个项目中获取Cookie

  当然这需要配置两个虚拟主机才行。
  

4. Cookie保存中文
  Cookie的name和value都是不能保存中文的，但可以先把中文转换成URL编码，然后在保存到Cookie的name和value中。
  String name = "姓名";
  String value = "张三";
  name = URLEncoder.encode(name, "utf-8");
  value = URLEncoder.encode(value, "utf-8");

  Cookie c = new Cookie(name, value);
  response.addCookie(c);

  在获取Cookie时，再使用URL解码即可。
  Cookie[] cs = request.getCookies();
  if(cs != null) {
    for(Cookie c : cs) {
      String name = URLDecoder.decode(c.getName(), "utf-8");
      String value = URLDecoder.decode(c.getValue(), "utf-8");
      System.out.println(name + "=" + value);
    }
  }

=============================
=============================
=============================

HttpSession

在JavaWeb中提供了HttpSession类，用来表示http会话。

1. 获取HttpSession
HttpSession session = request.getSession();
HttpSession session = request.getSession(false);

2. 域功能
  session是域对象，所以有setAttribute()和getAttribute()等方法
  服务器会为每个会话创建一个session对象，所以session中的数据可供当前会话中所有servlet共享。

3. 登录案例
  请求功能：
  1. 如果登录功能，在session中保存user对象
  2. 访问index1.jsp，查看session中是否存在user对象，如果存在，说明已经登录过。
  3. 访问index2.jsp，查看session中是否存在user对象，如果存在，说明已经登录过。
  如果关闭了浏览器，那么会话结束，再打开浏览器就开始了一个新会话，那么直接访问index1.jsp或index2.jsp时，session是新的，没有保存user对象，那么表示还没有登录。

4. session的原理
  session是依赖Cookie实现的。
  session是服务器端对象
  当用户第一次使用session时（表示第一次请求服务器），服务器会创建session，并创建一个Cookie，在Cookie中保存了session的id，发送给客户端。这样客户端就有了自己session的id了。但这个Cookie只在浏览器内存中存在，也就是说，在关闭浏览器窗口后，Cookie就会丢失，也就丢失了sessionId。
  当用户第二次访问服务器时，会在请求中把保存了sessionId的Cookie发送给服务器，服务器通过sessionId查找session对象，然后给使用。也就是说，只要浏览器容器不关闭，无论访问服务器多少次，使用的都是同一个session对象。这样也就可以让多个请求共享同一个session了。
  当用户关闭了浏览器窗口后，再打开浏览器访问服务器，这时请求中没有了sessionId，那么服务器会创建一个session，再把sessionId通过Cookie保存到浏览器中，也是一个新的会话开始了。原来的session会因为长时间无法访问而失效。
  当用户打开某个服务器页面长时间没动作时，这样session会超时失效，当用户再有活动时，服务器通过用户提供的sessionId已经找不到session对象了，那么服务器还是会创建一个新的session对象，再把新的sessionId保存到客户端。这也是一个新的会话开始了。

　　设置session超时时间
  web.xml文件中配置如下：
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>


5. session与浏览器
 session对象是保存在服务器端的，而sessionId是通过Cookie保存在客户端的。
 因为Cookie不能在多个浏览器中共享，所以session也不能在多个浏览器中共享。也就是说，使用IE登录后，再使用FireFox访问服务器还是没有登录的状态。

 而且同时打开多个相同浏览器的窗口，是在使用同一session。如果你使用的是老浏览器，例如IE6，那么就会每个窗口一个session。

6. session的API
 * String getId()：获取sessionId；
 * int getMaxInactiveInterval()：获取session可以的最大不活动时间（秒），默认为30分钟。当session在30分钟内没有使用，那么Tomcat会在session池中移除这个session；
 * void setMaxInactiveInterval(int interval)：设置session允许的最大不活动时间（秒），如果设置为1秒，那么只要session在1秒内不被使用，那么session就会被移除；
 * long getCreationTime()：返回session的创建时间，返回值为当前时间的毫秒值；
 * long getLastAccessedTime()：返回session的最后活动时间，返回值为当前时间的毫秒值；
 * void invalidate()：让session失效！调用这个方法会被session失效，当session失效后，客户端再次请求，服务器会给客户端创建一个新的session，并在响应中给客户端新session的sessionId；
 * boolean isNew()：查看session是否为新。当客户端第一次请求时，服务器为客户端创建session，但这时服务器还没有响应客户端，也就是还没有把sessionId响应给客户端时，这时session的状态为新。

7. URL重写
 session依赖Cookie，这是因为服务器需要把sessionId保存到客户端。如果用户的浏览器关闭了Cookie功能，那么session不能使用了！
 还可以在浏览器关闭了Cookie后使用URL重写的方法保存sessionId，这需要在每个URL后面都加上sessionId！这样用户的请求中就包含了sessionId，服务器就可以通过sessionId找到对应的session对象了。
  使用response.encodeURL()方法对URL进行编码，这样URL中会智能的添加sessionId。
　当浏览器支持cookie时，response.encodeURL()方法不会在URL后追加sessionId
  当浏览器不支持cookie时，response.encodeURL()方法会在URL后追加sessionId

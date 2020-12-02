软件体系结构

1. C/S：Client/Servlet，例如QQ就是CS结构
需要编写服务器端程序和客户端程序。
缺点：更新需要两端，总要求客户下载新的客户端程序
优点：安全性比较好

2. B/S：Browser/Server
缺点：安全性较差
优点：只需要编写服务器端程序

3. Web资源
 * 静态资源：html、css、javascript、图片等；
 * 动态资源：javaweb的动态资源有jsp/servlet，其他web程序动态资源有asp、php等。

4. 静态资源和动态资源的区别
 * 客户端访问服务器静态资源，服务器直接响应；
 * 客户端访问服务器动态资源，服务器需要先把动态资源转换成静态资源，再响应。

5. 客户端通过浏览器访问服务器
 * http://主机名:端口号/路径，例如：http://www.icore.cn:80/index.html

6. Web服务器
 * Tomcat（Apache）：当前应用最广的JavaWeb服务器，支持servlet规则，不支持JavaEE规范；
 * JBoss（Redhat红帽）：支持JavaEE规则；
 * GlassFish（Orcale）：支持servlet规则，应用不是很广；
 * Resin（Caucho）：支持JavaEE规则，应用越来越广；
 * Weblogic（Orcale）：要钱的！支持JavaEE规则，适合大型项目；
 * Websphere（IBM）：要钱的！支持JavaEE规则，适合大型项目；

===================================
===================================
===================================

Tomcat

tomcat6支持servlet2.5
tomcat7支持servlet3.0

1. 启动关闭tomcat
  需要先配置JAVA_HOME
  * 双击%CATALANA_HOME%\bin\startup.bat
  * 双击%CATALANA_HOME%\bin\shutdown.bat

　访问服务器：http://localhost:8080/index.jsp
  因为服务器在本机上，所以主机名为localhost，表示本机
  tomcat的默认端口为8080
  index.jsp是主页

2. 修改端口号
  * 修改端口号，%CATALANA_HOME%\conf\server.xml，修改<Connector port="8080">，把8080修改了即可。
  * http协议默认端口为80，也就是说http://localhost，等同与http://localhost:80

  如果把Tomcat端口号修改成80，那么访问服务器就可以无需再给出端口号。

3. tomcat目录结构

 * bin：二进制可执行文件，例如startup.bat和shutdown.bat
 * conf：配置文件，例如：server.xml、context.xml、web.xml、tomcatusers.xml
 * lib：tomcat所需jar包
 * logs：日志文件
 * temp：存放tomcat运行时产生的临时文件，当tomcat关闭后，这个目录中的文件可以删除
 * webapps：这个目录下的每个文件夹对应一个JavaWeb应用程序
 * work：webapps下的应用程序在运行时会自动生成文件，就在work目录下。work目录删除了也没问题，但再次运行应用程序还要再生成work目录和文件。

4. 创建JavaWeb目录：hello
 * 在webapps目录下创建一个hello目录，hello目录就是项目目录了；
 * 在hello目录下创建WEB-INF
 * 在WEB-INF下创建web.xml
 * 在WEB-INF下创建classes目录
 * 在WEB-INF下创建lib目录
 * 在hello目录下创建index.html

　　在web.xml文件中添加如下内容：
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" 
	xmlns="http://java.sun.com/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
</web-app>

　　在index.html中添加如下内容：
<html>
  <head><title>index.html</title></head>
  <body>
    <h1>hello主页</h1>
  </body>
</html>
　　启动tomcat，打开客户端访问http://localhost:8080/hello/index.html

===================================

配置外部应用
　　外部应用既是把应用程序不放到Tomcat的wabapps目录下！而已放在外面，例如：F:/hello

1. 在conf/server.xml下配置，指定外部应用的路径。

<Host name="localhost" appBase="webapps"
      unpackWARs="true" autoDeploy="true">
      <Context path="icore_hello" docBase="F:/hello"/>
</Host>

  * 在<Host>元素下添加<Context>元素，path为URL访问路径，docBase为外部应用的目录。
  * 在浏览器地址栏中访问：http://localhost:8080/icore_hello/index.html

2. 在conf/catalana/localhost下添加配置文件，指定外部应用路径
  * 在conf/catalana/localhost目录下创建icore_hello.xml文件，其中icore_hello就是URL访问路径
  * 在icore_hello.xml文件中添加：<Context docBase="F:/hello"/>，docBase指定外部应用的目录。

3. 缺省web应用
  * 在webapps目录下有一个ROOT目录，它是缺省web应用，访问这个应用的路径：http://localhost:8080/index.jsp
  * 如果把web应用的内部放到webapps/ROOT下，访问URL路径中不用给出应用名称。

4. 配置虚拟主机
  希望：http://www.icore.cn访问web应用。
  * 这需要把域名http://www.icore.cn映射成IP地址：127.0.0.1
  * 需要把tomcat端口号修改为80
  * 需要在server.xml中配置主机，并指定主机的应用目录
  * 在应用目录中添加名为ROOT的web应用。

  1). 找到C:\WINDOWS\system32\drivers\etc\hosts文件，添加127.0.0.1 http://www.icore.cn
  2). 在conf/server.xml中修改端口为80
  3). 在conf/server.xml中添加<Host>元素
<Host name="www.icore.cn" appBase="F:/myapps" unpackWARs="true" autoDeploy="true">
</Host>
    * name：指定该主机域名为www.icore.cn
    * appBase：指定该主机的应用目录为F:/myapps
  4). 在F:/myapps下创建名为ROOT的web应用。

  访问：http://www.icore.cn/index.html
  其中www.icore.cn在本机上会被解析为127.0.0.1，但其他电脑访问时无效。

===================================

使用MyEcipse创建Web项目

1. MyEclipse指定Tomcat
2. MyEclipse创建Web项目
3. MyEclipse发布Web项目到Tomcat下
4. MyEclipse启动关闭Tomcat
5. MyEclipse对已发布项目的修改会对tomcat下项目的进行修改

　　MyEcipse是JavaWeb应用的开发环境，而不是运行环境！运行还是在Tomcat下运行。

　　使用MyEclipse打war包，把war包发布到Tomcat下。

===================================

Tomcat管理页面

===================================
===================================
===================================

HTTP协议

1. 安装HttpWatch

2. http协议
  * 即超文本传输协议。它规定了浏览器与服务器之间的通讯规则。
  * http是基于请求/响应模式的，所以分为请求协议和响应协议

===================================

请求

　　请求内容就是客户端发送给服务器的数据！

1. 请求格式：
  * 请求首行
  * 请求头
  * 空行
  * 请求体（或称之为请求正文）

2. 请求方法
  * 常见请求方法有GET和POST
  * 在浏览器地址栏中发送请求，以及点击超链接都是GET请求
  * 提交表单可以发送GET请求，以及POST请求
  * GET请求没有请求体，但空行是存在的
  * POST请求是存在请求体的

3. 使用HttpWatch获取请求信息
  * 请求行：请求方法 请求路径 请求协议及版本，例如：GET /hello/index.jsp HTTP/1.1
  * 请求头：请求头就是一些键值，格式为：头:值，例如：Host:localhost
  * 空行：就是一个空行，用来与请求体分隔
  * 请求体：GET方法没有请求体，POST才有请求体，请求体内容为：参数名=参数值&参数名=参数值，其中参数值为中文，会使用URL编码。

4. 常见请求头
  * Host：请求的服务器主机名
  * User-Agent：客户端浏览器与操作系统相关信息
  * Accept-Encoding：客户端支持的数据压缩格式
  * Connection：客户端支持的连接方式
  * Cookie：客户端发送给服务器的“小甜点”，它服务器寄存在客户端的。如果当前访问的服务器没有在客户端寄存东西，那么就不会存在它！
  * Content-Length：请求体的长度
  * Referer：当前发出请求的地址，例如在浏览器地址栏直接访问服务器，那么没有这个请求头。如果是在www.baidu.com页面上点击链接访问的服务器，那么这个头的值就是www.baidu.com
    > 作用1：统计来源
    > 作用2：防盗链
  * Content-Type：如果是POST请求，会有这个头，默认值为application/x-www-form-urlencoded，表示请求体内容使用url编码。

===================================

响应

　　响应就是服务器发送给客户端的数据！

1. 响应格式：
  * 响应首行
  * 响应头
  * 空行
  * 响应体（或称之为响应正文）

2. 状态码
　　响应首行的结构：协议及版本 状态码 状态码说明，例如：HTTP/1.1 200 OK

  * 200：请求成功
  * 302：请求重定向
  * 304：请求资源没有改变
  * 404：请求资源不存在，属性客户端错误
  * 500：服务器内部错误

3. 响应头
  * Content-Type：响应正文的MIME类型，例如image/jpeg表示响应正文为jpg图片，例如text/html;charset=utf-8表示响应正文为html，并且编码为utf-8编码。浏览器会通过这一信息来显示响应数据
  * Content-Length：响应正文的长度
  * Set-Cookie：服务器寄存在客户端的“小甜点”，当客户端再次访问服务器时会把这个“小甜点”还给服务器
  * Date：响应时间，可能会有8小时的误差，因为中国的时区问题

　　通知客户端浏览器不要缓存页面的响应头：
  * Expires:-1
  * Cache-Control: no-cache
  * Pragma: no-cache

　　自动刷新响应头，浏览器会在3秒钟后自动重定向到传智主页
  * Refresh: 3;url=http://www.icore.cn

4. 状态码304
　相关头信息
  * Last-Modified：响应头，表示当前资源的最后修改时间；
  * If-Modified-Since：请求头，表示缓存的资源最后修改时间；

　状态码304：表示访问的资源没有改变

  1. 客户端首次访问服务器的静态资源index.html，服务器会把index.html响应给客户端，而且还会添加一个名为Last-Modified的响应头，它说明了当前index.html的最后修改时间
  2. 客户端收到响应后，会把index.html缓存在客户端上，而且还会把Last-Modified缓存起来。
  3. 客户端第二次请求index.html时，会添加名为If-Modified-Since的请求头，它的值是上次服务器响应头Last-Modified，服务器获取到客户端保存的最后修改时间，以及当前资源的最后修改时间进行比较，如果相同，说明index.html没有改动过，那么服务器不会发送index.html，而是响应状态码304，即通知客户端资源没有改变，你可以使用自己的缓存。
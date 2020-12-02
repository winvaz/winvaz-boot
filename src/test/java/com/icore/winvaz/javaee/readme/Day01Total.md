HTML
1.	HTML概念，作用
  HTML超文本标记语言，HyperText MarkUp Language，专门制作网页。需要我们使用浏览器进行观看
  学习的是HTML的标签
 HTML特点：
	非严谨性语言，不区分大小写，混着写，全部小写
	没有具体的语法规则 (){}; 就是标签

2.	标签的语法规则
   开始       网页内容   结束
  <标签的名字>        </标签的名字>  一起完成一对标签 例如:<h1>dafa</h1>
  <标签的名字  /> 例如:<input />

  标签有自己的属性和属性值,可以给标签添加辅助功能
  属性的语法
  <标签名字 属性名字="属性值" 属性名字="属性值"> 标签内容 </标签名字>
3.	标签<html> </html>
   标明这是一个网页文件，写法是固定格式，开始标签写在网页的最开端
   结束标签写在网页的最末尾
   所有的标签都是html的子标签，写在html开始和结束之间
  专业名称:网页标签
4.	标签head
  头部标签，也是html的子标签
  <head>  </head>
  头部标签，包含了网页的编码规则，包括引入的外部文件，网页中的关键字，包含标题信息。
  头标签的子标签
  <title></title>网页的标题，写在两个head之间，标题的具体内容写在两个titile之间
网页中，指定字符的编码表
 <meta / >指定网页中的编码表,利用了标签的属性来实现的 charset = "utf-8"，编码表的名字，是告诉浏览器，解析我的文件，查询utf-8码表

 <link />引入外部文件css样式表   <script></script>引入外部javascript文件
5.	标签body
  <body> </body>表示两个标签中的，内容是网页的主体内容，所有你的内容，写在两个body之间
6.	字体标签font
  调整文字的大小和字体，颜色
  <font> </font> 两个标签之间，写文本内容。利用标签的属性对文字进行调整
  字体属性 face属性 <font face="属性值"> 字体只能是宋体
 颜色 color 属性 color = ""
 大小 size  属性 size="" 网页默认的大小为16px

7.	标签<h1...h6>标题标签
  数字小，文字反而变大
  被css样式表取代

8.	列表标签
	有序列表<ol>  </ol>
  <ol>标签自己的属性 type 字母数组，罗马数字
      start 从几开始编号
	无序列表<ul>  </ul>
  <ul>标签的属性 type 空心圆，实心园，方块
	列表的内容标签 <li> </li>列表中的文字内容，写在两个li之间的

9.	图像标签
 <img /> 可以在网页中添加图片 
 标签中的属性 src = "属性值写路片的路径”
 标签中的属性 width="宽度像素"  height="高度像素"
 网页中图片都是位图，原始的图片大小尽量别动
 边框属性 border = "1"边框的粗细像素"
10.	表格标签
  表格可以对写的网页内容进行定位布局排版
  <table>表格，行标签<tr>写在table中的，单元格标签<td>写在tr中的
  table标签中的属性 border="边框的粗细像素
      标签中的属性 bordercolor="边框颜色"
      标签中的属性 bgcolor="表格背景色"
      标签中的属性 background="表格背景图"
      标签中的属性 align = "对齐属性"
      标签中的属性 cellpadding="像素值" 单元格和表格之间的距离
      标签中的属性 cellspacing="像素值"  单元格与单元格之间的距离
      标签中的属性 width height
  <th>表格标题</th>
  <tr>行标签，控制的是表格中的每一个行
    标签的属性 bgcolor = "颜色"一行的背景颜色
  <td>单元格标签
    表格中所有的内容，都是写在单元格中
     标签中的属性 align="对齐属性左中右"
     标签中的属性，跨行属性 rowspan="行"
     标签中的属性，跨单元格(列) colspan="列"
11.	超链接
  从一个页面跳转到另一个页面的功能
  超链接的标签 <a> 你要实现连接的文字</a>
  属性href="连接的网页路径"  href的属性值写的是# 空连接，没有具体位置
 属性 target 目标，属性值="_blank"在新的窗口中打开链接
 超链接，连接到外部网络，href 地址写全URL  http://域名

12.	框架集 frameset
  将网页进行拆分的技术，以后做B/S的软件的时候，出现的频率很高
13.	表单form
  是客户端的浏览器，和我们服务器端的Java程序进行数据互交的工具
  用户可以在表单中填写数据，发送到我们Java中的程序
  表单，也是在网页中，唯一可以进行输入的地方
  <input>
	普通的文本框，利用input标签中的属性 type="text"表示一个普通的文本框
      input标签的属性 value="文本框打开后的默认显示文字"
	密码框，利用input标签中的属性 type="password"表示密码框
	文件域，用户上传文件使用，利用input标签属性 type="file"
	复选框，可以给用户提供多个选择，打勾 利用input标签的属性 type="checkbox",属性value="复选框的实际的内容"，告诉服务器端，用户选择的具体是什么内容
	单选框，用户只能选择一个，利用input属性 type="radio"，如果使用单选，请你用name属性，保证一组单选按钮的name属性值相同。默认选择属性，页面打开单选按钮就有一个已经被默认选中 属性 checked="checked"
	隐藏域，不显示在页面上，对于页面整体效果无影响,利用input属性 type="hidden"。作用在与页面与页面之间传递数据使用
	按钮，利用input标签属性 type="button"，value属性，属性值，显示在按钮上
	重置按钮。利用input标签属性 type="reset".按钮起作用，重置表单数据的功能，请你使用标签form
	提交按钮，利用input标签属性 type="submit"向服务器发送表单数据的功能

  <select>下拉菜单
   <select> <option>菜单的每一项</option><option>菜单的每一项</option></select>
    使用select ,写一个属性，value，写在option中
  <textarea>
   多行的文本区域 <textarea></textarea>

	<form>标签使用方法属性
   使用，将开始的标签和结束的标签之间，存储页面中的所有表单数据
   属性：action="属性值" ，就是发送的服务器的哪一个程序上(Java类)
   属性：method="属性值"，提交方式 post get
   enctype="multipart/form-data"--告诉服务器有附件上传，请你接收
14.	写一个表单数据发送到服务器
   练习HTML标签
   表单
   提交方式上 get  post 区别
	get提交方式，通过浏览器地址栏进行的发送
	post提交方法，不通过浏览器的地址栏
	get提交方式，数据显示在地址栏上，敏感信息不安全
	port提交方式，不会将数据显示出来的，提交方式安全
	get提交走的是浏览器地址栏，地址栏的容量有限，不适合大数据传输
	post不走地址栏，实现大数据的传输
	get提交方式发送的数据，封装在了数据的请求头
	post提交方式，将数据封装在了请求体

15.	get   post发送中文数据时候的乱码
  以后javaweb开发过程中，获取客户端表单数据，需要一个对象 HttpRequest request中的一个方法，String name= request.getParamter(表单的name属性值 "user")
 但是获取到的汉字是乱码，Web服务器是Tomcat，查询ISO8859-1,采用编码一次，解码一次，还原回汉字
  字符串的编码和解码     getBytes  new String
  name = new String(name.getBytes("iso8859-1"),"utf-8");  或者是gbk
以上方法，对get提交方式有效

 如果客户端浏览器，以post形式将数据发送给服务器，String name= request.getParamter(表单的name属性值 "user")获取到客户端的表单数据，处理乱码的方式和get不同
  调用方法 request.setCharacterEncoding("utf-8");或者写gbk
  String name = request.getParamter("user");不是乱码
以上方法，对post请求有效，setCharacterEncoding处理客户端的请求消息体，不处理请求消息头

16.	客户端向服务器发送数据三种形式
	表单提交 -- get  post
	URL传参数 -- get
	超链接传参数 -- get

17.	其他常用的标签
  <div></div>容器标签，块级元素，本身没有什么实际的意义，空行，霸占整个浏览器的一行
  <span></span>容器标签，块级元素，本身没有什么实际的意义，不会霸占一行

 <pre><pre>保证两个标签中的内容格式
 <marquee> </marquee>
AJAX概述

1. 什么是ajax
  asynchronous javascript and xml（异步的javascript和xml技术）
  * 可以访问服务器，以往js不能访问服务器！
  * 可以异步访问服务器，而且还是局部刷新

2. 什么是异步交互(异步交互与同步交互的区别)
  * 提交表单、点击超链接，传统的交互方式都是同步交互
    > 发一个请求，就要等到服务器响应后，才能做其他事情！在服务器响应之前，都是处于卡的状态！
    > 一个请求，一个响应，再一个请求，再一个响应，这么一个流程！而且还整个页面进行刷新！
  * 异步交互
    > 发一个请求后，无需等待服务器响应，就可以再发请求！没有卡的感觉！（增强了用户体验）
    > 局部刷新，不是整个页面进行刷新，而是可以使用js技术完成页面的局部刷新

3. ajax的应用场景
  * 用户是否被注册
  * 百度搜索栏

4. ajax的优缺点
  优点：
  * 异步请求：增强了用户的体验
  * 局部刷新：服务器响应的内容不在是整个页面，而是局部的数据，这样减少的服务器的压力！
  缺点：
  * 并不是所有的情景都可以使用ajax，也就是说大多数时候还要使用同步交互！
  * ajax增强了用户的体验，但无形中增强了对服务器的访问次数，所以也增大了服务器的压力！

----------------

AJAX的第一例

1. 四步操作
  * 创建异步对象
    > 其实学习ajax就是在学习这么一个对象，学会这个对象，就掌握了ajax技术
    > 大多数浏览器都支持如下方式创建：ie7以后版本，firefox，chrome等：var xmlHttp = new XMLHttpRequest();
    > IE5.5以及之前版本的IE有自己的方式：var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    > IE6.0也有一种自己的方式：var xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    > 编写一个得到xmlHttp异步对象的方法
    function createXMLHttpRequest() {
      try {
        return new XMLHttpRequest();//支持IE7.0之后版本的IE，其他大多数浏览器
      } catch(e) {
        try {
	  return new ActiveXObject("Msxml2.XMLHTTP");//支持ie6.0
	} catch(e) {
	  try {
	    return new ActiveXObject("Microsoft.XMLHTTP");//支持IE5.5以及更早版本
	  } catch(e) {
	    alert("哥们儿，你用的是什么浏览器啊？");
	    throw e;
	  }
	}
      }
    }
  * 打开与服务器的连接
    > xmlHttp.open("GET", "/day23_1/AServlet", true);
      * GET：指定请求方式
      * /day23_1/AServlet：请求的URL
      * true：指定使用异步处理！！！
  * 发送请求
    > xmlHttp.send(null)：如果不给null参数，可能会异步部份浏览器无法发送请求
  * 注册监听
    > 
    xmlHttp.onreadystatechange = function() {
        if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	    var text = xmlHttp.responseText;
	}
    };

==========================================
ajax

1. 什么是ajax
  asynchronous javascript and xml
  异步的js和xml技术

  * 可以使用js请求servlet
  * js本身的能力，局部刷新

2. 异步交互和同步交互
  * 异步交互：发送请求后，不需等待服务器响应，即可发下一个请求。
  * 同步交互：发送请求后，需要等待服务器响应后，才能发下一个请求。

3. ajax的优点和缺点
  * 优点
    > 增强了用户的体验：发异步请求，局部刷新
    > 局部刷新：不需要服务器响应整个页面，只需要是局部的数据即可，减少了对服务器的压力
  * 缺点
    > js本身的问题，浏览器兼容性问题
    > 不能适用于所有的场景，很多时候还是需要使用传统的同步交互！
    > 使请求增多，给服务器增强了压力！

==============================================================

ajax四步

1. 得到请求对象
  * ajax的核心只是一个对象而已，只要学会了这个对象的使用，那么就会了ajax！
  * 大多数浏览器：new XMLHttpRequest()
  * IE6.0：new ActiveXObject("Msxml2.XMLHTTP");
  * IE5.5及更早版本：new ActiveXObject("Microsoft.XMLHTTP");
  * 通常编写一个函数来完成对请求对象的创建

  function createXMLHttpRequest() {
      try {
          return new XMLHttpRequest();
      } catch(e) {
          try {
	      return new ActiveXObject("Msxml2.XMLHTTP");
	  } catch(e) {
	      try {
	          return new ActiveXObject("Microsoft.XMLHTTP");
	      } catch(e) {
	          alert("哥，你用的是什么浏览器啊？");
	      }
	  }
      }
  }
    


2. 打开与服务器连接
  * 打开与服务器的连接，需要调用请求对象的open()方法
  * open(method, url, isAsync)
    > method：请求方式，例如：GET或POST
    > url：请求的服务器地址，例如：/day23_1/AServlet
    > isAsync：是否为异步请求，为true表示异步；否则为同步

  var xmlHttp = createXMLHttpRequest();//创建请求对象
  xmlHttp.open("GET", "/day23_1/AServlet", true);

3. 发送请求
  * 向服务器发送请求，需要使用请求对象的send()
  * send(请求体内容)：
    > GET请求，没有请求体，需要传递null！部分浏览器在不传递参数时会出现问题！
    > POST请求，有请求体，例如：send("username=zhangSan&password=123");

  xmlHttp.send(null)


4. 接收服务器响应（难点）
  * 介绍xmlHttp对象的属性和方法
    > open()：打开连接
    > send()：发送请求
    > onreadystatechange：状态改变监听器
    > readyState：当前状态
    > status：服务器响应的状态码
    > responseText：服务器响应的文本内容
  * 接收服务器响应
    > 注册一个监听器在onreadystatechange上面！
      * 双重判断：readyState为4，status为200
        > responseText得到响应文本

  xmlHttp.onreadystatechange = function() {
      if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
          var text = xmlHttp.responseText;
      }
  };

==============================================================

Hello Ajax!!!

1. 服务器端
  * AServlet，响应文本为Hello AJAX!!!

2. 编写一个jsp页面，向服务器发送请求

==============================================================

发送POST请求

1. 请求头：Content-Type
  * Content-Type=application/x-www-form-urlencoded
    > xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  * send(请求体)


==============================================================

第三例（注册表单校验：用户名是否被注册过）

1. 页面
  * regist.jsp
    > 用户名输入框
      * 失去焦点时，向服务器发送post请求，传递当前表单项的内容给服务器
      * 得到服务器的响应，如果响应的是true，那么在表单项后面给出错误信息
  * Ajax3Servlet
    > 获取用户名，判断用户名是否被注册
      * 如果已注册，响应true
      * 如果没有注册，响应false


=======================================

请求对象的状态

0：初始化未完成状态，只是创建了XMLHttpRequest对象，还未调用open()方法；
1：请求已开始，open()方法已调用，但还没调用send()方法；
2：请求发送完成状态，send()方法已调用；
3：开始读取服务器响应；
4：读取服务器响应结束。

xmlHttp.readyState == 4

=======================================

响应内容可以是xml
  * 服务器要给客户端响应复杂的数据时，需要使用xml格式！

1. 服务器发送xml
  * response.setContentType("text/xml;charset=utf-8");
2. 客户端接收xml
  * var doc = xmlHttp.responseXML;//得到的是文档对象

=======================================

省市联动

1. 页面
  * 两个下拉列表
    > 省
    > 市
  * ajax代码
    > 在打开页面时（window.onload），向服务器请求所有的省名称，显示在省下拉列表中
    > 当用户选择了某个省时，向服务器请求该省下的所有市，显示到市下拉列表中

2. Servlet
  * ProvinceServlet：查询所有省
    > 加载xml文件，把所有的省名称使用逗号连接成一个字符串，发送给客户端
  * CityServlet：通过省名称查询该省下的所有市
    > 获取省名称，从xml中查询该省对应的元素，把元素转换成xml字符串发送到客户端

=======================================
=======================================

XStream

1. 可以把javabean，序列化成xml
  * 数据从数据库中查询出现，通常都封装到一组javabean中！
  * 我们有时需要把javabean传递给js，这就需要把javabean转换成xml格式。

2. XStream入门代码
  * jar包：
    > xstream-1.4.7.jar
    > xpp3_min-1.1.4c
  * 创建XStream对象：XStream xsteram = new XStream();
  * 把对象转换成xml：String xml = xstream.toXML(对象);

<list> --> 集合的种类
  <cn.icore.domain.Province> --> 类名
    <name>北京</name> --> Province类的name属性
    <cities> --> Province类的cities属性
      <cn.icore.domain.City> --> cities是一个集合，它的元素类型
        <name>东城区</name> --> City类的name属性
        <description>东城区</description> --> City类的description属性
      </cn.icore.domain.City>
      <cn.icore.domain.City>
        <name>海淀区</name>
        <description>海淀区</description>
      </cn.icore.domain.City>
    </cities>
  </cn.icore.domain.Province>


  <cn.icore.domain.Province>
    <name>辽宁</name>
    <cities>
      <cn.icore.domain.City>
        <name>沈阳</name>
        <description>沈阳</description>
      </cn.icore.domain.City>
      <cn.icore.domain.City>
        <name>葫芦岛</name>
        <description>葫芦岛</description>
      </cn.icore.domain.City>
    </cities>
  </cn.icore.domain.Province>
</list>


-----------------------

XSteram的细节
  * alias(String name, Class c)：设置c类型的生成的xml元素的名称为name
  * useAttributeFor(Class c, String name)：让c类型中的名为name属性，生成xml元素的属性，而不是xml元素！
  * addImplicitCollection(Class c, String name)：去除c类型中名为name的集合属性，让它不生成对应元素，但集合内的元素还要保留
  * omitField(Class c, String name)：去除c类型中名为name的属性，让它不生成xml！


1. 别名


<china>
  <proinvce>
    <name>北京</name>
    <cities>
      <city>
        <name>东城区</name>
        <description>东城区</description>
      </city>
      <city>
        <name>海淀区</name>
        <description>海淀区</description>
      </city>
    </cities>
  </proinvce>
  <proinvce>
    <name>辽宁</name>
    <cities>
      <city>
        <name>沈阳</name>
        <description>沈阳</description>
      </city>
      <city>
        <name>葫芦岛</name>
        <description>葫芦岛</description>
      </city>
    </cities>
  </proinvce>
</china>

2. 默认生成的是xml元素，若希望生成元素属性也可以！
  * 默认javabean的属性，生成的是xml的元素
  * 我们希望javabean的属性，生成的是xml元素的属性

<china>
  <proinvce name="北京">
    <cities>
      <city>
        <name>东城区</name>
        <description>东城区</description>
      </city>
      <city>
        <name>海淀区</name>
        <description>海淀区</description>
      </city>
    </cities>
  </proinvce>
  <proinvce name="辽宁">
    <cities>
      <city>
        <name>沈阳</name>
        <description>沈阳</description>
      </city>
      <city>
        <name>葫芦岛</name>
        <description>葫芦岛</description>
      </city>
    </cities>
  </proinvce>
</china>


3. 去除集合属性，但保留集合内的元素
  * javabean中的集合类型的属性，我们不需要，想把它去除！

<china>
  <proinvce name="北京">
    <city>
      <name>东城区</name>
      <description>东城区</description>
    </city>
    <city>
      <name>海淀区</name>
      <description>海淀区</description>
    </city>
  </proinvce>
  <proinvce name="辽宁">
    <city>
      <name>沈阳</name>
      <description>沈阳</description>
    </city>
    <city>
      <name>葫芦岛</name>
      <description>葫芦岛</description>
    </city>
  </proinvce>
</china>



4. 去除javabean属性，让属性不生成xml元素！

<china>
  <proinvce name="北京">
    <city>
      <name>东城区</name>
    </city>
    <city>
      <name>海淀区</name>
    </city>
  </proinvce>
  <proinvce name="辽宁">
    <city>
      <name>沈阳</name>
    </city>
    <city>
      <name>葫芦岛</name>
    </city>
  </proinvce>
</china>


<china>
  <proinvce name="北京">
    <city name="东城区"/>
    <city name="海淀区"/>
  </proinvce>
  <proinvce name="辽宁">
    <city name="沈阳"/>
    <city name="葫芦岛"/>
  </proinvce>
</china>

========================================

JSON

1. 什么是JSON
  * js对象格式
  * json就是一种js自有的一种数据转换格式！可以用来替代xml

2. json与xml比较
  * 流行度：xml是老东西，它更流行！在ajax范围内，json是主场它更受欢迎！
  * 可读性：xml强一些！！
  * 解析难度：json不需要解析，执行即可用！（主场）

3. JSON语法
  * 对象：大括号（{...}）
    > 属性：以key:value格式来书写
    > 属性名：必须使用双引！
  * 数组：方括号（[...]）

  var str = "{\"name\":\"zhangSan\", \"age\":18, \"getAge\":function() {return 18;} }";
  var person = eval("(" + str + ")");

  var str1 = "[{\"name\":\"zhangSan\"}, {\"name\":\"liSi\"}]";

4. 服务器端有了选择
  * 可以给客户端传递xml
    > 把javabean转换成xml：XStream
  * 可以给客户端传递json串
    > 把javabean转换成json串：json-lib


==========================================

json-lib小工具

1. json-lib
  * 把javabean转换成json串

2. 导jar包
  * json-lib的核心jar包有：　
    > json-lib.jar
  * json-lib的依赖jar包有：
    > commons-lang.jar
    > commons-beanutils.jar
    > commons-logging.jar
    > commons-collections.jar
    > ezmorph.jar

3. 核心类
  * JSONObject
    > 实现了Map接口
  * JSONArray
    > 实现了List接口

=============================================

把ajax四步进行封装

1. 目标是编写一个名为ajax()函数
  * 在四步中提取出参数！
    > method：请求方式，默认为GET
    > url：请求的地址，没有！
    > async：是否异步，默认为true
    > params：请求体内容，默认为null
    > resultType：text、xml、json，默认为text
    > callback：函数

function ajax(options /*对象类型的参数*/) {
    if(!options.method) {
        options.method = "GET";
    }
    if() ... 

    四步！
}

==========================================

第一例；

1. 编写一个Servlet，响应Hello Ajax!!!
2. 编写页面，给出一个按钮，点击后执行ajax四步，把服务器响应结果显示在<h1>的元素中！

---------------

第二例：

1. 编写一个Servlet，响应Hello Ajax!!!
2. 编写页面，给出一个按钮，点击后执行ajax四步，把服务器响应结果显示在<h1>的元素中！
  * 在ajax四步中的第二步，打开连接时，需要指定请求方式为POST：xmlHttp.open("POST", "/day23_1/BServlet", true)
  * 添加一步：因为POST请求的Content-Type请求头的值必须为application/x-www-form-urlencoded
    > xmlHttp.setRequestHeader("Content-Type", "application/x-www.form-urlencoded");
  * 第四步在发送请求时，可以指定请求体内容：xmlHttp.send("username=zhangSan&password=123");

---------------

第三例：用户名是否注册

1. 给出一个注册表单
  >  给用户名文本框添加一个失去焦点的事件：
    * 在这时发送一个请求，把用户名发送给服务器
    * 服务器查询数据库得到结果，如果这个用户名存在，返回true；否则返回false
  > 得到这个结果后，决定是否在用户名文本框后显示“用户名已被注册！”

---------------

服务器响应xml数据

1. 服务器端：
  * 需要设置响应头：response.setContentType("text/xml;charset=utf-8");
  * 响应时其实响应的就是xml的字符串：response.getWriter().print("<students><student number='1001' name='zahngSan'/></students>");
2. 客户端：
  * 在获取服务器响应内容时：var doc = xmlHttp.responseXML;

==========================

XStream

1. 作用
  * XStream可以javabean对象转换成xml文档

2. XStream相关jar包
  * 核心jar：xstream-1.4.7.jar
  * 必须依赖jar；xpp3_min-1.1.4c.jar

3. XStream的使用
  * XStream xStream = new XStream();
  * String s = xStream.toXML(list);

4. alias(别名)
  * 生成xml中存在完整类名和对象名现象，可以使用alias指定类名来进行转换
  * xStream.alias("province", Province.class) --> 把Province类型的对象转换成名为province的元素
  * xStream.alias("city", City.class)
  * xStream.alias("china", List.class)

5. 使类的成员转换成元素属性
  * 默认类的成员转换成子元素，也可以把类的成员转换成元素属性
  * xStream.useAttributeFor(Pronvice.class, "name") --> 把Proinvce类的name成员转换成元素属性

6. 类的List成员不生成对应xml元素
  * 大多数情况下不希望类的List成员生成xml
  * xStream.addImplicitCollection(Province.class, "cities"); --> 让Province类的cities成员不生成元素属性

7. 类的成员不生成元素
  * 某些类的成员不希望生成xml元素
  * xStream.omitField(City.class, "description") --> 让City类的description成员不生成属性


======================================

JSON

1. 什么是JSON
  * JSON是一种用字符串表示js对象的方式，它是一个轻量级的数据交换格式。
  * 它与XML很相似！在AJAX中，我们在服务器端使用Java给客户端JS传递XML数据（字符串），这需要把先把数据转换成XML，再发送给JS，JS再解析XML
  * 也可以让服务器端把数据转换成JSON串，发送给JS，JS解析JSON！！！

2.  JSON语法
  * {} --> 指定对象
    > 属性名:属性值，其中属性名必须使用双引括起来。多个属性之间使用逗号分隔；
    > {"name":"zhangSan", "age":18, "gender":"male"}
  * [] --> 指定数组
    > 多个元素之间使用逗号分隔；
    > ["zhangSan", "liSi", "wangWu"]

3. JSON与XML比较
  * 可读性：xml胜出
  * 解析难度：JSON本身就是JS的一部份，无需解析，所以胜出
  * 流行度：XML已流行多年，所以XML胜出，但在AJAX领域，JSON更受欢迎！

4. JS执行字符串
  * eval()：把字符串当成JS代码来执行，但要把字符串使用小括号括起来！
  * var sum = eval("(1+2)");//结果为3
  * var person = eval("({\"name\":\"zhangSan\", \"age\":18})");

-----------------------

json-lib

1. json-lib的作用
  * 把Java对象转换成json串

2. 核心类
  * JSONObject
    > Map的子类
    > 使用Map一样添加键值，然后toString()就可以得到JSON串
    > JSONObject.fromObject(javabean).toString()：把javabean对象转换成JSONObject对象，再toString()得到JSON串
  * JSONArray
    > List的子类
    > 可以像使用List一样把javabean添加到JSONArray对象中，然后toString()得到JSON串；
    > JSONArray.fromObject(数组).toString()：把数组转换成JSONArray对象，再toString()得到JSON串

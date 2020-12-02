1. EL函数库
  * apache的JSTL标签库中的一部分
  * EL函数库还是JSTL的，所以需要导标签库！

2. 使用EL函数库，需要导标签库（与import相似！）
  * <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

3. 使用EL函数库
  * ${fn:toUpperCase("hello") }

=============================================

*****MyEclipse默认会给每个javaweb项目提供三个jar包，其中一个是jstl标签库的核心jar包！！！

自定义函数库

1. 自定义一个类
  > 所有的方法都必须为static，并且有返回值

2. 编写tld文件
  > 去查看其他函数库的tld文件编写！
  > 每个javaweb项目，都有jstl.jar这个文件，它在WEB-INF/lib下
  > 打开这个jar包，找到META-INF/fn.tld文件，我们对照着它来写我们自己的tld文件
  <function>
    <name>hello</name>
    <function-class>cn.icore.functions.MyFunctions</function-class>
    <function-signature>String hello()</function-signature>
  </function>


3. 在jsp页面中使用<%@ taglib %>来指向tld文件！

==========================================
==========================================
JSTL标签库

1. JSTL标签库是由apache提供的！
2. 使用JSTL标签库
  * 导包：把jstl-1.2.jar导入到你的/WEB-INF/lib中！（如果是使用MyEcipse开发它会自动帮你完成这一步！）
  * 在JSP文件中使用<%@ taglib%>指令来导标签库
    > core --> 核心库：<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    > fmt --> 格式化库：<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      * 只学习两个标签

3. 学习core库的标签
  * out、set、remove
    > out：<c:out value="hello"/>
      * <c:out value="${requestScope.xxx }" default="xxx不存在"/>：要输出的东西不存在时，输出default的内容！
      * <c:out>标签自动会对于输出内容进行转义！！！使用escapeXml="false"，可以使该标签不转义！
    > set：<c:set var="" value="" scope=""/>，用来创建变量，存放到指定的域中
      * var：指定属性名
      * value：指定属性值
      * scope：指定域，可选值：page、request、session、application
    > remove：<c:remove var="" scope=""/>，删除指定域的属性
      * var：指定属性名
      * scope：指定域！（如果没有指定域，默认删除所有域中的该变量）
  * url：给指定的url添加项目前缀，输出或保存到变量中！可以添加参数（自动url编码）
    > value：指定url！
    > var：指定变量名，若指定变量名，那么就不会再输出，而是把结果保存到变量中
    > 添加子标签：<c:param name="参数名" value="参数值"/>，其中参数值如果是中文，会自动url编码
  
  -------------------------------------------
   
  * if：与java中的if语句相似
    > <c:if test="boolean值">...</c:if>，如果test为true，那么执行标签体内容！

  * choose：与java中的if/else或是if/else if.../else
    > 两个子标签：
      * <c:when test="boolean类型">...</c:when/>
      * <c:otherwise>....</c:otherwise>
    > 如果满足某个when的条件，那么就执行这个when的内容，不在向下执行下一个when，及otherwise
    > 如果没有东路某个when那么，执行下一个when或otherwise

  * forEach：与java中的for循环相似
    > 计数形式（老for）：for(int i = 0; i < 10; i++) {...}
      * var：指定变量名
      * begin：指定开始值
      * end：指定结束值
      * step：指定步长
    > 增强形式（新for）：for(Object obj : 数组或集合) {...}
      * items：指定被遍历的集合、数组...
      * var：每个元素！
    > 循环状态变量：定义循环状态变量，从中可以获取与循环状态相关的信息
      * varStatus：<c:forEach var="name" items="${names}" varStatus="vs">
      * ${vs.count}：int类型，表示当前循环的圈数：1  2
      * ${vs.index}：int类型，表示当前元素的下标：0  1
      * ${vs.first}：boolean类型，表示当前循环是否为第一圈
      * ${vs.last}：boolean类型，表示当前循环是否为最后一圈
      * ${vs.current}：Object类型，表示当前遍历的元素本身

4. 学习fmt库的标签
  * formatDate：<fmt:formatDate value="要格式的日期对象" pattern="格式化模式"/>
    * value：必须是一个日期类型的对象
    * pattern：yyyy-MM-dd HH:mm:ss
  * formatNumber：<fmt:formatNumber value="要格式的数值" pattern="格式化模式"/>
    * pattern：
      > 0.00：保留小数点后两位，四舍五入！如果不足两位，补零！
      > #.##：保留小数点后两位，四舍五入！如果不足两位，不补零！

==========================================
自定义标签

1. 自定义标签步骤
  * 写一个标签处理类
  * 写tld文件
  * 在jsp页面中使用标签，需要先使用<%@ tablib %>导入标签库(指向tld文件所在位置)

  * 标签处理类
    > JspTag：祖先
      * Tag
      * SimpleTag(*)
        > void doTag()：最为重要！每次执行标签时，其实就是执行这个方法！
	  * tomcat调用！它会在所有的set方法之后被调用
	> getParent()：返回父标签！
	  * 我们自己调用，如果自己也不调用，那么它就没用
	> setParent(JspTag)：设置父标签！
	  * tomcat调用，用来给标签处理类传递父标签！
	> setJspContext(JspContext)：设置页面上下文对象！PageContext
	  * tomcat调用，用来给标签处理类传递页面上下文对象！
	> setJspBody(JspFragment)：设置标签体内容
	  * tomcat调用，用来把标签体对象传递给当前标签处理类！
      * SimpleTagSupport
        > 只需要覆盖父类的doTag()方法！

2. 标签第一例（Hello World!）
  * 编写标签处理类
public class MyTag1 implements SimpleTag {
	private JspFragment body;
	private JspContext pageContext;
	private JspTag parent;
	
	/**
	 * 每次执行到页面的标签时，都会调用本方法，它用来处理请求！
	 */
	public void doTag() throws JspException, IOException {
		/*
		 * 它会在所有set方法之后执行！
		 * 在这个方法内部可以使用：标签体对象、父标签对象、页面上下文对象
		 */
		
		/*
		 * 获取当前页面的输出流，输出hello world
		 */
		pageContext.getOut().println("Hello World!");
	}

	/**
	 * 它不由tomcat调用！
	 */
	public JspTag getParent() {
		return null;
	}

	/**
	 * tomcat会在doTag()方法被调用之前，调用本方法
	 * 把标签体内容传递给本类
	 */
	public void setJspBody(JspFragment body) {
		this.body = body;
	}

	/**
	 * tomcat会在doTag()方法被调用之前，调用本方法
	 * 把页面上下文对象传递给本类
	 */
	public void setJspContext(JspContext pageContext) {
		this.pageContext = pageContext;
	}

	/**
	 * tomcat会在doTag()方法被调用之前，调用本方法
	 * 把父标签传递过来
	 */
	public void setParent(JspTag parent) {
		this.parent = parent;
	}
}

  * tld文件：去借c标签的tld文件！jstl-1.2.jar/META-INF/c.tld
  <tag>
    <name>hello1</name> <!--指定标签名称-->
    <tag-class>cn.icore.tags.MyTag1</tag-class> <!--标签处理类-->
    <body-content>empty</body-content> <!--标签体内容样式-->
  </tag>

  * 标签体内容样式：
    > (*****)empty：无标签体
    > JSP：jsp脚本、el、文本为标签体内容（JSP2.0就不能再使用这个东东了）
    > (*****)scriptless：el、文本为标签体内容，如果你的标签有标签体，那么JSP2.0之后建议大使用该样式（不让使用jsp脚本了）
    > tagdependent：对于标签体不进行执行，直接把标签体当作字符串来传递！（没有人会使用这种样式）
  * 在jsp页面中使用标签
    > 使用<%@ taglib %>导入标签库，指定tld文件的位置

-------------------------

SimpleTagSupport

 * SimpleTagSupport类
 *   * 它把三个set方法的传参已经完成了！
 *   * 它还提供了对应的get方法，子类中可以通过get来获取这三个属性
 *     > 上下文对象
 *     > 父标签
 *     > 标签体
 *     	super.getJspBody();
	super.getJspContext();
	super.getParent();

-------------------------

第三例（有标签体的标签）

  * 需要得到标签体，然后执行之！
    > 先获取标签体对象：JspFragement body = super.getJspBody();
    > 调用invoke()方法，传递输出流：body.invoke(null)
    　* 如果传递的是null，默认为向页面输出！
  * tld
  <tag>
    <name>hello3</name>
    <tag-class>cn.icore.tags.MyTag3</tag-class>
    <body-content>scriptless</body-content> <!--有标签体的标签-->
  </tag>

---------------------------

第四例

  * SkipPageException：在处理类的doTag()方法中抛出这个异常，使页面下面的内容不在执行！

---------------------------

第五例（带属性的标签）
  * 标签处理类必须提供javabean属性

  * tld中配置
    > 需要在<tag>中添加子元素<attribute>
    <attribute>
    	<name>test</name><!--指定属性名称-->
    	<required>true</required><!--指定属性是否为必需的-->
    	<rtexprvalue>true</rtexprvalue><!--指定属性值是否可以为EL-->
    </attribute>

==========================================
JSTL

1. jstl的概述
  * apache的东西，依赖EL
  * 使用jstl需要导入jstl1.2.jar
  * 四大库：
    > core：核心库，重点
    > fmt：格式化：日期、数字
    > sql：过时
    > xml：过时

2. 导入标签库
  * jar包
  * 在jsp页面中：<%@taglib prefix="前缀" uri="路径"%>

----------------------

core --> c标签！

1. out和set
  * <c:out>：输出
    > value：可以是字符串常量，也可以是EL表达式
    > default：当要输出的内容为null时，会输出default指定的值
    > escapeXml：默认值为true，表示转义！
  * <c:set>：设置(创建域的属性)
    > var：变量名
    > value：变量值，可以是EL表达式
    > scope：域，默认为page，可选值：page、request、session、application
2. remove
  * <remove>：删除域变量
    > var：变量名
    > scope：如果不给出scope，表示删除所有域中的该名称的变量；如果指定了域，那么只删除该域的变量。
3. url
  * value：指定一个路径！它会在路径前面自动添加项目名。
    <> <c:url value="/index.jsp"/>，它会输出/day13_1/index.jsp
  * 子标签：<c:param>，用来给url后面添加参数，例如：
    <c:url value="/index.jsp">
      <c:param name="username" value="张三"/>  <!--可以对参数进行url编码！！-->
    </c:url>
    结果为：/day13_1/index.jsp?username=%ED%2C%3F%ED%2C%3F
  * var：指定变量名，一旦添加了这个属性，那么url标签就不会再输出到页面，而是把生成url保存到域中。
  * scope：它与var一起使用，用来保存url。
4. if：对应java中的if语句
  * <c:if test="布尔类型">...</c:if>，当test为值时，执行标签体内容！
5. choose：它对应java中的if/else if/ ... /else
  * 例如：
    <c:choose>
      <c:when test="">...</c:when>
      <c:when test="">...</c:when>
      <c:when test="">...</c:when>
       ... 
      <c:otherwise> ...</c:otherwise>
    </c:choose>
    等同与
    if(...) {
    } else if( ....) {
    } else if( ....) {
    } else if( ....) {
    } ...
    else { ...}

6. forEach
  它用来循环遍历数组、集合！
  它还可以用来计数方式来循环！

  计数方式：

  for(int i = 1; i <= 10; i++) {
    ...
  }

  <c:forEach var="i" begin="1" end="10">
    ${i}
  </c:forEach>

  属性：
    * var：循环变量
    * begin：设置循环变量从几开始。
    * end：设置循环变量到几结束。
    * step：设置步长！等同与java中的i++，或i+=2。step默认为1

----------------------

用来输出数组、集合！

<c:forEach items="${strs }" var="str">
 ${str }<br/>
</c:forEach>

等同于

for(String str : strs) {
  ...
}

属性：
* items：指定要循环谁，它可以是一个数组或一个集合
* var：把数组或集合中的每个元素赋值给var指定的变量。

----------------------

循环状态

可以使用varStatus来创建循环状态变量！

循环状态变量有如下属性：
  * count：循环元素的个数
  * index：循环元素的下标
  * first：是否为第一个元素
  * last：是否为最后一个元素
  * current：当前元素

<c:forEach items="${list }" var="ele" varStatus="vs">
	${vs.index} ${vs.count } ${vs.first } ${vs.last } ${vs.current }<br/>
</c:forEach>

====================================

fmt库
  它是格式化库

<fmt:formatDate value="" pattern="">

value：指定一个Date类型的变量
pattern：用来指定输出的模板！例如：yyyy-MM-dd HH:mm:ss

--------------

<fmt:formatNumber value="${num1}" pattern="0.00">
  保留小数点后2位，它会四舍五入！如果不足2位，以0补位！

<fmt:formatNumber value="${num1}" pattern="#.##">
  保留小数点后2位，它会四舍五入！如果不足2位，不补位！

====================================

自定义标签

1. 步骤
  * 标签处理类（标签也是一个对象，那么就需要先有类！）
  * tld文件，它是一个xml
  * 页面中使用<%@taglib%>来指定tld文件的位置

2. 标签处理类
  SimpleTag接口：
  * void doTag()：每次执行标签时都会调用这个方法；
  * JspTag getParent()：返回父标签（非生命周期方法）
  * void setParent(JspTag)：设置父标签
  * void setJspBody(JspFragment)：设置标签体
  * void seetJspContext(JspContext)：设置jsp上下文对象，它儿子是PageContext

　　其中doTag()会在其他三个方法之后被tomcat调用。

3. 配置tld文件

tld文件一般都放到WEB-INF之下，这样保证客户端访问不到！
  <tag>
  	<name>myTag1</name> 指定当前标签的名称
  	<tag-class>cn.icore.tag.MyTag1</tag-class> 指定当前标签的标签处理类！
  	<body-content>empty</body-content> 指定标签体的类型，我们这里使用的是空标签！
  </tag>

4. 页面中指定tld文件位置

<%@ taglib prefix="it" uri="/WEB-INF/tlds/icore-tag.tld" %>
导标签库，就是为页面指定tld文件的位置！

-------------------------------

进阶

标签体内容
  * empty：无标签体！
  * JSP：jsp2.0已经不在支持这个类型了！表示标签体内容可以是：java脚本，可以是标签，可以是el表达式
  * scriptless：只能是EL表达式，也可以是其他的标签！
  * tagdependent：标签体内容不会被执行，而是直接赋值标签处理类！

不在执行标签下面内容的标签！

在标签处理类中的doTag()中使用SkipPageException来结束！
　　Tomcat会调用标签处理类的doTag()方法，然后Tomcat会得到SkipPageException，它会跳过本页面其他内容！

---------------

标签属性

步骤：
1. 给你的标签处理类添加属性！
　　为标签处理类添加属性，属性至少要且一个set方法！这个set方法会在doTag()方法之前被tomcat执行！所在doTag()中就可以使用属性了。

2. 在tld文件中对属性进行配置

  	<attribute>
  		<name>test</name> 指定属性名
  		<required>true</required> 指定属性是否为必需的
  		<rtexprvalue>true</rtexprvalue> 指定属性是否可以使用EL
  	</attribute>

==========================================

MVC
  它不是java独有，所有的B/S结构的项目都在使用它！

 M -- model 模型(自己写代码)
 V -- View  视图(jsp)
 C -- Cotroller 控制器(Servlet)

==========================================

JavaWeb三层框架

Web层 --> 与Web相关的内容(Servlet，JSP，Servlet相关API：request、response、session、ServletContext)
业务层 --> 业务对象(Service)
数据层 --> 操作数据库(DAO Data Access Object)(所有对数据库的操作，不能跳出到DAO之外)

==========================================

JSTL

Apache提供的标签库，
jar包：jstl-1.2.jar，如果傅MyEclipse，它会在我们导入jar包，无需自己导入，如果没有使用MyEclipse那么需要自行导入。

------------------

导入JSTL核心标签库
<%taglib prefix="c" uri="http://java.sun.com/jstl/core"%>


<c:set> 
* <c:set var="a" value="hello"/> 创建名为a,值为hello的域属性，范围：page
* <c:set var="a" value="hello" scope="session"/> 范围为session

<c:out>
* <c:out value="aaa"/> 输出字符串aaa
* <c:out value="${aaa"/> 输出域属性aaa，其中与${aaa}相同
* <c:out value="${aaa}" default="xxx"/>如果${aaa}不存在，那么输出xxx字符串
* <c:out value="${aaa}" escapeXml="true"/>如果${aaa}中包含特殊字符，那么转义它。这可以防止javascript攻击

<c:remove>
* <c:remove var="a"/> 删除名为a的域属性
* <c:remove var="a" scope="page"/> 删除page域中名为a的域属性

<c:url>
* <c:url value="/AServlet"/> 输出URL：/项目名/AServlet
* <c:url value="/AServlet" var="url" scope="page"/> 把生成的url保存到page域中，而不会输出
* <c:url value="/AServlet">：输出URL：/项目名/AServlet?username=%xx%xx%xx%xx%xx%xx，其中张三会被URL编码
   <c:param name="username" value="张三"/>
  </c:url/>

<c:if>
* <c:if test="${条件}"> 当条件为true时执行标签体内容
    hello
  </c:if>

<c:choose>
* <c:choose>
    <c:when test="${条件1}">a</c:when>
    <c:when test="${条件2}">b</c:when>
    <c:when test="${条件3}">c</c:when>
    <c:otherwise>d</c:otherwise>
  </c:choose>

  等同与：
  if() {
  } esle if() {
  } esle if() {
  } else if() {
  } else {
  }

-------------

<c:forEach>

可以用来遍历数组、List、Map、

1. 计数循环

<c:forEach begin="1" end="10" var="i">
 ${i}
</c:forEach>
等同于
for(int i = 1; i <= 10; i++) {
  out.println(i);
}


<c:forEach begin="1" end="10" var="i" step="2">
 ${i}
</c:forEach>
等同于
for(int i = 1; i <= 10; i+=2) {
  out.println(i);
}

-------------

2. 遍历数组

<%
String[] names = {"zhangSan", "liSi", "wangWu", "zhaoLiu"};
pageContext.setAttribute("ns", names);
%>
<c:forEach var="item " items="${ns } ">
	<c:out value="name: ${item } "/><br/>
</c:forEach>


-------------

3. 遍历List

<%
	List<String> names = new ArrayList<String>();
	names.add("zhangSan");
	names.add("liSi");
	names.add("wangWu");
	names.add("zhaoLiu");
	pageContext.setAttribute("ns", names);
%>
<c:forEach var="item" items="${ns }"> 
	<c:out value="name: ${item }"/><br/>
</c:forEach>

-------------

4. 遍历Map

<%
	Map<String,String> stu = new LinkedHashMap<String,String>();
	stu.put("number", "N_1001");
	stu.put("name", "zhangSan");
	stu.put("age", "23");
	stu.put("sex", "male");
	pageContext.setAttribute("stu", stu);
%>
<c:forEach var="item " items="${stu }">
	<c:out value="${item.key }: ${item.value } "/><br/>
</c:forEach>


-------------

5. 循环状态对象

循环状态对象是用来说明循环的状态的，属性如下：
count：int类型，当前以遍历元素的个数；
index：int类型，当前元素的下标；
first：boolean类型，是否为第一个元素；
last：boolean类型，是否为最后一个元素；
current：Object类型，表示当前项目。

<c:forEach var="item" items="${ns }" varStatus="vs" >
	<c:if test="${vs.first } ">第一行：</c:if>
	<c:if test="${vs.last } ">最后一行：</c:if>
	<c:out value="第${vs.count } 行: "/>
	<c:out value="[${vs.index } ]: "/>
	<c:out value="name: ${vs.current } "/><br/>
</c:forEach>


------------------

导入JSTL格式化标签库

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Date date = new Date();
	pageContext.setAttribute("d", date);
%>
<fmt:formatDate value="${d }" pattern="yyyy-MM-dd HH:mm:ss "/>

---------

<%
	double d1 = 3.5;
	double d2 = 4.4; 
	pageContext.setAttribute("d1", d1);
	pageContext.setAttribute("d2", d2);
%>
<fmt:formatNumber value="${d1 }" pattern="0.00 "/><br/>
<fmt:formatNumber value="${d2 }" pattern="#.## "/>

* pattern：0.00，表示小数不足两位时，使用0补足两位
* pattern：#.##，表示小数不足两位时，有几位显示几位，不会补足


============================
============================
============================


自定义标签

自定义标签：

1. 实现Tag接口，即传统标签。不建议使用！
2. 实现SimpleTag接口，即简单标签。建议使用！

JavaWeb中还提供了SimpleTagSupport类，继承它要比实现SimpleTag接口方便。

-----------------

步骤：
1. 标签处理类：继承SimpleTagSupport类
public class HelloTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		// 获取JspContext对象，再获取out对象，再向页面输出
		// 获取到的JspContext其实就是当前页面的pageContext对象
		this.getJspContext().getOut().write("<p>Hello SimpleTag!</p>") ;
	}
}

2. 标签描述符文件(tld)

/WEB-INF/tlds/icore.tld

<?xml version="1.0" encoding="UTF-8"?>
<taglib version="2.0" xmlns="http://java.sun.com/xml/ns/j2ee"
	xmlns:xml="http://www.w3.org/XML/1998/namespace" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee 
						http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd ">

	<tlib-version>1.0</tlib-version> 
	<short-name>icore</short-name> 
	<uri>http://www.icore.cn/tags</uri> 
	<tag> 
		<name>hello</name> <!--标签名称-->
		<tag-class>cn.icore.tag.HelloTag</tag-class> <!--标签处理类名称-->
		<body-content>empty</body-content> <!--标签体为空，即空标签-->
	</tag>
</taglib>

3. jsp页面中使用自定义标签

<%@ taglib prefix="it"  uri="/WEB-INF/hello.tld"  %>
......
<it:hello/>

----------------------

有标签体的标签

1. 标签处理类
public class HelloTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		PageContext pc = (PageContext) this.getJspContext();
		HttpServletRequest req = (HttpServletRequest) pc.getRequest();
		String s = req.getParameter("exec");
		if(s != null && s.endsWith("true")) {
			// 获取标签体对象
			JspFragment body = this.getJspBody() ;
			// 执行标签体
			body.invoke (null);
		}

	}
}

2. tld

	<tag>
		<name>hello</name>
		<tag-class>cn.icore.tags.HelloTag</tag-class>
		<body-content>scriptless</body-content> <!--标签体内容不可以是java脚本，但可以是el、jstl等-->
	</tag>

----------------------

不执行标签下面的页面内容

	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().print("<h1>只能看到我！</h1>");
		throw new SkipPageException();
	}

----------------------

带有属性的标签

public class IfTag extends SimpleTagSupport {
	private boolean test;//设置属性，提供getter/setter方法
	public boolean isTest() {
		return test;
	}
	public void setTest (boolean test) {
		this.test = test;
	}
	@Override
	public void doTag() throws JspException, IOException {
		if(test) {//如果test为true，执行标签体内容
			this.getJspBody().invoke(null);
		} 
	}
}

	<tag> 
		<name>if</name> 
		<tag-class>cn.icore.tag.IfTag</tag-class> 
		<body-content>scriptless</body-content>
		<!--部署属性-->
		<attribute> 
			<name>test</name> <!--属性名-->
			<required>true</required> <!--属性是否为必须的-->
			<rtexprvalue>true</rtexprvalue> <!--属性值是否可以为EL或JSTL等-->
		</attribute> 
	</tag>

-------------------

MVC

1. 什么是MVC
　　MVC模式（Model-View-Controller）是软件工程中的一种软件架构模式，把软件系统分为三个基本部分：模型（Model）、视图（View）和控制器（Controller）。

* 控制器Controller：对请求进行处理，负责请求转发；
* 视图View：界面设计人员进行图形界面设计；
* 模型Model：程序编写程序应用的功能（实现算法等等）、数据库管理；

2. Java与MVC

JSP Model1第一代：JSP + DB
JSP Model1第二代：JSP + javabean + DB
JSP Model2：JSP + Servlet + JavaBean + DB

-------------------

JavaWeb三层框架

* Web层（表述层）：与Web相关的，例如jsp、servlet都是Web层
* Business层（业务逻辑层）：封装业务逻辑，通常对应一个业务功能，例如登录、注册都是一个业务功能。
* Data层（数据访问层）：封装对数据库的操作，通常对应一次对数据库的访问，例如添加、修改、删除、查询等。

=====================================
MVC

1. 是一种设计模式
2. 几乎所有的B/S结构的软件，都在使用MVC！
3. MVC简介
  M：模型
  V：视图
  C：控制器

4. JSP
  * model1：什么都是jsp
  * model2第一代
    1. jsp处理：视图的工作（直接与客户打交到）（1. 接收数据、2. 显示数据）（中转的工作，去找谁完成工作）
    2. javabean：它负责完成具体的业务！

  * model2第二代
    1. jsp处理（V：视图）：视图的工作（直接与客户打交到）（１.接收数据、２.显示数据）
    2. servlet（C：控制器）：中转工作！接收jsp的请求、调用javabean的方法完成具体业务、把结果传递给jsp显示
    3. javabean（M：模型）：处理具体的业务！由servlet来调用它！

  jsp --> 把所有工作都包了！
    * 接收用户的请求，显示数据给用户看
    * 数据中转
    * 封装业务逻辑
    * 访问数据库

--------------------------------------

三层框架

1. WEB、表述层（依赖service）
  * 与tomcat相关的都是web层
2. 业务、service层（依赖dao）
  * 封装具体的业务逻辑
  * 注册的业务
    > 校验用户名：使用用户名进行查询
    > 校验email：使用email进行查询
    > 保存注册数据：把数据保存到数据库
3. 持久、数据层、dao层
  * 封装了对数据库的访问，所有对数据库的访问都需要他帮忙！

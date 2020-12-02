1. 介绍Struts2的内容
* day01
  * 框架，以及什么是Struts2
  * Hello World程序
  * Struts2的基本配置
  * Action的样式
  * Action与Servlet API
  * Action与<result>
  * 登录练习
* day02
  * 参数的封装(今天讲)
  * 类型转发
  * 输入校验
  * 国际化
  * 拦截器（自定义拦截器）
* day03
  * 上传
  * 下载
  * OGNL表达式
  * Struts2中的OGNL表达式
  * Struts标签库
  * Struts2原理（源代码）
  * 防止表单重复提交
* day04和day05
  * 单表的CRUD（上传和下载）

======================================

1. 什么是框架
  * 软件 = 应用组件 + 业务组件
  * 业务组件：每个项目的业务是不同的
  * 应用组件：每个项目的应用组件部分都是相同的！（可重用）

2. Struts2框架
  * MVC框架，Web层框架（jsp、servlet）
  * Servlet：在Struts2中被替换成Action了
  * JSP：添加了s标签，即Struts2标签！

  * 常见的Web层框架
    > Struts1
    > Struts2
    > SpringMVC（占有率在提升！）

3. Struts2的核心
  * Struts2 = WebWork + Struts名字 + 杂七杂八的扩展
  * Struts2的核心是WebWork：Struts2就是WebWork的升级版
  * WebWork的核心：XWork

======================================

Struts2处理请求的流程

1. JavaWeb处理请求的流程
  * a.jsp页面请求AServlet
  * b.jsp页面请求BServlet


2. Struts处理请求的流程
  * a.jsp页面请求AAction
  * b.jsp页面请求BAction
  * 所有的请求都需要先通过一个过滤器StrutsPrepareAndExecuteFilter
    > 前端控制器或核心控制器，前端过滤器
    > 把所有请求共性的部分处理了，然后再根据struts.xml配置转发到不同的Action
    > Action处理每个请求个性部分

3. 学习Struts2需要学习的内容
  * 多出一个配置文件struts2.xml
  * StrutsPrepareAndExecuteFilter做了些什么
  * Action也是新东西！

=============================================
=============================================
=============================================

1. Struts2的安装包
  * apps：demo
  * docs：文档
  * lib：Struts2所需所有jar包
  * src；源代码

=============================================

Hello World程序

1. 创建Web项目
2. 核心步骤
  * 导jar包：把blank这个demo中的11个jar包copy到自己项目中
  * 给出配置文件：在src下创建struts.xml
  * 配置核心控制器：它是一个过滤器！！！　在web.xml中配置过滤器

  称呼：搭建Struts2环境

--------------------------------

程序流程：
  * 在页面给出一个超链接，发出请求！
  * 被请求的是Action，Action中输出一个字符串（Hello Struts2!）

1. 步骤
  * 给出页面，给出超链接
  * 给出一个Action，给出请求处理方法，输出同Hello Struts2
    > Action可以是一个POJO（POJO就是不继承也不实现的普通java类）
    > Action中必须要有请求处理方法：public String execute()：每次请求Action时都会调用该方法
  * 配置Action，给Action指定访问路径！struts.xml
    > 配置package：
      * name：唯一标识，即起个名称
      * namepsace：给出“/”
      * extends：struts-default
    > 配置Action：
      * name：唯一标识，即起个名字
      * class：Action的完整类名
  * 修改页面中的超链接，指向Action
    > <a href="/day01_1/hello1">...</a>：访问的路径与struts.xml中<action>的name相同！
  * 配置结果：
    > <result name="success">/show.jsp</result>
      * name：逻辑视图，也可以叫结果名
      * 元素内容：物理视图，也可以叫结果值
    > Action类中execute()方法的返回值必须与当前Action配置<action>中的<result>的name匹配


=============================================

Struts2总图

1. 请求流
  * 执行的是ActionProxy
  * ActionProxy = Action + Interceptor（增强）
  * 在执行ActionProxy时，使用ActionInvocation对象来完成传递数据

=============================================
=============================================
=============================================

Struts2的6个配置文件
  * default.properties：常量
    > 文件路径：WEB App Libraries --> struts-core-2.3.7.jar --> org.apache.struts2 --> default.propeties
    > struts.i18n.encoding=UTF-8：指定了post请求的编码！有了它以后post请求就无需再写request.setCharachterEncoding("utf-8");
    > struts.action.extension=action,,：指定action的请求后缀（扩展名），默认为两种后缀，一是action后缀，另一个是没有后缀
  * struts-default.xml
    > 文件路径：WEB App Libraries --> struts-core-2.3.7.jar --> struts-default.xml
    > <bean>元素：它用来配置struts内部结构，如果你不想修改Struts的结构，那么就不要动它！
    > <package name="struts-default" abstract="true">：
      * 这个包名称叫struts-default，我们自定义的<package>都要去继承这个包（把这个包中的内容继承了）
      * abstract="true"：这个包本身不能包含<action>元素！也就是说，它就是用来被继承的！
    > <result-types>：定义了很多结果类型
      * dispatcher：转发到jsp页面，默认值
      * chain：转发到另一个Action
      * redirect：重定向到.jsp
      * redirectAction：重定向到Action
      * stream：下载！
    > <interceptors>和<interceptor-stack>
      * 拦截器：一个名称和一个类名
      * 拦截器栈：一大堆拦截器的意思。一系列的拦截器的组合方式！
        > defaultStack：它是默认拦截器，你的Action都会先执行默认拦截器栈！
	> <default-interceptor-ref name="defaultStack"/>：用来指定默认拦截器栈！
  * struts-plugin.xml
    > 每个插件中都有这么一个配置文件！由插件的提供的！
  * struts.xml
    > 我们自己编写：放到src下！
    > 最为常用的配置文件！
  * struts.properties
    > 我们自己编写：放到src下！
    > 不是常用的！
    > 只能配置常量！！！
  * web.xml
    > 每个web项目都有！
    > 只能配置常量！！
    > 给filter添加<init-param>来完成常量的配置

  其中前三是只读不写，后三由我们自己来写！
  6个配置文件中可能出现相同的内容！加载顺序就是由上到下！后者覆盖前者。

==========================================

struts.xml中的配置

1. 配置常量
  * <constant name="struts.devMode" value="true" />
    > 其中name指定常量名
    > 其中value指定常量值

2. Action的访问路径
  * Action的访问路径由：<package>的namespace + <action>的name + 后缀来决定！！！
    > 例如：namespace="/"，<action>的name="hello1"，那么访问路径：/hello1.action
  
3. Action默认处理类
  * 当我们编写一个<action>可以不给出class属性，即：<action name="hello1"/>
  * class有一个默认值：com.opensymphony.xwork2.ActionSupport

4. 默认<action>
  * <default-action-ref name="hello2" />：指定默认action名称为hello2。当访问的action不存在时，执行hello2这个Action

5. 配置结果
  * 全局结果（可以由所有<action>使用）
  * 局部结果（只能由当前<action>使用）

  <package>
    <action ...>
      <result name="success" type="dispatcher">/xx.jsp</result>
    </action>
    <action ...>
      <result name="error" ...>
      <result name="input" ...>
    </action>
  </package>

  result元素的属性：
    * name：指定结果名称，它的默认值为success
    * type：指定结果类型，它的默认值为dispatch，表示转发到jsp
    * 元素内容：为结果值，表示要动向的物理路径！

  结果可以有全局和局部两种：
    * 局部结果：即配置在<action>元素中的
    * 全局结果：在<package>中配置
	<package name="day01_2" namespace="/" extends="struts-default">
		<global-results>
			<result name="xxx">/xxx.jsp<result>
		</global-results>
      > 全局结果可以被当前包下的所有Action使用！

==========================================

常量

1. 常用常量
  * struts.i18n.encoding=UTF-8：post请求的编码！你不用再写request.setCharachterEncoding("utf-8")
  * struts.action.extension=action：用来指定action的后缀。
    > Action的请求路径：<package>的namespace + <action>的name + 后缀
  * struts.serve.static.browserCache=false：浏览器是否要缓存
  * struts.devMode=true：开发者模式！在struts.xml被修改后，无需重启tomcat！包含了上面的浏览器缓存的常量！
  * struts.configuration.xml.reload：可以在struts.xml修改后无需启动中tomcat。

2. struts.xml
<struts>
  <constant name="struts.i18n.encoding" value="UTF-8"/>
</struts>

3. struts.properties
struts.i18n.encoding=UTF-8

==========================================
==========================================
==========================================

Action的三种方式

1. Action为POJO
  * 不继承不实现
  * 必须有public String execute()

2. 去实现Action接口
  * 方法：
    > public String execute() throws Exception
  * 字符串常量：这些常量用来作用execute()方法返回值！<result name="success"/>
    > LOGIN
      * 值：login
      * 表示登录相关！
    > INPUT
      * 值：input
      * 表示输入错误！！！例如表单的输入错误
    > ERROR
      * 值：error
    > NONE
      * 值：none
      * 表示不转发，也不重定向！什么都不做！
      * 不需要对应的<result>元素！！！
      * 与return null相同！
    > SUCCESS：
      * 值：success

3. 去继承ActionSupport（这种是我们最后选择）
  * <action>的class属性默认值！
  * execute()方法直接返回success，没有做其他事。我们应该去覆盖它！！！
  * ActionSupport实现了Action接口！
  * 好处：
    > 表单校验；
    > 错误信息的设置；
    > 获取国际化信息。

==========================================
==========================================
==========================================

Action的请求处理方法

1. BaseServlet可以在一个Servlet中有多个请求处理方法，Action也能！

2. 三种形式

第一种形式：
  * Action类中的书写：
    > 要求类的请求处理方法的签名必须与execute()方法相同！例如写登录方法：public String login() {}
    > 在配置<action>元素时需要给出method属性来指定要调用的方法，例如：<action name="xxx" class="..." method="login">

  * 例如；
    > struts.xml中的配置：指定请求处理方法的名称，使用method指定
  		<action name="aaction_login" class="cn.icore.action.AAction" method="login"/>
		<action name="aaction_regist" class="cn.icore.action.AAction" method="regist"/>
    > 继承ActionSupport，请求处理方法签名与execute()相同！
	public String login() throws Exception {
		System.out.println("login()....");
		return null;
	}
	
	public String regist() throws Exception {
		System.out.println("regist()....");
		return null;
	}

--------------------------

第二种形式（比较常用）：使用通配符简化第一种
  * 在<action>元素的name中使用通配符，在method中使用{N}，其中N表示其N个通配符匹配的文本
  * 例如；<action name="user_*" class="..." method="{1}"/>其中{1}表示第一个通配符匹配的文本
    > /user_login.action，那么通配符匹配的是login，即method="login"，调用的是Action的login()方法

    <action name="user_*" class="cn.icore.action.AAction" method="{1}">（使用比较多）
  * <action name="*_*_*" class="cn.icore.action.{1}Action" method="{2}">
        <result name="success">/{3}.jsp</result>
    </action>
    /User_login_msg
     * UserAction
     * login()
     * msg.jsp

--------------------------

第三种形式：动态方法调用
  * 配置还是原来的配置！
  * 在请求时指定要调用的方法名称！
  * struts.enable.DynamicMethodInvocation=true，这个常量可以设置为false，即关闭动态方法调用。默认值为true，表示开启！


==========================================
==========================================
==========================================

Action获取Servlet API

1. 解耦方法
  * 获取使用的不是Servlet API，而是操作域对象（application、session、request）
                                     Servlet域(ServletContext,session,request)
  * 步骤：
    > 获取ActionContext
      * ActionContext ac = ActionContext.getContext();
    > 获取参数域
      * Map<String,String[]> params = ac.getParameters();，等同于request.getParameterMap();
      * ac.put("aa", "aa");，等同于request.setAttribute("aa", "aa");
      * Object obj = (Object)ac.get("aa");，等同与Object obj = (Object)request.getAttribute("aa");
      * Map<String,Object> sessionMap = ac.getSession()，得到的这个Map就是session域！
      * Map<String,Object> appMap = ac.getApplication();，得到的这个Map就是application域！

2. 感知方法
  * 它需要Action去实现一组接口，然后重写接口中的方法，通过这些方法得到Servlet API
  * 这些与Servlet API相关的接口，给出三个例子
    > ServletContextAware：setServletContext(ServletContext)
    > ServletRequestAware：setServletRequest(ServletRequest)
    > ServletResponseAware：setServletResponse(ServletResponse)
  * 这些接口中的方法，由Struts2来调用，Struts2负责把这些方法的参数传递过来，即传递Servlet API

3. ServletActionContext静态获取（常用）
  * 使用ServletActionContext类的静态方法获取Servlet API
		ServletContext context = ServletActionContext.getServletContext();
		ServletRequest request = ServletActionContext.getRequest();
		ServletResponse response = ServletActionContext.getResponse();


==========================================
==========================================
==========================================

登录练习

1. 页面
  * login.jsp：表单
  * succ.jsp：登录成功后看到的页面

2. UserAction
  * public String login()：给出登录逻辑

3. struts.xml：对Action进行配置

==========================================

从Action中给页面传递文本数据

1. ActionSupport类提供的三个方法
  * addFieldError(String 字段名称, String 错误信息)，例如：this.addFieldError("username", "用户名不能为空");
  * addActionError("用户名或密码错误！");
  * addActionMessage("恭喜，登录成功！");

2. 页面中使用s标签来获取这些信息
  * <%@ taglib prefix="s" uri="/struts-tags"%>
  * <s:feilderror fieldName="username"/>
  * <s:actionerror/>
  * <s:actionmessage/>

==========================================

在Action中获取表单数据

1. 属性驱动
  * 在Action中提供与表单字段同名的属性

2. 模型驱动
  * 让Action实现ModelDriven接口
  * 重写ModelDriven接口的getModel()方法：返回模型成员
  * 给Action提供模型成员，必须手动实例化！


3. OGNL方式
  * 在Action中给出User属性（要求给出getter/setter方法）
  * 页面中表单字段的名称需要使用ognl表达式！（这个东西与el很相似）

  	用户名：<input type="text" name="user.username"/>
	密　码：<input type="password" name="user.password"/>

--------------------------------------

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

========================================
========================================
========================================
框架：

1. 软件 = 应用组件 + 业务组件
2. 框架是半成品软件，完成了应用组件部分。在框架上开发只需要给出软件的应用部分即可。

Struts2框架

1. Struts2是MVC框架，MVC框架不会涉及到service和dao层。只涉及到jsp和servlet层。
2. Struts1、Struts2、JSF、SpringMVC都是MVC框架。

Struts1和Struts2比较
1. 两个不同的框架，Struts2并不是Struts1的升级版。
2. Struts2 = WebWork + Struts名称。Struts2核心是WebWork
3. WebWork的核心是XWork

==============================

Struts2处理请求流程

1. 页面 --> Servlet
2. 页面 --> 前端控制器 --> Action(替代Servlet)

在Struts2中没有Servlet了。而是由Struts2提供的Action。
前面控制器：StrutsPrepareAndExecuteFilter，过滤器。
没有前端控制器，就无法提供核心功能。

这个过滤器需要过滤所有请求。

===============================

下载Struts2安装包

struts.apache.org

-apps：demo
-docs：文档
-lib：struts核心jar包及依赖
-src：源代码

================================

Struts2流程图全貌

Struts执行的是Action的代理对象，即添加了增强的Action对象。增强部分就是Interceptor(拦截器)


================================
================================
================================

配置

1. 6大配置
  default.properties 
    --> struts-core-2.3.x.jar\org.apache.struts2\default.properties
    --> 默认的常量配置
  struts-default.xml
    --> 结果类型和拦截器的定义，它内部定义了一个包：struts-default，我们自定义的包需要继承这个包。
  struts-plugin.xml
    --> 每个struts2插件都有一个struts-plugin.xml配置文件，它是插件自己的配置文件。

  struts.xml
    --> 我们真正要写的配置文件
  struts.properties
    --> 我们可以写的配置文件，我们很少使用它。它可以用来配置常量
  web.xml
    --> 可以配置常量，常量需要配置给StrutsPreparAndExecuteFilter这个过滤器的初始化参数！优先级最高。

2. struts.xml配置信息

<package> -- <struts>的子元素
  * name --> 随便起名称，但如果在struts.xml中有多个<package>，那么必须保存<package>的name是唯一的。
  * namespace --> 通常设置为“/”，它与<action>的name一起来确定<action>的访问路径。
  * extends --> 父包，子包会继承父包中所有的内容。通常使用struts-default！这个包定义在struts-default.xml文件中。

<action> -- <package>的子元素
  * name --> 与<package>的namespace一起决定访问路径，注意，name不能有.action
  * class --> 当前action的类名。

<result> -- <action>的子元素
  * name --> 结果视图名称，默认值为success，与Action请求处理方法的返回对应。
  * 内容 --> 结果视图的路径
  * type --> 结果视图的类型，例如有转发到jsp、重定向到jsp、转发到action、重定向到action等
    
<default-class-ref> -- <package>子元素
  * class --> 指定<action>元素的class属性默认值。父包中定义为com.opensymphony.xwork2.ActionSupport

<default-action-ref> -- <package>子元素
  * name --> 引用某个<action>的name！当请求路径找不到匹配的<action>时，那么执行指定action。

<include> -- 父元素为<struts>，用来包含其他配置文件。
  * file --> 要包含的配置文件。

3. 配置常量

<constant> -- <struts>的子元素
  * name --> 常量名
  * value --> 常量值

常用常量：
struts.i18n.encoding=UTF-8 --> 默认值为utf-8，等同与request.setCharacterEncoding("utf-8")。
struts.action.extension=action --> 默认值为action,,，表示Action的请求路径的后缀可以是action或无后缀。
struts.serve.static.browserCache=true --> 默认值为true，表示浏览器会缓存页面
struts.devMode=false --> 是否为开发者模式，默认为非开发者模式。开发者模式会有详细的异常信息。
struts.configuration.xml.reload=false --> 在修改了struts的配置文件后是否自动加载，默认为false。

  

================================
================================
================================

================================
================================
================================

请求处理类三种形式

1. 无耦合：无实现、无继承，但必须要有public String execute()方法。不常用！
2. 实现Action接口：Action接口只有一个方法，即：public String execute()方法。不常用！
  Action接口的5个常量：
  * SUCCESS --> 功能页面
  * LOGIN --> 登录页面，因为访问了必须登录后才能访问的页面时，跳转到登录页面。
  * INPUT --> 输入页面，因为在表单中输入了错误数据，例如用户为空，跳转回来时的表单页面。
  * ERROR --> 错误页面，其中login和input都是错误的一种，如果错误不是login或input，那么就使用error。
  * NONE --> 不跳转，与null相同效果。
3. 继承ActionSupport：ActionSupport是Action接口的实现类。这种方式最常用！
  * 表单校验；
  * 错误信息的设置；
  * 获取国际化信息。

请求处理方法
1. 默认请求处理方法为execute()。
2. 一个Action多个请求处理方法。一般是一个模块只给出一个Action，例如User模块只有一个UserAction。

<action name="user_add" class="cn.icore.user.action.UserAction" method="add">
<action name="user_edit" class="cn.icore.user.action.UserAction" method="edit">
<action name="user_delete" class="cn.icore.user.action.UserAction" method="delete">
<action name="user_find" class="cn.icore.user.action.UserAction" method="find">

public class UserAction extends ActionSupport {
    public String add() {
        return null;
    }
    public String edit() {
        return null;
    }
    public String delete() {
        return null;
    }
    public String find() {
        return null;
    }
}

3. 使用通配符减少<action>个数

<action name="user_*" class="cn.icore.user.action.UserAction" method="{1}">

其中“*”匹配任意字符串，例如请求路径为：/user_add.action，那么“*”匹配add。
其中“{1}”表示第一个“*”匹配的内容，即add。

也可以在name中使用多个“*”，例如：
<action name="*_*_*" class="cn.icore.user.action.{1}Action" method="{2}">
  <result>/demo1/{3}.jsp</result>
</action>
常用！

其中请求路径为：/User_add_end.action，那么{1}=User、{2}=add、{3}=end。
即：
<action name="*_*_*" class="cn.icore.user.action.UserAction" method="add">
  <result>/demo1/end.jsp</result>
</action>
不常用！

4. 动态方法调用
作用与通配符相似。

<action name="user" class="cn.icore.user.action.UserAction">
public class UserAction extends ActionSupport {
    public String add() {
        return null;
    }
    public String edit() {
        return null;
    }
    public String delete() {
        return null;
    }
    public String find() {
        return null;
    }
}

请求路径中指定要调用的方法：/user!add.action，表示请求处理方法为add()。
可以使用常量来关闭动态方法调用：
struts.enable.DynamicMethodInvocation=false，这就关闭了。

==========================


Action中获取ServletAPI

1. 解耦方式（常用）
通过ActionContext获取request、session、application解耦Map。

* 获取ActionContext：ActionContext ac = ActionContext.getContext();
* 对request域的操作
ac.put("xxx", "XXX") --> 相等与request.setAttribute("xxx", "XXX");
Object o = ac.get("xxx"); --> 等同与Object o = request.getAttribute("xxx");

* 对session域的操作
Map<String,Object> sessionMap = ac.getSession();
sessionMap.put("xxx", "XXX") --> 等同与session.setAttribute("xxx", "XXX");
Object o = sessionMap.get("xxx") --> 等同与Object o = session.getAttribute("xxx");

* 对application域的操作
Map<String,Object> appMap = ac.getApplication();
appMap.put("xxx", "XXX") --> 等同与servletContext.setAttribute("xxx", "XXX");
Object o = appMap.get("xxx") --> 等同与Object o = servletContext.getAttribute("xxx");

* 对请求参数的操作
Map<String,Object> paramMap = ac.getParameters();
Object o = paramMap.get("username");
String[] values = (String[])o;
String username = values[0];


2. Action实现感知接口，得到Servlet API。（不建议使用）

class UserAction extends ActionSupport implements ServletContextAware {
    private ServletContext sc;
    // 这个方法会在请求处理方法之前被调用。
    @Override
    public void setServletContext(ServletContext sc) {
        this.sc = sc;
    }

    public String execute() {
        // 在这里可以大胆使用sc
    }
}


3. ServletActionContext获取ServletAPI。（最常用）
HttpServletRequest request = ServletActionContext.getRequest();
HttpServletResponse response = ServletActionContext.getResponse();
ServletContext sc = ServletActionContext.getServletContext();


======================


结果类型

<action ...>
  <result>/xxx.jsp</result>
</action>

<result>的name默认值为：success

当Action的execute()方法返回为"success"时，表示转发到/xxx.jsp页面。
如果我们希望可以重定向到/xxx.jsp页面时怎么办呢？

<result type="redirect">/xxx.jsp</result> --> 表示重定向到jsp
<result type="redirectAction">xxxAction</result> -->表示重定向到名为xxxAction的Action。
<result type="dispatcher">/xxx.jsp</result> --> 表示转发到jsp，type默认值为dispatcher
<result type="chain">xxxAction</result> --> 表示转发到xxxAction。

---------------

<action name="demo1" class="cn.icore.Demo1Action">
  <result name="demo1">/demo1.jsp</result>
</action>

<action name="demo2" class="cn.icore.Demo2Action">
  <result name="demo2">/demo2.jsp</result>
</action>

class Demo1Action extends ActionSupport {
   public String execute() {
      return "demo2";//demo2这个结果是在<action name="demo2">中定义的。所以不能使用！
   }
}

全局结果：
<global-results> -- <package>的子元素

<global-results>
  <result name="demo1">/demo1.jsp</result>
</global-results>

这里的结果所有Action都可以用！

===========================================
===========================================
===========================================
Struts2介绍

1. 什么是框架
　　* 软件=应用组件+业务组件，应用组件(共性)+业务组件(个性)，框架就是应用组件，使用框架开发，就不用什么都从头开始！
　　* 框架就是半成品的软件！
2. 什么是Struts2框架
　　* Struts2是针对MVC的一个框架！
　　* Struts2关注的是java软件三层中的WEB层！不关注业务逻辑层和数据访问层
3. Struts2的核心
　　* 内核是WebWork
　　* Struts1是全世界最早的MVC框架！
　　* Struts2 = WebWork + Struts这个名称 + 杂七杂八
　　* WebWork的内核是Xwork！
4. Struts2执行流程
　　* 



Hello Struts2

1. 下载Struts2
2. 安装包的结构
　　* apps --> demo
　　* docs --> 文档
　　* lib --> jar包
　　* src --> 源码
3. 搭建环境
　　* 导包：apps\blank.war\WEB-INF\lib下的jar包
　　* 配置web.xml：
  <filter>
  	<filter-name>struts2</filter-name>
  	<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>struts2</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
　　* 添加struts.xml：在src目录下
<struts>
	<package name="day01_1" namespace="/" extends="struts-default">
	</package>
</struts>
4. 完成第一例
　　* jsp：hello.jsp(表单)
　　* Action：HelloAction(请求处理类), (execute)请求处理方法
　　* 转发页面：result.jsp
　　* struts.xml中配置Action

页面中的访问路径与struts.xml中的<action>元素的name属性对应，并且访问路径还要有扩展名，即.action
请求处理方法格式：public String exeucte()
转发的与请求处理方法的返回值什么关系：例如execute()返回“success”，那么在<action>中添加<result>元素
<result>元素添加name="success"，内容添加/result.jsp

Struts2的基本配置

1. 6大配置文件
　　* default.properites：里面都是常量！
　　* struts-default.xml：<bean>、<result-type>、<interceptor>
　　* struts-plugin.xml：每个插件都要有一个配置文件，名称就是struts-plugin.xml
　　* struts.xml：<package>、<action>、<result>、<constant>
　　* struts.properites：常量，很少使用
　　* web.xml：也可以配置常量，根本不用！
2. <package>
　　* name：主键，只要不重复就OK!通常一个<package>元素对应一个模块！
　　* namespace：访问路径的前缀，一般给“/”，http://localhost:8080/day01_1/helloAction.action
　　* extends：一般都给“struts-default”，它是父包的名称。
3. <aciton>
　　* name：访问路径的后缀，它与namespace加在一起决定了访问路径！
namespace="/user"
<action name="hello">
/user/hello.action

struts通过访问路径查找的过程
	<package name="a" namespace="/user">
		<action name="foo"></action>
	</package>
	<package name="b" namespace="/">
		<action name="foo"></action>
	</package>
	<package name="c" namespace="">
		<action name="foo"></action>
	</package>
　　* class：指定请求处理类，它是可选属性，默认值为ActionSupport

		<action name="hello2Action">
			<result name="success">/result.jsp</result>
		</action>

当<action>没有指定class属性时，默认执行ActionSupport的execute()方法
这个方法内容是直接返回"success"，这也说明<action>中必须要配置<result name="success">，否则会出现异常！

4. <action>的class属性默认值
　　<default-class-ref class="cn.icore.action.HelloAction" />
　　指定当前<package>中，<action>元素的class属性的默认值为cn.icore.action.HelloAction

5. 不存在的<action>
　　当访问的路径是Action，因为有.action扩展名。但这个Action不存在！我们可以指定一个默认<action>
	<default-action-ref name="helloAction" />，其中name指定的是一个<action>的name
	<action name="helloAction" class="cn.icore.action.HelloAction">
当访问的Action不存在时，即访问helloAction！

6. <result>
　　name：与execute()方法的返回值对象。name是可选属性，它的默认值是success

public String execute() {
  return "abc";
}
<action>
  <result name="abc">/xxx.jsp</result>
</action>

public String execute() {
  return "success";
}
<action>
  <result>/xxx.jsp</result>
</action>

7. Struts2常量
　　* 配置常量通常在struts.xml中：<constant name="常量名" value="常量值" />
　　* <constant name="struts.i18n.encoding" value="utf-8"/>：配置访问编码，注意，只对post有效
　　* struts.action.extension=action,, --> 访问Action的扩展名，可以为action和没有扩展名。
　　* struts.serve.static.browserCache=true --> 浏览器是否缓存
　　* struts.devMode=true --> 是否使用开发者模式
　　* struts.configuration.xml.reload=true --> 是否自动加载xml文件

8. Struts2配置文件分离
　　多人开发一个项目，每个人可以自己写一个xml配置文件，然后都导入到struts.xml中，这时需要使用<include>元素！
　　* <include file="sturts-part1.xml"/> --> 导入一个配置文件。

========================
========================
========================

Action

1. 3种Action
　　* Action替代Servlet，它是Struts2中的请求处理类
  1). 解耦方式：上午打的样子。Action类，不继承，也不实现！(基本不用)
  2). 实现Action接口（基本不用）
　　Action接口中有一个方法和五个常量
　　* public String execute() throws Exception; --> 不解释
　　* SUCCESS --> 表示成功
    * NONE("none") --> 表示不跳转，其实null也表示不跳转
　　* ERROR --> 表示错误！
　　* INPUT --> 表示输入错误！从哪来回哪去！例如在login.jsp提交，出现输入错误，那么就返回到input视图，即login.jsp
　　* LOGIN --> 表示错误！需要转发到login页面时！例如：当你访问了受限的资源，那时应该跳转到login.jsp，并显示“您还没有登录”
　3). 继承ActionSupport类！我们写的Action都要继承ActionSupport！
　　继承ActionSupport的好处之一，添加信息：
　　　1). addActionError(String msg);
　　　2). addFieldError(String fieldName, String msg);
　　　3). addActionMessage(String msg);
2. 请求处理方法
  一个Action多个请求处理方法
  <action name="demo3_add" class="cn.icore.demo3.Demo3Action" method="add" />
  <action name="demo3_delete" class="cn.icore.demo3.Demo3Action" method="delete" />
  <action name="demo3_update" class="cn.icore.demo3.Demo3Action" method="update" />

  其中method属性用来指定请求处理方法

　　使用通配符来简化<action>的配置
  <action name="demo3_*" class="cn.icore.demo3.Demo3Action" method="{1}"/>
　使用我请求的路径为：/demo3_add，那么星号就与add匹配上了，其中{1}就表示星号的值！

  动态调用
配置还是使用原始的配置，其中不用给出method
<action name="demo4Action" class="cn.icore.demo4.Demo4Action" />
在请求路径中给出要调用的方法：/demo3Action!add.action，其中add就是要调用的方法。

动态调用可以关闭！
<constant name="struts.enable.DynamicMethodInvocation" value="false"/>


3. Action与Servlet API
　　如何在Action中获取request、response、session、application等对象！
　　3种方式：
　　1). 解耦方式：它不是用来获取request、session、application等对象的，而是获取解耦对象(Map)
　　　获取几个域对象的Map，然后我们对Map进行操作，就等同与操作域对象一样。
　　2). 感知接口方式
　　3). ServletActionContext方式
　　

Result

1. 局部结果和全局结果
　　* 局部结果：在<action>中的<result>元素是局部结果，局部结果只能被当前的<action>使用
　　* 全局结果：在<package>下创建<global-results>元素，该元素中的<result>元素全局结果，
全局结果可以供当前<package>中所有的<action>使用！
2. 结果类型
　　<result>元素有两个属性
　　* name：它的默认值success
　　* type：表示跳转的方式：
　　　1). dispatcher：转发到jsp，默认！
　　　2). chain：转发到Action
　　　3). redirect：重定向到jsp
　　　4). redirectAction：重定向到Action
　　  5). stream：下载时使用！

		<action name="abc" class="cn.icore.action.Servlet1Action">
			<result>/result1.jsp</result>
		</action>
		<action name="def" class="cn.icore.action.Servlet3Action">
			<result type="chain">abc</result> <!-- type="chain"表示转发到Action，其中abc就是另一个Action的配置名称 -->
			<result type="redirectAction" name="succ1">abc</result><!--重定向到另一个Action-->
			<result type="redirect" name="succ2">/index.jsp</result><!--重定向到jsp页面-->
		</action>




登录练习

1. 需求
  jsp：login.jsp、succ.jsp
  Action：LoginAction
  domain：User

  * 获取表单数据：先获取request，再获取表单数据，封装到User对象中
　* 校验User对象，如果有错误返回到login.jsp显示输入校验错误
  * 校验用户名和密码，如果错误，返回到login.jsp显示Action错误
  * 正确后把当前User对象保存到session中，这说明需要先获取Session，当然也可以获取session的灵魂！转发到succ.jsp

-------------

属性驱动：
1. 在表单中的字段名称与Action的属性名称相同，那么Struts2会自动把表单数据封装到Action的属性中。


模型驱动：
1. 要求Action必须实现ModelDriven
public class Login2Action extends ActionSupport implements ModelDriven<User> {
    ...
	public User getModel() {
		return null;
	}
}

2. 添加User属性，并手动实例化，然后在getModel()方法中返回它！

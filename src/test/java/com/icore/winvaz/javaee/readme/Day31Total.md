上传

1. 上传对表单的要求
  * method="post"
  * enctype="multipart/form-data"
  * <input type="file" name="xxx"/>

2. 服务器端
  * commons-fileupload

3. Struts2的上传
  * 1对3：页面上1个文件表单项，对应Action的3个属性。
    > 1个文件表单项：<input type="file" name="myfile"/>
    > Action的3个属性（属性命名必须使用文件表单项的名称为前缀）：
      * String myfileContentType：上传文件的mime类型
      * String myfileFileName：上传文件的文件名称
      * File myfile：上传文件的内容

  * 这三个属性会被自动赋值！给这三个属性赋值，是由一个名叫fileupload的拦截器完成的！
    > fileUpload拦截器它会去查看当前的表单提交是否为multipart/form-data
    > fileUpload拦截器底层依赖某种上传组件（默认使用commons-fileupload）

4. 上传的配置
  * 3个常量（<constant name="常量名" value="常量值"/>）
    > struts.multipart.parser：用来指定上传组件
      * jakarta：表示commons-fileupload，它是默认值！
      * cos：表示COS组件
      * pell：表示pell组件
    > struts.multipart.saveDir：指定临时目录，默认为空，表示在tomcat的work目录下
    > struts.multipart.maxSize：整个请求的字节数上限，默认为2097152，即2M
  * 3个拦截器参数（在<action>中装配拦截器，<interceptor-ref name="fileUpload"><param ....></...>）
    > maximumSize：一个文件表单项的字节数上限
    > allowedTypes：允许上传的文件MIME类型
    > allowedExtensions：允许上传的文件扩展名

			<interceptor-ref name="defualtStack">
				<param name="fileUpload.allowedExtensions">jpg,gif,png</param>
			</interceptor-ref>
  * 一旦出现违反了配置信息的事情，会跳转到input结果！

5. 多文件上传
  * 多对3：
    > 多个文件表单项
      * 多个文件表单项的名称必须相同
    > Action中3个属性：
      * List<String>：myfileContentType
      * List<String>：myfileFileName
      * List<File>：myfile

=========================================
=========================================
=========================================

下载

1. 两个头一个流
  * 在execute()方法中准备两个头一个流
  * 在<result>元素中配置三个参数，即两个头一个流！type必须为stream

=========================================
=========================================
=========================================

OGNL表达式（对象图导航语言）

1. 与EL相似，但比EL强大


2. 单独学习OGNL
  * 导包
    > ognl-3.0.5
    > javassist-3.11.0.GA

3. OGNL操作的域
  * EL操作四大域
  * OGNL操作
    > ognl上下文对象：OgnlContext，它是Map的实现类
    > root对象：任意的javabean，即Object类型

4. ognl对象导航（*****）
  * #key.属性：获取上下文中指定key的javabean的指定属性
  * 属性：获取root对象指定属性

5. 常量和运算符
  常量：
  * 字符串常量：使用双引或单引，如果字符串是一个字符，那么必须用双引。"aaa"、'abc'
  * 字符常量：使用单引。
  * 数值常量、boolean常量、null常量，都与java相同！
  
  运算符：
  * 大多数运算符与java相同
  * 逗号运算符：与C语言相同！
  * 大括号（{}）：创建ArrayList，例如：{‘zhangSan’,’liSi’,’wangWu’}
  * in：判断某值是否在集合中存在。返回boolean类型！
  * not in：不用解释了。

6. 设置javabean属性值
  * Ognl.setValue(表达式, 上下文, root, 值)

7. 调用对象方法
  * 'Hello'.toUpperCase()

8. 静态方法和静态成员
  * @java.lang.Math@max(1,2)
  * @java.lang.Math@PI

9. 操作数组和集合
  * 操作数组和操作List是相同的，可以使用下标法
    > 创建ArrayList的语法
  * 操作Map：也可以使用下标法，也可以使用javabean导航方式。
    > 创建Map对象

10. 投影和选择（了解）
  投影：
  * #empList.{name}：查询某一列，所有行

  选择：查询所有列，带有条件
  * #empList.{? salary>10000}：查询所有满足条件对象
  * #empList.{^ salary>10000}：查询第一个满足条件的对象
  * #empList.{$ salary>10000}：查询最后一个满足条件的对象

=========================================
=========================================
=========================================

Struts2中的Ognl

1. Struts2中的Ognl
  * 只需要给出ognl表达式！
  * 无需自己指定上下文（Struts2指定）
  * 无需自己指定根对象（Struts2指定）
  * 使用s标签来执行ognl表达式（不只是s标签）
  * 输出标签：<s:property value="ognl表达式"/>

2. Struts2提供的上下文和根对象
  得到上下文和根对象
  * 使用ActionContext对象的getContextMap()，返回类型为OgnlContext
  * OgnlContext#getRoot()，返回类型为CompoundRoot

  * 得到ValueStack：actionContext.getValueStack();
    > valueStack.getContext()：返回Map，其真实类型为OgnlContext
    > valueStack.getRoot()：返回CompoundRoot，即根对象！

3. 操作Ognl上下文和root对象
  * 通过ActionContext即可操作Ognl上下文
    > 五大map，以及当前Action对象
      * application域
      * session域
      * request域
      * parameters域
      * attr（全域）
      * 当前Action
  * 通过ValueStack即可操作root对象
    > 当前Action对象

=========================================

ValueStack

1. ValueStack是值栈
  * 可以用来操作root对象
  * root对象其实是个List
  * valueStack#findValue(String expr)：栈内查询！
    > 栈底层使用的是root对象，即List
    > 从栈顶开始查找，栈顶元素如果没有对应的属性
    > 到第二个元素查找，如果还没有找到，再去第三个，以此类推！


2. [N]语法
  * <s:property value="[2]"/>，其中[2]表示一个子栈，从下标2元素开始，到最后一个元素。
  * <s:property value="[2].name"/>，其中从子栈中查询name属性。

3. top请求
  * <s:property value="top"/>，取栈顶！
  * <s:property value="[2].top"/>，[2]是一个子栈，再取这个栈的栈顶，即下标2元素！
  * <s:property />，没有给出value属性，其默认值为top！

4. Struts2默认是关闭静态访问的
  <constant name="struts.ognl.allowStaticMethodAccess" value="true"/>，开启静态访问

5. 模型驱动与ValueStack
  * params拦截器：把表单数据封装到action中
    > 得到ognl表达式，给ValueStack处理，例如：<input type="text" name="username"/>，把username当成ognl来处理！
    > ValueStack中默认Action在栈顶，那么username这个ognl表达式指定就是Action的username属性！
  * modelDriven拦截器
    > 查看当前Action是否实现了ModelDriven接口
    > 如果是，获取model对象，然后压栈！


  问题：

  <form ...>
    <input type="text" name="username"/>//zhangSan
    <input type="text" name="password"/>//123
  </form>

  class User {
      private String username;

      ...
  }
  class Action implements ModelDriven<User> {
      private User model = new User();
      private String username;
      private String password;

      ...

      public User getModel() {
          return model;
      }
  }

ValueStack
  * User --> zhangSan赋给了它的username
  * Action --> 123赋给了它的password

6. EL操作值栈
  * Struts2把request调包了，换成装饰类：StrutsRequestWrapper
  * StrutsRequestWrapper类对getAttribute()方法进行了增强
    > 先去request域进行查找：Object value = req.getAttribute(name);
    > 如果返回null，把name交给ValueStack来查找：valueStack.findValue(name);
  * EL的全域查找：pageContext、Struts的request(request、valueStack)、session、application

7. #、%、$
  * #：表示操作OgnlContext！
  * $：在配置文件中ognl表达式需要使用${...}括起来！
  * %：不是所有的s标签的属性都支持ognl表达式！如果不支持，可以使用%{...}让它支持！
    * <s:property value=""/>，value属性支持！
    * <s:hidden name="" value=""/>，它的value就不支持ognl表达式
      > <s:hidden name="" value="%{ognl}"/>

  <s:xxx yyy="name"/>
    * 如果支持ognl，那么name就不是字符串，而且栈内查找name属性
    * 如果不支持ognl，那么name就是字符串name。
    * <s:xxx yyy="%{'name'}"/>

8. 使用ValueStack传递数据
  * Action向页面传递数据（String数据）：
    > this.addFieldError()
    > this.addActionError()
    > this.addMessageError()
  * Action向页面传递User或List<User>，使用ValueStack来完成
    > 把数据压栈：ActionContext.getContext().getValueStack().push(user)
    > 使用s标签读取栈内数据：<s:property value="username"/>、<s:property value="password"/>

9. debug标签
  * 在页面上生成一个超链接
  * 点击超链接会在页面上显示ognl上下文和valuestack的内容

==========================================
==========================================
==========================================
上传

1. 表单一file字段，Action三属性
  注意Action三属性的命名
  <input type="file" name="myUpload"/>
  class Action extends ActionSupport {
    private File myUpload;//上传文件内容，对应临时文件
    private String myUploadFileName;//上传文件名称，可能是绝对路径，需要自己截取名称
    private String myUploadContentType;//上传文件的MIME类型
  }

2. 流程：fileupload拦截器对表单的enctype进行判断，如果是multipart/form-data类型
那么把<input type="file">的信息封装到Action的3个属性中。
3. 常量配置：
  * struts.multipart.parser：指定上传组件，默认为jakarta、可选项：pell、cos
  * struts.multipart.saveDir：指定临时目录
  * struts.multipart.maxSize：指定整个请求大小限制，默认为2M
4. 拦截器参数：
  * maximumSize：单个文件大小限制
  * allowedTypes：允许的MIME类型，多个类型中间用逗号分隔
  * allowedExtensions：允许的扩展名，多个扩展名中间用逗号分隔

5. 错误信息key
  * struts.messages.error.uploading：上传出错
  * struts.messages.error.file.too.large：单个文件超出限制
  * struts.messages.error.content.type.not.allowed：不允许的MIME类型
  * struts.messages.error.file.extension.not.allowed：不允许的扩展名
  * struts.messages.upload.error.SizeLimitExceededException：整个请求超出限制

6. 多文件上传
  表单所有file字段的name必须相同
  Action三属性为List类型。
<form ...>
  <input type="file" name="myUpload"/>
  <input type="file" name="myUpload"/>
  <input type="file" name="myUpload"/>
  <input type="file" name="myUpload"/>
  <input type="file" name="myUpload"/>
</form>

class MyAction extends ActionSupport {
  private List<File> myUpload;
  private List<String> myUploadFileName;
  private List<String> myUploadContentType;
}

下载
1. 下载需要为response提供两个流一个头
2. <result type="stream">
  stream结果类为StreamResult类，需要给它传递两个流一个头。
<reuslt type="stream">
  <param name="contentType">image/jpeg</param>
  <param name="contentDisposition">attachment;filename=xxx.jpg</param>
  <param name="inputName">inputStream</param><!--指定Action的类型为InputStream类的属性名-->
</result>

class MyAction {
  private InputStream inputStream = new FileInputStream("F:/a.jpg");

  public InputStream getInputStream() {
     return inputStream;
  }

  public String execute() {
    return "success";//把一切交给结果处理
  }
}

================================
================================
================================

OGNL

1. 什么是OGNL：对象图导航语言
  * 与EL相似。
  * EL操作的是域对象，而OGNL操作的是上下文对象和根对象

2. OGNL基本语法
  * 上下文：OgnlContext，它是Map类型
  * 根对象：可以指定一个根对象
  OgnlContext cxt = new OgnlContext();
  Emp emp1 = new Emp("zhangSan", 2000, new Address("中国", "北京", "大北窑"));
  Emp emp2 = new Emp("liSi", 3000, new Address("美国", "华盛顿", "白宫路内大街"))
  cxt.put("e1", emp1);
  cxt.put("e2", emp2);
  cxt.setRoot(emp1);

  Ognl.getValue("#e1.name", cxt, cxt.getRoot());//获取emp1的name
  Ognl.getValue("#e2.address.city", cxt, cxt.getRoot());//获取emp2的address的city
  Ognl.getValue("salary");//获取根对象的salary
  
3. 常量
  * 字符串：单引或双引，其中单个字符的字符串必须用双引，因为单个字符用单引是字符，而不是字符串。
  * 其他基本与Java相同

4. 运算符
  * 逗号运算符：多个表单式从左到右依次运算，整个表单式的值为最后一个表达式的值
  * {}：创建List，{"zhang", "li", "wang"}，表示一个List
  * in和not in：name in {"zhang", "wang", "li"}，返回boolean，表示根对象.name是否在List中存在
  * 与

5. 设置JavaBean的值
  Ognl.setValue("#e1.name", cxt, cxt.getRoot(), "zhangSan");//设置emp1的name属性值为zhangSan

6. 调用方法
  Ognl.getValue("'hello'.toUpperCase()", cxt, cxt.getRoot());

7. 静态方法和静态属性
  Ognl.getValue("@java.lang.Math@min(10,20)", cxt, cxt.getRoot())

8. 操作数组或List
  cxt.put("arr", new String[]{"aa", "bb", "cc"});
  Ognl getValue("#arr[0]", cxt, cxt.getRoot());

9. 操作集合
  Map map1 = (Map)Ognl.getValue("#{'k1':'v1','k2':'v2','k3':'v3'}", cxt, cxt.getRoot());
  Map map2 = (Map)Ognl.getValue("#@java.lang.HashMap@{'k1':'v1','k2':'v2','k3':'v3'}", cxt, cxt.getRoot());
  cxt.put("map", map1);
  Ognl.getValue("#map.k1", cxt, cxt.getRoot());//v1
  Ognl.getValue("#map['k1']", cxt, cxt.getRoot());//v1

10. 投影和选择
List<Employee> list = new ArrayList<Employee>();
list.add(new Employee("zhangSan", 10000));
list.add(new Employee("liSi", 11000));
list.add(new Employee("wangWu", 12000));
cxt.put("empList", list);

// 获取list中所有Emp对象的name
List<String> nameList = (List<String>) Ognl.getValue("#empList.{name}", cxt, cxt.getRoot());
// 获取所有满足条件的Emp对象
List<Emp> empList = (List<Emp>) Ognl.getValue("#empList.{? salary > 2000}", cxt, cxt.getRoot());
// 获取第一个满足条件的Emp对象
List<Emp> empList = (List<Emp>) Ognl.getValue("#empList.{^ salary > 2000}", cxt, cxt.getRoot());
// 获取最后一个满足条件的Emp对象
List<Emp> empList = (List<Emp>) Ognl.getValue("#empList.{$ salary > 2000}", cxt, cxt.getRoot());


Struts2与OGNL

1. Struts2中的上下文和根对象
  * ActionContext.getContext().getContext() --> 上下文件
    1). parameters：参数解耦对象(Map类型)
    2). request：request域解耦对象(Map类型)
    3). session：session域解耦对象(Map类型)
    4). application：application域解耦对象(Map类型)
    5). attr：全域(依次pageContext、request、session、application)对象
  * ActionContext.getContext().getValueStack() --> 值栈，它是一个栈结构(后进先出)，栈中所有对象都是根对象
    1). 如果是属性驱动，栈顶对象是当前Action
    2). 如果是模型驱动，栈顶对象是模型对象，第二个对象是Action

2. <s:property value="ognl表达式"/> --> 输出
  <s:property value="name"/> --> 输出根对象的name属性值，如果栈顶对象没有name属性，那么查看第二个对象是否有name属性，一直向下找，直到找到为止，ValueStack中所有对象都没有name属性，那么什么都不输出。
  <s:property value="#parameters.username"/> --> 输出请求参数username

3. [N]语法
  <s:property value="[3].name"/>
其中[3]表示从ValueStack中获取子栈，这个子栈是从3下标开始到栈的最后一个元素。所以[3].name，表示从子栈第一个元素开始查找name属性，即ValueStack下标为3的元素开始查找。

4. top语法
  <s:property value="top"/>，top表示ValueStack栈顶元素，因为通常Action对象就是栈顶对象，所以top就是Action对象
  <s:property />，默认表示栈顶元素，所以<s:property value="top"/>与<s:property/>是一个效果。
  <s:property value="[3].top"/>子栈的栈顶元素，即ValueStack的下标为3的元素。

5. 访问静态属性和静态方法
  <s:property value="@java.lang.Math@min(10,20)"/>
　struts2默认不允许调用静态方法和静态属性，所以需要先打开一个常量：struts.ognl.allowStaticMethodAccess=true
  <s:property value="@java.lang.String@format('%tF %<tT', new java.util.Date())"/>

6. EL表达式访问值栈
  因为Struts对request进行了包装，所以request有了特殊的功能！
  ${name} 当page范围不存在时，到request范围查找，如果request也不存在时，那么这个特殊的request会去ValueStack去查找。如果ValueStack的栈顶元素是Action，那么${name}就是获取Action的name属性值。

7. %、#、$
　它们三个的区别太大了，五点关系都没有，不过它们都在Ognl中使用。

在配置文件中使用$，例如：${xxx}
操作上下文对象：#parameters.xxx，这是#的使用。
不是所有的struts2标签都支持ognl表达式，例如：<s:hidden name="xxx" value="xxx"/>其中value不支持ognl
如果需要在value中使用ognl，那么：<s:hidden name="xxx" value="%{xxx}"/>

8. 从Action传递非字符串内容到jsp页面
  ActionContext.getContext().getValueStack().push(new Emp());
  在jsp中使用struts标签(ognl)获取ValueStack中的元素

======================
======================
======================


Struts2标签

数据标签
1. <s:property> --> 输出
2. <s:set var="xx" value="parameters.username" scope="page"/>
创建变量一个名为xx变量，值为request.getParameter("username"), 保存范围是page。
scope的可选值有：page、request、action、session、application
其中action范围表示在request范围，以及保存到上下文中
3. <s:push value="'hello'"></s:push>
标签开始时向ValueStack中压入字符串hello，标签结束时把hello弹栈。
4. <s:url action="helloAction" namespace="/"/>
生成访问helloAction的url
<s:url action="helloAction" namespace="/">
  <s:param name="userId" value="'123'"/>
</s:url>
生成访问helloActoin的url，并且添加参数userId=123
5. <s:a action="helloAction" namespace="/">
生成访问helloAction的链接
<s:a action="helloAction" namespace="/">
  <s:param name="userId" value="'123'"/>
</s:a>
生成访问helloAction的链接，并添加userId参数
6. <s:debug/>生成debug链接，点击这个链接可以查看上下文和值栈的状态

控制标签
1. <s:if>、<s:elseif>、<s:else>
不用多解释了

2. <s:iterator>
循环遍历{'zhangSan','liSi','wangWu'}这个List，开始标签每次循环都会把一个元素压入栈顶，结束标签会移除栈顶元素
<s:iterator value="{'zhangSan','liSi','wangWu'}">
  <s:property /> --> 打印栈顶元素
</s:iterator>

创建循环状态对象，并保存到上下文中
<s:iterator value="{'zhangSan','liSi','wangWu'}" status="st">
  <s:property value="#st.count"/> --> 输出循环次数
  <s:property value="#st.index"/> --> 输出当前元素下标
  <s:property value="#st.even"/> --> 输出循环次数是否为偶数
  <s:property value="#st.odd"/> --> 输出循环次数是否为奇数
  <s:property value="#st.odd"/> --> 输出循环次数是否为奇数
  <s:property value="#st.first"/> --> 输出是否是循环第一圈
  <s:property value="#st.last"/> --> 输出是否是循环最后一圈
</s:iterator>

UI标签
  自动回显

<s:form action="helloAction" namespace="/" theme="simple">
  <s:textfield name="username"/>
  <s:password name="password"/>
  <s:redio list="{'男','女'}"/>
  <s:checkboxlist list="{"读书","游泳","旅游"}"/>
</s:form>


Struts2秘密

1. Action不是单例的，每个请求都会创建一个新的Action实例。
2. ValueStack与Action一样也不是单例的，也是每个请求一个。


防止重复提交

1. <s:token>
  * 生成token值
  * 在session中保存token值
  * 在表单中生成hidden，保存token值

2. 在<action>中部署token拦截器
  * 获取session中和表单中的token值进行比较，如果相同则放行，否则跳转到invalid.token结果。
  * 错误信息key为struts.messages.invalid.token


标签

1. <s:property>
  * 用途：输出！
  * 属性：
    > value：支持ognl表达式，给出要输出的值。默认值为top，即栈顶元素！
    > escape：默认为true，表示对特殊字符进行转义。
    > default：当value的值为null时，将输出default的值。

2. <s:set>
  * 用途：设置变量
    > var：指定变量名
    > value：指定变量值，默认为栈顶
    > scope：指定变量的范围，page、request、session、application、action，其中action是默认值
      * action：ognl上下文 + request

3. <s:push>
  * 用途：压栈及弹栈！
  * value：支持ognl表达式，要压栈的值。
  * <s:push value="#request.xxx"> 压栈
      这里可以使用
    </s:push>弹栈

4. <s:url>
  * 用途：生成url，针对action生成
  * 属性：
    > action：与<action>元素的name对应
    > namespace：<pakcage>的namespace对应

<package namespace="/user"...>
  <action name="loginAction" ... />
</...>

<s:url action="loginAction" namespace="/user">
  <s:param name="username" value="%{'张三'}">
</s:url>
其中参数会自动做url编码！

5. <s:a>
  * 用途：生成超链接
  * 属性：
    > action：与<action>元素的name对应
    > namespace：<pakcage>的namespace对应

<package namespace="/user"...>
  <action name="loginAction" ... />
</...>

<s:a action="loginAction" namespace="/user">
  <s:param name="username" value="%{'张三'}">
</s:a>

=====================================

控制符

1. <s:if>、<s:elseif>、<s:else>
  * <s:if>：if语句
  * <s:elseif>：else if语句
  * <s:else>：else语句


2. <s:iterator>
  * 用途：遍历Collection、Map、Enumeration、Iterator或者是数组
  * 属性：
    > value：被遍历的集合或数组。默认值为top
    > var：指定在ognl上下文中的key。如果没有给出var就不会向ognl上下文中保存！
  * 用法：
    > 每次拿出一个元素，压栈！还会把这个元素保存到ognl上下文中！
    > 当每次循环结束时，都会弹栈

假如栈顶是一个数组：['zhangSan', 'liSi', 'wangWu', 'zhaoLiu']

<s:iterator var="name"> <!-- 遍历栈项元素（必须保证栈顶元素是可遍历的） --><!--没有var属性，那么不会保存到ognl上下文中，但会压栈-->
  <s:property/> <!-- 打印栈项元素，即输出zhangSan -->
</s:iterator> <!--弹栈-->


['zhangSan', 'liSi', 'wangWu', 'zhaoLiu']


name='zhaoLiu'

------------------------

循环状态变量
  * status：可以给出任意值，它用来作用在ognl上下文中的key，value为循环状态变量

<s:iterator value="xxx" status="s">
  <s:propert value="#s.count"/> 元素个数，从1
  <s:propert value="#s.index"/> 当前元素的下标，从0
  <s:propert value="#s.even"/> 是否为偶数圈次
  <s:propert value="#s.odd"/> 是否为奇数圈次
  <s:propert value="#s.first"/> 是否为第一圈
  <s:propert value="#s.last"/> 是否为最后一圈
</s:iterator>

=====================================

UI标签中的表单标签

<form>
  <input type=""...>
  <select>
</form>

1. 为什么还需要struts2的表单标签
  优点：
  * 简化代码
  * 自动数据回显（最大的优点）
  * 主题样式（我认为是缺点）

  动态标签：
  * 所有的struts2标签（包含表单标签）都需要被执行
  * struts2的表单标签最终都会被转换成html的表单标签

2. <s:form>标签
  * 对应html中的<form>标签　
  * 属性：action、namespace、theme="simple"
    > action：与<action>的name对应
    > namespace：与<package>的namespace对应
    > method：默认值为post
    > theme：默认值为xhtml

3. <s:textfield>标签
  * 对应<input type="text" .../>
  * 属性：
    > name：指定表单项的名称
    > label：用来为当前的标签项指定<label>元素
    > value：指定表单项的值
4. <s:password>标签
  * 对应<input type="password" .../>
  * 属性
    > name：指定表单项的名称
    > label：用来为当前的标签项指定<label>元素
    > value：指定表单项的值

5. <s:submit>标签
  * 对应<input type="submit" .../>
  * 属性
    > name：指定表单项的名称
    > value：指定表单项的值

<s:form action="" namespace="" theme="simple">
 用户名：<s:textfield name="username"/><br/>
 密　码：<s:password name="password"/><br/>
  <s:submit value="提交"/>
</s:form>

6. 自动回显
  * 其实自动回显就是显示当前栈顶元素
  * 例如；<s:textfield name="username"/>，即显示当前栈的username属性值！
  * 例如；<s:password name="password"/>，即显示当前栈的password属性值！注意：密码框默认不显示，需要使用showPassword="true"

7. 选择性标签
  * 下拉列表
  * 单选按钮：<s:redio>
    > name：不用解释了！
    > list：用来指定选项的！
      * List类型：显示值和实际都相同
      * Map类型：实际值为key，显示值为value
    > <s:radio list="#{'male':'男', 'female':'女'}" name="sex" />
  * 多选按钮：<s:checkboxlist>
    > name
    > list：用来指定选项
      * List类型：显示值和实际都相同
      * Map类型：实现值为key，显示值为value
    > 回显问题：
      * 只有给它数组才能正确回显
      * 在value处给出ognl，把字符串切割成字符串数组！！！
      * <s:checkboxlist value="hobby.split(',')" list="#{'cf':'吃饭', 'sj':'睡觉', 'sw':'上网', 'sk':'上课'}" name="hobby" /><br/>

  * 多选按钮：<s:select>
    > name
    > list：用来指定选项
      * List类型：显示值和实际都相同
      * Map类型：实现值为key，显示值为value
    > headerKey：指定头的实际值
    > headerValue：指定头的显示值
  
<s:form action="" namespace="" theme="simple">
 用户名：<s:textfield name="username"/><br/>
 密　码：<s:password name="password" showPassword="true"/><br/>
 性　别：<s:radio list="#{'male':'男', 'female':'女'}" name="sex" /><br/>
 爱　好：<s:checkboxlist value="hobby.split(',')" list="#{'cf':'吃饭', 'sj':'睡觉', 'sw':'上网', 'sk':'上课'}" name="hobby" /><br/>
学　历：<s:select headerKey="" headerValue="===请选择===" list="{'小学','没上过小学','什么是小学'}" name="education" /><br/>
  <s:submit value="提交"/>
</s:form>


==============================================
==============================================
==============================================

1. 每个请求都有ValueStack、ActionContext（其中一部分不是新的，还是原来的！）
2. Action每个请求创建一个Action对象！
  * Servlet是单例的！Servlet是线程不安全的！
  * 每个请求创建一个Action对象，那么每个线程都有自己的Action对象！

==============================================
==============================================
==============================================

防止表单重复提交

* 在表单页面中使用<s:token/>
* 在<action>中添加一个名为token的拦截器！
  > 这个拦截器在拦截器，它要去哪个结果呢？invalid.token
  > 我们还需要在<action>中添加一个结果，名为invalid.token
  > 错误信息国际化：struts.messages.invalid.token

-----------------

页面：form1.jsp：表单
  * 在表单中给出<s:token/>
  * 打开这个页面，查看源代码！

给出一个Action

1. 在<action>中给出token拦截器！
2. 在<action>中还要配置invalid.token的结果
3. 国际化错误信息：struts.messages.invalid.token

=========================================
=========================================
=========================================
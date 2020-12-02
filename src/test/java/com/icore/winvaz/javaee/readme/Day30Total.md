封装请求参数

1. 属性驱动
  * 在Action中给出与表单字段同名的属性

2. 模型驱动
  * Action实现ModelDriven接口，实现getModel()方法
  * Action给出模型成员，必须手动实例化
  * getModel()方法返回模型成员

3. OGNL格式
  * Action提供模型属性（需要提供getter/setter方法）
  * 在表单字段名称中给出ognl表达式

4. List属性（OGNL）
  * Action提供List属性
  * 在表单字段名称中给出ognl表达式，例如：<input type="text" name="userList[0].username"/>

5. Map属性（OGNL）
  * Action提供Map属性（例如：Map<String,User>）
  * 在表单字段名称中给出ognl表达式，例如：<input type="text" name="userMap['zhangSan'].username"/>

用户1姓名：<input type="text" name="userList[0].username"/><br/>
用户1密码：<input type="password" name="userList[0].password"/><br/>
用户2姓名：<input type="text" name="userList[1].username"/><br/>
用户2密码：<input type="password" name="userList[1].password"/><br/>

张三姓名：<input type="text" name="userMap['zhangSan'].username"/><br/>
张三密码：<input type="password" name="userMap['zhangSan'].password"/><br/>
李四姓名：<input type="text" name="userMap['liSi'].username"/><br/>
李四密码：<input type="password" name="userMap['liSi'].password"/><br/>

==================================
==================================
==================================

类型转换（了解）

1. 什么是类型转换
  * 表单数据都是String[]
  * Action的属性可是多种多样的
  * 表单数据（String[]） --> Action的属性（Object）中，这需要类型转换！

2. Struts2内置的类型转换器
  * int和Integer；
    > String[] --> int或Integer（封装表单数据到Action的属性中）
    > int或Integer --> String（把Action属性显示在页面上）
  * long和Long；
  * float和Float；
  * double和Double；
  * char和Character；
  * boolean和Boolean；
  * Date（格式为：yyyy-MM-dd）

3. 类型转换错误
  * 当struts2希望把表单数据（String[]）转换成Action属性类型时，先查找对应的类型转换器
  * 如果找不到，Struts2将不再进行转换，而是查找原始类型（String[]）的方法完成封装
  * 如果没有找到原始类型的方法，抛出异常（NoSuchMethodException）
  * 封装数据信息到当前Action的输入错误集中（action.addFieldError()），尝试转换到input结果！
  * 如果没有input结果，会出现404！

4. 自定义类型转换器
  * 假如在Action中有一个属性类型为Point
  * 在页面中输入的String[]就需要转换成Point对象
  * 这时就需要有一个类型转换器，可以把String[] --> Point，而且还能把Point --> String


  步骤：
  1). 编写类型转换器
    * 继承org.apache.struts2.util.StrutsTypeConverter

  2). 部署类型转换器（配置它）
    * 配置文件存放在哪：与当前Action同包下
    * 配置文件的名称叫什么：Action类名-conversion.properties
    * 配置文件的内容是什么：key为Action的属性名，value为类型转换器的完整类名

5. 类型转换相关拦截器
  * params：封装参数，把表单数据封装到Action的属性中。
    > 类型转换！如果出现错误，它会把错误保存到OGNL上下文中
  * conversionError：
    > 查看是否存在类型转换错误，如果存在，它会把错误保存到当前Action对象中（action.addFieldError()）
  * workflow：
    > 查看Action中是否存在错误（fieldError，以及actionError）
      * 如果存在错误，转发到input结果
      * 如果要转换的结果不存在，404！！！
    > 没有错误（即没有fieldError也没有actionError），放行，即执行Action的请求处理方法（execute()）

6. 全局类型转换
  * 类型转换分为全局和局部的
  * 刚刚学习的是局部的
    > 一个类型转换器只为一个Action的某个属性使用
  * 全局类型转换
    > 我们写一个类型转换器类，这个类可以被某个类型所使用，无论这个类型它是哪个Action的属性。
    > 例如：AAction的Point point属性，以及BAction的Point point1属性，都想使用PointConverter类型转换器，这时就必须使用全局的！
  * 全局类型转换器与局部的只有配置不同：
    > 文件的位置：src下
    > 文件的名称：xwork-conversion.properties
    > 谁的的内容：属性类名=类型转换器类名

7. 错误信息国际化
  * 当类型转换出错时，我们默认的错误信息是英文的，如果需要使用中文，需要使用国际化技术
  * 步骤：
    > 文件的位置：在Action同包下创建一个文件
    > 文件名称：Action类名.properties
    > 文件的内容：invalid.fieldvalue.属性名=中文

==================================
==================================
==================================

输入校验

1. 什么叫输入校验
  * 就是对表单数据的校验，例如用户名不能为空之类的东西！

2. 输入校验之代码校验
  * 只需要重写父类的validate()方法即可
  * 在validate()方法中对表单数据进行校验，如果出现校验没能没通过，调用addFieldError()添加错误信息即可
  * 需要给出input结果，而且在input结果对应页面中使用<s:fielderror/>输出错误信息

3. 输入校验之相关拦截器
  * params：把表单数据封装到Action的属性中
  * validation：它负责调用Action的validate()方法，其实校验的就是Action的属性
  * workflow：查看当前Action是否存在错误信息，如果有转换到input

4. 一个Action中多个请求处理方式，它的输入校验
  * 我们需要为每个请求处理方法提供独有的校验方法
    > 例如login()方法的校验方法名称为：validateLogin()方法
    > 例如regist()方法的校验方法名称为：validateRegist()方法
  * 每个请求处理方法都应该有自己独有的input结果
    > 在struts.xml中为当前<action>提供两个结果，名称自己定义，例如结果名分别为：loginInput和registInput
    > 在Action类中为每个请求处理方法添加一个 @InputConfig注解，其中有一个名为resultName的属性，用来指定它自己的input结果名称

------------------------

5. xml校验
  * 我们不用再Action类提供validate()或validateXxx()方法了，而是把校验的逻辑提取出来，放到xml文件中

<field name="username">
  <field-validator name="required"><!--校验规则为非空-->
    <message>用户名不能为空！</message>
  </field-validator>
  <field-validator name="stringlength"><!--长度校验-->
    <param name="minLength">3</param>
    <param name="maxLength">10</param>
    <message>用户名长度必须在3~10之间！</message>
  </field-validator>
</field>

  * 编写一个xml文件
    > 位置：当前Action同包下
    > 名称：当前Action类名-validation.xml
    > 内容：

6. 一个Action中多个请求处理方法
  * 位置：当前Action同包下
  * 名称：Action类名-<action>name-validation.xml，
    > 例如：User1Action-user_login-validation.xml、User1Action-user_regist-validation.xml
  * 内容：

==================================
==================================
==================================

国际化

1. 什么是国际化
  * 使用资源文件基本名称+Locale来定位资源文件
    > res_zh_CN.properties
    > res_en_US.properties
  * 全局配置
    > 在struts.xml中指定一个常量：
      * 常量名：struts.custom.i18n.resouces
      * 常量值：基本名称 res
  * 局部配置
    > 包局部：资源文件在当前包以及子包中有效！
      * 资源文件的位置：任意包下，例如：cn下；例如：cn.icore下；例如：cn.icore.action下
      * 资源文件的名称：基本名称必须叫package
      * 资源文件的内容：国际化信息
      * 谁更局部，谁就优先级高
    > Action局部
      * 位置：Action同包下
      * 名称：基本名称为：Action类名
  * 临时的
    > 必须在页面中使用
    > <s:i18n>：指定资源文件基本名称
    > <s:text>：它作为i18n的子标签使用！
    <s:i18n name="基本名称">
      <s:text name="资源key"/>
      <s:text name="资源key"/>
    </s:i18n>

2. 国际化信息的使用情景
  * jsp页面：<s:text>标签
  * Action：ActionSupport的getText()方法
  * 配置文件：<message key="资源key" />

==================================
==================================
==================================

拦截器

1. 拦截器是什么
  * Struts执行的是ActionProxy，即Action的代理对象
  * 代理对象 = Action + 系列拦截器（增强）

2. 已知拦截器
  * params：封装表单数据到Action中
    > 类型转换
  * converterError：把转换错误封装到Action对象中
  * validation：执行校验方法，或执行校验文件
  * workflow：查看Action中是否存在错误，如果存在转发到input结果
 
3. 自定义拦截器
  * 编写拦截器类
    > 实现Interceptor接口
    > 继承AbstractInterceptor类
    > 继承MethodFilterIntercetpor类
  * 配置拦截器
    > 定义拦截器：给出一个名称和一个类名
    > 装配拦截器：把拦截器指定到某个Action上

  * 当<action>装配了拦截器后，那么不会再执行默认拦截器栈！！！


==================================
==================================
==================================

登录案例

1. 页面
  * login.jsp --> 表单页面
    > 显示错误信息
    > 指向UserAction的login()方法
  * index.jsp
    > 添加用户（只有登录用户才能访问） --> addUser()
    > 修改用户（只有登录用户才能访问） --> updateUser()
    > 删除用户（只有登录用户才能访问） --> deleteUser()
    > 查询用户（只有登录用户才能访问） --> findUser()

2. Action
  * UserAction
    > login()
      * 判断用户名为icore表示失败（回到login.jsp）
      * 判断用户名为其他表示成功（去index.jsp）
    > addUser()：输出
    > updateUser()：输出
    > deleteUser()：输出
    > findUser()：输出

3. Interceptor
  * LoginInterceptor
    > 查看session中是否存在User对象
      * 存在：放行
      * 不存在：添加错误信息，返回login结果

4. 输入校验
  * 给登录写输入校验
    > 位置：UserAction同包下
    > 名称：UserAction-user_login-validation.xml
    > 内容：略

5. 国际化
  * 页面中的中文
  * 错误信息，即在xml校验文件，以及Action中的错误信息
  * 我们使用全局

========================================
========================================
========================================
封装请求参数
1. 属性驱动：Action属性名与表单字段名相同
2. 模型驱动：
  * Action实现ModelDriven接口
  * 给出模型对象属性，并手动实例化
  * 在getModel()方法中返回模型对象属性
3. OGNL
  * 在Action中给出模型属性
  * 在表单的name中给出OGNL表达式

4. OGNL之集合属性
  * 在Action中给出模型的集合属性
  * 在表单的name中给出OGNL表达式

类型转换
1. 什么是类型转换：
  * 把表单数据类型（String[]）转换成Action（或Model）的属性类型！
  * 再把Action（或Model）的属性类型转换成String类型
2. Struts2中已经提供的类型转换器
  * 基本类型的类型转换器
  * Date类型的转换器（yyyy-MM-dd）
3. 自定义类型转换器
  * 类型转换器：继承StrutsTypeConverter
  * 部署类型转换器：
    1). 全局部署：
       > 文件路径：src目录下
       > 文件名称：xwork-conversion.properties
       > 文件内容：目标类型名称=类型转换器类名
    2). 局部部署：
       > 文件路径：Action同包下
       > 文件名称：Action类名-conversion.properties
       > 文件内容：属性名=类型转换器类名
  * 国际化错误信息
    1). 文件路径：Action同包
    2). 文件名称：Action类名.properties
    3). 文件内容：invalid.fieldvalue.属性名=中文

输入校验
1. 什么是输入校验：用户名不能为空！用户名长度必须在3~10之间
  * javascript校验：用户检验而已！
  * struts校验：完美！

2. 输入校验分类
  * 代码校验
  * xml校验

3. 代码校验
  * 公共校验（全局校验）
    1). 继承ActionSupport类，并重复validate()方法
    2). 在校验失败后，使用addFieldError()添加错误信息
    3). 在使用addFieldError()添加了错误后，不会执行execute()方法，而是转发到input结果
  * 私有校验（局部校验）
    1). 校验方法名称：前缀validate，后缀请求处理方法，例如请求处理方法名为login，那么校验方法名为validateLogin()
    2). 为每个请求处理方法指定私有的input结果：@InputConfig(resultName="loginInput")

4. xml校验
  * 公共校验
    1). 编写校验文件
    2). 部署校验文件
      > 文件路径：Action同包
      > 文件名称：Action类名-validation.xml
  * 私有校验
    1). 编写校验文件
    2). 部署校验文件
      > 文件路径：Action同包
      > 文件名称：Action类名-Action配置名-validation.xml

国际化
1. 什么是国际化：文字可以根据客户端自动变化为不同的语言
2. Java国际化原理：使用不同的资源文件来存储文字信息，以key/value格式存储文字，不同的资源文件中key是相同的，而value是不同国家的文字
  * 国际化资源文件名：基本名称_locale部分.properties，
    1). 例如基本名称为res，存储中文的资源文件名：res_zh_CN.properties
    2). 例如基本名称为res，存储英文的资源文件名：res_en_US.properties
  * 通过识别浏览器的local来确定资源文件，获取到不同的文字
3. Struts2国际化分类
  * 全局
  * 包
  * Action
  * 临时
4. 国际化应用场景
  * jsp：<s:text name="key"/>
  * Action：getText(key);
  * 配置文件，如xml校验文件中：<message key="key"/>

5. 全局
  * 给出配置文件
  * 配置常量struts.custom.i18n.resources=资源文件基本名称

6. 包
  * 放到指定包下
  * 基本名称为package
7. Action
  * 放到Action同包下
  * 基本名称为Action类名
8. 临时
  * 命名随意
  * 在页面中使用<s:i18n name="资源文件基本名称"><s:text name="key"/></s:i18>

拦截器

1. 什么是拦截器：拦截器是Action的增强部分，拦截器会在Action之前或之后执行！
拦截器与Action的关系，与Filter和Servlet的关系很相似

2. 自定义拦截器
  * 编写拦截器类
  * 部署拦截器
    1). 声明拦截器
    2). 在<action>中指定声明的拦截器
    3). 因为指定了拦截器，所以就不能再执行defaultStack拦截器栈，所以需要再<action>中指定defaultStack
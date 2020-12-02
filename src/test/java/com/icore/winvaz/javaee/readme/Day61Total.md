SpringMVC

3.在web.xml文件中配置DispatcherServlet
<web-app>
    <!-- 配制spring分发器servlet -->
    <servlet>
        <servlet-name>action</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>action</servlet-name>
        <url-pattern>*.action</url-pattern>
    </servlet-mapping>
    
    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
    </welcome-file-list>
</web-app>

-----------------------------------------------
4.创建spring配置文件/WEB-INF/action-servlet.xml
struts2--->struts.xml
hibernate--->hibernate.cfg.xml
spring--->applicationContext.xml
mybatis--->sqlMapConfig.xml
SpringMVC的配置文件名和<servlet-name>action</servlet-name>有关系

---------------------------------------
5.按照mvc框架的规范开发一个自定义的控制器,需要继承AbstractController，重写handleRequestInternal方法

6.在springmvc配置文件中注册自定义的控制器
<!-- 将自定义的控制器注册到springmvc配置文件中，通过spring框架负责创建控制器的实例 -->
<bean id="helloController" name="/hi.do" class="cn.icore.controller.HelloController"></bean>

7.在springmvc配置文件中配置视图解析器
<!-- 配置一个视图解析器，解析ModleAndView -->
<bean id="viewResolver" 
    class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!-- 通过setter方法注入前缀 -->
    <!-- /jsps/success.jsp -->
    <property name="prefix" value="/jsps/"></property>
    <!-- 通过setter方法注入后缀 -->
    <property name="suffix" value=".jsp"></property>
</bean>

8.通过浏览器发送一个http的get方式请求：
http://localhost/springmvc01/hi.do?name=xiaobai

=====================================
=====================================
框架提供的处理器映射组件

1.BeanNameUrlHandlerMapping(默认)----bean名url处理器映射
<!-- 配置bean名url处理器映射(默认) -->
<bean id="handler1" class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
    <property name="order" value="3"/>
</bean>

-------------------------------
2.SimpleUrlHandlerMapping----简单url处理器映射
<!-- 简单url处理器映射 -->
<bean id="handler2" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
    <property name="mappings">
        <props>
            <prop key="/say.do">helloController</prop>
            <prop key="/a.do">helloController</prop>
            <prop key="/b.do">helloController</prop>
        </props>
    </property>
    <property name="order" value="2"/>
</bean>

------------------------------------------
3.ControllerClassNameHandlerMapping----控制器类名处理器映射(控制器的首字母小写)
<!-- 控制器类名处理器映射 -->
<bean id="handler3" 
    class="org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping">
        <property name="order" value="1"/>
</bean>

=============================================
=============================================
框架提供的几种控制器
命令控制器
1.	创建一个控制器，继承AbstractCommandController,重写handle方法
2.	在控制器的构造方法中注册命令类，由框架负责包装一个注册的实体对象
3.	将编写的控制器注册到springmvc的配置文件中
<!-- 注册一个自定义的命令控制器 -->
<bean name="/command.do" class="cn.icore.controller.MyCommandController">
</bean>

-------------------------------------------------
表单控制器
1.	创建一个自定义的表单控制器，继承SimpleFormController,重写doSubmitAction方法(处理POST表单请求)
2.	在控制器中通过构造方法注册命令类
3.	将控制器配置到springmvc的配置文件中
<!-- 注册一自定义的表单控制器 -->
<bean name="/form.do" class="cn.icore.controller.MyFormController">
    <!-- 此处userform为逻辑视图名，视图解析器解析后结果： /jsps/userform.jsp-->
    <property name="formView" value="userform"/>
    <!-- 此处user为逻辑视图名，视图解析器解析后结果： /jsps/user.jsp-->
    <property name="successView" value="user"/>
</bean>
4.	提供一个表单页面userform.jsp和成功页面user.jsp

--------------------------------------------
向导表单控制器
1.	创建一个自定义的向导表单控制器，继承AbstractWizardFormController，并重写processFinish方法和processCancel方法
2.	提供三个表单页面1.jsp 2.jsp 3.jsp
3.	在springmvc配置文件中注册控制器
<!-- 注册一个自定义的向导表单控制器 -->
<bean name="/wizard.do" class="cn.icore.controller.MyWizardFormController">
    <!-- 通过setter方法注入表单页面 -->
    <property name="pages">
        <list>
            <!-- value标签中的值为逻辑视图名 -->
            <value>wizard/1</value>
            <value>wizard/2</value>
            <value>wizard/3</value>
        </list>
    </property>
</bean>

====================================
====================================
使用注解开发SpringMVC
1.	创建一个web项目
2.	导入jar包
3.	在web.xml中配置springmvc框架的前端控制器DispatcherServlet
4.	在config目录下提供springmvc的配置文件springmvc.xml

<!-- 组件扫描 ,由spring框架负责扫描指定包和子包下的类-->
<context:component-scan base-package="cn.icore.controller"/>
<!-- 支持spring的注解：引入spring的注解解析器 -->
<context:annotation-config/>

<!-- 配置一个视图解析器，解析ModleAndView -->
<bean id="viewResolver" 
    class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!-- 通过setter方法注入前缀 -->
    <property name="prefix" value="/jsps/"></property>
    <!-- 通过setter方法注入后缀 -->
    <property name="suffix" value=".jsp"></property>
</bean>

<!-- 配置一个文件上传的对象 -->
<bean id="multipartResolver"
     class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
     
<!-- 注册一个自定义拦截器 -->	 
<mvc:interceptors>
    <mvc:interceptor>
        <!-- 指定当前的拦截器需要拦截的请求url -->
        <mvc:mapping path="/user/*"/>
        <bean class="cn.icore.interceptor.MyInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>

-----------------------------------------
5.	在web.xml中通过一个初始化参数指定springmvc配置文件的位置
<!-- 使用spring框架提供的过滤器解决中文乱码 -->
<web-app>
<filter>
    <filter-name>characterFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!-- 通过初始化参数指定编码集 -->
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>characterFilter</filter-name>
    <url-pattern>*.action</url-pattern>
</filter-mapping>

<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 通过一个初始化参数指定springmvc配置文件的位置 -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc.xml</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>*.action</url-pattern>
</servlet-mapping>
	
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
</web-app>

------------------------------------
6.	创建一个自定义的控制器,在类上加入@Controller @RequestMapping注解，
在控制器类中提供一个方法save ,在方法上使用注解@RequestMapping。
/**
 * 使用注解方式开发一个自定义的控制器，不需要继承父类，也不需要实现接口
 * @author zhaoqx
 *
 */
@Controller
@RequestMapping(value="/user")//请求根路径，可以省略
public class UserController {
	/**
	 * 注册一个属性编辑器，用于将表单提交的数据{yyyy-MM-dd}转换为日期类型
	 */
	@InitBinder
	public void initBinder(HttpServletRequest req,ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@RequestMapping(value="/save.action")//请求子路径
	public String saveUser(HttpServletRequest req,String id,User user){
		//String id = req.getParameter("id");
		System.out.println("saveUser方法执行了。。。" + id);
		System.out.println(user);
		return "success";//success为逻辑视图名
	}
}
7.	通过请求根路径+请求子路径访问指定控制器中的方法
http://localhost/springmvc02/user/save.action

---------------------------------------------
在注解开发中获取表单提交参数方式
1.	使用HttpServletRequest对象的getParamter方法获取
在方法中声明一个Request对象参数，由框架注入
2.	在方法中声明一个和表单提交的参数名相同的参数，由框架直接赋值
3.	直接在方法中声明一个自定义的实体类作为参数,框架负责将提交的表单数据通过实体类的set方法注入
4.	通过@PathVariable注解，从请求url中获取一个指定的参数
/**
 * 浏览器地址栏中输入http: //localhost/springmvc02/user/delete/10.action，
 * 10代表的是请求的参数数据
 */
@RequestMapping(value="/delete/{id}.action")//请求子路径
public String deleteUser(
        @PathVariable
        String id){
    System.out.println(id);
    return "success";//success为逻辑视图名
}

@RequestMapping(value="/update.action")//请求子路径
public String updateUser(
        @RequestParam(value="id")
        String uid){
    System.out.println(uid);
    return "success";//success为逻辑视图名
}

=========================================
在注解开发中传递参数信息
1.	使用Request对象的setAttribute()方法
在方法中声明一个Request对象作为参数
2.	在方法中声明一个框架提供的用于传递数据的对象Model
调用Model对象的addAttribute()方法加入数据
3.	在方法中声明一个Map对象参数
调用Map的put()方法加入数据

=========================================
Springmvc中页面跳转方式
1.	默认方式跳转，直接返回一个逻辑视图名
2.	重定向跳转，执行完方法之后重新执行指定一个控制器中的方法
/**
 * 根据id删除，删除成功后重定向到查询方法
 * @return
 */
@RequestMapping(value="/deleteById.action",method=RequestMethod.GET)
public String deleteById(Integer id){
    //根据id删除成功
    System.out.println("根据id删除逻辑。。。");
    return "redirect:/user/findAll.action";//重新访问查询的方法，地址栏发生变化
    //return "forward:/user/findAll.action";//服务器内部的转发,地址栏不发生变化
}

@RequestMapping(value="/findAll.action")
public String findAll(){
    System.out.println("查询逻辑。。。");
    return "success";
}
3.	服务器内部转发方式的跳转

=====================================
Springmvc中实现文件上传(SpringMVC自带的StandardServletMultipartResolver)
1.	导入Apache提供的用于文件上传的组件jar包
2.	在springmvc的配置文件中配置一个用于文件上传的对象
<!-- 配置一个文件上传的对象 -->
<bean id="multipartResolver"
     <!-- 需要引入commons包 -->
     class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
</bean>

3.	在控制器中提供一个用于文件上传的方法，方法的参数为MultipartFile类型的对象,并且在参数上使用一个@RequestParam，指定表单中提交的用于上传的输入框的名称
/**
	 * 用于文件上传的方法
	 * @throws Exception 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="/upload.action")
	public String upload(HttpServletRequest req,
			@RequestParam(value="resource")
			MultipartFile resource) throws IllegalStateException, Exception{
		String realPath = req.getSession().getServletContext().getRealPath("uploadFiles");
		File target = new File(realPath + "/" + resource.getOriginalFilename());
		resource.transferTo(target);
		System.out.println(resource);
		return "success";
	}
4.	将上传的临时文件移动到指定的目标中：

======================================
使用springmvc中的拦截器
1.	编写一个自定义的拦截器类，实现HandlerInterceptor，实现接口中定义的三个方法
/**
 * 自定义的拦截器
 * @author zhaoqx
 *
 */
public class MyInterceptor implements HandlerInterceptor {
/**
 * 被拦截的Controller的方法执行后执行此方法
 */
public void afterCompletion(HttpServletRequest arg0,
        HttpServletResponse arg1, Object arg2, Exception arg3)
        throws Exception {
    System.out.println("-------afterCompletion-------");
}

/**
 * 被拦截的Controller的方法执行后执行此方法
 */
public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
        Object arg2, ModelAndView arg3) throws Exception {
    System.out.println("-------postHandle-------");
}

/**
 * 在被拦截的目标方法前执行
 */
public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
        Object arg2) throws Exception {
    System.out.println("---------preHandle-------");
    return true;//放行，执行目标方法
}

}

-------------------------------------------------------
2.	在springmvc的配置文件中注册自定义拦截器
<!-- 注册一个自定义拦截器 -->	 
<mvc:interceptors>
    <mvc:interceptor>
        <!-- 指定当前的拦截器需要拦截的请求url -->
        <mvc:mapping path="/user/*"/>
        <bean class="cn.icore.interceptor.MyInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>

==========================================
日期格式化
默认情况下，框架只能包装提交的日期格式为yyyy/MM/dd 的数据,
如果希望自定义日期的类型，需要在控制器中注册属性编辑器
/**
 * 注册一个属性编辑器，用于将表单提交的数据{yyyy-MM-dd}转换为日期类型
 */
@InitBinder
public void initBinder(HttpServletRequest req,ServletRequestDataBinder binder){
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
}

====================================
====================================
====================================
springmvc.xml(基础配置)
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
							http://www.springframework.org/schema/mvc
							http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
							http://www.springframework.org/schema/context
							http://www.springframework.org/schema/context/spring-context-3.0.xsd
							http://www.springframework.org/schema/aop
							http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
							http://www.springframework.org/schema/tx
							http://www.springframework.org/schema/tx/spring-tx-3.0.xsd ">

    <!-- 组件扫描 ,由spring框架负责扫描指定包和子包下的类-->
    <context:component-scan base-package="cn.icore.controller"/>
    <!-- 支持spring的注解：引入spring的注解解析器 -->
    <context:annotation-config/>

    <!-- 配置bean名url处理器映射(默认) -->
    <bean id="handler1" class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
        <property name="order" value="3"/>
    </bean>
    <!-- 简单url处理器映射 -->
    <bean id="handler2" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
        <property name="mappings">
            <props>
                <prop key="/say.do">helloController</prop>
                <prop key="/a.do">helloController</prop>
                <prop key="/b.do">helloController</prop>
            </props>
        </property>
        <property name="order" value="2"/>
    </bean>
    <!-- 控制器类名处理器映射 -->
    <bean id="handler3"
          class="org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping">
        <property name="order" value="1"/>
    </bean>
    <!-- 将自定义的控制器注册到springmvc配置文件中，通过spring框架负责创建控制器的实例 -->
    <bean id="helloController" name="/hi.do" class="cn.icore.controller.HelloController"></bean>
    <!-- 注册一个自定义的命令控制器 -->
    <bean name="/command.do" class="cn.icore.controller.MyCommandController">
    </bean>
    <!-- 注册一自定义的表单控制器 -->
    <bean name="/form.do" class="cn.icore.controller.MyFormController">
        <!-- 此处userform为逻辑视图名，视图解析器解析后结果： /jsps/userform.jsp-->
        <property name="formView" value="userform"/>
        <!-- 此处user为逻辑视图名，视图解析器解析后结果： /jsps/user.jsp-->
        <property name="successView" value="user"/>
    </bean>
    <!-- 注册一个自定义的向导表单控制器 -->
    <bean name="/wizard.do" class="cn.icore.controller.MyWizardFormController">
        <!-- 通过setter方法注入表单页面 -->
        <property name="pages">
            <list>
                <!-- value标签中的值为逻辑视图名 -->
                <value>wizard/1</value>
                <value>wizard/2</value>
                <value>wizard/3</value>
            </list>
        </property>
    </bean>
    <!-- 配置一个视图解析器，解析ModleAndView -->
    <bean id="viewResolver"
          class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 通过setter方法注入前缀 -->
        <property name="prefix" value="/jsps/"></property>
        <!-- 通过setter方法注入后缀 -->
        <property name="suffix" value=".jsp"></property>
    </bean>

    <!-- 配置一个文件上传的对象 -->
    <bean id="multipartResolver"
          class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>

    <!-- 注册一个自定义拦截器 -->
    <mvc:interceptors>
        <mvc:interceptor>
            <!-- 指定当前的拦截器需要拦截的请求url -->
            <mvc:mapping path="/user/*"/>
            <bean class="cn.icore.interceptor.MyInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
</beans>





















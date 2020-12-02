Spring是一个基于JAVA的轻量级J2EE应用框架
基于Java：底层由Java语言实现
轻量级：资源消耗较低，运行速度较快
J2EE框架：服务于整个J2EE开发过程
web程序的分层设计结构
表现层/视图层/Web层	（Servlet/JSP、Struts2、…)
逻辑层/业务层		
数据层/持久层		（JDBC、Hibernate3、…）
(了解)服务层、域模型层
Spring功能非常强大，能够服务于J2EE开发过程中的各个层面

Spring解决方案
表现层：SpringMVC
逻辑层：Bean管理、AOP、事务管理
数据层：Spring JDBCTemplate

Spring核心技术
IoC:控制反转
AOP:面向切面编程

===========================================
===========================================
applicationContext.xml(基础配置)
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
	">
    <!-- Spring控制资源 -->
    <!-- Spring认为自己控制的资源都叫做组件，内部简称为Bean -->
    <!-- bean:指对应的资源 -->
    <!-- bean:class:资源对应的类名 -->
    <!-- bean:id：资源对应的名称 -->
    <bean id="account" class="com.icore.winvaz.domain.model.account.Account"></bean>

    <bean id="bookService" class="cn.icore.di.BookService">
        <!-- 为该Bean提供需要的资源 -->
        <!-- name:资源名 -->
        <!-- value:为其提供的资源值（简单类型） -->
        <property name="name" value="icore"></property>
        <property name="age" value="8"></property>
    </bean>
</beans>

===========================================
IoC(Inversion of Control)控制反转
Spring的出现改变了以往应用程序为中心的局面，一切以Spring为中心，应用程序运行需要使用的资源完全由Spring来提供，Spring握有应用程序运行过程中的所需要使用的全部资源。
--------------------------------------
Spring反向控制应用程序所需要使用的外部资源。
——------------------------------------
优势：所有资源由Spring统一调度，统一管理，使对象的控制更加集中，避免应用程序中出现多余的对象，浪费资源。

Spring核心配置文件
applicationContext.xml
将应用程序需要使用的资源以bean的形式配置到配置文件中，以beans的子元素出现。应用程序通过Spring获取执行程序需要使用的资源。

bean的配置方式
id:Spring管理的bean的名称
class:bean对应的全路径名
<bean id="hello" class="com.icore.winvaz.Hello"></bean>

bean获取方式
Spring核心工厂对象ApplicationContext(应用上下文)
方式一：使用ClassPathXmlApplicationContext对象获取(从类路径(src)下获取applicationContext.xml配置文件
)(推荐)
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
方式二：使用FileSystemXmlApplicationContext对象获取(从应用路径(工程)下获取applicationContext.xml配置文件
)
ApplicationContext ctx = new FileSystemXmlApplicationContext("applicationContext.xml");
使用应用上下文获取bean对象
ctx.getBean("beanName")

DI(Dependency Injection)依赖注入
应用程序执行需要使用外部资源，由于资源已经完全由Spring进行统一管理，因此应用程序需要依赖Spring为其提供资源，提供资源的过程称为注入。
依赖注入指应用程序依赖Spring为其提供运行时所需的资源。

设置应用程序运行需要使用的资源
在Hello中设置程序运行需要使用的资源
private String msg ; 
提供Spring为其注入资源的入口
使用标准封装，提供对应的setter方法
public void setMsg(String msg) {
    this.msg = msg;
}
Spring核心配置文件中提供对应的资源数据
为bean指定需要的资源属性
<bean id="hello" class="com.icore.winvaz.Hello">
    <property name="msg" value="由spring提供的资源"/>
</bean>

应用程序运行需要使用相应的资源，由主动发起调用为其设置资源数据，转换为由Spring创建实例，并为其提供资源
应用程序被动等待Spring为其注入资源

IoC与DI是同一个问题不同参照物的不同描述方式
针对Spring而言：Spring反向控制应用程序需要使用的外部资源
针对应用程序而言：应用程序依赖Spring为其注入运行所需的资源
半杯水问题
乐观
悲观

BeanFactory接口
BeanFactory接口也可以用于创建Bean实例
步骤
获取资源对象
Resource res = new ClassPathResource("applicationContext.xml");
使用资源对象创建BeanFactory对象
BeanFactory bf = new XmlBeanFactory(res);
使用BeanFactory对象创建Bean实例
IHello ser = (IHelloe ) bf.getBean(“hello");

问题：BeanFactory与ApplicationContext的区别
加载方式：
BeanFactory：延迟加载，使用bean时才进行初始化
ApplicationContext：加载配置文件时，初始化bean对象
功能：
ApplicationContext提供更多的功能
国际化处理
事件传递
Bean自动装配
各种不同应用层的Context实现
实际开发中，优先选择ApplicationContext对象

========================================
========================================
========================================
Bean配置(XML)

Bean的初始化方式
Spring提供三种方式初始化Bean对象
类构造器初始化(主流)
Spring读取加载的Bean的class，调用对应class的无参构造方法，实例化对象
如果没有提供无参构造方法，Spring将无法初始化Bean实例
applicationContext.xml中配置对应的Bean
<bean id="bean1" class="cn.icore.spring.beanInit.Bean1"></bean>
获取Bean对象使用ApplicationContext对象进行
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
Bean1 bean1 = (Bean1) ctx.getBean(“bean1");

静态工厂方法初始化(非主流)
实例工厂方法初始化(非主流)

Bean的作用域
Spring中的Bean默认创造出的对象是同一个，也就是单例的，通过修改bean元素的scope属性可以控制创建出的实例的作用域
<bean id="beanScope" scope="singleton" class="cn.icore.spring.scope.Bean1"></bean>
Bean的5种作用域
singleton(默认)(常用)
创建出的实例为单例模式，在IoC容器中唯一
prototype(常用)
创建出的实例为非单例模式，每次获取bean得到新对象
request(用于web开发)
创建的实例绑定request对象，获取的bean作为request的属性
session (用于web开发)
创建的实例绑定session对象，获取的bean作为session的属性
globalSession (用于分布式web开发)
创建的实例绑定全局session对象，用于多个服务器间进行数据共享，获取的bean作为globalSession的属性

Bean的生命周期
Spring管理Bean实例的创建，通过配置的形式，设置两个回调方法，供开发者进行实例创建与销毁阶段的操作
<bean 
	id="lifeBean" 
	init-method="init" 
	destroy-method="destroy" 
	class="cn.icore.spring.lifecycle.LifeBean">
</bean>
init-method：设置bean初始化对象的回调方法
destroy-method ：设置bean销毁对象的回调方法

对应的Bean实体类中提供对应的回调方法
public void init(){
	System.out.println("LifeBean init...");
}
public void destory(){
	System.out.println("LifeBean destory...");
}
回调方法的作用：
针对当前实例创建与销毁时，进行资源的初始化与重置，或进行资源的打开与关闭等操作。

由于Bean的生命周期由Spring管理，Spring在没有关闭其IoC容器前，将不销毁所管理的Bean，因此必须将其手动关闭才可以销毁Spring所控制的Bean实例。
ApplicationContext接口并不提供关闭操作，使用其子类对象ClassPathXmlApplicationContext进行关闭操作。
注意：销毁操作只能用于单例的对象，即scope属性为singletion的对象

=========================================
Bean的属性注入
Spring支持使用两种方式为Bean注入属性
构造器注入Bean属性
1.提供对应参数的构造器
// 待注入的属性
private String name;
private Integer age;
// 对应的构造器
public Hello(String name, Integer age) {
    this.name = name;
    this.age = age;
}
2.配置
<bean id="hello" class="com.icore.winvaz.Hello">
    <!-- 使用构造器注入属性 index:构造器参数的索引位置，type:构造器中参数的类型，value:具体值 -->
    <constructor-arg index="0" type="java.lang.String" value="zhangsan"/>
    <constructor-arg index="1" type="java.lang.Integer" value="2020"/>
</bean>

===================================
setter注入(重点)
Spring使用属性对应的setter方法为属性注入资源
1.为属性提供对应的setter方法
// 提供待注入资源属性对应名称的setter方法
private String name;
public void setName(String name) {
    this.name = name;
}
private Integer age;
public void setAge(String age) {
    this.age = age;
}
2.配置
<bean id="hello" class="com.icore.winvaz.Hello">
    <!-- 使用setter注入属性 name:属性名，value:属性值 -->
    <property name="name" value="lisi"/>
    <property name="age" value="2021"/>
</bean>

setter注入Bean属性(重点)
Spring注入引用类型的对象，必须保障该对象以Bean形式存在于IoC容器，受Spring控制
1.为属性提供对应的setter方法
// 提供待注入资源属性对应名称的setter方法
private Name name;
public void setName(Name name) {
    this.name = name;
}
2.配置
<!-- 被注入的对象必须以bean的形式存在-->
<bean id="name" class="com.icore.winvaz.Name></bean>
<bean id="hello" class="com.icore.winvaz.Hello">
    <!-- 使用setter注入属性，name:属性名，ref:引用其他Bean的名称-->
    <property name="name" ref="name"/>
</bean>

==========================================
==========================================
==========================================
Bean配置(注解)
@Component
功能：指定对应的类为Spring控制的bean
格式：定义在类的上方，可以为类指定bean名称
定义UserDAOImpl类为Spring控制的bean，未指定名称
@Component
public class UserServiceImpl implements UserService{}
定义UserDAOImpl类为Spring控制的bean，名称”userDAO”
@Component("userService)
public class UserServiceImpl implements UserService{}
bean的名称可以定义多个，如@Component(“u1,u2,u3”)

Spring使用注解定义bean，IoC容器并不知道哪些类被声明了注解，因此需要类路径下每一个类进行扫描，并针对声明了注解的类进行加载。Spring通过XML声明的格式，定义加入扫描的路径(多配置)
<context:component-scan base-package="com.winvaz.restapi"/>

@Component衍生注解
@Repository 用于对数据层实现类进行标注
@Service 用于对业务逻辑层实现类进行标注
@Controller 用于对控制层实现类进行标注
目前上述三种注解与@Component功能完全相同，仅仅是名称上的区别

=============================================
使用注解定义属性注入
@Autowired
功能：标注类的成员变量为自动装配注入属性
格式：定义在成员变量的上方
参数：required
可选值：true(默认值)/false
作用：标识该属性是否必须注入，如果未对其指定注入的值，则系统抛出异常
注入不同类别的属性需要使用不同的注解
简单类型：@Value
对象类型；@Qualifier
---------------------------------------
@Value
功能：为属性注入的简单类型的值
格式：定义在成员变量的上方
注意：该注解与@Autowired配合使用
@@Autowired
@Value("hello")
private String name;

说明：无论何种类型，全部以字符串的形式传递值
----------------------------------------
@Qualifier
功能：为属性注入的Bean类型的值
格式：定义在成员变量的上方
注意：该注解与@Autowired配合使用
@@Autowired
@Qualifier("userService")
private UserService userService;

@Component("userService")
public class UserServiceImpl implements UserService {}

@Qualifier必须给出注入的bean的名称
如果对应的bean不存在，抛出异常，注入失败
为@Autowired指定参数required=false，避免注入失败时候抛出异常

=======================================
自动装配类型识别(引用类型)
注入属性类型为class
如果类名对应的类只有一个，注入成功
如果类名对应的类有多个，注入失败
注入属性类型为interface
如果不存在对应接口的实现类，注入失败
如果对应接口的实现类为一个，注入成功
如果对应接口的实现类为多个，注入失败
如果对应接口的实现类指定有bean名称，则按照按照属性定义的名称进行匹配
如果存在对应名称的自动装配bean，注入成功
如果存在对应名称的自动装配bean，注入失败

================================================
使用注解@Resource为属性注入
Spring提供对JSR-250中定义@Resource标准注解的支持。
@Resource与@Autowired功能非常相似，用于bean的自动装配，格式略有区别
@Resource(name="userService")
private UserService userService;

@Autowired与@Resource的区别
@Resource有两个属性是比较重要的，分是name和type，
Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。
所以如果使用name属性，则使用byName的自动注入策略，
而使用type属性时则使用byType自动注入策略。
如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
1.@Autowired按byType自动注入，@Resource默认按byName自动注入

==============================================
注解定义Bean的生命周期
@PostConstruct 
功能：为当前Bean指定init-method参数
格式：定义在成员方法的上方，兼容静态方法
@PostConstruct
public void init() {
    System.out.println("init...");
}
@PreDestroy 
功能：为当前Bean指定destory-method参数
格式：定义在成员方法的上方，兼容静态方法
注意：要求当前类被注册为Bean，否则无效果
@PreDestroy
public void destory() {
    System.out.println("destory...");
}

==================================
注解定义Bean的作用范围
@Scope
功能：为当前Bean指定scope参数，默认singleton
格式：定义在类的上方
@Component
@Scope("prototype")
public class Hello {
}

========================
Spring3.0注解定义Bean
@Bean
功能：指定当前方法返回的对象为指定名称的Bean
格式：定义在方法的上方
注意：要求方法返回指定为Bean的对象
通过配置的形式Bean已经存在，但是应用上下文还不知道此处定义了Bean，
因此需要对拥有上述配置的类进行说明，使JVM在加载该类时，认定该类是一个用于配置Bean的类，
其中的配置是用于加载Bean的配置，使用@Configuration注解完成。

@Configuration(服务于自动扫描)
功能：指定当前类为配置类，用于加载Bean定义
格式：定义在类的上方
注意：该类要被设置在注解自动扫描对应的包下

@Configuration
public class JavaConfig {
    @Bean
    public AnnotationBean getBean() {
        return new AnnotationBean();
    }
}

-----------------------------------------
Spring3.0提供的两个快速添加Bean的注解配置，基于JavaConfig技术完成。
使用@Configuration配置的Bean如果没有被设置为自动扫描，
还可以使用AnnotationConfigApplicationContext对象手动加载

AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.register(JavaConfig.class);
ctx.refresh();
System.out.println(ctx.getBean("xx"));

register(Class)：将带有@Bean的类加载到配置中
refresh()：刷新配置信息，否则加载的Bean无法被识别

================================================
================================================
================================================
JUnit整合Spring
设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
设置读取Spring的配置文件路径
@ContextConfiguration(locations="classpath:/applicationContext.xml")


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/applicationContext.xml")
public class App {
    @Autowired
    private AnnotationBean annotationBean;
    
    @Test
    public void test() {
        annotationBean.save();
    }
}





















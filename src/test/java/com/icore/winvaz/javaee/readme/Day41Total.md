AOP
AOP(Aspect Oriented Programing)面向切面编程
AOP是一种编程范式，隶属于软工范畴，指导开发者如何组织程序结构
AOP弥补了OOP的不足，基于OOP基础之上进行横向开发
AOP联盟

AOP范式编程研究的不是层与层之间的关系，也不是每层内部如何开发，AOP主要关注同一层面的各个不同功能模块之间的共性功能。
AOP时代的到来，使开发模块化的思想进行了进一步的提升，从刀耕火种的纯手工开发阶段，向半自动化/自动化阶段进行了一个大的突破。IT研发朝着“插拔式组件体系结构”又近了一步。

基本概念(重点)
连接点(Joinpoint)：类中所有的方法
程序运行过程中，JVM负责程序运行。执行到某个方法时，JVM能识别当前执行的是哪个方法。
这些定义在类中的方法，每个具有独立的功能，在AOP中，将这些具有特定功能的方法称为连接点。
连接点指类中的方法
-------------------------------------------

切入点(Pointcut)：具有共性功能的方法(静)-->连接的的动态运行表示格式
类中的方法具有很多，某些方法具有一些共同的流程，例如数据库连接的获取与关闭，事务的开启与提交，等等。
将这些公共的特性抽取出来，抽取完毕后，原始方法中就缺少了这些被抽取的代码。
在实际运行时，缺少这些代码是无法完成原始的功能的。这些被抽取了公共功能的方法称为切入点。
切入点一定是连接点，连接点不一定是切入点
切入点指被抽取了共性功能的方法
-------------------------------------------

通知(Advice)：共性功能的模块化(Java中就是方法)
切入点对应的方法的共性功能被抽取后，组成独立代码逻辑，被封装在某个类的某个方法中，
在被抽取了共性功能的方法被执行时，这些代码逻辑还要加入原始方法的执行，这些被抽取出来组成独立代码逻辑的共性功能称为通知。
共性功能被抽取后，可能来自于切入点对应的方法中的任何位置，因此通知不仅描述共性的代码逻辑，还是描述被抽取时从何处抽取。
例如切入点代码逻辑的前面、中间还是后面，被抽取的代码在切入点中的具体位置，称为通知类别。
------------------------------------------

引入(Introduction)
通知仅表示切入点被抽取的代码逻辑，对于切入点所在的类，如果存在有共性的成员变量或者成员方法，通知将无法进行描述。
AOP提供引入机制，将共性功能的成员进行加入。
引入机制可以为类添加额外的成员变量或者成员方法
引入机制是在编译期或类加载期完成的
------------------------------------------

目标对象(Target Object)：被创建代理的对象
切入点对应的共性功能被抽取后创建独立的通知完成共性功能，在程序运行时，动态的为类对象执行切入点方法时动态加入被抽取的共性功能，
此时需要使用代理的模式完成。此时被代理创建的对象，称为目标对象。
目标对象指包含切入点的类对象
--------------------------------------------------

AOP代理(AOP Proxy)：程序动态运行过程中，基于目标对象可执行原始功能的代理对象的过程。
切入点所在的类对象执行切入点方法时，需要将原始的共性功能(通知)加入，此时通过代理的形式创建类对象，并完成共性功能(通知)的加入，上述过程称为AOP代理。
AOP代理的意义是将被抽取共性功能的类对象创建出，同时将共性功能(通知)加入，完成原始的完整操作的执行过程。
--------------------------------------------------

织入(Weaving)：将被挖取的功能动态添加到原始对象操作中的动态过程
通过AOP代理，完成了切入点与通知的融合，并组成了完整的代码逻辑，将通知加入到切入点对应位置的动作称为织入。
织入是一个动态过程，不对应任何代码，可以理解为动态的运行过程。
织入可以在三个阶段进行，编译时，类加载时，运行时。Spring采用的是运行时织入。
-------------------------------------------------
切面(Aspect)：共性功能对应的执行位置
切面是一个设计概念，指切入点与通知的匹配模式，换句话说指被抽取了共性功能的方法(切入点)与被抽取了共性功能(通知)对应的绑定关系。
程序设计时，可以设置多个切面，用来描述切入点与通知之间的关系。


========================================
========================================
AOP工作流程
开发阶段(开发者完成)
将共性功能独立开发出来，制作成通知
将非共性功能开发到对应的目标对象类中，并制作成切入点方法
在配置文件中，声明切入点与通知间的关系，即切面
运行阶段(AOP完成)
JVM读取配置文件中的信息，监控切入点方法的执行
一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，
根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行。

-------------------------------------
AspectJ是一个面向切面的框架，它扩展了Java语言。
AspectJ定义了AOP语法,所以它有一个专门的编译器用来生成遵守Java字节编码规范的Class文件。
Spring2.0提供对AspectJ的支持
早期的SpringAOP开发十分繁琐，Spring2.x推荐使用AspectJ进行SpringAOP开发

=======================================
AOP开发——目标对象制作
制作目标对象类，制作连接点方法，并将其配置成Spring所控制的Bean
@Component
public class UserServiceImpl implements UserService {
    public void add() {
        System.out.println("add().....");
    }
    
    public void update() {
        System.out.println("update().....");
    }
    
    public void delete() {
        System.out.println("delete().....");
    }
    
}
--------------------------------
AOP开发——通知制作
制作通知对应的类，并制作出被抽取的通用功能，将其封装到方法中，并将该类配置成Spring所控制的Bean
@Component
public class UserAdvice {
    public void method() {
        System.out.println("method().....");
    }
}
------------------------------
AOP开发——开启AOP配置
在XML配置文件中，开启AOP命名空间
上面两个类用xml配置的形式
aop:config  AOP配置
aop:aspect 设置AOP切面，可以配置多个
ref:设置AOP切面对应的类，必须是一个Spring的Bean
aop:before 设置AOP的通知类别
pointcut:设置切入点，监控对应的方法
method:设置织入的代码逻辑对应的内容(方法)

<!-- bean配置 -->
<bean id="userServiceImpl" class="com.icore.winvaz.UserServiceImpl"></bean>
<bean id="userAdvice" class="com.icore.winvaz.UserAdvice"></bean>

<!-- 配置AOP -->
<!-- aop:config:开启AOP配置 -->
<aop:config>
    <!-- 配置切面 -->
    <!-- aop:aspect:配置切面 -->
    <!-- aop:aspect:ref:指定具体的切面类 -->
    <aop:aspect ref="userAdvice">
        <!-- aop:before:通知类别，也就是共性功能的挖取位置 -->
        <!-- aop:before:method:切面中的功能功能方法 -->
        <!-- pointcut:切入点 -->
        <!-- <aop:before method="共性功能方法名" pointcut="执行到UserDaoImpl类中的save方法时"/> -->
        <aop:before method="method" pointcut="execution(void cn.icore.winvaz.UserServiceImpl.add())"/>
    </aop:aspect>
</aop:config>

========================================
========================================
AOP切入点表达式
AOP切入点表达式支持多种形式的定义规则
execution:匹配方法的执行(常用)
execution(public * *(..))
within:匹配包或子包中的方法(了解)
within(cn.icore.aop..*)
this:匹配实现接口的代理对象中的方法(了解)
this(cn.icore.aop.user.UserDAO)
target:匹配实现接口的目标对象中的方法(了解)
target(cn.icore.aop.user.UserDAO)
args:匹配参数格式符合标准的方法(了解)
args(int,int)

=================================
execution
匹配方法的执行(常用)
格式：execution(方法格式表达式)
例一：匹配所有指定返回值类型的方法
void *(..)	int *(..)		double *(..)
例二：匹配指定包或子包中类的方法
*..*()      cn.icore.winvaz.aop.user.dao.*.*(..)      cn..dao.*.*(..)
例三：匹配指定类或接口的方法
**..UserImpl.*(..)       **..*Impl.*(..)      **..*DAO.*(..)
例四：匹配指定方法名或部分匹配方法名的方法
*..*.add(..)	 *..*.*e*(..)	 *..*.get*(..)
例五：匹配所有指定参数个数或类型的方法
**..*.*(int,int)	 **..*.*(*,*)	 **..*.*(*,..)

================================
AOP切入点配置
AOP配置中，由于切入点使用量很大，Spring提供了AOP切入点配置的引用格式
以下三种格式是等效的
格式一：直接配置切入点
<aop:before method="method" pointcut="execution(* *..*.*(..))"

格式二：先配置切入点元素，然后配置参照切入点
<aop:pointcut id="pt" expression="execution(* *..*.*(..))"></aop:pointcut>
<aop:before method="method" pointcut-ref="pt"></aop:before>

注意：切入点通常配置在范围内的最上方，方便开发者查阅
格式三：配置公共的切入点
<aop:config>
    <aop:pointcut id="pt1" expression="execution(* *..*.*(..))"></aop:pointcut>
    <aop:pointcut id="pt2" expression="execution(* *..*.*(..))"></aop:pointcut>
</aop:config>

================================================
AOP通知类型
AOP的通知类型共5种
before:前置通知(应用：各种校验)
在方法执行前执行，如果其中抛出异常
after:后通知(应用：清理现场)
方法执行完毕后执行，无论方法中是否出现异常
afterReturning:返回后通知(应用：常规数据处理)
方法正常返回后执行，如果方法中抛出异常，无法执行
afterThrowing:抛出异常后通知(应用：包装异常信息)
方法抛出异常后执行，如果方法没有抛出异常，无法执行
around:环绕通知(应用：十分强大，可以做任何事情)
方法执行前后分别执行，可以阻止方法的执行

--------------------------
AOP通知类型配置
使用aop命名空间，配置5种通知类型
<aop:before method="before" pointcut-ref="pt"/>
<aop:after method="after" pointcut-ref="pt"/>
<aop:after-throwing method="afterThrowing" pointcut-ref="pt"/>
<aop:after-returning method="afterReturning" pointcut-ref="pt"/>
<aop:around method="around" pointcut-ref="pt"/>
常用属性
pointcut：配置切入点表达式
pointcut-ref：配置切入点引用对象
method：配置切入点执行的通知方法

注意：环绕通知必须声明对原始方法的执行
public void around(PorceedingJoinPoint pjp) throw Throable {
    System.out.println("around before.....");
    pjp.proceed();
    System.out.println("around after.....");
}

----------------------------------
AOP通知的相对顺序
AOP的通知类型存在5种，其中分为在方法前执行与方法后执行
方法执行前运行
before
around(before)
方法执行后运行
after
afterReturning
around(after)
其中afterThrowing需要抛出异常时运行，与上述操作没有过多的交集，不在此讨论

AOP通知顺序总结：
方法执行前执行：
无论是单独配置还是整体配置，配置在上方的先执行
方法执行后执行：
无论是单独配置还是整体配置，配置在上方的先执行
不同通知类型执行的顺序以配置顺序为准
实际开发应用：
配置后，调整配置顺序，直到达到自己的目标即可


============================================
AOP通知获取参数
在通知中如果使用方法调用时传递的参数，可以为通知添加JoinPoint参数，该参数位于形参的第一位，使用对应的getArgs()获取对应的值
public void before(JoinPoint jp) {
    Object[] obj = jp.getArgs();
}
------------------------------
AOP通知还可以使用切入点表达式指定通知参数名
<aop:before method="before" pointcut="args(a, b)"/>
<aop:before method="before" pointcut="execution(* *..*.*(..)); args(a, b)"/>
参数的名称为通知中用于获取参数值的形参名称
public void before(int a, int b) {
    System.out.println(a);
    System.out.println(b);
}
上述格式中变量名分别定义在配置文件与类中，如果修改一方，另一方不修改，就会出现错误
------------------------------------
AOP通知获取返回值
AOP通知中有两种通知可以获取方法的返回值
afterReturning
around
afterReturning获取返回值
为返回后通知添加参数returning，参数值必须与方法中定义的保存返回值的形参名称完全相同，否则报错
<aop:after-returning
    method="after-returning"
    pointcut="execution(* *..*.*(..))"
    returning="retValue"
</aop:after-returning>
public void afterReturning(Object retValue) {
    System.out.println(retValue);
}
-----------------------------------------
around获取返回值
around通知类型中，存在有对原始方法的调用，因此参数与返回值获取相对简单，直接通过调用原始方法得到返回值
public Object around(ProceedingJoinPoint pjp) throw Throwable {
    Object obj = pjp.proceed();
    System.out.println(obj);
    return obj
}
环绕通知可以依赖对原始方法手工调用的特性，阻止原始方法的执行，甚至可以修改原始方法的返回值

注意：如果原始方法返回值为void，获取的返回值统一为null。

=======================================
AOP参数与返回值的应用
AOP编程中，由于可以拦截方法的参数与返回值，在编程时，可以根据业务需要对参数与返回值进行修改。
用户界面输入的用户名最后包含有空格，使用AOP可以将多余的空格处理掉后，再传入逻辑层或数据层
用户界面收集的分页页码值并不是数据层最终的使用值，可以通过AOP将该值处理后再传入数据层

===================================
===================================
===================================
注解开发AOP(@AspectJ支持)(重点)
@AspectJ提供使用注解开发SpringAOP
开启Spring对@AspectJ注解开发的支持
<aop:aspectj-autoproxy/>

使用注解开发AOP要保障所有的相关类必须受到Spring控制，因此在进行注解开发AOP之前，首先将所有相关类配置成Bean
配置式
注解式

-------------------------
注解开发AOP——配置aspect
AOP开发是基于切面的，因此首先配置切面类
使用@Aspect将Bean设置为切面
@Aspect
public class HelloAdvice {}

使用注解配置@Aspect与下列效果等同，注解免去了配置时指定对应Bean的环节
<aop:config>
    <aop:aspect ref="helloAdvice">
    </aop:aspect>
</aop:config>

------------------------------
注解开发AOP——配置通知类型
通知类型通过注解的形式完成
使用@Before将指定方法设置为前置通知
@Before(value="executrion(* *..*.*(..)))
public void before() {
    System.out.println("xxx");
}
使用注解配置@Before与下列效果等同，注解免去了配置时指定方法名的环节
<aop:before method="before" pointcut="execution(* *..*.*(..))></aop:before>

--------------------------------------
注解开发AOP——切入点
只用注解形式开发公共切入点
使用@Pointcut配置切入点，@Pointcut声明在无返回值方法上方，方法名即切入点名称，方法最好声明为private
@Pointcut(value="execution(* *..*.*(..)))
private void pt() {}

使用注解配置@Pointcut与下列效果等同，注解免去了配置时指定切入点名称的环节，所配置的方法名即为切入点名
<aop:pointcut id="pt" expression="exection(* *..*.*(..))></aop:pointcut>

使用自定义的切入点格式发生了较大的变化
@Before("HelloAdvice.pt()")
public void before() {}

---------------------------------------
注解开发AOP——各种通知类型
前置通知
@Before(value="execution(* *..*.*(..))")
后置通知
@After(value="execution(* *..*.*(..))")
抛出异常通知
@AfterThrowing(value="execution(* *..*.*(..))",throwing=“ex")
返回后通知
@AfterReturning(value="execution(* *..*.*(..))",returning=“ret")
环绕通知
@Around(value="execution(* *..*.*(..))")

-------------------------------------------
注解开发AOP的通知顺序
使用注解开发AOP，由于不存在配置的顺序问题，因此，AOP注解开发的顺序比较特殊
around before
before
around after
after
afterReturning
注意：Spring2.x注解开发AOP通知顺序在同一个类中与注解顺序和通知方法名定义有关，控制较为繁琐
实际开发以最终运行顺序为准


=============================================
=============================================
=============================================
代理对象创建模式
代理对象创建模式根据被代理对象的特点划分为两种常见模式
接口实现的对象——JDK动态代理
非接口实现的对象——cglib动态代理
看com.icore.winvaz.javase.basic.designpattern.proxypattern包下的代理模式
-------------------------
JDK动态代理
JDK1.3引入动态代理机制
JDK动态代理是针对内存中的Class对象，使用类加载器，动态为目标对象的实现接口创建代理类

Spring在运行期，生成动态代理对象，不需要特殊的编译器
Spring AOP的底层就是通过JDK动态代理或cglib动态代理技术，为目标Bean执行横向织入
若目标对象实现了若干接口，spring使用JDK的java.lang.reflect.Proxy类代理。
若目标对象没有实现任何接口，spring使用cglib库生成目标对象的子类。
程序中应优先对接口创建代理，便于程序解耦维护
AOP核心——代理模式

==========================================
==========================================
==========================================
DAO支持抽象类













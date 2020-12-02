Spring事物管理
J2EE体系进行分层开发，事务处理位于业务层，Spring提供了分层设计业务层的事务处理解决方案
Spring事务管理主要包括3个接口
PlatformTransactionManager
事务管理器
TransactionDefinition
事务定义信息
TransactionStatus
事务具体运行状态
------------------------------------
PlatformTransactionManager
PlatformTransactionManager接口提供事务操作的方法，包含有3个具体的操作
获取事务状态信息
TransactionStatus getTransaction(TransactionDefinition definition) 
提交事务
void commit(TransactionStatus status) 
回滚事务
void rollback(TransactionStatus status) 

--------------------------------------
常用平台事务管理器
DataSourceTransactionManager
使用Spring JDBC或iBatis 进行持久化数据时使用
HibernateTransactionManager	
使用Hibernate3.0版本进行持久化数据时使用
JpaTransactionManager
使用JPA进行持久化时使用
JdoTransactionManager	
当持久化机制是Jdo时使用
JtaTransactionManager	
使用JTA实现管理事务，在一个事务跨越多个资源时必须使用

-------------------------------
TransactionDefinition
TransactionDefinition接口提供事务相关信息的获取方法，包含有5个具体的操作
获取事务对象名称
String getName()
获取事务隔离级
int getIsolationLevel()
获取事务传播行为
int getPropagationBehavior()
获取事务超时时间
int getTimeout()
获取事务是否只读
boolean isReadOnly()

------------------------------------
TransactionStatus
TransactionStatus接口描述了某个时间点上事务对象的状态信息，包含有6个具体的操作
刷新事务
void flush() 
获取是否是否存在存储点
boolean hasSavepoint() 
获取事务是否完成
boolean isCompleted() 
获取事务是否为新的事务
boolean isNewTransaction() 
获取事务是否回滚		
boolean isRollbackOnly()
设置事务回滚
void setRollbackOnly()

----------------------------------
事务隔离级
事务隔离级反映事务提交并发访问时的处理态度
ISOLATION_DEFAULT
默认级别，归属下列某一种
ISOLATION_READ_UNCOMMITTED 
可以读取未提交数据
ISOLATION_READ_COMMITTED 
只能读取已提交数据，解决脏读问题(Oracle默认级别)
ISOLATION_REPEATABLE_READ 
是否读取其他事务提交修改后的数据，解决不可重复读问题(MySQL默认级别)
ISOLATION_SERIALIZABLE 
是否读取其他事务提交添加后的数据，解决幻影读问题

-------------------------------------
Spring事务管理方式
Spring提供两种事务管理范式
编程式事务管理
通过代码进行，利用TransactionTemplate，开发中几乎不使用
声明式事务管理
通过配置文件对事务进行管理，基于AOP思想完成，开发主流
XML配置格式
注解格式

--------------------------------------
编程式事务管理(了解)
编程式事务管理是通过代码编程的形式来控制事务

在没有为业务逻辑层添加事务前，调用数据层的操作，每个操作是一个独立的事务，事务针对不是业务，而是数据层操作
使用TransactionTemplate管理业务层事务
为业务逻辑层实现注入TransactionTemplate对象
private TransactionTemplate transactionTemplate;
public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
    this.transactionTemplate = transactionTemplate;
}

配置事务管理模板
事务管理模板是工具类，实现事务管理功能需要使用具体事务管理器进行真正的事务管理。事务管理器的选择需要对应当前使用技术进行选择(PPT_4)
<bean id="accountService" class="cn.icore.tx.AccountService">
    <property name="accountDao" ref="accountDao"/>
    <property name="transactionTemplate" ref="transactionTemplate"></property>
</bean>
	
<!-- transactionTemplate -->
<bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
    <property name="transactionManager" ref="transactionManager"/>
</bean>
	
<!-- transactionManager -->
<!-- 为什么transactionManager需要使用dataSource -->
<!-- 事务管理器使用的数据库连接信息如果与被管理的操作使用的数据库连接信息不同，则事务管理器无法对被管理的操作进行统一的事务管理 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
	
<bean id="accountDao" class="cn.icore.tx.AccountDao">
    <property name="dataSource" ref="dataSource"/>
</bean>

<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/springdb"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>

在业务层，将归属同一个事务的操作封装到事务管理模板对应的操作中，实现同一事务管理操作
public void transfer(final String out,final String in,final Double money){
    TransactionCallback<Object> tc = new TransactionCallbackWithoutResult() {
        //此方法内书写的内容将归属于同一个事务
        protected void doInTransactionWithoutResult(TransactionStatus ts) {
            //调用转出
            accountDao.outMoney(out, money);
            //调用转入
            accountDao.inMoney(in, money);
            int i  =1 /0;
        }
    };
    transactionTemplate.execute(tc);
}
编程式事务管理过于繁琐，实际开发中没有实用性

========================================
========================================
========================================
声明式事务管理(重点)
编程式事务管理将数据层提交事务的代码加入到逻辑层，与Spring无侵入式编程的主思想有冲突，实际开发过程中，往往采用声明式事务管理形式
通过编程式事务管理的代码不难看出，在业务逻辑层对应的业务上添加某些代码即可完成整体事务管理的操作，
使用SpringAOP的思想，将公共的代码加入后，即可完成对应的工作，这就是声明式事务管理的核心机制。

使用tx命名空间，完成声明式事务
在配置文件中，引入tx与aop命名空间

使用tx命名空间定义事务管理的AOP
tx:advice:定义事务管理的通知(环绕通知)
transaction-manager:声明事务管理的实现类
tx:method:定义参与事务管理的方法

<!-- 配置事务管理的AOP -->
<!-- 通知类要配置，Spring已经提供了 -->
<!-- 该通知是一个事务管理的通知，Spring使用tx命名空间定义该通知 -->
<!-- tx:advice:声明spring的事务管理的通知类 -->
<!-- transaction-manager:指定事务管理器 -->
<tx:advice id="txAdvice" transaction-manager="txManager">
    <tx:attributes>
        <!-- name:绑定事务的方法名，支持通配符 -->
        <!-- timeout:事务超时时间 ,-1永不超时-->
        <!-- read-only:只读/读写事务 -->
        <!-- isolation:事务隔离级 -->
        <!-- no-rollback-for:遇到指定异常不回滚 -->
        <!-- rollback-for:遇指定异常强制回滚 -->
        <!-- propagation:定义事物的传播属性 -->
        <tx:method 
            name="transfer" 
            timeout="-1" 
            read-only="false" 
            isolation="DEFAULT" 
            no-rollback-for="java.lang.ArithmeticException"
            rollback-for=""
            propagation="REQUIRED"
            />
        <tx:method name="get*" read-only="true"/>
        <tx:method name="save*"/>
        <tx:method name="*List"/>
    </tx:attributes>
</tx:advice>
	
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
	
<!-- AOP配置 -->
<aop:config>
    <!-- aop:aspect用于完成用户自定义的切面开发，如果使用系统已经制作完毕的切面，不适用该选项 -->
    <aop:advisor advice-ref="txAdvice" pointcut="execution(* cn.icore.tx.AccountService.transfer(..))"/>
</aop:config>
	
<bean id="accountService" class="cn.icore.tx.AccountService">
    <property name="accountDao" ref="accountDao"/>
</bean>

<bean id="accountDao" class="cn.icore.tx.AccountDao">
    <property name="dataSource" ref="dataSource"/>
</bean>

<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/springdb"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>

=====================================
=====================================
=====================================
事务传播属性
事务传播属性反映了当前操作对事务的需求
传播属性    事务管理者   事务协调者
              T1         T1(加入)
REQUIRED  
              无          T(新建)
        ---------------------   
              T1         T2(新建)
REQUIRES_NEW
              无          T(新建)
        -------------------------
              T1         T1(加入)
SUPPORTS
              无         无(没有)
        ----------------------
              T1        无
NOT_SUPPORTED
              无        无
        -----------------------
              T1        T1
MANDATORY
              无       ERROR(抛异常)
        ---------------------    
             T1       ERROR(抛异常)
NEVER
             无         无(正常)
        --------------------------
NESTED  设置savePoint,一旦事务回滚，事务将回滚到savePoint处，交由客户响应提交/回滚
------------------------------------------------
REQUIRED:支持当前事务，如果当前有事务，那么加入事务，如果当前没有事务则新建一个(默认情况)
REQUIRES_NEW：新建事物，如果当前存在事物，把当前事物挂起，新建的事物和被挂起的事物没有任何关系，是两个独立的事物。
SUPPORTS：支持当前事物，如果当前没有事物，则以非事物运行。
MANDATORY：支持当前事物，如果当前没有事物，就抛出异常。
NOT_SUPPORTED：以非事物方式运行，如果当前存在事物，则把当前事物挂起。
NEVER：以非事物方式运行，如果当前存在事物，则抛出异常。
NESTED：如果一个活动的事物存在，则运行在一个嵌套的事务中，如果没有活动事物，则按REQUIRED属性执行。

==========================================
注解式事务管理(重点)
Spring支持注解式事务管理
@Transactional
功能：为类/接口或其中的方法添加事务管理
位置：类/接口定义上方或者方法定义上方
@Transactional
public class UserServiceImpl implements UserService {
    @Transactional
    public void add() {}
}

注解配置事务需要在配置文件中开启注解驱动
<!-- 开启注解驱动 -->
<tx-annotation-driven/>
--------------------------------------
使用注解配置事务管理的核心是使用AOP思想进行事务功能的添加，因此事务管理器必须进行配置
使用注解进行事务管理时，由于事务管理器属于默认注入的Bean，因此其名称必须按照要求进行配置
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/springdb"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>

如果事务管理器使用其他名称，必须手工为注解驱动指定对应的事务管理器
<!-- 开启注解式事务驱动 -->
<tx:annotation-driven transaction-manager="txManager"/>

------------------------------------------


















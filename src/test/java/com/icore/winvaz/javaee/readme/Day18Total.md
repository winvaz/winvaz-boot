1. 批处理
  * 客户端可以把一堆的sql语句一次性发送给服务器，服务器执行所有sql语句。
  * 批处理的实现步骤
    > Statement：可以理解为，它内部有一个集合，用来装载sql语句
      * void addBatch(String sql)：添加sql语句到集合内！（没有执行！！！）
      * int[] executeBatch()：执行集合中所有sql语句！
      * void clearBatch()：清空集合中的sql语句！
    > PreparedStatement：（模板不能改变！批处理中执行的是相同的sql语句，只是参数不同！）
      * void addBatch()：没有参数！在每个addBatch()之前需要给参数赋值！
        > PreparedStatement内部的集合，装载的是N组参数！

   * mysql默认是关闭批处理的！！！
    MySQL的批处理也需要通过参数(链接在URL之后)来打开：rewriteBatchedStatements=true

==========================================================

事务

1. 什么是事务
  * 需要把多个操作绑定成一个事务（要么完全成功，要么完全失败）

2. 事务的特性
  * A 原子性：事务中的多条语句是不可再分隔的！要么完全成功，要么完全失败！
  * C 一致性（核心）：在事务执行前后，要保证业务数据前后一致！
  * I 隔离性：多个事务并发执行，需要隔离开来，保存不相互影响！
  * D 持久性：数据只要保存到了数据库中，数据就是持久的！就算数据库马上崩溃，也要在重启后有能力恢复数据。


===============================

MySQL中事务操作
  * 事务有两个边界！
    > 事务开始
    > 事务结束
      * 成功
      * 失败
  * start transaction：开启事务
    > N条语句
  * commit：成功！把N条语句提交了！
  * rollback：失败！把N条语句回滚！回到事务开始时的状态！

===============================

JDBC中事务操作
  * 在JDBC中，所有与事务相关的操作，都是Connection的方法完成的！
  * 开启事务：con.setAutoCommit(false)
    > 默认情况下：所有的语句都是自动提交的，即一条语句一个事务！
    > 调用setAutoCommit(false)，表示设置为手动提交！需要我们自己来写代码完成提交！
  * 结束事务：
    > con.commit()：提交事务
    > con.rollback()：回滚事务

事务的模板代码：
  * try项：开启事务
  * try底：提交事务
  * catch：回滚事务
  
  Connection con = null;
  PrearedStatement pstmt = null;
  try {
      con = ...
      con.setAutoCommit(false);//开始事物

      pstmt = ...
      
      ...

      con.commit();//try块最后一句完成提交！
  } catch(SQLException e) {
      con.rollback();//在抛出异常后，完成回滚！
  } finally {
    ...
  }

===============================

保存点（了解）

1. jdbc3.0后，可以在事务中设置保存点
  > 可以回滚到保存点！不用再回滚到事务开始时的状态了！
  > 如果回滚到保存点，事务并未结束！
2. 保存点在Spring框架中大量应用，用来处理事务传播！

开始事务
a条语句
设置保存点1
b条语句
设置保存点2
c条语句
回滚到保存点1（回滚了b和c，但没有回滚a！a又没回滚，又没提交）
需要回滚或提交

3. 保存点相关API
  * 设置当前数据库是否支持保存点：boolean b = con.getMetaData().supportsSavepoints();
  * 设置保存点：Savepoint sp = con.setSavepoint();
  * 回滚到保存点：con.rollback(sp);

===============================

隔离级别

1. 事务的并发读问题
  * 脏读：读取到另一个事务未提交数据！！！
  * 不可重复读：对同一数据两次读取前后不一致！！！因为在第二次读取之前另一个事务修改数据！
  * 幻读（虚读）：对数据的统计两次不一致！！！因为在第二次统计时，另一个事务插入了一条记录！
  
  并发读问题：
  1.脏读：读取未提交回滚数据。事务1更新了记录，但没有提交，事务2读取了更新后的行，然后事务T1回滚，现在T2读取无效。
  2.不可重复读取(在一个事务里面读取了两次某个数据，读出来的数据不一致)：事务1读取记录时，事务2更新了记录并提交，事务1再次读取时可以看到事务2修改后的记录；
  3.幻读(读取已提交数据)。事务1读取记录时事务2增加了记录并提交，事务1再次读取时可以看到事务2新增的记录；
  
  不可重复读与幻读的区别
  不可重复读的重点是修改，比如多次读取一条记录发现其中某些列的值被修改。
  幻读的重点是新增或删除，比如读取一条记录发现记录增多或减少了。

2. 事务的隔离级别(处理并发读问题)
  * SERIALIZABLE（串行化）
    > 所有事务要排除执行！！！
    > 最安全、无性能可言！！！
  * REPEATABLE READ （可重复读）（mysql默认使用该级别）
    > 处理了不可重复读问题，但没有处理幻读问题！！！
    > 比串行化好一点！！！
  * READ COMMITTED （读已提交数据）（orcale默认使用该级别）
    > 处理了脏读问题，但没有处理不可重复读和幻读！！！
    > 比可重复读好一点
  * READ UNCOMMITTED （读未提交数据）
    > 什么都没有处理，性能最佳！！！

3. MySQL操作隔离级别
  * SELECT @@tx_isolation：查看当前使用的隔离级别
  * set transaction isolationlevel [4先1]：设置隔离级别

4. JDBC操作隔离级别
  * con.setTransactionIsolation(int level)：设置数据库隔离级别
    	Connection.TRANSACTION_READ_UNCOMMITTED；
	Connection.TRANSACTION_READ_COMMITTED；
	Connection.TRANSACTION_REPEATABLE_READ；
	Connection.TRANSACTION_SERIALIZABLE。
  * int getTransactionIsolation()：获取数据库当前的隔离级别

====================================================
====================================================
====================================================

连接池

1. 连接每次创建和销毁都是比较浪费资源的！
2. 连接的管理交给连接池管理！！！
  > 应用程序不再管理连接，把管理连接的事情交给连接池！
  > 应用程序在使用完连接之后，不应该销毁，而是把连接归还给连接池（重用）！
3. 连接池管理连接
  > 初始化大小
  > 最大连接数
  > 最大等待时间
  > 最大空闲连接
  > 最小空闲连接

=========================

开源免费连接池

* sun提供了一个连接池接口：javax.sql.DataSource
  > Connection getConnection()
* 连接池要创建连接，所以需要四大连接参数！（必须要配置的）数据库(驱动类、URL、用户名、密码)
* 连接池还需要配置池参数（不设置它也有默认值）
* 连接池的连接都和使用四大参数创建的连接有点不同：调用它的close()方法并不会销毁连接，而是把连接对象归还给池！
  Connection con = dataSource.getConnection();//从连接池得到一个连接对象
  con.close();//没有销毁连接，只是把连接归还给池！

----------------------------------

对象的增强
1. 继承
  * 被增强对象是固定的，不能变
  * 增强也是固定的，不能变
2. 装饰者模式
  * 被增强的对象是可以变化的
  * 增强的内容是固定的
3. 动态代理（javaweb最后一天才学习）

class 咖啡类{}
class 加糖咖啡 extends 咖啡类{}
class 加奶咖啡 extends 咖啡类{}
class 加盐咖啡 extends 咖啡类{}

new 加盐咖啡((new 加奶咖啡(new 加糖咖啡())));


真言：是你还有你，一切拜托你
is a --> 是一个，继承
  class A extends B {}
has a --> 有一个，A类有一个成员的类型是B类型，那么A类有一个B类型
  class A {private B b;}
use a --> 用一个
  class A {public void fun(B b) {new B()} }

class 加糖咖啡 extends 咖啡 {//是你
  private 咖啡 kf;//有你
  public 加糖咖啡(咖啡 kf) {
    this.kf = kf;
  }

  //实现方法：一切拜托你
  public void fun1() {
    this.kf.fun1();
  }

  //实现方法：一切拜托你
  public void fun2() {
    this.kf.fun2();
  }

  public void fun3() {
    // 不再调用kf的方法，而是自己来完成！
  }
}

----------------------------------

1. DBCP

* jar包：
  > commons-dbcp.jar
  > commons-pool.jar

* 核心类：
  > BasicDataSource，实现了javax.sql.DataSource接口
   * 四大连接参数
   * 池参数
   * 获取连接对象（归还需要con.close()）

----------------------------------

2. C3P0

* jar包
  > c3p0.jar
  > mchange-commons.jar
* 核心类：
  > ComboPooledDataSource，实现了javax.sql.DataSource
   * 四大连接参数
   * 池参数
   * 获取连接对象（归还需要con.close()）
  > 还可以使用配置文件
   * 配置文件的名称：c3p0-config.xml
   * 配置文件的位置：src下

=========================

在Tomcat上配置资源（JNDI）

1. JNDI
  java的名称和目录接口
  * 在服务器上配置资源（连接池），资源需要有一个存放的目录和名称

TomCat上配置的
<Context ...>
  ...
  <Resource name="bean/MyBeanFactory"  --> 资源的目录和名称
            auth="Container"  -->固定值，不用理睬
            type="com.mycompany.MyBean"  -->资源的类型
            factory="org.apache.naming.factory.BeanFactory" -->固定的，不用理睬
            bar="23"/>  -->对资源的配置
  ...
</Context>


-------------------------------------------
<Context>
  <Resource name="jdbc/mysqldatasource"  --> 资源的目录和名称
	    auth="Container" --> 固定值，不用理睬
            type="com.mchange.v2.c3p0.ComboPooledDataSource" --> 资源的类型
            factory="org.apache.naming.factory.BeanFactory" --> 固定的，不用理睬
            classDriver="com.mysql.jdbc.Driver" --> 对资源的配置
	    jdbcUrl="jdbc:mysql://localhost:3306/mydb1"
	    user="root"
	    password="123"
	    />
</Context>


--------------------------------------------------------
TomCat上配置的例子
// Obtain our environment naming context
Context initCtx = new InitialContext();
Context envCtx = (Context) initCtx.lookup("java:comp/env");

// Look up our data source
DataSource ds = (DataSource)
  envCtx.lookup("jdbc/EmployeeDB");

// Allocate and use a connection from the pool
Connection conn = ds.getConnection();
... use this connection to access the database ...
conn.close();


------------------------------------------------------------
  * 可以通过一组JNDI API来获取服务器上的资源！
//Context initCtx = new InitialContext();//创建jndi上下文对象
//Context envCtx = (Context) initCtx.lookup("java:comp/env");//通过查询java:comp/env，得到的还是一个上下文

//DataSource ds = (DataSource)envCtx.lookup("jdbc/mysqldatasource");
//DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/mysqldatasource");
DataSource ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/mysqldatasource");

====================================================
====================================================
====================================================

ThreadLocal

1. 线程本地变量
  * ThreadLocal：每个线程都可以向其内保存数据，谁保存了，谁就可以获取到！没有保存，是获取不到的！
    > 1号线程，是否可以获取2线程保存的数据？不能！！！

2. ThreadLocal内部是一个Map
  * key：线程对象
  * value：保存的数据


  A线程中的代码：map.put(Thread.currentThread(), "hello");  map.get(Thread.currentThread());
  B线程中的代码：map.put(Thread.currentThread(), "world");  map.get(Thread.currentThread());

3. ThreadLocal的API
  * void set(Object) --> 保存数据，底层在操作map，使用Thread.currentThread()
  * Object get() --> 获取数据，底层在操作map，使用Thread.currentThread()
  * void remove() --> 移除数据，底层在操作map，使用Thread.currentThread()

====================================================
====================================================
====================================================

BaseServlet

1. 一个功能一个Servlet
   * Servlet会太多了！
   * 希望一个模块一个Servlet，例如我们希望把LoginServlet、RegistServlet，合并到UserServlet中！
   * LoginServlet
     > doPost()
   * RegistServlet
     > doPost()
   * UserServlet
     > login()
     > regist()

====================================================
====================================================
====================================================

dbutils

1. 什么是dbutils
  * 它是jdbc小工具，用来简化jdbc的代码
  * commons组件

2. 使用
  * jar：commons-dbutils.jar

3. 核心类
  * DBUtils --> close()
  * QueryRunner
    > int update()：insert、update、delete
    > T query()：select
    > int[] batch()：批处理


4. 步骤
  * 创建QueryRunner
    > QueryRunner qr = new QueryRunner();
      * 使用这个对象时，需要给它提供Connection
    > QueryRunner qr = new QueryRunner(dataSource);//需要连接池对象
      * 使用这个对象时，不用给Connection
  * 调用update()：
    > qr.update(connection, sql, array)
      * connection：连接对象
      * sql：sql模板，带有问号
      * array：对应sql模板中问号的值！
    > qr.update(sql, array)
      * sql：sql模板，带有问号
      * array：对应sql模板中问号的值！

  * 调用batch()
    > batch(Connection con, String sql, Object[][] params);
    > batch(String sql, Object[][] params);

  * 调用query()
    > query(Connection con, String sql, ResultSetHandler rh, Object[] params);
   
  * 6个结果集处理器
    要求：列名与javabean属性名必须相同！！！
    > BeanHandler：把单行结果集转换成一个javabean对象
    > BeanListHandler：把多行结果集转换成List<javabean>对象，即多个javabean对象

    > MapHandler：把单行结果集转换成一个Map对象，key为列名, value为列值
    > MapListHandler：把多行结果集转换成一个List<Map>对象，即多个Map对象

    > ColumnListHandler：把多行单列结果集转换成List<Object>，对应多行单列的查询
    > ScalarHandler：把单行单列的结果集转换成Object！！！

==============================================
==============================================
事务

什么是事务?
转账：
1. 给张三账户减1000元
2. 给李四账户加1000元

当给张三账户减1000元后，抛出了异常！这会怎么样呢？我相信从此之后，张三再也不敢转账了。

使用事务就可以处理这一问题：把多个对数据库的操作绑定成一个事务，要么都成功，要么都失败！

==============

事物的特性：ACID

* 原子性：事务中所有操作是不可再分割的原子单位。事务中所有操作要么全部执行成功，要么全部执行失败。
* 一致性：事务执行后，数据库状态与其它业务规则保持一致。如转账业务，无论事务执行成功与否，参与转账的两个账号余额之和应该是不变的。
* 隔离性：隔离性是指在并发操作中，不同事务之间应该隔离开来，使每个并发中的事务不会相互干扰。
* 持久性：一旦事务提交成功，事务中所有的数据操作都必须被持久化到数据库中，即使提交事务后，数据库马上崩溃，在数据库重启时，也必须能保证通过某种机制恢复数据。

==============

MySQL操作事务

1. 开始事务：start transaction
2. 结束事务：commit或rollback

==============

JDBC事务

1. 开始事务：con.setAutoCommit(false);
2. 结束事务；con.commit()或con.rollback();

==============

保存点

保存点的是可以回滚到事务中的某个位置，而不是回滚整个事务。
回滚到保存点不会结束事务。
设置保存点：Savepoint sp = con.setSavepoint();
回滚到保存点：con.rollback(sp);

==============

事务隔离级别

* 脏读：读到未提交
* 不可重复读：两次读取不一致，读取到另一事务修改的记录
* 幻读：两次读取不一致，读取到另一事务插入的记录

--------------

四大隔离级别
* SERIALIZABLE（串行化）：对同一数据的访问是串行的，即非并发的，所以不会出现任何并发问题。易出现死锁，效率太低！不可用！
* REPEATABLE READ（可重复读）：防止了脏读、不可重复读，但没有防止幻读
* READ COMMITTED（读已提交）：防止了脏读，但没有防止不可重复读，以及幻读
* READ UNCOMMITTED（读未提交）：可能出现所有并发问题，效率最高，但不可用！

MySQL默认事务隔离级别为：REPEATABLE READ
Oracle默认事务隔离级别为：READ COMMITTED

--------------

MySQL设置事务隔离级别
* 查看：select @@tx_isolation
* 设置：set transaction isolation level 四选一

JDBC设置事务隔离级别
con.setTransactionIsolation(四选一)

===============

数据库连接池

作用：使用池来管理连接的生命周期，节省资源，提高性能。
java提供的连接池接口：javax.sql.DataSource，连接池厂商的连接池类需要实现这一接口。

-------------

DBCP

jar：commons-pool.jar、commons-dbcp.jar

BasicDataSource ds = new BasicDataSource();
ds.setUsername("root");
ds.setPassword("123");
ds.setUrl("jdbc:mysql://localhost:3306/mydb1");
ds.setDriverClassName("com.mysql.jdbc.Driver");
 		
ds.setMaxActive(20); 
ds.setMaxIdle(10); 
ds.setInitialSize(10) ;
ds.setMinIdle(2) ;
ds.setMaxWait(1000) ;
		
Connection con = ds.getConnection();

-------------

C3P0

jar：c3p0-0.9.2-pre1.jar、c3p0-oracle-thin-extras-0.9.2-pre1.jar、mchange-commons-0.2.jar

ComboPooledDataSource ds = new ComboPooledDataSource();
ds.setJdbcUrl("jdbc:mysql://localhost:3306/mydb1");
ds.setUser("root");
ds.setPassword("123");
ds.setDriverClass("com.mysql.jdbc.Driver");
 		
ds.setAcquireIncrement(5) ;
ds.setInitialPoolSize(20) ;
ds.setMinPoolSize(2) ;
ds.setMaxPoolSize(50) ;
		
Connection con = ds.getConnection();

-------------

C3P0配置文件

1. 通过默认配置初始化连接池
ComboPooledDataSource ds = new ComboPooledDataSource();
Connection con = ds.getConnection();

<default-config>
  <property name="xxx">XXX</property>
</defualt-config>

2. 通过命名配置初始化连接池
ComboPooledDataSource ds = new ComboPooledDataSource("oracle-config");
Connection con = ds.getConnection();

<named-config name="orcale-config">
  <property name="xxx">XXX</property>
</named-config>

==================

Tomcat配置连接池
在server.xml中，或在conf/catalina/localhost/下创建xml文件

<Context>  
  <Resource name="myc3p0" 
			type="com.mchange.v2.c3p0.ComboPooledDataSource"
			factory="org.apache.naming.factory.BeanFactory"
			user="root" 
			password="123" 
			classDriver="com.mysql.jdbc.Driver"    
			jdbcUrl="jdbc:mysql://127.0.0.1/mydb1"
			maxPoolSize="20"
			minPoolSize ="5"
			initialPoolSize="10"
			acquireIncrement="2"/>
</Context>  

-------------

获取Tomcat资源
Context cxt = new InitialContext(); 
DataSource ds = (DataSource)cxt.lookup("java:/comp/env/myc3p0");
Connection con = ds.getConnection();

==================

修改JdbcUtils

public class JdbcUtils {
	private static DataSource dataSource = new ComboPooledDataSource();

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

==================

DBUtils

jar：commons-dbutils.jar
核心类：QueryRunner、ResultSetHandler

QueryRunner方法：
* update()：DDL、DML
* query()：DQL
* batch()：批处理

-------------

增、删、改

public void fun1() throws SQLException {
	QueryRunner qr = new QueryRunner();
	String sql = "insert into user values(?,?,?)";
	qr.update(JdbcUtils.getConnection(), sql, "u1", "zhangSan", "123");
}

-------------

查

DataSource ds = JdbcUtils.getDataSource();
QueryRunner qr = new QueryRunner(ds);
String sql = "select * from tab_student";

// 把结果集转换成Bean
Student stu = qr.query(sql, new BeanHandler<Student>(Student.class));

// 把结果集转换成Bean的List
List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class));

// 把结果集转换成Map
Map<String,Object> map = qr.query(sql, new MapHandler());

// 把结果集转换成List<Map>
List<Map<String,Object>> list = qr.query(sql, new MapListHandler() );

// 把结果集转换成一列的List
List<Object> list = qr.query(sql, new ColumnListHandler("name")) ;

// 把结果转换成单行单列的值
Number number = (Number)qr.query(sql, new ScalarHandler());

================

批处理

	DataSource ds = JdbcUtils.getDataSource();
	QueryRunner qr = new QueryRunner(ds);
	String sql = "insert into tab_student values(?,?,?,?)";
	Object[][] params = new Object[10][]; //表示 要插入10行记录
	for(int i = 0; i < params.length; i++) {
		params[i] = new Object[]{"S_300" + i, "name" + i, 30 + i, i%2==0?"男":"女"};
	}
	qr.batch (sql, params);

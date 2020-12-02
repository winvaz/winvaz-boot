1. service层事务
  * 分层：web层、业务层、持久层
  * 事务：把多个操作绑定在一起，形成一个事务，要么完全成功、要么完全失败

2. 目标
  * 封装如下方法：
    > JdbcUtils.beginTransaction()：开启事务
    > JdbcUtils.commitTransaction()：提交事务
    > JdbcUtils.rollbackTransaction()：回滚事务

  class servicexxx {
    public void fun1() {
      try {
        JdbcUtils.beginTransaction();//开始事务
	...dao操作1：JdbcUtils.getConnection，它是在dao中的
	...dao操作2：JdbcUtils.getConnection，它是在dao中的
	...dao操作N：JdbcUtils.getConnection，它是在dao中的
	JdbcUtils.commitTransaction();//提交事务
      } catch(xxx) {
        JdbcUtils.rollbackTransaction();//回滚事务
      }
    }
  }

-----------------------------------------------------

最初处理事务的方式，回到dao

1. 写一个案例，使用dbutils来完成事务操作
  * 得到Connection对象！
  * 调用Connection的setAutoCommit(false)来开启事务
    * 给张三减去1000
    * 给李四加上1000
  * 调用Connection的commit()来提交事务
  * 在catch中调用Connection的rollback()方法来回滚事务

-----------------------------------------------------

dao中完成事务是不正确的！

1. 转账方法不应该在dao中，因为它是一个业务操作
2. 把转账方法移动到service中，又发现了问题！
  * 某些类只能在dao中出现，而不应该在service层出现
    > 与jdbc相关的所有类


-----------------------------------------------------

在JdbcUtils类中封装事务相关方法

  class servicexxx {
    public void fun1() {
      try {
        JdbcUtils.beginTransaction();//开始事务 --> 调用con.setAutoCommit(false);
	...操作1 --> 使用con：JdbcUtils.getConnection()来获取
	...操作2 --> 使用con
	...操作N --> 使用con
	JdbcUtils.commitTransaction();//提交事务 --> 调用con.commit();
      } catch(xxx) {
        JdbcUtils.rollbackTransaction();//回滚事务 --> 调用con.rollback();
      }
    }
  }
  上面所有使用con的地址，必须同一个con！！！

JdbcUtils.getConnection()
beginTransaction()
commitTransaction()
roolbackTransaction()

以上四个方法中使用的必须是同一个connection

-----------------------------------------------------

JdbcUtils类中相关事务方法的问题

1. 已经开启了事务，就不能重复开启
2. 没有开启事务，不能提交或回滚
3. 提交或回滚，应该把con设置为null，表示事务已经结束！
4. 事务结束时，是否应该关闭？

------------

5. 因为JdbcUtils支持两种方式获取Connection
  * 事务连接：在提交或回滚时，能够关闭！！！
  * 非事务连接：不由JdbcUtils来完成关闭操作！！！

  * 在dao中的方法，不知道当前调用是否在事务中！
    > 在事务中：dao不能关闭连接！
    > 不在事务中：dao需要关闭连接！

  * dao不管关闭连接的事情，因为它不知道方法是否在事务中被调用！
    > Jdbcutils一定知道当前调用是否在事务中
    > 让dao把连接给Jdbcutils来处理！

------------

6. JdbcUtils类是否支持多线程并发访问
  * A线程开启事务：con!=null
  * B线程开启事务：con因为不等于null，所以抛出异常。'

  处理多线程并发访问问题
  * 只要让每个线程都有自己的事务专用连接即可！
  * ThreadLocal
    > 需要修改所有与Connection相关的方法

------------

7. 因为QueryRunner的代码总要处理获取连接，使用，再释放连接，所以我们写出一个QueryRunner的子类
  重复其中的所有方法，把获取连接，和释放连接，添加进去！


  class TxQueryRunner extends QueryRunner {
    重写没有Connection的update、query、batch三个方法
    * 在这些方法中使用JdbcUtils来获取Connection对象
    * 在调用父类的有Connection的方法
    * 在使用JdbcUtils来释放连接
  }


===========================

  class servicexxx {
    public void fun1() {
      try {
        JdbcUtils.beginTransaction();//开始事务
	...dao操作1：使用TxQueryRunner#没有Connection的方法
	...dao操作2：使用TxQueryRunner#没有Connection的方法
	...dao操作N：使用TxQueryRunner#没有Connection的方法
	JdbcUtils.commitTransaction();//提交事务
      } catch(xxx) {
        JdbcUtils.rollbackTransaction();//回滚事务
      }
    }
  }

==============================================
==============================================
==============================================
c3p0-config.xml

<?xml version="1.0" encoding="UTF-8" ?>
<c3p0-config>
	<default-config> 
		<property name="jdbcUrl">jdbc:mysql://localhost:3306/mydb1</property>
		<property name="driverClass">com.mysql.jdbc.Driver</property>
		<property name="user">root</property>
		<property name="password">123</property>
		
		<property name="acquireIncrement">3</property>
		<property name="initialPoolSize">10</property>
		<property name="minPoolSize">2</property>
		<property name="maxPoolSize">10</property>
	</default-config>
	
	<named-config name="mysqlconfig"> 
		<property name="jdbcUrl">jdbc:mysql://localhost:3306/mydb1</property>
		<property name="driverClass">com.mysql.jdbc.Driver</property>
		<property name="user">root</property>
		<property name="password">123</property>
		
		<property name="acquireIncrement">3</property>
		<property name="initialPoolSize">10</property>
		<property name="minPoolSize">2</property>
		<property name="maxPoolSize">10</property>
	</named-config>
</c3p0-config>

==============================================
==============================================
==============================================
package cn.icore.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * v2.0
 * @author cuixifan
 *
 */
/*
 * 本类内部使用c3p0
 * 需要配置一个ComboPooledDataSource对象
 * 还需要配置c3p0-config.xml
 */
public class JdbcUtils {
	private static DataSource dataSource = new ComboPooledDataSource();
	// 处理多线程并发访问问题
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();//默认为null，事务专用连接
	
	/**
	 * 返回连接池对象
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 返回连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		/*
		 * 判断是否开启了事务，如果开启了，那么事务专用连接就不是null了，那么返回专用连接
		 */
		if(con != null) return con;
		return dataSource.getConnection();
	}
	
	/**
	 * 开启事务
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		// 从tl中获取自己线程的专用连接
		Connection con = tl.get();
		
		if(con != null) throw new SQLException("事务已经开启，不要重复开启！");
		con = getConnection();//给con赋值
		con.setAutoCommit(false);//开启事务
		
		// 把处理后的Connection保存到tl中，这才能说明它是当前线程的事务专用连接 
		tl.set(con);
	}
	
	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException {
		// 从tl中获取本线程专用连接
		Connection con = tl.get();
		
		if(con == null) throw new SQLException("事务没有开启，不能提交事务！");
		con.commit();
		con.close();
		
		// 把本线程专用连接，从tl中移除！！！
		tl.remove();
	}
	
	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		// 从tl中获取本线程专用连接
		Connection con = tl.get();
		
		if(con == null) throw new SQLException("事务没有开启，不能回滚事务！");
		con.rollback();
		con.close();
		
		// 把本线程专用连接，从tl中移除！！！
		tl.remove();
	}
	
	/**
	 * 关闭非事务连接
	 * @param con
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		Connection con = tl.get();
		/*
		 * 判断连接是否为事务连接，如果不是，那么关闭！
		 * con == null：说明没有事务！因为con是事专用连接，只有它不等于null时才会有事务存在
		 * con != connection：在有事务时，如果传递的connection不是事务专用连接，那么就可以关闭！
		 */
		if(con == null || con != connection) {
			connection.close();
		}
	}
}

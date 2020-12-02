jdbc按排

1. 第一天：基础知识
2. 第二天：事务、工具类
3. 第三天：service事务，小练习
4. 第四天：分页、监听器

============================================

1. 什么是jdbc？
　　JDBC（Java DataBase Connectivity）就是Java数据库连接

2. jdbc原理？
　　* jdbc是一组接口，连接数据库的规范！
　　* 由各个数据库厂商提供对这一组接口的实现！
　　* jdbc驱动：各个数据库厂商对jdbc的实现！（没有它就无法连接数据库！）

============================================

第一例

1. 导驱动jar包
  * mysql-connector-java-5.1.13-bin.jar（connectorJ）

2. jdbc api
  * 核心类：
    > DriverManager：你可以使用它得到连接对象！
    > Connection：它是连接对象（得到它就表示与数据库连接成功了）
    > Statement：它可以用来向数据库发送（执行）sql语句，可以通过Connection来创建它！
      * 增、删、改（更新操作）：返回int类型
      * 查：结果集（二维的表格）
    > ResultSet：表示结果集（二维的表格），它有一套api，用来操作其内的数据！
  * 第一步
    > 得到Connection：需要四大参数！
      * 指定驱动：driverClassName，驱动类名称，它在驱动包中！上网收！
        > 每个驱动包中，必须有一个类实现了java.sql.Driver接口
	> mssql的驱动包中提供的驱动类名称叫：com.mysql.jdbc.Driver
      * url：数据库服务器在哪里
        > 第一段:第二段:第三段
	  * 第一段：jdbc
	  * 第二段：厂商名称，例如：mysql
	  * 第三段：由厂商自己提供
	  * 完整的：jdbc:mysql://localhost:3306/mydb1?xxx=xxx&yyy=yyy
      * username：root
      * password：123
    > 得到Connection：加载驱动类
      * Class.forName(driverClassName);

    > 得到Connection：使用DriverManager类的静态方法，得到Connection
      * Connection con = DriverManager.getConnection(url, username, password);

  * 第二步：得到Statement
    > Statement stmt = con.createStatement();  

  * 第三步：发送sql语句
    > 发送更新sql语句：int row = stmt.executeUpdate(String sql);
    > 发送查询sql语句：ResultSet rs = stmt.executeQuery(String sql);
  * 第四步：操作ResultSet，从中获取数据
    > ResultSet内部有一个行光标，默认位置在beforeFirst（第一行的上面）
    > 操作行：next()方法可以把行光标向下移动一行！
    > 操作列：Xxx getXxx(int colIndex)系列方法，获取指定列的数据，再转换成指定类型
      （Xxx getXxx(String colName)），获取指定列名称的列数据
      * int getInt(int i)：获取第i列的数据，转换成int类型返回
      * int getInt(String colName)：rs.getString("ename")，获取当前行，名为ename的列数据
      * String getString(int i)：获取第i列的数据，转换成String类型返回
      * Object getObject(int i)：获取第i列的数据，转换成Object类型返回

      * 两种方式：
        > 通过列编号获取数据，编号从1开始
	> 通过列名称获取数据
   * 第五步：关闭资源
     > 倒关！
      
============================================

1. 加载驱动类
  * Class.forName(driverClassName)
    > 加载了驱动类（加载类会做什么）
      * 加载一个类时，会执行这个类的static块！

  * 在加载驱动类时，会执行static块，其内容如下：

	static {
		try {
			java.sql.DriverManager.registerDriver(new Driver());
		} catch (SQLException E) {
			throw new RuntimeException("Can't register driver!");
		}
	}
  * 把驱动类注册给了DriverManager

  * jdbc4.0提供了一种不用注册驱动类的方式
    > jar包中提供了一个名为java.sql.Driver的文件，文件内容为驱动类名称！！！
    > jdbc4.0会自动加载它！

    > 建议在代码给出Class.forName()，完成手动加载！因为我们不能保证使用的驱动包支持jdbc4.0

============================================
详细介绍jdbc核心类

1. Connection
  * Statement createStatement()
  * Statement createStatement(int,int)

2. Statement
  * int executeUpdate(String sql)：执行DML和DDL
  * ResultSet executeQuery(String sql)：执行DQL
  * boolean execute(String sql)：它可以执行DML、DDL、DQL
    > 返回true表示执行的是DQL
      * 当返回true时，可以使用getResultSet()来获取结果集
    > 返回false表示执行的是DML或DDL
      * 当返回false时，可以使用getUpdateCount()来获取影响的行数！

3. ResultSet(游标就是结果集的行光标)
  操作行：
  * void beforeFirst()：把光标放到第一行的前面，这也是光标默认的位置；
  * void afterLast()：把光标放到最后一行的后面；
  * boolean first()：把光标放到第一行的位置上，返回值表示调控光标是否成功；
  * boolean last()：把光标放到最后一行的位置上；

  * boolean isBeforeFirst()：当前光标位置是否在第一行前面；
  * boolean isAfterLast()：当前光标位置是否在最后一行的后面；
  * boolean isFirst()：当前光标位置是否在第一行上；
  * boolean isLast()：当前光标位置是否在最后一行上；

  * boolean previous()：把光标向上挪一行；
  * boolean next()：把光标向下挪一行；
  * boolean relative(int row)：相对位移，当row为正数时，表示向下移动row行，为负数时表示向上移动row行；
  * boolean absolute(int row)：绝对位移，把光标移动到指定的行上；

  * int getRow()：返回当前光标所有行。

  操作列：
  * Xxx getXxx(int colIndex)：通过列编号获取数据
  * Xxx getXxx(String colName)：通过列名获取数据

　获取结果集元数据（获取结果集的列数、获取指定列的名称）
  * 得到结果集元数据对象：ResultSetMetaData rsmd = rs.getMetaData();
    > String getColumnName(int column)：通过指定的列编码，获取列名称
    > int getColumnCount()：获取总列数

============================================

PreparedStatement

1. 什么是PreparedStatement
  * 预编译语句集
  * 优点：
    > 防SQL攻击
    > 提供可读性
    > 提高效率

2. 以对象形式进行增、删、改操作
  * 写一个工具类，用来提供得到Connection对象！
    > JdbcUtils，提供getConnection()方法
    > 把四大参数存放到配置文件中，getConnection()获取配置文件的四大参数！


3. 使用PreparedStatement的步骤
  * 得到PreparedStatement
    > 准备sql模板！（非完成的sql语句，其中参数不明确）
    > 使用sql模板来调用Connection的prepareStatement()方法，得到PreparedStatement对象
  * 调用PreparedStatement的setXxx()方法，为参数赋值（有几个不明确的参数，就要赋几个值）
  * 调用PreparedStatement的executeUpdate()或executeQuery()方法完成执行sql语句！
    > 它的executeUpdate和executeQuery方法没有参数！

4. PreapredStatement提供效率
  mysql服务器的执行流程
  * 查看语法是否正确！（费时）
  * 对sql语句进行编译！（把sql语句变成一个与函数相似的东西）
  * 执行！（调用函数）
　PreparedStatement的作用
  * 把sql模板给服务器，服务器会使用模板进行请求校验，然后再进行编译（其中参数编译后都会成为函数的参数）
  * 执行时只是把参数代入到函数中完成调用！
  * 当再次执行时，无需校验语法和编译的操作，只需要调用即可。

  * 提供效率是使用同一个PreparedStatement对象，才会提高！

5. 防SQL攻击

==========================

JdbcUtils工具类

==========================

UserDao

1. 面向接口编程
  * 提供dao接口
  * 提供dao接口的实现类

2. 编写dao工厂
  * 提供创建dao接口实现类的方法，并返回

----------------

1. UserDAO修改为接口
2. 把原UserDAO中的内容复制到UserDAOImpl类中，并让其实现UserDAO接口
3. 提供了UserDAOFactory类，添加getUserDAO()方法：通过配置文件得到实现类名称，创建实现类实例对象
4. 修改Service，Service不再自己创建DAO，而是通过工厂得到DAO实现

-----------

编写一个UserDAO实现类（使用jdbc连接数据库）
修改配置文件，把实现类修改为新的实现类名称！！！

=========================================

时间类型的转换

1. java.sql包下的所有类，不要出现在dao之外！！！
2. domain中的类，需要使用时间类型时，到底使用哪一种！！！
  * java.util.Date
    > java.sql.Date(年月日)
    > java.sql.Time(时分秒)
    > java.sql.Timestamp(年月日时分秒)

class User {
  private java.util.Date registtime
}

3. PreparedStatement（做增、删、改时可能会用到下面方法，而值却来自domain包下的对象（时间都是util的））
  1. setDate(int index, java.sql.Date data)
  2. setTime(int index, java.sql.Time data)
  3. setTimestamp(int index, java.sql.Timestamp data)

  java.util.Date --> sql时间

4. ResultSet（做查时，可能会使用下面方法，把sql的时间转换成util的时间，封装到domain包下的类中）
  1. java.sql.Date getDate()
  2. java.sql.Time getTime()
  3. java.sql.Timestamp getTimestamp()

  sql时间 --> java.util.Date

-------------------------

  java.sql.Date d1 = ....
  java.sql.Time t1 = ...
  java.util.Date d2 = d1;//不用转换！
  java.util.Date d2 = t1;//不用转换！

  java.util.Date d1 = ...
  java.sql.Date d2 = new java.sql.Date(d1.getTime());//转换的手段
  java.sql.Time t1 = new java.sql.time(d1.getTime());
  java.sql.Timestamp stamp = new java.sql.Timestamp(d1.getTime());

===================================================

大数据

1. 把二进制数据保存到数据库中
  * 列类型：二进制的列类型！

2. 通过byte[]得到Blob对象
  new SerialBlob(byte[] bytes);

3. 把Blob转换成InputStream
  InputStream in = blob.getBinaryStream();

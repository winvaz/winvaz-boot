MyBatis
MyBatis 本是apache的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis。 
MyBatis是一个优秀的持久层框架， 使用简单的 XML或注解用于配置和原始映射，将接口和 Java 的POJOs（Plain Old Java Objects，普通的 Java对象）映射成数据库中的记录。
持久层框架，对jdbc做了封装，将sql提取到xml配置文件中，便于后期维护,提供动态sql、缓存等功能。
===================================

提供mybatis的核心配置文件sqlMapConfig.xml,并放在项目的src目录下。
此文件中配置了数据库的基本连接参数和sql映射文件等相关信息。
我们可以从mybatis安装包中提供的官方文档(mybatis-3.2.7.pdf)中获得此文件的模板。
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
	PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 注册自定义的别名 -->
	<typeAliases>
		<typeAlias type="cn.icore.domain.User" alias="User"/>
		<typeAlias type="cn.icore.domain.Order" alias="Order"/>
		<typeAlias type="cn.icore.domain.Customer" alias="Customer"/>
	</typeAliases>
	<environments default="development">
		<environment id="development">
			<!-- mybatis框架提供的事务管理器类型 -->
			<transactionManager type="JDBC" />
			<!-- 数据源 -->
			<dataSource type="POOLED">
				<property name="driver" value="com.mysql.jdbc.Driver" />
				<property name="url" value="jdbc:mysql:///mybatis_day1" />
				<property name="username" value="root" />
				<property name="password" value="root" />
			</dataSource>
		</environment>
	</environments>
	<!-- 注册mybatis的映射文件 -->
	<mappers>
		<mapper resource="cn/icore/domain/UserMapper.xml" />
		<mapper resource="cn/icore/domain/BookMapper.xml" />
		<mapper resource="cn/icore/domain/OrderMapper.xml" />
		<mapper resource="cn/icore/domain/CustomerMapper.xml" />
		<!-- 注册接口文件 -->
		<mapper class="cn.icore.domain.IDeparmentDao"/>
	</mappers>
</configuration>

-----------------------
5.	创建一个对应icore_user表的实体类User
6.	创建一个mybatis的SQL映射文件：UserMapper.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
	PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.icore.domain.User">
    <!-- 将所有的字段提取出来 -->
    	<sql id="allColumns">
    		id,name,age,address
    	</sql>
    
    <!-- 
        在select标签中编写查询语句 
        id:当前sql语句的一个唯一标识
        resultType:结果类型，指定执行完成查询语句后由mybatis框架负责包装成指定类型的对象
    -->
    <select id="selectUserById" resultType="User">
        select 
            <include refid="allColumns"/>
         from icore_user where id = #{id}
    </select>
</mapper>

-----------------------------
7.将编写的sql映射文件注册到核心配置文件中
8.使用mybatis框架提供的API完成对数据库的操作
@Test
public void test1() throws Exception {
    // 读取核心配置文件，获得一个输入流
    InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
    // 读取mybatis框架的核心配置文件，创建一个sql会话工厂对象
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
            .build(is);
    // 从会话工厂对象中获取一个session，用于操作数据库
    SqlSession session = sessionFactory.openSession();
    /**
     * 参数一：使用命名空间 + sql语句id的方式唯一锁定要执行的sql 参数二：sql语句接收的参数值
     */
    User user = session
            .selectOne("cn.itcast.domain.User.selectUserById", 5);
    System.out.println(user);
}

===========================================
CRUD操作
1.查询操作
<select id="xx" parameterType="int" resultType="com.icore.winvaz.XXX">
    select * from user where id = #{id}
</select>

2.插入操作
<insert id....>
    insert into ...
</insert>

3.修改操作
<update id....>
    update table set ...
</update>

4.删除操作
<delete id....>
    delete from table ...
</delete>

------------------------------------------
Log4j日志级别
Fatal：致命错误 error：普通错误 warn：警告 info：普通信息 debug：调试信息 trace：堆栈信息

------------------------------------
关于mybatis的几点说明
为了更加直观的看到mybatis执行的sql语句，可以在项目的src目录下加入log4j.properties文件，将程序执行的sql输出到控制台。
当数据表中的字段和对应的实体类中的属性不完全对应时，可以使用resultMap标签进行字段和属性的对应，这点和hibernate非常类似。
Sql映射文件中的parameterType和resultType都可以指定为hashmap类型。
在sqlMapConfig.xml文件中可以定义别名，来简化开发。
使用<sql>标签将所有字段进行提取，来消除sql语句中的重复字段
可以使用如下语句进行模糊查询：
   <select id="selectUser" parameterType=“User" resultMap="userMap">
    select * from user where 1=1
    and name like '%${name}%'
    </select>
    
${}:会对传入的参数调用getter方法

-----------------------------
1.	可以在项目中加入log4j的配置文件，将日志的输出级别改为debug，将mybatis框架执行的sql输出在控制台，便于程序调试
2.	当实体类中的属性和表中的字段名称不一致时，可以在sql映射文件中通过resultMap标签进行映射
3.	sql映射文件中的参数类型和结果类型都可以使用HashMap类型
4.	在sqlMapConfig.xml中注册自定义的别名，简化sql映射文件中的配置
5.	使用sql标签将重复的sql语句统一进行提取：
6.	在sql映射文件中使用模糊查询(${}),查询语句的参数可以为User，或者为HashMap,保证对象中有get方法

---------------------------------
关联查询
* 可以在sql映射文件中通过association和collection标签进行关联查询的映射。
<resultMap id="xx" type="xxx">
    <id property="zz" colum="table_zz"/>
    ....
    <collection property="xx" ofType="XX">
        <id property="yy" column="table_yy"/>
        <result property="".../>
    </collection>
</resultMap>

======================================
使用注解开发mybatis
1.	创建表和对应的实体类
2.	编写一个接口文件，在接口中声明方法，在方法上使用mybatis提供的注解，编写sql
public class UserDao {
    @Value("select * from user where id=#{id})
    public User findUserById(int id);
}
-------------------------
3.	将编写的接口文件注册到sqlMapConfig.xml中
<!-- 注册接口文件 -->
<mapper class="cn.itcast.domain.UserDao"/>
----------------------------
4.	使用框架提供的API获得一个接口引用的代理对象，操作数据库
@Test
public void test10() {
    SqlSession session = sessionFactory.openSession();
    UserDao dao = session.getMapper(UserDao.class);
    User user = dao.findUserById(1);
    System.out.println(user);
    session.close();
    System.out.println(dao.getClass().getName());
}

=======================================
关联查询
* 可以在sql映射文件中通过association和collection标签进行关联查询的映射。
<resultMap id="xx" type="baseResultMap>
    <id property="id" column="id/>
    <result property="name" column="name"/>
    <!-- 描述集合属性使用collection标签
        property:集合对象的属性名称
        ofType:集合中的元素类型
    -->
    <collection property="yy" ofType="YY">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
    </collection>
</resultMap>

=======================================
动态SQL
* 在实际应用中经常有根据条件进行组合查询或是更新时会根据情况来更新数据表中的某几个字段，
这时我们很难将每种情况都编写一个sql语句，这时动态sql就是我们一个非常好的选择。

7批量插入数据
foreach的主要用在构建in条件中，它可以在SQL语句中进行迭代一个集合。
foreach元素的属性主要有 item，index，collection，open，separator，close。
item表示集合中每一个元素进行迭代时的别名，
index指 定一个名字，用于表示在迭代过程中，每次迭代到的位置，
open表示该语句以什么开始，
separator表示在每次进行迭代之间以什么符号作为分隔 符，
close表示以什么结束，
在使用foreach的时候最关键的也是最容易出错的就是collection属性，该属性是必须指定的，但是在不同情况下，该属性的值是不一样的，
主要有一下3种情况：
1.如果传入的是单参数且参数类型是一个List的时候，collection属性值为list。
2.如果传入的是单参数且参数类型是一个array数组的时候，collection的属性值为array。
3.如果传入的参数是多个的时候，我们就需要把它们封装成一个Map了，当然单参数也可以封装成map，实际上如果你在传入参数的时候，
在breast里面也 是会把它封装成一个Map的，map的key就是参数名，
所以这个时候collection属性值就是传入的List或array对象在自己封装的map 里面的key 

===========================================
延迟加载
1.在sqlMapConfig.xml中开启延迟加载的功能
<setting>
    <!-- 开启延迟加载功能 -->
    <settings name="LazyLoadingEnabled" value="true"/>
</settings>

2.在客户的sql映射文件CustomerMapper.xml中配置主sql语句

3.在CustomerMapper.xml中配置主sql语句的结果映射resultMap
<!-- 对象基本属性 -->
<resultMap id="xx" type="XX">
    <id xxx/>
</resultMap>
<!-- 关联属性的延迟加载的映射 -->
<resultMap id="yy" type="XX" extends="xx">
    <!-- -->
    <collection
        proerty="zz"
        column="zz_id"
        select="com.icore.winvaz.selectUserById">
    </collection>
    <collection property="shipmentRoute"
    			ofType="com.icore.winvaz.User"
    			javaType="ArrayList" 
    			select="com.icore.winvaz.selectUserById"
    			column="{detailId=id}">
    			</collection>
</resultMap>

==================================================
缓存

一级缓存
1.一级缓存的生命周期就是session的生命周期，如果session关闭，一级缓存中的数据将消失
2.当修改数据时，一级缓存中对应的数据会失效，再次查询对应的对象时，重新发出sql查询数据库

----------------------------------
二级缓存
1.二级缓存默认没有开启，如果需要使用，必须手动通过配置文件的方式开启二级缓存
<!-- 开启二级缓存 -->
<setting name="cacheEnabled" value="true"/>
2.在sql映射文件中加入当前文件中使用二级缓存的配置
<cache/>
3.将需要被缓存的实体类实现序列化接口
4.在sql语句中通过useCache设置当前的sql语句是否使用缓存中的数据,默认值为true
5.二级缓存的生命周期SessionFactory的生命周期

映射文件中所有的insert、update和delete语句将刷新缓存，三个语句中有flushCache="true" 属性，默认情况下为true，即同个sessionFactory中当有sql语句更新时缓存自动被刷新以保证数据的实时性，如果改成false则不会刷新。使用缓存时如果手动修改数据库表中的查询数据会出现脏读， 缓存将使用LRU（Least Recently Used）最近最少使用策略算法
<cache  eviction="FIFO"  flushInterval="60000"  size="512"  readOnly="true"/>
这个更高级的配置创建了一个 FIFO 缓存,并每隔 60 秒刷新,存数结果对象或列表的 512 个引用,而且返回的对象被认为是只读的,因此在不同线程中的调用者之间修改它们会 导致冲突。可用的收回策略有, 默认的是 LRU:
1.	LRU – 最近最少使用的:移除最长时间不被使用的对象。
2.	FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
3.	SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
4.	WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。
flushInterval(刷新间隔)可以被设置为任意的正整数,而且它们代表一个合理的毫秒 形式的时间段。默认情况是不设置,也就是没有刷新间隔,缓存仅仅调用语句时刷新。
size(引用数目)可以被设置为任意正整数,要记住你缓存的对象数目和你运行环境的 可用内存资源数目。默认值是1024。
readOnly(只读)属性可以被设置为 true 或 false。只读的缓存会给所有调用者返回缓 存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。可读写的缓存 会返回缓存对象的拷贝(通过序列化) 。这会慢一些,但是安全,因此默认是 false

=====================================
Ehcache缓存
采用mybatis的二级缓存框架
第一步：引入缓存的依赖包
第二步：引入缓存配置文件
ehcache.xml

defaultCache配置说明：
maxElementsInMemory 内存中最大缓存对象数.当超过最大对象数的时候,ehcache会按指定的策略去清理内存
eternal 缓存对象是否永久有效,一但设置了timeout , eternal将不起作用.
timeToIdleSeconds 设置Element在失效前的允许闲置时间.仅当element不是永久有效时使用,可选属性,默认值是0,也就是可闲置时间无穷大.
timeToLiveSeconds：设置Element在失效前允许存活时间.最大时间介于创建时间和失效时间之间.仅当element是永久有效时使用,默认是0.,也就是element存活时间无穷大.
overflowToDisk 配置此属性,当内存中Element数量达到maxElementsInMemory时,Ehcache将会Element写到磁盘中.
diskSpoolBufferSizeMB 这个参数设置DiskStore(磁盘缓存)的缓存区大小.默认是30MB.每个Cache都应该有自己的一个缓冲区.
maxElementsOnDisk 磁盘中最大缓存对象数,若是0表示无穷大.
diskPersistent 是否在重启服务的时候清除磁盘上的缓存数据.true不清除.
diskExpiryThreadIntervalSeconds 磁盘失效线程运行时间间隔.
memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时,Ehcache将会根据指定的策略去清理内存.默认策略是LRU(最近最少使用).你可以设置为FIFO(先进先出)或是LFU(较少使用).

第三步：修改mapper文件中缓存类型
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>

====================================================
====================================================
====================================================
sqlMapConfig.xml(基础配置)
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <!-- 开启延迟加载功能 -->
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"/>
        <!-- 开启二级缓存 -->
        <setting name="cacheEnabled" value="true"/>
    </settings>


    <!-- 注册自定义的别名 -->
    <typeAliases>
        <typeAlias type="cn.icore.domain.User" alias="User"/>
        <typeAlias type="cn.icore.domain.Order" alias="Order"/>
        <typeAlias type="cn.icore.domain.Customer" alias="Customer"/>
    </typeAliases>
    <environments default="development">
        <environment id="development">
            <!-- mybatis框架提供的事务管理器类型 -->
            <transactionManager type="JDBC"/>
            <!-- 数据源 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///mybatis_day1"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <!-- 注册mybatis的映射文件 -->
    <mappers>
        <mapper resource="cn/icore/domain/UserMapper.xml"/>
        <mapper resource="cn/icore/domain/BookMapper.xml"/>
        <mapper resource="cn/icore/domain/OrderMapper.xml"/>
        <mapper resource="cn/icore/domain/CustomerMapper.xml"/>
        <!-- 注册接口文件 -->
        <mapper class="cn.icore.domain.IDeparmentDao"/>
    </mappers>
</configuration>



































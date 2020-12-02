RDBMMS --> 关系型数据库管理系统
1. 包含多个DATABASE
2. DATABASE包含多个TABLE
3. 一个TABLE从两个层面来看，又分为：
  > 表结构
  > 表记录

----------------------------------------

MySQL目录结构

1. bin
  > mysqld.exe：服务器程序（先打开它，才能被客户端访问）
    * 启动mysql服务器端程序：net start mysql（在服务中手动完成）
    * 停止mysql服务器端程序：net stop mysql（在服务中手动完成）
  > mysql.exe：客户端程序
    * mysql -u用户名 -p密码 -h主机

2. 总配置文件
  > my.ini：后期我们会在这个文件中配置很多东西！

3. 在C:\programdata\MySQL\MySQL Server 5.5\data\
  这里每个目录都是一个数据库！

----------------------------------------

SQL

1. 什么是SQL
  * 结构化查询语言
  * 作用：可以操作所有关系型数据库管理系统
  * 各个数据库厂商还存在方言！

2. SQL语句的语法
  * 不区别大小写，建议关键字使用大写！
  * SQL语句的分类：
    > DDL --> 数据定义语言
    > DML --> 数据操作语言
    > DCL --> 数据控制语言
    > DQL --> 数据查询语言

----------------------------------------

DDL

1. 对数据库的操作
  * 查看所有数据库：show databases
  * 切换数据库（进入数据库）：use mydb1
  * 创建数据库：create database mydb1
  * 删除数据库：drop database mydb1
  * 修改数据库的编码：alter database mydb1 characeter set utf8


********************************************

列类型

* int：整型
* double：浮点型，例如double(5,2)表示最多5位，其中必须有2位小数，即最大值为999.99；
* decimal：浮点型，在表单钱方面使用该类型，因为不会出现精度缺失问题；
* char：固定长度字符串类型；char(200)，上限为255，最大为char(255)
* varchar：可变长度字符串类型；varchar(50)，上限为65535，最大为varchar(65535)
* text：字符串类型；表示大字符串，细分一共四种类型！
* blob：字节类型；表示大字节，细分一共四种类型！

* date：日期类型，格式为：yyyy-MM-dd；
* time：时间类型，格式为：hh:mm:ss
* timestamp：时间戳类型；年月日 + 时分秒 + 毫秒

********************************************

2. 对表的操作
  * 创建表
    > create table 表名称(列名 列类型, 列名 列类型, ...)
      * s_num int
      * s_name varchar(50)
      * s_age int
      * s_sex varchar(10)
  * 查看当前数据库中所有表名称
    > show tables;
  * 查看表结构
    > desc 表名
  * 删除表
    > drop table 表名
  * 修改表：前缀：alter table 表名
    > 添加列：add(列名 列类型, 列名 列类型, ...)
    > 修改列类型：modify 列名 新类型
    > 修改列名称和类型：change 老列名 新列名 新类型
    > 删除列：drop 列名
    > 修改表名称：rename to 新表名

================================================

DDL

1. 数据库
* 查看所有数据库：SHOW DATABASES
* 切换（选择要操作的）数据库：USE 数据库名
* 创建数据库：CREATE DATABASE [IF NOT EXISTS] mydb1 [CHARSET=utf8]
* 删除数据库：DROP DATABASE [IFEXISTS] mydb1
* 修改数据库编码：ALTER DATABASE mydb1 CHARACTER SET utf8

2. 数据类型(列类型)

int：整型
double：浮点型，例如double(5,2)表示最多5位，其中必须有2位小数，即最大值为999.99；
decimal：浮点型，在表单钱方面使用该类型，因为不会出现精度缺失问题；
char：固定长度字符串类型； char(255)
varchar：可变长度字符串类型； varchar(65535), zhangSan
text(clob)：字符串类型；
  > 很小
  > 小
  > 中
  > 大
blob：字节类型；
  > 很小
  > 小
  > 中
  > 大
date：日期类型，格式为：yyyy-MM-dd；
time：时间类型，格式为：hh:mm:ss
timestamp：时间戳类型；


3. 表

* 创建表：
  CREATE TABLE [IF NOT EXISTS] 表名(
    列名 列类型,
    列名 列类型,
    ...
    列名 列类型
  );
* 查看当前数据库中所有表名称：SHOW TABLES;
* 查看指定表的创建语句：SHOW CREATE TABLE 表名(了解);
* 查看表结构：DESC 表名;
* 删除表：DROP TABLE 表名;
* 修改表：前缀：ALTER TABLE 表名
  > 修改之添加列：
    ALTER TABLE 表名 ADD (
      列名 列类型,
      列名 列类型,
      ...
    );
  > 修改之修改列类型(如果被修改的列已存在数据，那么新的类型可能会影响到已存在数据)：ALTER TABLE 表名 MODIFY 列名 列类型;
  > 修改之修改列名：ALTER TABLE 表名 CHANGE 原列名 新列名 列类型;
  > 修改之删除列：ALTER TABLE 表名 DROP 列名;
  > 修改表名称：ALTER TABLE 原表名 RENAME TO 新表名;

================================================

DML（数据操作语言）
  * 它的操作是针对表记录的操作（ddl会改变表记录）
  * 字符串常量必须使用单引号，而不是双引号

1. （DQL）查询表记录
  > select * from 表名

2. 插入记录
  > insert into 表名(列名,列名,...) values(列值,列值,...)
  > insert into tbl_student(s_num,s_name) values(1001, 'zhangSan');
  > insert into tbl_student(s_age, s_gender, s_name) values(28, 'M', 'liSi');
  > insert into tbl_student values(1003, 'wangWu', 99, 'F');
    * 若插入所有列，那么列名可以省略，值的顺序按创建表时表的顺序！

3. 修改记录
  > update 表名 set 列名=列值,列名=列值,...
  > update tbl_student set s_age=18, s_gender='M';

4. 删除记录
  > delete from 表名 [where 条件]
  > delete from tbl_student where s_age is null

5. truncate table 表名：删除所有记录（DDL）（不能回滚）
  > drop table：删除表！！！
  > create table：创建表！！！

==================================================

SELECT * FROM 表名

DML(数据操作语言，它是对表记录的操作(增、删、改)！)

1. 插入数据
* INTERT INTO 表名(列名1,列名2, ...) VALUES(列值1, 列值2, ...);
  > 在表名后给出要插入的列名，其他没有指定的列等同与插入null值。所以插入记录总是插入一行，不可能是半行。
  > 在VALUES后给出列值，值的顺序和个数必须与前面指定的列对应
* INTERT INTO 表名 VALUES(列值1, 列值2)
  > 没有给出要插入的列，那么表示插入所有列。
  > 值的个数必须是该表列的个数。
  > 值的顺序，必须与表创建时给出的列的顺序相同。

2. 修改数据
* UPDATE 表名 SET 列名1=列值1, 列名2=列值2, ... [WHERE 条件]
* 条件(条件可选的)：
  > 条件必须是一个boolean类型的值或表达式：UPDATE t_person SET gender='男', age=age+1 WHERE sid='1';
  > 运算符：=、!=、<>、>、<、>=、<=、BETWEEN...AND、IN(...)、IS NULL、NOT、OR、AND

3. 删除数据
* DELETE FROM 表名 [WHERE 条件];
* TRUNCATE TABLE 表名：TRUNCATE是DDL语句，它是先删除drop该表，再create该表。而且无法回滚！！！

==================================================

DCL（数据控制语言）

1. 创建用户
  > create user 用户名@'IP地址' identified by '密码'
  > create user zhangSan@'localhost' identified by '123'

2. 给用户授权
  > grant 权限1,权限2,... on 数据库.* to 用户名@'IP地址'
  > grant create, alter, drop, select on mydb1.* to zhangSan@'localhost'


3. 撤消权限
  > revoke 权限1, 权限2, ... on 数据库.* from 用户名@'IP地址'
  > revoke create on mydb1.* from zhangSan@'localhost'

4. 查看权限
  > show grants for 用户名@'localhost'

5. 删除用户
  > drop user 用户名@'localhost'
==================================================

DCL(理解)

1. 创建用户
  * CREATE USER 用户名@IP地址 IDENTIFIED BY '密码';
    > 用户只能在指定的IP地址上登录
  * CREATE USER 用户名@'%' IDENTIFIED BY '密码';
    > 用户可以在任意IP地址上登录

2. 给用户授权
  * GRANT 权限1, ⋯ , 权限n ON 数据库.* TO 用户名@IP地址
    > 权限、用户、数据库
    > 给用户分派在指定的数据库上的指定的权限
    > 例如；GRANT CREATE,ALTER,DROP,INSERT,UPDATE,DELETE,SELECT ON mydb1.* TO user1@localhost;
      * 给user1用户分派在mydb1数据库上的create、alter、drop、insert、update、delete、select权限
  * GRANT ALL ON 数据库.* TO 用户名@IP地址;
    > 给用户分派指定数据库上的所有权限

3. 撤销授权
  * REVOKE 权限1, ⋯ , 权限n ON 数据库.* FROM 用户名@IP地址;
    > 撤消指定用户在指定数据库上的指定权限
    > 例如；REVOKE CREATE,ALTER,DROP ON mydb1.* FROM user1@localhost;
      * 撤消user1用户在mydb1数据库上的create、alter、drop权限

4. 查看权限
  * SHOW GRANTS FOR 用户名@IP地址
    > 查看指定用户的权限

5. 删除用户
  * DROP USER 用户名@IP地址
==================================================
SELECT 列
FROM 表
WHERE 分组前条件
GROUP BY 分组列
HAVING 分组后条件
ORDER BY 排序列
LIMIT int,int


DQL --> 数据查询语言

1. 基本查询
  > select * from 表名：查询所有行所有列！

1.1 列控制
  > 查询指定列：
    * select 列名,列名,... from 表名。
    * select ename, sal from emp;
  > distinct：去除完全相同的行
    * SELECT DISTINCT job FROM emp：查询job列，再去除完全重复行！
  > 列运算：列还可以做四则运算！
    * select sal*0.8 from emp;
    * ifnull(列名, 期望值)：ifnull(comm, 0)：当comm这一列上出现null时，把null当成0。
      > SELECT ename, sal+IFNULL(comm, 0) FROM emp;
    * 给列起别名：as 别名
      > SELECT ename, sal+IFNULL(comm, 0) [as] salary FROM emp;
    * 连接字符串：concat('', '')
      > SELECT CONCAT('我是一名快乐的员工，我叫', ename) FROM emp;
1.2 条件查询
  * 基本条件查询
    > select * from 表名 [where 条件]

  * 模糊查询
    > 下划线(_)：匹配一个任意字符
    > 百分号(%)：匹配0~N个任意字符
    > 关键字：like
    > 查询2001年入职的员工：select * from emp where hiredate like '2001%'
      Mybatis框架模糊查询：1. '%${xx}'
                         2. CONCAT('%', #{xx}, '%')
                         3. "%"${xx}"%"

2. 排序
  * 关键字：order by
  * 单列排序：order by 列名 asc 或 desc
    > asc：升序，可以省略asc
    > desc：降序
  * 多列排序：第一列如果相同，那么才会使用第二列进行排序！
    > order by 第一列 asc或desc, 第二列 asc或desc
    > SELECT * FROM emp ORDER BY sal ASC, comm DESC：使用sal的升序进行排序，如果sal相同，那么使用comm的降序排序

3. 聚合函数（纵向运算）
  * count()：计数：
    > select count(*) from emp：计算有效行！一行上所有列都为null表示无效行！
    > select count(comm) from emp：计算指定列的有效行，如果这一列上为null值，表示无效行！
  * sum()：求和
    > select sum(sal) from emp：计算sal这一列所有值的和
  * max()：最大
    > select max(sal) from emp;
  * min()：最小
    > select min(sal) from emp;
  * avg()：平均
    > select avg(sal) from emp;

4. 分组查询
  * 使用某一列进行分组，这一列有几种值就有几行记录。
  * 分组查询只能查询组信息，其他信息就算语法没有错误，也不能这样查！
    > 组信息：
      * 分组列
      * 聚合函数
    > 分组查询的案例：SELECT job, COUNT(*), MAX(sal), MIN(sal), AVG(sal), SUM(sal) FROM emp GROUP BY job;
  * having：分组后的条件，条件中带有聚合函数就是分组后条件！

1).按job分组
2).工资低于15000的出去（分组前条件）
3).工资合大于60000的（分组后条件）
SELECT job, COUNT(*), SUM(sal)
FROM emp
WHERE sal > 15000
GROUP BY job
HAVING  SUM(sal) > 60000

5. limit（分页）只有MySQL中有它！！！
  > select * from emp limit 0, 3
  > limit后有两个整数
    * 第一个：从第几行开始查询（第一行叫第0行）
    * 第二个：一共查询几行记录（如果不足指定的行，那么有几行显示几行）

问题：查询工资最高的员工姓名和工资
SELECT * FROM emp
ORDER BY sal DESC
LIMIT 0,1

-------------------------

分页

共15行记录
每页4行记录
问：总页数？
* 总页数 = 总记录数%每页记录数==0 ? 总记录数/每页记录数 ：总记录数/每页记录数+1

select * from emp
limit (要查的页-1)*每页记录数,每页记录数


==========================================================

*****DQL -- 数据查询语言
  查询不会修改数据库表记录！

一、 基本查询


1. 字段(列)控制
1) 查询所有列
 SELECT * FROM 表名;
 SELECT * FROM emp;
 --> 其中“*”表示查询所有列

2) 查询指定列
 SELECT [列1, 列2, ... 列N] FROM 表名;
 SELECT empno, ename, sal, comm FROM 表名;

3) 完全重复的记录只一次
 当查询结果中的多行记录一模一样时，只显示一行。一般查询所有列时很少会有这种情况，但只查询一列（或几列）时，这总可能就大了！
 SELECT DISTINCT * | [列1, 列2, ... 列N] FROM 表名;
 SELECT DISTINCT sal FROM emp;
 --> 保查询员工表的工资，如果存在相同的工资只显示一次！

4) 列运算
 I 数量类型的列可以做加、减、乘、除运算
   SELECT sal*1.5 FROM emp;
   SELECT sal+comm FROM emp;

 II 字符串类型可以做连续运算
   SELECT CONCAT('$', sal) FROM emp;

 III 转换NULL值
   有时需要把NULL转换成其它值，例如com+1000时，如果com列存在NULL值，那么NULL+1000还是NULL，而我们这时希望把NULL当前0来运算。
   SELECT IFNULL(comm, 0)+1000 FROM emp;
   --> IFNULL(comm, 0)：如果comm中存在NULL值，那么当成0来运算。

 IV 给列起别名
   你也许已经注意到了，当使用列运算后，查询出的结果集中的列名称很不好看，这时我们需要给列名起个别名，这样在结果集中列名就显示别名了
   SELECT IFNULL(comm, 0)+1000 AS 奖金 FROM emp;
   --> 其中AS可以省略

2. 条件控制
1) 条件查询
  与前面介绍的UPDATE和DELETE语句一样，SELECT语句也可以使用WHERE子句来控制记录。
  * SELECT empno,ename,sal,comm FROM emp WHERE sal > 10000 AND comm IS NOT NULL;
  * SELECT empno,ename,sal FROM emp WHERE sal BETWEEN 20000 AND 30000;
  * SELECT empno,ename,job FROM emp WHERE job IN ('经理', '董事长');
2) 模糊查询
  当你想查询姓张，并且姓名一共两个字的员工时，这时就可以使用模糊查询
  * SELECT * FROM emp WHERE ename LIKE '张_';
  --> 模糊查询需要使用运算符：LIKE，其中_匹配一个任意字符，注意，只匹配一个字符而不是多个。
  --> 上面语句查询的是姓张，名字由两个字组成的员工。
  * SELECT * FROM emp WHERE ename LIKE '___'; /*姓名由3个字组成的员工*/

  如果我们想查询姓张，名字几个字可以的员工时就要使用“%”了。
  SELECT * FROM emp WHERE ename LIKE '张%';
  --> 其中%匹配0~N个任意字符，所以上面语句查询的是姓张的所有员工。
  SELECT * FROM emp WHERE ename LIKE '%阿%';
  --> 千万不要认为上面语句是在查询姓名中间带有阿字的员工，因为%匹配0~N个字符，所以姓名以阿开头和结尾的员工也都会查询到。
  SELECT * FROM emp WHERE ename LIKE '%';
  --> 这个条件等同与不存在，但如果姓名为NULL的查询不出来！

二、排序
1) 升序
  SELECT * FROM WHERE emp ORDER BY sal ASC;
  --> 按sal排序，升序！
  --> 其中ASC是可以省略的
2) 降序
  SELECT * FROM WHERE emp ORDER BY comm DESC;
  --> 按comm排序，降序！
  --> 其中DESC不能省略
3) 使用多列作为排序条件
  SELECT * FROM WHERE emp ORDER BY sal ASC, comm DESC;
  --> 使用sal升序排，如果sal相同时，使用comm的降序排

三、聚合函数
  聚合函数用来做某列的纵向运算。
1) COUNT
  SELECT COUNT(*) FROM emp;
  --> 计算emp表中所有列都不为NULL的记录的行数
  SELECT COUNT(comm) FROM emp;
  --> 云计算emp表中comm列不为NULL的记录的行数
2) MAX
  SELECT MAX(sal) FROM emp;
  --> 查询最高工资
3) MIN
  SELECT MIN(sal) FROM emp;
  --> 查询最低工资
4) SUM
  SELECT SUM(sal) FROM emp;
  --> 查询工资合
5) AVG
  SELECT AVG(sal) FROM emp;
  --> 查询平均工资

四、分组查询
  分组查询是把记录使用某一列进行分组，然后查询组信息。
  例如：查看所有部门的记录数。
  SELECT deptno, COUNT(*) FROM emp GROUP BY deptno;
  --> 使用deptno分组，查询部门编号和每个部门的记录数
  SELECT job, MAX(SAL) FROM emp GROUP BY job;
  --> 使用job分组，查询每种工作的最高工资

  组条件
  以部门分组，查询每组记录数。条件为记录数大于3
  SELECT deptno, COUNT(*) FROM emp GROUP BY deptno HAVING COUNT(*) > 3;

五、limit子句(方言)
  LIMIT用来限定查询结果的起始行，以及总行数。
  例如：查询起始行为第5行，一共查询3行记录
  SELECT * FROM emp LIMIT 4, 3;
  --> 其中4表示从第5行开始，其中3表示一共查询3行。即第5、6、7行记录。

==============================

练习：

/*1. 查询出部门编号为30的所有员工*/
/*
分析：
1). 列：没有说明要查询的列，所以查询所有列
2). 表：只一张表，emp
3). 条件：部门编号为30，即deptno=30
*/
SELECT * FROM emp WHERE deptno=30;

/**********************************************/

/*2. 所有销售员的姓名、编号和部门编号。*/
/*
分析：
列：姓名ename、编号empno、部门编号deptno
表：emp
条件：所有销售员，即job='销售员'
*/
SELECT ename,empno,deptno FROM emp WHERE job='销售员'

/**********************************************/

/*3. 找出奖金高于工资的员工。*/
/*
分析：
列：所有列
表：emp
条件：奖金>工资，即comm>sal
*/
SELECT * FROM emp WHERE comm>sal;

/**********************************************/

/*4. 找出奖金高于工资60%的员工。*/
/*
分析：
列：所有列
表：emp
条件：奖金>工资*0.6，即comm>sal*0.6
*/
SELECT * FROM emp WHERE comm>sal*0.6;

/**********************************************/

/*5. 找出部门编号为10中所有经理，和部门编号为20中所有销售员的详细资料。*/
/*
分析：
列：所有列
表：emp
条件：部门编号=10并且job为经理，和部门编号=20并且job为销售员
*/
SELECT * FROM emp WHERE (deptno=10 AND job='经理') OR (deptno=20 AND job='销售员');

/**********************************************/

/*6. 找出部门编号为10中所有经理，部门编号为20中所有销售员，还有即不是经理又不是销售员但其工资大或等于20000的所有员工详细资料。*/
/*
分析：
列：所有列
表：emp
条件：deptno=10 and job='经理', depnto=20 and job='销售员', job not in ('销售员','经理') and sal>=20000
*/

SELECT * FROM emp 
WHERE 
  (deptno=10 AND job='经理') 
  OR (deptno=20 AND job='销售员') 
  OR job NOT IN ('经理','销售员') AND sal>=20000;

/**********************************************/

/*7. 有奖金的工种。*/
/*
列：工作(不能重复出现)
表：emp
条件：comm is not null
*/
SELECT DISTINCT job FROM emp WHERE comm IS NOT NULL;

/**********************************************/

/*8. 无奖金或奖金低于1000的员工。*/
/*
分析：
列：所有列
表：emp
条件：comm is null 或者 comm < 1000
*/

SELECT * FROM emp WHERE comm IS NULL OR comm < 1000;

/**********************************************/

/*9. 查询名字由三个字组成的员工。*/
/*
分析：
列：所有
表：emp
条件：ename like '___'
*/
SELECT * FROM emp WHERE ename LIKE '___'

/**********************************************/

/*10.查询2000年入职的员工。*/
/*
分析：
列：所有
表：emp
条件：hiredate like '2000%'
*/
SELECT * FROM emp WHERE hiredate LIKE '2000%';

/**********************************************/

/*11. 查询所有员工详细信息，用编号升序排序*/
/*
分析；
列：所有
表：emp
条件：无
排序：empno asc
*/
SELECT * FROM emp ORDER BY empno ASC;

/**********************************************/

/*12. 查询所有员工详细信息，用工资降序排序，如果工资相同使用入职日期升序排序*/
/*
分析：
列：所有
表：emp
条件：无
排序：sal desc, hiredate asc
*/
SELECT * FROM emp ORDER BY sal DESC, hiredate ASC

/**********************************************/

/*13. 查询每个部门的平均工资*/
/*
分析：
列：部门编号、平均工资(平均工资就是分组信息)
表：emp
条件：无
分组：每个部门，即使用部门分组，平均工资，使用avg()函数
*/
SELECT deptno, AVG(sal) FROM emp GROUP BY deptno;

/**********************************************/

/*14. 求出每个部门的雇员数量。*/
/*
分析：
列：部门编号、人员数量（人员数量即记录数，这是分组信息）
表：emp
条件：无
分组：每个部门是分组信息，人员数量，使用count()函数
*/
SELECT deptno, COUNT(1) FROM emp GROUP BY deptno;

/**********************************************/

/*
15. 查询每种工作的最高工资、最低工资、人数
列：部门、最高工资、最低工资、人数（其中最高工资、最低工资、人数，都是分组信息）
表：emp
条件：无
分组：每种工资是分组信息，最高工资使用max(sal)，最低工资使用min(sal)，人数使用count(*)
*/
SELECT job, MAX(sal), MIN(sal), COUNT(1) FROM emp GROUP BY job;

/**********************************************/

/*16. 显示非销售人员工作名称以及从事同一工作雇员的月工资的总和，并且要满足从事同一工作的雇员的月工资合计大于50000，输出结果按月工资的合计升序排列*/
/*
列：工作名称、工资和(分组信息)
表：emp
条件：无
分组：从事同一工作的工资和，即使用job分组
分组条件：工资合计>50000，这是分组条件，而不是where条件
排序：工资合计排序，即sum(sal) asc
*/
SELECT job,SUM(sal) FROM emp GROUP BY job HAVING SUM(sal)>50000 ORDER BY SUM(sal) ASC;
==========================================================

1. 编码问题
  * 查看MySQL数据库编码：SHOW VARIABLES LIKE 'char%';
  * 创建数据库时，已经指定的默认编码！
    > 创建的数据库的编码
      * 创建的表的编码

2. 与编码相关的变量
  * character_set_client=utf8
    > 无论客户端传递的是什么编码的数据，服务器都当成utf8来处理！
  * character_set_results=utf8
    > 服务器会先把数据转换成utf8，然后再发送给客户端

  * 我们的小黑屏，它的默认编码为gbk（OS相关）！它而且无法修改！
    > 发送的数据永远是gbk
    > 接收到的数据都当成gbk

  * 设置变量的语句（可以处理编码问题，但需要每次都设置一次！）：
    > set character_set_client=gbk;
    > set character_set_results=gbk;

==========================================================
编码

1. 查看MySQL数据库编码
  * SHOW VARIABLES LIKE 'char%';
  
2. 编码解释
  * character_set_client：MySQL使用该编码来解读客户端发送过来的数据，例如该编码为UTF8，那么如果客户端发送过来的数据不是UTF8，那么就会出现乱码
  * character_set_results：MySQL会把数据转换成该编码后，再发送给客户端，例如该编码为UTF8，那么如果客户端不使用UTF8来解读，那么就会出现乱码
  其它编码只要支持中文即可，也就是说不能使用latin1

3. 控制台乱码问题
  * 插入或修改时出现乱码：
    > 这时因为cmd下默认使用GBK，而character_set_client不是GBK的原因。我们只需让这两个编码相同即可。
    > 因为修改cmd的编码不方便，所以我们去设置character_set_client为GBK即可。
  * 查询出的数据为乱码：
    > 这是因为character_set_results不是GBK，而cmd默认使用GBK的原因。我们只需让这两个编码相同即可。
    > 因为修改cmd的编码不方便，所以我们去设置character_set_results为GBK即可。
  * 设置变量的语句：
    > set character_set_client=gbk;
    > set character_set_results=gbk;
  
  注意，设置变量只对当前连接有效，当退出窗口后，再次登录mysql，还需要再次设置变量。
  为了一劳永逸，可以在my.ini中设置：
  设置default-character-set=gbk即可。
　
4. 指定默认编码
  我们在安装MySQL时已经指定了默认编码为UTF8，所以我们在创建数据库、创建表时，都无需再次指定编码。
  为了一劳永逸，可以在my.ini中设置：
  设置character-set-server=utf8即可。

 character_set_client     | utf8 --> mysql把我们客户端传递的数据都当成是utf8！一是给它传递utf8，二是如果我们传递的是gbk，那么需要修改这个变量为gbk
 character_set_connection | utf8
 character_set_database   | utf8
 character_set_results    | utf8 --> mysql发送给客户端的数据都是utf8的。一是客户端用utf8编码，二是如果客户端使用gbk来编码，那么需要修改这个变量为gbk的。
 character_set_server     | utf8
 character_set_system     | utf8

==========================================================

备份与恢复

1. 数据库导出SQL脚本
  > mysqldump –u用户名 –p密码 数据库名>生成的脚本文件路径
  > mysqldump -uroot -p123 mydb1>F:/mydb1.sql
2. SQL脚本导入到数据库
  > mysql -u用户名 -p密码 数据库<脚本文件路径
  > mysql -uroot -p123 mydb1<F:/mydb1.sql

3. 登录客户端后，使用source命令完成导入
  > source F:/mydb1.sql

========================================
备份与恢复

1. 数据库导出SQL脚本
  > mysqldump –u用户名 –p密码 数据库名>生成的脚本文件路径
  > 例如：mysqldump -uroot -p123 mydb1>C:\mydb1.sql  (与mysql.exe和mysqld.exe一样, 都在bin目录下)
  > 注意，不要打分号，不要登录mysql，直接在cmd下运行
  > 注意，生成的脚本文件中不包含create database语句

2. 执行SQL脚本
  第一种方式
  > mysql -u用户名 -p密码 数据库<脚本文件路径
  > 例如：
    * 先删除mydb1库，再重新创建mydb1库
    * mysql -uroot -p123 mydb1<C:\mydb1.sql
  > 注意，不要打分号，不要登录mysql，直接在cmd下运行

  第二种方式
  > 登录mysql
  > source SQL脚本路径
  > 例如：
    * 先删除mydb1库，再重新创建mydb1库
    * 切换到mydb1库
    * source c:\mydb1.sql

---------------------------------

数据库 --> sql：备份
sql --> 数据库：恢复
========================================


约束
  * 约束添加到列上！
  * 约束是对列上的值进行约束！

1. 主键约束
  * 一张表只能有一个主键！
  * 建议使用代理主键，而不是自然主键
    > 自然主键：表中的原有列，例如身份证号
    > 代理主键：再添加一列，专门作为主键
  特性
  * 非空：这一列上的值不能为null
  * 唯一：这一列上的任意一行的值，都是唯一的，与其他行不重复
  * 可引用：需要学习外键后才能理解！
  语法
  * 创建表
    1. 
CREATE TABLE stu(
  s_num INT PRIMARY KEY,
  s_name VARCHAR(50),
  s_age INT,
  s_gender VARCHAR(10)
);
    2. 
CREATE TABLE stu(
  s_num INT,
  s_name VARCHAR(50),
  s_age INT,
  s_gender VARCHAR(10),
  PRIMARY KEY(s_num)
);
  * 修改表：
    1. 修改表之添加主键
ALTER TABLE stu 
ADD PRIMARY KEY(s_num);

    2. 修改表之删除主键
ALTER TABLE stu 
DROP PRIMARY KEY;


2. 主键自增长 
  
   auto_increment
  
  * 主键有唯一的特性，插入记录时，需要了解原来表中的记录的主键是什么，为了不出现重复主键！
  * 主键自增长：处理主键重复问题
    > 要求主键列必须是int类型
    > 使用一个一起在增长的序列来做主键！
    > 当指定主键为null时，由自增长的序列来指定主键！默认值为1，不断自增
  * 使用uuid来生成主键！！！

3. 非空约束
  * 在列后添加not null即可
    > s_name VARCHAR(50) NOT NULL
4. 唯一约束
  * UNIQUE
  * s_name varchar(50) unique
5. 外键约束
  * 外键是用来引用其他表的主键
  * 外键的特性
    > 可以为空
    > 可以重复
    > 外键必须来自另一张表的主键！（也存在自身关联）
  * 外键的语法：
    > 创建表：
CREATE TABLE tb_emp(
  eid CHAR(32) PRIMARY KEY,
  ename VARCHAR(50),
  dept_id CHAR(32),
  CONSTRAINT fk_emp_dept FOREIGN KEY(dept_id) REFERENCES tb_dept(did)
);
    > 修改表
alert table tb_emp
add CONSTRAINT fk_emp_dept FOREIGN KEY(dept_id) REFERENCES tb_dept(did)

create table stu (    
  s_num int,
  s_name varchar(50)
  class_id int,
  CONSTRAINT 外键约束名 FOREIGN KEY(class_id) REFERENCES tbl_class(cid) 
)
  * 约束：约束名 constraint fk_xxx_yyy
  * 外键：外键列 foreing key(外键列）
  * 引用：主表名(主键) references 主表名(主键)

===========================

6. 表与表的关系
  * 多对一（学生和班级）：略
  * 多对多（学生和老师）
    > 需要中间表！
    > 中间表需要给出两个外键，分别引用多对多的两张表！
  * 一对一（老公和老婆）
    > 主表没有什么特殊，与多对一相同！
    > 从表的主键，还要是相对主表的外键
      * 非空、唯一
      * 必须来自主表的主键

===========================

7. 对象模型、关系模型
  * 对象 --> 记录
  * 对象 <-- 记录

　　对象与记录总要相互转换，我们称之为orm --> 我们有一个工具处理（orm工具），它叫hibernate-->全自动！



===========================
===========================
===========================

多表查询

1. 合并结果集（把两个结果集纵向排列）
  * 要求：
    > 两个结果集的列数必须相同
    > 两个结果集的列类型必须一一对应
  * 语法：
    > union：select * from tb_a union select * from tb_b（去除完全相同的行）
    > union all：select * from tb_a union all select * from tb_b（不去除完全相同的行）


2. 连接查询（把两个结果横向排列）
  * 笛卡尔积：
    > {a,b} * {1,2,3} = {a1,a2,a3,b1,b2,b3}

  * 内连接
    > 特性：
      * 列 = 两个表的列数和
      * 行 = 笛卡尔积
      * 通常连接查询只是想多要几列，不想多要几行！
      * 去除笛卡尔积：主外键
    > 方言：select * from emp e, dept d where e.deptno=d.deptno
    > 标准：select * from emp e inner join dept d on e.deptno=d.deptno
                                (内 连 接)    (去笛卡尔积)
    > 自然（简化形式）：SELECT * FROM emp e NATURAL JOIN dept d
练习：
/*
1. 查询所有员工的姓名，以及所在部门名称
select ename, dname
from emp e, dept d
where e.deptno=d.deptno
*/
SELECT e.ename, d.dname
FROM emp e, dept d
WHERE e.deptno=d.deptno

/*
2. 查询张飞的姓名、工资、所在部门名称
1). 列：姓名、工资、部门名称
2). 表：emp dept
3). 积：e.deptno=d.deptno
4). 条件：e.ename='张三'
*/
SELECT e.ename,e.sal,d.dname
FROM emp e
INNER JOIN dept d
ON e.deptno=d.deptno
WHERE e.ename='张飞'

select 列
from 表
inner join 内连接的表
on 去除笛卡尔积
where 条件

  * 外连接
    > 左外连接
      * 左表数据无论是否满足关联条件都会出现！右表显示匹配数据！如果左表不匹配关联条件，右表补null
      * 语法：select * from emp e left outer join dept d on e.deptno=d.deptno

1. 查询所有员工姓名，以及上级姓名，若无上级，显示BOSS
*/
SELECT e.ename,IFNULL(m.ename,'BOSS') mname
FROM emp e 
LEFT OUTER JOIN emp m 
ON e.mgr=m.empno
    > 右外连接
      * 右表数据无论是否满足关联条件都会出现！左表显示匹配数据！如果右表不匹配关联条件，左表补null
      * 语法：select * from emp e right outer join dept d on e.depnto=d.deptno
    > 全外连接（MySQL不支持）
      * 左右不满足条件的也都显示出来，但都补null
SELECT * FROM emp e LEFT OUTER JOIN dept d ON e.deptno=d.deptno
UNION
SELECT * FROM emp e RIGHT OUTER JOIN dept d ON e.deptno=d.deptno


3. 子查询（查询中包含查询，多个select）
  * 查询中有查询
    > 子查询的位置
      * from后：（通常是多行多列）把子查询作为一张表来对待
      * where后：把子查询作为条件
        > 查询结果是单行单列，单个值
	> 查询结果是多行单列，一个集合
	> 查询结果是单行多列，一个对象

3.1 子查询作为条件的三种样式

/*
单行单列 --> 单个值
1. 查询与张飞同一部门的所有员工
*/
SELECT * FROM emp e WHERE deptno=(SELECT deptno FROM emp WHERE ename='张飞')

/*
多行单列 --> 数组
2. 查询与张飞或关羽同一部门的所有员工
*/
SELECT * FROM emp e WHERE deptno IN (SELECT deptno FROM emp WHERE ename IN ('张飞', '关羽'))

/*
单行多列 --> 对象
3. 查询工资和部门与谢逊都相同的员工信息
*/
SELECT * FROM emp e WHERE (sal,deptno)=(SELECT sal, deptno FROM emp WHERE ename='谢逊')

--------------------------

3.2 子查询作为表的样式
SELECT * FROM emp e INNER JOIN (SELECT * FROM dept WHERE deptno>10) d 

 
ON e.deptno=d.deptno
 

--------------------------

3.3 与子查询（条件样式）相关的两个关键字
  * all --> 所有
  * any --> 任意

/*
1. 查询工资大于30部门所有人的员工信息
*/
SELECT * FROM emp e
WHERE sal > ALL (SELECT sal FROM emp e WHERE deptno=30)

/*
2. 查询工资小于30部门任意一人的员工信息
*/
SELECT * FROM emp e
WHERE sal < ANY (SELECT sal FROM emp e WHERE deptno=30)

=============================================
select * 
from emp, dept, (select * from emp)
where
group by
having
order by
limit

多表查询
  1. 分类：
    * 合并结果集(了解)
    * 连接查询
    * 子查询

合并结果集
  * 要求被合并的表中，列的类型和列数相同
  * UNION，去除重复行
  * UNION ALL，不去除重复行

SELECT * FROM cd
UNION ALL
SELECT * FROM ab;

连接查询
  1. 分类
    * 内连接
    * 外连接
      > 左外连接
      > 右外连接
      > 全外连接(MySQL不支持)
    * 自然连接（属于一种简化方式）

  2. 内连接
    * 方言：SELECT * FROM 表1 别名1, 表2 别名2 WHERE 别名1.xx=别名2.xx
    * 标准：SELECT * FROM 表1 别名1 INNER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
    * 自然：SELECT * FROM 表1 别名1 NATURAL JOIN 表2 别名2
    * 内连接查询出的所有记录都满足条件。
  
  3. 外连接
    * 左外：SELECT * FROM 表1 别名1 LEFT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
      > 左表记录无论是否满足条件都会查询出来，而右表只有满足条件才能出来。左表中不满足条件的记录，右表部分都为NULL
    * 左外自然：SELECT * FROM 表1 别名1 NATURAL LEFT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
    * 右外：SELECT * FROM 表1 别名1 RIGHT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
      > 右表记录无论是否满足条件都会查询出来，而左表只有满足条件才能出来。右表不满足条件的记录，其左表部分都为NULL
    * 右外自然：SELECT * FROM 表1 别名1 NATURAL RIGHT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
    * 全链接：可以使用UNION来完成全链接

子查询
　　：查询中有查询（查看select关键字的个数！）
  1. 出现的位置：
    * where后作为条件存在
    * from后作为表存在(多行多列)

  2. 条件
    * (***)单行单列：SELECT * FROM 表1 别名1 WHERE 列1 [=、>、<、>=、<=、!=] (SELECT 列 FROM 表2 别名2 WHERE 条件)
    * (**)多行单列：SELECT * FROM 表1 别名1 WHERE 列1 [IN, ALL, ANY] (SELECT 列 FROM 表2 别名2 WHERE 条件)
    * (*)单行多列：SELECT * FROM 表1 别名1 WHERE (列1,列2) IN (SELECT 列1, 列2 FROM 表2 别名2 WHERE 条件)
    * (***)多行多列：SELECT * FROM 表1 别名1 , (SELECT ....) 别名2 WHERE 条件

====================================================
/*1. 查出至少有一个员工的部门。显示部门编号、部门名称、部门位置、部门人数。*/
/*
列：部门编号、部门名称、部门位置、部门人数(分组)
列：dept、emp(部门人数没有员工表不行)
条件：没有
分组条件：人数>1

部门编号、部门名称、部门位置在dept表中都有，只有部门人数需要使用emp表，使用deptno来分组得到。
我们让dept和（emp的分组查询），这两张表进行连接查询
*/
SELECT
z.*,d.dname,d.loc
FROM dept d, (SELECT deptno, COUNT(*) cnt FROM emp GROUP BY deptno) z
WHERE z.deptno=d.deptno;

/**************************************************/

/*2. 列出薪金比关羽高的所有员工。*/
/*
列：所有
表：emp
条件：sal>关羽的sal，其中关羽的sal需要子查询
*/
SELECT *
FROM emp e
WHERE e.sal > (SELECT sal FROM emp WHERE ename='关羽')

/**************************************************/

/*3. 列出所有员工的姓名及其直接上级的姓名。*/
/*
列：员工名、领导名
表：emp、emp
条件：领导.empno=员工.mgr

emp表中存在自身关联，即empno和mgr的关系。
我们需要让emp和emp表连接查询。因为要求是查询所有员工的姓名，所以不能用内连接，因为曾阿牛是BOSS，没有上级，内连接是查询不到它的。
*/
SELECT e.ename, IFNULL(m.ename, 'BOSS') AS lead
FROM emp e LEFT JOIN emp m
ON e.mgr=m.empno;

/**************************************************/

/*4. 列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称。*/
/*
列：编号、姓名、部门名称
表：emp、dept
条件：hiredate < 领导.hiredate

emp表需要查。部门名称在dept表中，所以也需要查。领导的hiredate需要查，这说明需要两个emp和一个dept连接查询
即三个表连接查询
*/
SELECT e.empno, e.ename, d.dname
FROM emp e LEFT JOIN emp m 
ON e.mgr=m.empno 
LEFT JOIN dept d ON e.deptno=d.deptno
WHERE e.hiredate<m.hiredate;

/**************************************************/

/*5. 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。*/
/*
列：员工表所有列、部门名称
表：emp, dept
要求列出没有员工的部门，这说明需要以部门表为主表使用外连接
*/
SELECT e.*, d.dname
FROM emp e RIGHT JOIN dept d
ON e.deptno=d.deptno;

/**************************************************/

/*6. 列出所有文员的姓名及其部门名称，部门的人数。*/
/*
列：姓名、部门名称、部门人数
表：emp emp dept
条件：job=文员
分组：emp以deptno得到部门人数
连接：emp连接emp分组，再连接dept
*/
SELECT e.ename, d.dname, z.cnt
FROM emp e, (SELECT deptno, COUNT(*) cnt FROM emp GROUP BY deptno) z, dept d
WHERE e.deptno=d.deptno AND z.deptno=d.deptno;

/**************************************************/

/*7. 列出最低薪金大于15000的各种工作及从事此工作的员工人数。*/
/*
列：工作，该工作人数
表：emp
分组：使用job分组
分组条件：min(sal)>15000
*/
SELECT job, COUNT(*)
FROM emp e
GROUP BY job
HAVING MIN(sal) > 15000;

/**************************************************/

/*8. 列出在销售部工作的员工的姓名，假定不知道销售部的部门编号。*/
/*
列：姓名
表：emp, dept
条件：所在部门名称为销售部，这需要通过部门名称查询为部门编号，作为条件
*/
SELECT e.ename
FROM emp e
WHERE e.deptno = (SELECT deptno FROM dept WHERE dname='销售部');

/**************************************************/

/*9. 列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级。*/
/*
列：员工所有信息(员工表)，部门名称(部门表)，上级领导(员工表)，工资等级(等级表)
表：emp, dept, emp, salgrade
条件：sal>平均工资，子查询
所有员工，说明需要左外
*/
SELECT e.*, d.dname, m.ename, s.grade
FROM emp e 
  NATURAL LEFT JOIN dept d
  LEFT JOIN emp m ON m.empno=e.mgr
  LEFT JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal
WHERE e.sal > (SELECT AVG(sal) FROM emp);

/**************************************************/

/*10.列出与庞统从事相同工作的所有员工及部门名称。*/
/*
列：员工表所有列，部门表名称
表：emp, dept
条件：job=庞统的工作，需要子查询，与部门表连接得到部门名称
*/
SELECT e.*, d.dname
FROM emp e, dept d
WHERE e.deptno=d.deptno AND e.job=(SELECT job FROM emp WHERE ename='庞统');

/**************************************************/

/*11.列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称。*/
/*
列：姓名、薪金、部门名称(需要连接查询)
表：emp, dept
条件：sal > all(30部门薪金)，需要子查询
*/

SELECT e.ename, e.sal, d.dname
FROM emp e, dept d
WHERE e.deptno=d.deptno AND sal > ALL(SELECT sal FROM emp WHERE deptno=30)

/**************************************************/

/*12.列出在每个部门工作的员工数量、平均工资。*/
/*
列：部门名称, 部门员工数，部门平均工资
表：emp, dept
分组：deptno
*/
SELECT d.dname, e.cnt, e.avgsal
FROM (SELECT deptno, COUNT(*) cnt, AVG(sal) avgsal FROM emp GROUP BY deptno) e, dept d
WHERE e.deptno=d.deptno;

13.查出年份、利润、年度增长比
SELECT t1.*, IFNULL(CONCAT((t1.col_lirun-t2.col_lirun)/t2.col_lirun*100,'%'),0) cnt
FROM tb_year t1
LEFT JOIN tb_year t2
ON t1.col_year = t2.col_year + 1
ORDER BY t1.col_year

===================================================
MongoDB
NoSQL(Not Only SQL )，意即“不仅仅是SQL” ,指的是非关系型的数据库 。是一项全新的数据库革命性运动，早期就有人提出，发展至2009年趋势越发高涨。NoSQL的拥护者们提倡运用非关系型的数据存储，相对于铺天盖地的关系型数据库运用，这一概念无疑是一种全新的思维的注入。 
关系型数据库中的表都是存储一些格式化的数据结构，每条记录的字段的组成都一样，即使不是每条记录都需要所有的字段，但数据库会为每条数据分配所有的字段。而非关系型数据库以键值对(key-value)存储，它的结构不固定，每一条记录可以有不一样的键，每条记录可以根据需要增加一些自己的键值对，这样就不会局限于固定的结构，可以减少一些时间和空间的开销。 

NoSql数据库优缺点
在优势方面主要体现在下面几点：
	简单的扩展
	快速的读写
	低廉的成本
	灵活的数据模型

在不足方面主要有下面几点：
	不提供对SQL的支持
	支持的特性不够丰富
	现有的产品部够成熟

================================================
MongoDB简介
MongoDB是用C++语言编写的非关系型数据库。特点是高性能、易部署、易使用，存储数据十分方便，主要特性有：
面向集合存储，易于存储对象类型的数据
模式自由
支持动态查询
支持完全索引，包含内部对象
支持复制和故障恢复
使用高效的二进制数据存储，包括大型对象
文件存储格式为BSON(一种JSON的扩展)

------------------------------------------
MongoDB基本概念介绍
文档(document)是MongoDB中数据的基本单元，非常类似于关系型数据库系统中的行(但是比行要复杂的多)
集合(collection)就是一组文档，如果说MongoDB中的文档类似于关系型数据库中的行，那么集合就如同表
MongoDB的单个实例可以容纳多个独立的数据库，每一个数据库都有自己的集合和权限
MongoDB自带简洁但功能强大的JavaScript shell，这个工具对于管理MongoDB实例和操作数据作用非常大
每一个文档都有一个特殊的键"_id",它在文档所处的集合中是唯一的，相当于关系数据库中的表的主键

===========================================
数据类型 	      描述 	                                          举例
null 	      表示空值或者未定义的对象 	                      {"x":null}
布尔值 	      真或者假：true或者false 	                      {"x":true}
32位整数       32位整数。shell是不支持该类型的，shell中默认会转换成64位浮点数 	 
64位整数       64位整数。shell是不支持该类型的，shell中默认会转换成64位浮点数 	 
64位浮点数     64位浮点数。shell中的数字就是这一种类型 	      {"x"：3.14，"y"：3}
字符串 	      UTF-8字符串 	                              {"foo":"bar"}
符号 	      shell不支持，shell会将数据库中的符号类型的数据自动转换成字符串 	 
对象id 	      文档的12字节的唯一id 	                      {"id": ObjectId()}
日期 	      从标准纪元开始的毫秒数 	                      {"date":new Date()}
正则表达式     文档中可以包含正则表达式，遵循JavaScript的语法  {"foo":/foobar/i}
代码 	      文档中可以包含JavaScript代码 	              {"x"：function() {}}	 
未定义 	      undefined 	                              {"x"：undefined}
数组 	      值的集合或者列表 	                              {"arr": ["a","b"]}
内嵌文档        文档可以作为文档中某个key的value 	              {"x":{"foo":"bar"}}

===========================================
启动MongoDB
在启动MongoDB之前，要手动创建一个存放MongoDB数据文件的目录，如D:\mongo_data
在命令行执行 mongod --dbpath=D:\mongo_data

小技巧：手动创建一个后缀为bat的文件，文件名任意，内容为： mongod --dbpath=D:\mongo_data，双击此文件就可以启动MongoDB服务，不用每次都到命令行执行启动命令
在启动服务器的shell下可以键入Ctrl-C来完全的停止mongod的运行

还可以使用命令将mongodb作为服务进行安装：
E:\Program Files\mongodb\bin\mongod.exe --logpath E:\Program Files\mongo_log\mongodb.log --logappend --dbpath I:\JavaEE\mongo_data --directoryperdb --serviceName MongoDB --install
从系统服务中移除此服务：
D:\mongodb\bin\mongod.exe --logpath D:\mongo_log\mongodb.log --logappend --dbpath D:\mongo_data --directoryperdb --serviceName MongoDB --remove

Mac mongodb解压版操作手册
1.进入的bin目录启动(sudo ./mongod)会报错。
exception in initAndListen: NonExistentPath: Data directory /data/db not found., terminating

2.创建存储文件目录
sudo mkdir -p /data/db
此时，文件只有只读权限，需要增加可写权限
sudo chmod +X /data/db  -- 增加可写权限
sudo chown username /data/db  -- 授权给username

3.启动还报错，则指定存储文件目录
./mongod --dbpath ../data/db

4.正确停止mongodb
在在客户端进去，使用shutdown命令
/usr/local/mongodb/bin:$ ./mongo
> use admin; 
switched to db admin 
> db.shutdownServer();
 server should be down...
>exit

===============================================
连接到MongoDB服务器
在命令行中可以使用mongo命令连接到MongoDB服务器，如下，输入mongo命令默认连接到本地的名称为test的数据库，如果希望连接到远程数据库，可以使用mongo ip:port

-------------------------------------------
MongoDB常用操作
创建数据库,使用命令 use 数据库名称 ,如：
use mydb1
删除当前数据库，使用命令 db.dropDatabase()

use 命令后跟的数据库名，如果存在就进入此数据库，如果不存在就创建，所以这种创建方式又叫隐式创建 
注意：使用命令use mydb1创建数据库后，并没有真正生成对应的数据文件，如果此时退出，此数据库将被删除，只有在此数据库中创建集合后，才会真正生成数据文件

------------------------------------------
 查看所有数据库，使用命令 show dbs 
 查看当前所在数据库，使用命令 db

--------------------------------------
 查看当前数据库中所有的集合，使用命令 show collections 或使用show tables
 
-----------------------------------------
 创建集合有两种方式，显示创建和隐式创建
显示创建可以使用命令 db.createCollection(“集合名称")
隐式创建可以使用命令 db.集合名称.insert({}),指创建集合并同时向集合中插入数据,例如：db.customer.insert({name:”jack”})

删除集合使用命令：db.集合名称.drop()

-------------------------------------------
 向集合添加文档，使用命令 db.集合名称.insert({})，例如：
db.user1.insert({name:”jack”,age:20})

可以使用下面的循环语句批量插入多个文档
for(var i=0;i<1000;i++){
	db.customer.insert({name:”name”+i ,age:i});
}

还可以使用db.集合名称.save({})向集合中添加文档，save于insert不同之处为：使用insert如果插入的文档中_id已经存在，则不能插入，类似于关系型数据库中的主键冲突
save方法如果插入的文档中_id已经存在，则执行更新

-------------------------------------------
删除集合中的文档，使用命令 db.集合名称.remove({删除条件})，不加删除条件为删除集合中的所有文档，例如，db.c1.remove() 为删除c1集合中的所有文档，db.c1.remove({name:”user1”})为删除c1集合中name为user1的文档

-----------------------------------------
 查询集合中的文档，可以使用命令 db.集合名称.find({条件})，或者使用 db.集合名称.findOne()  查询第一个文档

 查询集合中的文档，返回某些特定的键值

 除了查询表达式以外，Mongodb还支持一些额外的参数选项。例如，我们可能仅仅只想返回某些特定的字段值
 // 返回除了age字段外的所有字段
 db.user.find({},{age:0});
 // 返回tags=tenis除了comments的所有列
 db.posts.find({tags:'tenis'},{comments:0});
 // 返回userid=16的name字段
 db.user.find({userid:16},{name:1});
 // 返回x=json的所有z字段
 db.things.find({x:"json"},{z:1});

注：_id字段始终都会被返回，哪怕没有明确指定

==========================================
 查询集合中的文档 ，使用条件表达式(<, <=, >, >=,!=)

//大于： field > value
db.collection.find({field:{$gt:value}});

//小于： field < value
db.collection.find({field:{$lt:value}});

//大于等于： field >= value
db.collection.find({field:{$gte:value}});

//小于等于： field <= value
db.collection.find({field:{$lte:value}});

//不等于：  field != value
db.collection.find({field:{$ne:value}});

----------------------------------------------
 查询集合中的文档 ,$all主要用来查询数组中的包含关系，查询条件中只要有一个不包含就不返回

 查询集合中的文档 ,$in，类似于关系型数据库中的IN

 查询集合中的文档 ,$nin，与$in相反

 查询集合中的文档 ,$or，相当于关系型数据库中的OR，表示或者的关系，例如查询name为user2或者age为3的文档，命令为：
db.customer.find({$or:[{name:”user2”},{age:3}]})

 查询集合中的文档 ,$nor，表示根据条件过滤掉某些数据，例如查询name不是user2，age不是3的文档，命令为：
db.customer.find({$nor:[{name:”user2”},{age:3}]})

 查询集合中的文档 ,$exists，用于查询集合中存在某个键的文档或不存在某个键的文档，例如查询customer集合中存在name键的所有文档，可以使用 db.customer.find({name:{$exists:1}})，
$exists:1表示真，指存在
$exists:0表示假，指不存在

=====================================================
 查询集合中的文档 ，统计(count)、排序(sort)、分页(skip、limit)

db.customer.count();
db.customer.find().count();
db.customer.find({age:{$lt:5}}).count();
db.customer.find().sort({age:1});
db.customer.find().skip(2).limit(3);
db.customer.find().sort({age:-1}).skip(2).limit(3);
db.customer.find().sort({age:-1}).skip(2).limit(3).count();
db.customer.find().sort({age:-1}).skip(2).limit(3).count(0);
db.customer.find().sort({age:-1}).skip(2).limit(3).count(1);

---------------------------------------
 查询集合中的文档 ，类似于关系型数据库，mongodb中也有游标的概念

 更新集合中的文档，语法如下：

db.collection.update(criteria,objNew,upserti)

参数说明：
criteria:用于设置查询条件的对象
objNew:用于设置更新内容的对象
upsert:如果记录已经存在，更新它，否则新增一个记录，取值为0或1

注意：默认情况下，只会更新第一个符合条件的记录
一般情况下后两个参数分别为0,1 ，即：
db.collection.update(criteria,objNew,0,1)

 更新集合中的文档,将集合中name为user1的文档改成name为jack

 更新集合中的文档, $set 用来指定一个键的值，如果这个键不存在，则创建它。例如：
给name为user1的文档添加address，可以使用命令：db.c1.update({name:”user1”},{$set:{address:”bj”}},0,1)
将name为user1的文档修改address为tj，其它键值对不变,命令为：
db.c1.update({name:”user1”},{$set:{address:”tj”}},0,1)

 更新集合中的文档,使用 $inc 将集合中name为user1的age加1，其它键不变, $inc表示使某个键值加减指定的数值

 更新集合中的文档, $unset 用来删除某个键，例如删除name为user1的文档中的address键，可以使用命令：
db.c1.update({name:”user1”},{$unset:{address:1}},0,1)

==============================================
索引
索引就是用来加速查询的。数据库索引与书籍的索引类似：有了索引就不需要翻遍整本书，数据库则可以直接在索引中查找，使得查找速度能提高几个数量级。在索引中找到条目以后，就可以直接跳转到目标文档的位置。

-----------------------------------
 创建普通索引，使用命令 db.collection.ensureIndex({key:1})
 查看关于索引的相关信息，使用命令 db.collection.stats()
 查看查询使用索引的情况，使用命令   db.collection.find({key:value}).explain()
 删除索引，使用命令 db.collection.dropIndex({key:1})
 删除集合，也会将集合中的索引全部删除

 explain是非常有用的工具，会帮助你获得查询方面诸多有用的信息。只要对游标调用该方法，就可以得到查询细节。explain会返回一个文档，而不是游标本身。如：    > db.test.find().explain()    {        "cursor" : "BasicCursor",        "nscanned" : 1,        "nscannedObjects" : 1,        "n" : 1,        "millis" : 0,        "nYields" : 0,        "nChunkSkips" : 0,        "isMultiKey" : false,        "indexOnly" : false,        "indexBounds" : {        }        }    explain会返回查询使用的索引情况，耗时和扫描文档数的统计信息。    "cursor":"BasicCursor"表示没有使用索引。    "nscanned":1 表示查询了多少个文档。    "n":1 表示返回的文档数量。    "millis":0 表示整个查询的耗时。 

==============================================
固定集合(capped collection)
固定集合指的是事先创建而且大小固定的集合 。
固定集合特性：固定集合很像环形队列，如果空间不足，最早的文档就会被删除，为新的文档腾出空间。一般来说，固定集合适用于任何想要自动淘汰过期属性的场景，没有太多的操作限制。
创建固定集合使用命令：db.createCollection(“collectionName”,{capped:true,size:100000,max:100});  
size指定集合大小，单位为KB，max指定文档的数量 
当指定文档数量上限时，必须同时指定大小。淘汰机制只有在容量还没有满时才会依据文档数量来工作。要是容量满了，淘汰机制会依据容量来工作。 

================================================
备份(mongodump)和恢复(mongorestore)
MongoDB提供了备份和恢复的功能，分别是MongoDB下载目录下的mongodump.exe和mongorestore.exe文件 
备份数据使用下面的命令：
>mongodump -h dbhost -d dbname -o dbdirectory
-h：MongDB所在服务器地址，例如：127.0.0.1，当然也可以指定端口号：127.0.0.1:27017
-d：需要备份的数据库实例，例如：test
-o：备份的数据存放位置，例如：c:\data\dump，当然该目录需要提前建立，在备份完成后，系统自动在dump目录下建立一个test目录，这个目录里面存放该数据库实例的备份数据。

恢复数据使用下面的命令：
>mongorestore -h dbhost -d dbname -directoryperdb dbdirectory
-h：MongoDB所在服务器地址
-d：需要恢复的数据库实例，例如：test，当然这个名称也可以和备份时候的不一样，比如test2
-directoryperdb：备份数据所在位置，例如：c:\data\dump\test

================================================
导入(mongoimport)和导出(mongoexport)
导出数据可以使用命令：
mongoexport -h dbhost -d dbname -c collectionName -o output

参数说明:
-h  数据库地址
-d 指明使用的库
-c 指明要导出的集合
-o 指明要导出的文件名

导入数据可以使用命令：
mongoimport -h dbhost -d dbname -c collectionname output 

参数说明:
-h  数据库地址
-d 指明使用的库
-c 指明要导入的集合

===================================================
安全和认证
每个MongoDB实例中的数据库都可以有许多用户。如果开启了安全性检查，则只有数据库认证用户才能执行读或者写操作。
在认证的上下文中，MongoDB会将普通的数据作为admin数据库处理。admin数据库中的用户被视为超级用户(即管理员)。
在认证之后，管理员可以读写所有数据库，执行特定的管理命令，如listDatabases和shutdown。
在开启安全检查之前，一定要至少有一个管理员账号。

在admin数据库中创建管理员账号：
use admin;
db.addUser(“root”,”root”);

在test数据库中创建普通账号：
use test;
db.addUser(“zhangsan”,”123”);
db.addUser(“lisi”,”123”,true);
注意：用户zhangsan，密码为123，对test数据库拥有读写权限
           用户lisi，密码为123，对test数据库拥有只读权限

重新启动数据库服务，并开启安全检查：
mongod --dbpath d:\mongo_data --auth

==================================================
主从复制(主从集群 )
主从复制是MongoDB最常用的复制方式。这种方式非常灵活，可用于备份、故障恢复、读扩展等。
最基本的设置方式就是建立一个主节点和一个或者多个从节点，每个从节点要知道主节点的地址。运行mongod --master就启动了主服务器。运行mongod --slave --source master_address 则启动了从服务器，其中master_address就是上面主节点的地址。

MongoDB的复制至少需要两个服务器或者节点。其中一个是主节点，负责处理客户端请求，其它的都是从节点，负责映射主节点的数据。
主节点记录在其上执行的所有操作。从节点定期轮询主节点获得这些操作，然后对自己的数据副本执行这些操作。由于和主节点执行了
相同的操作，从节点就能保持与主节点的数据同步。
主节点的操作记录成为oplog(operation log)。oplog存储在一个特殊的数据库中，叫做local。oplog就在其中的oplog.$main集合
里面。oplog中的每个文档都代表主节点上执行的一个操作。需要重点强调的是oplog只记录改变数据库状态的操作。比如，查询就
不再存储在oplog中。这是因为oplog只是作为从节点与主节点保持数据同步的机制。

为了方便演示，可以在一台计算机上来模拟主节点和从节点。在D盘创建两个目录master和slave，master目录作为主节点的数据文件的目录，slave目录作为从节点的数据文件的目录。
注意：主节点和从节点要指定不同的端口。
启动主节点：mongod --dbpath d:\master --port 10000 --master
启动从节点：mongod --dbpath d:\slave --port 10001 --slave --source localhost:10000

启动成功后就可以连接主节点进行操作了，而这些操作会同步到从节点。

===============================================
副本集
副本集就是有自动故障恢复功能的主从集群。

主从集群和副本集最大的区别就是副本集没有固定的“主节点”；整个集群会选出一个“主节点”，当其挂掉后，又在剩下的从节点中选中其他节点为“主节点”，副本集总有一个活跃点(primary)和一个或多个备份节点(secondary)。

以三个节点为例：
节点1：
HOST：localhost:10001
Log File：D:\mongodb\logs\node1\logs.txt
Data File：D:\mongodb\dbs\node1

节点2：
HOST：localhost:10002
Log File：D:\mongodb\logs\node2\logs.txt
Data File：D:\mongodb\dbs\node2

节点3：
HOST：localhost:10003
Log File：D:\mongodb\logs\node3\logs.txt
Data File：D:\mongodb\dbs\node3

D:\app-green\mongodb\bin
D:\mongodata\instance1
D:\mongodata\log1

-----------
启动节点1：
mongod --dbpath D:\mongodb\dbs\node1 --logpath D:\mongodb\logs\node1\logs.txt --logappend --port 10001 --replSet icore/localhost:10002  --master

启动节点2：
mongod --dbpath D:\mongodb\dbs\node2 --logpath D:\mongodb\logs\node2\logs.txt --logappend --port 10002 --replSet icore/localhost:10001

启动节点3：  
mongod --dbpath D:\mongodb\dbs\node3 --logpath D:\mongodb\logs\node3\logs.txt --logappend --port 10003 --replSet icore/localhost:10001,localhost:10002

D:\app-green\mongodb\bin
D:\mongodata\instance1
D:\mongodata\log1
D:\mongodata\instance1
D:\app-green\mongodb\bin\mongod.exe --dbpath D:\mongodata\instance1 --logpath D:\mongodata\log1 –logappend –port 10001 –replSet icore/localhost:10002 –master
D:\app-green\mongodb\bin\mongod.exe --dbpath D:\mongodata\instance2 --logpath D:\mongodata\log2 –logappend –port 10002 –replSet icore/localhost:10001
D:\app-green\mongodb\bin\mongod.exe --dbpath D:\mongodata\instance3 --logpath D:\mongodata\log3 –logappend –port 10003 –replSet icore/localhost:10001,icore/localhost:10002

---------------
初始化节点(只能初始化一次)：随便登录一个节点,以10001为例
 mongo localhost:10001/admin
 db.runCommand({ "replSetInitiate":{  "_id":“icore",  "members":[   {     "_id":1,     "host":"localhost:10001",      "priority":3   },   {     "_id":2,     "host":"localhost:10002",     "priority":2   },   {     "_id":3,     "host":"localhost:10003",      "priority":1   }  ]}});

参数解释：
--replSet  指定副本集  后面紧跟着副本集的名称
--logappend 日志文件末尾添加
--port 指定端口号
db.runCommand({})  初始化副本集 
初始化文档:
"_id":“icore",  指副本集的名称
 "members":[...]  副本集的服务器列表  每个列表有个 
               "_id": 每个服务器的唯一id,  
              "host" 指定服务器的主机, 
              "priority"设置优先级，默认优先级为1,可以是1-1000的数字

---------------------------
查询当前主库，登录10002
mongo localhost:10002
db.$cmd.findOne ( {ismaster: 1 } ); 

-------------------
关闭10001服务Dos命令窗口,  登录10002查询当前主库
mongo localhost:10002
db.$cmd.findOne ( {ismaster: 1 } ); 

默认情况下从库是不能进行读写操作的
设置从库可读(在从库secondary上执行)：
rs.slaveOk ( ); 

-------------------------------
分片(sharding)
分片(sharding)是指将数据拆分，将其分散存在不同的机器上的过程。有时也用分区(partitioning)来表示这个概念。将数据分散到不同的机器上，不需要功能强大的大型计算机就可以储存更多的数据，处理更多的负载。

MongoDB分片的基本思想就是将集合切分成小块。这些块分散到若干片里面，每个片只负责总数据的一部分。应用程序不必知道哪片对应哪些数据，甚至不需要知道数据已经被拆分了，所以在分片之前要运行一个路由进程，该进程名为mongos。这个路由器知道所有数据的存放位置，所以应用可以连接它来正常发送请求。对应用来说，它仅知道连接了一个普通的mongod。路由器知道数据和片的对应关系，能够转发请求到正确的片上。如果请求有了回应，路由器将其收集起来回送给应用。

设置分片时，需要从集合里面选一个键，用该键的值作为数据拆分的依据。这个键称为片键(shard key)。

用个例子来说明这个过程：假设有个文档集合表示的是人员。如果选择名字("name")作为片键，第一片可能会存放名字以A~F开头的文档，第二片存的G~P的名字，第三片存的Q~Z的名字。随着添加或者删除片，MongoDB会重新平衡数据，使每片的流量都比较均衡，数据量也在合理范围内。

mongos就是一个路由服务器，它会根据管理员设置的“片键”将数据分摊到自己管理的mongod集群，数据和片的对应关系以及相应的配置信息保存在“config服务器”上。
mongod:一个普通的数据库实例，如果不分片的话，我们会直接连上mongod。 

1、创建三个目录，分别存放两个mongod服务的数据文件和config服务的数据文件
2、开启config服务器 。mongos要把mongod之间的配置放到config服务器里面，所以首先开启它，这里就使用2222端口。 命令为：
mongod --dbpath E:\sharding\config_node --port 2222
3、开启mongos服务器 。这里要注意的是我们开启的是mongos，端口3333，同时指定下config服务器。命令为：
mongos --port 3333 --configdb=127.0.0.1:2222
4、启动mongod服务器 。对分片来说，也就是要添加片了，这里开启两个mongod服务，端口分别为：4444，5555。命令为：
mongod --dbpath E:\sharding\mongod_node1 --port 4444
mongod --dbpath E:\sharding\mongod_node2 --port 5555 
5、服务配置 。client直接跟mongos打交道，也就说明我们要连接mongos服务器，然后将4444，5555的mongod交给mongos,添加分片也就是addshard()。
6、开启数据库分片功能，命令很简单 enablesharding(),这里就开启test数据库。 
7、指定集合中分片的片键，这里就指定为person.name键。 
8、通过mongos插入10w记录，然后通过printShardingStatus命令查看mongodb的数据分片情况。 

这里主要看三点信息：
  ① shards：     可以看到已经别分为两个片了，shard0000和shard0001。
  ② databases:： 这里有个partitioned字段表示是否分区，这里可以看到test已经分区。
  ③ chunks：     集合被砍成四段：
                           无穷小 —— jack0，
			jack0 ——jack234813，
			jack234813——jack9999，
			jack9999——无穷大。
分区情况为：3：1，从后面的 on shardXXXX也能看得出。

================================
================================
================================
小技巧：手动创建一个后缀为bat的文件，文件名任意，内容为： 
mongod --dbpath=D:\mongo_data，双击此文件就可以启动MongoDB服务，不用每次都到命令行执行启动命令
在启动服务器的shell下可以键入Ctrl-C来完全的停止mongod的运行

还可以使用命令将mongodb作为服务进行安装：
D:\mongodb\bin\mongod.exe --logpath D:\mongo_log\mongodb.log --logappend --dbpath D:\mongo_data --directoryperdb --serviceName MongoDB --install
从系统服务中移除此服务：
D:\mongodb\bin\mongod.exe --logpath D:\mongo_log\mongodb.log --logappend --dbpath D:\mongo_data --directoryperdb --serviceName MongoDB --remove




条件查询
db.students_info.find({$or:[{name:"xiaoguaiguai10"},{fensi:{$all:["kesaihao","heimaojz"]}}]})



权限验证
----  mongo localhost/icore0830 -u bingbing -p 520  直接以指定的用户名密码登陆到指定的库
----  如果登陆的时候没有指定，怎么认证？
         use  你有权限的库
         db.auth("zhangsan","123")


主从复制集群
运行主服务器： mongod --dbpath c:\masterdata  --master  --port 10000
运行从服务器:  mongod --dbpath c:\slave1data  --port 10001 --slave  --source  localhost:10000
							 mongod --dbpath c:\slave2data  --port 10002 --slave  --source  localhost:10000



副本集
d:
cd D:\app-green\mongodb\bin\
mongod.exe --dbpath c:\node1 --logpath c:\node1log\log --logappend --port 10001 --replSet icore/localhost:10002 --master
mongod.exe --dbpath c:\node2 --logpath c:\node2log\log --logappend --port 10002 --replSet icore/localhost:10001
mongod.exe --dbpath c:\node3 --logpath c:\node3log\log --logappend --port 10003 --replSet icore/localhost:10001,icore/localhost:10002
3个节点启动完成之后，需要对副本集进行初始化
先登陆到任意一台节点   mongo localhost:10001/admin 
执行以下初始化命令：
db.runCommand({"replSetInitiate":{"_id":"icore","members":[{"_id":1,"host":"localhost:10001","priority":3},{"_id":2,"host":"localhost:10002","priority":2},{"_id":3,"host":"localhost:10003","priority":1}]}});

db.$cmd.findOne({ismaster:1}); 



分片
配置服务器  mongod --dbpath c:\mongodata\instance1 --port 2222
路由服务器  mongos --port 3333 --configdb=127.0.0.1:2222
分片服务器  mongod --dbpath c:\mongodata\instance2 --port 4444
分片服务器  mongod --dbpath c:\mongodata\instance3 --port 5555

用客户端登陆到路由服务器进行初始化操作
mongo localhost:3333/admin 
db.runCommand({"addshard":"127.0.0.1:4444",allowLocal:true})
db.runCommand({"addshard":"127.0.0.1:5555",allowLocal:true})

对某个指定的数据库开启分片功能
db.runCommand({"enablesharding":"test"})
指定分片用的片键
db.runCommand({"shardcollection":"test.person","key":{"name":1}})
查看分片的状态信息
db.printShardingStatus()


==========================================
==========================================
==========================================
db.getCollection('student').find({})

/**显示所有数据库*/
show dbs

/**显示当前数据库*/
db

/**创建数据库*/
use winvaz

/**删除数据库*/
db.dropDatabase()

/**创建集合*/
db.createCollection("student")

/**显示集合*/
show collections;

/**插入一条数据*/
db.student.insert({name:"张三", age:18, sex:"女"});
db.student.insert({name:"liuyan", age:18, size:[88, 90, 92], sex:"女"});
db.student.insert({name:"fanbingbing", age:28, production:"苹果", sex:"女"});
db.student.insert({name:"libingbing", age:30, production:"变形金刚四", sex:"女"});

for(var i=0; i<10000; i++){
    db.student.insert({name:"xiaohuihui"+i, age:i, sex:i%2==0 ? "男" : "女"});
}

/**查询*/
db.student.help()

db.student.find();
db.student.find({name:"xiaohuihui20"});
/**显示指定字段*/
db.student.find({name:"xiaohuihui20"},{name:1,age:1,_id:0});
db.student.findOne({name:"liuyan"});
/**条件 $lt(<)*/
db.student.find({age:{$lt:50}});
/**$al查询数组包含关系*/
db.student.find({size:{$all:[92]}});
/**$in/$nin查询数组或者关系*/
/**$or/$nor*/
db.student.find({age:{$in:[20,21,22]}});
/**聚合count*/
db.student.count();
db.student.find().skip(10).limit(5);
/**游标*/
>var myit=db.student.find()
>myit.hasNext()
true
>myit.next()
{
	"_id" : ObjectId("5efde411ad731bb5a93d7ec4"),
	"name" : "liuyan",
	"age" : 18,
	"size" : [
		88,
		90,
		92
	],
	"sex" : "女"
}
>myit.next()

/**expalin*/
db.student.find({name:"liuyan"}).explain();

/**删除*/
db.student.remove();
db.student.drop();
db.student.remove({name:"张三"});

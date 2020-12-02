day26
1.	键盘事件
   文本框组件，TextField类
   构造方法，空参数的，带有一个String的构造(默认文本)
   如何获取按下的键
   KeyEvent类的方法和静态常量
      int getKeyCode()返回键盘的ASCII
      char getKeyChar()返回键盘上的键的字符类型
      consume()方法，父类方法，阻止输入，父类
      setEchoChar(char c)文本框将会显示传递的char字符
      isXXXDown()返回布尔值，判断是否按下了XXX键，父类
      KeyEvent类的中，有一片静态常量，数据类型都是int类型
这些常量，封装了键盘的ASCII码值，只需要知道键的名字。键盘的名字 VK_键名组成
直接类名调用就可以获取到键盘的ASCII值
   阻止非法键
   组合键
     CTRL+ENTER
2.	目录列表展示
  用户输入目录，展示目录下的子目录全部的文件
  点击按钮，按下回车键
  目录不存在，对话框提示
  容器：窗体Frame，对话框Dailog
  组件：输入框TextField 按钮 Button 展示列表的TextArea  对话框上Label ,Button
  main中不在实现任何功能，负责开启程序，其他的事情不在参数，图形界面来自己完成，main中只写一行代码，开启图形界面就OK
  Dailog的使用，是一个容器，
  构造方法 三个参数的，第一个窗体，标题，模式

3.	菜单
  MenuBar
    用窗体的setMenuBar方法设置MenuBar
    构造方法只有空参数的
    add(Menu)将菜单添加到菜单栏
  Menu
    构造方法，带有一个String标签的
    add(MenuItem)添加菜单条目到菜单中
  
  MenuItem
    构造方法，带有一个String标签的
    addActionListener()添加活动的事件监听
  窗体设置Bar,Bar添加Menu，Menu添加MenuItem
4.	模拟记事本，打开保存
 菜单，打开，保存
 多行文本，文件对话框

5.	IP地址
  IP地址，每一个接入互联网的主机的唯一身份标识，IP由4个数字段组成，每个数字段0-255  IP地址分类
  A  1.0.0.0 到126.0.0.0
  B  128.1.0.0到191.254.0.0
  C  192.0.1.0 到223.255.254.0
  D  224.0.0.0到239.255.255.255
  E  240.0.0.0到255.255.255.254
  局域网内的IP，192.168开头
  本地回环地址：127.0.0.1 本机IP地址，只要你的网卡工作正常，127.0.0.1
  Java中IP地址也是对象，IP地址对象的描述类 java.net.InetAddress类
  提供操作IP地址的方法。不公开构造方法，非静态的方法，需要对象
  找类中的静态方法，返回本类对象的，调用非静态方法
  static InetAddress getLocalHost()返回本机IP地址对象
  String getHostName()返回主机名，返回String类型
  String getHostAddress()返回主机对应的IP地址，返回String类型

  static InetAddress getByName(String 主机名)获取到的就是主机名对应的主机的IP地址对象
6.	端口号
  系统中的数字的标识，如果一个程序占用一个端口，别的程序将不能使用这个端口了。
  0-1024系统的保留端口号， 0-65535
  80端口，互联网访问默认端口号
  21端口，FTP上传端口号
  3306 MySQL数据库的连接端口
  1521 Oracle 数据库的连接端口
  1433 MS SQLServer数据库的连接端口

7.	通信协议
  协议就是通信双方，约定俗称的内容
  2点中，打你的123开头的号 ，说英语
  UDP协议
	将数据，和目的封装在数据包中
	不需要建立连接，面向无连接
	数据限制在64KB以内
	无连接，不安全可靠的协议
	不需要连接，速度快
	适合做即时通讯，Feiq，QQ，MSN
8.	Socket服务
  通信工具，通信双方必须有Socket服务，手机
  将你的数据，传递给Socket传输工具，将数据发送出去，接收者也是通过Socket工具接收发来的数据。
   数据包，Socket服务如何建立
   DategramPacket数据包对象
   DataGramSocket UDP的通信工具，发送数据和接收数据

9.	UDP的发送端
	将数据封装成数据包DategramPacket
	建立通信工具DataGramSocket对象
	将数据包交给工具DataGramSocket进行发送
	关闭资源
  DategramPacket类如何使用，找构造方法
  参数1： 字节数组，参数2：数组长度，参数3：目的IP地址，参数4：接收方端口号

  DataGramSocket类如何使用，找构造方法，空参
  发送方法send（数据包对象）

10.	UDP的接收端
	建立数据包对象，接收发送的数据包
	建立通信工具DataGramSocket对象
	将数据包交给工具DataGramSocket进行接收
	将数据包进行解包拆开
	关闭资源
 DategramPacket类的使用，找构造方法
 参数1：字节数组，参数2：长度
 DataGramSocket类如何使用，找构造方法，带有1int参数的构造
 int参数，写端口号，程序运行的时候Windows打开这个端口等待数据的进入
 接受的方法receive(数据包)
   拆包：
     将字节数组变成字符串
     DatagramPackat类中的方法 getLength()发送的有效字节数
     DatagramPackat类中的方法 getPort()发送方的端口号
     DatagramPackat类中的方法 getAddress()获取到发送方的IP地址对象InetAddress
     IP对象获取IP地址

11.	TCP协议
	面向连接协议，形成传输通道
	适合大数据传输
	三次握手，建立连接，安全
	建立连接，效率低
  适合做安全的数据传输，下载，在线视频
  IO的数据传输，都是字节流，通过Socket连接对象获取，不能自己创建

12.	TCP的客户端
	创建连接对象Socket
	通过Socket对象获取字节输出流，将数据发送到服务器
	通过Socket对象获取字节输入流，读取服务器发回的数据
	关闭资源

  客户端的连接对象 java.net.Socket类
  构造方法，两个参数，String IP，int 端口

13.	TCP的服务器端
	建立服务器连接对象ServerSocket
	获取客户端的连接对象，知道哪一个客户端和我连接的，ServerSocket类的方法accept
	获取到了客户端的连接对象，可以从客户端的连接对象Socket获取对象
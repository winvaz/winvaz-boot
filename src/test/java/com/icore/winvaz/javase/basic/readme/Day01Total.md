1：一些常见的软件使用。
	(1)金山打字通
	(2)金山词霸

2：计算机基础知识
	(1)计算机
	(2)计算机硬件
	(3)计算机软件
		系统软件：windows,linux,mac
		应用软件：QQ,YY,扫雷,CS/F
       （4）文件
		基本释义
		常用扩展名：EXE,BAT,COM(txt等不是可执行文件)
	(5)软件开发
		就是用开发工具和计算机语言做出软件
	(6)计算机语言
		人与计算机的交流方式
	(7)人机交互方式
		A:图像界面	方便，简单，直观。
		B:DOS窗口方式	要有控制台，要记住很多的命令，麻烦。
	(8)键盘功能键和快捷键的介绍
		A:键盘功能键
			tab
			shift
			ctrl
			alt
			空格
			enter
			windows键
			PrtSc
			上下左右
		B:键盘快捷键(补齐中文意思)
			ctrl+A 全选
			ctrl+C 复制
			ctrl+V 粘贴
			ctrl+X 剪切
			ctrl+Z 撤销
			ctrl+S 保存
	(9)常用的DOS命令
		A:如何打开控制台
			win+R -- cmd -- 回车
		B:常用的命令(补齐中文意思)
			d:回车
			cd demo回车
			cd.. 返回上一层目录
			cd\  返回根目录
			cls  清屏
			exit 退出

3：Java语言概述
	(1)Java语言的发展史
		Java之父 -- 詹姆斯·高斯林（James Gosling）
	(2)Java语言的平台
		A:J2SE 基础版,桌面应用。
		B:J2ME 微型版,手机开发。(android,ios)
		C:J2EE 企业版,所有浏览器访问的应用程序。

		注意：JDK5以后改名
			JavaSE,JavaME,JavaEE

		J2SE是学习其他两门的基础。
	(3)Java语言的特点
		其他的很多特点...

		开源：源代码开放
		跨平台：在任意操作系统下都可以使用。
	(4)跨平台
		通过火星人和中国，棒子，岛国人的交流知道的原理，找一个翻译。
		而java语言的这个翻译是jvm。

		注意：java语言是跨平台的，jvm不是跨平台的。
	(5)JRE和JDK
		JRE: JVM + class library	运行环境
		JDK: JRE + tools		开发工具包

		一句话：有JDK开发的软件，必须在JRE上运行，并由JVM保证跨平台。

4：JDK的下载与安装
	(1)JDK的下载。
		通过官网：http://www.oracle.com/cn/index
	(2)JDK的安装
		A:傻瓜式安装，会点击下一步即可。
		B:注意：
			a:请不要在中文目录和有特殊字符的目录(空格)
			b:请把所有开发相关的软件放到一个目录中。
		C:JRE是不需要单独安装的。

5：HelloWorld案例
	(1)开发工具
		A:记事本windows自带的
		B:高级记事本EditPlus
		C:集成开发工具Eclipse/MyEclipse
	(2)一个HelloWorld案例
		最终代码：
			class Demo
			{
				public static void main(String[] args)
				{
					System.out.println("Hello World");
				}
			}

		解释：
			A:java语言的最基本单位是类。用class表示
				定义类的格式：
					class 类名
			B:程序如果要运行，就必须有main方法，它是被jvm调用。
				格式：
					public static void main(String[] args)
			C:程序如果要输出一些内容，就必须使用输出语句。
				格式：
					System.out.println("Hello World");
	(3)一个Java程序的执行
		A:开发源程序(.java文件)
			Demo.java
		B:通过javac命令编译(.class)
			javac Demo.java
		C:通过java命令执行
			java Demo
	(4)常见的错误及注意事项
		A:文件扩展名导致编译失败。把隐藏文件扩展名给显示出来。
			win7,win8的同学如果不会单独问我。
		B:非法字符错误
			只要看到是非法字符，肯定是中英文问题。
			因为我们要求所有的符号全部是英文状态的。
		C:注意大小写问题
			class -- Class
			String -- string
			System -- system
		D:括号的对应问题
			在java程序中，括号都是成对出现的。
			所以，我建议在编写程序时，请遇到括号，成对打。
		E:main方法格式错误	
			public static void main(String [] args){ }
		F:当java源文件发生变化后，请重写编译在运行
		G:缩进问题
			写代码，遇到{}，请缩进一个tab位置。

6：环境变量
	(1)path环境变量的作用
		让javac和java命令可以在任意的目录下使用。
	(2)path环境变量的配置(掌握)
		A:只修改path
			D:\develop\Java\jdk1.7.0_45\bin;以前的path
		B:先建立一个JAVA_HOME,后修改path
			新建：JAVA_HOME 值是 D:\develop\Java\jdk1.7.0_45
			修改：%JAVA_HOME%\bin;以前的path
			好处：不必频繁修改path变量

		推荐使用B方案。
	(3)classpath环境变量的配置
		让指定的class文件在任意目录都可以被访问。

		技巧：在最左边配置一个.;
		      这样做的好处就是能够先在当前目录下查找并执行。

7：注释
	注释的内容，不会被JVM解释执行。

	功能：
		解释说明代码
		方便代码调试

	单行注释
		//
	多行注释
		/**/
	文档注释/**
		*/


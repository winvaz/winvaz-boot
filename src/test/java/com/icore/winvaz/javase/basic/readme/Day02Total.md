1:关键字(掌握)
	(1)被Java语言赋予特殊意义的单词。
	(2)特点：
		Java语言中的所有关键字全部是小写。
	(3)注意事项：
		goto和const作为保留字(在JDK的新版本中可能提升为关键字)存在。没有被使用。
	
2:标识符(掌握)
	(1)就是给类,变量,方法起名字。
	(2)组成规则：
		由数字0-9,英文大小写字母,$以及_组成。
	(3)注意事项：
		A:只能有组成规则规定的内容组成。
		B:不能以数字开头。
		C:不能是Java中的关键字。
		D:区分大小写。
	(4)常用的命名规则：见名知意,驼峰命名
		A:包(文件夹，用于区分同名的类)
			全部小写。如果多级包，用.分开。
			举例：
				com	一级包
				cn.icore	二级包	
				注意：www.icore.cn(域名反写)
		B:类和接口
			如果是一个单词,首字母大写。
				举例：Demo,Test,Student
			如果是多个单词,每个单词的首字母大写。大驼峰式
				举例：HelloWorld,StudyJava
		C:变量和方法名
			如果是一个单词,首字母小写。
				举例：main,name,age
			如果是多个单词,从第二个单词开始每个单词的首字母大写。小驼峰式
				举例：showName(),studentName
		D:常量
			全部大写，如果多个单词组成，用_连接。
				举例：PI,STUDENT_MAX_AGE

3：注释(掌握)
	(1)就是对程序的解释性文字。
	(2)注释的分类：
		A:单行注释
			a:以//开头，以回车结束。
			b:单行注释是可以嵌套的。
		B:多行注释
			a:以/*开头，以*/结束。
			b:多行注释是不可以嵌套的。
		C:文档注释(了解)
			将来被javadoc工具解析，生成一个说明书。
	(3)注释的作用：
		A:解释程序，提高程序的阅读性。
		B:可以调试错误。
	(4)把HelloWorld案例用注释改版。
		建议先写思路，在写代码体现。

4：常量(理解)
	(1)在程序的运行过程中，其值是不可以发生改变的量。
	(2)常量的分类：
		A:字面值常量
			a:整数常量
				12,-23
			b:实数（这里专指小数）常量
				12.5,-65.43
			c:字符常量
				'a','A','0'
			d:字符串常量
				"hello"
			e:布尔常量
				true,false
			d:空常量(后面讲，不是类型，是一个数值)
				null
		B:自定义常量(后面讲)
	(3)常量可以直接被输出。

5：进制(理解)
	(1)是一种进位的方式。X进制，表示逢x进1。
	(2)Java中整数常量的表示
	int a = 15；
	int a = 0b1111；
	int a = 017;
	int a = 0xF;
		A:二进制 由0,1组成。以0b开头。JDK7以后的新特性。
		B:八进制 由0-7组成。以0开头。
		C:十进制 由0-9组成。默认就是十进制。
		D:十六进制 由0-9，A-F(不区分大小写)组成，以0x开头。
	(3)进制转换：
		A:其他进制到十进制
			系数：就是每一位上的数据。
			基数：X进制，基数就是X。
			权：在右边，从0开始编号，对应位上的编号即为该位的权。
			结果：把系数*基数的权次幂相加即可。
			
			二进制：1011
				十进制：
			八进制：74
				十进制：
			十六进制：a3
				十进制：

		B:十进制到其他进制
			除基取余，直到商为0，余数反转。

			十进制：60
			结果：
				二进制
				八进制
				十六进制

		C:快速转换
			a:8421码。
			b:二进制--八进制(3位组合)
			c:二进制--十六进制(4位组合)
                二进制转换成十进制，由于通常只计算少位数的转换，
                          所以直接采用8421法即可
                        0001 1
                        0010 2
                        0100 4
                        1000 8

6：变量(掌握)
	(1)程序的运行过程中，在指定范围内发生改变的量。
	(2)格式：
		数据类型 变量名 = 初始化值;

		变量的定义格式：
			数据类型 变量名;
			变量名 = 初始化值;
			数据类型 变量名 = 初始化值;
			数据类型 变量1,变量2,变量3=10;

		举例：
			方式1：	byte b = 10;

			方式2：	byte b;
				b = 10;

7：数据类型(掌握)
	(1)分类
		基本类型：4类8种。
		引用类型：类，接口，数组。(了解)
	(2)基本类型
		整型：
			byte	1
			short	2
			int	    4
			long	8
		浮点型：
			float	4
			double	8
		字符型：
			char	2
		布尔型：		
			boolean 不明确。可以认为是1个字节。
		
		注意：
			整数默认是int类型。long类型需要加L或者l后缀。
			浮点数默认是double类型。float类型需要加F或者f后缀。
	(3)类型转换
		A:boolean类型不参与转换。
		B:隐式转换(从小到大)
			byte,short,char -- int -- long -- float -- double
		C:强制转换(从大到小)
			格式：
				(数据类型)数据;
	(4)面试题
		byte b1 = 3;
		byte b2 = 4;
		byte b3 = b1 + b2; // 精度损失
		byte b4 = 3 + 4; // 正常运行
/*运算符暂留
8：运算符(掌握)
	(1)就是把常量和变量连接的符号，一般参与运算使用。
	(2)分类：
		算术运算符
		赋值运算符
		关系运算符
		逻辑运算符
		位运算符
		三元运算符
	(3)算术运算符
		+,-,*,/,%,++,--

		+：正号，加法，字符串连接符。
			System.out.println("5+5="+5+5);//5+5=55
			System.out.println(5+5+"=5+5");//10=5+5

		%：取得余数
			左边如果大于右边，结果是余数。
			左边如果小于右边，结果是左边。
			左边如果等于右边，结果是0。

			正负号跟左边一致。

		++/--：
			++ 其实相当于把数据+1

			单独使用：
				在数据的前后，结果一致。
		
			参与操作使用：
				如果在数据的后边，数据先操作，在++/--
				如果在数据的前边，数据先++/--，再操作。

	(4)赋值运算符
		=,+=,-=,*=,/=,%=

		int a = 10;
			把10赋值给int类型的变量a。

		a += 20;
			把左边和右边的和赋值给左边。

		注意事项：
			a = a + 20;
			a += 20;
			结果是等价的，理解不是等价的。

			因为+=这种运算符，内含了强制类型转换功能。
			比如：
				short s = 2;

				s+=3;
				等价于
				s = (short)(s+3);
*/
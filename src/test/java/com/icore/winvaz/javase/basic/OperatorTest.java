package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

/**
 * 1：运算符(掌握)
 * 	(1)算符运算符
 * 		+，-,*,/,%,++,--
 * 		+:
 * 		  1、数学上的加法(减法，乘法相同)
 * 		  2、指定正数(减法相同)
 * 		  3、连接字符串
 * 		/：
 * 		  两个整数相除，取整数
 * 		%:
 * 		  两个数相除，取余数
 * 		  符号与被除数相同
 * 		++，--：
 * 		  单独使用时：直接自增，自减
 * 		  混合使用时(++,--规则相同)：
 * 			++在前：先加1，再使用
 * 			++在后：先使用，后加1
 * 	(2)赋值运算符
 * 		=，+=，-=，*=，/=，%=
 * 		对于类似+=(简捷运算符)这样的赋值，是将等号左边的数与等号右边的数计算，再赋值给等号左边
 * 。
 * 		还包含一个隐形的强制转换即：
 * 		byte b = 10;
 * 		b += 10;//不会，相当于b = (byte)(b+10)
 * 		除等：除数不能为0
 *
 * 	(3)关系运算符
 * 		==,!=,>,>=,<,<=
 *
 * 		特点：关系运算符的结果都是boolean类型。
 * 		      请千万注意：== 不要写成 =
 * 	(4)逻辑运算符
 * 		&,|,!,^,&&,||
 *
 * 		&:有false则false
 * 		|:有true则true
 * 		!:true变false,false变true
 * 		^:相同false,不同true // 适用于结婚
 *
 * 		&&:有false则false
 * 		||:有true则true
 *
 * 		&&和&的区别是：如果左边有false了，右边将不再执行。
 * 		||和|的区别是：如果左边有true了，右边将不再执行。
 *
 * 		开发中常用：
 * 			&&,||,!
 * 	(3)位运算符(了解)
 * 		^ : 一个数据对同一个数据^两次，结果还是数据本身。
 * 		举例：a ^ b ^ b = a
 * 	(4)三元运算符
 * 		格式：
 * 			条件表达式?表达式1:表达式2
 *
 * 			执行流程：
 * 				根据条件表达式返回的是true还是false，决定结果是什么。
 * 				如果是true,就把表达式1作为结果。
 * 				如果是false,就把表达式2作为结果。
 * 		举例：
 * 			int a = 100;
 * 			int b = a > 50 ? 200 : 100;
 *
 * 			请问b的值?200
 *
 * 2：面试题(理解)
 * 	(1)请用最有效率的代码写出2乘以8
 * 		2<<2
 * 	    1<<3
 * 	(2)请交换两个变量。
 * 		int a = 10;
 * 		int b = 20;
 *
 * 		开发：临时变量
 * 			int temp = a;
 * 			a = b;
 * 			b = temp;
 *
 * 		面试：位^运算符
 * 			a = a ^ b;
 * 			b = a ^ b;
 * 			a = a ^ b;
 *
 * 3：if语句(掌握)
 * 	(1)用于做判断使用的。
 * 	   常见于对某个范围进行判断，或者几个变量进行判断，还有就是boolean表达式的判断。
 * 	(2)格式：
 * 		A:第一种格式
 * 			if(条件表达式)
 *                        {
 * 				语句体;
 *            }
 *
 * 			执行流程：
 * 				如果条件表达式为true，就执行语句体；
 * 				否则，什么都不执行。
 *
 * 		B:第二种格式
 * 			if(条件表达式)
 *            {
 * 				语句体1;
 *            }
 * 			else
 *            {
 * 				语句体2;
 *            }
 *
 *
 * 			执行流程：
 * 				如果条件表达式为true，就执行语句体1；
 * 				否则，就执行语句体2；
 *
 * 			特殊：
 * 				可以和条件表达式(三元运算符)在某些情况下进行替换。
 * 				一般是在赋值的情况下可以。
 *
 * 			举例：
 * 				获取两个数中的最大值。
 *
 * 		C:第三种格式
 * 			if(条件表达式1)
 *            {
 * 				语句体1;
 *            }
 * 			else if(条件表达式2)
 *            {
 * 				语句体2;
 *            }
 * 			...
 * 			else
 *            {
 * 				语句体n;
 *            }
 *
 * 			执行流程：
 * 				如果条件表达式1为true，就执行语句体1；
 * 				如果条件表达式2为true，就执行语句体2；
 * 				...
 * 				否则，就执行语句体n；
 *
 * 		D:注意事项
 * 			a:什么时候时候哪一种if语句。
 * 				第一种格式在判断条件为一种情况下使用。
 * 				第二种格式在判断条件为两种情况下使用。
 * 				第三种格式在判断条件为多种情况下使用。
 * 			b:每一种if语句其实都是一个整体，如果有地方执行了，
 * 			  其他的就不执行了。
 * 			c:如果if或者else里面控制的语句体是一条语句，是可以省略大括号的，
 * 			  但是，如果是控制多条语句，就必须写上大括号。
 * 			  建议：永远写上大括号。
 * 			d:大括号和分号一般不同时出现。
 *
 * 		E:作用域
 * 			所有变量的定义只在它所属的大括号内有效。
 * 暂留
 * 	(3)案例：
 * 		A:根据键盘录入的成绩，判断等级。
 * 		B:根据键盘录入的月份，输出该月份所对应的季节。
 *
 *
         * 4：switch语句(掌握)
         * 	(1)用于做选择使用的。一般用于几个常量的判断。* 		switch会把几个常量值直接加载到内存，在判断的时候，效率要比if高。
         * 		所以，针对几个常量的判断，一般选择switch语句。
         *(2)switch语句的格式：
         *switch(表达式)//byte,short,int,char,String
         *{
         *case 值1:
         *语句体1;
         *break;
         *case 值2:
         *语句体2;
         *break;
         *case 值3:
         *语句体3;
         *break;
         *...
         *default:
        *语句体n;
        *break;
        *}
        *
        *A:针对格式的解释
        *switch:表示这里使用的是switch语句，后面跟的是选项。
        *表达式：byte,short,int,char
        *JDK5以后可以是枚举(就业班讲)
        *JDK7以后可以是字符串(后面讲)
        *case:表示这里就是选项的值，它后面的值将来和表达式的值进行匹配。
        *case后面的值是不能够重复的。
        *break:
        *switch语句执行到这里，就结束了。
        *default:
        *当所有的case和表达式都不匹配的时候，就走default的内容。
        *它相当于if语句的else。一般不建议省略。
        *B:执行流程
        *进入switch语句后，就会根据表达式的值去找对应的case值。
        *如果最终没有找到，那么，就执行default的内容。
        *
        *C:注意事项：
        *a:default整体可以省略吗?
        *可以，但是不建议。
        *b:default的位置可以放到前面吗?
        *可以，但是不建议。
        *c:break可以省略吗?
        *可以，但是不建议。
        *default在最后，break是可以省略的。
        *case后面的break可以省略，但是结果可能有问题。
        *d:switch语句什么时候结束呢?
        *就是遇到break或者执行到程序的末尾。
        *(3)案例：
        *A:根据键盘录入的日期(1-7),输出对应的星期日期。
        *B:根据键盘录入的月份，输出该月份所对应的季节。(选做)
        * /*暂留
 * 5：Scanner的使用(掌握)
 * 	(1)Scanner是JDK5以后设计的用来接收键盘录入数据使用的。
 * 	(2)目前我们要通过键盘录入int类型数据,必须按照如下步骤：
 * 		A:导包
 * 			import java.util.Scanner;
 * 		B:创建对象,封装键盘录入
 * 			Scanner $$$$ = new Scanner(System.in);
 * 		C:调用方法,获取数据
 * 			int number = $$$$.nextInt();
 * 6:
 * 	for(变量初始化;循环判断条件;循环变量变化){
 * 		执行循环体
 * 	}
 * 	for(1;2;4){
 * 	    3
 * 	}
 * 	1可以省略，需要放在循环前
 * 	2不可以省略，省略会出现死循环
 * 	3省略没有意义，不可以省略
 * 	4可以省略，放在循环体最后
 * */
public class OperatorTest {
    /**
     * 算数运算符：
     * +：
     * 1、数学意义上的加法运算
     * 2、指定一个数字为正数
     * 3、可以连接字符串：
     * a)两个字符串可以直接相加(相连接)
     * b)一个字符串直接加上数字类型，是将数字类型变成字符串，再连接
     * c)一个数字直接加上字符串，是将数字类型变成字符串，再连接
     * d)字符串不是直接与数字连接，则看运算规则
     * <p>
     * -:
     * 1、数学意义上的减法运算
     * 2、指定一个数字为负数
     * <p>
     * *:
     * 1、数学意义上的乘法法运算
     * /(整除运算)：
     * 1、两个整数相除，使用/可以得到其整数部分
     * 2、含有小数相除，则为数学意义上的除法运算
     * %(取模运算):
     * 1、两个数字相除，使用%可以得到其小数(余数)部分
     * 2、符号与被除数相同
     */
    @Test
    public void test1() {
        // 赋值运算符:=
        // byte b = 10;
        // int i1 = 10;
        // int i2 = 20;
        // System.out.println(i1 + i2);
        int a = 3;
        System.out.println(a++ * ++a); // 3 * 5 = 15
        System.out.println(a++ + ++a); // 5 + 7 = 12

        int i = 20;
        int i2 = 30;
        int i3 = -21;
        int i4 = -30;

        double d = 101.1;
        double d2 = 50.5;

        System.out.println(i % i3);//正数与负数取模，得正数
        System.out.println(i3 % i2);//负数与正数取模，得负数
        System.out.println(i3 % i4);//负数与负数取模，得负数

        System.out.println(i % i2);//正数与正数取模，得正数
        System.out.println("=====================================");
        System.out.println(i2 % i);

        System.out.println(i % d);
        System.out.println(d % d2);

        System.out.println(i - i2);
        System.out.println(i * i2);
        System.out.println("==============================");
        System.out.println(i / i2);
        System.out.println(i / d);
        System.out.println(d / i);
        System.out.println(d / d2);
        /*
		String s = "hi";
		String s2 = " dream!";
		System.out.println(s+i+i2);
		System.out.println(i+i2+s);
		*/
    }

    /**
     * 算数运算符：
     * ++，--
     * 单独使用时：
     * 将变量加1，赋值给变量
     * 在与其他使用方法配合使用时：
     * 当++在变量后时，先使用，再加1
     * 当++在变量前时，先加1，再使用
     */
    @Test
    public void test2() {
        int i = 10;

        int j = i++; //先使用i的值，所以j=10,
        System.out.println(i); // 接上，在使i得值加1，所以i=11
        System.out.println(j); // 11
        System.out.println("==========================");
        int k = ++i;// 先使i自增1为i=12，上面11
        System.out.println(i); // 12
        System.out.println(k); // 12
        System.out.println("=========================");
        System.out.println(i++);//先输出i的值为12
        System.out.println(++i);//上面的i=13,在自增1为14

        System.out.println("++++++++++++++++++++++++++++++++++++++");
        //单独使用++在后
        i++;//i = i+1;
        System.out.println(i); // 15
        //单独使用++在前
        ++i;//i = i+1;
        System.out.println(i); // 16
    }

    /**
     * 赋值运算符：
     * =：等号右边的数赋值给等号左边
     * +=，-=，*=，/=，%=:等号左边与等号右边进行计算后，赋值给等号左边
     */
    @Test
    public void test3() {
        /*
        int a,b,c;
      	a=b=c=3; //不建议使用该种赋值方法
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
        System.out.println("==================");
	    */

        int i = 100;
        int j = 200;
        i += 50;
        //i /= 0;0不能做除数
        //i %= 0;

        byte b = 100;
        // b = b + 10; // b + 10提升为int类型，赋值给byte会损失精度

        b += 10;//在类似+=这样的赋值运算符当中，有一步隐藏的强转动作即：b = (byte)(b+10)
        // b = (byte)(b+10);
        System.out.println(b);
    }

    /**
     * 比较运算符：
     * ==，!=,>,<,>=,<=
     * 当比较运算符进行比较之后，得到一个布尔值
     */
    @Test
    public void test4() {
        int a = 10;
        int b = 20;

        System.out.println(a == b); // false
        System.out.println(a != b); // true
        System.out.println(a > b); // false
        System.out.println("====================");
        System.out.println(a < b); // true
        System.out.println(a >= b); // false
        System.out.println(a <= b); // true
        //System.out.println(a==b=10);方法不可取
    }

    /**
     * 逻辑运算符：运算结果为一个布尔类型的值
     * &：与    and的意思，并且。规则：有假则假
     * |: 或    or的意思，或者。规则：有真则真
     * ^: 异或  规则：相同则假，不同则真   可使用结婚原则
     * !: 非    规则：取反   单目运算符
     * <p>
     * 短路：在能判断出结果时，就不再进行后边的判断了(即后边的代码不再执行！)
     * &&:短路与 与与的规则相同：有假则假
     * ||:短路或 与或的规则相同：有真则真
     * <p>
     * 其他注意事项：
     * 1、当在短路判断时,如果能判断出结果，则短路后的内容不再变化
     * 2、不要连续使用比较运算符，可能出现类型不匹配的问题
     */
    @Test
    public void test5() {
        int x = 10;
        //System.out.println(3<x<6);
        //System.out.println(true<6);
        //boolean bl = x>3;
        //boolean bl2 = x<6;

        //System.out.println(bl&bl2);

        System.out.println(x > 3 && x < 6); // false
        //System.out.println();
        System.out.println("========================");

        int a = 10;
        int b = 11;
        System.out.println(a == b & b == a++); // false 先判断a==b为假，再判断b==a++,这里先做a++,但因++在后，还是先用a的值，之后再自增1
        System.out.println(a); // 接上，所以这里a的值为11
        System.out.println(b); // 11

        System.out.println(a != b && b == a++); // 接上 a = 11, b = 11 false
        System.out.println(a); // 11，接上，因为a!=b为false，而且&&具有短路功能，所以b == a++不执行，所以a的值还是11
        System.out.println(b); // 11


        System.out.println("========================");

        System.out.println(true & false); // false
        System.out.println(false & true); // false
        System.out.println(true & true); // true
        System.out.println(false & false); // false
        System.out.println("========================");

        System.out.println(true && false); // false
        System.out.println(false && true); //false
        System.out.println(true && true); // true
        System.out.println(false && false); // false
        System.out.println("========================");

        System.out.println(true | false); // true
        System.out.println(false | true); // true
        System.out.println(true | true); // true
        System.out.println(false | false); // false
        System.out.println("========================");

        System.out.println(true || false); // true
        System.out.println(false || true); // true
        System.out.println(true || true); // true
        System.out.println(false || false); // false
        System.out.println("========================");

        System.out.println(true ^ false); // true
        System.out.println(false ^ true); // true
        System.out.println(true ^ true); // false
        System.out.println(false ^ false); // false
        System.out.println("========================");

        System.out.println(!true); // false
        System.out.println(!false); // true
        System.out.println("========================");

		/*
		int a = 10;
		int b = 20;

		System.out.println(a==b&a!=b);
		//System.out.println(false&true);
		*/
    }

    /**
     * 位运算符：
     * 对于以下四种位运算符，直接将0认为false，1认为true，计算规律与逻辑运算符规律完全一致
     * &:有0则0
     * |:有1则1
     * ^:相同则0,不同则1
     * ~:1变0,0变1。注意：与负数的原码取反码不同，是将每位上的数字均取反
     */
    @Test
    public void test6() {
        int x = 6;
        int y = 3;
        // 8bit = 1byte
        // int四个字节，四个八位
        //x的二进制原码：00000000 00000000 00000000 00000110
        //y的二进制原码：00000000 00000000 00000000 00000011

		/*
			00000000 00000000 00000000 00000110
		 & 00000000 00000000 00000000 00000011
		   -----------------------------------------------
		    00000000 00000000 00000000 00000010
		*/
        System.out.println(x & y); // 2
		/*
			00000000 00000000 00000000 00000110
		  | 00000000 00000000 00000000 00000011
		   -------------------------------------------------
			00000000 00000000 00000000 00000111
		*/
        System.out.println(x | y); // 7
		/*
			00000000 00000000 00000000 00000110
		  | 00000000 00000000 00000000 00000011
		   -------------------------------------------------
			00000000 00000000 00000000 00000101
		*/
        System.out.println(x ^ y); // 5
		/*
		 ~ 00000000 00000000 00000000 00000110
			---------------------------------------------
			 11111111 11111111 11111111 11111001(补码)(最高位为1，为负数，补码变反码-1)
			 11111111 11111111 11111111 11111000(反码)(符号位不变，其他各位取反)
			 10000000 00000000 00000000 00000111(原码)
			 -7
		*/
        System.out.println(~x); //-7
    }

    /**
     * 位运算符：
     * 左移：<<
     * 将所有的数向左移动指定的位数
     * 右移：>>
     * 包含符号的移动：即首位为几，则前边所有位数均补齐几
     * 无符号右移：>>>
     * 不包含符号的移动：即无论之前是1还是0，均用0补齐
     * <p>
     * 知道的人很简单，不知道的人很头疼的面试题：
     * 使用效率最快的方法，将变量x(值为10)乘以8=2^3.
     */
    @Test
    public void test7() {
        int x2 = 10;
        System.out.println(x2 << 3); // 80

        int z = 2;
        System.out.println(z << 2); // 8

        int x = 3;
        //x的原码：|00000000 00000000 00000000 00000011|
        // 向左移两位   00|000000 00000000 00000000 0000001100|
        System.out.println(x << 2); // 12
        int y = 8;
        //y的原码：00000000 00000000 00000000 00001000
        System.out.println(y >> 2); // 2
        System.out.println(y >>> 2); // 2
        System.out.println("================");
        System.out.println(16 / 4);
        System.out.println(16 >> 2);
        System.out.println(16 >>> 2);
        System.out.println(16 << 2);
    }

    /**
     * 1：使用第三方变量，将两个变量的数值交换
     * 2：不使用第三方变量，将两个变量的数值交换
     */
    @Test
    public void test8() {

        int x = 10;
        int y = 20;

        //需要的结果：x = 20  ，y = 10
        //使用第三方变量交换,在日常的开发过程当中，基本上全部使用这种方式。该动作是相对频繁的。
        int temp = x;
        x = y;
        y = temp;
        System.out.println(x); // 20
        System.out.println(y); // 10

        //不使用第三方变量，将两个变量的数值交换
        int x2 = 10;
        int y2 = 20;

        x2 = x2 ^ y2;//x2 = x2^y2   ,  y2 = y2
        y2 = x2 ^ y2;//x2 = x2^y2   ,  y2 = x2^y2^y2 = x2,
        x2 = x2 ^ y2;//x2 = x2^y2^x2=y2  ,  y2 = x2

        System.out.println(x2); // 20
        System.out.println(y2); // 10


        //提示：在位运算当中,当一个数异或另外一个数两次后，结果仍为这个数，利用这个规律可以做第二题。
        int a = 12;
        int b = 21;

        System.out.println(a ^ b); // 25
        System.out.println(a ^ b ^ b); // 12
        System.out.println(a ^ b ^ a); // 21
    }

    /**
     * 三元运算符：
     * 格式：
     * (条件表达式)?表达式1：表达式2；
     * 运算顺序：
     * 如果条件为true，运算后的结果是表达式1；
     * 如果条件为false，运算后的结果是表达式2；
     * <p>
     * 简单习题：使用三元运算符获取两个数字当中较大的那个数。
     */
    @Test
    public void test9() {
        int x = 300;
        int y = 200;
        //习题题解：
        System.out.println(x > y ? x : y);
        //考虑如果两数相等，该解法成立么？同样成立。

        //三元运算符的嵌套问题：
        System.out.println(x > 200 ? (y < 40 ? 100 : 200) : (y > 100 ? 300 : 400));

		/*
		//一?二:三
		//一：必须是一个布尔值
		//二、三：可以是表达式，可以是数值

		int x = 10;
		int y =20;
		System.out.println(x>y?4:2);
		*/

        // 三元运算符测试NPE问题
        // 三元运算符中，表达式1和2在涉及算术运算或数据类型转换时，会触发自动拆箱，当其中的操作数为null值时，会导致NPE
        // 1.表达式1或表达式2的值只要有一个是原始类型
        // 2.表达式1或表达式2的值的类型不一致，会强制拆箱升级成表示范围更大的那个类型
        Integer a = 1;
        Integer b = 2;
        Integer c = null;
        boolean flag = false;
        // a * b的结果是int类型，那么c会强制拆箱成int类型，抛出NPE异常
        // System.out.println((flag ? a * b : c)); // java.lang.NullPointerException
    }

}
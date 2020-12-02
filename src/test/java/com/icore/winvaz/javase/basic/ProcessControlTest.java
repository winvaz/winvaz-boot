package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * 1：循环(掌握)
 * (1)如果我们发现有很多重复的内容的时候，就该考虑使用循环改进代码。
 * 让我们代码看起来简洁了。
 * (2)循环的组成
 * A:循环体,就是要做的事情。
 * B:初始化条件。一般定义的是一个初始变量
 * C:判断条件。用于控制循环的结束。
 * D:变量的变化。一般都是一个++/--操作。
 * (3)循环的分类：
 * A:for
 * for(初始化条件;判断条件;控制条件)
 * {
 * 循环体;
 * }
 * <p>
 * 执行流程：
 * a:先执行初始化条件;
 * b:执行判断条件
 * c:根据判断条件的返回值：
 * true:执行循环体。
 * false:就结束循环。
 * d:最后执行控制条件。返回到b继续。
 * <p>
 * B:while
 * 初始化条件;
 * while(判断条件)
 * {
 * 循环体;
 * 更改循环变量;
 * }
 * <p>
 * 执行流程：
 * a:先执行初始化条件;
 * b:执行判断条件
 * c:根据判断条件的返回值：
 * true:执行循环体。
 * false:就结束循环。
 * d:最后执行控制条件。返回到b继续。
 * <p>
 * C:do...while(了解)
 * 初始化条件;
 * do{
 * 循环体;
 * 控制条件;
 * }while(判断条件);
 * <p>
 * 执行流程：
 * a:先执行初始化条件;
 * b:执行循环体和控制条件;
 * c:执行判断条件
 * d:根据返回值
 * true:返回b。
 * false:就结束循环。
 * <p>
 * 注意：
 * a:一般使用for循环或者while循环。而且这两种循环是可以等价转换的。
 * b:do...while循环至少执行一次循环体。
 * <p>
 * *(4)案例：(掌握)
 * A:请在控制台输出5次"我爱Java"
 * B:请在控制台输出1-10的数据。
 * C:求1-10的和，改进位求1-100的和。
 * D:求1-100之间偶数的和。
 * E:求5的阶乘。
 * F:水仙花。
 * G:统计叠多少次，能叠成珠穆朗玛峰的高度。
 * (5)循环嵌套：(理解)
 * A:也就是循环语句的循环体是一个循环语句。
 * B:通过输出
 * ****
 * ****
 * ****
 * 我们不断的改进。发现了一个问题：
 * 外循环控制行数，内循环控制列数。
 * (6)案例：(理解)
 * A:正三角形
 * 内循环的判断条件：y<=x
 * <p>
 * for(int x=0; x<5; x++)
 * {
 * for(int y=0; y<=x; y++)
 * {
 * System.out.print("*");
 * }
 * System.out.println();
 * }
 * <p>
 * B:倒三角形
 * ****
 * ***
 * **
 * *
 * 内循环的初始化条件：y=x
 * <p>
 * for(int x=0; x<5; x++)
 * {
 * for(int y=x; y<5; y++)
 * {
 * System.out.print("*");
 * }
 * System.out.println();
 * }
 * <p>
 * C:九九乘法表(掌握)
 * <p>
 * <p>
 * 2：break和continue(掌握)
 * (1)有些时候，我们需要对循环进行一些控制终止,这个时候，就出现了两个关键字：
 * break和continue
 * (2)特点：
 * A:它们都必须在循环中(break还可以在switch中。)。
 * 一般在循环的判断中。
 * B:如果单独使用break和continue，后面是不能有语句的。
 * (3)区别：
 * A:break 结束当前循环。
 * B:continue 结束本次循环，进入下一次循环。
 * (4)如何退出嵌套循环：(了解)
 * 用带标签的循环。
 * 格式:
 * 标签名：for(){
 * for(){
 * if()
 * {
 * break 标签名;
 * }
 * }
 * }
 * <p>
 * 3：应用场景(理解)
 * (1)变量：发现有一个数据是变化的时候，就要用变量。
 * (2)if语句：如果是一个范围的判断，boolean类型的表达式的判断，几个数据的判断。
 * (3)switch语句：几个数据的判断。一般这种情况，有限选择switch。
 * (4)for语句：如果次数或者范围特别明确。(打印1-100之间所有数，打印矩形)
 * (5)while语句：如果次数或者范围不明确。(珠穆朗玛峰)
 * <p>
 * *4：函数的定义格式：
 * 修饰符 返回值类型 函数名(参数类型 形式参数1，参数类型 形式参数2，⋯){
 * 执行语句;
 * return 返回值;
 * }
 * 修饰符：public static
 * 返回值类型：因为函数的功能是打印一个数字，并不需要它返回一个数值，所以返回值类型为空（void）！还要说明
 * 函数名：我们自己给函数起的名字：符合标识符的小驼峰式
 * 参数：要给方法传入的数据！还要说明
 * 执行语句：方法体内要执行的逻辑，根据业务需求
 * return ：对于返回值为void的方法，return语句可以不写。！还要说明
 * <p>
 * 把每个组件的功能说清楚即可：打印两个数当中较大的那个数
 */
public class ProcessControlTest {
    /**
     * if语句：
     * 条件控制语句，当满足条件时，可以执行指定代码。
     * <p>
     * 格式一：
     * if(条件表达式){
     * 执行语句；
     * }
     * 对于格式一：如果满足条件，则执行条件体，不满足，则直接跳出条件判断语句。
     * 这种定义方式，可能会使语句当中的代码一行都不执行。
     * <p>
     * 格式二:
     * if(条件表达式){
     * 执行语句；
     * }else {
     * 执行语句；
     * }
     * 对于格式二：如果条件满足，执行if后语句，不满足，执行else后语句。
     * 这种定义方式，一定会执行条件控制当中的语句。
     * <p>
     * 格式三：
     * if(条件表达式){
     * 执行语句；
     * }
     * else if (条件表达式){
     * 执行语句；
     * }
     * ⋯⋯
     * else {
     * 执行语句；
     * }
     * 对于格式三：
     * 1、要充分考虑边界值问题
     * 2、每种情况不要重叠数值区域，因为只要有一种情况的代码执行了，则其他情况直接跳过。跳出if语句。
     * 如果重复，则重复的部分不会到后边的条件所在代码中执行。这样就没有意义。
     * 3、如果条件满足，执行if后语句，不满足，执行else后语句。
     * 这种定义方式，一定会执行条件控制当中的语句。
     * <p>
     * 注意事项：
     * 1、在条件控制语句当中也可以赋值
     * 2、不要将==写成=
     * 3、if语句后如果只有一条语句，则可以省略大括号
     * 4、if语句可以与三元运算符通用么？
     */
    @Test
    public void test() {
        //格式三举例：
        //一个学校学生的学习成绩，如果是90-100为优秀，如果是80-89为优良，
        //如果是60-79为及格，如果60以下为不及格
        //业务要求，不能出现负数以及100以上的分数
        int score = 95;
        if (90 <= score && score <= 100) {
            System.out.println("成绩优秀！");
            System.out.println("再接再厉！");
        } else if (80 <= score && score <= 89) {
            System.out.println("成绩优良！");
        } else if (60 <= score && score <= 79) {
            System.out.println("成绩及格！");
        } else if (0 <= score && score <= 59) {
            System.out.println("成绩不及格！");
        } else {
            System.out.println("您输入的成绩有误！");
        }


        //格式一举例：
        int a = 20;
        int b = 40;

        boolean bl = a != b;

        if (bl = false) {
            System.out.println("a与b相等");
        }
		/*
		//格式二举例：
		if(a<b){
			System.out.println("a和b两个数中，较大的是a");
		}else {
			System.out.println("a和b两个数中，较大的是b");
		}
		System.out.println("Hello World!");
		*/
    }

    /**
     * switch:
     * 格式：
     * switch(表达式){
     * case 取值1:
     * 执行语句;
     * break;
     * case 取值2:
     * 执行语句;
     * break;
     * ⋯
     * default:
     * 执行语句;
     * break;
     * }
     * switch的结束条件：
     * 第一种：
     * 遇到break会结束switch语句
     * 第二种：
     * 执行到大括号结束，结束switch语句
     * <p>
     * 注意事项：
     * 对于switch后的变量，必须是以下数据类型：	byte，short，int，char，String(1.7)
     * 对于switch语句的case(default)情况,只在进入switch时判断一次，之后不再判断。击穿穿透效果
     * 在语句内，当遇到break时，跳出语句，否则继续执行
     * 在同一种情况下，如果将语句写在break后，则报错：无法被访问到
     * default放在最后时，break可以省略，但是不建议省略(保持格式良好)
     * default语句可以放到前边，但是，必须有break,但是不建议
     */
    @Test
    public void test2() {
        //分数问题：1-6分   5分以上为通过，3-4分为待定，1-2分为不通过
        int score = 10;

        switch (score) {
            case 1:
                System.out.println("不通过");
                break;
            case 2:
                System.out.println("不通过");
                break;
            case 4:
                System.out.println("待定");
                break;
            //System.out.println("这是4分的待定"); // 无法执行到，因前有break
            case 3:
                System.out.println("待定");
                break;
            case 5:
                System.out.println("通过");
                break;
            case 6:
                System.out.println("通过");
                break;
            default:
                System.out.println("您输入的分数有误");
                break;
        }
        System.out.println("Hello World!");
    }

    /**
     * 使用"*"打印1115次。
     * 为了将重复的代码进行复用，实现相同或类似的功能，我们使用循环来解决问题。
     * 可以使用三种循环：
     * for循环√
     * while循环√
     * do while循环√×
     * <p>
     * for循环：
     * for(初始化表达式; 循环条件表达式; 循环后的操作表达式){
     * 执行语句；(循环体)
     * }
     * for循环运算顺序：
     * for(1; 2; 4){
     * 3
     * }
     * <p>
     * 循环所具备的条件：
     * 1、初始化一个循环变量，通常是从0开始
     * 2、需要判断条件，作为循环出口
     * 3、循环体
     * 4、对循环变量的操作(经常为++，--)
     * <p>
     * 死循环：
     * 循环变量(类似循环变量的常量)一直无法满足循环条件表达式的，则无法跳出循环，会出现死循环。
     * 一般情况下，必须保证可以达到循环条件表达式的要求，有些时候，可以使用break/continue来跳出循环
     */
    @Test
    public void test3() {

		/*
	    需求：
		    1、使用"*"打印指定数量的*。
		        使用"*"每行打印指定数量的*，打印5行，形成一个矩形
			    如果想直接换行，什么都不操作：System.out.println();System.out.println("");
		    2、输出从1-10。
		    将数字从1加到10,得到和。
        */
        for (int i = 0; i < 5; i++) {
            System.out.println("*");
        }

        int sum = 0;
        for (int i = 1; i < 11; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    /**
     * for循环：
     * for(初始化表达式; 循环条件表达式; 循环后的操作表达式){
     * 执行语句；(循环体)
     * }
     * <p>
     * for循环运算顺序：
     * for(1; 2; 4){
     * 3
     * }
     * for (int i=1; i<111115; i*=2){
     * System.out.print("*");
     * }
     * <p>
     * 题干：
     * 1，输出10以内所有奇数
     * 2，输出100以内(不包括0)所有3的倍数，求出一共有多少个3的倍数
     * <p>
     * 注意：
     * for循环当中，如果将变量定义在初始化表达式的位置(1)的位置，则该变量属于for循环内的变量，在跳出for
     * 循环后，变量消失。后边可以重新定义一个变量与该变量重名。
     */
    @Test
    public void test4() {
        //第一题：
        for (int i = 1; i < 10; i++) {
            if (i % 2 == 1) {
                if (i == 9)
                    System.out.print(i);
                else
                    System.out.print(i + ",");
            }
        }
        System.out.println("==========================");
        //第二题
        //定义一个变量，来记录3的倍数的个数
        int count = 0;
        for (int i = 1; i < 101; i++) {
            if (i % 3 == 0) {
                // System.out.println(i);
                count++;
            }
        }
        System.out.println(count);
        System.out.println("=======================");
        // 计算从1~50个数字中取6个数字来抽奖
        BigInteger lotteryOdds = BigInteger.valueOf(1);
        for (int i = 1; i <= 60; i++) {
            // lotteryOdds = lotteryOdds * (50 - i + 1) / i;
            // 换成大数计算
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(490 - i + 1)).divide(BigInteger.valueOf(i));
        }
        System.out.println("You odds are 1 in " + lotteryOdds + ", Good Luck !");
    }

    /**
     * 循环所具备的条件：
     * 1、初始化一个循环变量，通常是从0开始
     * 2、需要判断条件，作为循环出口
     * 3、循环体
     * 4、对循环变量的操作(经常为++，--)
     * <p>
     * while循环也应该具备以上条件
     * 格式：
     * while(条件表达式){
     * 执行语句；
     * }
     * <p>
     * 修改后while的格式：
     * 初始化循环变量(初始化表达式)
     * while(条件表达式){
     * 执行语句；
     * 对循环变量的操作
     * }
     * <p>
     * 执行顺序：
     * 1
     * while(2){
     * 3
     * 4
     * }
     * 使用while语句完成for循环的例子：
     * 1，将"*"输出10000遍
     * 2，将1-10输出
     * 3，将1-100之间所有的奇数相加得到和
     */
    @Test
    public void test5() {
        //第三题
        int number3 = 1;
        int sum = 0;
        while (number3 <= 100) {
            if (number3 % 2 == 1)
                sum = sum + number3;
            number3++;
        }
        System.out.println(sum);


		/*第二题
		int number2 = 1;
		while (number2<=10)
		{
			System.out.println(number2++);
		}

		int number3 = 0;
		while (number3<10)
		{
			System.out.println(++number3);
		}
		*/
		/*第一题
		int number = 0;
		while (number<10000)
		{
			System.out.print("*");
			number++;
		}
		*/
    }

    /**
     * 循环所具备的条件：
     * 1、初始化一个循环变量，通常是从0开始
     * 2、需要判断条件，作为循环出口
     * 3、循环体
     * 4、对循环变量的操作(经常为++，--)
     * <p>
     * do while循环也应该具备以上条件
     * 格式：
     * do{
     * 执行语句；
     * }while(条件表达式);
     * <p>
     * <p>
     * 修改后do while的格式：
     * 初始化循环变量(初始化表达式)
     * do{
     * 执行语句；
     * 对循环变量的操作
     * }while(条件表达式);
     * <p>
     * 执行顺序：
     * 1
     * do{
     * 2
     * 3
     * }while(4)
     * <p>
     * 对于dowhile来讲，while循环可以做的，dowhile都可以
     * 使用dowhile语句完成：
     * 1，将"*"输出10000遍
     * 2，将1-10输出
     * 3，将1-100之间所有的奇数相加得到和
     */
    @Test
    public void test6() {
        //第三题
        int number3 = 1;
        int sum = 0;
        do {
            if (number3 % 2 == 1)
                sum = sum + number3;
            number3++;
        } while (number3 <= 100);
        System.out.println(sum);
		/*第二题
		int number2 = 1;

		do
		{
			System.out.println(number2);
			number2++;
		}
		while (number2<11);
		*/
		/*第一题
		int number = 0;
		do
		{
			System.out.print("*");
			number++;
		}while (number<10000);
		*/
    }

    /**
     * 不同循环的使用场景：
     * for：
     * 明确循环次数时，一般使用for循环
     * while和dowhile:
     * 当不确定循环次数时，通常使用while循环
     * <p>
     * for和while：
     * 均是先判断循环条件，再执行循环体，如果第一次判断就不符合条件，循环体一次都不执行
     * do while：
     * 先执行循环体，再判断条件，如果继续满足条件，则继续执行循环体，否则跳出循环
     * <p>
     * 日常开发过程当中，通常使用for循环与while循环，如果不是为了至少运行一遍循环体，则不会使用do while循环
     * <p>
     * 珠峰问题：
     * 我国最高山峰是珠穆朗玛峰，8848米。现在我有一张足够大的纸，它的厚度是0.01厘米。
     * 请问，我折叠多少次，可以折成珠穆朗玛峰的高度。
     */

    @Test
    public void test7() {
        /*
         * 分析：
		    1、需要一个目标高度，需要记录纸的当前厚度，折叠的次数
		    2、统一变量的单位目标高度为88480000，纸的厚度为1
		    3、每回纸的厚度都会变成之前厚度的2被
		    4、折纸的厚度超过或者等于珠峰高度时，不再折叠
         */
        // 目标高度
        int fHeight = 88480000;
        // 当前纸张的高度
        int zHeight = 1;
        // 折叠次数
        int times = 0;
        // 只要纸张的厚度小于珠峰的高度，就一直循环
        while (zHeight <= fHeight) {
            // 每次对折，纸张的厚度变为原来的二倍
            zHeight *= 2;
            // 没折叠一次，则折叠次数加1
            times++;
        }
        System.out.println(times);
    }

    /**
     * 使用for循环，完成一个指定行数，指定每行个数的矩形，用"*"来完成
     * *****
     * *****
     * *****
     * <p>
     * 类似以下的for循环的使用方法叫做for循环的嵌套，也叫做双层for循环
     * 对于该例程：外层循环控制行数，内层循环控制每行的个数
     */
    @Test
    public void test8() {
        // 外层循环控制行数
        for (int j = 0; j < 3; j++) {
            // 内层循环控制列数
            for (int i = 0; i < 5; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
        //i与j均是属于循环内部的变量，如果在循环外访问，则找不到符号
        //System.out.println(i);
        //System.out.println(j);
    }

    /**
     * 打印一个正三角，形状如下，要求必须使用for循环，不可以逐行打印
     * <p>
     * *
     * **
     * ***
     */
    @Test
    public void test9() {
		/*
			发现规律：
				第一行：j=0  x=1
				第二行：j=1  x=2
				第三行：j=2  x=3
				...
				j永远比x小1
				所以x的值与j+1的值相同
		*/
        /*第三版*/
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
		/*第二版
		for (int j=0; j<4; j++)
		{
			for (int i=0; i<(j+1); i++){
				System.out.print("*");
			}
			System.out.println();
		}
		*/
		/*
		int x = 1;
		for (int j=0; j<4; j++)
		{
			for (int i=0; i<x; i++){
				System.out.print("*");
			}
			System.out.println();
			x++;
		}
		*/
    }

    /**
     * 打印九九乘法表:
     * 1*1=1
     * 1*2=2	2*2=4
     * 1*3=3	2*3=6	3*3=9
     * 。。。
     * 1*9=9	2*9=18	...   9*9=81
     * <p>
     * 提示：
     * 将正三角代码修改成九九乘法表，中间的空格可以使用转义字符\t，将数据格式化
     * 如果无法一步完成，可以先写成：
     * 1
     * 2 4
     * 3 6 9
     * ...
     * 9 18 27 ...81
     */
    @Test
    public void test10() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                //换成相对应的乘法式子。里边的乘数与被乘数均由i，j控制
                System.out.print(j + "*" + i + " = " + (i * j) + " \t");
            }
            System.out.println();
        }
    }

    /**
     * 循环特殊写法：
     * while：while后的条件表达式，可以写成while(true)，这时，将陷入死循环，如果不跳出循环，则一直循环下去
     * <p>
     * for：
     * 初始化循环变量可以省略，但是需要在for循环前定义
     * 方法体后对循环变量的操作可以省略，但是需要在循环体后边写出
     * 循环条件可以省略，但是会陷入死循环
     */
    @Test
    public void test11() {
        //for循环的几种特殊写法
		/*
		for (初始化循环变量; 循环条件; 方法体后对循环变量的操作)
		{
			方法体
		}
		*/
        // 死循环
		/*
        for (; ; ) {
            System.out.println("我爱java");
        }
        */
		/*
		int i = 0;
		for (;i<10 ;)
		{
			System.out.println("我爱java");
			i++;
		}
		System.out.println(i);
		*/

		/*初始化循环变量可以写在for循环前
		int i = 0;
		for (;i<10 ; i++)
		{
			System.out.println("我爱java");
		}
		System.out.println(i);
		*/

		/*while死循环
		while (true)
		{
			System.out.println("我爱JAVA！");
		}
		*/
    }

    /**
     * break与continue
     * break：
     * 1、跳出循环语句
     * 2、跳出选择结构语句
     * continue：
     * 跳出当前这次的循环体，进入到下一次循环的循环体
     * <p>
     * 注意事项：
     * 在continue所在范围内，后边不可以再有其他语句，即不可能打印出"我可能不太适合做java"这句话，因为永远无法被执行到。
     * 在break所在范围内，后边不可以再有其他语句，即不可能打印出"我可能不太适合做java"这句话，因为永远无法被执行到。
     */
    @Test
    public void test12() {
		/*
		for (int i=0; i<180; i++)
		{
			if(i==3){
				continue;
				//System.out.println("我可能不太适合做java");
			}
			System.out.println("距离雄起还有"+(180-i)+"天");
		}
		System.out.println("同志们hold住！要淡定，不要烦躁，要冷静，不要急躁！");
		*/

        for (int i = 0; i < 180; i++) {
            System.out.println("距离雄起还有" + (180 - i) + "天");
            if (i == 3) {
                break;
                //System.out.println("我可能不太适合做java");
            }
        }
        System.out.println("同志们hold住！");
    }

    /**
     * 在多层嵌套的循环当中，使用break跳出的是break所在的循环
     * 循环标号：
     * 可以在循环处为循环取一个标号，相当于给循环起一个名字。
     * 标号方法：标识符+：
     * 使用标号，可以将内层循环的break直接跳出外层循环(continue同理，但是较少使用)
     */
    @Test
    public void test13() {
        int a = 10;
        //break与continue必须用于循环语句
        //continue;
        //break;
        int b = 20;

        int c = a + b;
		/*
		out:for (int j=1; j<=9; j++)
		{
			inner:for (int i=1; i<=j; i++){
				System.out.print(i+"*"+j+"="+i*j+"\t");
				continue out;
				//break out;
			}
			System.out.println();
		}
		*/
    }

    @Test
    public void test14() {
        // 当default在中间时，且看输出是什么
        int a = 1;
        switch (a) {
            case 2:
                System.out.println("Print 2");
            case 1:
                System.out.println("Print 1"); // 1 Print 1
            default:
                System.out.println("first default print"); // 2 first default print
            case 3:
                System.out.println("Print 3"); // 3 Print 3
        }
        System.out.println("================");
        // String param = null; // 空指针
        // 当switch括号内的变量为String类型的外部参数时，且看输出是什么？
        // java.lang.NullPointerException，null不是字符串，为空引用
        String param = "null";
        switch (param) {
            case "param":
                System.out.println("print param");
                break;
            case "String":
                System.out.println("print String");
                break;
            case "null":
                System.out.println("print null");
                break;
            default:
                System.out.println("second default print");
        }
    }
}
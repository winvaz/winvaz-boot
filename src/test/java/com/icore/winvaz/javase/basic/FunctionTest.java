package com.icore.winvaz.javase.basic;


import org.junit.jupiter.api.Test;

/**
 * @Deciption 函数(方法)
 * @Author wdq
 * @Create 2020/3/24 22:52
 * @Version 1.0.0
 */
public class FunctionTest {
    /**
     * 函数：
     * 独立的一小段功能代码
     * 格式：
     * 修饰符 返回值类型 函数名(参数类型 形式参数1，参数类型 形式参数2，⋯)
     * {
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
     * 对于函数：
     * 函数与函数之间的关系是平等的，他们通常都直接定义在类中，除非出现内部类，否则不会定义在另外一个函数内
     * 函数必须调用，不调用不执行，先定义，后调用。在其他函数当中可以调用。
     * <p>
     * 函数的调用：
     * 函数名，传入相应的参数。
     */
    @Test
    public void test() { //将两个数相加，并打印出结果
        //调用定义好的函数
        myFirstMethod(40, 30);
        myFirstMethod(50, 60);
    }

    //定义了一个参数为两个整型，返回值为void，功能为打印这两个数的和的函数
    public static void myFirstMethod(int a, int b) {
        int sum = a + b;
        System.out.println(sum);
    }
    //定义一个参数为两个整型，返回值为void，功能为打印这两个数当中较大的那个数，并且在mian方法当中调用

    /**
     * 打印出所有的"水仙花数"。
     * 所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。例如：
     * 153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。
     * 提示：
     * 1：采用循环取得所有的三位数。(三位数指的是100-999之间的数)。
     * 2：把每个三位数的个位，十位，百位进行分解。
     */
    @Test
    public void test1() {
        //定义一个变量，存储水仙花数
        //int number =  153;

        for (int number = 100; number < 1000; number++) {
            int ge = number % 10;
            int shi = number / 10 % 10;
            int bai = number / 100;

            if (ge * ge * ge + shi * shi * shi + bai * bai * bai == number) {
                System.out.println(number);
            }
        }
        System.out.println("Hello World!");
    }

    /**
     * 求5的阶乘。
     * 提示：5的阶乘就是：5*4*3*2*1。
     * 思路1：
     * 5   *4*3*2*1  对于5来讲重复4次循环，每次循环乘以比上次循环少1的数
     * 4   *3*2*1    对于4来讲重复3次循环
     * 6   *5*4*3*2*1对于6来讲重复5次循环
     * <p>
     * 初步分析：
     * 1、number变量来记录要求阶乘的这个数，times记录循环次数，result记录最后的结果
     * 2、每次循环之后让number-1
     * 3、开始的result值即为number值，后边一次乘以number
     */
    @Test
    public void test2() {
        int number = 6;
        int times = number - 1;
        int result = number;
        /*
        for (int i = 0; i < times; i++) {
            System.out.println("当前的number=" + number);
            result = result * (number - 1);
            System.out.println("计算之后的结果result为" + result);
            // number--;
            --number;
            System.out.println("======================");
        }
        System.out.println(result);
        */
    }

    /**
     * 阶乘的循环实现
     *
     * @param n
     * @return
     */
    public static long method_loop(int n) {
        long result = n;
        while (n > 1) {
            n--;
            result = result * n;
        }
        return result;
    }

    /**
     * 请输出满足这样条件的五位数。
     * 10000-99999
     * 个位=万位
     * 十位=千位
     * 个位+十位+千位+万位=百位
     * 分析：
     * 取值范围：10000-99999，需要一个变量来记录当前这个数
     * 满足三个条件ge=wan shi=qian ge+shi+qian+wan=bai
     * (到该步时，有部分同学使用对称数的算法结构进行计算，可以，但不建议。)
     */
    @Test
    public void test3() {
        //11411
        for (int number = 10000; number < 100000; number++) {
            int ge = number % 10;
            int shi = number / 10 % 10;
            int bai = number / 10 / 10 % 10;
            int qian = number / 10 / 10 / 10 % 10;
            int wan = number / 10 / 10 / 10 / 10 % 10;
            //注意：可以考虑如何用循环将它取出

            if (ge == wan && shi == qian && ge + shi + qian + wan == bai) {
                System.out.println(number);
            }
        }
        System.out.println("Hello World!");
    }

    /**
     * 函数：
     * 修饰符 返回值类型 函数名(参数类型 形式参数1，参数类型 形式参数2，⋯)
     * {
     * 执行语句;
     * return 返回值;
     * }
     * 返回值类型：函数运行后的结果的数据类型。
     * 参数类型：是形式参数的数据类型。
     * 形式参数：是一个变量，用于存储调用函数时传递给函数的实际参数。
     * 实际参数：传递给形式参数的具体数值。
     * (函数调用的过程实际上就是，调用函数，将实际参数传递给形式参数的变量，再使用形式参数进行计算)
     * return：用于结束函数。
     * 返回值：该函数运算的结果，该结果会返回给调用者。
     * <p>
     * 返回三个数当中最大的那个数。
     */
    /**
     * 函数的注意事项：
     * 先定义函数，再调用函数。如果没有定义，直接调用会报错。如果先定义，永久不调用，则该函数没有意义
     * 函数调用才执行，不调用不执行
     * <p>
     * 同一个类中不能定义返回值类型完全一致，并且函数名相同的两个方法 --这条说法是不有错(这叫重载)
     * 同一个类中不能定义参数类型完全一致，并且函数名相同的两个方法
     * 当函数有返回值时，不使用这个返回值，不报错
     * 当函数没有返回值时，使用这个返回值，会报错
     * <p>
     * 静态方法不能调用非静态方法
     * <p>
     * 方法应该直接定义在类中,与其他方法平等.(在不考虑内部类的情况下)
     * 在有非void返回值类型的情况下，至少要有一种情况可以返回return
     * 在返回值类型为void时，函数可以省略return，也可以写成return;的形式
     * 在一个方法中，return后不可以再有其他语句，因为永久性的无法访问到
     * <p>
     * 函数的注意事项2：
     * 不同方法间调用个，被调用的方法内部逻辑与外部方法无关
     * 函数内部变量，在函数调用结束后，随之消失
     * <p>
     */
    @Test
    public void test4() {
        int a = getMax(10, 20);
        System.out.println(a);
        int max = getMax(10, 20, 30);
        System.out.println(max);
        System.out.println(getMyNumber());
    }

    //需求：获取两个数中较大的那个数
    /*
    public static int getMax(int x, int y) {

        //使用三元运算符
        //int max;
        //max = x>y?x:y;
        //return max;
        return x > y ? x : y;
		*//*if方式
		//int max;
		if(x>y){
			return x;
		}else {
			return y;
		}
		//return max;
		*//*
    }
    */

	/*
	需求：打印两个数中较大的那个数
	public static void getMax(int x,int y){
		if(x>y){
			System.out.println(x);
		}else {
			System.out.println(y);
		}
	}
	*/

    //完成返回三个数当中最大值的函数
    /*
    public static int getMax(int x, int y, int z) {
        //使用三元运算符：
        //	return (x>y?x:y)>z?(x>y?x:y):z;
        //建议使用if语句
        int max;
        if (x >= y) {
            max = x;
        } else {
            max = y;
        }

        if (max < z) {
            max = z;
        }
        return max;
    }
    */

    //得到100以内第三个偶数
    public static int getMyNumber() {
        int index = 0;
        int i = 0;
        for (; i <= 100; i++) {
            if (i % 2 == 0) {
                index++;
                if (index == 3) {
                    //System.out.println(i);
                    break;
                }
            }
        }
        return i;
        //System.out.println("函数调用完毕！！");
    }

    /**
     * 函数重载：
     *      * 在同一个类中，允许存在一个以上的同名函数，只要它们的参数个数或者参数类型不同即可。
     *      * 在一个类中有两个方法，他们具有相同的名字，但有不同的参数列表。
     */
    /**
     * 函数重载的注意事项：
     * 1、在调用重载方法时，会根据形参类型进行方法的调用
     * -在重载方法调用时，如果没有精确的类型匹配，会寻找近似的类型匹配，如果没有近似的，则报错。
     * 在寻找近似类型匹配的方法时，遵循隐式类型转换的规律
     * byte，short，char>>int>>long>>float>>double
     * -如果有int类型参数的方法，但是传入double类型，则不能匹配，属于没有近似的，则报错
     * 2、函数是否重载也在于参数的顺序是否相同
     * 3、当参数只有形参变量不同时，两个函数不是重载，是相同的。
     * 4、当函数名称相同，参数列表不同时，已经可以断定两个方法是重载的。与返回值类型无关。
     */

    /*获取两个小数的最大值
    public static double getMax(double a,double b){
        System.out.println("调用了参数为double,double的方法");
        return a>b?a:b;
    }
    */

    //获取两个小数的最大值
    public static int getMax(double x, double y) {
        System.out.println("调用了参数为double,double的方法");
        return (int) (x > y ? x : y);
    }

    /*
    //获取两个小数的最大值
    public static double getMax(double x,double y){
        System.out.println("调用了参数为double,double的方法");
        return x>y?x:y;
    }
    */

    //获取两个数的最大值
    public static double getMax(double x, int y) {
        System.out.println("调用了参数为double,int的方法");
        return x > y ? x : y;
    }

    //获取两个数的最大值
    public static double getMax(int x, double y) {
        System.out.println("调用了参数为int,double的方法");
        return x > y ? x : y;
    }

    //获取两个整数的最大值
    public static int getMax(int x, int y) {
        System.out.println("调用了参数为int,int的方法");
        return x > y ? x : y;
    }

    //获取三个整数数的最大值
    public static int getMax(int x, int y, int z) {
        return (x > y ? x : y) > z ? (x > y ? x : y) : z;
    }


    /**
     * 四种访问权限：
     *
     *                              任何地方      包外子类      包内       类内
     *      public                  OK              OK           OK         OK
     *      protected            NO              OK           OK         OK
     *      无                       NO               NO          OK         OK
     *      private               NO               NO          NO         OK
     *
     *      public：修饰外部类，属性，方法，表示公开的，无限制的。是访问权限最松的一级。
     *      protected：只能修饰属性和方法。表示受保护的，有限制的。被修饰的只能被包内及包外子类访问。即使并非继承关系也可以。
     *      无：即无任何访问权限修饰符，不要说成default，它并非访问权限控制符的关键字。
     *      private：只能修饰属性，方法，内部类。只能在该类内部访问。
     */
    // 测试四种访问权限修饰符

    /**
     * public访问权限修饰符
     */
    public void publicMethod() {
        System.out.println("我是public修饰的方法");
    }

    /**
     * protected访问权限修饰符
     */
    protected void protectedMethod() {
        System.out.println("我是protected修饰的方法");
    }

    /**
     * 无权限修饰符
     */
    void NoneMethod() {
        System.out.println("我是无修饰的方法");
    }

    /**
     * private访问权限修饰符
     */
    private void privateMethod() {
        System.out.println("我是private修饰的方法");
    }
}
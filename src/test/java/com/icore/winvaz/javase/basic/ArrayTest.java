package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1：函数(掌握)
 * (1)定义在类中，有特定功能的一段小程序，可以独立运行。
 * (2)函数的格式：
 * 修饰符 返回值类型 函数名(形参类型 形式参数1,形参类型 形式参数2...)
 * {
 * 函数体;
 * reutrn 返回值;
 * }
 * <p>
 * A:修饰符	public static
 * B:返回值类型	根据需求，定义返回值类型
 * C:函数名	其实就是函数的名称,方便我们调用。符合小驼峰式命名规则。
 * D:参数
 * 形参类型	数据类型
 * 形式参数	就是接收实际参数的变量
 * 实际参数	就是实际参与操作的变量(常量)
 * E:函数体	就是按照正常的逻辑完成功能的代码。
 * F:返回值	就是程序的最终结果
 * G:return 返回值	哪里调用程序，return就把结果返回到哪里。
 * (3)函数的特点：
 * A:函数与函数之间是平级关系。不能在函数中定义函数。
 * B:运行特点	函数只有被调用才执行。
 * (4)案例：
 * 有明确返回值的例子：
 * <p>
 * A:求两个数据的和
 * B:求两个数据的最大值
 * C:比较两个数是否相等
 * <p>
 * void类型例子：
 * <p>
 * A:nn乘法表
 * B:根据给定的行和列输出一个*组成的长方形
 * (5)函数的调用
 * 1、函数调用顺序与定义顺序无关
 * 2、可以多层调用//放到面向对象时给大家介绍
 * 3、具有返回值的函数，我们调用都会将值赋值给一个变量
 * 4、单独调用一个函数时，通常这个函数是没有返回值的。
 * (6)函数重载
 * A:函数名相同,参数列表不同(个数不同,对应的类型不同)。
 * 与返回值类型无关。
 * <p>
 * B:举例：
 * public static int sum(int a,int b){...}
 * public static int sum(int a,int b,int c){...}
 * public static int sum(float a,float b){...}
 * <p>
 * 2：数组(掌握)
 * (1)数组是存储同一种类型的多个元素的容器。
 * (2)好处：数组中的元素会被自动从0开始编号，方便我们获取。
 * (3)格式：
 * A:int[] arr = new int[3];
 * B:int arr[] = new int[3];
 * C:int[] arr = new int[]{1,2,3};
 * D:int[] arr = {1,2,3};
 * <p>
 * 推荐A和D。
 * (4)Java内存图：
 * A:栈	存储局部变量使用。
 * 使用完毕，立马消失。
 * <p>
 * B:堆	所有new出来的都在堆里面。
 * a:每一个实体都有地址值
 * b:每一个实体内的内容都有默认值
 * 整数：0
 * 浮点数：0.0
 * 字符：'\u0000'
 * 布尔：false
 * 内容为引用数据类型时(String)：null
 * c:在垃圾回收器回收时被回收。
 * <p>
 * C:方法区
 * D:本地方法区
 * E:寄存器
 * (5)操作：(请自己补齐)
 * <p>
 * 数组的索引。
 * 数组的长度。
 * 数组名.length
 * <p>
 * A:数组的遍历
 * <p>
 * B:数组获取最值
 * (6)二维数组(理解)：
 * 格式：
 * A:int[][] arr = new int[3][2];
 * B:int[][] arr = new int[3][];
 * C:int[][] arr = {{1,2,3},{4,5},{6,7,8,9}};
 * <p>
 * <p>
 * 遍历：(请自己补齐)
 * <p>
 * 应用：遍历求和。
 */
public class ArrayTest {

    /**
     * 数组:
     * 同一种类型数据的集合，相当于一个容器。
     * 数组的定义格式：
     * 格式一：
     * 元素类型[] 数组名 = new 元素类型[元素个数或数组长度];
     * int[] arr = new int[13];
     * arr[0] = 13;arr[1] = 2.....arr[12] = 87;
     * 格式二：
     * 元素类型[] 数组名 = new 元素类型[]{元素，元素，⋯⋯};
     * int[] arr = new int[]{具体内容};
     * int[] arr = new int[]{13,2,43,65,8,9,5,4,4332,2,65,23,87};
     * 格式三：
     * 数组内所放元素数据类型[] 数组名 = {具体内容};
     * int[] arr = {13,2,43,65,8,9,5,4,4332,2,65,23,87};
     * <p>
     * 遍历数组：
     * 访问数组当中每一个元素
     * 数组的长度为：arr.length
     */
    @Test
    public void test() {
        // 格式一
        int[] arr1 = new int[13];
        System.out.println(arr1[0]); // 0
        // System.out.println(arr1[13]); // java.lang.ArrayIndexOutOfBoundsException: 13
        arr1[0] = 13;
        arr1[1] = 2;
        // ....
        arr1[12] = 87;

        // 格式二
        // 最后一个值后面允许有逗号
        int[] arr2 = new int[]{13, 2, 43, 65, 8, 9, 5, 4, 4332, 2, 65, 23, 87};
        // 数组拷贝
        int[] copyArr = Arrays.copyOf(arr2, 2 * arr2.length);
        System.out.println("copyArr的长度为:" + copyArr.length); // 26

        // 格式三(不适合用来传参)
        int[] arr3 = {13, 2, 43, 65, 8, 9, 5, 4, 4332, 2, 65, 23, 87,};
        arr3[10] = 100;
        System.out.println(arr3[10]); // 100
        // 注意:索引从0开始，始终比自然顺序少1
        // 数组总共13个数，但索引最大为13-1(因为索引是从0开始的)
        // arr[13]:java.lang.ArrayIndexOutOfBoundsException: 13(数组脚本越界异常)
        // System.out.println(arr3[13]);
        // 在堆内存中的数据都必须要有值
    }

    /**
     * 遍历数组
     */
    @Test
    public void test1() {
        int[] ints = {2, 9, 3, 0, 5, 4, 7, 1, 6, 8};
        // for遍历数组
        /*
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        */

        // foreach遍历数组
        /*
        for (int i : ints) {
            System.out.print(ints[i] + " ");
        }
        */

        // Arrays.toString()打印
        // System.out.println(Arrays.toString(ints));

        // System.out.println(ints.length); // 10(数组元素个数)

        // 倒序遍历数组
        /*
        for (int i = ints.length - 1; i >= 0; i--) {
            System.out.print(ints[i] + " ");
        }
        */

        System.out.println("数组排序前:" + Arrays.toString(ints));
        bubbleSore(ints);
        // 对数组排序实际上修改了数组本身。
        System.out.println("数组排序后:" + Arrays.toString(ints));

        // 工具类排序
        // Arrays.sort()排序
        // Arrays.sort(ints);

        // 二维数组
        int[][] sints = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(sints[1][2]); // 7
        // 打印二维数组
        /*
        for (int[] its: sints) {
            for (int i : its) {
                System.out.print(i);
                System.out.print(", ");
            }
            System.out.println();
        }
        */
        System.out.println(Arrays.deepToString(sints));

        // 用二维数组表示的学生成绩:
        int[][] scores = {
                {82, 90, 91},
                {68, 72, 64},
                {95, 91, 89},
                {67, 52, 60},
                {79, 81, 85},
        };
        int sum = 0;
        int num = 0;
        for (int[] its : scores) {
            for (int i : its) {
                sum += i;
                num++;
            }
            double average = (double) sum / num;
            if (Math.abs(average - 77.733333) < 0.000001) {
                System.out.println("测试成功");
            } else {
                System.out.println("测试失败");
            }
        }
    }

    /**
     * 多个引用指向同一对象：
     * 多个变量指向同一实例对象的堆存地址。
     * <p>
     * 当通过其中一个引用修改了该实例对象，则通过其他引用访问的这个对象就被修改了。
     */
    @Test
    public void test2() {
        int[] arr1 = new int[]{12, 34, 36, 54};
        System.out.println(arr1[2]); // 36
        int[] arr2 = new int[]{23, 56, 20};
        System.out.println(arr2[2]); // 20
        int[] arr3 = arr1;
        arr3[2] = 100;
        System.out.println(arr1[2]); // 100
        System.out.println(arr2[2]); // 20
        System.out.println(arr3[2]); // 100;
    }

    /**
     * 数组的一些细节问题：
     * 1、错误的定义方式
     * 2、如果定义一个数组，但是数组没有赋值，则报错。如果数组的引用值为null是指值为空，但是有值。
     * 3、null不可以被直接打印，但是如果一个数组引用值为null，则打印不报错。
     * 4、如果直接打印一个有值的数组，则其打印结果为：该数组的类型及所在地址。
     * 5、类似String[]的数组，他里边存放的是引用数据类型，则默认初始化值为null
     * 6、常见异常：ArrayIndexOutOfBoundsException：数组角标越界异常：数组中没有这个索引，非要访问没有的索引
     * NullPointerException：空指针异常：当引用变量值为null时，非要对其进行访问
     * 以上两种异常由于没有语法错误，所以编译可以通过，但是运行不能通过。
     */
    @Test
    public void test3() {
        //元素类型[] 数组名 = new 元素类型[元素个数或数组长度];
        //int[] arr = new int[5];
        int[] arr = new int[5];//正确！但是不建议
        int arr2[] = new int[5];//正确！但是强烈不建议
        //int[] arr3 = new int[5]{1,4,6,7,7};//错误！因为当指定了数组长度，再显式赋值，可能会有不同的数组长度出现
        //int arr4 = new int[];//错误！因为没有制定长度，就不知道该开辟多大的内存空间

		/*
		int[] arr5;//错误！因为没有给初始化值
		int a;
		System.out.println(arr5[2]);
		System.out.println(a);
		*/
		/*
		int[] arr6 = new int[5];
        System.out.println(arr6[3]); // 0
        arr6 = null;
        System.out.println(arr6[3]); // java.lang.NullPointerException
        System.out.println(arr6); // null
        */
		/*
		int[] arr7 = new int[3];
		System.out.println(arr7); // [I@71623278(int类型的数组对象地址值)
        */

        String[] strings = new String[5];
        // System.out.println(strings[3]); // String引用数据类型的默认值为null

        String[] arr8 = new String[]{"我爱java", "你爱JAVA", "我们都爱JAVA"};
        // System.out.println(arr8[3]); // java.lang.ArrayIndexOutOfBoundsException: 3

        arr8 = null;
        // System.out.println(arr8[2]); // java.lang.NullPointerException
    }

    /**
     * 数组传参问题
     */
    @Test
    public void test4() {
        int a = 10;
        int b = 20;
        System.out.println("main函数：a:" + a + ",b:" + b); // 10, 20
        change(a, b);
        System.out.println("main函数：a:" + a + ",b:" + b); // 10, 20

        System.out.println("=====================================");

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("main函数:" + arr[1]); // 2
        change(arr);
        System.out.println("main函数:" + arr[1]); // 4
    }

    public static void change(int a, int b) {
        System.out.println("change方法内部：a:" + a + ",b:" + b); // 10, 20
        a = b;
        b = a + b;
        System.out.println("change方法内部：a:" + a + ",b:" + b); // 20, 40
    }

    public static void change(int[] arr) {
        System.out.println("change方法内部:" + arr[1]); // 2
        for (int x = 0; x < arr.length; x++) {
            if (arr[x] % 2 == 0) {
                arr[x] *= 2; // 4 = 2 * 2
            }
        }
        System.out.println("change方法内部:" + arr[1]); // 4
    }

    /**
     * 方法参数
     * 按值调用(call by value)表示方法接收的是调用者提供的值
     * 按引用调用(call by reference)表示方法接收的是调用者提供的变量地址
     *
     * @param null
     * @throws
     * @author wdq
     * @create 2020/9/2 14:47
     * @Return
     */
    @Test
    public void test13() {
        double percent = 10;
        Employee x = new Employee("Alice", 100);
        Employee y = new Employee("Bob", 200);
        // employee.raiseSalary(percent);
        // System.out.println(percent); // 10
        // Employee.tripeValue(percent);
        // System.out.println(percent);
        // 地址
    }
    static class Employee {
        private static int nextId = 1;

        private int id;
        private String name;
        private double salary;
        private LocalDate hireDay;

        public Employee() {

        }

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public Employee(String name, double salary, int year, int month, int day) {
            this(name, salary);
            this.id = 0;
            this.hireDay = LocalDate.of(year, month, day);
        }

        public static int getNextId() {
            return nextId;
        }

        public int getId() {
            return id;
        }

        public void setId() {
            this.id = nextId;
            nextId++;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public LocalDate getHireDay() {
            return hireDay;
        }

        public void raiseSalary(double percent) {
            double raise = salary * percent / 100;
            salary += raise;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    ", hireDay=" + hireDay +
                    '}';
        }

        public static void tripeValue(double x) {
            x = 3 * x;
        }

        public static void tripeValue(Employee employee) {
            employee.raiseSalary(200);
        }

        public static void swap(Employee x, Employee y) {
            Employee temp = x;
            x = y;
            y = temp;
        }

    }


    /**
     * 二维数组：
     * 在数组当中存放数组元素，这样的数组叫做二维数组。
     * 格式1：
     * int[][] arr = new int[3][2];
     * 定义了名称为arr的二维数组
     * 二维数组中有3个一维数组
     * 每一个一维数组中有2个元素
     * 一维数组的名称分别为arr[0], arr[1], arr[2]
     * 给第一个一维数组1脚标位赋值为78写法是：arr[0][1] = 78;
     * <p>
     * 格式2：
     * int[][] arr = new int[3][];
     * 二维数组中有3个一维数组
     * 每个一维数组都是默认初始化值null
     * 可以对这个三个一维数组分别进行初始化
     * arr[0] = new int[3];
     * arr[1] = new int[1];
     * arr[2] = new int[2];
     * <p>
     * 格式3：
     * int[][] arr = {{3,8,2},{2,7},{9,0,1,6}};
     * 定义一个名称为arr的二维数组
     * 二维数组中的有三个一维数组
     * 每一个一维数组中具体元素也都已初始化
     * 第一个一维数组 arr[0] = {3,8,2};
     * 第二个一维数组 arr[1] = {2,7};
     * 第三个一维数组 arr[2] = {9,0,1,6};
     * 第三个一维数组的长度表示方式：arr[2].length;
     */
    @Test
    public void test5() {
        int[] arr = new int[5];
        String[] arr2 = new String[3];
        boolean[] arr3 = new boolean[2];

        //3的地方代表：二维数组有多少个一维数组
        //2的地方代表：一维数组的长度(其中元素的个数)
        int[][] arr5 = new int[3][2];

        arr5[0][0] = 1;
        arr5[0][1] = 132;
        arr5[1][0] = 12;
        arr5[1][1] = 123;
        arr5[2][0] = 12213;
        arr5[2][1] = 98;

        // 指定了一维数组的长度
        System.out.println(arr5[0]); // [I@32143(数组地址值)
        System.out.println(arr5[1][1]); // 123
        System.out.println("=====================");

        int[][] arr6 = new int[3][];
        //注意！！！：这种为二维数组里边的一维数组赋值的操作，不能使用格式3:int[] arr = {1,2,4,5};
        arr6[0] = new int[]{1, 2};
        // arr6[0][0] = 1; // NullPointerException
        arr6[1] = new int[]{2, 45, 12};
        arr6[2] = new int[]{98, 2198};

        // 没有指定一维数组的长度，数组默认值为null
        System.out.println(arr6[0]); // 没有赋值时==null，赋值后==NullPointException
        // System.out.println(arr6[1][1]); // 没有赋值时==NullPointException

        System.out.println("=====================");
        int[][] arr7 = {{3, 8, 2}, {2, 7}, {9, 0, 1, 6}};
        System.out.println(arr7.length);
        System.out.println(arr7[1][1]); // 7

    }


    /**
     * 二维数组的注意事项：
     * 1、使用第一种格式：
     * int[][] arr = new int[3][2];要求所有一维数组的长度都相同，则无法制定一维数组为特定长度
     * 2、使用.length同样可以查询到二维数组当中一维数组的长度
     * 3、二维数组不指定其长度，只指定其内部的一维数组长度，是定义不成功的
     */
    @Test
    public void test6() {
        int[][] arr = {{3, 8, 2}, {2, 7}, {9, 0, 1, 6}};
        System.out.println(arr.length); // 3
        System.out.println(arr[2].length); // 4
        //System.out.println(arr2[2].length);会空指针异常，因为没有数组，所以无法找到其长度

        int[] x, y[]; //定义了一个一维数组x，同时定义了一个二维数组y
        //System.out.println(x); // 错误，可能未被初始化值
        //System.out.println(y);
        //int[][] arr2 = new int[3][];
        x = new int[3];
        y = new int[3][];
        //没有指定二维数组长度编译不通过
        // int[][] arrx = new int[][2];
        // System.out.println(arrx);
    }

    /**
     * 定义并初始化一个二维数组，要求，至少3个元素，内部数组也至少为3个元素，遍历数组。
     * <p>
     * 将二维数组里的所有值，求和。
     * 将二维数组里的最大值，打印出来。
     */
    @Test
    public void test7() {
        int[] arr2 = {23, 5, 6, 789, 1, 132};
        //标准的一维数组遍历
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
        System.out.println("===============================");

        int[][] arr = {{3, 8, 2}, {2, 7}, {9, 0, 1, 6}};
        // 定义变量，记录和
        int sum = 0;
        // 定义变量，记录最大值
        int max = arr[0][0];
        //标准的二维数组遍历
        for (int i = 0; i < arr.length; i++) {
            //arr[i]一维数组
            for (int j = 0; j < arr[i].length; j++) {
                // sum += arr[i][j];
                /*
                if (max < arr[i][j]) {
                    max = arr[i][j];
                }
                */
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("二维数组之和为:" + sum);
        System.out.println("数组最大值为:" + max);
        System.out.println("=================");
        // 二维数组遍历可以使用Java标准库的Arrays.deepToString()
        System.out.println(Arrays.deepToString(arr));
        System.out.println("Hello World!");

        /*
         * 三维数组
         *      二维数组的数组
         */
        // 如果我们要访问三维数组的某个元素，例如，ns[2][0][1]，只需要顺着定位找到对应的最终元素15即可。
        // 理论上，我们可以定义任意的N维数组。但在实际应用中，除了二维数组在某些时候还能用得上，更高维度的数组很少使用。
        int[][][] ns = {
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                },
                {
                        {10, 11},
                        {12, 13}
                },
                {
                        {14, 15, 16},
                        {17, 18}
                }
        };
    }

    /**
     * 数组习题：
     * 数据加密：
     * 某个公司采用公用电话传递数据信息，数据是小于8位的整数，为了确保安全，在传递过程中需要加密，
     * 加密规则如下：
     * 首先将数据倒序，然后将每位数字都加上5，再用和除以10的余数代替该数字，
     * 最后将第一位和最后一位数字交换。 请任意给定一个小于8位的整数，然后，把加密后的结果在控制台打印出来。
     * <p>
     * 提示：
     * 1、小于等于一个八位数
     * 2、将数字拆散，放到一个int[]数组当中，通过对数组的操作，对所有的数组元素进行倒叙得到倒叙后的数据
     * 3、使用计算符对数组当中每一个元素进行计算，替代之前的数值
     * 4、首尾交换
     * 5、打印
     * <p>
     * 难点：
     * 1、第二步时，如何将数字拆分到int[]当中
     * 2、并不确定给的数字的位数。如何来记录这个数字是多少位
     * 3、需要使用位数来获取每一个元素并对其操作
     * 4、前提：定义数组时，维度应该为多少？只要能存下所有的数字即可
     */
    @Test
    public void test8() {
        int number = 98;
        //int number = 984;
		/*
	    int ge = number%10;                     //number%10，number = number/10；
		int shi = number/10%10;					//number%10，number = number/10；
		int bai = number/10/10%10;				//number%10，number = number/10；
		int qian = number/10/10/10%10;			//number%10，number = number/10；
		int wan = number/10/10/10/10%10;		//number%10，number = number/10；
		int shiwan = number/10/10/10/10/10%10;  //number%10，number = number/10；number==0
		*/
        int[] arr = new int[8];
        int index = 0;
        while (number != 0) {
            arr[index] = number % 10;
            number /= 10;
            index++;
        }
        System.out.println(index);

		/*只针对6位数的做法
	    int ge = number%10;                     //6
		int shi = number/10%10;					//4
		int bai = number/10/10%10;				//3
		int qian = number/10/10/10%10;			//6
		int wan = number/10/10/10/10%10;		//8
		int shiwan = number/10/10/10/10/10%10;  //9

		int[] arr = new int[8];
		arr[0] = ge;
		arr[1] = shi;
		arr[2] = bai;
		arr[3] = qian;
		arr[4] = wan;
		arr[5] = shiwan;
		*/
        for (int i = 0; i < index; i++) {
            //首先将数据倒序，然后将每位数字都加上5，再用和除以10的余数代替该数字
            arr[i] = (arr[i] + 5) % 10;
            System.out.print(arr[i]);
        }
        System.out.println();

        int temp;
        temp = arr[0];
        arr[0] = arr[index - 1];
        arr[index - 1] = temp;

        for (int i = 0; i < index; i++) {
            System.out.print(arr[i]);
        }
        //System.out.println("Hello World!");
		/*
			986346:
			643689
			一大堆计算：198134
			498131
		*/
    }

    @Test
    public void test9() {
        int[] arr = new int[]{8, 2, 1, 0, 3};
        int[] index = new int[]{2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3};
        String tel = "";
        for (int i : index) {
            tel += arr[i];
        }
        System.out.println(tel);
    }

    @Test
    public void test10() {
        /**
         * 抽彩从49个数字中抽取6个
         * @author wdq
         * @create 2020/8/31 17:57
         * @param
         * @Return void
         * @exception
         */
        int n = 49;
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        // 定义结果集
        int[] result = new int[6];
        for (int i = 0; i < result.length; i++) {
            // 通过Random获取随机数
            int r = (int) (Math.random() * n);
            // 抽取的的数字赋值给结果集
            result[i] = numbers[r];
            // 覆盖原有值
            numbers[r] = numbers[n - 1];
            n--;
        }
        // 对结果集进行排序
        Arrays.sort(result);
        for (int i : result) {
            System.out.println(i);
        }
    }

    @Test
    public void test11() {
        // 二维数组测试
        // 利率增长
        final double STARTRATE = 10;
        final int NRATES = 6;
        final int NYEARS = 10;
        // set insterst rates to 10 .... 15%
        double[] interestRate = new double[NRATES];
        for (int i = 0; i < interestRate.length; i++) {
            interestRate[i] = (STARTRATE + i) / 100.0;
        }
        double[][] blances = new double[NYEARS][NRATES];
        // set initial blances to 10000
        for (int j = 0; j < blances[0].length; j++) {
            blances[0][j] = 10000;
        }
        // compute interest for future years
        for (int i = 1; i < blances.length; i++) {
            for (int j = 0; j < blances[i].length; j++) {
                double oldBlances = blances[i - 1][j];
                // compute interest
                double insterest = oldBlances * interestRate[j];
                // compute this years blances
                blances[i][j] = oldBlances + insterest;
            }
        }
        for (int i = 0; i < interestRate.length; i++) {
            System.out.printf("%9.0f%%", 100 * interestRate[i]);
        }
        System.out.println();
        // print blances table
        for (double[] row : blances) {
            // print table row
            for (double d : row) {
                System.out.printf("%10.2f", d);
            }
            System.out.println();
        }
    }

    @Test
    public void test12() {
        final int MAX = 10;
        int[][] odds = new int[MAX + 1][];
        for (int i = 0; i <= MAX ; i++) {
            odds[i] = new int[i + 1];
        }
        // fill triangular array
        for (int i = 0; i < odds.length; i++) {
            for (int j = 0; j < odds[i].length; j++) {
                int lotteryOdds = 1;
                for (int k = 1; k < j; k++) {
                    lotteryOdds = lotteryOdds * (i - k + 1) / k;
                }
                odds[i][j] = lotteryOdds;
            }
        }
        // print array
        for (int[] row : odds) {
            for (int odd : row) {
                System.out.printf("%4d", odd);
            }
            System.out.println();
        }
    }

    /**
     * 二分法查找
     *
     * @param data
     * @param number
     */
    public static void binarySearch(int[] data, int number) {
        int minIndex = 0;
        int maxIndex = data.length - 1;
        int middleIndex = (maxIndex + maxIndex) / 2;
        while (data[middleIndex] != number) {
            if (data[middleIndex] < number) {
                minIndex = middleIndex + 1;
            } else if (data[middleIndex] > number) {
                maxIndex = middleIndex - 1;
            }
            // 查找没有值
            if (minIndex > maxIndex) {
                middleIndex = -1;
                break;
            }
            // 索引变化
            middleIndex = (minIndex + maxIndex) / 2;
        }
        System.out.println(middleIndex);
    }

    /**
     * 数组工具类的折半查找
     * Arrasy.binarySearch()
     */
    @Test
    public void arraysBinarySearch() {
        int[] ints = {1, 4, 5, 8, 9};
        // int i = Arrays.binarySearch(ints, 3);
        // System.out.println(i); // -2,插入点-1
        String string = Arrays.toString(ints);
        System.out.println(string);

        char[] chars = {'c', 'b', 'a'};
        // [I@683dbc2c, int[]是基本数据类型，不存，需要引用数据类型。
        // 这里只有ints是引用，所以打印的是数组哈希值
        System.out.println(ints); // [I@683dbc2c，数组哈希值
        System.out.println(chars); // 只有字符数组输出时显示具体数据 cba
    }

    /**
     * 数组转集合
     */
    @Test
    public void arrayToCollection() {
        String[] strings = {"a", "e", "c"};
        // 数组转集合推荐方式
        List<String> stringList = new java.util.ArrayList<>(Arrays.asList(strings));

        // 不推荐方式(抛出下列异常)
        // List<String> stringList = Arrays.asList(strings);

        // 修改转换后的集合
        stringList.set(0, "aa");
        System.out.println(strings[0]); // aa

        // 以下三行编译正确，但都会抛出运行时异常
        // 使用Arrays.asList()方法数组转集合后，不能使用其修改集合的相关方法，
        // 它的add/remove/clear会抛出：java.lang.UnsupportedOperationException
        // 具体参考《码出高效》164页
        stringList.add("d");
        System.out.println(stringList);
        System.out.println(stringList.remove(3));
        stringList.clear();
        System.out.println(stringList);
    }

    /**
     * 集合转数组
     */
    @Test
    public void collectionToArray() {
        List<String> stringList = new ArrayList<>(3);
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");

        // 泛型丢失，无法使用String[]接受无参方法会的结果
        System.out.println(stringList.toArray());

        // 数组长度小于元素个数
        String[] strings = new String[2];
        stringList.toArray(strings);
        System.out.println(Arrays.asList(strings)); // [null, null]

        // Collection接口方法 T[] toArray(T[] a)集合变数组
        String[] stringsArray = stringList.toArray(new String[stringList.size()]);
        for (String array : stringsArray) {
            System.out.println(array);
        }
        // System.out.println(Arrays.asList(stringList.toArray(new String[stringList.size()])));
    }

    /**
     * 选择排序基本思路：
     * * 把第一个元素依次和后面的所有元素进行比较。
     * * 第一次结束后，就会有最小值出现在最前面。
     * * 依次类推
     */
    public static void selectionSor(int[] data) {
        // 比较轮数
        for (int i = 0; i < data.length - 1; i++) {
            // 每次比较次数
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    swap(data, i, j);
                }
            }
        }
    }

    /**
     * 冒泡排序基本概念是：
     * * 依次比较相邻的两个数，将小数放在前面，大数放在后面。
     * * 即在第一趟：首先比较第1个和第2个数，将小数放前，大数放后。
     * * 然后比较第2个数和第3个数，将小数放前，大数放后，如此继续，
     * * 直至比较最后两个数，将小数放前，大数放后。至此第一趟结束，
     * * 将最大的数放到了最后。在第二趟：仍从第一对数开始比较
     * * （因为可能由于第2个数和第3个数的交换，使得第1个数不再小于第2个数），
     * * 将小数放前，大数放后，一直比较到倒数第二个数（倒数第一的位置上已经是最大的），
     * * 第二趟结束，在倒数第二的位置上得到一个新的最大数
     * * （其实在整个数列中是第二大的数）。如此下去，重复以上过程，直至最终完成排序。
     */
    // 冒泡排序的特点是，每一轮循环后，最大的一个换到末尾。
    // 因此，下一轮循环就可以“刨除”最后的数，每一轮循环都比上一轮循环的结束位置靠前一位。
    public static void bubbleSore(int[] ints) {
        // 比较的轮数
        for (int i = 0; i < ints.length - 1; i++) {
            // 每次比较的次数
            for (int j = 0; j < ints.length - i - 1; j++) {
                // 如果比较的数和被比较的数(后一个)大，则交换位置
                // 升序(>)，倒序(<)
                if (ints[j] > ints[j + 1]) {
                    // 交换两数
                    /*
                    int temp = ints[j]; // 把j的值保存到临时变量temp中。
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                    */
                    // 不用第三方变量交换两数
                    ints[j] = ints[j] ^ ints[j + 1];
                    ints[j + 1] = ints[j] ^ ints[j + 1];
                    ints[j] = ints[j] ^ ints[j + 1];
                }
            }
        }
    }

    /**
     * * 堆排序利用了大根堆（或小根堆）堆顶记录的关键字最大（或最小）这一特征，
     * 使得在当前无序区中选取最大（或最小）关键字的记录变得简单。
     * * （1）用大根堆排序的基本思想
     * ① 先将初始文件R[1..n]建成一个大根堆，此堆为初始的无序区
     * ②再将关键字最大的记录R[1]（即堆顶）和无序区的最后一个 记录R[n]交换，
     * 由此得到新的无序区R[1..n-1]和有序区R[n]，且满足R[1..n-1].keys≤R[n].key
     * ③由于交换后新的根R[1]可能违反堆性质，故应将当前无序区R[1..n-1]调整为堆。
     * * 然后再次将R[1..n-1]中关键字最大的记录R[1]和该区间的最后一个记录R[n-1]交换，
     * * 由此得到新的无序区R[1..n-2]和有序区R[n-1..n]，
     * * 且仍满足关系R[1..n-2].keys≤R[n-1..n].keys，同样要将R[1..n-2]调整为堆。 　 直到无序区只有一个元素为止。
     * * （2）大根堆排序算法的基本操作：
     * ① 初始化操作：将R[1..n]构造为初始堆；
     * ②每一趟排序的基本操作：将当前无序区的堆顶记录R[1]和该区间的最后一个记录交换， 然后将新的无序区调整为堆（亦称重建堆）。
     */
    public static void HeapSort(int[] ints) {

    }

    /**
     * 插入排序基本思想
     * * 将n个元素的数列分为已有序和无序两个部分，如插入排序过程示例下所示：
     * * {{a1}，{a2，a3，a4，⋯，an}}
     * * {{a1⑴，a2⑴}，{a3⑴，a4⑴ ⋯，an⑴}}
     * * {{a1(n-1），a2(n-1) ，⋯},{an(n-1)}}
     * * 每次处理就是将无序数列的第一个元素与有序数列的元素从后往前逐个进行比较，
     * * 找出插入位置，将该元素插入到有序数列的合适位置中。
     */
    public static void InsertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; (j > 0) && (data[j] < data[j - 1]); j--) {
                swap(data, j, j - 1);
            }
        }
    }

    /**
     * 归并操作(merge)，也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。
     * * 如设有数列{6，202，100，301，38，8，1}
     * * 初始状态： [6] [202] [100] [301] [38] [8] [1] 比较次数
     * * i=1 [6 202 ] [ 100 301] [ 8 38] [ 1 ]　3
     * * i=2 [ 6 100 202 301 ] [ 1 8 38 ]　4
     * * i=3　[ 1 6 8 38 100 202 301 ] 4
     */
    private static void mergeSort(int[] data, int[] temp, int l, int r) {
        int mid = (l + r) / 2;
        if (l == r)
            return;
        mergeSort(data, temp, l, mid);
        mergeSort(data, temp, mid + 1, r);

        for (int i = l; i <= r; i++) {
            temp[i] = data[i];
        }
        int i1 = l;
        int i2 = mid + 1;
        for (int cur = l; cur <= r; cur++) {
            if (i1 == mid + 1) {
                data[cur] = temp[i2++];
            } else if (i2 > r) {
                data[cur] = temp[i1++];
            } else if (temp[i1] < temp[i2]) {
                data[cur] = temp[i1++];
            } else {
                data[cur] = temp[i2++];
            }
        }
    }

    /**
     * 快速排序：
     * * 一趟快速排序的算法是：
     * * 1）设置两个变量i、j，排序开始的时候：i=0，j=N-1；
     * * 2）以第一个数组元素作为关键数据，赋值给key，即 key=A[0]；
     * * 3）从j开始向前搜索，即由后开始向前搜索（j=j-1即j--），
     * * 找到第一个小于key的值A[j]，A[i]与A[j]交换；
     * * 4）从i开始向后搜索，即由前开始向后搜索（i=i+1即i++），
     * * 找到第一个大于key的A[i]，A[i]与A[j]交换；
     * * 5）重复第3、4、5步，直到 I=J；
     * * (3,4步是在程序中没找到时候j=j-1，i=i+1，直至找到为止。
     * * 找到并交换的时候i， j指针位置不变。
     * * 另外当i=j这过程一定正好是i+或j-完成的最后令循环结束。）
     */
    private static void quickSort(int[] data, int i, int j) {
        int pivotIndex = (i + j) / 2;
        // swap
        swap(data, pivotIndex, j);

        int k = partition(data, i - 1, j, data[j]);
        swap(data, k, j);
        if ((k - i) > 1)
            quickSort(data, i, k - 1);
        if ((j - k) > 1)
            quickSort(data, k + 1, j);

    }

    /**
     * @param data
     * @param l
     * @param r
     * @return
     */
    private static int partition(int[] data, int l, int r, int pivot) {
        do {
            while (data[++l] < pivot)
                ;
            while ((r != 0) && data[--r] > pivot)
                ;
            swap(data, l, r);
        } while (l < r);
        swap(data, l, r);
        return l;
    }

    /**
     * 希尔排序：先取一个小于n的整数d1作为第一个增量，
     *  * 把文件的全部记录分成（n除以d1）个组。所有距离为d1的倍数的记录放在同一个组中。
     *  * 先在各组内进行直接插入排序；然后，取第二个增量d2<d1重复上述的分组和排序，
     *  * 直至所取的增量dt=1(dt<dt-l<⋯<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。
     */

    /**
     * 二分查找
     */
    private static int binarySearch(int[] a, int fromIndex, int toIndex,
                                    int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    private static int[] insertElement(int original[], int element, int index) {
        int length = original.length;
        int destination[] = new int[length + 1];

        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;

        System.arraycopy(original, index, destination, index + 1, length - index);

        return destination;
    }

    /**
     * 交换数组中两个元素
     *
     * @param data
     * @param i
     * @param j
     */
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
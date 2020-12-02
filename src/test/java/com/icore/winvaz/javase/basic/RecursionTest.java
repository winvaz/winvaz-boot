package com.icore.winvaz.javase.basic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * 递归只是一种编程手段而已，C C++ C# php java
 * 递归就是方法的自身调用，自己调用自己  public static void a(){  a();  }
 * 从前有个山，山里有庙，庙里有个老和尚讲故事，讲的是从前有座山，山里有个庙...
 * 什么时候可以使用递归：当你发现一个功能，运算的主体不变，但是，每次运算的参数都在变化，考虑使用递归实现功能
 * 注意：
 * 	递归必须要有条件限制
 * 	递归不能太深，方法进栈次数不能过多，否则出现栈内存溢出
 */
public class RecursionTest {
    /**
     * 经典兔子问题
     * 有一对小兔子，第1个月不生，第2个月不生，第3个月，生下1对小兔子，以后每个月都生下1对小兔子
     * 生下来的小兔子，第1个月不生，第2个月不生，第3个月，生下1对小兔子，以后每个月都生下1对小兔子
     * 如果所有的兔子都不死，1年后，一共有多少对兔子
     * 从第三个月开始，每个月是前面两个的和  斐波那契数列
     * 1 1 2 3 5 8 13 21 34 55 89 144 233
     * 第n项 = (n-1)+(n-2)
     * 当n>2
     */
    public static int test(int month) {
        if (month == 1) {
            return 1;
        }
        if (month == 2) {
            return 1;
        }
        return test(month - 1) + test(month - 2);
    }

    /**
     * 输入一个正整数n，输出n!的值。其中n!=1*2*3*…*n,即求阶乘
     * 阶乘的递归实现
     * 5 = 5 * 4 * 3 * 2 * 1
     */
    public static int factorial(int n) {
        // 递归函数的堆栈轨迹
        // StackWalker since 9
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (StackTraceElement e : stackTrace) {
            System.out.println("\n---the  "+ e +"  element"+"---");
            System.out.println("toString:"+e.toString());
            System.out.println("ClassName:"+e.getClassName());
            System.out.println("FileName:"+e.getFileName());
            System.out.println("LineNumber:"+e.getLineNumber());
            System.out.println("MethodName:"+e.getMethodName());
        }
        // 寻找问题与子问题的关系 阶乘的关系比较简单，
        // 我们以f(n)来表示n的阶乘，显然f(n) = n * f(n - 1),同时临界条件是f(1) = 1
        // 临界条件
        if (n <= 1) {
            return 1;
        }
        // 时间复杂度,由于f(n) = n * f(n-1) = n * (n-1) * .... * f(1),总共作了n次乘法，所以时间复杂度为 n
        // 递推公式
        return n * factorial(n - 1);
    }

    /**
     * 斐波那契数列 1，1，2，3，5，8，13，21，34
     * fib(5) = fib(4) + fib(3)
     * fib(4) = fib(3) + fib(2)
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 回文字符串就是正读倒读都一样的字符串，如：98789
     * 递归回文字符串的判断
     */
    public static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        if (end > start) {
            // 递归终止条件，两个指针相向移动，当start超过end时，完成判断
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            } else {
                // 递归调用
                return isPalindrome(s.substring(start + 1).substring(0, end - 1));
            }
        }
        return true;
    }

    /**
     * 递归计算1+2+3+4+5+6。。。到总数为44980060时，计算多少次
     */
    int index = 0;
    int sum = 0;
    int count = 3;

    public int test1(int i) {
        if (sum != count) {
            sum += i;
            index++;
            test1(i + 1);
        }
        return index - 1;
    }

    /**
     * 一只青蛙可以一次跳 1 级台阶或一次跳 2 级台阶,例如:
     * 跳上第 1 级台阶只有一种跳法：直接跳 1 级即可。跳上第 2 级台阶
     * 有两种跳法：每次跳 1 级，跳两次；或者一次跳 2 级。
     * 问要跳上第 n 级台阶有多少种跳法？
     */
    // 和上面的生兔子算法一样
    public static Integer frogStep(int n) {
        /**
         * 寻找问题与子问题之前的关系 这两者之前的关系初看确实看不出什么头绪，但仔细看题目，
         * 一只青蛙只能跳一步或两步台阶，自上而下地思考，
         * 也就是说如果要跳到 n 级台阶只能从 从 n-1 或 n-2 级跳，
         * 所以问题就转化为跳上 n-1 和 n-2 级台阶的跳法了，
         * 如果 f(n) 代表跳到 n 级台阶的跳法，
         * 那么从以上分析可得 f(n) = f(n-1) + f(n-2),
         * 显然这就是我们要找的问题与子问题的关系,而显然当 n = 1, n = 2，
         * 即跳一二级台阶是问题的最终解
         */
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return frogStep(n - 1) + frogStep(n - 2);
    }

    /**
     * 高斯算法
     * 100 + (100-1) + (100-1-1)
     * 具体的方法是：首项加末项乘以项数除以2
     * 项数的计算方法是末项减去首项除以项差（每项之间的差）加1.
     * 如：1+2+3+4+5+······+n，则用字母表示为：n（1+n）/2
     */
    @Test
    public Integer gauss(int n) {
        if (n == 1) {
            return 1;
        }
        return n + gauss(n - 1);
    }

    /**
     * KMP 算法
     * 􏷾谈到字符串问题，不得不提的就是KMP算法，它是用来解决字符串查找问题。
     * 可以在一个字符串(S)中查找一个子串(W)出现的位置。
     * KMP算法把字符匹配的时间复杂度缩小到O(m+n),而空间复杂度也只有O(m)。
     */
    public static String kmpReplace(StringBuffer stringBuffer) {
        return stringBuffer.toString().replaceAll("\\s", "%20");
    }

    /**
     * 最长公共前缀
     * 查找字符串数组中的最长公共前缀，如不存在返回""
     * 如：["flower", "flow", "flight"] --> "fl"
     * ["dog", "rececar", "car"] --> ""
     * 思路：先排序，再将数组的第一个元素的字符和最后一个元素的字符从前往后对比即可
     */
    public static String maxPublicPrefix(String[] strings) {
        // 检查不合法返回空串
        if (!checkStrs(strings)) {
            return "";
        }
        // 数组长度
        int length = strings.length;
        // 保存结果
        StringBuilder stringBuilder = new StringBuilder();
        // 升序排序
        Arrays.sort(strings);
        //
        int m = strings[0].length();
        int n = strings[length - 1].length();
        int num = Math.min(m, n);
        for (int i = 0; i < num; i++) {
            if (strings[0].charAt(i) == strings[length - 1].charAt(i)) {
                stringBuilder.append(strings[0].charAt(i));
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    private static boolean checkStrs(String[] strings) {
        boolean flag = false;
        if (strings != null) {
            // 遍历检查
            for (int i = 0; i < strings.length; i++) {
                if (strings[i] != null
                        && strings[i].length() > 0) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 􏾫􏲎􏰕􏲠􏻆􏺴􏲋􏻸􏻿􏻆􏺴􏱔􏲋􏾫􏳺􏻆􏺴􏰤􏻝􏲌􏵦􏶄翻转链表
     * 输入一个链表，翻转链表后，输出链表的所欲元素。
     * 思路：如何让后一个节点指向前一个节点
     */
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode ReverseList(ListNode node) {
        // 前节点
        ListNode prefix = null;
        // 后节点
        ListNode next = null;

        /**
         *  node = 1->2->3->4->5
         *  第一次
         *  next = 2;
         *  node.next = null;
         *  prefix = node;
         *  node = 2;
         *  第二次
         *  next = 3;
         *  node.next = node;
         *  prefix = node;
         *  node = 3;
         */
        while (Objects.nonNull(node)) {
            // 保存要翻转到头的那个节点
            next = node.next;
            // 要翻转的那个节点指向已经翻转的上一个节点
            node.next = prefix;
            // 上一个已经翻转到头部的节点
            prefix = node;
            // 一直向链表尾走
            node = next;
        }
        return prefix;
    }

    /**
     * 二维数组查找
     * int[][] arr7 = {
     * {1, 2, 3},
     * {2, 3, 4},
     * {3, 4, 5}
     * };
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都是按从上到下递增的顺序排序
     * 思路：矩阵是有序的，从左下角来看，向上数字递减，向右数字递增。
     * 因此从左下角开始查找。
     * 当要查找的数字比左下角数字大时，右移。
     * 当要查找的数字比左下角数字小时，上移。
     */
    public static boolean find(int[][] array, int target) {
        // 行
        int row = array.length - 1;
        // 列
        int column = 0;
        // 当行数大于0，当前列小于总列数时循环条件成立
        while ((row >= 0) && (column < array[0].length)) {
            if (array[row][column] > target) {
                row--;
            } else if (array[row][column] < target) {
                column++;
            } else {
                return true;
            }
        }
        return false;
    }

    // 测试
    public static void main(String[] args) {
        // String[] strs = {"customer", "car", "cat"};
        // String[] strs = new String[]{"flower", "flow", "flight"};
        // String[] strs = {"customer", "car", null}; // 空串 // String[] strs = {};//空串
        // String[] strs = null; // 空串
        // System.out.println(maxPublicPrefix(strs));// c
        factorial(3);
        ListNode a = new RecursionTest().new ListNode(1);
        ListNode b = new RecursionTest().new ListNode(2);
        ListNode c = new RecursionTest().new ListNode(3);
        ListNode d = new RecursionTest().new ListNode(4);
        ListNode e = new RecursionTest().new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        ReverseList(a);
        while (e != null) {
            System.out.println(e.val);
            e = e.next;
        }

        int[][] arr7 = {
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5}
        };
        System.out.println(find(arr7, 6));
    }
}
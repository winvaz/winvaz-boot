package com.icore.winvaz.javase.basic;


import org.junit.jupiter.api.Test;

/**
 * @Deciption 标识符测试
 * @Author wdq
 * @Create 2020/3/16 22:36
 * @Version 1.0.0
 */
public class IdentifierTest {
    /**
     * 标识符
     * 组成：有26个英文字母大小写，数字0~9和符号_、$
     * 规则：数字不能开头
     * 不能使用关键字
     * Java中严格区分大小写，定义标识符不限制长度。
     * 注意：在起名字的时，为了提高阅读性，要见名知意。
     * 另外还有一点：驼峰式命名
     * <p>
     * Java中的名称规范
     * 包名：多单词组成时所有字母都小写。
     * xxx.yyy.zzz  www.icore.cn
     * 类名接口名：多单词组成时，所有单词的首字母大写。(大驼峰式)
     * XxxYyyZzz
     * 变量名和函数名：多单词组成时，第一个单词首字母小写，第二个单词开始每个单词首字母大写。(小驼峰式)
     * xxxYyyZzz
     * 常量名：所有字母都大写。多单词时每个单词用下划线连接。
     * XXX_YYY_ZZZ
     * <p>
     * 注意：文件名必须和类名一致，不然编译文件名报错(错误: 类Test是公共的, 应在名为 Test.java 的文件中声明)
     * 编译类名可以通过，但编译之后的xx.class与文件名不一致。
     */
    @Test
    public void test1() {
        /**
         常量：
         不能改变的数值
         整数：12
         小数：12.4  12.0
         字符类型：需要用''包裹即'1','a',' ','中'
         字符串型：需要用""包裹即"中国" ,"","aaa11"
         布尔类型：
         只有两个值：true，false
         null:空，是一个值不是一种类型，是引用数据类型的值。

         注意事项：
         字符类型：
         必须是一个字符
         可以与数字相运算
         布尔类型：
         只有true与false
         null值：
         不可以被打印，即System.out.println()不可以打印空值
         */
        System.out.println(12.0 + 1); // 13.0
        System.out.println(12.3); // 12.3
        System.out.println('a'); // a
        System.out.println('1' + 1); // 字符'1'的ASCII码为49 + 1 = 50
        System.out.println("123456"); // 123456
        // System.out.println(null); // 错误：对println的引用不明确
        System.out.println(true); // true
    }
}
package com.icore.winvaz.javase.basic;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description String类
 * @Author wdq
 * @Create 2019-06-09 22:00
 */
public class StringTest {
    @Test
    public void test() {
        // TODO String : 字符串不可变，这种不可变特性是通过内部的private final char[]，以及没有任何修改char[]的方法实现的。
        /*
        String s1 = "Hello!";
        System.out.println(s1);
        s1 = s1.toUpperCase();
        System.out.println(s1);
        String s2 = new String(new char[] {'H', 'e', 'l', 'l', 'o', '!'});
        */

        // TODO 构造函数 String():创建一个空参的字符串
        /*String string1 = new String(); // 通常我们使用 String string = "";
        System.out.println(string1);*/

        // TODO 构造函数 String(byte[] bytes):传入一个字节数组
        // 字节数组转字符串
        /*byte[] bytes = new byte[]{1, 2, 3, 4};
        String string2 = new String(bytes);
        System.out.println(string2);*/

        // 字符串转字节数组
        /*String string3 = "123456";
        byte[] bytes = string3.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]); // 49 50...打印字符串所对应的编码表
        }*/

        // TODO 构造函数 String(char[] value):传入一个字符数组
        /*char[] chars = new char[]{'a', 'A', 'b', 'B'};
        String string4 = new String(chars);
        System.out.println(string4); // aAbB*/

        // TODO Java面试题
        // 题目一
        /*String s2 = "abc"; // 创建一个对象
        String s3 = new String("abc"); // 创建两个对象
        System.out.println(s2 == s3); // false 地址值不相等
        System.out.println(s2.equals(s3)); // true equals被重写了
        System.out.println("abc".equals(s2)); // true 地址值相等
        System.out.println("abc".equals(s3)); // true 地址都相等了
        System.out.println(s2.length()); // 3
        System.out.println("===========");*/

        // 第二题
        /*
        String s4 = new String("abc");
        String s5 = new String("abc");
        System.out.println(s4 == s5); // false 地址值不相等 在对内存中出现两个地址
        System.out.println(s4.equals(s5)); // true 重写了方法

        String s6 = "abc";
        String s7 = "abc";
        System.out.println(s6 == s7); // true 地址值相等
        System.out.println(s6.equals(s7)); // true 地址值都相等了 重写了方法
        System.out.println("========");
        System.out.println(s4 == s6); // false 地址值相等
        System.out.println(s4.equals(s6)); // true 重写了方法
        */

        // 题目三
        /*
        String s4 = "hello";
        String s5= "world";
        String s6= "helloworld";
        System.out.println(s6 == s4 + s5); // false 常量池的地址不同，编译不会判断s4和s5的值
        System.out.println(s6 == "hello" + "world"); // true 显示
        System.out.println(s6.equals(s4+s5)); // true 重写了方法
        */

        // TODO String 判断功能
        // String类型的本质是char类型数组
        //String string5 = new String();
        /**
         * boolean equals(Object object); // 重写了Object的equals()方法，比较二者的内容
         * boolean equalsIgnoreCase(String anotherString); // 忽略大小写，比较两个字符串是否相等
         * boolean contains(CharSequence s); // 判断一个字符串是否包含另外一个字符串
         * boolean startsWith(String prefix); // 判断是否以某个字符串开始
         * boolean endsWith(String suffix); // 判断是否以某个字符串结尾
         * boolean isEmpty(); // 判断是否为空
         * boolean matches(String regex); // 使用一个正则表达式来判断该字符串是否符合这个规则。比如：电话号码，邮箱
         */
        String string6 = new String("Hello");
        String string7 = "Hello";
        String string8 = "lo";
        String string9 = "He";
        String string10 = ""; // new String();
        char c = 'o';

        //System.out.println(string6 == string7); // false
        //System.out.println(string6.equals(string7)); // true
        //System.out.println(string6.equalsIgnoreCase(string7)); // true 忽略大小写比较两个字符串
        //System.out.println(string7.contains(string8)); // true 判断s7中是否有s8这个字符串
        //System.out.println(string7.startsWith(string9)); // true 判断s7是否以s8开头
        //System.out.println(string7.endsWith(string8)); // true 判断s7是否以s8结尾
        //System.out.println(string6.isEmpty()); // false 判断s6是否为空,注意null是一个具体的值，是无法调用方法的
        //System.out.println(string10.isEmpty()); // true

        // TODO String类的获取功能
        /**String类的构造函数 String​(byte[] bytes, int offset, int length); // 给出指定字符数组，在指定位置开始判断，构造指定长度的字符
         *  int length(); // 获取字符串的长度。数组有length属性，String类有length()方法
         *  char charAt(int index); // 获取指定索引的字符
         *  int indexOf(char ch); 和 int indexOf(String str); // 给出指定字符或字符串，返回相应的索引
         *  int indexOf(int ch, int fromIndex) 和 int indexOf(String str, int fromIndex); // 给出指定字符或字符串，在指定位置开始判断的返回相应的索引
         *  String substring(int beginIndex) 和 String substring(int beginIndex, int endIndex); // 获取子字符串
         *
         */
        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        String string11 = new String(chars, 1, 2);
        System.out.println(string11); // bc
        // 获取实际的长度，即码点数量
        System.out.println(string11.codePointCount(0, string11.length())); // 2
        // 调用String.charAt(0)将返回位置n(0 ~ s.length()-1)的代码单元
        System.out.println(string11.charAt(0));
        System.out.println(string11.charAt(string11.length()-1));
        // 得到位置第i个码点
        int offset = string11.offsetByCodePoints(0, string11.length() - 1);
        int index = string11.codePointAt(offset);// c的ASCII码表对应十进制数为99
        // 遍历
        if (Character.isSupplementaryCodePoint(index)) {

        } else {

        }
        // 逆向遍历
        if (Character.isSurrogate(string11.charAt(index))) {

        }
        // 利用codePoints()方法
        int[] ints = string11.codePoints().toArray();
        //System.out.println(string7.length()); // 5 调用s7的length()方法
        //System.out.println(string7.charAt(0)); // H 获取s2的第三个元素(角标为2)
        //System.out.println(string7.indexOf(string8)); // 3 第一次出现的位置 返回s7字符传中，s8这个字符串第一次出现的位置
        //System.out.println(string8.indexOf(c)); // 1 返回s7字符传中，ch这个字符第一次出现的位
        // System.out.println(string7.indexOf(string8, 2)); // 3 从s7的角标为2的位置开始判断s8所在的索引
        // System.out.println(string7.indexOf(c, 2)); // 4 从s7的角标为2的位置开始判断'o'所在的索引
        //System.out.println(string7.substring(1)); // ello 包括指定位置到最后 将s7截取(包含起始位置)
        //System.out.println(string7.substring(1, 3)); // el 包括指定位置开始，不包括最后位置结束(包前不包后)
        // System.out.println(string7.substring(2, string7.length())); // llo 将s7截取(包含起始位置,但不包含结束位置)

        // TODO String类的转换功能
        /**
         * byte[] getBytes(); // 将字符串转换成字节数组
         * char[] toCharArray(); // 将字符串转换成字符数组
         * static String copyValueOf(char data[]); // 复制字符数组的内容转换成字符串
         * static String valueOf(char c); // 获取char字符值，转换成字符串
         * static String valueOf(int i); // 获取int类型的值，转换成字符串
         * String toLowerCase(); // 大写变小写
         * String toUpperCase(); // 小写变大写
         * String concat(String str); // 连接字符串
         */
        //System.out.println(string7.getBytes()); // [B@1d44bcfa 直接打印的是数组地址，需要遍历打印
        //System.out.println(string7.toCharArray()); // Hello
        //System.out.println(String.copyValueOf(chars)); // abcde
        //System.out.println(String.valueOf(c)); // o
        //System.out.println(String.valueOf(1)); // 1
        //System.out.println(string7.toUpperCase()); // HELLO
        //System.out.println(string7.concat(string8)); // Hellolo

        // TODO String类替换功能
        /**
         * String replace(char oldChar, char newChar); // 字符替换
         * String[] split(String regex); // 正则切割
         * String trim(); 去字符串前后空格，空白字符包括空格，\t，\r，\n
         * int compareTo(String anotherString); // 按字典顺序比较两个字符串，返回编码值的差
         */
        String s = "    \tHello\r\n";
        System.out.println(s.trim()); // Hello
        // 另一个strip()方法也可以移除字符串首尾空白字符。与trim()不同的是，类似中文的空格\u3000也会被移除
        // strip() JDK 11
        // System.out.println("\u3000Hello\u3000".strip()); // Hello
        // System.out.println(" Hello ".stripLeading()); // Hello (去除前空格)
        // System.out.println(" Hello   ".stripTrailing()); //  Hello(去除后空格)
        // isEmpty()和isBlank()判断字符串是否为空和空白字符串。
        // System.out.println("".isEmpty()); // true
        // System.out.println("    ".isEmpty()); // false
        // System.out.println(StringUtils.isBlank("    \n")); // true
        // System.out.println(StringUtils.isBlank(" Hello  ")); // false

        /**
         * 替换子串
         */
        // 1.根据字符或字符串替换
        System.out.println(s.replace('l', 'w')); // Hewwo，所有字符'l'被替换为'w'
        // 2.通过正则表达式替换
        s = "A,,B;C , D";
        System.out.println(s.replaceAll("[\\,\\;\\s]+", ",")); // "A,B,C,D"

        /**
         * 分隔字符串
         */
        // 分隔字符串，使用split()方法
        s = "A,B,C,D";
        String[] split = s.split("\\,");
        for (int i = 0; i < split.length; i++) {
            System.out.print(split[i]);
        }

        /**
         * 拼接字符串
         */
        String join = String.join("***", split);
        System.out.println(join);

        /**=================================================*/
        /**
         * StringBuffer:
         *          StringBuffer概述：
         *  * 	            优化了String，可以不在常量池创建非理性的对象数量
         *  * 	            StringBuffer提供缓冲区，在这个缓冲区中，该字符串的长度“可以变化”。
         *          * 	构造方法：
         *          * 	StringBuffer()   创建一个长度为16的字符串缓冲区
         *          *  StringBuffer(int length)   创建一个长度为length的字符串缓冲区
         *          *  StringBuffer(String string)   创建一个长度为string.length()+16的字符串缓冲区
         *          *
         *          *  字段：
         *          *  	无
         *          *
         *          *	常用方法：
         *          *      append(X)  将传进的数据转换成字符串形式，追加到结尾.注意：返回的是对象本身
         *          *      insert(int insert的位置, 任意类型）   //将传进的数据转换成字符串形式，插入到指定位置.注意：返回的是对象本身
         *          *      delete(int start, int end) //删除[start,end)
         *                 deleteCharAt(int index) 	//删除索引位置的字符
         *                 String substring(int start) //与字符串相同
         *                 String substring(int start, int end) //与字符串相同
         *                 StringBuffer replace(int start, int end, String str)  //将指定的起始位置到结尾位置的字符串转换成给定的字符串
         *                 StringBuffer reverse()  //字符串翻转功能
         */
        System.out.println("======StringBuffer==============");
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer(20);
        StringBuffer stringBuffer3 = new StringBuffer(" realy!");
        StringBuffer stringBuffer4 = new StringBuffer("Java is my life");

        System.out.println(stringBuffer3.length()); // 7 字符串缓冲区的长度(实际数据的长度)
        System.out.println(stringBuffer3.capacity());// 23=7 + 16 字符串缓冲区开辟的大小(容量)
        System.out.println(stringBuffer4.append(stringBuffer3)); // Java is my life realy!
    }

    /**
     * 字符串缓冲区反转
     */
    @Test
    public void stringBuilderReverse() {
        // 字符串缓冲区变字符串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1);
        stringBuilder.append(2);
        stringBuilder.append(3);
        stringBuilder.append(4);
        stringBuilder.append(5);
        String string = stringBuilder.toString();
        // 字符串变字符数组
        char[] charArray = string.toCharArray();
        // 倒序遍历数组
        for (int i = 0, y = charArray.length - 1; i < y; i++, y--) {
            // 交换两个字符位置
            char temp = charArray[i];
            charArray[i] = charArray[y];
            charArray[y] = temp;
        }
        // 数组反转完毕后放回缓冲区中
        stringBuilder.delete(0, stringBuilder.length()).append(charArray);
        System.out.println(stringBuilder.toString());
        System.out.println("==================");
        // StringBuilder.reverse()
        stringBuilder = stringBuilder.reverse();
        System.out.println(stringBuilder.toString());
    }

    /**=======================================================*/
    /**
     * 正则表达式编译表示形式
     * * Pattern和Matcher
     * *
     * * 范例:
     * Pattern p = Pattern.compile("a*b");   //指定一个符合某规则的正则表达式编译表示形式
     * Matcher m = p.matcher("aaaaab");		//要匹配的字符串，光剑匹配器
     * boolean b = m.matches();				//要进行的操作：这里这句话进行的操作就是：将整个字符串进行这个公式的匹配
     * 在仅使用一次正则表达式时，可以方便地通过此类定义 matches 方法。
     * 此方法编译表达式并在单个调用中将输入序列与其匹配。语句
     * boolean b = Pattern.matches("a*b", "aaaaab");
     *  
     * 步骤:
     * 1:先将正则表达式编译成对象,使用的是Pattern类中一个静态的方法.compile(regex);
     * 2:让正则表达式和要操作的字符串相关联,通过matches方法完成,并返回匹配器对象.
     * 3:通过匹配器对象的方法将正则表达式作用到字符串上对字符串进行针对性的功能操作.
     */
    @Test
    public void test1() {
        //想要获取3个字母组成的单词
        String str = "da jia zhu yi le, ming tian bu fang jia, xie xie!";
        String reg = "\\b[a-z]{3}\\b";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);

        while (m.find()) {
            //System.out.println(m.start()+"..."+m.end());
            //System.out.println("sub:"+str.substring(m.start(),m.end()));
            System.out.println(m.group());//必须与find()方法一起使用
        }
    }

    public boolean safeEquals(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int equal = 0;
        for (int i = 0; i < a.length(); i++) {
            equal |= a.charAt(i) ^ b.charAt(i);
        }
        return equal == 0;
    }

    /**
     * 六位随机验证码
     * 利用字符数组，获取验证码
     * 随机数，随机产生下标，通过下标到数组中获取一个字符
     * 装在字符串的缓冲区中，如果缓冲去长度到达6个，直接退出循环
     */
    public static void main(String[] args) {
        // 字符串格式化案例
        String name = "zhangwuji";
        int sex = 1;
        int age = 18;
        String format = "I am %s, I am %s, I am %d years old !";
        System.out.printf(format, name, sex, age);
        System.out.println();
        // 定义数组，保存一些字符
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', '你', '我', '他', '她', '还', '有', '谁'};
        Scanner scanner = new Scanner(System.in);
        // 循环得到随机数
        w:
        while (true) {
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                int i = new Random().nextInt(chars.length);
                // 获取到的随机数作为数组下标获取值
                // 字符转字符串，不建议这种形式+""
                String value = String.valueOf(chars[i]);
                // 判断这个字符是否存在缓冲区中，字符缓冲区变String，调用contains()方法
                if (stringBuilder.toString().contains(value)) {
                    // 存在，继续随机
                    continue;
                } else {
                    stringBuilder.append(value);
                }
                // 然后判断字符串缓冲区的长度
                if (6 == stringBuilder.length()) {
                    break;
                }
            }
            System.out.println(stringBuilder);
            String line = scanner.nextLine();
            if (stringBuilder.toString().equalsIgnoreCase(line)) {
                System.out.println("正确");
                break;
            } else {
                System.out.println("错误");
                continue w;
            }
        }
    }

    @Test
    public void stringUtilsTest() {
        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank(" "));
        System.out.println(StringUtils.isBlank(null));
    }
}
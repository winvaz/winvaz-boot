package com.icore.winvaz.javase.basic.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @Deciption Java 8 新特性--Optional
 * @Author wdq
 * @Create 2019-10-12 13:47
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Integer x = null;
        Integer y = new Integer(10);

        // Optional.ofNullable()允许传递null
        Optional<Integer> x1 = Optional.ofNullable(x);

        // Optional.of()不允许传递null，否则报空指针异常(NullPointException)
        Optional<Integer> y1 = Optional.of(y);

        System.out.println("两数之和为:" + add(x1, y1));

        System.out.println("=============");
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        //get count of empty string
        Optional<String> any = strings.stream().filter(string -> string.isEmpty()).findFirst();
        Pattern pattern = Pattern.compile("^[^\\*]*\\*+.*$");
    }
    
    /**
     * @author wdq
     * @create 2019/11/16 14:45
     * @param a
     * @param b
     * @Return java.lang.Integer
     * @exception 
     */
    public static Integer add(Optional<Integer> a, Optional<Integer> b) {

        // Optional.isPresent()判断值是否存在
        System.out.println("第一个值：" + a.isPresent());
        System.out.println("第二个值：" + b.isPresent());

        // Optional.orElse()如果值存在返回该值，不存在设置默认值
        Integer x = a.orElse(new Integer(0));

        // Optional.get()获取值，值需要存在
        Integer y = b.get();

        return x + y;
    }
}
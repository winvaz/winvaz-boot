package com.icore.winvaz.javase.basic.java8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Deciption Java 8 新特性--Base64
 * @Author wdq
 * @Create 2019-10-14 14:02
 */
public class Base64Demo {
    public static void main(String[] args) {
        try {
            // 编码
            byte[] encode = Base64.getEncoder().encode("winvaz@163.com".getBytes("UTF-8"));
            System.out.println("Base64编码：" + new String(encode, "UTF-8"));

            // 解码
            byte[] decode = Base64.getDecoder().decode(encode);
            System.out.println("Base64解码：" + new String(decode, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

package com.icore.winvaz.javase.basic.socket;
/**
 * 抓取本机文件中的邮件
 * 抓取远程互联网邮件
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatchEmail {
    public static void main(String[] args) throws Exception {
        catchWebEmail();
    }

    //获取Web网络资源上的邮件列表
    private static void catchWebEmail() throws Exception {
        //、定义正则规则
        String reg = "[48]00-[0-9]{3}-[0-9]{4}";//400-882-8008
        Pattern p = Pattern.compile(reg);
        //写URL，地址进行连接，创建URL类对象
        URL url = new URL("http://appserver.lenovo.com.cn/Public/public_bottom/contact.shtml");
        //使用URL类的方法。openConnection去掉和地址URL的连接
        URLConnection connection = url.openConnection();
        //通过连接对象，获取字节输入流，读取Web地址中的资源
        BufferedReader bfr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        while ((line = bfr.readLine()) != null) {
            //第二步，调用Pattern类方法，matcher(字符串)方法，返回Matcher对象，匹配对象
            Matcher m = p.matcher(line);
            //第三步，调用Mathcer中的方法find，找匹配后的内容，find返回布尔值，找到匹配就是true
            while (m.find()) {
                //第四步，获取到匹配后的结果， Mathcer类中的方法 group获取，返回String
                System.out.println(m.group());
            }
        }
    }

    //获取本机文件中的邮件列表
    private static void catchEmail() throws Exception {
        //、定义正则规则
        String reg = "[a-zA-Z0-9_]+@[a-z0-9]+(\\.[a-z]+)+";
        //第一步，调用Pattern类的静态方法compile将正则规则预编译，方法返回Pattern类的对象
        Pattern p = Pattern.compile(reg);
        //流读取文本文件
        BufferedReader bfr = new BufferedReader(new FileReader("c:\\mail.txt"));
        String line = null;
        while ((line = bfr.readLine()) != null) {
            //第二步，调用Pattern类方法，matcher(字符串)方法，返回Matcher对象，匹配对象
            Matcher m = p.matcher(line);
            //第三步，调用Mathcer中的方法find，找匹配后的内容，find返回布尔值，找到匹配就是true
            while (m.find()) {
                //第四步，获取到匹配后的结果， Mathcer类中的方法 group获取，返回String
                System.out.println(m.group());
            }
        }
    }
}
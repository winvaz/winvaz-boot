package com.icore.winvaz.javase.basic.socket;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author wdq
 * @Create 2021/4/5 13:26
 * @Version 1.0.0
 */
public class URLConnectionTest {
    public static void main(String[] args) throws IOException {
        String urlName;
        if (args.length > 0) urlName = args[0];
        // else urlName = "http://horstmann.com";
        else urlName = "https://www.baidu.com/s?ie=utf-8&f=3&rsv_bp=1&tn=02003390_42_hao_pg&wd=mac%E8%A7%86%E9%A2%91%E6%A0%BC%E5%BC%8F%E8%BD%AC%E6%8D%A2%E8%BD%AF%E4%BB%B6&oq=mac%2520%25E8%25A7%2586%25E9%25A2%2591%25E5%2589%25AA%25E8%25BE%2591%25E8%25BD%25AF%25E4%25BB%25B6%25E6%258E%25A8%25E8%258D%2590&rsv_pq=8ee1955500099776&rsv_t=7a55n1eX6nDBJ9zSWotE74l52H4j3AipkOSgYt5t%2BPWNiqrTV82tWMaQBeQd0FjzZU3sk7xdqloP&rqlang=cn&rsv_dl=ts_1&rsv_enter=1&rsv_sug3=7&rsv_sug1=3&rsv_sug7=100&rsv_sug2=1&rsv_btype=t&prefixsug=mac%2520%25E8%25A7%2586%25E9%25A2%2591geshi&rsp=1&inputT=4090&rsv_sug4=6184";

        URL url = new URL(urlName);
        URLConnection conn = url.openConnection();

        // set username password if specified on command line
        if (args.length > 2) {
            String username = args[1];
            String password = args[2];
            String input = username + ":" + password;
            Base64.Encoder encoder = Base64.getEncoder();
            String encode = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
            conn.setRequestProperty("Authorization", "Basic " + encode);
        }

        conn.connect();

        // print header fields
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                System.out.println(key + "="+value);
            }
        }

        // print convenience functions
        System.out.println("-------------");
        System.out.println("getContentType: " + conn.getContentType());
        System.out.println("getContentLength: " + conn.getContentLength());
        System.out.println("getContentEncoding: " + conn.getContentEncoding());
        System.out.println("getDate: " + conn.getDate());
        System.out.println("getExpiration: " + conn.getExpiration());
        System.out.println("getLastModified: " + conn.getLastModified());
        System.out.println("-----------");

        String encoding = conn.getContentEncoding();
        if (encoding == null) encoding = "UTF-8";
        try (Scanner scanner = new Scanner(conn.getInputStream(), encoding)) {
            // print first ten lines of contents
            for (int i = 1; scanner.hasNextLine() && i <= 10; i++) {
                System.out.println(scanner.nextLine());
            }
            if (scanner.hasNextLine()) {
                System.out.println("。。。");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

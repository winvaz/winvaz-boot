package com.icore.winvaz.javase.apache.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @Deciption HttpClient.GET
 * @Author wdq
 * @Create 2019/11/26 10:54
 */
public class HttpClient {
    public static void main(String[] args) {
        // 创建一个HttpClient对象
        CloseableHttpClient httpClient = null;
        // 执行请求
        CloseableHttpResponse httpResponse = null;
        try {
            // 创建一个HttpClient对象
            httpClient = HttpClients.createDefault();
            // 创建一个HttpGet对象
            HttpGet httpGet = new HttpGet("https://www.kaops.com/");
            //HttpPost httpPost = new HttpPost("https://www.kaops.com/");
            // 输出请求方式
            System.out.println("Request Method:" + httpGet.getMethod());
            // 执行请求
            httpResponse = httpClient.execute(httpGet);
            System.out.println(httpResponse.getStatusLine());
            // 终止请求
            httpGet.abort();
            System.out.println(httpResponse.getEntity().getContentLength());
            // 再次执行
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            System.out.println(execute.getStatusLine());
            // 打印响应数据
            //Scanner scanner = new Scanner(httpResponse.getEntity().getContent());
            // 打印状态行
            /*System.out.println("HttpResponse Status Line:" + httpResponse.getStatusLine());
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

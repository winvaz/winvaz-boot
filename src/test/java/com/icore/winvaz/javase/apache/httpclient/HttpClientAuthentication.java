package com.icore.winvaz.javase.apache.httpclient;

import org.apache.http.Header;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @Deciption HttpClient用户校验
 * @Author wdq
 * @Create 2019/11/29 17:06
 */
public class HttpClientAuthentication {
    public static void main(String[] args) {
        try {
            // 创建CredentialsProvider对象
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            // 设置凭据
            AuthScope authScope = new AuthScope("https://www.kaops.com/", 80);
            Credentials credentials = new UsernamePasswordCredentials("USERNAME", "PASSWORD");;
            credentialsProvider.setCredentials(authScope, credentials);
            //credentialsProvider.setCredentials(new AuthScope("example.com", 80), new UsernamePasswordCredentials("user", "mypass"));
            //credentialsProvider.setCredentials(new AuthScope("localhost", 8080), new UsernamePasswordCredentials("abc", "password"));
            // 创建HttpClientBuilder对象
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            // 设置credentialsPovider
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            // 构建CloseableHttpClient
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
            // 创建一个HttpGet对象并执行它
            HttpGet httpGet = new HttpGet("https://www.kaops.com/index.php");
            // 打印请求方式
            System.out.println(httpGet.getMethod());
            // 执行GET请求
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            // 打印状态行
            System.out.println(response.getStatusLine());
            // 打印状态码
            System.out.println(response.getStatusLine().getStatusCode());
            // 打印所有响应头
            Header[] allHeaders = response.getAllHeaders();
            for (int i = 0; i < allHeaders.length; i++) {
                System.out.println(allHeaders[i].getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

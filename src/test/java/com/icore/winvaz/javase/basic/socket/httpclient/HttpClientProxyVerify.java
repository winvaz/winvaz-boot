package com.icore.winvaz.javase.basic.socket.httpclient;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @Deciption HttpClient代理验证
 * @Author wdq
 * @Create 2019/12/2 11:19
 */
public class HttpClientProxyVerify {
    public static void main(String[] args) {
        try {
            // 创建CredentialsProvider对象
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            // 设置凭证
            credentialsProvider.setCredentials(new AuthScope("example.com", 80), new UsernamePasswordCredentials("user", "mypass"));
            credentialsProvider.setCredentials(new AuthScope("localhost", 8000), new UsernamePasswordCredentials("abc", "mypasswd"));
            // 创建HttpClientBuilder对象
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            // 设置CredentialsProvider
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            // 构建CloseableHttpClient
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
            // 创建代理和目标主机
            HttpHost target = new HttpHost("example.com", 80, "http");
            HttpHost proxy = new HttpHost("localhost", 8000, "http");
            // 设置代理并构建RequestConfig对象
            RequestConfig.Builder builder = RequestConfig.custom();
            builder = builder.setProxy(proxy);
            RequestConfig requestConfig = builder.build();
            // 创建一个HttpGet请求
            HttpGet httpGet = new HttpGet("/");
            httpGet.setConfig(requestConfig);
            // 执行GET请求
            CloseableHttpResponse response = closeableHttpClient.execute(target, httpGet);
            System.out.println(response.getStatusLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

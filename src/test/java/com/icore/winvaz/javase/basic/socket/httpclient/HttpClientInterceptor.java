package com.icore.winvaz.javase.basic.socket.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @Author wdq
 * @Create 2019/11/29 15:32
 */
public class HttpClientInterceptor {
    public static void main(String[] args) {
        try {
            /**
             * 请求拦截器
             */
            HttpRequestInterceptor httpRequestInterceptor = new HttpRequestInterceptor(){
                @Override
                public void process(HttpRequest request, HttpContext httpContext) throws HttpException, IOException {
                    if (request.containsHeader("sample-header")) {
                        System.out.println("Contain header sample-header removing it..");
                        request.removeHeaders("sample-header");
                    }
                    // 打印所有header
                    Header[] allHeaders = request.getAllHeaders();
                    for (int i = 0; i < allHeaders.length; i++) {
                        System.out.println(allHeaders[i].getName());
                    }
                }
            };
            /**
             */
            HttpResponseInterceptor httpResponseInterceptor = new HttpResponseInterceptor() {
                @Override
                public void process(HttpResponse response, HttpContext httpContext) throws HttpException, IOException {
                    System.out.println("Adding header sample-header, demo-header, test-header to response");
                    response.setHeader("sample-header", "My First header");
                    response.setHeader("demo-header", "My scond header");
                    response.setHeader("test-header", "My third header");
                }
            };
            // 创建HttpClient对象
            CloseableHttpClient closeableHttpClient = HttpClients.custom().addInterceptorFirst(httpResponseInterceptor).build();
            // 创建GET请求
            HttpGet httpGet = new HttpGet("https://www.kaops.com/");
            // 设置头
            httpGet.setHeader(new BasicHeader("sample-header", "My First Header"));
            httpGet.setHeader(new BasicHeader("demo-header", "My second Header"));
            httpGet.setHeader(new BasicHeader("test-header", "My third Header"));
            // 执行请求
            HttpResponse response = closeableHttpClient.execute(httpGet);
            // 打印状态行
            //System.out.println(response.getStatusLine());
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

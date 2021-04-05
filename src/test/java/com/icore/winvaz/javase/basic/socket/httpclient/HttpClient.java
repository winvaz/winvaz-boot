package com.icore.winvaz.javase.basic.socket.httpclient;

import com.icore.winvaz.util.common.LogUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Deciption HttpClient.GET
 * @Author wdq
 * @Create 2019/11/26 10:54
 */
public class HttpClient {
    /*
    public static void main(String[] args) {
        RequestConfig config = RequestConfig.custom()
                .setRedirectsEnabled(false)
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000).build();
        // 创建一个HttpClient对象
        // CloseableHttpClient httpClient = null;
        // 执行请求
        // CloseableHttpResponse httpResponse = null;
        for (int i = 0; i < 5000; i++) {
            try {
                // 创建一个HttpClient对象
                CloseableHttpClient httpClient = HttpClients.createDefault();
                // 创建一个HttpGet对象
                HttpGet httpGet = new HttpGet("http://www.baidu.com/");
                //HttpPost httpPost = new HttpPost("https://www.kaops.com/");
                // 输出请求方式
                System.out.println("Request Method:" + httpGet.getMethod());
                // 执行请求
                CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
                EntityUtils.consume(httpResponse.getEntity());
                // 只关心code
                LogUtils.info("status code: {}", httpResponse.getStatusLine().getStatusCode());
                // System.out.println(httpResponse.getStatusLine());
                // 终止请求
                // httpGet.abort();
                // System.out.println(httpResponse.getEntity().getContentLength());
                // 再次执行
                // CloseableHttpResponse execute = httpClient.execute(httpGet);
                // System.out.println(execute.getStatusLine());
                // 打印响应数据
                //Scanner scanner = new Scanner(httpResponse.getEntity().getContent());
                // 打印状态行
            *//*System.out.println("HttpResponse Status Line:" + httpResponse.getStatusLine());
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }*//*
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // httpClient.close();
                // httpResponse.close();
            }
        }
    }*/

    public static void main(String[] args) throws IOException {
        String http = "http://api.shipxy.com/apicall/GetManShip";
        RequestConfig config = RequestConfig.custom()
                .setRedirectsEnabled(false)
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000).build();
        HttpClientBuilder.create().setDefaultRequestConfig(config);
        CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();

        for (int i = 0; i < 10; i++) {
            HttpGet request = new HttpGet(http);
            CloseableHttpResponse response = client.execute(request);
            EntityUtils.consume(response.getEntity());
            // 只关心code
            LogUtils.info("status code: {}", response.getStatusLine().getStatusCode());
        }
    }
}

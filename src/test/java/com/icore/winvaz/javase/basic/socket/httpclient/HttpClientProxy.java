package com.icore.winvaz.javase.basic.socket.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Deciption HttpClient使用代理
 *            代理服务器是客户端和Internet之间的中间服务器。代理服务器提供以下基本功能
 *              防火墙和网络数据过滤
 *              网络连接共享
 *              数据缓存
 *              @Author wdq
 * @Create 2019/11/29 17:34
 */
public class HttpClientProxy {
    public static void main(String[] args) {
        try {
            // 创建一个HttpHost主机目标
            HttpHost proxyHost = new HttpHost("192.168.110.142");
            // 创建另一个HttpHost对象来表示需要向其发送请求的目标主机。
            HttpHost targetHost = new HttpHost("www.baidu.com:80");
            // 创建一个HttpRoutePlanner(接口计算到指定主机的路由)对象
            HttpRoutePlanner httpRoutePlanner = new DefaultProxyRoutePlanner(proxyHost);
            // 将路线规划器设置为客户端构建器
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            httpClientBuilder = httpClientBuilder.setRoutePlanner(httpRoutePlanner);
            // 构建CloseableHttpClient对象
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
            // 创建一个HttpGetobject
            HttpGet httpGet = new HttpGet("/");
            // 执行请求
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(targetHost, httpGet);
            // 打印状态行
            System.out.println(httpResponse.getStatusLine());
            // 打印所有响应头
            Header[] allHeaders = httpResponse.getAllHeaders();
            for (int i = 0; i < allHeaders.length; i++) {
                System.out.println(allHeaders[i].getName());
            }
            // 打印响应体
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.icore.winvaz.javase.apache.httpclient;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @Deciption HttpClient基于表单登录
 * @Author wdq
 * @Create 2019/12/2 11:39
 */
public class HttpClientFormLogin {
    public static void main(String[] args) {
        /*
        // 创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建RequestBuilder对象
        RequestBuilder requestBuilder = RequestBuilder.post();
        // 将URI和参数设置为RequestBuilder
        requestBuilder.setUri("http://httpbin.org/post");
        requestBuilder.addParameter("name", "username").addParameter("password", "password");
        // 构建HttpUriRequest对象
        HttpUriRequest httpUriRequest = requestBuilder.build();
        // 执行请求
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println(response.getStatusLine());
        */

        /**
         *  Cookie表单登录
         */
        // 创建BasicCookieStore对象
        BasicCookieStore basicCookieStore = new BasicCookieStore();
        // 创建ClientCookie对象
        BasicClientCookie basicClientCookie = new BasicClientCookie("name", "maxsu");
        // 将值设置为Cookie
        Calendar calendar = new GregorianCalendar(2020, 01, 01);
        basicClientCookie.setExpiryDate(calendar.getTime());
        basicClientCookie.setDomain(".sample.com");
        basicClientCookie.setPath("/");
        basicClientCookie.setSecure(true);
        basicClientCookie.setValue("25");
        basicClientCookie.setVersion(5);
        basicCookieStore.addCookie(basicClientCookie);
        // 创建HttpClientBuilder对象
        /*HttpClientBuilder httpClientBuilder = HttpClients.custom();
        // 使用setDefaultCookieStore()将Cookie存储设置为客户端构建器
        httpClientBuilder.setDefaultCookieStore(basicCookieStore);
        // 使用Build()构建CloseableHttpClient对象
        CloseableHttpClient httpClient = httpClientBuilder.build();
        List<Cookie> cookies = basicCookieStore.getCookies();
        Iterator<Cookie> iterator = cookies.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
    }
}

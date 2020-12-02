package com.icore.winvaz.javase.apache.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Deciption 处理响应程序
 * @Author wdq
 * @Create 2019/11/26 14:05
 */
public class ResponseHandlerDemo {
    public static void main(String[] args) {
        try {
            // 创建HttpClient对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 初始化响应对象
            ResponseHandler<String> responseHandler = new MyResponseHandler();
            // 创建get请求
            HttpGet httpGet = new HttpGet("https://www.kaops.com/");
            // 执行请求
            String execute = httpClient.execute(httpGet, responseHandler);
            // 打印
            System.out.println(execute);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyResponseHandler implements ResponseHandler<String> {

    @Override
    public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
        // 获取响应状态
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >= 200 && statusCode < 300) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return "";
            } else {
                return EntityUtils.toString(entity);
            }
        } else {
            return "" + statusCode;
        }
    }
}

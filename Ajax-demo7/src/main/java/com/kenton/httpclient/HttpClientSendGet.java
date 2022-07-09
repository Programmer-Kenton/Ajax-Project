package com.kenton.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author: Kenton
 * @description
 * @date: 2022/7/8 22:55
 */
public class HttpClientSendGet {
    public static void main(String[] args) throws Exception {
        // 使用Java代码发送Http get请求
        // 目标地址
//        String url = "https://www.baidu.com";
        String url = "https://localhost:8081/AjaxB/hello";
        HttpGet httpGet = new HttpGet(url);

        // 设置类型 "application/x-www-form-urlencoded" "application/json"
        httpGet.setHeader("Content-Type","application/x-www-form-urlencoded");
        System.out.println("调用URL:" + httpGet.getURI());

        // httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求并获取返回
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.println("返回状态码:" + response.getStatusLine());

        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8));
        String line = null;
        StringBuilder responseSB = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseSB.append(line);
        }
        System.out.println("返回消息:" + responseSB);
        reader.close();
        httpClient.close();

    }
}

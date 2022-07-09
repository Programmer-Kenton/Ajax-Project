package com.kenton.javaweb.servlet;

import com.sun.net.httpserver.HttpServer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: Kenton
 * @description
 * @date: 2022/7/8 23:35
 */
public class ProxyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过Httpclient组件 发送Http GET请求 访问TargetServlet
        String url = "http://localhost:8081/AjaxB/target";
        HttpGet httpGet = new HttpGet(url);

        // 设置类型 "application/x-www-form-urlencoded" "application/json"
        httpGet.setHeader("Context-Type","application/x-www-form-urlencoded");

        // httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求并获取返回
        CloseableHttpResponse responseProxy = httpClient.execute(httpGet);
        HttpEntity entity = responseProxy.getEntity();

        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        StringBuffer responseSB = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseSB.append(line);
        }
        System.out.println("服务器响应的数据:" + responseSB);
        reader.close();
        httpClient.close();

        // b站点响应回来的数据
        response.getWriter().print(responseSB);
    }
}

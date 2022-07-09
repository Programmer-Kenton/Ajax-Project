package com.kenton.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Kenton
 * @description
 * @date: 2022/7/7 13:37
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应头允许Ajax跨域请求
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        // 响应
        response.getWriter().print("{\"username\":\"zhangsan\"}");
    }
}

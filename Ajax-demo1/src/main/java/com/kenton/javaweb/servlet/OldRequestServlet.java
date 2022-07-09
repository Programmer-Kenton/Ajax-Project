package com.kenton.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Kenton
 * @description 传统请求
 * @date: 2022/6/30 17:47
 */
@WebServlet("/old/request")
public class OldRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应信息到浏览器
        resp.setContentType("/text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        // 向浏览器输出HTML代码 浏览器收到HTML代码之后渲染页面 展示页面给用户
        writer.print("<h1>欢迎学习Ajax</h1>");
    }
}

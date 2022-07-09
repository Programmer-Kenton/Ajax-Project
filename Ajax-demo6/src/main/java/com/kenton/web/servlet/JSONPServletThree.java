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
 * @date: 2022/7/7 14:16
 */
@WebServlet("/jsonp3")
public class JSONPServletThree extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取函数名
        // String callback = request.getParameter("callback");
        String callback = request.getParameter("fun");
        // 响应一段JS代码 调用函数
        response.getWriter().print(callback + "({\"username\":\"lisi\"})");
    }
}

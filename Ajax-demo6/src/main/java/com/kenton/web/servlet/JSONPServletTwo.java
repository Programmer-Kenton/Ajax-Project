package com.kenton.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Kenton
 * @description
 * @date: 2022/7/7 14:16
 */
@WebServlet("/jsonp2")
public class JSONPServletTwo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取函数名
        String fun = request.getParameter("fun");
        // 响应一段js代码
        response.getWriter().print(fun+"({\"username\":\"lucy\"})");
    }
}

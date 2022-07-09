package com.kenton.ajax.servlet;

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
 * @date: 2022/6/30 19:36
 */
@WebServlet("/ajax/ajaxrequest1")
public class AjaxRequest1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* String s = null;
        s.toString();*/

        // Servlet向浏览器响应一段数据
        PrintWriter writer = resp.getWriter();

        //writer对象向浏览器输出信息
        // 服务器代码实际上和以前的代码还是一样的
        // 只不过这个writer在响应的时候 浏览器客户端的XMLHttpRequest对象会接收这个响应的信息
        writer.print("<font color='red'>welcome to ajax</font>");
    }
}

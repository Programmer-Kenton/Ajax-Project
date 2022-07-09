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
@WebServlet("/jsonp1")
public class JSONPServletOne extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 后台输出
        System.out.println("json方式完成跨域访问");

        // 向前端响应一段js代码
        PrintWriter writer = response.getWriter();
//        writer.print("alert(123)");
//        writer.print("sayHello()");

        // 响应一段js代码 然后传一个json数据到前端
//        writer.print("sayHello({\"name\":\"jackson\"})");

        // 动态获取函数名
        String fun = request.getParameter("fun");
        writer.print(fun+"({\"name\":\"jackson\"})");

    }
}

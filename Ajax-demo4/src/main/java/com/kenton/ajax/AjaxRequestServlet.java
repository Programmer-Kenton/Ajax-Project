package com.kenton.ajax;

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
 * @date: 2022/7/1 15:39
 */
@WebServlet(value = "/ajax-request")
public class AjaxRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型以及字符集
        resp.setContentType("/text/html;charset=UTF-8");
        // 获取响应流
        PrintWriter writer = resp.getWriter();
        // 响应
        // writer.print("<font color='red'>用户名已经存在!!!</font>");

        // 获取Ajax get请求提交的数据
        String usercode = req.getParameter("usercode");
        String username = req.getParameter("username");

        writer.print("usercode=" + usercode + ",username=" + username);
    }
}

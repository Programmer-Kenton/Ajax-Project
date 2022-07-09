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
@WebServlet(value = "/ajax-request2")
public class AjaxRequestServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        // writer.print("<font color='red'>狗蛋已经存在了!!!</font>");

        // 获取提交的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        writer.print("用户名:"+username + "密码:" + password);
    }
}

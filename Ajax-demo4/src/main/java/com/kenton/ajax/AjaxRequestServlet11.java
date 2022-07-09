package com.kenton.ajax;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author: Kenton
 * @description 将Ajax请求分装到jQuery当中
 * @date: 2022/7/6 10:49
 */
@WebServlet("/ajaxrequest11")
public class AjaxRequestServlet11 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("{\"username\":\""+username.toUpperCase(Locale.ROOT) + "\"}");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("{\"username\":\""+username.toLowerCase(Locale.ROOT) + "\"}");
    }
}

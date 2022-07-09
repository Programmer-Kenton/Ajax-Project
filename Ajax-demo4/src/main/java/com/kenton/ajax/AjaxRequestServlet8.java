package com.kenton.ajax;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Kenton
 * @description 测试Ajax同步和异步
 * @date: 2022/7/5 23:08
 */
@WebServlet("/ajaxrequest8")
public class AjaxRequestServlet8 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("ajax请求1");
    }
}

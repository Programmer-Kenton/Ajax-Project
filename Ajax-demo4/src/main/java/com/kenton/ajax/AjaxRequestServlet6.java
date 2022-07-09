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
 * @description 服务器端返回XML字符串
 * @date: 2022/7/4 18:00
 */
@WebServlet("/ajaxrequest6")
public class AjaxRequestServlet6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 注意:响应的内容类型是XML
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        /*
         <students>
             <student>
                <name>zhangsan</name>
                <age>20</age>
             </student>
             <student>
                <name>lisi</name>
                <age>22</age>
             </student>
          </students>
         */
        StringBuilder xml = new StringBuilder();
        xml.append("<students>");
        xml.append("<student>");
        xml.append("<name>zhangsan</name>");
        xml.append("<age>20</age>");
        xml.append("</student>");
        xml.append("<student>");
        xml.append("<age>22</age>");
        xml.append("</student>");
        xml.append("</students>");

        writer.print(xml);
    }
}

package com.kenton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author: Kenton
 * @description 验证用户名是否存在
 * @date: 2022/7/1 15:39
 */
@WebServlet(value = "/ajax-request3")
public class AjaxRequestServlet3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名
        String username = request.getParameter("username");
        // 定义布尔标记
        boolean flag = false; // 默认用户不存在
        // 验证用户名是否存在
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajax?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC",
                    "root", "root");
            // 3.获取预编译的数据库操作对象
            String sql = "select id,name from t_user where name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            // 4.执行SQL语句
            rs = ps.executeQuery();
            // 5.处理结果集
            if (rs.next()) {
                // 用户名已存在
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 响应结果到浏览器
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        if (flag) {
            // 用户名已存在 不可用
            writer.print("<font color='red'>对不起 用户名已存在</font>");
        }else {
            // 用户名不存在 可用使用
            writer.print("<font color='green'>用户名可以使用</font>");
        }
    }
}

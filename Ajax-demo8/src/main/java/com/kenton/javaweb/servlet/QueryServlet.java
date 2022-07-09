package com.kenton.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @author: Kenton
 * @description
 * @date: 2022/7/9 18:41
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入的关键字
        String keywords = request.getParameter("keyword");
        // jdbc代码连接数据库 根据关键字查询数据库 返回数据 拼接json格式字符串
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/ajax?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = "java";
            conn = DriverManager.getConnection(url,user,password);

            String sql = "select content from t_ajax where content like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,keywords + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String content = rs.getString("content");
                sb.append("{\"content\":\"+content+\"},");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(sb.subSequence(0,sb.length()-1)+"]");
    }
}

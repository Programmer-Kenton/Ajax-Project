package com.kenton.ajax;

import com.alibaba.fastjson.JSON;
import com.kenton.ajax.beans.Area;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kenton
 * @description 动态获取对应的区域
 * @date: 2022/7/6 12:53
 */
@WebServlet("/listArea")
public class ListAreaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pcode = request.getParameter("pcode");
        // 连接数据库 获取所有的对应区域。最终响应一个JSON格式的字符串给web前端
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Area> areaList = new ArrayList<>();
        String sql;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/ajax?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String password = "java";
            conn = DriverManager.getConnection(url,user,password);
            // 获取预编译数据库对象
            if (pcode == null) {
                sql = "select code,name from t_area where pcode is null";
                ps = conn.prepareStatement(sql);
            }else {
                sql = "select code,name from t_area where pcode = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,pcode);
            }
            ps = conn.prepareStatement(sql);
            // 执行sql
            rs = ps.executeQuery();
            // 处理结果集
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                Area area = new Area(code, name);
                areaList.add(area);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (rs != null) {
                try {
                    rs.close();
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        // 使用fastjson将Java对象转换成json字符串
        String json = JSON.toJSONString(areaList);
        response.getWriter().print(json);
    }
}

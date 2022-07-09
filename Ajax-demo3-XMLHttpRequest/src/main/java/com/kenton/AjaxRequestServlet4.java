package com.kenton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: Kenton
 * @description 验证用户名是否存在
 * @date: 2022/7/1 15:39
 */
@WebServlet(value = "/ajax-request4")
public class AjaxRequestServlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 连接数据库 查询学员信息 拼接HTML代码 响应HTML代码到浏览器
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        // 连接数据库 拼接HTML代码
        StringBuilder html = new StringBuilder();

        // 目前存在的缺点 在后端的Java代码中开始拼接HTML代码 不易维护
       /*html.append("<tr>");
       html.append("<th>1</th>");
       html.append("<th>张三</th>");
       html.append("<th>20</th>");
       html.append("<th>北京大兴区</th>");
       html.append("</tr> ");
       html.append("<tr>");
       html.append("<th>2</th>");
       html.append("<th>李四</th>");
       html.append("<th>22</th>");
       html.append("<th>北京海淀区</th>");

       writer.print(html);*/

        // 将以上程序拼接HTML 换成拼接JSON格式的字符串
        /*String jsonStr = "[{\"name\":\"王五\",\"age:\"20\",\"addr\":\"北京大兴区\"},{\"name\":\"李四\",\"age\":22,\"addr\":\"北京海淀区\"}]";*/

        // 准备StringBuilder对象 拼接JSON
        StringBuilder json = new StringBuilder();
        // 连接数据库 查询所有学生 拼接json字符串
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String jsonStr;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.获取连接
            DriverManager.getConnection("jdbc:mysql://localhost:3306/ajax?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", "root", "java");
            // 3.获取预编译的数据库操作对象
            String sql = "select name,age,addr from t_student";
            ps = conn.prepareStatement(sql);
            // 4.执行SQL语句
            rs = ps.executeQuery();
            // 5.处理结果集
            json.append("[");
            while (rs.next()) {
                // 获取每个学生的信息
                String name = rs.getString("name");
                String age = rs.getString("age");
                String addr = rs.getString("addr");
                // 拼接json格式的字符串
                // {"name":"王五","age":20,"addr":"北京大兴区"}
                json.append("{\"name\":\"");
                json.append(name);
                json.append("\",\"age\":");
                json.append(age);
                json.append(",\"addr\":\"");
                json.append(addr);
                json.append("\"},");
            }
            jsonStr = json.substring(0, json.length() - 1) + "]";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 响应JSON格式的字符串给前端
        writer.print(jsonStr);
    }
}

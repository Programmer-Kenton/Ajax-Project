package com.kenton.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kenton
 * @description 测试fastjson
 * @date: 2022/7/4 17:34
 */
public class FastjsonTest {
    public static void main(String[] args) {
        // 创建User对象
        User user = new User("111","zhangsan",20);

        // 将以上的User对象转换成json格式的字符串
        // 使用阿里巴巴的fastJson组件中的Json类即可
        // fastjson中有一个类 又叫JSON
        // JSON类中的方法都是静态的 直接调用即可
        String jsonStr = JSON.toJSONString(user);
        System.out.println(jsonStr);

        // 尝试List集合是否可以转换成数组
        List<User> userList = new ArrayList<>();
        User userOne = new User("120", "lisi", 30);
        User userTwo = new User("130", "jackson", 33);
        userList.add(userOne);
        userList.add(userTwo);

        String jsonStr2 = JSON.toJSONString(userList);
        System.out.println(jsonStr2);
    }
}

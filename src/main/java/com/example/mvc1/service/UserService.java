package com.example.mvc1.service;

import com.example.mvc1.model.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    // 模拟数据库：存储用户（线程安全的Map）
    private static final Map<String, User> userMap = new HashMap<>();

    // 初始化测试用户
    static {
        userMap.put("1", new User("1", "admin", "123456", 25));
        userMap.put("2", new User("2", "张三", "654321", 22));
        userMap.put("3", new User("3", "李四", "111222", 20));
    }

    // 登录验证
    public User login(String username, String password) {
        for (User user : userMap.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // 根据ID查询用户
    public User getUserById(String id) {
        return userMap.get(id);
    }
}
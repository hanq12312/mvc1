package com.example.mvc1.controller;

import com.example.mvc1.model.User;
import com.example.mvc1.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理中文乱码
        req.setCharacterEncoding("UTF-8");

        // 获取表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String captcha = req.getParameter("captcha");

        // 验证验证码
        HttpSession session = req.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha)) {
            req.setAttribute("error", "验证码错误！");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        // 验证用户名密码
        UserService userService = new UserService();
        User user = userService.login(username, password);
        if (user != null) {
            // 登录成功，存储用户信息
            session.setAttribute("loginUser", user);
            resp.sendRedirect(req.getContextPath() + "/list"); // 跳转到讨论列表
        } else {
            // 登录失败
            req.setAttribute("error", "用户名或密码错误！");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }

    // 允许GET请求访问登录页（防止刷新报错）
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }
}
package com.example.mvc1.controller;

import com.example.mvc1.model.Message;
import com.example.mvc1.model.User;
import com.example.mvc1.service.MessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class PublishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 处理中文乱码

        // 1. 验证登录状态
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. 获取表单参数
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        // 3. 简单验证
        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            req.setAttribute("error", "标题和内容不能为空！");
            req.getRequestDispatcher("/jsp/publish.jsp").forward(req, resp);
            return;
        }

        // 4. 调用Service层发布新讨论（主讨论：replyId为null）
        MessageService messageService = new MessageService();
        Message newMessage = new Message(
                null, // ID由Service自动生成
                title.trim(),
                new Date(), // 当前时间
                content.trim(),
                null, // 主讨论无回复ID
                loginUser
        );
        messageService.addMessage(newMessage); // 新增方法，后续实现

        // 5. 发布成功，跳转回讨论列表
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    // 支持GET请求访问发布页
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 验证登录状态（未登录跳转登录页）
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // 已登录，转发到发布页
        req.getRequestDispatcher("/jsp/publish.jsp").forward(req, resp);
    }
}

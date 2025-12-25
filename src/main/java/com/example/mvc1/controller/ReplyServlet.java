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

public class ReplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 1. 验证登录状态
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. 获取表单参数
        String mainMessageId = req.getParameter("mainMessageId"); // 主讨论ID
        String replyContent = req.getParameter("replyContent");

        // 3. 验证参数
        if (mainMessageId == null || mainMessageId.trim().isEmpty()
                || replyContent == null || replyContent.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/detail?id=" + mainMessageId);
            return;
        }

        // 4. 调用Service层添加回复（replyId为主讨论ID，无标题）
        MessageService messageService = new MessageService();
        Message reply = new Message(
                null, // ID由Service生成
                null, // 回复无标题
                new Date(), // 当前时间
                replyContent.trim(),
                mainMessageId, // 关联主讨论ID
                loginUser
        );
        messageService.addMessage(reply); // 复用之前的addMessage方法

        // 5. 回复成功，刷新详情页
        resp.sendRedirect(req.getContextPath() + "/detail?id=" + mainMessageId);
    }
}
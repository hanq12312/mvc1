package com.example.mvc1.controller;

import com.example.mvc1.model.Message;
import com.example.mvc1.service.MessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取讨论ID
        String messageId = req.getParameter("id");
        if (messageId == null || messageId.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/list");
            return;
        }

        // 获取讨论详情和回复
        MessageService messageService = new MessageService();
        Message mainMessage = messageService.getMessageById(messageId);
        List<Message> replies = messageService.getRepliesByMainId(messageId);

        // 传递数据到JSP
        req.setAttribute("mainMessage", mainMessage);
        req.setAttribute("replies", replies);
        req.getRequestDispatcher("/jsp/detail.jsp").forward(req, resp);
    }
}

package com.example.mvc1.controller;

import com.example.mvc1.model.Message;
import com.example.mvc1.service.MessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取所有主讨论
        MessageService messageService = new MessageService();
        List<Message> mainMessages = messageService.getAllMainMessages();

        // 传递数据到JSP
        req.setAttribute("mainMessages", mainMessages);
        req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
    }
}
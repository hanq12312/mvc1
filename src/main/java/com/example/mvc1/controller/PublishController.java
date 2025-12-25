package com.example.mvc1.controller;

import com.example.mvc1.model.Message;
import com.example.mvc1.model.User;
import com.example.mvc1.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/publish")
    public String publishPage() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(HttpServletRequest req, HttpSession session) throws Exception {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }

        Message msg = new Message();
        msg.setTitle(title);
        msg.setContent(content);
        msg.setDatetime(new Date());
        msg.setSender(user);

        messageService.addMessage(msg);
        return "redirect:/list";
    }
}

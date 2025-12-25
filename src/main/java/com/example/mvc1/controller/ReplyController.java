package com.example.mvc1.controller;

import com.example.mvc1.model.Message;
import com.example.mvc1.model.User;
import com.example.mvc1.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class ReplyController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/reply")
    public String doReply(HttpServletRequest req, HttpSession session) throws Exception {
        req.setCharacterEncoding("UTF-8");
        String mainId = req.getParameter("mainId");
        String content = req.getParameter("content");

        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }

        Message reply = new Message();
        reply.setReplyId(mainId);
        reply.setContent(content);
        reply.setDatetime(new Date());
        reply.setSender(user);

        messageService.addMessage(reply);
        return "redirect:/detail?id=" + mainId;
    }
}

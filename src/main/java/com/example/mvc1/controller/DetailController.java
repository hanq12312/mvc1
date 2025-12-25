package com.example.mvc1.controller;

import com.example.mvc1.model.Message;
import com.example.mvc1.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/detail")
    public String detail(@RequestParam("id") String id, Model model, HttpServletRequest req) {
        Message main = messageService.getMessageById(id);
        List<Message> replies = messageService.getRepliesByMainId(id);
        model.addAttribute("main", main);
        model.addAttribute("replies", replies);
        return "detail";
    }
}

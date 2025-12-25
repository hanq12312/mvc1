package com.example.mvc1.controller;

import com.example.mvc1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("mainMessages", messageService.getAllMainMessages());
        return "list"; // maps to /jsp/list.jsp
    }
}

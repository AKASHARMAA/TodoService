package com.skarvo.todosService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class WebController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "index.html";
    }

    @GetMapping("/create")
    public String create() {
        return "create.html";
    }
}

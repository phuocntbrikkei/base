package com.example.ss9.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test-thymeleaf")
public class TestThymeleaf {

    @GetMapping("/test")
    public String testThymeleaf() {
        // model.addAttribute("message", "Thymeleaf is working!");
        return "java";
    }

    @GetMapping("")
    @ResponseBody
    public String test() {
        return "a";
    }
}

package com.grigoBiteUI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/homepage")
    public String showHomePage() {
        return "home";
    }
    @GetMapping("/homepage/login")
    public String showHomePageAfterLogin() {
        return "home-after-login";
    }
}
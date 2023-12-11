package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.RequestLogin;
import com.grigoBiteUI.dto.RequestRegister;
import com.grigoBiteUI.dto.ResponseLogin;
import com.grigoBiteUI.dto.ResponseRegister;
import com.grigoBiteUI.dto.ResponseUser;
import com.grigoBiteUI.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @GetMapping(path = "/register")
    public String registerPage(Model model) {
        return "register";
    }

    @GetMapping(path = "/login")
    public String loginPage(Model model) {
        return "login";
    }

    @Autowired
    private AuthService authenticationService;

    @PostMapping("/register")
    public String register(Model model, RequestRegister request) {
        authenticationService.register(request);
        return "redirect:/auth/login";
    }

    @PostMapping("/login")
    public String login(Model model, RequestLogin request) {
        authenticationService.login(request);
        return "redirect:/home";
    }

    @GetMapping("/user")
    public String user(Model model, HttpServletRequest request) {
        ResponseUser responseUser = authenticationService.getUser(request);
        model.addAttribute("user", responseUser);
        return "user";
    }
}

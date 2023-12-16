package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.RequestLogin;
import com.grigoBiteUI.dto.RequestRegister;
import com.grigoBiteUI.dto.ResponseLogin;
import com.grigoBiteUI.dto.ResponseUser;
import com.grigoBiteUI.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @GetMapping(path = "/register-login")
    public String loginRegisterPage(Model model) {
        return "login-register";
    }

    @Autowired
    private AuthService authenticationService;

    @PostMapping("/register")
    public String register(Model model, RequestRegister request) {
        authenticationService.register(request);
        return "redirect:/auth/register-login";
    }


    @PostMapping("/login")
    public String login(Model model, RequestLogin request, HttpServletResponse response) {
        ResponseLogin responseLogin = authenticationService.login(request);

        System.out.println("kena dsiini");

        String token = responseLogin.getToken();
        Cookie jwtCookie = new Cookie("jwt", token);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        return "redirect:/homepage";
    }

    @GetMapping("/user")
    public String user(Model model, HttpServletRequest request) {
        ResponseUser responseUser = authenticationService.getUser(request);
        model.addAttribute("user", responseUser);
        return "user";
    }
}

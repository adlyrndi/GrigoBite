package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.*;
import com.grigoBiteUI.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseRegister> register (
            @RequestBody RequestRegister request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login (
            @RequestBody RequestLogin request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseUser> user (
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(authenticationService.getUser(request));
    }

    private static void getCurrentUser() {
        System.out.println(SecurityContextHolder.getContext()
                .getAuthentication());
    }
}

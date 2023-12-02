package com.grigoBiteUI.auth.controller;

import auth.auth.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import com.grigoBiteUI.auth.dto.*;
import com.grigoBiteUI.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}

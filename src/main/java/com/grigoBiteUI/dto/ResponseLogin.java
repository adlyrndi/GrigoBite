package com.grigoBiteUI.dto;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLogin {
    private String token;
    private ResponseUser user;

    private String cookieName = "jwtcookie";
    private Cookie cookie;
}

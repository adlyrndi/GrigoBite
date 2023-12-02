package com.grigoBiteUI.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegister {
    private String username;
    private String nickname;
    private String password;
    private String role;
    private String phoneNumber;
    @Nullable
    private String profilePicture;
}
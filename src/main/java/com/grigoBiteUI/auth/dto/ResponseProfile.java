package com.grigoBiteUI.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProfile {
    private String username;
    private String nickname;
    private String phoneNumber;
    private String profilePicture;
}



package com.grigoBiteUI.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestEdit {
    private String nickname;
    private String password;
    private String phoneNumber;
    private String profilePicture;

}



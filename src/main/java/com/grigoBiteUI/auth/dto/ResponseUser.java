package com.grigoBiteUI.auth.dto;

import com.grigoBiteUI.auth.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    public ResponseUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
        this.profilePicture = user.getProfilePicture();
    }

    private Integer id;
    private String username;
    private String role;
    private String nickname;
    private String phoneNumber;
    private String profilePicture;

    public static List<ResponseUser> fromEntities(List<User> users) {
        return users.stream()
                .map(ResponseUser::new)
                .toList();
    }
}

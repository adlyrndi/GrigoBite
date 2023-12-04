package com.grigoBiteUI.service;

import com.grigoBiteUI.dto.RequestEdit;
import com.grigoBiteUI.dto.ResponseProfile;
import com.grigoBiteUI.dto.ResponseUser;
import com.grigoBiteUI.model.auth.User;
import com.grigoBiteUI.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final AuthService authenticationService;
    private final JwtService jwtService;

    public ResponseProfile getProfileByUserId(HttpServletRequest request, Integer userId) {
        String token = request.getHeader("Authorization").substring(7);
        jwtService.validateToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return convertToProfileResponse(new ResponseUser(user));
    }

    public ResponseProfile editProfile(HttpServletRequest request, RequestEdit editProfileRequest) {
        ResponseUser userResponse = authenticationService.getUser(request);
        User user = userRepository.findByUsername(userResponse.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        updateUserProfile(user, editProfileRequest);

        return convertToProfileResponse(new ResponseUser(user));
    }


    private void updateUserProfile(User user, RequestEdit editProfileRequest) {
        user.setPassword(editProfileRequest.getPassword());
        user.setNickname(editProfileRequest.getNickname());
        user.setPhoneNumber(editProfileRequest.getPhoneNumber());
        user.setProfilePicture(editProfileRequest.getProfilePicture());

        userRepository.save(user);
    }

    private ResponseProfile convertToProfileResponse(ResponseUser user) {
        return ResponseProfile.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .profilePicture(user.getProfilePicture())
                .build();
    }
}


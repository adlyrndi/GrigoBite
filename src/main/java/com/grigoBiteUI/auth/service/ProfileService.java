package com.grigoBiteUI.auth.service;

import com.grigoBiteUI.auth.dto.RequestEdit;
import com.grigoBiteUI.auth.dto.ResponseProfile;
import com.grigoBiteUI.auth.dto.ResponseUser;
import com.grigoBiteUI.auth.model.User;
import com.grigoBiteUI.auth.repository.userRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final userRepo userRepository;
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


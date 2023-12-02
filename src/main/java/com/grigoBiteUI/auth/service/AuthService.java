package com.grigoBiteUI.auth.service;

import auth.auth.dto.*;
import com.grigoBiteUI.auth.repository.userRepo;
import com.grigoBiteUI.auth.dto.*;
import com.grigoBiteUI.auth.exceptions.UsernameAlreadyExistsException;
import com.grigoBiteUI.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final userRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public ResponseRegister register(RequestRegister request) {
        checkUserDoesNotExist(request.getUsername());

        User user = buildUserFromRegisterRequest(request);
        userRepository.save(user);

        return ResponseRegister.builder()
                .isRegistered(true)
                .user(new ResponseUser(user))
                .build();
    }

    public ResponseLogin login(RequestLogin request) {
        authenticateUser(request.getUsername(), request.getPassword());

        User user = getUserByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);

        return ResponseLogin.builder()
                .token(jwtToken)
                .user(new ResponseUser(user))
                .build();
    }

    public ResponseUser getUser(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String username = extractUsernameFromToken(token);
        User user = getUserByUsername(username);

        return new ResponseUser(user);
    }

    private void checkUserDoesNotExist(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException();
        }
    }


    private User buildUserFromRegisterRequest(RequestRegister request) {
        return User.builder()
                .username(request.getUsername())
                .active(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phoneNumber(request.getPhoneNumber())
                .profilePicture(request.getProfilePicture())
                .build();
    }

    private void authenticateUser(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private String extractUsernameFromToken(String token) {
        return jwtService.extractUsername(token);
    }
}


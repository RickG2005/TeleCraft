package com.github.rickg2005.telecraft.controller;

import com.github.rickg2005.telecraft.domain.User;
import com.github.rickg2005.telecraft.dto.LoginResponse;
import com.github.rickg2005.telecraft.dto.UserLoginRequest;
import com.github.rickg2005.telecraft.dto.UserRegisterRequest;
import com.github.rickg2005.telecraft.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.rickg2005.telecraft.service.UserService;
import util.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest request){
        User savedUser = userService.registerUser(request.getEmail(), request.getPassword(), request.getRole());

        UserResponse response = UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest request) {
        User loggedInUser = userService.loginUser(request.getEmail(), request.getPassword());

        String token = JwtUtils.generateToken(loggedInUser.getEmail(), loggedInUser.getRole().name());

        LoginResponse response = LoginResponse.builder()
                .token(token)
                .id(String.valueOf(loggedInUser.getId()))
                .email(loggedInUser.getEmail())
                .role(loggedInUser.getRole())
                .createdAt(loggedInUser.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }
}

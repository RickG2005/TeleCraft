package com.github.rickg2005.telecraft.controller;

import com.github.rickg2005.telecraft.domain.User;
import com.github.rickg2005.telecraft.dto.UserLoginRequest;
import com.github.rickg2005.telecraft.dto.UserRegisterRequest;
import com.github.rickg2005.telecraft.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.rickg2005.telecraft.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest request){
        User savedUser = userService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest request) {
        User loggedInUser = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok(loggedInUser);
    }
}

package com.github.rickg2005.telecraft.dto;

import com.github.rickg2005.telecraft.domain.UserRole;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String id;
    private String email;
    private UserRole role;
    private OffsetDateTime createdAt;
}

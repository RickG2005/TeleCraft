package com.github.rickg2005.telecraft.dto;

import com.github.rickg2005.telecraft.domain.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
    private String email;
    private String password;
    private UserRole role;
}

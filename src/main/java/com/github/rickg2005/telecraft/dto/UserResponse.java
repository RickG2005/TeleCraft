package com.github.rickg2005.telecraft.dto;

import com.github.rickg2005.telecraft.domain.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class UserResponse {
    private UUID id;
    private String email;
    private UserRole role;
    private OffsetDateTime createdAt;
}

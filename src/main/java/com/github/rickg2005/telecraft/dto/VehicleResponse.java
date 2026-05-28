package com.github.rickg2005.telecraft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private String id;
    private String name;
    private String typeCode;
    private String typeDescription;
    private String status;
    private OffsetDateTime createdAt;
}

package com.github.rickg2005.telecraft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleEnrollRequest {
    String name;
    String type;
}

package com.github.rickg2005.telecraft.controller;

import com.github.rickg2005.telecraft.domain.Vehicle;
import com.github.rickg2005.telecraft.dto.UserRegisterRequest;
import com.github.rickg2005.telecraft.dto.UserResponse;
import com.github.rickg2005.telecraft.dto.VehicleEnrollRequest;
import com.github.rickg2005.telecraft.dto.VehicleResponse;
import com.github.rickg2005.telecraft.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/enrollment")
    public ResponseEntity<VehicleResponse> register(@RequestBody VehicleEnrollRequest request){
        Vehicle savedVehicle = vehicleService.enrollVehicle(request.getName(), request.getType());

        VehicleResponse response = VehicleResponse.builder()
                .id(String.valueOf(savedVehicle.getId()))
                .name(savedVehicle.getName())
                .typeCode(savedVehicle.getType().getCode())
                .typeDescription(savedVehicle.getType().getDescription())
                .status(String.valueOf(savedVehicle.getStatus()))
                .createdAt(savedVehicle.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

}

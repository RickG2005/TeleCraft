package com.github.rickg2005.telecraft.controller;

import com.github.rickg2005.telecraft.domain.Vehicle;
import com.github.rickg2005.telecraft.domain.VehicleStatus;
import com.github.rickg2005.telecraft.dto.VehicleEnrollRequest;
import com.github.rickg2005.telecraft.dto.VehicleResponse;
import com.github.rickg2005.telecraft.dto.VehicleStatusChangeRequest;
import com.github.rickg2005.telecraft.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        VehicleResponse response = mapToResponse(savedVehicle);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> getAllVehicles(){
        List<Vehicle> Vehicles = vehicleService.getAllVehicles();

        List<VehicleResponse> responseList = new ArrayList<>();
        for (Vehicle vehicle : Vehicles){
            VehicleResponse dto = mapToResponse(vehicle);
            responseList.add(dto);
        }

        return ResponseEntity.ok(responseList);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<VehicleResponse> updateStatus(@PathVariable UUID id,
                                                        @RequestBody VehicleStatusChangeRequest request){
        Vehicle updatedVehicle = vehicleService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(mapToResponse(updatedVehicle));
    }

    //Map Vehicle object to Vehicle Response DTO
    private VehicleResponse mapToResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId().toString())
                .name(vehicle.getName())
                .typeCode(vehicle.getType().getCode())
                .typeDescription(vehicle.getType().getDescription())
                .status(vehicle.getStatus().name())
                .createdAt(vehicle.getCreatedAt())
                .build();
    }

}

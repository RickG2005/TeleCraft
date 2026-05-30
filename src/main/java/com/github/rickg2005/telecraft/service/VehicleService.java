package com.github.rickg2005.telecraft.service;

import com.github.rickg2005.telecraft.domain.Vehicle;
import com.github.rickg2005.telecraft.domain.VehicleStatus;
import com.github.rickg2005.telecraft.domain.VehicleType;
import com.github.rickg2005.telecraft.repository.VehicleRepository;
import com.github.rickg2005.telecraft.repository.VehicleTypeRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleService(VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    public Vehicle enrollVehicle (String name, String type){
        if (vehicleRepository.findByName(name).isPresent()){
            throw new RuntimeException("Vehicle name already exists.");
        }
        Optional<VehicleType> vehicleTypeOpt = vehicleTypeRepository.findByCode(type.toUpperCase());
        if (vehicleTypeOpt.isEmpty() || !vehicleTypeOpt.get().isActive()){
            throw new RuntimeException("Valid type required: DRONE, TANK, SHIP.");
        }

        VehicleType verifiedType = vehicleTypeOpt.get();

        Vehicle enrolledVehicle = Vehicle.builder()
                .id(UUID.randomUUID())
                .name(name)
                .type(verifiedType)
                .status(VehicleStatus.ACTIVE)
                .createdAt(OffsetDateTime.now())
                .build();

        return vehicleRepository.save(enrolledVehicle);
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle updateStatus(UUID id, String status){

        Vehicle changedVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Vehicle ID"));

        VehicleStatus targetStatus;
        try{
            targetStatus = VehicleStatus.valueOf(String.valueOf(status).toUpperCase());
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid status transition. Allowed values: ACTIVE, MAINTENANCE, OFFLINE");
        }

        changedVehicle.setStatus(targetStatus);
        return vehicleRepository.save(changedVehicle);
    }
}

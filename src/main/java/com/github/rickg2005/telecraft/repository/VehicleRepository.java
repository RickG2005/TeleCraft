package com.github.rickg2005.telecraft.repository;

import com.github.rickg2005.telecraft.domain.Vehicle;
import com.github.rickg2005.telecraft.domain.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, UUID> {
    Optional<Vehicle> findByName (String name);
}

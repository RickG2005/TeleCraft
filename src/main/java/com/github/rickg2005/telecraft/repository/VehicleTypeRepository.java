package com.github.rickg2005.telecraft.repository;

import com.github.rickg2005.telecraft.domain.VehicleType;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, UUID> {
    Optional<VehicleType> findByCode(String code);
}

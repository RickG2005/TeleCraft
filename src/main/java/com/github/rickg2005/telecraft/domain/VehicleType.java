package com.github.rickg2005.telecraft.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "vehicle_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleType {
    @Id
    @Column(name = "type_id", nullable = false, updatable = false)
    UUID type_id;
    @Column(name = "code", nullable = false, unique = true)
    String code;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean active = true;
}

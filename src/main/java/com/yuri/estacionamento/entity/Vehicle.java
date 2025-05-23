package com.yuri.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicle", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"license_plate", "plate_format"})
})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_name", nullable = false, length = 60)
    private String ownerName;

    @Column(name = "license_plate", nullable = false, length = 7)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(name = "plate_format", nullable = false, length = 10)
    private PlateFormat plateFormat;

    public enum PlateFormat {
        OLD, MERCOSUL
    }
}
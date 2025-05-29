package com.yuri.estacionamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @NotBlank(message = "A placa do veículo é obrigatória.")
    @Size(max = 7, message = "A placa deve conter no máximo 7 caracteres.")
    private String licensePlate;


    @Enumerated(EnumType.STRING)
    @Column(name = "plate_format", nullable = false, length = 10)
    private PlateFormat plateFormat;

    public enum PlateFormat {
        OLD, MERCOSUL
    }

    @Column(name = "entry_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryTime;
}
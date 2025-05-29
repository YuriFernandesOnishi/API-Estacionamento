package com.yuri.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "parking_spot")
public class ParkingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "spot_number", nullable = false, unique = true, length = 10)
    private String spotNumber;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // Verificação para que o campo alinhamento de data seja incluído!
}


package com.yuri.estacionamento.repository;

import com.yuri.estacionamento.entity.ParkingLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingLocationRepository extends JpaRepository<ParkingLocation, Long> {

    // Buscar vaga pelo nome da vaga (e.g., "A01")
    Optional<ParkingLocation> findBySpotNumber(String spotNumber);
}
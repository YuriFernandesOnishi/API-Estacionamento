package com.yuri.estacionamento.repository;

import com.yuri.estacionamento.entity.Vehicle;
import com.yuri.estacionamento.entity.Vehicle.PlateFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByLicensePlateAndPlateFormat(String licensePlate, PlateFormat plateFormat);
}
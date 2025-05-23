package com.yuri.estacionamento.controller;

import com.yuri.estacionamento.dto.ParkingEntryDto;
import com.yuri.estacionamento.entity.ParkingLocation;
import com.yuri.estacionamento.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping
    public ResponseEntity<?> registerParking(@RequestBody ParkingEntryDto parkingEntryDto) {
        try {
            String response = parkingService.registerParking(parkingEntryDto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // Endpoint para buscar vaga pelo nome ou ID
    @GetMapping("/{spotNumber}")
    public ResponseEntity<?> getParkingLocation(@PathVariable String spotNumber) {
        try {
            ParkingLocation location = parkingService.getParkingLocation(spotNumber);
            return ResponseEntity.ok(location);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
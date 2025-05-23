package com.yuri.estacionamento.service;

import com.yuri.estacionamento.dto.ParkingEntryDto;
import com.yuri.estacionamento.entity.Vehicle;
import com.yuri.estacionamento.entity.ParkingRecord;
import com.yuri.estacionamento.entity.ParkingLocation;
import com.yuri.estacionamento.entity.User;
import com.yuri.estacionamento.repository.VehicleRepository;
import com.yuri.estacionamento.repository.ParkingRecordRepository;
import com.yuri.estacionamento.repository.ParkingLocationRepository;
import com.yuri.estacionamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLocationRepository locationRepository;

    @Autowired
    private ParkingRecordRepository parkingRepository;

    public String registerParking(ParkingEntryDto parkingEntryDto) {

        // Localizar usuário responsável
        User user = userRepository.findById(parkingEntryDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID informado!"));

        // Localizar ou criar veículo
        Vehicle vehicle = vehicleRepository.findByLicensePlateAndPlateFormat(
                parkingEntryDto.getLicensePlate(),
                parkingEntryDto.getPlateFormat()
        ).orElseGet(() -> {
            Vehicle newVehicle = new Vehicle();
            newVehicle.setOwnerName(parkingEntryDto.getOwnerName());
            newVehicle.setLicensePlate(parkingEntryDto.getLicensePlate());
            newVehicle.setPlateFormat(parkingEntryDto.getPlateFormat());
            return vehicleRepository.save(newVehicle);
        });

        // Localizar vaga pelo nome ou pelo ID
        ParkingLocation location;
        try {
            Long spotId = Long.valueOf(parkingEntryDto.getSpotNumber());
            location = locationRepository.findById(spotId)
                    .orElseThrow(() -> new IllegalArgumentException("Vaga de estacionamento não encontrada com o ID informado!"));
        } catch (NumberFormatException e) {
            location = locationRepository.findBySpotNumber(parkingEntryDto.getSpotNumber())
                    .orElseThrow(() -> new IllegalArgumentException("Vaga de estacionamento não encontrada com o nome informado!"));
        }

        // Validar disponibilidade da vaga
        if (!location.isAvailable()) {
            throw new IllegalArgumentException("A vaga de estacionamento já está ocupada!");
        }

        // Criar novo registro de estacionamento
        ParkingRecord parkingRecord = new ParkingRecord();
        parkingRecord.setVehicle(vehicle);
        parkingRecord.setEntryTime(parkingEntryDto.getEntryTime());
        parkingRecord.setHandler(user);
        parkingRecord.setLocation(location);

        // Atualizar a disponibilidade da vaga
        location.setAvailable(false);
        locationRepository.save(location);

        // Salvar o registro do estacionamento
        parkingRepository.save(parkingRecord);

        return "Registro de estacionamento realizado com sucesso!";
    }

    public ParkingLocation getParkingLocation(String spotNumber) {
        try {
            Long spotId = Long.valueOf(spotNumber);
            return locationRepository.findById(spotId)
                    .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada pelo ID informado!"));
        } catch (NumberFormatException e) {
            return locationRepository.findBySpotNumber(spotNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada pelo nome informado!"));
        }
    }
}
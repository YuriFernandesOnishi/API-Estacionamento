package com.yuri.estacionamento.dto;

import com.yuri.estacionamento.entity.Vehicle.PlateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ParkingEntryDto {

    @NotBlank(message = "O nome do proprietário é obrigatório.")
    private String ownerName;

    @NotBlank(message = "A placa do veículo é obrigatória.")
    private String licensePlate;

    @NotNull(message = "O formato da placa é obrigatório.")
    private PlateFormat plateFormat; // Padrão ENUM para diferenciar formatos de placa

    @NotBlank(message = "O número da vaga deve ser informado.")
    private String spotNumber;

    @NotNull(message = "O horário de entrada é obrigatório.")
    private Date entryTime;

    @NotNull(message = "O ID do usuário responsável é obrigatório.")
    private Integer userId;
}
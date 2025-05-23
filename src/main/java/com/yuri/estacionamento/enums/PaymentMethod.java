package com.yuri.estacionamento.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CASH("Dinheiro"),
    CARD("Cartão"),
    PIX("PIX"),
    CREDIT("Crédito");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

}
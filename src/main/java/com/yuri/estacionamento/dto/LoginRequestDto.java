package com.yuri.estacionamento.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
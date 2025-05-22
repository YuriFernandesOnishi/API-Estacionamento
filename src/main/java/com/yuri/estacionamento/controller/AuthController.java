package com.yuri.estacionamento.controller;

import com.yuri.estacionamento.dto.LoginRequestDto;
import com.yuri.estacionamento.dto.RegisterRequestDto;
import com.yuri.estacionamento.dto.AuthResponseDto;
import com.yuri.estacionamento.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint para registrar um novo usuário
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }

    /**
     * Endpoint para autenticar um usuário
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response); // Retorna o token JWT na resposta
    }

    @GetMapping("/api/protected")
    public ResponseEntity<String> protectedEndpoint() {
        return ResponseEntity.ok("Bem-vindo, você está autenticado!");
    }
}
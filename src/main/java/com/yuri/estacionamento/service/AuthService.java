package com.yuri.estacionamento.service;

import com.yuri.estacionamento.dto.*;
import com.yuri.estacionamento.entity.User;
import com.yuri.estacionamento.repository.UserRepository;
import com.yuri.estacionamento.security.JwtTokenUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtility jwtUtil;

    public void register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já registrado!");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    public AuthResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        if (!user.isActive()) {
            throw new IllegalArgumentException("Usuário inativo, contato o administrador!");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciais inválidas!");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
}
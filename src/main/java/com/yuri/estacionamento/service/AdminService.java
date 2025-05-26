package com.yuri.estacionamento.service;

import com.yuri.estacionamento.entity.SystemSettings;
import com.yuri.estacionamento.entity.User;
import com.yuri.estacionamento.repository.SystemSettingsRepository;
import com.yuri.estacionamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SystemSettingsRepository systemSettingsRepository;

    public void updateSystemSettings(Integer userId, SystemSettings updatedSettings) {
        // Verifica se o usuário é administrador
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        if (!"ADMIN".equalsIgnoreCase(user.getRole().getName())) {
            throw new SecurityException("Acesso negado! Somente administradores podem alterar as configurações.");
        }

        // Obtém as configurações existentes (ID sempre será 1 conforme tabela)
        SystemSettings currentSettings = systemSettingsRepository.findById(1)
                .orElseThrow(() -> new IllegalStateException("Configurações do sistema não encontradas."));

        // Atualiza os valores permitidos
        currentSettings.setMaxSpots(updatedSettings.getMaxSpots());
        currentSettings.setFirstHourPrice(updatedSettings.getFirstHourPrice());
        currentSettings.setAdditionalHourPrice(updatedSettings.getAdditionalHourPrice());

        // Salva as novas configurações
        systemSettingsRepository.save(currentSettings);
    }
}
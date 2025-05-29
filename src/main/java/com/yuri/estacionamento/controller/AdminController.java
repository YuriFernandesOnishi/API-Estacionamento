package com.yuri.estacionamento.controller;

import com.yuri.estacionamento.entity.SystemSettings;
import com.yuri.estacionamento.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/settings")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Permitir apenas ADMIN acessar este endpoint
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateSettings(@RequestBody SystemSettings updatedSettings) {
        adminService.updateSystemSettings(updatedSettings);
        return ResponseEntity.ok("Configurações atualizadas com sucesso!");
    }
}
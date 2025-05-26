package com.yuri.estacionamento.controller;

import com.yuri.estacionamento.entity.SystemSettings;
import com.yuri.estacionamento.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/settings")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping
    public ResponseEntity<String> updateSettings(@RequestParam Integer userId, @RequestBody SystemSettings updatedSettings) {
        try {
            adminService.updateSystemSettings(userId, updatedSettings);
            return ResponseEntity.ok("Configurações atualizadas com sucesso!");
        } catch (IllegalArgumentException | SecurityException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro ao atualizar as configurações.");
        }
    }
}
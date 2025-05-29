package com.yuri.estacionamento.repository;

import com.yuri.estacionamento.entity.SystemSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemSettingsRepository extends JpaRepository<SystemSettings, Integer> {

}
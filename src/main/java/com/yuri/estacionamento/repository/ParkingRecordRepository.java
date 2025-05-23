package com.yuri.estacionamento.repository;

import com.yuri.estacionamento.entity.ParkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

}
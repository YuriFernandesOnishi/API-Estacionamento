package com.yuri.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.yuri.estacionamento.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parking")
public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle; // Relacionamento com a tabela de veículos

    @Column(name = "entry_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryTime;

    @Column(name = "exit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exitTime;

    @Column(name = "amount_paid", precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @ManyToOne(optional = false)
    @JoinColumn(name = "spot_id", nullable = false)
    private ParkingLocation location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User handler;

    @Column(name = "owner_name", nullable = false, length = 60)
    private String ownerName;

    @Column(name = "license_plate", nullable = false, length = 7)
    private String licensePlate;

}
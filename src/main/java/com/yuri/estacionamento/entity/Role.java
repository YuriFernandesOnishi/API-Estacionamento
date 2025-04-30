package com.yuri.estacionamento.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "privilege") // Ajuste do nome da tabela
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(name = "privilege_level", nullable = false, unique = true)
    private int privilegeLevel;
}
package com.yuri.estacionamento.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60) // Nome do usuário
    private String name;

    @Column(name = "password_hash", nullable = false) // Alinha com o nome do campo na tabela
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privilege_id", nullable = false) // Relacionando à tabela privilege
    private Role role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
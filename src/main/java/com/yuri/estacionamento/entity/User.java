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

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privilege_id", nullable = false) // Relacionando à tabela privilege
    private Role role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

}
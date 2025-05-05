package com.yuri.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yuri.estacionamento.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNameOrEmail(String name, String email);

    Boolean existsByEmail(String email);
}

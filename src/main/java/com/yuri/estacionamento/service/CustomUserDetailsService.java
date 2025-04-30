package com.yuri.estacionamento.service;

import com.yuri.estacionamento.entity.User;
import com.yuri.estacionamento.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException {
        // Busca o usuário pelo nome ou email usando findByNameOrEmail
        User user = userRepository.findByNameOrEmail(nameOrEmail, nameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with name or email: " + nameOrEmail));

        // Obtém as permissões/roles associadas ao usuário
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(user.getRole().getName()));

        // Retorna a implementação do Spring Security UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),        // Nome de usuário para autenticação é o email
                user.getPassword(),     // Senha do usuário
                user.isActive(),        // Checa se a conta do usuário está ativa
                true,                   // Account non-expired
                true,                   // Credentials non-expired
                true,                   // Account non-locked
                authorities             // Permissões do usuário
        );
    }
}
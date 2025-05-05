package com.yuri.estacionamento.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtility {

    // Substitua por uma chave segura e com pelo menos 256 bits
    private static final String SECRET_KEY = "sua_chave_secreta_segura_com_256_bits_________";

    // Método para obter a chave com base no SECRET_KEY
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Decodifica a chave
        return Keys.hmacShaKeyFor(keyBytes); // Gera a chave HMAC com base no tamanho necessário
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Define o "dono" do token como o email solicitado
                .setIssuedAt(new Date()) // Data de emissão do token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expira em 10h
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Gera o token com a chave e alg.
                .compact(); // Compila o Token
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder() // Novo parser compatível com JWT moderno
                .setSigningKey(getSigningKey()) // Define a chave de assinatura
                .build() // Constrói a instância do parser
                .parseClaimsJws(token) // Faz o parsing do Token JWT
                .getBody()
                .getSubject(); // Extrai o "subject" (neste caso, o email)
    }

    public boolean validateToken(String token, String email) {
        String extractedEmail = extractEmail(token); // Extrai o email do token
        return (extractedEmail.equals(email) && !isTokenExpired(token)); // Compara email e validade
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date()); // Verifica se a data de expiração já passou
    }
}
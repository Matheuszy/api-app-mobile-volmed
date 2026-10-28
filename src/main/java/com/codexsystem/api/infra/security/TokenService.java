package com.codexsystem.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.codexsystem.api.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Usuario usuario ) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("volmed")
                    .withClaim("id", usuario.getId().toString())
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpired())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error while generating token", e);
        }

    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("volmed")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant dataExpired() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("+03:00"));

    }

}

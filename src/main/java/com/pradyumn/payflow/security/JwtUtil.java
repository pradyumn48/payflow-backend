package com.pradyumn.payflow.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mySecretKeymySecretKeymySecretKey12345mySecretKey";

    private static final long EXPIRATION =
            86400000;

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes());

    public String generateToken(
            Long userId,
            String email,
            String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String extractEmail(
            String token) {

        return getClaims(token)
                .getSubject();
    }

    public boolean validateToken(
            String token) {

        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(
            String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
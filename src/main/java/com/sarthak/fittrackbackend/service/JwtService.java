package com.sarthak.fittrackbackend.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes());
    }

    public String extractUsername(String token) {

    return extractAllClaims(token)
            .getSubject();
}

public boolean isTokenValid(String token) {

    try {

        extractAllClaims(token);
        return true;

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}

private Claims extractAllClaims(String token) {

    return Jwts.parser()
            .verifyWith((javax.crypto.SecretKey) getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
}
    
}

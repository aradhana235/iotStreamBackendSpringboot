package com.alphacore.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    private final String SECRET =
            "cjv4Zi7WdK2H4hBfCiqQQvzrFOOccS70Rv1f0mjo96o=";

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // =========================
    // LOGIN TOKEN
    // =========================
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(getSigningKey())
                .compact();
    }

    // =========================
    // RESET PASSWORD TOKEN
    // =========================
    public String generateResetToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 900000)
                ) // 15 min
                .signWith(getSigningKey())
                .compact();
    }

    // =========================
    // EXTRACT EMAIL
    // =========================
    public String extractEmail(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
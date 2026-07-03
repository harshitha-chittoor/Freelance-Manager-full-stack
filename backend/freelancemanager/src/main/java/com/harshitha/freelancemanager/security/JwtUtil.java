package com.harshitha.freelancemanager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // Must be at least 32 characters (256 bits) for HS256
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(
                    "mySuperSecretJwtKeyForFreelanceManager2026SecureKey"
                            .getBytes(StandardCharsets.UTF_8)
            );

    // Token validity: 24 hours
    private static final long EXPIRATION_TIME =
            1000L * 60 * 60 * 24;

    // ==========================
    // Generate JWT
    // ==========================
    public String generateToken(Long userId) {

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(SECRET_KEY)
                .compact();
    }

    // ==========================
    // Extract User ID
    // ==========================
    public static Long extractUserId(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    // ==========================
    // Validate Token
    // ==========================
    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}
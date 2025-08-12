package com.romina.taskapi.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;

@Service
public class JwtService {

    private static final long EXPIRATION_TIME = 86400000;

    private final byte[] signingKey;

    public JwtService(@Value("${jwt.secret}") String rawKey) {
        this.signingKey = generateSigningKey(rawKey);
    }

    private byte[] generateSigningKey(String rawKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(rawKey.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error generando clave segura", e);
        }
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token.trim())
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, String email) {
        try {
            return extractEmail(token).equals(email) && !tokenIsExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean tokenIsExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token.trim())
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}



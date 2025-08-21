package org.example.digitalWalletSystem.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.example.digitalWalletSystem.user.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        byte[] encoded = Base64.getEncoder().encode(secret.getBytes());
        return Keys.hmacShaKeyFor(encoded);
    }

    public String generateToken(UserEntity user, long expirationMs) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId",user.getId())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }


    public Long extractUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }


    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }


    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

package com.example.subscriptions.infrastructure.config;

import com.example.subscriptions.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final long TOKEN_VALIDITY_HOURS = 1;

    @Value("${jwt.secret:mySecretKeyForJWTTokenGenerationThatIsAtLeast256BitsLong}")
    private String secret;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("login", user.getLogin());
        claims.put("name", user.getName());
        claims.put("surname", user.getSurname());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusHours(TOKEN_VALIDITY_HOURS);

        return Jwts.builder()
                .claims(claims)
                .subject(user.getLogin())
                .issuedAt(toDate(now))
                .expiration(toDate(expiry))
                .signWith(getSigningKey())
                .compact();
    }

    public LocalDateTime getExpirationTime() {
        return LocalDateTime.now().plusHours(TOKEN_VALIDITY_HOURS);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractLogin(String token) {
        return extractClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

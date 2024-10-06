package com.port.tracking.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final SecretKey secretKey = generateSecretKey();
    private final long validityInMilliseconds = 3600000; // 1 hour
    private static final String secretKeyBase64 = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b"; // Make sure this key is long enough and secure


    private static SecretKey generateSecretKey() {
        //String secretKeyBase64 = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b"; // Make sure this key is long enough and secure
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyBase64);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(secretKey) // Use consistent signing key
                .compact();
    }

    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username) && !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        try {
            return extractAllClaims(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey) // Ensure consistent signing key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

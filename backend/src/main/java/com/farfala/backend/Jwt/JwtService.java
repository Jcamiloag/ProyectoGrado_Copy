package com.farfala.backend.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.farfala.backend.User.User;
import com.farfala.backend.User.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final UserRepository userRepository;

    public String getToken(UserDetails user) {
        User currentUser = userRepository.findByEmail(user.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + user.getUsername()));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", currentUser.getRole().name());
        extraClaims.put("id", currentUser.getId());
        extraClaims.put("username", currentUser.getUsername()); 

        return generateToken(extraClaims, user);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername()) 
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) 
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Integer getUserIdFromToken(String token) {
        Claims claims = getAllClaims(token);
        Object idClaim = claims.get("id");
        
        if (idClaim instanceof Integer) {
            return (Integer) idClaim;
        } else if (idClaim instanceof Number) {
            return ((Number) idClaim).intValue();
        } else {
            throw new RuntimeException("ID no encontrado o en formato incorrecto en el token");
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}









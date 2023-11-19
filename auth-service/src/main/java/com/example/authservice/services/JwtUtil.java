package com.example.authservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtil {


    public final String secret = "24104d6c319b492a6365d08de24104d6c319b492a6365d08de";
    public  final String expiration = "86400";

    private Key key;

    @PostConstruct
    public void k() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }
    public Date getExpirationDate(String token){
        return getClaims(token).getExpiration();
    }
    public String generate(String userId,String role,String tokenType){
        Map<String,String> claims = Map.of("id",userId);
        long expMillis = "ACCESS".equalsIgnoreCase(tokenType)
                ? Long.parseLong(expiration)*1000
                : Long.parseLong(expiration)*1000*5;
        final Date now = new Date();
        final Date exp = new Date(now.getTime()+expMillis);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }
    private boolean isExpired(String token){
        return getExpirationDate(token).before(new Date());
    }
}

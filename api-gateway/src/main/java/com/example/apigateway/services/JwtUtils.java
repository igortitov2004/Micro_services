package com.example.apigateway.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtils {
    private final String secret="24104d6c319b492a6365d08de24104d6c319b492a6365d08de";
    private Key key;

//    public JwtUtils(){
//        this.key = Keys.hmacShaKeyFor(secret.getBytes());
//    }

    @PostConstruct
    private void l(){
        this.key=Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
    public boolean isExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }
}

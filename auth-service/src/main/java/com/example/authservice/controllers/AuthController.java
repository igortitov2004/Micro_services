package com.example.authservice.controllers;

import com.example.authservice.entities.AuthRequest;
import com.example.authservice.entities.AuthResponse;
import com.example.authservice.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}

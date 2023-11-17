package com.example.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-controller")
public class Demo {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }
}

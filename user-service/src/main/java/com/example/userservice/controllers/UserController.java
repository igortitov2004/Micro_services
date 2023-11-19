package com.example.userservice.controllers;

import com.example.userservice.entities.UserVO;
import com.example.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserVO> save(@RequestBody UserVO userVO){
        return ResponseEntity.ok(userService.save(userVO));
    }

    @GetMapping("/secured")
    private ResponseEntity<String> secured(){
        return ResponseEntity.ok("Hello");
    }
}

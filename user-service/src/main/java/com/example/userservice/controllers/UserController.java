package com.example.userservice.controllers;

import com.example.userservice.exeptions.UserAlreadyExistException;
import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/secured")
    private ResponseEntity<String> secured(){
        return ResponseEntity.ok("Hello");
    }
    @PostMapping("/registration")
    private ResponseEntity<String> registered(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("Registration success", HttpStatus.ACCEPTED);
    }
    @PostMapping("/checkAuth")
    private ResponseEntity<User> checkAuth(@RequestBody User user){
        return new ResponseEntity<>(userService.findUser(user),HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<String> handleAuth(UserAlreadyExistException e){
        return new ResponseEntity<>("User with this email already exists!",HttpStatus.FORBIDDEN);
    }
}

package com.example.userservice.services;

import com.example.userservice.models.User;

import java.util.Optional;

public interface UserService {
    void createUser(User user);

    User findUser(User user);
}

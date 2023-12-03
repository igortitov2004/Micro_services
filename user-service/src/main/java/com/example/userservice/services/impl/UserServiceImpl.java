package com.example.userservice.services.impl;

import com.example.userservice.exeptions.UserAlreadyExistException;
import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public void createUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail()).isEmpty()){
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userRepository.save(user);
        }else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public User findUser(User user){
        Optional<User> foundUser = userRepository.findUserByEmail(user.getEmail());
        if(foundUser.isPresent()){
            if(passwordEncoder.matches(user.getPassword(),foundUser.get().getPassword())){
                return foundUser.get();
            }
        }
        return null;
    }
}

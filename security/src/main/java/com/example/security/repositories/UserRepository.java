package com.example.security.repositories;

import com.example.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(String email);
}

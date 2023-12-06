package com.example.usersservice.Repositaries;

import com.example.usersservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User save(User user);
    User findById(long userId);
}

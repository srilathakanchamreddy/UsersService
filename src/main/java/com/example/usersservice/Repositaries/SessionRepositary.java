package com.example.usersservice.Repositaries;

import com.example.usersservice.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepositary extends JpaRepository<Session, Long> {
    Session findByUserIdAndToken(long userId, String token);
    Session save(Session session);
}

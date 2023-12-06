package com.example.usersservice.Services;

import com.example.usersservice.Models.Session;
import com.example.usersservice.Models.SessionStatus;
import com.example.usersservice.Models.User;
import com.example.usersservice.Repositaries.SessionRepositary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserService userService;
    private SessionRepositary sessionRepositary;
    AuthService(UserService userService, SessionRepositary sessionRepositary) {
        this.userService = userService;
        this.sessionRepositary = sessionRepositary;
    }
    public User signup(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userService.create(user);
    }

    public User login(String email, String password) {
        User user = userService.findByEmail(email);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        if(!user.getPassword().equals(password)) {
            throw new RuntimeException("Password is incorrect");
        }
        return user;
    }

    public void logout(long userId, String token) {
        Optional<Session> optionalSession = Optional.ofNullable(sessionRepositary.findByUserIdAndToken(userId, token));
        if(optionalSession.isEmpty()) {
            throw new RuntimeException("Session not found");
        }
        Session session = optionalSession.get();
        session.setStatus(SessionStatus.EXPIRED);
        sessionRepositary.save(session);




    }

    public SessionStatus validate() {
        return SessionStatus.ACTIVE;

    }
}

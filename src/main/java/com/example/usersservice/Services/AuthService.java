package com.example.usersservice.Services;

import com.example.usersservice.Models.Session;
import com.example.usersservice.Models.SessionStatus;
import com.example.usersservice.Models.User;
import com.example.usersservice.Repositaries.SessionRepositary;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.random.RandomGenerator;

@Service
public class AuthService {
    private final UserService userService;
    private final SessionRepositary sessionRepositary;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    MacAlgorithm alg = Jwts.SIG.HS256;
    SecretKey key = alg.key().build();
    AuthService(UserService userService, SessionRepositary sessionRepositary, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.sessionRepositary = sessionRepositary;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User signup(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userService.create(user);
    }

    public User login(String email, String password) {
        User user = userService.findByEmail(email);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        if(!BCrypt.checkpw(password, user.getPassword())) {
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

    public SessionStatus validate(long userId, String token) {


//        Optional<Session> optionalSession = Optional.ofNullable(sessionRepositary.findByUserIdAndToken(userId, token));
//        if(optionalSession.isEmpty()) {
//            throw new RuntimeException("Session not found");
//        }
//        Session session = optionalSession.get();



        Claims claims = Jwts.parser().verifyWith(key).build()
                        .parseSignedClaims(token)
                        .getPayload();



        return SessionStatus.ACTIVE;

    }

    public String generateToken(User user) {
        //String token = RandomStringUtils.randomAlphanumeric(32);
//        String message = "{\n" +
//        "   \"email\": \"naman@scaler.com\",\n" +
//        "   \"roles\": [\n" +
//        "      \"mentor\",\n" +
//        "      \"ta\"\n" +
//        "   ],\n" +
//        "   \"expirationDate\": \"23rdOctober2023\"\n" +
//        "}";
        // user_id
        // user_email
        // roles

//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("expirationDate", new Date());
        //if(xx =!null)
        jsonForJwt.put("createdAt" , new Date());



        String token = Jwts.builder().claims(jsonForJwt).signWith(key, alg).compact();
        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setStatus(SessionStatus.ACTIVE);
        sessionRepositary.save(session);
        return token;
    }
}

package com.example.usersservice.Controllers;

import com.example.usersservice.DTOs.LogoutDTO;
import com.example.usersservice.DTOs.SignUPRequestDTO;
import com.example.usersservice.DTOs.UserDTO;
import com.example.usersservice.DTOs.ValidateDTO;
import com.example.usersservice.Models.SessionStatus;
import com.example.usersservice.Models.User;
import com.example.usersservice.Services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    AuthController(AuthService authService) {
        this.authService = authService;

    }
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignUPRequestDTO request) {
        User user = authService.signup(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(UserDTO.from(user));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody SignUPRequestDTO request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        String token = authService.generateToken(user);
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);
        return new ResponseEntity<>(UserDTO.from(user), headers, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutDTO request) {
        authService.logout(request.getUserId(), request.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateDTO request) {
        SessionStatus sessionStatus = authService.validate(request.getUserId(), request.getToken());
        return ResponseEntity.ok(sessionStatus);
    }

}

package com.example.usersservice.Controllers;

import com.example.usersservice.DTOs.SetRollRequestDTO;
import com.example.usersservice.DTOs.UserDTO;
import com.example.usersservice.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable long userId) {
        return ResponseEntity.ok(UserDTO.from(userService.findById(userId)));
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<UserDTO> setRoles(@PathVariable long userId, @RequestBody SetRollRequestDTO request) {
        return ResponseEntity.ok(UserDTO.from(userService.setRoles(userId, request.getRoles())));
    }

}

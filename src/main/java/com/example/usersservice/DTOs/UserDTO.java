package com.example.usersservice.DTOs;

import com.example.usersservice.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class UserDTO {
    private String email;
    private List<String> roles;
    static public UserDTO from(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        if(user.getRoles() != null) {
            userDTO.setRoles(new ArrayList<>());
            user.getRoles().forEach(role -> {
                userDTO.getRoles().add(role.getName());
            });
        }

        return userDTO;
    }
}

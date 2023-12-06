package com.example.usersservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUPRequestDTO {
    private String email;
    private String password;
}

package com.example.usersservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutDTO {
    private long userId;
    private String token;
}



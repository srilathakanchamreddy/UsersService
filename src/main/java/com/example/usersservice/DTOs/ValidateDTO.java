package com.example.usersservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateDTO {
    private String token;
    private long userId;
}

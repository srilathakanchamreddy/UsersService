package com.example.usersservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SetRollRequestDTO {
    private List<Long> roles;
}

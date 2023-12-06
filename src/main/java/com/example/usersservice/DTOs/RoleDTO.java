package com.example.usersservice.DTOs;

import com.example.usersservice.Models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    private long id;
    private String name;
    public static RoleDTO from(com.example.usersservice.Models.Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public Role toRole() {
        Role role = new Role();
        role.setId(this.getId());
        role.setName(this.getName());
        return role;
    }
}

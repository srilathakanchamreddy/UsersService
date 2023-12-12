package com.example.usersservice.Services;

import com.example.usersservice.Models.Role;
import com.example.usersservice.Repositaries.RolesRepositary;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RolesRepositary rolesRepositary;
    RoleService(RolesRepositary rolesRepositary) {
        this.rolesRepositary = rolesRepositary;
    }
    public Role create(Role role) {
        return rolesRepositary.save(role);
    }

    public Role findById(long roleId) {
        return rolesRepositary.findById(roleId);
    }

}

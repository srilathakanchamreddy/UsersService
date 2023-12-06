package com.example.usersservice.Repositaries;

import com.example.usersservice.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepositary extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Role save(Role role);
    Role findById(long roleId);
}

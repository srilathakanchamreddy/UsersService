package com.example.usersservice.Controllers;

import com.example.usersservice.DTOs.RoleDTO;
import com.example.usersservice.Services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("/")
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO) {
        roleService.create(roleDTO.toRole());
        return ResponseEntity.ok(roleDTO);
    }
}

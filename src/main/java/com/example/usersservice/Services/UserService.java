package com.example.usersservice.Services;

import com.example.usersservice.Models.User;
import com.example.usersservice.Repositaries.RolesRepositary;
import com.example.usersservice.Repositaries.UserRepositary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepositary userRepositary;
    private RoleService roleService;

    UserService(UserRepositary userRepositary) {
        this.userRepositary = userRepositary;
    }
    public User create(User user) {
        return userRepositary.save(user);
    }

    public User findByEmail(String email) {
        return userRepositary.findByEmail(email);
    }

    public User setRoles(long userId ,List<Long> roles) {
        User user = userRepositary.findById(userId);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        if(user.getRoles()==null){
            user.setRoles(new ArrayList<>());
        }
        roles.forEach(roleId -> {
            user.getRoles().add(roleService.findById(roleId));
        });
        return userRepositary.save(user);

    }

    public User findById(long userId) {
        return userRepositary.findById(userId);
    }
}

package com.example.usersservice.Security;

import com.example.usersservice.Models.User;
import com.example.usersservice.Repositaries.UserRepositary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepositary userRepositary;
    CustomUserDetailService(UserRepositary userRepositary) {
        this.userRepositary = userRepositary;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepositary.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}

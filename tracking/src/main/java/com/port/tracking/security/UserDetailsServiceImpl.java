package com.port.tracking.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.port.tracking.model.User;
import com.port.tracking.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}

package com.betanet.lynxsupport.service;

import com.betanet.lynxsupport.domain.User;
import com.betanet.lynxsupport.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userService.getByLogin(login);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }
}

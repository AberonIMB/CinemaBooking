package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.exception.user.UserNotFoundException;
import com.cinema.cinemabooking.model.User;
import com.cinema.cinemabooking.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByEmail(email);

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
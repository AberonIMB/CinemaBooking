package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.user.RegisterDTO;
import com.cinema.cinemabooking.exception.user.UserAlreadyExistsException;
import com.cinema.cinemabooking.exception.user.UserNotFoundException;
import com.cinema.cinemabooking.model.User;
import com.cinema.cinemabooking.model.enums.UserRole;
import com.cinema.cinemabooking.repository.UserRepository;
import com.cinema.cinemabooking.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException(email);
        }

        return user;
    }

    @Override
    @Transactional
    public void saveUser(RegisterDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail().trim())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(userDTO.getEmail().trim(),
                userDTO.getFullName().trim(),
                passwordEncoder.encode(userDTO.getPassword()),
                UserRole.USER);

        userRepository.save(user);
    }
}
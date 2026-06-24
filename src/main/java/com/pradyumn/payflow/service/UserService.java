package com.pradyumn.payflow.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pradyumn.payflow.dto.CreateUserRequest;
import com.pradyumn.payflow.entity.User;
import com.pradyumn.payflow.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(
            CreateUserRequest userRequest) {

        User user = new User();

        user.setName(userRequest.getName());

        user.setEmail(userRequest.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        userRequest.getPassword()));

        user.setRole("USER");

        return userRepository.save(user);
    }
}
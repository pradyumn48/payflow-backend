package com.pradyumn.payflow.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradyumn.payflow.entity.User;
import com.pradyumn.payflow.repository.UserRepository;

@RestController
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
package com.pradyumn.payflow.service;

import com.pradyumn.payflow.dto.CreateUserRequest;
import com.pradyumn.payflow.entity.User;
import com.pradyumn.payflow.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest userRequest) {

        User user = new User();

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return userRepository.save(user);
    }
}
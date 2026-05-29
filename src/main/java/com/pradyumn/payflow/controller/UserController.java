package com.pradyumn.payflow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradyumn.payflow.dto.CreateUserRequest;
import com.pradyumn.payflow.entity.User;
import com.pradyumn.payflow.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }
    
    
}

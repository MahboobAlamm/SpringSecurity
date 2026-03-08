package com.alam.SpringSecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alam.SpringSecurity.bean.User;
import com.alam.SpringSecurity.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User postMethodName(@RequestBody User user) {
        return userService.register(user);
    }
    

}

package com.alam.SpringSecurity.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alam.SpringSecurity.bean.User;
import com.alam.SpringSecurity.security.JwtService;
import com.alam.SpringSecurity.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManger;
    private final JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManger = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/login") 
    public String loginUser(@RequestBody User user) {
        Authentication auth = authenticationManger.authenticate(
                                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if(auth.isAuthenticated()) {
            String token =  jwtService.generateToken(user.getUsername());
            return token;
        }
        else {
             return "Wrong crdentials";
        }

    }
    
}

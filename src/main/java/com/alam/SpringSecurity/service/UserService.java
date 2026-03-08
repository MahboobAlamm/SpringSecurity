package com.alam.SpringSecurity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alam.SpringSecurity.bean.User;
import com.alam.SpringSecurity.repository.UserRepo;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

}

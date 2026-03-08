package com.alam.SpringSecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alam.SpringSecurity.bean.User;
import com.alam.SpringSecurity.bean.UserPrincipal;
import com.alam.SpringSecurity.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

   private final UserRepo userRepo;
   public MyUserDetailsService(UserRepo userRepo) {
    this.userRepo = userRepo;
   }

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Username not found.");
        }

        return new UserPrincipal(user);
   }

}

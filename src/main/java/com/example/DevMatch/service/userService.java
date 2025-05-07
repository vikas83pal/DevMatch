package com.example.DevMatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DevMatch.model.User;
import com.example.DevMatch.respository.userRepo;

@Service
public class userService {
    
    @Autowired
    private userRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User register(User user){
        String hasedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hasedPassword);
        System.out.println(hasedPassword);
        return userRepo.save(user);
    }

    public User login(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email cannot be null or empty");
        }
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }
}

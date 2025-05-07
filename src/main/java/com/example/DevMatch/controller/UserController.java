package com.example.DevMatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.DevMatch.model.User;
import com.example.DevMatch.service.userService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private userService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User u = this.userService.register(user);
        return ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Use email and password for login
            User loggedInUser = userService.login(user.getEmail(), user.getPassword());

            // Create a sanitized response (exclude sensitive fields like password)
            User sanitizedUser = new User();
            sanitizedUser.setId(loggedInUser.getId());
            sanitizedUser.setName(loggedInUser.getName());
            sanitizedUser.setEmail(loggedInUser.getEmail());

            return ResponseEntity.ok(sanitizedUser);
        } catch (RuntimeException e) {
            // Return an error response if login fails
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}

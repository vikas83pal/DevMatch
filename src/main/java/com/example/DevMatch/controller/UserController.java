package com.example.DevMatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.DevMatch.model.User; 
import com.example.DevMatch.service.userService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private userService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        User u = this.userService.register(user);
        return ResponseEntity.ok(u);
    }
}

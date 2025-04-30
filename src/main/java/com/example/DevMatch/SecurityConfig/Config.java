package com.example.DevMatch.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register").permitAll()  // Allow access to /register
                .anyRequest().authenticated()  // Require authentication for other endpoints
            )
            .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity (not recommended for production)
            // .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/"));  // Enable OAuth2 login with a default success URL

        return http.build();
    }
}
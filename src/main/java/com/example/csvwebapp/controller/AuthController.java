package com.example.csvwebapp.controller;

import com.example.csvwebapp.model.User;
import com.example.csvwebapp.repository.UserRepository;
import com.example.csvwebapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = authService.register(user);
        if (user != null) {
            model.addAttribute("success", "Registration successful! Please log in.");
            return "login";
        } else {
            model.addAttribute("error", "Registration failed. Username may already be taken.");
            return "register";
        }
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = authService.login(username, password).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/login", "/css/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll()
        );
    return http.build();
}
@Autowired
private UserRepository userRepository;

@GetMapping("/test-db")
@ResponseBody
public String testDb() {
    try {
        long count = userRepository.count();
        return "Database connected! User count: " + count;
    } catch (Exception e) {
        return "Database connection failed: " + e.getMessage();
    }
}
}
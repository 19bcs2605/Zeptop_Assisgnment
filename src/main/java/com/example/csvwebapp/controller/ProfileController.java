package com.example.csvwebapp.controller;

import com.example.csvwebapp.model.User;
import com.example.csvwebapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        User user = profileService.getCurrentUser();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String username, @RequestParam String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        profileService.updateUserProfile(user);
        return "redirect:/profile";
    }
}
package com.example.csvwebapp.service;

import com.example.csvwebapp.model.User;
import com.example.csvwebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserProfile(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateUserProfile(User user) {
        return userRepository.save(user);
    }

    /**
     * @return
     */
    public User getCurrentUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentUser'");
    }
}
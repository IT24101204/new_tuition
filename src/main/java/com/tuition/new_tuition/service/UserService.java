package com.tuition.new_tuition.service;

import com.tuition.new_tuition.entity.AppUser;
import com.tuition.new_tuition.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public AppUser register(AppUser user) {

        // check duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists. Please use another email.");
        }

        // NOTE: for now plain password (later hash)
        return userRepository.save(user);
    }
}

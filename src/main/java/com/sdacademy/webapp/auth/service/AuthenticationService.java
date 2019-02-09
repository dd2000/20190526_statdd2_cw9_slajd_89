package com.sdacademy.webapp.auth.service;

import com.sdacademy.webapp.auth.components.PasswordEncoder;
import com.sdacademy.webapp.auth.model.User;
import com.sdacademy.webapp.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private List<String> loggedInUsers = new ArrayList<>();

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean signInUser(final String email, final String password) {
        final Optional<User> user = userRepository.findByEmail(email);
        return user
                .map(user1 -> user1.getPassword().equals(passwordEncoder.encode(password)))
                .orElse(false);
    }

}

package com.sdacademy.webapp.auth.controller;

import com.sdacademy.webapp.auth.model.Role;
import com.sdacademy.webapp.auth.repository.UserRepository;
import com.sdacademy.webapp.auth.service.AuthenticationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public AuthenticationController(final AuthenticationService authenticationService, final UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    // NOT HOW IT SHOULD BE DONE! GET MAPPINGS FOR EASY TESTING IN WEB BROWSER!

    @GetMapping("/sign-in/{email}/{password}")
    public String signIn(@PathVariable final String email, @PathVariable final String password) {
        if (authenticationService.signInUser(email, password)) {
            return "User signed in";
        }
        return "User authentication failed";
    }

    @GetMapping("/delete/{name}")
    public String deleteUser(@PathVariable final String name) {
        userRepository.deleteUser(name);
        return "User " + name + " deleted";
    }

    @GetMapping("/add-role/{name}/{role}")
    public String addRole(@PathVariable final String name, @PathVariable final String role) {
        userRepository.addRole(name, Role.valueOf(role));
        return "Role " + role + " added for user " + name;
    }

    @GetMapping("/remove-role/{name}/{role}")
    public String removeRole(@PathVariable final String name, @PathVariable final String role) {
        userRepository.removeRole(name, Role.valueOf(role));
        return "Role " + role + " taken away fror user " + name;
    }

}

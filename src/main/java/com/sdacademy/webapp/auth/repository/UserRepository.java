package com.sdacademy.webapp.auth.repository;

import com.sdacademy.webapp.auth.components.EmailValidator;
import com.sdacademy.webapp.auth.components.PasswordEncoder;
import com.sdacademy.webapp.auth.exception.SdaException;
import com.sdacademy.webapp.auth.model.Role;
import com.sdacademy.webapp.auth.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserRepository {

    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;

    private List<User> users = new ArrayList<>();

    public UserRepository(final PasswordEncoder passwordEncoder, final EmailValidator emailValidator) {
        this.passwordEncoder = passwordEncoder;
        this.emailValidator = emailValidator;
        users.add(User.builder()
                .email("admin@sdacademy.pl")
                .name("admin")
                .password(passwordEncoder.encode("sdacademyAdmin"))
                .build());
    }

    public void createUser(final String name, final String email, final String plainTextPassword) {
        final boolean emailAlreadyRegistered = users.stream()
                .anyMatch(user -> user.getEmail().equals(email));

        if (emailAlreadyRegistered || !emailValidator.isEmailValid(email)) {
            throw new SdaException("Email already registered or invalid");
        }

        users.add(User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(plainTextPassword))
                .build());
        log.info("User " + name + " added.");
    }

    public void deleteUser(final String name) {
        users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .map(user -> users.remove(user))
                .orElseThrow(() -> new SdaException("Cannot delete user because it does not exist"));
    }

    public void addRole(final String name, final Role role) {
        final Optional<User> user = users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();

        user.ifPresent(user1 -> user1.getRoles().add(role));
    }

    public void removeRole(final String name, final Role role) {
        final Optional<User> user = users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();

        user.ifPresent(user1 -> user1.getRoles().remove(role));
    }

    public Optional<User> findByEmail(final String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }
}

package com.sdacademy.webapp.auth.components;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

    private static final int EMAIL_MIN_LENGTH = 5;

    public boolean isEmailValid(final String email) {
        return email.contains("@")
                && email.length() >= EMAIL_MIN_LENGTH
                && !email.startsWith("@")
                && !email.endsWith("@");
    }
}

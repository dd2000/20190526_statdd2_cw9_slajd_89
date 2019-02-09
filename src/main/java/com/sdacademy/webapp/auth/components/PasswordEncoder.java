package com.sdacademy.webapp.auth.components;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;

@Component
public class PasswordEncoder {

    public String encode(final String passwordToEncode) {
        return Base64.getEncoder().encodeToString(passwordToEncode.getBytes());
    }

    public String decode(final String passwordToDecode) {
        return Arrays.toString(Base64.getDecoder().decode(passwordToDecode));
    }

}

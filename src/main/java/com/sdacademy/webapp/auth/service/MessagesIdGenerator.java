package com.sdacademy.webapp.auth.service;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessagesIdGenerator {

    public Long generate() {
        return new Date().getTime();
    }
}

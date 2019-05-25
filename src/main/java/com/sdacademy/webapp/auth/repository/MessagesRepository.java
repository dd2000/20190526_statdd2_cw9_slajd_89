package com.sdacademy.webapp.auth.repository;


import com.sdacademy.webapp.auth.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Message, Long> {
}

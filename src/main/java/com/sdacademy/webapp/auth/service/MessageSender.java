package com.sdacademy.webapp.auth.service;

import com.sdacademy.webapp.auth.model.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MessageSender {

    private List<Message> sentMessages = new ArrayList<>();

    public void sendMessages(final Collection<Message> messages) {
        for (final Message message: messages) {
            if (sentMessages.contains(message)) {
                System.out.println("Message with id " + message.getId() + " was already sent");
            } else {
                System.out.println("Sending message with id " + message.getId());
                sentMessages.add(message);
            }
        }
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(final List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }
}

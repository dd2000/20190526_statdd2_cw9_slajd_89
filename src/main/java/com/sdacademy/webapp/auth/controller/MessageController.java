package com.sdacademy.webapp.auth.controller;

import com.sdacademy.webapp.auth.service.MessagesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    private final MessagesService messagesService;

    public MessageController(final MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    // again not the way to do it!

    @GetMapping("/messages/{topic}/{messages}")
    public String addMessages(@PathVariable final String topic, @PathVariable final String messages) {
        final String[] messagesValues = messages.split("_");
        messagesService.saveAndSend(topic, messagesValues);
        return "Sent " + messagesValues.length + " messages";
    }

    @GetMapping("/messages/remove/{topic}")
    public void removeTopic(@PathVariable final String topic) {
        messagesService.removeMessagesWithTopic(topic);
    }

    @GetMapping("/messages/{topic}")
    public List<String> getMessagesFromSession(@PathVariable String topic) {
        return messagesService.getSentMessagesFromTopicInThisSession(topic);
    }
}

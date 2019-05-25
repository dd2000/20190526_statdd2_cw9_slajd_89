package com.sdacademy.webapp.auth.service;

import com.sdacademy.webapp.auth.model.Message;
import com.sdacademy.webapp.auth.repository.MessagesRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagesService {

    private final MessageSender messageSender;
    private final MessagesRepository messagesRepository;
    private final MessagesIdGenerator messagesIdGenerator;

    public MessagesService(final MessageSender messageSender, final MessagesRepository messagesRepository, final MessagesIdGenerator messagesIdGenerator) {
        this.messageSender = messageSender;
        this.messagesRepository = messagesRepository;
        this.messagesIdGenerator = messagesIdGenerator;
    }

    @Transactional
    public void saveAndSend(final String topic, final String... values) {

        final List<Message> messages = new ArrayList<>();

        for (final String value : values) {
            final Message message = new Message(messagesIdGenerator.generate(), topic, value);
            messagesRepository.save(message);
            messages.add(message);
        }

        messageSender.sendMessages(messages);
    }

    @Transactional
    public void removeMessagesWithTopic(final String topic) {
        messageSender.setSentMessages(messageSender.getSentMessages().stream().
                filter(message -> !message.getTopic().equals(topic))
                .collect(Collectors.toList()));
    }

    public List<String> getSentMessagesFromTopicInThisSession(final String topic) {
        return messageSender.getSentMessages().stream()
                .filter(message -> message.getTopic().equals(topic))
                .map(Message::getValue).collect(Collectors.toList());
    }
}

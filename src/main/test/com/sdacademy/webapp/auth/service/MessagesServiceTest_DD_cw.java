package com.sdacademy.webapp.auth.service;

import com.sdacademy.webapp.auth.model.Message;
import com.sdacademy.webapp.auth.repository.MessagesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessagesServiceTest_DD_cw {

    @Mock
    private MessageSender messageSender;

    @Mock
    private MessagesRepository messagesRepository;

    @Mock
    private MessagesIdGenerator messagesIdGenerator;

    @InjectMocks
    private MessagesService messagesService;

    @Captor
    private ArgumentCaptor<Collection<Message>> captor;

    @Test
    public void shouldSaveAndSend(){   // w JUnit4 musi być PUBLIC
        final String topic = "topic1";
        final String messageValue = "wiadomość1";
        final long timestamp = 123L;

        when(messagesIdGenerator.generate()).thenReturn(timestamp);

        messagesService.saveAndSend(topic,messageValue);

        verify(messagesRepository)
                .save(new Message(timestamp, topic, messageValue));
        verify(messageSender).sendMessages(captor.capture());
        assertTrue(captor.getValue()
        .contains(new Message(timestamp, topic, messageValue)));

    }

}
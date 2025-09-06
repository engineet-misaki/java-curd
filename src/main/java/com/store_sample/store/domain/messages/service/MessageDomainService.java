package com.store_sample.store.domain.messages.service;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.messages.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageDomainService {
    private final MessageRepository messageRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddhhmm");


    public Message post (Message message) {
        var uuid = UUID.randomUUID();
        var now = LocalDateTime.now();
        message.setId(now.format(FORMATTER) + "-" + uuid.toString());
        message.setTimestamp(now);

        messageRepository.insert(message);

        return message;
    }
}

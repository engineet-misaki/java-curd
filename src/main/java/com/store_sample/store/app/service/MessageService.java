package com.store_sample.store.app.service;

import com.store_sample.store.domain.messages.model.Message;
import com.store_sample.store.domain.messages.service.MessageDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageDomainService messageDomainService;

    public Message post (Message message) {

        var username = "guest";
        message.setUsername(username);

        messageDomainService.post(message);
        return message;
    }
}

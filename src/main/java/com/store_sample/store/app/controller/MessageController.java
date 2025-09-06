package com.store_sample.store.app.controller;

import com.store_sample.store.app.service.MessageService;
import com.store_sample.store.domain.messages.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channels/{channelId}/message")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public Message post(@PathVariable int channelId, @RequestBody Message message) {
        message.setChannelId(channelId);

        return messageService.post(message);
    }
}

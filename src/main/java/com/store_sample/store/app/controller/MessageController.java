package com.store_sample.store.app.controller;

import com.store_sample.store.app.service.MessageService;
import com.store_sample.store.domain.messages.model.Message;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channels/{channelId}/message")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @PostMapping
  public Message post(@PathVariable int channelId, @RequestBody Message message) {
    message.setChannelId(channelId);

    return messageService.post(message);
  }

  @GetMapping
  public List<Message> find(
      @PathVariable int channelId,
      @RequestParam("searchWord") Optional<String> searchWord
  ) {

    return messageService.find(channelId, searchWord);
  }
}

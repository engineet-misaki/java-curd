package com.store_sample.store.app.controller.message;

import com.store_sample.store.domain.messages.model.Message;
import com.store_sample.store.infrastructure.jpa.messages.JpaMessageRepository;
import com.store_sample.store.infrastructure.jpa.messages.TblMessages;
import com.store_sample.store.service.message.MessageService;
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
  private final JpaMessageRepository repository;

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


  //  TODO あとで消す
  @GetMapping("/all")
  public List<TblMessages> findAll(
      @PathVariable int channelId
  ) {

    return repository.findAll();
  }
}

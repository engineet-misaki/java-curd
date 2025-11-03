package com.store_sample.store.app.controller.channel;

import com.store_sample.store.app.service.channel.ChannelService;
import com.store_sample.store.domain.channels.model.Channel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/channels")
public class ChannelController {

  private final ChannelService channelService;

  @GetMapping
  public List<Channel> findAll() {
    return channelService.findAll();
  }

  @PostMapping
  public Channel create(@RequestBody Channel channel) {
    return channelService.create(channel);
  }

  @PutMapping("/{id}")
  public Channel update(@PathVariable("id") int id, @RequestBody Channel channel) {
    channel.setId(id);

    return channelService.update(channel);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") int id) {

    channelService.delete(id);
  }
}

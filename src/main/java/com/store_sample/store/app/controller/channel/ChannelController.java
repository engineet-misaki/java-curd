package com.store_sample.store.app.controller.channel;

import com.store_sample.store.app.controller.channel.dto.create.CreateChannelReq;
import com.store_sample.store.app.controller.channel.dto.create.CreateChannelRes;
import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.infrastructure.channels.TblChannels;
import com.store_sample.store.service.channel.ChannelService;
import com.store_sample.store.service.channel.command.CreateChannelCommand;
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
  public List<FindAllChannelModel> findAll() {
    return channelService.findAll();
  }

  @PostMapping
  public CreateChannelRes create(@RequestBody CreateChannelReq request) {
    CreateChannelCommand command = new CreateChannelCommand();
    command.setName(request.getName());
    TblChannels result = channelService.create(command);

    CreateChannelRes res = new CreateChannelRes();
    res.setId(result.getId());
    res.setName(result.getName());

    return res;
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

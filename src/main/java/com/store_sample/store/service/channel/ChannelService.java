package com.store_sample.store.service.channel;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.channels.model.CreateChannelModel;
import com.store_sample.store.domain.channels.service.ChannelDomainService;
import com.store_sample.store.infrastructure.channels.TblChannels;
import com.store_sample.store.service.channel.command.CreateChannelCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChannelService {

  private final ChannelDomainService channelDomainService;

  public TblChannels create(CreateChannelCommand command) {
    CreateChannelModel model = new CreateChannelModel();
    model.setName(command.getName());
    return channelDomainService.create(model);
  }

  public Channel update(Channel channel) {
    return channelDomainService.update(channel);
  }

  public void delete(int id) {
    channelDomainService.delete(id);
  }

  public List<Channel> findAll() {
    return channelDomainService.findAll();
  }
}

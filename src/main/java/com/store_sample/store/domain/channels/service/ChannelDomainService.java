package com.store_sample.store.domain.channels.service;

import com.store_sample.store.domain.channels.model.Channel;
import com.store_sample.store.domain.channels.model.CreateChannelModel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.infrastructure.channels.TblChannels;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelDomainService {

  private final ChannelRepository channelRepository;


  public TblChannels create(CreateChannelModel model) {
    TblChannels entity = new TblChannels();
    entity.setName(model.getName());

    channelRepository.insert(entity);

    return entity;
  }

  public Channel update(Channel channel) {
    channelRepository.update(channel);

    return channel;
  }

  public void delete(int id) {
    channelRepository.delete(id);
  }

  public List<FindAllChannelModel> findAll() {
    return channelRepository.findAll();
  }

}

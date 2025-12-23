package com.store_sample.store.domain.channels.service;

import com.store_sample.store.domain.channels.model.CreateChannelModel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.domain.channels.model.UpdateChannelModel;
import com.store_sample.store.infrastructure.channels.JpaChannelRepository;
import com.store_sample.store.infrastructure.channels.TblChannels;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelDomainService {

  private final ChannelRepository channelRepository;
  private final JpaChannelRepository JpaChannelRepository;


  public TblChannels create(CreateChannelModel model) {
    TblChannels entity = new TblChannels();
    entity.setName(model.getName());

    channelRepository.insert(entity);

    return entity;
  }

  public TblChannels update(UpdateChannelModel model) {
    TblChannels entity = new TblChannels();
    entity.setId(model.getId());
    entity.setName(model.getName());
    channelRepository.update(entity);

    return entity;
  }

  public void delete(int id) {
    channelRepository.delete(id);
  }

  public List<FindAllChannelModel> findAll() {
    return channelRepository.findAll();
  }

  public List<TblChannels> findById(int id) {
    return JpaChannelRepository.findById(id);
  }
}

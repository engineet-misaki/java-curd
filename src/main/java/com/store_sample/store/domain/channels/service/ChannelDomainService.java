package com.store_sample.store.domain.channels.service;

import com.store_sample.store.domain.channels.model.ChannelAddedUserModel;
import com.store_sample.store.domain.channels.model.CreateChannelModel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.domain.channels.model.UpdateChannelModel;
import com.store_sample.store.infrastructure.channel_members.JpaChannelMemberRepository;
import com.store_sample.store.infrastructure.channel_members.TblChannelMembers;
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
  private final JpaChannelMemberRepository JpaChannelMemberRepository;


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

  public void channelAddedUser(ChannelAddedUserModel model) {
    model.getUserIds().forEach(userId -> {
      TblChannelMembers entity = new TblChannelMembers();
      entity.setChannelId(model.getChannelId());
      entity.setUserId(userId);
      JpaChannelMemberRepository.save(entity);
    });
  }
}

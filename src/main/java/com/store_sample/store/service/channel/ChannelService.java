package com.store_sample.store.service.channel;

import com.store_sample.store.app.controller.exception.ResponseCode;
import com.store_sample.store.app.controller.exception.StopProcessingException;
import com.store_sample.store.domain.channel_members.model.ChannelMemberModel;
import com.store_sample.store.domain.channels.model.ChannelAddedUserModel;
import com.store_sample.store.domain.channels.model.ChannelDeletedUserModel;
import com.store_sample.store.domain.channels.model.CreateChannelModel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.domain.channels.model.FindIdChannelModel;
import com.store_sample.store.domain.channels.model.UpdateChannelModel;
import com.store_sample.store.domain.channels.service.ChannelDomainService;
import com.store_sample.store.infrastructure.channels.TblChannels;
import com.store_sample.store.service.channel.command.ChannelAddedUserCommand;
import com.store_sample.store.service.channel.command.ChannelDeletedUserCommand;
import com.store_sample.store.service.channel.command.CreateChannelCommand;
import com.store_sample.store.service.channel.command.UpdateChannelCommand;
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

  public TblChannels update(UpdateChannelCommand channel) {
    UpdateChannelModel model = new UpdateChannelModel();
    model.setId(channel.getId());
    model.setName(channel.getName());
    return channelDomainService.update(model);
  }

  public void delete(int id) {
    channelDomainService.delete(id);
  }

  public List<FindAllChannelModel> findAll() {
    return channelDomainService.findAll();
  }

  public FindIdChannelModel findById(int id) {
    TblChannels channel = channelDomainService.findById(id);
    FindIdChannelModel model = new FindIdChannelModel();

    if (channel == null) {
      throw new StopProcessingException(ResponseCode.CHANNEL_NOT_FOUND);
    }

    model.setId(channel.getId());
    model.setName(channel.getName());
    model.setChannelMembers(channel.getChannelMembers().stream()
        .map(m -> new ChannelMemberModel(
            m.getUser().getId(),
            m.getUser().getUsername()
        ))
        .toList());
    return model;
  }

  @Transactional
  public void channelAddedUser(ChannelAddedUserCommand command) {

    channelDomainService.checkDuplicateUser(command.getChannelId(), command.getUserIds());

    ChannelAddedUserModel model = new ChannelAddedUserModel();
    model.setChannelId(command.getChannelId());
    model.setUserIds(command.getUserIds());
    channelDomainService.channelAddedUser(model);
  }

  @Transactional
  public void channelDeletedUser(ChannelDeletedUserCommand command) {
    
    ChannelDeletedUserModel model = new ChannelDeletedUserModel();
    model.setChannelId(command.getChannelId());
    model.setUserIds(command.getUserIds());
    channelDomainService.channelDeletedUser(model);
  }
}

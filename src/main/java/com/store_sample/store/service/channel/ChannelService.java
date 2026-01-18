package com.store_sample.store.service.channel;

import com.store_sample.store.app.controller.channel.dto.get.GetChannelRes;
import com.store_sample.store.app.controller.channel.dto.get.MemberResponse;
import com.store_sample.store.domain.channels.model.ChannelAddedUserModel;
import com.store_sample.store.domain.channels.model.CreateChannelModel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.domain.channels.model.UpdateChannelModel;
import com.store_sample.store.domain.channels.service.ChannelDomainService;
import com.store_sample.store.infrastructure.channels.TblChannels;
import com.store_sample.store.service.channel.command.ChannelAddedUserCommand;
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

  public GetChannelRes findById(int id) {
    TblChannels channel = channelDomainService.findById(id);
    GetChannelRes res = new GetChannelRes();
    res.setId(channel.getId());
    res.setName(channel.getName());

    res.setMembers(channel.getChannelMembers().stream()
        .map(m -> new MemberResponse(
            m.getUser().getId(),
            m.getUser().getUsername()
        ))
        .toList());
    return res;
  }

  @Transactional
  public void channelAddedUser(ChannelAddedUserCommand command) {

    channelDomainService.checkDuplicateUser(command.getChannelId(), command.getUserIds());

    ChannelAddedUserModel model = new ChannelAddedUserModel();
    model.setChannelId(command.getChannelId());
    model.setUserIds(command.getUserIds());
    channelDomainService.channelAddedUser(model);
  }
}

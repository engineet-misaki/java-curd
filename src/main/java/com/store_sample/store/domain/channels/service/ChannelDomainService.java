package com.store_sample.store.domain.channels.service;

import com.store_sample.store.app.controller.exception.ResponseCode;
import com.store_sample.store.app.controller.exception.StopProcessingException;
import com.store_sample.store.domain.channels.model.ChannelAddedUserModel;
import com.store_sample.store.domain.channels.model.ChannelDeletedUserModel;
import com.store_sample.store.domain.channels.model.CreateChannelModel;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.domain.channels.model.UpdateChannelModel;
import com.store_sample.store.domain.users.service.UserDomainService;
import com.store_sample.store.infrastructure.channel_members.JpaChannelMemberRepository;
import com.store_sample.store.infrastructure.channel_members.TblChannelMembers;
import com.store_sample.store.infrastructure.channels.JpaChannelRepository;
import com.store_sample.store.infrastructure.channels.TblChannels;
import com.store_sample.store.infrastructure.users.TblUsers;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelDomainService {

  private final ChannelRepository channelRepository;
  private final JpaChannelRepository jpaChannelRepository;
  private final JpaChannelMemberRepository jpaChannelMemberRepository;
  private final UserDomainService userDomainService;


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

  public TblChannels findById(int id) {
    return jpaChannelRepository.findById(id).orElse(null);
  }

  public void channelAddedUser(ChannelAddedUserModel model) {
    TblChannels channel = findById(model.getChannelId());
    if (channel == null) {
      throw new StopProcessingException(ResponseCode.CHANNEL_NOT_FOUND);
    }
    model.getUserIds().forEach(userId -> {
      TblUsers user = userDomainService.findById(userId);
      if (user == null) {
        throw new StopProcessingException(ResponseCode.USER_NOT_FOUND);
      }
      user.joinChannel(channel);
    });
  }

  public void channelDeletedUser(ChannelDeletedUserModel model) {
    TblChannels channel = findById(model.getChannelId());
    if (channel == null) {
      throw new StopProcessingException(ResponseCode.CHANNEL_NOT_FOUND);
    }

    model.getUserIds().forEach(userId -> {
      TblUsers user = userDomainService.findById(userId);
      if (user == null) {
        throw new StopProcessingException(ResponseCode.USER_NOT_FOUND);
      }
      user.leaveChannel(channel);
    });
  }

  public void checkDuplicateUser(int channelId, List<Integer> addUserList) {
    TblChannels channel = findById(channelId);
    if (channel == null) {
      throw new StopProcessingException(ResponseCode.CHANNEL_NOT_FOUND);
    }
    List<TblChannelMembers> members = jpaChannelMemberRepository.findByChannelId(channelId);
    List<Integer> memberIdList = members.stream().map(TblChannelMembers::getUser)
        .map(TblUsers::getId).toList();
    addUserList.forEach(userId -> {
      if (memberIdList.contains(userId)) {
        throw new StopProcessingException(ResponseCode.DUPLICATE_CHANNEL_MEMBER,
            "チャンネル名:" + channel.getName(), "ユーザーId:" + userId);
      }
    });
  }
}

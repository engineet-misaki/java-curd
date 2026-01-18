package com.store_sample.store.domain.channels.service;

import com.store_sample.store.app.controller.exception.ResponseCode;
import com.store_sample.store.app.controller.exception.StopProcessingException;
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

  public TblChannels findById(int id) {
    return JpaChannelRepository.findById(id).orElse(null);
  }

  public void channelAddedUser(ChannelAddedUserModel model) {
    model.getUserIds().forEach(userId -> {
//      TblChannelMembers entity = new TblChannelMembers();
//      entity.setChannelId(model.getChannelId());
//      entity.setUserId(userId);
//      JpaChannelMemberRepository.save(entity);
    });
  }

  public void checkDuplicateUser(int channelId, List<Integer> userList) {
    TblChannels channel = JpaChannelRepository.findById(channelId).orElse(null);
    if (channel == null) {
      throw new StopProcessingException(ResponseCode.CHANNEL_NOT_FOUND);
    }
    List<TblChannelMembers> members = JpaChannelMemberRepository.findByChannelId(channelId);
    userList.forEach(userId -> {
//      if (!members.stream().map(TblChannelMembers::getUserId).toList().contains(userId)) {
//        // TODO ユーザ検索してユーザの存在チェックとユーザ名使用する
//        throw new StopProcessingException(ResponseCode.DUPLICATE_CHANNEL_MEMBER,
//            "チャンネル名:" + channel.getName(), "ユーザー名:" + userId);
//      }
    });
  }
}

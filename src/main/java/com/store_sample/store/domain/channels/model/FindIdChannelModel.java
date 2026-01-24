package com.store_sample.store.domain.channels.model;

import com.store_sample.store.domain.channel_members.model.ChannelMemberModel;
import com.store_sample.store.domain.messages.model.MessageReturnModel;
import java.util.List;
import lombok.Data;

@Data
public class FindIdChannelModel {

  private int id;
  private String name;
  private List<ChannelMemberModel> channelMembers;
  private List<MessageReturnModel> messages;
}

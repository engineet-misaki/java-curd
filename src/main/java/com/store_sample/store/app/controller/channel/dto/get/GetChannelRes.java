package com.store_sample.store.app.controller.channel.dto.get;

import com.store_sample.store.domain.channel_members.model.ChannelMemberModel;
import com.store_sample.store.domain.messages.model.MessageReturnModel;
import java.util.List;
import lombok.Data;

@Data
public class GetChannelRes {

  private int id;
  private String name;
  private List<ChannelMemberModel> members;
  private List<MessageReturnModel> messages;
}


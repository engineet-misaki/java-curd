package com.store_sample.store.domain.channels.model;

import com.store_sample.store.domain.channel_members.model.ChannelMemberModel;
import java.util.List;
import lombok.Data;

@Data
public class FindIdChannelModel {

  private int id;
  private String name;
  private List<ChannelMemberModel> channelMembers;
}

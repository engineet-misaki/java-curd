package com.store_sample.store.domain.channels.model;

import java.util.List;
import lombok.Data;

@Data
public class ChannelDeletedUserModel {

  private int channelId;
  private List<Integer> userIds;
}
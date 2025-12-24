package com.store_sample.store.domain.channels.model;

import java.util.List;
import lombok.Data;

@Data
public class ChannelAddedUserModel {

  private int channelId;
  private List<Integer> userIds;
}
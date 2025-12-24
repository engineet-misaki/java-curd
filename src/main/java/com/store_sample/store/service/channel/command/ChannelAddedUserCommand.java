package com.store_sample.store.service.channel.command;

import java.util.List;
import lombok.Data;

@Data
public class ChannelAddedUserCommand {

  private int channelId;

  private List<Integer> userIds;
}
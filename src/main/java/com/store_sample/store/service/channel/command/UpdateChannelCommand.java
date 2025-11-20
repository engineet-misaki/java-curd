package com.store_sample.store.service.channel.command;

import lombok.Data;

@Data
public class UpdateChannelCommand {

  private int id;
  private String name;
}
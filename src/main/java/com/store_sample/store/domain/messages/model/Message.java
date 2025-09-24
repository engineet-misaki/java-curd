package com.store_sample.store.domain.messages.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Message {

  private String id;

  private int channelId;

  private String text;

  private String username;

  private LocalDateTime timestamp;
}

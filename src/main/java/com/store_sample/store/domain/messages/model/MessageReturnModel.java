package com.store_sample.store.domain.messages.model;

import com.store_sample.store.domain.users.model.DetailUserModel;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageReturnModel {

  private String id;

  private String text;

  private DetailUserModel user;

  private LocalDateTime timestamp;
}

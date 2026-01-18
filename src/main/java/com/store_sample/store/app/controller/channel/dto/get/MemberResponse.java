package com.store_sample.store.app.controller.channel.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MemberResponse {

  int userId;
  String username;
}

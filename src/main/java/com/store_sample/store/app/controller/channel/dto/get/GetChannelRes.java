package com.store_sample.store.app.controller.channel.dto.get;

import java.util.List;
import lombok.Data;

@Data
public class GetChannelRes {

  private int id;
  private String name;
  List<MemberResponse> members;
}


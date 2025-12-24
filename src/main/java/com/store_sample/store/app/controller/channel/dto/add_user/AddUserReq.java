package com.store_sample.store.app.controller.channel.dto.add_user;

import java.util.List;
import lombok.Data;

@Data
public class AddUserReq {
  
  private List<Integer> userIds;
}
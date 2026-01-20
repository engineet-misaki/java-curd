package com.store_sample.store.app.controller.channel.dto.delete_user;

import java.util.List;
import lombok.Data;

@Data
public class DeleteUserReq {

  private List<Integer> userIds;
}
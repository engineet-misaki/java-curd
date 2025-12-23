package com.store_sample.store.domain.channels.model;

import com.store_sample.store.infrastructure.users.TblUsers;
import java.util.List;
import lombok.Data;

@Data
public class FindIdChannelModel {

  private int id;
  private String name;
  private List<TblUsers> users;
}

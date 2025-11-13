package com.store_sample.store.domain.channels.model;

import com.store_sample.store.domain.users.model.DetailUserModel;
import java.util.List;
import lombok.Data;

@Data
public class FindAllChannelModel {

  private int id;
  private String name;
  private List<DetailUserModel> userDetails;
}

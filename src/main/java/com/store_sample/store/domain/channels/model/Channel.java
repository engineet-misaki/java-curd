package com.store_sample.store.domain.channels.model;

import com.store_sample.store.domain.auth.model.CustomUserDetails;
import java.util.List;
import lombok.Data;

@Data
public class Channel {

  private int id;
  private String name;
  private List<CustomUserDetails> userDetails;
}

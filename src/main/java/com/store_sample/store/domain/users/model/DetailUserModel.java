package com.store_sample.store.domain.users.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class DetailUserModel {

  public DetailUserModel() {
  }

  @NotNull
  private Integer id;

  @NotNull
  private String username;

  private String role;

  private Boolean enabled;
}
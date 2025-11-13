package com.store_sample.store.domain.users.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class DetailUserWithPasswordModel {

  public DetailUserWithPasswordModel() {
  }

  @NotNull
  private Integer id;

  @NotNull
  private String username;

  @NotNull
  private String password;

  private String role;

  private Boolean enabled;
}
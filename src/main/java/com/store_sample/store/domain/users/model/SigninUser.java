package com.store_sample.store.domain.users.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class SigninUser {

  @NotNull
  private String username;

  @NotNull
  private String password;

  @NotNull
  private String role;

  private Boolean enabled;
}
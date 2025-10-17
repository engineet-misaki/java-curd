package com.store_sample.store.domain.users.model;

import lombok.Data;

@Data
public class SigninUser {

  private String username;
  private String password;
  private String role;
}
package com.store_sample.store.domain.users.service;

import com.store_sample.store.domain.users.model.SigninUser;

public interface UserRepository {

  void insert(SigninUser user);

  SigninUser find(String name);

}

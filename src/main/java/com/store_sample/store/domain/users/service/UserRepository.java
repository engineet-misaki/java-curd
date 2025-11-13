package com.store_sample.store.domain.users.service;

import com.store_sample.store.domain.users.model.CreateUserModel;
import com.store_sample.store.domain.users.model.DetailUserWithPasswordModel;

public interface UserRepository {

  void insert(CreateUserModel user);

  DetailUserWithPasswordModel findWithPassword(String name);

}

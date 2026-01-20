package com.store_sample.store.infrastructure.users;

import com.store_sample.store.domain.users.model.CreateUserModel;
import com.store_sample.store.domain.users.model.DetailUserModel;
import com.store_sample.store.domain.users.model.DetailUserWithPasswordModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(CreateUserModel user);

  DetailUserWithPasswordModel findWithPassword(String name);

  DetailUserModel findById(int id);
}

package com.store_sample.store.infrastructure.users;

import com.store_sample.store.domain.users.model.SigninUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(SigninUser user);

  SigninUser find(String name);
}

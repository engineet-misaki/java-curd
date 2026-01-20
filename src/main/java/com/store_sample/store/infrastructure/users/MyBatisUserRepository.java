package com.store_sample.store.infrastructure.users;

import com.store_sample.store.domain.users.model.CreateUserModel;
import com.store_sample.store.domain.users.model.DetailUserModel;
import com.store_sample.store.domain.users.model.DetailUserWithPasswordModel;
import com.store_sample.store.domain.users.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public void insert(CreateUserModel user) {
    userMapper.insert(user);
  }

  @Override
  public DetailUserWithPasswordModel findWithPassword(String name) {
    return userMapper.findWithPassword(name);
  }

  @Override
  public DetailUserModel findById(int id) {
    return userMapper.findById(id);
  }
}

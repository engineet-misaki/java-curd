package com.store_sample.store.infrastructure.users;

import com.store_sample.store.domain.users.model.SigninUser;
import com.store_sample.store.domain.users.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public void insert(SigninUser user) {
    userMapper.insert(user);
  }

  @Override
  public SigninUser find(String name) {
    return userMapper.find(name);
  }

}

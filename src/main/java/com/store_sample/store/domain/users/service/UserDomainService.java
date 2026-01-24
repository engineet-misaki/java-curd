package com.store_sample.store.domain.users.service;

import com.store_sample.store.domain.users.model.CreateUserModel;
import com.store_sample.store.domain.users.model.DetailUserModel;
import com.store_sample.store.domain.users.model.DetailUserWithPasswordModel;
import com.store_sample.store.infrastructure.jpa.users.JpaUserRepository;
import com.store_sample.store.infrastructure.jpa.users.TblUsers;
import com.store_sample.store.infrastructure.mybatis.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {

  private final UserRepository userRepository;
  private final JpaUserRepository jpaUserRepository;

  public CreateUserModel save(CreateUserModel user) {
    userRepository.insert(user);

    return user;
  }

  public DetailUserWithPasswordModel findWithPassword(String name) {
    return userRepository.findWithPassword(name);
  }

  public DetailUserModel MybatisFindById(int id) {
    return userRepository.findById(id);
  }

  public TblUsers findById(int id) {
    return jpaUserRepository.findById(id).orElse(null);
  }

  public TblUsers findByName(String name) {
    return jpaUserRepository.findByUsername(name).orElse(null);
  }
}

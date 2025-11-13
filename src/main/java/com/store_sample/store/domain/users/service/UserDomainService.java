package com.store_sample.store.domain.users.service;

import com.store_sample.store.domain.users.model.CreateUserModel;
import com.store_sample.store.domain.users.model.DetailUserWithPasswordModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {

  private final UserRepository userRepository;

  public CreateUserModel save(CreateUserModel user) {
    userRepository.insert(user);

    return user;
  }

  public DetailUserWithPasswordModel findWithPassword(String name) {
    return userRepository.findWithPassword(name);
  }
}

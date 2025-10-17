package com.store_sample.store.domain.users.service;

import com.store_sample.store.domain.users.model.SigninUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {

  private final UserRepository userRepository;

  public SigninUser save(SigninUser user) {
    userRepository.insert(user);

    return user;
  }

  public SigninUser find(String name) {
    return userRepository.find(name);
  }
}

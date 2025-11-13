package com.store_sample.store.domain.auth.service;

import com.store_sample.store.domain.auth.model.CustomUserDetails;
import com.store_sample.store.domain.users.model.DetailUserWithPasswordModel;
import com.store_sample.store.domain.users.service.UserRepository;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    DetailUserWithPasswordModel user = userRepository.findWithPassword(username);

    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));

    return new CustomUserDetails(
        user.getUsername(),
        user.getPassword(),
        user.getRole(),
        authorities
    );
  }
}
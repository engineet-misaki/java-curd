package com.store_sample.store.domain.auth.service;

import com.store_sample.store.domain.auth.model.CustomUserDetails;
import com.store_sample.store.domain.users.model.DetailUserWithPasswordModel;
import com.store_sample.store.domain.users.service.UserDomainService;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserDomainService userDomainService;

  public CustomUserDetailsService(UserDomainService userDomainService) {
    this.userDomainService = userDomainService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    DetailUserWithPasswordModel user = userDomainService.findWithPassword(username);

    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));

    return new CustomUserDetails(
        user.getUsername(),
        user.getPassword(),
        user.getRole(),
        authorities
    );
  }
}
package com.store_sample.store.domain.auth.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private final String username;
  private final String password;
  private final String role;
  private final Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(String username, String password, String role,
      Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.authorities = authorities;
  }

  public String getRole() {
    return role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
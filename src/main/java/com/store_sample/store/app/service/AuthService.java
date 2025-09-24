package com.store_sample.store.app.service;

import com.store_sample.store.domain.auth.model.SigninUser;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

  private final JwtEncoder encoder;
  private final UserDetailsManager users;
  private final PasswordEncoder passwordEncoder;


  public String issueToken(Authentication authentication) {
    Instant now = Instant.now();

    // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self") // トークン発行者
                .issuedAt(now) // トークン発行日時
                // トークンの有効期限を1時間とする
                .expiresAt(now.plusSeconds(36000L)) // トークン有効期限
                .subject(authentication.getName()) // 認証ユーザーの名前
                .build();
        // @formatter:on
    return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }


  public void addUser(SigninUser signinUser) {
    UserDetails user = User.builder()
        .username(signinUser.getUsername())
        .password(passwordEncoder.encode(signinUser.getPassword()))
        .roles("USER")
        .build();
    users.createUser(user);
  }
}

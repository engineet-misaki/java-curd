package com.store_sample.store.app.service.auth;

import com.store_sample.store.domain.users.model.SigninUser;
import com.store_sample.store.domain.users.service.UserDomainService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

  private final JwtEncoder encoder;
  private final UserDomainService users;
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

    SigninUser user = new SigninUser(signinUser.getUsername(),
        passwordEncoder.encode(signinUser.getPassword()),
        "USER");
    user.setEnabled(true);
    users.save(user);
  }
}

package com.store_sample.store.app.controller;

import com.store_sample.store.app.service.AuthService;
import com.store_sample.store.domain.auth.dto.JwtResponse;
import com.store_sample.store.domain.auth.dto.LoginRequest;
import com.store_sample.store.domain.auth.service.CustomUserDetailsService;
import com.store_sample.store.domain.auth.service.JwtService;
import com.store_sample.store.domain.users.model.SigninUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final CustomUserDetailsService userRepository;

  @PostMapping("/token")
  public String token(Authentication authentication) {
    return authService.issueToken(authentication);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    // username/passwordで認証チェック
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    // ユーザー情報取得
    var user = userRepository.loadUserByUsername(request.getUsername());

    // JWT発行
    String token = jwtService.generateToken(user);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  @PostMapping("/signup")
  public SigninUser signup(@RequestBody SigninUser signinUser) {
    authService.addUser(signinUser);
    return signinUser;
  }
}

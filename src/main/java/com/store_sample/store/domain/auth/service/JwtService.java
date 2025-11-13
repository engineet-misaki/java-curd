package com.store_sample.store.domain.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.File;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  public String generateToken(UserDetails userDetails) throws Exception {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1日
        .signWith(getSignKey(), SignatureAlgorithm.RS256)
        .compact();
  }

  public String extractUsername(String token) throws Exception {
    return Jwts.parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) throws Exception {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) throws Exception {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) throws Exception {
    return Jwts.parserBuilder()
        .setSigningKey(getPublicKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getExpiration();
  }

  private Key getSignKey() throws Exception {
    String key = Files.readString(new File("src/main/resources/app.key").toPath())
        .replaceAll("-----BEGIN (.*)-----", "")
        .replaceAll("-----END (.*)-----", "")
        .replaceAll("\\s", "");

    byte[] decoded = Base64.getDecoder().decode(key);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePrivate(keySpec);
  }

  private PublicKey getPublicKey() throws Exception {
    String key = Files.readString(new File("src/main/resources/app.pub.key").toPath())
        .replaceAll("-----BEGIN (.*)-----", "")
        .replaceAll("-----END (.*)-----", "")
        .replaceAll("\\s", "");

    byte[] decoded = Base64.getDecoder().decode(key);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(keySpec);
  }
}


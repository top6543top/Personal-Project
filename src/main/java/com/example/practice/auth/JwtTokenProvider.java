package com.example.practice.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.access.expiration}")
  private Long accessTokenValidationTime;

  @Value("${jwt.refresh.expiration}")
  private Long refreshTokenValidationTime;

  private Key key;

  @PostConstruct
  protected void init() {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String createAccessToken(String username) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + accessTokenValidationTime);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key)
        .compact();
  }

  public String createRefreshToken(String username) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + refreshTokenValidationTime);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key)
        .compact();
  }

  public String getUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}

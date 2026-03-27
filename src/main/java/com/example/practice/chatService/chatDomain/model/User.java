package com.example.practice.chatService.chatDomain.model;

public class User {
  private final Long id;
  private final String loginId;
  private final String password;
  private final String name;

  public User(Long id, String loginId, String password, String name) {
    if (loginId == null || loginId.isBlank()) {
      throw new IllegalArgumentException("LoginId is necessary");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalArgumentException("Password is necessary");
    }

    this.id = id;
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }

  public String getLoginId() {
    return loginId;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }
}

package com.example.practice.chatService.chatDomain.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatRoom {
  private Long id;
  private Set<Long> userIds;
  private LocalDateTime createdAt;

  public ChatRoom(Long id, Set<Long> userIds, LocalDateTime createdAt) {
    if (userIds == null || userIds.size() == 1) {
      throw new IllegalArgumentException("채팅방은 반드시 한명이상의 유저가 있어야 합니다.");
    }

    this.id = id;
    this.userIds = userIds;
    this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
  }

  public static ChatRoom createChatRoom(Long requesterId, Set<Long> targetUserIds) {
    Set<Long> allMembers = new HashSet<>();

    if (requesterId != null) {
      allMembers.add(requesterId);
    }

    if (targetUserIds != null) {
      allMembers.addAll(targetUserIds);
    }

    if (allMembers.size() < 2) {
      throw new IllegalArgumentException("자기 자신 외에 최소 1명 이상의 초대 대상이 필요합니다.");
    }

    return new ChatRoom(null, Set.copyOf(allMembers), LocalDateTime.now());
  }

  public boolean hasMember(Long userId) {
    return userIds.contains(userId);
  }

  public Set<Long> getUserIds() {
    return userIds;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getCreatedAt(){ return createdAt; }
}

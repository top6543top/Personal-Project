package com.example.practice.chatService.chatDomain.model;

import java.time.LocalDateTime;

public class ChatMessage {
  private final Long id;
  private final Long senderId;
  private final Long roomId;
  private final String content;
  private final LocalDateTime createdAt;

  public ChatMessage(Long id, Long senderId, Long roomId, String content, LocalDateTime createdAt) {
    if (senderId == null || roomId == null) {
      throw new IllegalArgumentException("발송자와 방 정보는 존재해야합니다.");
    }
    if (content == null || content.trim().isEmpty()) {
      throw new IllegalArgumentException("보내는 메시지는 존재해야합니다..");
    }

    this.id = id;
    this.senderId = senderId;
    this.roomId = roomId;
    this.content = content;
    this.createdAt = createdAt;
  }

  public static ChatMessage send(ChatRoom chatRoom, Long senderId, String content) {
    if (!chatRoom.hasMember(senderId)) {
      throw new IllegalArgumentException("채팅방에 속하는 유저만 메시지를 보낼 수 있습니다.");
    }
    return new ChatMessage(null, senderId, chatRoom.getId(), content, LocalDateTime.now());
  }

  public Long getId() {
    return id;
  }

  public Long getSenderId() {
    return senderId;
  }

  public Long getRoomId() {
    return roomId;
  }

  public String getContent() {
    return content;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}

package com.example.practice.chatService.chatAdapter.broker;

import com.example.practice.chatService.chatDomain.model.ChatMessage;
import java.time.LocalDateTime;

public record ChatMessageRedisDto(
    Long id,
    Long senderId,
    Long roomId,
    String content,
    LocalDateTime createdAt
) {
  public static ChatMessageRedisDto from(ChatMessage chatMessage) {
    return new ChatMessageRedisDto(
        chatMessage.getId(),
        chatMessage.getSenderId(),
        chatMessage.getRoomId(),
        chatMessage.getContent(),
        chatMessage.getCreatedAt());
  }
}

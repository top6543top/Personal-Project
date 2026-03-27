package com.example.practice.chatService.chatApplication.port.in.command;

public record SendMessageCommand(Long roomId, Long senderId, String content) {
  public SendMessageCommand {
    if (roomId == null || senderId == null || content == null) {
      throw new IllegalArgumentException("채팅을 보내기 위한 필수 정보가 부족합니다.");
    }
  }
}

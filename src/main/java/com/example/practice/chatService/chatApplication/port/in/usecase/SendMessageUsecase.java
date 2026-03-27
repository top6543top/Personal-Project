package com.example.practice.chatService.chatApplication.port.in.usecase;

import com.example.practice.chatService.chatApplication.port.in.command.SendMessageCommand;

public interface SendMessageUsecase {
  void sendMessage(SendMessageCommand sendMessageRequest);
}

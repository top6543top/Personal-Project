package com.example.practice.chatService.chatApplication.port.out;

import com.example.practice.chatService.chatDomain.model.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public interface MessagePublishPort {
  void publishMessage(ChatMessage chatMessage);
}

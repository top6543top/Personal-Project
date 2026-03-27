package com.example.practice.chatService.chatApplication.port.out;

import com.example.practice.chatService.chatDomain.model.ChatRoom;
import org.springframework.stereotype.Component;

@Component
public interface LoadRoomPort {
  ChatRoom loadRoom(Long roomId);
}

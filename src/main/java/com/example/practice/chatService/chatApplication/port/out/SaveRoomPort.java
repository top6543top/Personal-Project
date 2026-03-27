package com.example.practice.chatService.chatApplication.port.out;

import com.example.practice.chatService.chatDomain.model.ChatRoom;

public interface SaveRoomPort {
    ChatRoom save(ChatRoom chatRoom);
}

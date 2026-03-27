package com.example.practice.chatService.chatApplication.port.in.usecase;

import com.example.practice.chatService.chatApplication.port.in.command.CreateRoomCommand;
import com.example.practice.chatService.chatDomain.model.ChatRoom;

public interface CreateRoomUseCase {
    ChatRoom createGroupRoom(CreateRoomCommand createRoomRequest);
}

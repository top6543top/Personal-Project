package com.example.practice.chatService.chatAdapter.in.web;

import com.example.practice.chatService.chatApplication.port.in.command.CreateRoomCommand;
import com.example.practice.chatService.chatApplication.port.in.usecase.CreateRoomUseCase;
import com.example.practice.chatService.chatDomain.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final CreateRoomUseCase createRoomUseCase;

    @PostMapping
    public ResponseEntity<Long> createRoom(@RequestBody CreateRoomCommand createRoomRequest, Principal principal){
        Long requesterId = Long.parseLong(principal.getName());
        CreateRoomCommand createRoomCommand = new CreateRoomCommand(requesterId, createRoomRequest.targetUserIds());
        ChatRoom chatRoom = createRoomUseCase.createGroupRoom(createRoomCommand);
        return ResponseEntity.ok(chatRoom.getId());
    }
}

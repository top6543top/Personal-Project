package com.example.practice.chatService.chatApplication.service;

import com.example.practice.chatService.chatApplication.port.in.command.CreateRoomCommand;
import com.example.practice.chatService.chatApplication.port.in.usecase.CreateRoomUseCase;
import com.example.practice.chatService.chatApplication.port.out.LoadUserPort;
import com.example.practice.chatService.chatApplication.port.out.SaveRoomPort;
import com.example.practice.chatService.chatDomain.model.ChatRoom;
import com.example.practice.chatService.chatDomain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRoomService implements CreateRoomUseCase {

    private final SaveRoomPort saveRoomPort;
    private final LoadUserPort loadUserPort;

    @Override
    public ChatRoom createGroupRoom(CreateRoomCommand createRoomRequest){
        for (Long targetUserId : createRoomRequest.targetUserIds()){
            User targetuser = loadUserPort.loadUser(targetUserId);
            if(targetuser == null){
                throw new IllegalArgumentException("유저가 존재하지 않습니다.");
            }
        }

        ChatRoom chatRoom = ChatRoom.createChatRoom(createRoomRequest.requesterId(),
                createRoomRequest.targetUserIds());

        return saveRoomPort.save(chatRoom);
    }

}

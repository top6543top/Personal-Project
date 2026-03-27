package com.example.practice.chatService.chatAdapter.out.persistence;

import com.example.practice.chatService.chatAdapter.out.persistence.entity.ChatRoomEntity;
import com.example.practice.chatService.chatAdapter.out.persistence.repository.ChatRoomRepository;
import com.example.practice.chatService.chatApplication.port.out.LoadRoomPort;
import com.example.practice.chatService.chatApplication.port.out.SaveRoomPort;
import com.example.practice.chatService.chatDomain.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomPersistenceAdapter implements LoadRoomPort, SaveRoomPort {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom save(ChatRoom chatRoom){
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity(chatRoom.getUserIds(), chatRoom.getCreatedAt());
        ChatRoomEntity saveRoomEntity = chatRoomRepository.save(chatRoomEntity);
        return new ChatRoom(saveRoomEntity.getId(), saveRoomEntity.getMemberIds(), saveRoomEntity.getCreatedAt());
    }

    @Override
    public ChatRoom loadRoom(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .map(entity -> new ChatRoom(entity.getId(), entity.getMemberIds(), entity.getCreatedAt()))
                .orElse(null);
    }
}

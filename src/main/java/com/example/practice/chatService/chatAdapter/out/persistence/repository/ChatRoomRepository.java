package com.example.practice.chatService.chatAdapter.out.persistence.repository;

import com.example.practice.chatService.chatAdapter.out.persistence.entity.ChatRoomEntity;
import com.example.practice.chatService.chatDomain.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity,Long> {
}

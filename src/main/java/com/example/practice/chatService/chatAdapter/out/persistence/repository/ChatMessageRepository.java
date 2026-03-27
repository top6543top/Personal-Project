package com.example.practice.chatService.chatAdapter.out.persistence.repository;

import com.example.practice.chatService.chatAdapter.out.persistence.entity.ChatMessageEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
}

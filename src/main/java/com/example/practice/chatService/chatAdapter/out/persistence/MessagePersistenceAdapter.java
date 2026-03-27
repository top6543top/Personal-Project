package com.example.practice.chatService.chatAdapter.out.persistence;

import com.example.practice.chatService.chatAdapter.out.persistence.entity.ChatMessageEntity;
import com.example.practice.chatService.chatAdapter.out.persistence.repository.ChatMessageRepository;
import com.example.practice.chatService.chatApplication.port.out.SaveMessagePort;
import com.example.practice.chatService.chatDomain.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagePersistenceAdapter implements SaveMessagePort {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage save(ChatMessage chatMessage){
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity(chatMessage.getRoomId(), chatMessage.getSenderId(),
                chatMessage.getContent(), chatMessage.getCreatedAt());

        ChatMessageEntity saveEntity = chatMessageRepository.save(chatMessageEntity);

        return new ChatMessage(
                saveEntity.getId(),
                saveEntity.getRoomId(),
                saveEntity.getSenderId(),
                saveEntity.getContent(),
                saveEntity.getCreatedAt()
        );
    }
}

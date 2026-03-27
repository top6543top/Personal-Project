package com.example.practice.chatService.chatAdapter.out.broker;

import com.example.practice.chatService.chatAdapter.broker.ChatMessageRedisDto;
import com.example.practice.chatService.chatApplication.port.out.MessagePublishPort;
import com.example.practice.chatService.chatDomain.model.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompMessagePublishAdapter implements MessagePublishPort {

  private static final String CHANNEL_PREFIX = "chat:room:";

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  @Override
  public void publishMessage(ChatMessage chatMessage) {
    try {
      ChatMessageRedisDto dto = ChatMessageRedisDto.from(chatMessage);
      String payload = objectMapper.writeValueAsString(dto);
      redisTemplate.convertAndSend(CHANNEL_PREFIX + chatMessage.getRoomId(), payload);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Redis 메시지 직렬화 실패", e);
    }
  }
}

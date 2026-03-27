package com.example.practice.chatService.chatAdapter.in.broker;

import com.example.practice.chatService.chatAdapter.broker.ChatMessageRedisDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageListener {

  private static final String TOPIC_PREFIX = "/topic/rooms/";

  private final ObjectMapper objectMapper;
  private final RedisTemplate<String, Object> redisTemplate;
  private final SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    try {
      ChatMessageRedisDto chatMessage =
          objectMapper.readValue(message.getBody(), ChatMessageRedisDto.class);
      simpMessagingTemplate.convertAndSend(TOPIC_PREFIX + chatMessage.roomId(), chatMessage);
    } catch (IOException e) {
      log.error("Redis 메시지 역직렬화 실패: {}", e.getMessage());
    }
  }
}

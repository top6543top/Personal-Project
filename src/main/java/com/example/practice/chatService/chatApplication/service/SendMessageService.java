package com.example.practice.chatService.chatApplication.service;

import com.example.practice.chatService.chatApplication.port.in.command.SendMessageCommand;
import com.example.practice.chatService.chatApplication.port.in.usecase.SendMessageUsecase;
import com.example.practice.chatService.chatApplication.port.out.LoadRoomPort;
import com.example.practice.chatService.chatApplication.port.out.MessagePublishPort;
import com.example.practice.chatService.chatApplication.port.out.SaveMessagePort;
import com.example.practice.chatService.chatDomain.model.ChatMessage;
import com.example.practice.chatService.chatDomain.model.ChatRoom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SendMessageService implements SendMessageUsecase {

  private final LoadRoomPort loadRoomPort;
  private final MessagePublishPort messagePublishPort;
  private final SaveMessagePort saveMessagePort;

  @Override
  public void sendMessage(SendMessageCommand sendMessageRequest) {
    ChatRoom chatRoom = loadRoomPort.loadRoom(sendMessageRequest.roomId());
    if (chatRoom == null){
      throw new IllegalArgumentException("존재하지 않는 채팅방입니다.");
    }

    ChatMessage message = ChatMessage.send(chatRoom, sendMessageRequest.senderId(), sendMessageRequest.content());
    ChatMessage saveMessage = saveMessagePort.save(message);
    messagePublishPort.publishMessage(saveMessage);
  }
}

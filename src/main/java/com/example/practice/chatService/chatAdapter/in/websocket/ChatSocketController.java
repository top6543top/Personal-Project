package com.example.practice.chatService.chatAdapter.in.websocket;

import com.example.practice.chatService.chatAdapter.in.websocket.request.ChatMessageRequest;
import com.example.practice.chatService.chatApplication.port.in.command.SendMessageCommand;
import com.example.practice.chatService.chatApplication.port.in.usecase.SendMessageUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatSocketController {

    private final SendMessageUsecase sendMessageUsecase;

    @MessageMapping("/rooms/{roomId}/Messages")
    public void sendMessage(
            @DestinationVariable Long roomId,
            @Payload ChatMessageRequest request,
            Principal principal
            ) {

        if (principal == null) {
            throw new IllegalStateException("인증된 사용자 정보가 없습니다.");
        }

        Long senderId = Long.parseLong(principal.getName());
        SendMessageCommand sendMessageCommand = new SendMessageCommand(
                roomId,
                senderId,
                request.content()
        );

        sendMessageUsecase.sendMessage(sendMessageCommand);

    }
}

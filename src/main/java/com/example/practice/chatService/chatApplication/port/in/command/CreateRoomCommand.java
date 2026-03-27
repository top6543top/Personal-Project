package com.example.practice.chatService.chatApplication.port.in.command;

import java.util.Set;

public record CreateRoomCommand(
        Long requesterId,
        Set<Long> targetUserIds
) {
    public CreateRoomCommand {
        if (requesterId == null || targetUserIds == null || targetUserIds.isEmpty()) {
            throw new IllegalArgumentException("요청자 식별자와 대상자 식별자는 필수입니다.");
        }
        if (targetUserIds.contains(requesterId)) {
            throw new IllegalArgumentException("자기 자신과 채팅방을 생성할 수 없습니다.");
        }
    }
}

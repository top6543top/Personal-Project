package com.example.practice.chatService.chatAdapter.in.web.request;

import java.util.Set;

public record CreateRoomRequest(Set<Long> targetUserIds) {
}

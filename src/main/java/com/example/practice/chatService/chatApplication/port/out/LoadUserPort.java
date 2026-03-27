package com.example.practice.chatService.chatApplication.port.out;

import com.example.practice.chatService.chatDomain.model.User;

public interface LoadUserPort {
    User loadUser(Long userId);
}

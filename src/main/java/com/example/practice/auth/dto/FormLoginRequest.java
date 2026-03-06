package com.example.practice.auth.dto;

public record FormLoginRequest(
        String loginId,
        String password
) {
}

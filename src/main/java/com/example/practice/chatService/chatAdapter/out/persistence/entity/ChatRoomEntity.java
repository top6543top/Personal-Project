package com.example.practice.chatService.chatAdapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "chat_room_members", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "user_id")
    private Set<Long> memberIds = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public ChatRoomEntity(Set<Long> memberIds, LocalDateTime createdAt){
        this.memberIds = memberIds;
        this.createdAt = createdAt;
    }
}

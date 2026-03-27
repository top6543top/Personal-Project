# ChatService

헥사고날 아키텍쳐로 구현


```
chatService/                                                                                                                                                                                                                      
  ├── chatAdapter/                                                                                                                                                                                                                
  │   ├── broker/                                                                                                                                                                                                                   
  │   │   └── ChatMessageRedisDto.java                                                                                                                                                                                              
  │   ├── in/                                                                                                                                                                                                                       
  │   │   ├── broker/                                                                                                                                                                                                               
  │   │   │   └── RedisMessageSubscriber.java
  │   │   ├── web/
  │   │   │   ├── request/
  │   │   │   │   └── CreateRoomRequest.java
  │   │   │   └── ChatRoomController.java
  │   │   └── websocket/
  │   │       ├── request/
  │   │       │   └── ChatMessageRequest.java
  │   │       ├── ChatSocketController.java
  │   │       └── StompConnectionInterceptor.java
  │   └── out/
  │       ├── broker/
  │       │   └── StompMessagePublishAdapter.java
  │       └── persistence/
  │           ├── entity/
  │           │   ├── ChatMessageEntity.java
  │           │   └── ChatRoomEntity.java
  │           ├── repository/
  │           │   ├── ChatMessageRepository.java
  │           │   └── ChatRoomRepository.java
  │           ├── MessagePersistenceAdapter.java
  │           └── RoomPersistenceAdapter.java
  ├── chatApplication/
  │   ├── port/
  │   │   ├── in/
  │   │   │   ├── command/
  │   │   │   │   ├── CreateRoomCommand.java
  │   │   │   │   └── SendMessageCommand.java
  │   │   │   └── usecase/
  │   │   │       ├── CreateRoomUseCase.java
  │   │   │       └── SendMessageUsecase.java
  │   │   └── out/
  │   │       ├── LoadRoomPort.java
  │   │       ├── LoadUserPort.java
  │   │       ├── MessagePublishPort.java
  │   │       ├── SaveMessagePort.java
  │   │       └── SaveRoomPort.java
  │   └── service/
  │       ├── CreateRoomService.java
  │       └── SendMessageService.java
  ├── chatDomain/
  │   └── model/
  │       ├── ChatMessage.java
  │       ├── ChatRoom.java
  │       └── User.java
  └── config/
      ├── RedisConfig.java
      └── WebSocketConfig.java
```

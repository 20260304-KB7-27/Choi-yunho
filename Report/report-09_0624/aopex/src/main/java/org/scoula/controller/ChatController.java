package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.chat.domain.ChatMessage;
import org.scoula.chat.domain.ChatRoom;
import org.scoula.chat.service.ChatRoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping({"/", "/chat"})
    public String chatPage() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/api/rooms")
    public List<ChatRoom> rooms() {
        return chatRoomService.findAllRooms();
    }

    @ResponseBody
    @PostMapping("/api/rooms")
    public ChatRoom createRoom(@RequestParam("roomName") String roomName) {
        ChatRoom room = chatRoomService.createRoom(roomName);
        broadcastRoomList();
        return room;
    }

    @MessageMapping("/chat.enter")
    public void enter(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        String sender = message.getSender() == null || message.getSender().isBlank() ? "익명" : message.getSender();
        if (headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());
            headerAccessor.getSessionAttributes().put("sender", sender);
        }

        ChatRoom room = chatRoomService.enterRoom(message.getRoomId(), sessionId);

        messagingTemplate.convertAndSend("/topic/rooms", chatRoomService.findAllRooms());
        messagingTemplate.convertAndSend("/topic/chat/" + room.getRoomId(),
                ChatMessage.builder()
                        .type(ChatMessage.MessageType.SYSTEM)
                        .roomId(room.getRoomId())
                        .roomName(room.getRoomName())
                        .sender("system")
                        .content(sender + " 님이 입장했습니다.")
                        .build());
    }

    @MessageMapping("/chat.send")
    public void send(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        if (message.getRoomId() == null || message.getRoomId().isBlank()) {
            return;
        }
        message.setSessionId(sessionId);
        message.setType(ChatMessage.MessageType.CHAT);
        messagingTemplate.convertAndSend("/topic/chat/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat.leave")
    public void leave(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        String sender = message.getSender();
        if ((sender == null || sender.isBlank()) && headerAccessor.getSessionAttributes() != null) {
            sender = (String) headerAccessor.getSessionAttributes().get("sender");
        }
        if (sender == null || sender.isBlank()) {
            sender = "익명";
        }
        String roomId = message.getRoomId();
        if ((roomId == null || roomId.isBlank()) && headerAccessor.getSessionAttributes() != null) {
            roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        }
        if (roomId == null) {
            return;
        }

        ChatRoom room = chatRoomService.leaveRoom(roomId, sessionId);
        if (headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().remove("roomId");
            headerAccessor.getSessionAttributes().remove("sender");
        }
        if (room != null) {
            messagingTemplate.convertAndSend("/topic/rooms", chatRoomService.findAllRooms());
            messagingTemplate.convertAndSend("/topic/chat/" + room.getRoomId(),
                    ChatMessage.builder()
                            .type(ChatMessage.MessageType.SYSTEM)
                            .roomId(room.getRoomId())
                            .roomName(room.getRoomName())
                            .sender("system")
                            .content(sender + " 님이 퇴장했습니다.")
                            .build());
        }
    }

    @EventListener
    public void onSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        String roomId = chatRoomService.leaveSession(sessionId);
        if (roomId != null) {
            messagingTemplate.convertAndSend("/topic/rooms", chatRoomService.findAllRooms());
        }
    }

    private void broadcastRoomList() {
        messagingTemplate.convertAndSend("/topic/rooms", chatRoomService.findAllRooms());
    }
}

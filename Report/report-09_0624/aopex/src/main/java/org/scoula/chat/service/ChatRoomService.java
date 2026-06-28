package org.scoula.chat.service;

import org.scoula.chat.domain.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatRoomService {

    private final Map<String, ChatRoomState> rooms = new LinkedHashMap<>();
    private final Map<String, String> sessionRoomMap = new ConcurrentHashMap<>();

    public synchronized List<ChatRoom> findAllRooms() {
        List<ChatRoom> result = new ArrayList<>();
        for (ChatRoomState state : rooms.values()) {
            result.add(state.toDto());
        }
        result.sort(Comparator.comparing(ChatRoom::getRoomName));
        return result;
    }

    public synchronized ChatRoom createRoom(String roomName) {
        String trimmed = Optional.ofNullable(roomName).orElse("").trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("대화방 이름을 입력해 주세요.");
        }

        String roomId = UUID.randomUUID().toString().substring(0, 8);
        ChatRoomState state = new ChatRoomState(roomId, trimmed);
        rooms.put(roomId, state);
        return state.toDto();
    }

    public synchronized ChatRoom enterRoom(String roomId, String sessionId) {
        ChatRoomState state = getRoomState(roomId);
        if (state == null) {
            throw new IllegalArgumentException("존재하지 않는 대화방입니다.");
        }

        String previousRoomId = sessionRoomMap.get(sessionId);
        if (roomId.equals(previousRoomId)) {
            return state.toDto();
        }

        sessionRoomMap.put(sessionId, roomId);
        if (previousRoomId != null) {
            ChatRoomState previousState = getRoomState(previousRoomId);
            if (previousState != null && previousState.memberCount > 0) {
                previousState.memberCount--;
            }
            state = getRoomState(roomId);
        }

        state.memberCount++;
        return state.toDto();
    }

    public synchronized ChatRoom leaveRoom(String roomId, String sessionId) {
        ChatRoomState state = getRoomState(roomId);
        if (state == null) {
            return null;
        }

        String mappedRoomId = sessionRoomMap.get(sessionId);
        if (roomId.equals(mappedRoomId)) {
            sessionRoomMap.remove(sessionId);
            if (state.memberCount > 0) {
                state.memberCount--;
            }
        }

        if (state.memberCount < 0) {
            state.memberCount = 0;
        }

        return state.toDto();
    }

    public synchronized String leaveSession(String sessionId) {
        String roomId = sessionRoomMap.remove(sessionId);
        if (roomId == null) {
            return null;
        }
        ChatRoomState state = getRoomState(roomId);
        if (state != null && state.memberCount > 0) {
            state.memberCount--;
        }
        return roomId;
    }

    public synchronized ChatRoom getRoom(String roomId) {
        ChatRoomState state = getRoomState(roomId);
        return state == null ? null : state.toDto();
    }

    private ChatRoomState getRoomState(String roomId) {
        return rooms.get(roomId);
    }

    private static class ChatRoomState {
        private final String roomId;
        private final String roomName;
        private int memberCount;

        private ChatRoomState(String roomId, String roomName) {
            this.roomId = roomId;
            this.roomName = roomName;
            this.memberCount = 0;
        }

        private ChatRoom toDto() {
            return ChatRoom.builder()
                    .roomId(roomId)
                    .roomName(roomName)
                    .memberCount(memberCount)
                    .build();
        }
    }
}

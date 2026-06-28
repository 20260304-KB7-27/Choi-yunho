package org.scoula.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    private MessageType type;
    private String roomId;
    private String sender;
    private String content;
    private String sessionId;
    private String roomName;

    public enum MessageType {
        ENTER,
        CHAT,
        LEAVE,
        SYSTEM
    }
}

package org.scoula.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // WebSocket / STOMP 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 클라이언트가 WebSocket을 최초 연결할 때 사용할 엔드포인트
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-app") // ws://localhost:8080/chat-app
                .setAllowedOrigins("*"); // CORS 허용
    }

    //
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // /topic 시작하는 경로는 브로커가 처리 (서버 -> 클라이언트)
        // /topic/chat, /topic/greeting
        registry.enableSimpleBroker("/topic");

        // /app/chat (클라이언트 -> 서버)
        // 사용자가 메세지를 보내면 서버에서 @MessageMapping("/chat") 으로 연결
        registry.setApplicationDestinationPrefixes("/app");
    }
}

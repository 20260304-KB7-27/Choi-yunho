package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.domain.ChatMessage;
import org.scoula.domain.GreetingMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class ChatController {

    /*
    * 입장 알림 처리
    * Client : /app/hello ->
    * Server : /topic/greetings 구독하고 있는 모든 클라이언트에게 GreetingMessage 전달
    * */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingMessage greetingMessage(GreetingMessage message) {

        return message;
    }
    @MessageMapping("/chat")    // app/chat으로 발행된 메시지 수신
    @SendTo("/topic/greetings") // 처리결과를 chat 구독자들에게 전송
    public ChatMessage chat(ChatMessage message) {

        return message;
    }
}

package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import pe.edu.upao.bookchange.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Tag(name = "Chat", description = "API de gestión de mensajería")
@Controller
public class WebSocketController {
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
        System.out.println(message);
        return new ChatMessage(message.getMessage(), message.getUser());
    }
}

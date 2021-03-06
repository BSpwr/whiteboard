package com.techstars.whiteboard.controller;

import com.techstars.whiteboard.bean.MessageBean;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public SocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/whiteboard/line/{sessionID}")
    public void sendLine(@Payload MessageBean message, @DestinationVariable String sessionID) {
        simpMessagingTemplate.convertAndSend("/topic/whiteboard/line/" + sessionID, message);
    }

    @MessageMapping("/whiteboard/clear/{sessionID}")
    public void sendClear(@Payload String message, @DestinationVariable String sessionID) {
        simpMessagingTemplate.convertAndSend("/topic/whiteboard/clear/" + sessionID, message);
    }
}
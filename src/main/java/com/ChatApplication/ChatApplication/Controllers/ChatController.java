package com.ChatApplication.ChatApplication.Controllers;

import com.ChatApplication.ChatApplication.DTO.MessageDTO;
import com.ChatApplication.ChatApplication.Model.MessageModel;
import com.ChatApplication.ChatApplication.Services.ChatMessageService;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;


    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ChatMessageService chatMessageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatMessageService = chatMessageService;
    }

    /**
     * Receives a message from a user, stores it in the database,
     * and sends it in real-time to the recipient only if they have that chat open.
     */
    @MessageMapping("/chat")
    public void sendMessage(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {

        System.out.println(messageDTO.toString());
        String receiver = messageDTO.getReceiverEmail();
        System.out.println(" Receiver is : " + receiver);

        String senderEmail = (String) headerAccessor.getSessionAttributes().get("user-email");

        if (senderEmail == null) {
            System.out.println("❌ Error: Sender email not found in session!");
            return;
        }

        MessageModel messageModel = chatMessageService.saveMessage(messageDTO);
        simpMessagingTemplate.convertAndSend("/topic/chat-" + receiver, messageModel);

    }

    /**
     * Retrieves chat history between two users when a user opens a chat room.
     */
    @GetMapping("/chat/history/{user1}/{user2}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getChatHistory(@PathVariable String user1, @PathVariable String user2) {
        return chatMessageService.getChatHistory(user1, user2);
    }
}

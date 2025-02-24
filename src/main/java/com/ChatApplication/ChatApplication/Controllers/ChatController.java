package com.ChatApplication.ChatApplication.Controllers;

import com.ChatApplication.ChatApplication.DTO.MessageDTO;
import com.ChatApplication.ChatApplication.Services.ChatMessageService;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ChatController handles real-time messaging and chat history retrieval.
 * It supports sending messages via WebSockets and fetching chat history from the database.
 */
@Controller // Marks this class as a Spring MVC controller for handling chat-related requests
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate; // Used for sending messages in real-time
    private final ChatMessageService chatMessageService; // Service for handling chat-related operations

    /**
     * Constructor for injecting dependencies.
     *
     * @param simpMessagingTemplate Used for real-time WebSocket messaging.
     * @param chatMessageService    Service for chat message storage and retrieval.
     */
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ChatMessageService chatMessageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatMessageService = chatMessageService;
    }

    /**
     * Handles incoming chat messages from users.
     * Stores the message in the database and sends it in real-time to the recipient.
     *
     * @param messageDTO     The message payload received from the sender.
     * @param headerAccessor Provides access to WebSocket session attributes.
     */
    @MessageMapping("/chat") // Maps incoming messages to "/app/chat" (from frontend)
    public void sendMessage(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {

        System.out.println(messageDTO.toString()); // Logs the received message
        String receiver = messageDTO.getReceiverEmail(); // Extracts receiver's email

        // Retrieve sender's email from WebSocket session attributes
        String senderEmail = (String) headerAccessor.getSessionAttributes().get("email");

        if (senderEmail == null) {
            System.out.println("Error: Sender email not found in session!"); // Logs an error if the sender is not found
            return;
        }

        // Store the message in the database
        chatMessageService.saveMessage(messageDTO);

        // Send the message in real-time to the recipient's topic if they have the chat open
        simpMessagingTemplate.convertAndSend("/topic/chat-" + receiver, messageDTO);
    }

    /**
     * Retrieves the chat history between two users.
     * This is called when a user opens a chat room to load previous messages.
     *
     * @param user1 First user's email.
     * @param user2 Second user's email.
     * @return ResponseEntity containing chat history wrapped in ApiResponse.
     */
    @GetMapping("/chat/history/{user1}/{user2}") // Maps HTTP GET request to fetch chat history
    @ResponseBody
    public ResponseEntity<ApiResponse> getChatHistory(@PathVariable String user1, @PathVariable String user2) {
        return chatMessageService.getChatHistory(user1, user2); // Fetch chat history from service
    }

    /**
     * Retrieves all chat messages for a given user.
     * This is useful for displaying a list of conversations in the chat panel.
     *
     * @param email The email of the user whose chats should be fetched.
     * @return ResponseEntity containing all chat messages wrapped in ApiResponse.
     */
    @GetMapping("/chats/all") // Maps HTTP GET request to fetch all chats for a user
    public ResponseEntity<ApiResponse> getAllChats(@RequestParam String email) {
        return chatMessageService.getAllChatMessages(email); // Fetch all chats for the user
    }
}

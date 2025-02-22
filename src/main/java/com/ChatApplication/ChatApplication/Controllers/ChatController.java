package com.ChatApplication.ChatApplication.Controllers;

import com.ChatApplication.ChatApplication.Model.MessageModel;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Services.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;


    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ChatMessageService chatMessageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat")
    public void sendPrivateMessage(@Payload MessageModel messageModel){
        UserModel receiver = messageModel.getReceiver();
        messageModel.setDelivered(false);
        chatMessageService.saveMessage(messageModel);
        simpMessagingTemplate.convertAndSendToUser(receiver.getEmail(),"/queue/messages",messageModel);

    }
}

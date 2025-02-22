package com.ChatApplication.ChatApplication.Services;

import com.ChatApplication.ChatApplication.Model.MessageModel;
import com.ChatApplication.ChatApplication.Repository.ChatMessageRepo;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;

    public ChatMessageService(ChatMessageRepo chatMessageRepo) {
        this.chatMessageRepo = chatMessageRepo;
    }

    public MessageModel saveMessage(MessageModel messageModel) {
        return chatMessageRepo.save(messageModel);
    }

    public ResponseEntity<ApiResponse> getMessage(Integer sId, Integer rId) {
        List<MessageModel> messages = chatMessageRepo.findBySenderAndReceiverOrReceiverAndSender(sId, rId, rId, sId);
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "List of Messages",
                        messages
                ),
                HttpStatus.OK
        );
    }
}

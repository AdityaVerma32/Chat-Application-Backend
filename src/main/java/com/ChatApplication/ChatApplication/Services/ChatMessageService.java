package com.ChatApplication.ChatApplication.Services;

import com.ChatApplication.ChatApplication.Model.MessageModel;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Repository.ChatMessageRepo;
import com.ChatApplication.ChatApplication.Repository.UserRepo;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final UserRepo userRepo;

    public ChatMessageService(ChatMessageRepo chatMessageRepo, UserRepo userRepo) {
        this.chatMessageRepo = chatMessageRepo;
        this.userRepo = userRepo;
    }

    public MessageModel saveMessage(MessageModel messageModel) {
        return chatMessageRepo.save(messageModel);
    }

    public ResponseEntity<ApiResponse> getMessage(Integer sId, Integer rId) {
        Optional<UserModel> sUser = userRepo.findById(sId);
        Optional<UserModel> rUser = userRepo.findById(rId);
        List<MessageModel> messages = chatMessageRepo.findBySenderAndReceiverOrReceiverAndSender(sUser.get(), rUser.get(), rUser.get(), sUser.get());
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

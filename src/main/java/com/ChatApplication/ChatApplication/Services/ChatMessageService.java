package com.ChatApplication.ChatApplication.Services;

import com.ChatApplication.ChatApplication.DTO.MessageDTO;
import com.ChatApplication.ChatApplication.Model.MessageModel;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Projection.MessageProjection;
import com.ChatApplication.ChatApplication.Repository.ChatMessageRepo;
import com.ChatApplication.ChatApplication.Repository.UserRepo;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.http.HttpStatus;
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

    public MessageModel saveMessage(MessageDTO messageDTO) {

        UserModel senderModel = userRepo.findByEmail(messageDTO.getSenderEmail());
        UserModel receiverModel = userRepo.findByEmail(messageDTO.getReceiverEmail());

        MessageModel messageModel = new MessageModel();
        messageModel.setIsRead(false);
        messageModel.setContent(messageDTO.getContent());
        messageModel.setSender(senderModel);
        messageModel.setReceiver(receiverModel);

        messageModel.setIsRead(false);
        return chatMessageRepo.save(messageModel);
    }

    public ResponseEntity<ApiResponse> getMessage(Integer sId, Integer rId) {
        Optional<UserModel> sUser = userRepo.findById(sId);
        Optional<UserModel> rUser = userRepo.findById(rId);
        List<MessageModel> messages = chatMessageRepo.findBySenderAndReceiverOrReceiverAndSender(sUser.get(), rUser.get(), rUser.get(), sUser.get());
        return new ResponseEntity<>(new ApiResponse(true, "List of Messages", messages), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> getChatHistory(String user1, String user2) {
        UserModel sUser = userRepo.findByEmail(user1);
        UserModel rUser = userRepo.findByEmail(user2);
        List<MessageProjection> messagesList = chatMessageRepo.findBySenderAndReceiverOrReceiverAndSenderOrderByTimestamp(sUser, rUser, rUser, sUser);
        return new ResponseEntity<>(new ApiResponse(true, "List of Messages", messagesList), HttpStatus.OK);
    }
}

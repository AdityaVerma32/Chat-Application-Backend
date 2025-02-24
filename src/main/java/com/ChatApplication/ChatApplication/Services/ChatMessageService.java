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

/**
 * Service class for handling chat messages.
 * Provides functionality to save messages, fetch chat history, and retrieve all messages for a user.
 */
@Service
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final UserRepo userRepo;

    /**
     * Constructor to inject repositories.
     *
     * @param chatMessageRepo Repository for handling chat message database operations.
     * @param userRepo Repository for handling user-related database operations.
     */
    public ChatMessageService(ChatMessageRepo chatMessageRepo, UserRepo userRepo) {
        this.chatMessageRepo = chatMessageRepo;
        this.userRepo = userRepo;
    }

    /**
     * Saves a new chat message in the database.
     *
     * @param messageDTO Data transfer object containing sender email, receiver email, and message content.
     * @return The saved MessageModel object.
     */
    public MessageModel saveMessage(MessageDTO messageDTO) {
        // Fetch sender and receiver details from the database using their emails
        UserModel senderModel = userRepo.findByEmail(messageDTO.getSenderEmail());
        UserModel receiverModel = userRepo.findByEmail(messageDTO.getReceiverEmail());

        // Create a new MessageModel object and populate it with details
        MessageModel messageModel = new MessageModel();
        messageModel.setIsRead(false); // Mark message as unread initially
        messageModel.setContent(messageDTO.getContent());
        messageModel.setSender(senderModel);
        messageModel.setReceiver(receiverModel);

        // Save and return the message object
        return chatMessageRepo.save(messageModel);
    }

    /**
     * Retrieves the chat history between two users.
     *
     * @param user1 Email of the first user.
     * @param user2 Email of the second user.
     * @return Response entity containing a list of messages exchanged between the two users.
     */
    public ResponseEntity<ApiResponse> getChatHistory(String user1, String user2) {
        // Fetch user models from the database using emails
        UserModel sUser = userRepo.findByEmail(user1);
        UserModel rUser = userRepo.findByEmail(user2);

        // Retrieve messages exchanged between these two users
        List<MessageProjection> messagesList = chatMessageRepo.findBySenderAndReceiver(sUser.getId(), rUser.getId());

        // Return the messages as part of the API response
        return new ResponseEntity<>(new ApiResponse(true, "List of Messages", messagesList), HttpStatus.OK);
    }

    /**
     * Retrieves all chat messages involving a specific user.
     *
     * @param CurrEmail Email of the current user.
     * @return Response entity containing a list of all messages where the user is either sender or receiver.
     */
    public ResponseEntity<ApiResponse> getAllChatMessages(String CurrEmail) {
        // Fetch the user model from the database using email
        UserModel currUser = userRepo.findByEmail(CurrEmail);

        // Retrieve all messages where the user is either sender or receiver
        List<MessageProjection> messageList = chatMessageRepo.findBySenderOrReceiver(currUser.getId());

        // Return the messages as part of the API response
        return new ResponseEntity<>(new ApiResponse(true, "List Of All Messages.", messageList), HttpStatus.OK);
    }
}

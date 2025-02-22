package com.ChatApplication.ChatApplication.Repository;

import com.ChatApplication.ChatApplication.Model.MessageModel;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<MessageModel, Integer> {
    List<MessageModel> findBySenderAndReceiverOrReceiverAndSender(UserModel sId, UserModel rId, UserModel rId1, UserModel sId1);
}

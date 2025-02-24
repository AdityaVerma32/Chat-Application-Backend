package com.ChatApplication.ChatApplication.Repository;

import com.ChatApplication.ChatApplication.Model.MessageModel;
import com.ChatApplication.ChatApplication.Model.UserModel;
import com.ChatApplication.ChatApplication.Projection.MessageProjection;
import com.ChatApplication.ChatApplication.Utility.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<MessageModel, Integer> {
    @Query("SELECT m FROM MessageModel m WHERE (m.sender.id = :sUserId AND m.receiver.id = :rUserId) " +
            "OR (m.sender.id = :rUserId AND m.receiver.id = :sUserId) ORDER BY m.timestamp")
    List<MessageProjection> findBySenderAndReceiver(Integer sUserId, Integer rUserId);
}
